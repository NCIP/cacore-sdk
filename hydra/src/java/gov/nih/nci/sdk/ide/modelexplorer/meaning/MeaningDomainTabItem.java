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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;


public class MeaningDomainTabItem
   extends CategoryTabItem
{
	public MeaningDomainTabItem(TabFolder parent, int style, Object data)
	{
		super(parent, style, data, Constants.TAB_Domain);
	}
	
	@Override
	protected void prepareData()
	{
		ModelSelectionEvent mse = (ModelSelectionEvent)this.getData();
		System.out.println("Event fired: " + this.getData());
	}

	@Override
	public void paint()
	{
		ModelSelectionEvent modelSelectionEvent = (ModelSelectionEvent)this.getData();
		EPackage ePackage = gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager.getInstance().getRootEPackage();
		EClass eClass = gov.nih.nci.sdk.util.EcoreUtil.getEClass(ePackage, modelSelectionEvent.getFullModelName());
		
		String domainName = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.domain");
		domainName = (domainName == null) ? modelSelectionEvent.getModelName() : domainName;
		String domainDesc = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.desc");
		domainDesc = (domainDesc == null) ? "" : domainDesc;
		
		String modelConcept = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.concept");
		modelConcept = (modelConcept == null) ? "" : modelConcept;
		List<String> conceptList = new ArrayList<String>();
		conceptList.add(modelConcept);
		
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
		
		for (String concept: conceptList) {
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(super.getGridData());
		}

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}