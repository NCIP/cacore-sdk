package gov.nih.nci.sdk.ide.core;

import java.util.List;

public abstract class TabItemsBuilderImpl implements TabItemsBuilder {
	public void activate(List<CategoryTabItem> tabs) {
		if (tabs == null) return;
		
		for (CategoryTabItem tab : tabs) {
			tab.paint();
		}
	}
}
