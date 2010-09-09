package gov.nih.nci.sdk.ide.core;

import java.util.List;


import org.eclipse.swt.widgets.TabFolder;

public interface TabItemsBuilder {
	public List<CategoryTabItem> buildTabs(TabFolder parent, int style, Object data);
}
