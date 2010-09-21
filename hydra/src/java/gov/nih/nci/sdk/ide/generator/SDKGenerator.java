package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class SDKGenerator extends SDKScreen {
	public SDKGenerator(Shell parent, String title) {
		super(parent, title);
	}
	
	public void createScreen(Composite composite) {
		composite.setSize(Constants.MODEL_EXPLORER_SCREEN_WIDTH, Constants.MODEL_EXPLORER_SCREEN_HEIGHT);
		
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 10;
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		composite.setLayout(layout);
		
		EPackage rootEPackage = SDKUIManager.getInstance().getRootEPackage();
		GroupPanel dlPanel = new DomainListGroupPanel(composite, SWT.NONE, rootEPackage, "Domain List");
		dlPanel.paint();
		
		GroupPanel gdPanel = new GeneratorDetailsGroupPanel(composite, SWT.NONE, rootEPackage, "Generator");
		gdPanel.paint();
		
		GroupPanel glPanel = new GeneratorListGroupPanel(composite, SWT.NONE, rootEPackage, "Generators");
		glPanel.paint();
	}
}
