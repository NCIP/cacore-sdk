package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
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
		domainDesc = (domainDesc == null) ? "There is no description for this domain" : domainDesc;
		
		String modelConcept = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.concept");
		modelConcept = (modelConcept == null) ? "" : modelConcept;
		List<String> conceptList = new ArrayList<String>();
		conceptList.add(modelConcept);
		
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());
		
		Group group = new Group(composite, SWT.SHADOW_OUT);
		group.setText(domainName + " Domain Info");
		group.setLayout(super.getLayout());
		group.setLayoutData(super.getGridData());

		new Label(group, SWT.NONE).setText("Domain Name");
		Text domainNameText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(domainName);
		domainNameText.setLayoutData(super.getGridData());
		
		new Label(group, SWT.NONE).setText("Description");
		Text domainDescText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(domainDesc);
		domainDescText.setLayoutData(super.getGridData());
		
		new Label(group, SWT.NONE).setText("Concept");
		
		Composite conceptsArea = new Composite(group, SWT.NONE);
		GridLayout cgd = (GridLayout)super.getLayout();
		cgd.numColumns = 1;
		conceptsArea.setLayout(cgd);
		
		Text conceptText = null;
		
		for (String concept: conceptList) {
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(super.getGridData());
		}

		group.setSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}