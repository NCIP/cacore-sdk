package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public abstract class CategoryTabItem extends ActiveUI {
	private String title;
	private TabItem theTabItem;
	
	public CategoryTabItem(TabFolder parent, int style, Object data, String title) {
		super(parent, style, data);
		this.title = title;
		theTabItem.setText(title);
		prepareData();
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
		sc.setMinSize(tabArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		theTabItem.setControl(sc);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		tabArea.setLayout(layout);
		tabArea.setSize(tabArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		tabArea.setLayoutData(getGridData());

		//tabArea.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_RED));
		
		return tabArea;
	}
	
	protected GridData getGridData() {
		GridData gd = UIHelper.getFieldGridData();
		//gd.grabExcessVerticalSpace = false;
		return gd;
	}
	
	protected Layout getLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		layout.numColumns = 2;
		return layout;
	}
	
	//TODO: make it abstract.
	protected abstract void prepareData();
}
