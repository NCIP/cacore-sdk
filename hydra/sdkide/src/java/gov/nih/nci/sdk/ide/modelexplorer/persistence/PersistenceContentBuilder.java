package gov.nih.nci.sdk.ide.modelexplorer.persistence;

import gov.nih.nci.sdk.ide.core.TabItemsBuilder;
import gov.nih.nci.sdk.ide.modelexplorer.TabbedContentBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class PersistenceContentBuilder extends TabbedContentBuilder {
	public TabFolder buildContent(Composite composite, Object data) {
		TabFolder tabFolder = super.buildContent(composite, data);
		
		TabItemsBuilder tib = new PersistenceTabItemsBuilder();
		tib.buildTabs(tabFolder, SWT.NONE, data);
		
		//super.autoSetFirstTab(tabFolder);
		
		return tabFolder;
	}
}
