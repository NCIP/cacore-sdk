package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

public class MeaningOperationsTabItem extends CategoryTabItem {

	public MeaningOperationsTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Operations);
	}

	@Override
	public void paint() {
		ModelSelectionEvent modelSelectionEvent = (ModelSelectionEvent)this.getData();
		EPackage ePackage = gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager.getInstance().getRootEPackage();
		EClass eClass = gov.nih.nci.sdk.util.EcoreUtil.getEClass(ePackage, modelSelectionEvent.getFullModelName());		
		EList<EOperation> eOperationList = eClass.getEOperations();
		String domainName = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.domain");
		domainName = (domainName == null) ? modelSelectionEvent.getModelName() : domainName;
		
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());

		Group group = new Group(composite, SWT.SHADOW_OUT);
		group.setText(domainName + " Operation Info");
		group.setLayout(super.getLayout());
		group.setLayoutData(super.getGridData());

		if (eOperationList != null && eOperationList.isEmpty() == false)
		{
			EOperation selectedOperation = eOperationList.get(0);
		
			List operationsList = new List(composite, SWT.NONE);
			
			for (EOperation eOperation: eOperationList)
			{
				operationsList.add(eOperation.getName());
			}
			
			new Label(group, SWT.NONE).setText("Name");
			Text nameText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
			nameText.setText(selectedOperation.getName());
			nameText.setLayoutData(super.getGridData());
	
			new Label(group, SWT.NONE).setText("Description");
			Text descriptionText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String attributeDescription = gov.nih.nci.sdk.util.SDKUtil.getTagValue(selectedOperation, "prop.mea.desc");
			attributeDescription = (attributeDescription == null) ? "No operation description found" : attributeDescription;
			descriptionText.setText(attributeDescription);
			descriptionText.setLayoutData(new GridData());
	
			new Label(group, SWT.NONE).setText("Return Type");
			Text typeText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String type = (selectedOperation.getEType() != null) ? selectedOperation.getEType().getName() : "";
			typeText.setText(type);
			typeText.setLayoutData(new GridData());
			
			new Label(group, SWT.NONE).setText("Parameters");
			List parameterList = new List(group, SWT.NONE);
			java.util.List<EParameter> eParameterList = selectedOperation.getEParameters();
			
			for (EParameter eParameter: eParameterList)
			{
				String parameterType = (eParameter.getEType() != null) ? eParameter.getEType().getName() : "";
				parameterList.add(eParameter.getName() + ":" + parameterType);
			}
			
			if (eParameterList.isEmpty() == true)
			{ 
				parameterList.add("This operation has no parameters");
			}
		}
		else
		{
			new Label(group, SWT.NONE).setText("This domain has no operations");		
		}

		group.setSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void prepareData() {}
}
