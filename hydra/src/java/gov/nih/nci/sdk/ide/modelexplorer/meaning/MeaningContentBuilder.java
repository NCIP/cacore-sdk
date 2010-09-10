package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.TabItemsBuilder;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.TabbedContentBuilder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

public class MeaningContentBuilder extends TabbedContentBuilder {
	public TabFolder buildContent(Composite composite, Object data) {
		TabFolder tabFolder = super.buildContent(composite, data);
		
//		TabItemsBuilder tib = new MeaningTabItemsBuilder();
//		tib.buildTabs(tabFolder, SWT.NONE, data);
		

		UIHelper.cleanTabs(tabFolder);
		
		List<CategoryTabItem> tabs = new ArrayList<CategoryTabItem>(Constants.meaningTabs.length);
		tabs.add(new MeaningDomainTabItm(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningPropertiesTabItm(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningOperationsTabItm(tabFolder, SWT.NONE, data));
		tabs.add(new MeaningRelationshipTabItm(tabFolder, SWT.NONE, data));
		
		System.out.println("SWT.None: " + SWT.None);
		System.out.println("SWT.NONE: " + SWT.NONE);
		
		for (CategoryTabItem tab : tabs) {
			tab.paint();
		}
		
		//super.autoSetFirstTab(tabFolder);
		
		return tabFolder;
	}
}
