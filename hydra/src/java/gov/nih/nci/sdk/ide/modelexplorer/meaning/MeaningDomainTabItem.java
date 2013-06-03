/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;
import gov.nih.nci.sdk.util.SDKUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;


public class MeaningDomainTabItem extends CategoryTabItem {
	public MeaningDomainTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Domain);
	}

	@Override
	public void paint() {
		String domainName = SDKModelExplorerUtil.getDomainName((ModelSelectionEvent)this.getData());
		domainName = (UIHelper.isEmpty(domainName)) ? ((ModelSelectionEvent)this.getData()).getModelName() : domainName;
		
		String domainDesc = getDomainDesc((ModelSelectionEvent)this.getData());
		domainDesc = (domainDesc == null) ? "There is no description for this domain" : domainDesc;
		
		List<String> superList = getDomainSuperClasses((ModelSelectionEvent)this.getData());
		List<String> concepts = getConcepts((ModelSelectionEvent)this.getData());
		if (concepts == null || concepts.size() == 0) {
			String defaultConcept = getDefaultConcept((ModelSelectionEvent)this.getData());
			if (!UIHelper.isEmpty(defaultConcept)) {
				concepts.add(defaultConcept);
			}
			else {
				concepts.add("No concepts.");
			}
		}
		
		Composite composite = super.getUIComposite();
		
		Group detailsGroup = new Group(composite, SWT.SHADOW_OUT);
		detailsGroup.setText(domainName + " Domain Info");
		detailsGroup.setLayout(UIHelper.getTwoColumnLayout());
		detailsGroup.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(detailsGroup);

		Label nameLabel = new Label(detailsGroup, SWT.NONE);
		nameLabel.setText("Domain Name");
		UIHelper.setWhiteBackground(nameLabel);
		
		Text domainNameText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(domainName);
		domainNameText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(domainNameText);
		
		Label descLabel = new Label(detailsGroup, SWT.NONE);
		descLabel.setText("Description");
		UIHelper.setWhiteBackground(descLabel);
		
		Text domainDescText = new Text(detailsGroup, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(domainDesc);
		domainDescText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(domainDescText);
		
		
		Label superTypeLabel = new Label(detailsGroup, SWT.NONE);
		superTypeLabel.setText("Super Type");
		UIHelper.setWhiteBackground(superTypeLabel);
		
		Composite supersArea = new Composite(detailsGroup, SWT.NONE);
		GridLayout sLayout = new GridLayout();
		sLayout.marginHeight = 0;
		sLayout.marginWidth = 0;
		sLayout.verticalSpacing = 1;
		sLayout.horizontalSpacing = 1;
		sLayout.numColumns = 1;
		supersArea.setLayout(sLayout);
		UIHelper.setWhiteBackground(supersArea);
		
		Text superText = null;
		for (String superType: superList) {
			superText = new Text(supersArea, SWT.BORDER | SWT.READ_ONLY);
			superText.setText(superType);
			superText.setLayoutData(UIHelper.getCoverAllGridData());
			UIHelper.setWhiteBackground(superText);
		}

		
		Label conceptLabel = new Label(detailsGroup, SWT.NONE);
		conceptLabel.setText("Concept");
		UIHelper.setWhiteBackground(conceptLabel);
		
		Composite conceptsArea = new Composite(detailsGroup, SWT.NONE);
		GridLayout cLayout = new GridLayout();
		cLayout.marginHeight = 0;
		cLayout.marginWidth = 0;
		cLayout.verticalSpacing = 1;
		cLayout.horizontalSpacing = 1;
		cLayout.numColumns = 1;
		conceptsArea.setLayout(cLayout);
		UIHelper.setWhiteBackground(conceptsArea);
		
		Text conceptText = null;
		for (String concept: concepts) {
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(UIHelper.getCoverAllGridData());
			UIHelper.setWhiteBackground(conceptText);
		}
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	private static String getDomainDesc(ModelSelectionEvent event) {
		if (event == null) return "";
		EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());
		String value = SDKUtil.getTagValue(eClass, "class.mea.desc");
		return (value == null)?"":value;
	}
	
	private String getDefaultConcept(ModelSelectionEvent event) {
		if (event == null || event.isEmptyModel()) return null;
		EClass selected = SDKUIManager.getInstance().getEClass(event.getFullModelName());
		String uriPrefix = SDKUtil.getTagValue(selected, "class.mea.concept.uri.prefix");
		if (UIHelper.isEmpty(uriPrefix)) {
			uriPrefix = SDKUtil.getTagValue(selected, "package.class.mea.concept.uri.prefix");
		}
		String name = "";
		if (selected != null) name = selected.getName();
		return super.getDefaultConcept(uriPrefix, name);
	}
	
	private static List<String> getDomainSuperClasses(ModelSelectionEvent event) {
		List<String> list = new ArrayList<String>();
		if (event == null) return list;
		EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());
		if (eClass != null) {
			EList<EClass> eList = eClass.getESuperTypes();
			if (eList != null) {
				Iterator<EClass> it = eList.iterator();
				while(it.hasNext()) {
					EClass item = it.next();
					if (item != null) {
						list.add(item.getName());
					}
				}
			}
		}
		
		if (list.size() == 0) {
			list.add("No super types");
		}
		return list;
	}
	
	private List<String> getConcepts(ModelSelectionEvent event) {
		List<String> concepts = new ArrayList<String>();
		if (event != null && !event.isEmptyModel()) {
			EClass eClass = SDKUIManager.getInstance().getEClass(event.getFullModelName());
			if (eClass != null) {
				String value = SDKUtil.getTagValue(eClass, "class.mea.concept");
				if (!UIHelper.isEmpty(value)) {
					concepts.add(value);
				}
			}
		}
		return concepts;
	}
}