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

import gov.nih.nci.sdk.util.SDKUtil;
import gov.nih.nci.sdk.util.EcoreUtil;
import org.eclipse.emf.ecore.EClassifier;

public class MeaningDomainTabItem
   extends CategoryTabItem
{
	private String domainName;
	private String domainDescription;
	private List<String> conceptList;
	private EClassifier eClassifier; //the domain object to be displayed.

	public String getDomainName() { return domainName; }
	public String getDomainDescription() { return domainDescription; }
	public List<String> getConceptList() { return conceptList; }
	public EClassifier getEClassifier() { return eClassifier; }

	public void setDomainName(String _domainName) { domainName = _domainName; }
	public void setDomainDescription(String _domainDescription) { domainDescription = _domainDescription; }
	public void setConceptList(List<String> _conceptList) { conceptList = _conceptList; }
	public void setEClassifier(EClassifier _eClassifier) { eClassifier = _eClassifier; }
	
	public MeaningDomainTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Domain);
		setConceptList(new ArrayList<String>());
	}
	
	@Override
	protected void prepareData()
	{
		String domainName = "No domain selected";
		String domainDescription = "";
		
		if (getEClassifier() != null)
		{
			domainDescription = SDKUtil.getTagValue(getEClassifier(), "class.mea.desc");
			setDomainDescription((domainDescription != null) ? domainDescription : "");
			domainName = EcoreUtil.determineFullyQualifiedName(getEClassifier());

			String concept = SDKUtil.getTagValue(getEClassifier(), "class.mea.concept");
			concept = (concept != null && "".equalsIgnoreCase(concept) == true) ? concept : null;

			if (concept != null) { getConceptList().add(concept); }
		}

		setDomainName((domainName != null) ? domainName : "No domain selected");
	}

	@Override
	public void paint()
	{
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());
		
		new Label(composite, SWT.NONE).setText("Domain Name");
		Text domainNameText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(domainName);
		domainNameText.setLayoutData(super.getGridData());
		
		new Label(composite, SWT.NONE).setText("Description");
		Text domainDescText = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(getDomainDescription());
		domainDescText.setLayoutData(super.getGridData());
		
		new Label(composite, SWT.NONE).setText("Concept");
		
		Composite conceptsArea = new Composite(composite, SWT.NONE);
		GridLayout cgd = (GridLayout)super.getLayout();
		cgd.numColumns = 1;
		conceptsArea.setLayout(cgd);
		
		Text conceptText = null;
		for (String concept: conceptList)
		{
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(super.getGridData());
		}

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
