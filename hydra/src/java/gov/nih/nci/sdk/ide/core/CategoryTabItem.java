package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public abstract class CategoryTabItem extends ActiveUI {
	private String title;
	private TabItem theTabItem;
	
	public CategoryTabItem(TabFolder parent, int style, Object data, String title) {
		super(parent, style, data);
		this.title = title;
		theTabItem.setText(title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public TabItem getTabItem() {
		return theTabItem;
	}
	
	protected Composite initUIComposite() {
		Composite parent = getParent();
		theTabItem = new TabItem((TabFolder)parent, SWT.V_SCROLL);
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
		
		Composite tabArea = new Composite(sc, SWT.NONE);
		sc.setContent(tabArea);

		theTabItem.setControl(sc);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		tabArea.setLayout(layout);
		tabArea.setLayoutData(UIHelper.getCoverAllGridData());
		
		UIHelper.setWhiteBackground(sc);
		UIHelper.setWhiteBackground(tabArea);
		
		return tabArea;
	}
}
