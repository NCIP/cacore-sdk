package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class SDKGenerator extends SDKScreen {
	private DomainSelectionEvent dsEvent;
	private GeneratorSelectionEvent gsEvent;
	private Button generateButton;
	
	public SDKGenerator(Shell parent, String title) {
		super(parent, title);
		SDKUIManager.getInstance().registerAsListener(Constants.DOMAIN_SELECTION_EVENT, this);
		SDKUIManager.getInstance().registerAsListener(Constants.GENERATOR_SELECTION_EVENT, this);
	}
	
	@Override
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
		
		GroupPanel gdPanel = new GeneratorDetailsGroupPanel(composite, SWT.NONE, rootEPackage, "Generator Details");
		gdPanel.paint();
		
		GroupPanel glPanel = new GeneratorListGroupPanel(composite, SWT.NONE, rootEPackage, "Generators");
		glPanel.paint();
	}
	
	@Override
	public void okPressed() {
		//TODO: to be completed
		System.out.println("okPressed - fire code generation here");
		System.out.println("dsEvent: " + dsEvent);
		System.out.println("gsEvent: " + gsEvent);
	}
	
	@Override
	public Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button button = super.createButton(parent, id, label, defaultButton);
		if (id == IDialogConstants.OK_ID) {
			button.setText("Generate");
			generateButton = button;
			generateButton.setEnabled(false);
		}
		return button;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public int open() {
		dsEvent = null;
		gsEvent = null;
		return super.open();
	}
	
	@Override
	public void handleEvent(Event event) {
		System.out.println("SDKGenerator receiving " + event);
		if (event == null) return;
		
		if (event instanceof GeneratorSelectionEvent) {
			gsEvent = (GeneratorSelectionEvent)event;
		}
		
		if (event instanceof DomainSelectionEvent) {
			dsEvent = (DomainSelectionEvent)event;
		}
		
		if (dsEvent != null && gsEvent != null) {
			generateButton.setEnabled(true);
			generateButton.setFocus();
		}
	}
}
