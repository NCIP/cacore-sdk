package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;
import gov.nih.nci.sdk.util.SDKUtil;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

public class MeaningPropertiesTabItem extends CategoryTabItem {
	private Text nameText;
	private Text descriptionText;
	private List conceptList;
	private Text typeText;

	public MeaningPropertiesTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Properties);
		SDKUIManager.getInstance().registerAsListener(Constants.MEANING_PROPERTY_SELECTION_EVENT, this);
	}
	
	private static EList<EAttribute> getEAttributes(ModelSelectionEvent event) {
		if (event == null) return null;
		EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());	
		return (eClass != null)?eClass.getEAttributes():null;
	}

	@Override
	public void paint() {
		String domainName = SDKModelExplorerUtil.getDomainName((ModelSelectionEvent)this.getData());
		domainName = (UIHelper.isEmpty(domainName)) ? ((ModelSelectionEvent)this.getData()).getModelName() : domainName;

		final EList<EAttribute> eAttributeList = getEAttributes((ModelSelectionEvent)this.getData());
		
		Composite composite = super.getUIComposite();

		Group detailsGroup = new Group(composite, SWT.SHADOW_OUT);
		detailsGroup.setText(domainName + " Property Info");
		detailsGroup.setLayout(UIHelper.getTwoColumnLayout());
		detailsGroup.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(detailsGroup);
		
		Label nameLabel = new Label(detailsGroup, SWT.NONE);
		nameLabel.setText("Name");
		UIHelper.setWhiteBackground(nameLabel);
		nameText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY);
		nameText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(nameText);

		Label descLabel = new Label(detailsGroup, SWT.NONE);
		descLabel.setText("Description");
		UIHelper.setWhiteBackground(descLabel);
		descriptionText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		descriptionText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(descriptionText);
		
		Label conceptsLabel = new Label(detailsGroup, SWT.NONE);
		conceptsLabel.setText("Concept");
		UIHelper.setWhiteBackground(conceptsLabel);
		conceptList = new List(detailsGroup, SWT.NONE);
		conceptList.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(conceptList);
		conceptList.add("No concepts.");

		Label typeLabel = new Label(detailsGroup, SWT.NONE);
		typeLabel.setText("Type");
		UIHelper.setWhiteBackground(typeLabel);
		typeText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		typeText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(typeText);

		Group listGroup = new Group(composite, SWT.SHADOW_OUT);
		listGroup.setText("has properties ...");
		listGroup.setLayout(UIHelper.getOneColumnLayout());
		listGroup.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(listGroup);
		
		final List list = new List(listGroup, SWT.NONE);
		list.addListener (SWT.Selection, new Listener() {
			public void handleEvent (Event event) {
				int[] selection = list.getSelectionIndices();
				EAttribute selected = eAttributeList.get(selection[0]);
				MeaningPropertySelectionEvent eve = new MeaningPropertySelectionEvent(selected);
				SDKUIManager.getInstance().publishEvent(Constants.MEANING_PROPERTY_SELECTION_EVENT, eve);
			}
		});
		
		if (eAttributeList != null && eAttributeList.isEmpty() == false) {
			for (EAttribute eAttribute : eAttributeList) {
				list.add(eAttribute.getName());
			}
			
			EAttribute selected = eAttributeList.get(0);
			MeaningPropertySelectionEvent event = new MeaningPropertySelectionEvent(selected);
			SDKUIManager.getInstance().publishEvent(Constants.MEANING_PROPERTY_SELECTION_EVENT, event);
		}
		else {
			list.add("No properties.");
		}
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event == null) return;
		
		if (event instanceof MeaningPropertySelectionEvent) {
			_paint((MeaningPropertySelectionEvent)event);
		}
	}
	
	private void _paint(MeaningPropertySelectionEvent event) {	
		EAttribute selected = event.getEAttribute();
		if (selected != null) {
			nameText.setText(selected.getName());

			String attributeDescription = SDKUtil.getTagValue(selected, "prop.mea.desc");
			attributeDescription = (UIHelper.isEmpty(attributeDescription)) ? "No property description found" : attributeDescription;
			descriptionText.setText(attributeDescription);
			
			conceptList.removeAll();
			java.util.List<String> concepts = getConcepts(event);
			if (concepts == null || concepts.size() == 0) {
				String defaultConcept = getDefaultConcept(selected);
				if (!UIHelper.isEmpty(defaultConcept)) {
					concepts.add(defaultConcept);
				}
				else {
					concepts.add("No concepts.");
				}
			}
			
			for (String concept: concepts) {
				conceptList.add(concept);
			}

			typeText.setText(gov.nih.nci.sdk.util.EcoreUtil.determineSubstituteType(selected.getName(), selected.getEType()));
		}

		super.getUIComposite().redraw();
	}
	
	private String getDefaultConcept(ENamedElement selected) {
		String uriPrefix = SDKUtil.getTagValue(selected, "prop.mea.concept.uri.prefix");
		if (UIHelper.isEmpty(uriPrefix)) {
			uriPrefix = SDKUtil.getTagValue(selected, "package.prop.mea.concept.uri.prefix");
		}
		String name = "";
		if (selected != null) name = selected.getName();
		return super.getDefaultConcept(uriPrefix, name);
	}
	
	private java.util.List<String> getConcepts(MeaningPropertySelectionEvent event) {
		java.util.List<String> concepts = new ArrayList<String>();
		if (event != null) {
			EAttribute selected = event.getEAttribute();
			if (selected != null) {
				String value = SDKUtil.getTagValue(selected, "prop.mea.concept");
				if (!UIHelper.isEmpty(value)) {
					concepts.add(value);
				}
			}
		}
		return concepts;
	}
	
	class MeaningPropertySelectionEvent extends Event {
		private EAttribute eAttribute;
		private Date timestamp;
		
		public MeaningPropertySelectionEvent(EAttribute eAttribute) {
			this.eAttribute = eAttribute;
			this.timestamp = new Date();
			super.data = eAttribute;
			super.type = Constants.MEANING_PROPERTY_SELECTION_EVENT;
		}

		public EAttribute getEAttribute() {
			return eAttribute;
		}
		
		public Date getTimestamp() {
			return timestamp;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("timestamp=").append(timestamp).append(", ");
			sb.append("eAttribute=").append(eAttribute);
			return sb.toString();
		}
	}
}