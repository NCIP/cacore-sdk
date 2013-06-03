/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.TabbedContentBuilder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class MeaningContentBuilder extends TabbedContentBuilder {
	public TabFolder buildContent(Composite composite, Object data) {
		TabFolder tabFolder = super.buildContent(composite, data);

		UIHelper.cleanTabs(tabFolder);
		
		List<CategoryTabItem> tabs = new ArrayList<CategoryTabItem>(Constants.meaningTabs.length);
		tabs.add(new MeaningDomainTabItem(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningPropertiesTabItem(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningOperationsTabItem(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningRelationshipTabItem(tabFolder, SWT.NONE, data));
		
		super.activate(tabs);
		
		return tabFolder;
	}
}
