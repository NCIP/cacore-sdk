package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

public class MeaningDomainTabItm extends CategoryTabItem {
	private String domainName;
	private String domainDesc;
	private List<String> concepts;

	public MeaningDomainTabItm(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Domain);
	}
	
	@Override
	protected void prepareData() {
		ModelSelectionEvent mse = (ModelSelectionEvent)this.getData();
		domainName = mse.getFullModelName();
		
		domainDesc = mse.getFullModelName();
		
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		
		concepts = new ArrayList<String>();
		concepts.add("http://mayoclinic.com/concept/person");
		concepts.add("http://mayoclinic.com/concept/patient");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing1");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing2");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing3");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing4");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing5");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing6");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing7");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing8");
		concepts.add("http://lexevs.nci.gov/concept/humanbeing9");
	}

	@Override
	public void paint() {
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());
		
		new Label(composite, SWT.NONE).setText("Domain Name");
		Text domainNameText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(domainName);
		domainNameText.setLayoutData(super.getGridData());
		
		new Label(composite, SWT.NONE).setText("Description");
		Text domainDescText = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(domainDesc);
		domainDescText.setLayoutData(super.getGridData());
		
		new Label(composite, SWT.NONE).setText("Concept");
		
		Composite conceptsArea = new Composite(composite, SWT.NONE);
		GridLayout cgd = (GridLayout)super.getLayout();
		cgd.numColumns = 1;
		conceptsArea.setLayout(cgd);
		
		Text conceptText = null;
		for (String concept: concepts) {
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(super.getGridData());
		}

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
