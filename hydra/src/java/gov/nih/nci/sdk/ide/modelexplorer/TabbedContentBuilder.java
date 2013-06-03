/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer;

import java.util.List;

import gov.nih.nci.sdk.ide.core.CategoryContentBuilder;
import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TabbedContentBuilder implements CategoryContentBuilder {
	public TabFolder buildContent(Composite composite, Object data) {
		TabFolder tf = findTabFolder(composite);
		boolean oldTabFolderFound = (tf != null)?true:false;
		
		final TabFolder tabFolder = (oldTabFolderFound)?tf:new TabFolder(composite, SWT.V_SCROLL);
		tabFolder.setLayoutData(UIHelper.getCoverAllGridData());
		
		if (!oldTabFolderFound) {
			tabFolder.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent event) {
					TabItem[] selected = tabFolder.getSelection();
					if (selected.length > 0) {
						Event eve = new Event();
						eve.type = SWT.Selection;
						eve.text = selected[0].getText();
					}
				}
			});
		}

		return tabFolder;
	}
	
	private TabFolder findTabFolder(Composite parent) {
		Control[] children = parent.getChildren();
		if (children == null || children.length == 0) return null;

		TabFolder tf = null;
		for (int i = 0; i < children.length; i++) {
			Control c = children[i];
			if (c instanceof TabFolder) {
				tf = (TabFolder)c;
				break;
			}
		}
		
		return tf;
	}
	
	public void autoSetFirstTab(TabFolder tabFolder) {
		if (tabFolder == null) return;
		
		if (tabFolder.getItemCount() > 0) {
			tabFolder.setSelection(0);
			Event event = new Event();
			event.item = tabFolder.getItem(0);
			tabFolder.notifyListeners(SWT.Selection, event);
		}
	}
	
	public void activate(List<CategoryTabItem> tabs) {
		if (tabs == null) return;
		
		for (CategoryTabItem tab : tabs) {
			tab.paint();
		}
	}
}
