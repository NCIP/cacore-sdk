package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
	
	public static void cleanUp(Composite composite) {
		Control[] children = composite.getChildren();
		if (children == null) return;
		for (int i = 0; i < children.length; i++) {
			children[i].dispose();
		}
	}
	
	public static boolean isEmpty(String s) {
		return (s == null || "".equals(s))?true:false;
	}
	
	public static void setBackground(Control control, int colorCode) {
		if (control == null) return;
		Color color = control.getDisplay().getSystemColor(colorCode);
		control.setBackground(color);
	}
	
	public static void setWhiteBackground(Control control) {
		setBackground(control, SWT.COLOR_WHITE);
	}
}
