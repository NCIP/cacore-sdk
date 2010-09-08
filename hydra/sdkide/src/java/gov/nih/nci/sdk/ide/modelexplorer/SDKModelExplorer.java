package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.SDKScreen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SDKModelExplorer extends SDKScreen {
	private Object data;
	
	public SDKModelExplorer(Shell parent, String title, Object data) {
		super(parent, title);
		this.data = data;
	}
	
	public void createScreen(Composite composite) {
		composite.setSize(Constants.MODEL_EXPLORER_SCREEN_WIDTH, Constants.MODEL_EXPLORER_SCREEN_HEIGHT);
		
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 10;
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		composite.setLayout(layout);

		GroupPanel masterPanel = new ModelMasterGroupPanel(composite, SWT.NONE, data, Constants.MODEL_MASTER_PANEL_TITLE);
		masterPanel.create();
		
		GroupPanel detailsPanel = new ModelDetailsGroupPanel(composite, SWT.NONE, data, Constants.MODEL_DETAILS_PANEL_TITLE);
		detailsPanel.create();
		
		masterPanel.addListener(SWT.Selection, detailsPanel);
	}

}
