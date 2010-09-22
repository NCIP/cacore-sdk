package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class DomainListGroupPanel extends GroupPanel {
	private static final String ITEM_ALL = "All";
	private static final String ITEM_NONE = "None";
	private List<String> allModelNames = new ArrayList<String>();
	
	public DomainListGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	public void paint() {
		Composite composite = super.getUIComposite();

		final Tree domainTree = new Tree(composite, SWT.MULTI);
		domainTree.setLayoutData(UIHelper.getFieldGridData());

		domainTree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TreeItem[] allSelected = domainTree.getSelection();
				if (allSelected.length > 0) {
					List<String> modelNames = new ArrayList<String>(allSelected.length);
					for (int i = 0; i < allSelected.length; i++) {
						TreeItem item = allSelected[i];
						String itemName = item.getText();
						if (ITEM_NONE.equals(itemName)) {
							modelNames.clear();
							break;
						}
						else if (ITEM_ALL.equals(itemName)) {
							modelNames = allModelNames;
							break;
						}
						else {
							modelNames.add(itemName);
						}
					}
					
					Event eve = new DomainSelectionEvent(modelNames);
					System.out.println("Posting " + eve);
					SDKUIManager.getInstance().publishEvent(Constants.DOMAIN_SELECTION_EVENT, eve);
				}
			}
		});

		List<ModelPackageVO> dataList = SDKModelExplorerUtil.getModelPackages((EPackage)super.getData());
		if (dataList == null) return;
		
		TreeItem treeItem = new TreeItem(domainTree, 0);
		treeItem.setText(ITEM_ALL);
		treeItem = new TreeItem(domainTree, 0);
		treeItem.setText(ITEM_NONE);
		
		allModelNames.clear();
		
		for (int k = 0; k < dataList.size(); k++) {
			ModelPackageVO mpVO = dataList.get(k);
			if (mpVO.hasPackage()) {
				String packageName = mpVO.getPackageName();
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					String itemText = packageName + "." + models.get(i);
					treeItem = new TreeItem(domainTree, 0);
					treeItem.setText(itemText);
					allModelNames.add(itemText);
				}
			}
			else {
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					String itemText = models.get(i);
					treeItem = new TreeItem(domainTree, 0);
					treeItem.setText(models.get(i));
					allModelNames.add(itemText);
				}
			}
		}
	}
}
