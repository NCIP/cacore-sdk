package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

public class MeaningRelationshipTabItem extends CategoryTabItem {

	public MeaningRelationshipTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Relationship);
	}

	@Override
	public void paint() {
		ModelSelectionEvent modelSelectionEvent = (ModelSelectionEvent)this.getData();
		EPackage ePackage = gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager.getInstance().getRootEPackage();
		EClass eClass = gov.nih.nci.sdk.util.EcoreUtil.getEClass(ePackage, modelSelectionEvent.getFullModelName());		
		EList<EReference> eReferenceList = (eClass != null)?eClass.getEReferences():null;
		String domainName = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.domain");
		domainName = (domainName == null) ? modelSelectionEvent.getModelName() : domainName;
		
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());

		Group group = new Group(composite, SWT.SHADOW_OUT);
		group.setText(domainName + " Relationship Info");
		group.setLayout(super.getLayout());
		group.setLayoutData(super.getGridData());

		Group listGroup= new Group(composite, SWT.SHADOW_OUT);
		listGroup.setText("has relationships...");
		listGroup.setLayout(super.getLayout());
		listGroup.setLayoutData(super.getGridData());
		
		if (eReferenceList != null && eReferenceList.isEmpty() == false)
		{
			EReference selectedReference = eReferenceList.get(0);
		
			List propertiesList = new List(listGroup, SWT.NONE);
			
			for (org.eclipse.emf.ecore.EReference eReference: eReferenceList)
			{
				propertiesList.add(eReference.getName());
			}
			
			new Label(group, SWT.NONE).setText("Name");
			Text nameText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
			nameText.setText(selectedReference.getName());
			nameText.setLayoutData(super.getGridData());
	
			new Label(group, SWT.NONE).setText("Description");
			Text descriptionText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String referenceDescription = gov.nih.nci.sdk.util.SDKUtil.getTagValue(selectedReference, "rel.mea.desc");
			referenceDescription = (referenceDescription == null) ? "No reference description found" : referenceDescription;
			descriptionText.setText(referenceDescription);
			descriptionText.setLayoutData(new GridData());
	
			new Label(group, SWT.NONE).setText("Type");
			Text typeText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String type = (selectedReference.getEType() != null) ? selectedReference.getEType().getName() : "";
			typeText.setText(type);
			typeText.setLayoutData(new GridData());
		}
		else
		{
			new Label(group, SWT.NONE).setText("This domain has no references");		
		}

		group.setSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		listGroup.setSize(listGroup.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}

	@Override
	public void prepareData() {}
}