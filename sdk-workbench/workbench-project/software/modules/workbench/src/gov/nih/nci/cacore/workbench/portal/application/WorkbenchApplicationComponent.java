package gov.nih.nci.cacore.workbench.portal.application;

import javax.swing.JInternalFrame;

public class WorkbenchApplicationComponent extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	public WorkbenchApplicationComponent() {
		setSize(600, 700);
		setMaximizable(false);
		setIconifiable(false);
		setClosable(true);
		setResizable(false);
	}

}
