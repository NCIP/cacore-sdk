package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.UIHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ModelMasterGroupPanel extends GroupPanel {
	
	public ModelMasterGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	public void paint() {
		Composite composite = super.getUIComposite();
		
		final Tree domainTree = new Tree(composite, SWT.SINGLE);
		domainTree.setLayoutData(UIHelper.getFieldGridData());
		
		domainTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TreeItem[] allSelected = domainTree.getSelection();
				if (allSelected.length > 0) {
					TreeItem selectedItem = allSelected[0];
					String modelName = "";
					String categoryName = "";
					if (selectedItem.getItemCount() == 0) {
						modelName = selectedItem.getParentItem().getText();
						categoryName = selectedItem.getText();
					}
					else {
						modelName = selectedItem.getText();
						categoryName = "Meaning";
					}
					Event eve = new ModelSelectionEvent(modelName, categoryName);
					notifyListeners(SWT.Selection, eve);
				}
			}
		});
		
		String[] domains = getDomains();
		String[] categories = Constants.categories;
		
		for (int i = 0; i < domains.length; i++) {
			TreeItem domain = new TreeItem(domainTree, 0);
			domain.setText(domains[i]);
			for (int j = 0; j < categories.length; j++) {
				TreeItem category = new TreeItem(domain, 0);
				category.setText(categories[j]);
				
				if (i == 0 && j == 0) {
					domainTree.setSelection(category);
					String categoryName = category.getText();
					Event event = new ModelSelectionEvent(domain.getText(), categoryName);
					domainTree.notifyListeners(SWT.Selection, event);
				}
			}
		}
	}
	
	private String[] getDomains() {
		//TODO: hard-coded for now.
		String[] domains = {"Person", "Contact", "Address", "Doctor"};
		return domains;
	}
}
