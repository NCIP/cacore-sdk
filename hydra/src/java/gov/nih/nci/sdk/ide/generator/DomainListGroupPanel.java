package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;

import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class DomainListGroupPanel extends GroupPanel {
	
	public DomainListGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	public void paint() {
		System.out.println("a");
		Composite composite = super.getUIComposite();

		System.out.println("a1");
		final Tree domainTree = new Tree(composite, SWT.SINGLE);
		domainTree.setLayoutData(UIHelper.getFieldGridData());

		System.out.println("a2");
		domainTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TreeItem[] allSelected = domainTree.getSelection();
				if (allSelected.length > 0) {
					TreeItem selectedItem = allSelected[0];
					String packageName = "";
					String modelName = "";
					TreeItem pkgItem = selectedItem.getParentItem();
					if (pkgItem != null) packageName = pkgItem.getText();
					modelName = selectedItem.getText();
					
					if (!(UIHelper.isEmpty(packageName) && UIHelper.isEmpty(modelName))) {
						Event eve = new DomainSelectionEvent(packageName, modelName);
						notifyListeners(SWT.Selection, eve);
					}
				}
			}
		});

		System.out.println("a3");
		//@SuppressWarnings("unchecked")
		List<ModelPackageVO> dataList = (List<ModelPackageVO>)super.getData();
		System.out.println("a4 " + dataList);
		for (int k = 0; k < dataList.size(); k++) {
			System.out.println("a4 k: " + k);
			ModelPackageVO mpVO = dataList.get(k);
			if (mpVO.hasPackage()) {
				TreeItem packageItem = new TreeItem(domainTree, 0);
				packageItem.setText(mpVO.getPackageName());
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					System.out.println("a4 i: " + i);
					TreeItem model = new TreeItem(packageItem, 0);
					model.setText(models.get(i));
				}
			}
			else {
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					TreeItem model = new TreeItem(domainTree, 0);
					model.setText(models.get(i));
				}
			}
			
			
		}
		System.out.println("a9");
	}
}
