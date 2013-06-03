/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class UIHelper {
	
	public static Layout getOneColumnLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		layout.numColumns = 1;
		return layout;
	}
	
	public static Layout getTwoColumnLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		layout.numColumns = 2;
		return layout;
	}

	public static GridData getCoverAllGridData() {
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
