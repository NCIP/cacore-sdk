/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public abstract class SDKScreen extends Dialog implements Listener {
	protected String title;
	
	public SDKScreen(Shell parent, String title) {
		super(parent);
		this.title = title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

//	public int open() {
//		Shell parent = super.getParentShell();
//		Display display = parent.getDisplay();
//		
//		Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
//		shell.setText(title);
//		
//		createDialogArea(shell);
//
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//
//		return 0;
//	}
//	
//	//SWT
//	public Control createDialogArea(Composite parent) {
//		createScreen(parent);
//		return parent;
//	}

	//JFace Dialog
	public Control createDialogArea(Composite parent) {
		super.getShell().setText(title);
		//super.getShell().setSize(500, 500);
		Composite outer = (Composite) super.createDialogArea(parent);
		createScreen(outer);
		return outer;
	}
	
	protected abstract void createScreen(Composite parent);
	
	public void handleEvent(Event event) {
	}
}
