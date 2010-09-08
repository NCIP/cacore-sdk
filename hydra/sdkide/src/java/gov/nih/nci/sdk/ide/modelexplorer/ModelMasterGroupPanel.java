package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.Constants;
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
	
	public void create() {
		String[] domains = {"Person", "Contact", "Address", "Doctor"};
		String[] categories = Constants.categories;
		
		final Tree domainTree = new Tree(getUIComposite(), SWT.SINGLE);
		domainTree.setLayoutData(UIHelper.getFieldGridData());
		
		for (int i = 0; i < domains.length; i++) {
			TreeItem domain = new TreeItem(domainTree, 0);
			domain.setText(domains[i]);
			for (int j = 0; j < categories.length; j++) {
				TreeItem category = new TreeItem(domain, 0);
				category.setText(categories[j]);
				
				if (i == 0 && j == 0) {
					domainTree.setSelection(category);
					String categoryName = category.getText();
					Event event = new ModelSelectionEvent(null, categoryName);
					event.data = categoryName;
					event.item = category;
					domainTree.notifyListeners(SWT.Selection, event);
				}
			}
		}
		
		domainTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TreeItem[] selected = domainTree.getSelection();
				if (selected.length > 0) {
					String categoryName = getSelectionData(selected[0]);
					Event eve = new ModelSelectionEvent(null, categoryName);
					eve.data = categoryName;
					System.out.println("MASTER eve.data: " + eve.data);
					getUIComposite().notifyListeners(SWT.Selection, eve);
				}
			}
		});
	}
	
	private String getSelectionData(TreeItem itemSelected) {
		String text = itemSelected.getText();
		System.out.println("MASTER Tree item text: " + text);
		return text;
	}
}
