package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Layout;

public abstract class GroupPanel extends ActiveUI {
	public GroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data);
		((Group)super.getUIComposite()).setText(title);
	}
	
	protected Composite initUIComposite() {
		Composite parent = getParent();
		
		Group theGroup = new Group(parent, SWT.NONE);
		theGroup.setLayoutData(getGridData());
		theGroup.setLayout(getLayout());
		
		return theGroup;
	}
	
	protected GridData getGridData() {
		GridData gd = UIHelper.getFieldGridData();
		return gd;
	}
	
	protected Layout getLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		return layout;
	}
	
	public abstract void create();
}
