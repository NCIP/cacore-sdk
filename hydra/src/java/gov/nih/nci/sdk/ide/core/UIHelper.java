package gov.nih.nci.sdk.ide.core;

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
}
