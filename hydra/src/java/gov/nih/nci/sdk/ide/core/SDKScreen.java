package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class SDKScreen extends Dialog {
	protected String title;
	protected Shell shell2;
	
	public SDKScreen(Shell parent, String title) {
		super(parent);
		setTitle(title);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public int open() {
		Shell parent = getParent();
		Display display = parent.getDisplay();
		
		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell2 = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setText(title);
		
		createDialogArea(shell);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		return 0;
	}

	public Control createDialogArea(Composite parent) {
		//Composite outer = (Composite) super.createDialogArea(parent);
		createScreen(parent);
		return parent;
	}
	
	protected abstract void createScreen(Composite parent);
}
