package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class SDKGenerator extends SDKScreen {
	private static EPackage epackage;
	
	public SDKGenerator(Shell parent, String title, Object data) {
		super(parent, title);
		epackage = (EPackage)data;
	}
	
	public static EPackage getEPackage() {
		return epackage;
	}
	
	public void createScreen(Composite composite) {
		composite.setSize(Constants.MODEL_EXPLORER_SCREEN_WIDTH, Constants.MODEL_EXPLORER_SCREEN_HEIGHT);
		
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 10;
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		composite.setLayout(layout);
		System.out.println("1");
		
		GroupPanel dlPanel = new DomainListGroupPanel(composite, SWT.NONE, null, "Domain List");
		dlPanel.paint();
		System.out.println("2");
		
		GroupPanel gdPanel = new GeneratorDetailsGroupPanel(composite, SWT.NONE, null, "Generator");
		gdPanel.paint();
		System.out.println("3");
		
		GroupPanel glPanel = new GeneratorListGroupPanel(composite, SWT.NONE, null, "Generators");
		glPanel.paint();
		System.out.println("4");
	}
}
