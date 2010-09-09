package gov.nih.nci.sdk.ide.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class UIHelper {

	public static GridData getFieldGridData() {
		GridData data = new GridData();
		data.horizontalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
		data.verticalAlignment = SWT.FILL;
		data.grabExcessVerticalSpace = true;
		return data;
	}
	
	public static void cleanTabs(TabFolder tabFolder) {
		if (tabFolder == null) return;
		
		TabItem[] tabs = tabFolder.getItems();
		for (int i = 0; i < tabs.length; i++) {
			TabItem tab = tabs[i];
			tab.dispose();
		}
	}
	
	//TODO: hard-coded for now. --John
	public static List<ModelPackageVO> getModelPackages(EPackage epackage) {
		List<ModelPackageVO> list = new ArrayList<ModelPackageVO>();
		
		ModelPackageVO mpVO = new ModelPackageVO();
		mpVO.setPackageName("gov.nih.nci.sdkexample");
		List<String> models = new ArrayList<String>();
		models.add("Person");
		models.add("Contact");
		models.add("Address");
		models.add("Doctor");
		mpVO.setModels(models);
		list.add(mpVO);
		return list;
	}
	
	public static boolean isEmpty(String s) {
		return (s == null || "".equals(s))?true:false;
	}
}
