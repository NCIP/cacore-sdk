package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;
import gov.nih.nci.sdk.util.SDKUtil;

import java.util.Date;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

public class MeaningRelationshipTabItem extends CategoryTabItem {
	private Text nameText;
	private Text descriptionText;
	private Text typeText;
	
	public MeaningRelationshipTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Relationship);
		SDKUIManager.getInstance().registerAsListener(Constants.MEANING_RELATIONSHIP_SELECTION_EVENT, this);
	}
	
	private static EList<EReference> getEReferences(ModelSelectionEvent event) {
		if (event == null) return null;
		EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());	
		return (eClass != null)?eClass.getEReferences():null;
	}
	
	@Override
	public void paint() {
		String domainName = SDKModelExplorerUtil.getDomainName((ModelSelectionEvent)this.getData());
		domainName = (UIHelper.isEmpty(domainName)) ? ((ModelSelectionEvent)this.getData()).getModelName() : domainName;
		
		final EList<EReference> eReferenceList = getEReferences((ModelSelectionEvent)this.getData());
		
		Composite composite = super.getUIComposite();
		
		Group detailsGroup = new Group(composite, SWT.SHADOW_OUT);
		detailsGroup.setText(domainName + " Relationship Info");
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

		Label typeLabel = new Label(detailsGroup, SWT.NONE);
		typeLabel.setText("Type");
		UIHelper.setWhiteBackground(typeLabel);
		typeText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		typeText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(typeText);
		
		Group listGroup= new Group(composite, SWT.SHADOW_OUT);
		listGroup.setText("has relationships...");
		listGroup.setLayout(UIHelper.getOneColumnLayout());
		listGroup.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(listGroup);
		
		final List list = new List(listGroup, SWT.NONE);
		list.addListener (SWT.Selection, new Listener() {
			public void handleEvent (Event event) {
				int[] selection = list.getSelectionIndices();
				EReference selected = eReferenceList.get(selection[0]);
				MeaningRelationshipSelectionEvent eve = new MeaningRelationshipSelectionEvent(selected);
				SDKUIManager.getInstance().publishEvent(Constants.MEANING_RELATIONSHIP_SELECTION_EVENT, eve);
			}
		});
		
		if (eReferenceList != null && eReferenceList.isEmpty() == false) {
			for (EReference eReference : eReferenceList) {
				list.add(eReference.getName());
			}
			
			EReference selected = eReferenceList.get(0);
			MeaningRelationshipSelectionEvent event = new MeaningRelationshipSelectionEvent(selected);
			SDKUIManager.getInstance().publishEvent(Constants.MEANING_RELATIONSHIP_SELECTION_EVENT, event);
		}
		else {
			list.add("No relationships.");
		}
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event == null) return;
		
		if (event instanceof MeaningRelationshipSelectionEvent) {
			_paint((MeaningRelationshipSelectionEvent)event);
		}
	}
	
	private void _paint(MeaningRelationshipSelectionEvent event) {	
		EReference selected = event.getEReference();
		if (selected != null) {
			nameText.setText(selected.getName());

			String referenceDescription = SDKUtil.getTagValue(selected, "rel.mea.desc");
			referenceDescription = (UIHelper.isEmpty(referenceDescription)) ? "No reference description found" : referenceDescription;
			descriptionText.setText(referenceDescription);
			
			String type = (selected.getEType() != null) ? selected.getEType().getName() : "";
			typeText.setText(type);
		}

		super.getUIComposite().redraw();
	}
	
	class MeaningRelationshipSelectionEvent extends Event {
		private EReference eReference;
		private String relationship;
		private Date timestamp;
		
		public MeaningRelationshipSelectionEvent(EReference eReference) {
			this.eReference = eReference;
			this.relationship = eReference.getName();
			this.timestamp = new Date();
			super.data = eReference;
			super.type = Constants.MEANING_RELATIONSHIP_SELECTION_EVENT;
		}

		public String getRelationship() {
			return eReference.getName();
		}

		public EReference getEReference() {
			return eReference;
		}
		
		public Date getTimestamp() {
			return timestamp;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("timestamp=").append(timestamp).append(", ");
			sb.append("relationship=").append(relationship);
			return sb.toString();
		}
	}
}