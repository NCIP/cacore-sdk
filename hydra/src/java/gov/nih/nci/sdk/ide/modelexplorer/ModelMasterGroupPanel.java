package gov.nih.nci.sdk.ide.modelexplorer;

import java.util.List;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
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
					String packageName = "";
					String modelName = "";
					String categoryName = "";
					if (clickedOnCategory(selectedItem)) {
						TreeItem pkgItem = selectedItem.getParentItem().getParentItem();
						if (pkgItem != null) packageName = pkgItem.getText();
						modelName = selectedItem.getParentItem().getText();
						categoryName = selectedItem.getText();
					}
					else if (clickedOnModel(selectedItem)) {
						TreeItem pkgItem = selectedItem.getParentItem();
						if (pkgItem != null) packageName = pkgItem.getText();
						modelName = selectedItem.getText();
						categoryName = Constants.DEFAULT_CATEGORY;
					}
					else {
						packageName = selectedItem.getText();
						if (selectedItem.getItemCount() > 0) {
							modelName = selectedItem.getItem(0).getText();
							categoryName = Constants.DEFAULT_CATEGORY;
						}
					}
					
					if (!(UIHelper.isEmpty(packageName) && UIHelper.isEmpty(modelName) && UIHelper.isEmpty(categoryName))) {
						Event eve = new ModelSelectionEvent(packageName, modelName, categoryName);
						notifyListeners(SWT.Selection, eve);
					}
				}
			}
		});
		
		@SuppressWarnings("unchecked")
		List<ModelPackageVO> dataList = (List<ModelPackageVO>)super.getData();
		String[] categories = Constants.ALL_CATEGORIES;
		for (int k = 0; k < dataList.size(); k++) {
			ModelPackageVO mpVO = dataList.get(k);
			if (mpVO.hasPackage()) {
				TreeItem packageItem = new TreeItem(domainTree, 0);
				packageItem.setText(mpVO.getPackageName());
				List<String> models = mpVO.getModels();
				for (int i = 0; i < models.size(); i++) {
					TreeItem model = new TreeItem(packageItem, 0);
					model.setText(models.get(i));
					for (int j = 0; j < categories.length; j++) {
						TreeItem category = new TreeItem(model, 0);
						category.setText(categories[j]);
						
						if (k == 0 && i == 0 && j == 0) {
							domainTree.setSelection(category);
							String categoryName = category.getText();
							Event event = new ModelSelectionEvent(mpVO.getPackageName(), model.getText(), categoryName);
							domainTree.notifyListeners(SWT.Selection, event);
						}
					}
				}
			}
			else {
				List<String> models = mpVO.getModels();
				for (int i = 0; i < models.size(); i++) {
					TreeItem model = new TreeItem(domainTree, 0);
					model.setText(models.get(i));
					for (int j = 0; j < categories.length; j++) {
						TreeItem category = new TreeItem(model, 0);
						category.setText(categories[j]);
						
						if (k == 0 && i == 0 && j == 0) {
							domainTree.setSelection(category);
							String categoryName = category.getText();
							Event event = new ModelSelectionEvent(mpVO.getPackageName(), model.getText(), categoryName);
							domainTree.notifyListeners(SWT.Selection, event);
						}
					}
				}
			}
			
			
		}
	}
	
	private boolean clickedOnModel(TreeItem selectedItem) {
		return (selectedItem.getItemCount() == Constants.ALL_CATEGORIES.length && Constants.ALL_CATEGORIES[0].equals(selectedItem.getItem(0).getText()))?true:false;
	}
	
	private boolean clickedOnCategory(TreeItem selectedItem) {
		return (selectedItem.getItemCount() == 0 && selectedItem.getParentItem() != null)?true:false;
	}
}
