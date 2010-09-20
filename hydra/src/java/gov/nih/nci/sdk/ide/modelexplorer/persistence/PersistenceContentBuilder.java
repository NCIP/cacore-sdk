package gov.nih.nci.sdk.ide.modelexplorer.persistence;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.TabbedContentBuilder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class PersistenceContentBuilder extends TabbedContentBuilder {
	public TabFolder buildContent(Composite composite, Object data) {
		TabFolder tabFolder = super.buildContent(composite, data);
		
		UIHelper.cleanTabs(tabFolder);
		
		List<CategoryTabItem> tabs = new ArrayList<CategoryTabItem>(Constants.persistenceTabs.length);
		tabs.add(new PersistenceDomainTabItem(tabFolder, SWT.NONE, data));
		tabs.add(new PersistencePropertiesTabItem(tabFolder, SWT.NONE, data));
		
		super.activate(tabs);
		
		return tabFolder;
	}
}
