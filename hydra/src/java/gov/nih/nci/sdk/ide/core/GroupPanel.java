package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public abstract class GroupPanel extends ActiveUI {
	public GroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data);
		((Group)super.getUIComposite()).setText(title);
	}
	
	protected Composite initUIComposite() {
		Composite parent = getParent();
		
		Group theGroup = new Group(parent, SWT.NONE);
		theGroup.setLayoutData(UIHelper.getCoverAllGridData());
		theGroup.setLayout(UIHelper.getOneColumnLayout());
		
		return theGroup;
	}
}
