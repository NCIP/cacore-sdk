package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.common.util.EList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

public class MeaningPropertiesTabItem extends CategoryTabItem {

	public MeaningPropertiesTabItem(TabFolder parent, int style, Object data)
	{
		super(parent, style, data, Constants.TAB_Properties);
	}

	@Override
	public void paint()
	{
		ModelSelectionEvent modelSelectionEvent = (ModelSelectionEvent)this.getData();
		EPackage ePackage = gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager.getInstance().getRootEPackage();
		EClass eClass = gov.nih.nci.sdk.util.EcoreUtil.getEClass(ePackage, modelSelectionEvent.getFullModelName());		
		EList<EAttribute> eAttributeList = eClass.getEAttributes();
		String domainName = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "class.mea.domain");
		domainName = (domainName == null) ? modelSelectionEvent.getModelName() : domainName;
		
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());

		Group group = new Group(composite, SWT.SHADOW_OUT);
		group.setText(domainName + " Property Info");
		group.setLayout(super.getLayout());
		group.setLayoutData(super.getGridData());

		if (eAttributeList != null && eAttributeList.isEmpty() == false)
		{
			EAttribute viewedAttribute = eAttributeList.get(0);
		
			List propertiesList = new List(composite, SWT.NONE);
			
			for (org.eclipse.emf.ecore.EAttribute eAttribute: eAttributeList)
			{
				propertiesList.add(eAttribute.getName());
			}
			
			new Label(group, SWT.NONE).setText("Domain Name");
			Text nameText = new Text(group, SWT.BORDER | SWT.READ_ONLY);
			nameText.setText(viewedAttribute.getName());
			nameText.setLayoutData(super.getGridData());
	
			new Label(group, SWT.NONE).setText("Description");
			Text descriptionText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String attributeDescription = gov.nih.nci.sdk.util.SDKUtil.getTagValue(eClass, "prop.mea.desc");
			attributeDescription = (attributeDescription == null) ? "No attribute description found" : attributeDescription;
			descriptionText.setText(attributeDescription);
			descriptionText.setLayoutData(new GridData());
	
			new Label(group, SWT.NONE).setText("Type");
			Text typeText = new Text(group, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
			String type = (viewedAttribute.getEType() != null) ? viewedAttribute.getEType().toString() : "";
			typeText.setText(type);
			typeText.setLayoutData(new GridData());
	
			/*Composite conceptsArea = new Composite(composite, SWT.NONE);
			GridLayout cgd = (GridLayout)super.getLayout();
			cgd.numColumns = 1;
			conceptsArea.setLayout(cgd);
			*/
		}
		else
		{
			new Label(group, SWT.NONE).setText("This domain has no properties");		
		}

		group.setSize(group.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void prepareData() {}
}
