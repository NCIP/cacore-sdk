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

public class MeaningDomainTabItem
   extends CategoryTabItem
   implements DomainView
{
	private String domainName = "";
	private String domainDesc = "";
	private List<String> conceptList = new ArrayList<String>();

	public String getDomainName() { return domainName; }
	public String getDomainDesc() { return domainDesc; }
	public List<String> getConceptList() { return conceptList; }

	public void setDomainName(String _domainName) { domainName = _domainName; }
	public void setDomainDesc(String _domainDesc) { domainDesc = _domainDesc; }
	public void setConceptList(List<String> _conceptList) { conceptList = _conceptList; }

	public MeaningDomainTabItem(TabFolder parent, int style, Object data)
	{
		super(parent, style, data, Constants.TAB_Domain);
	}
	
	@Override
	protected void prepareData()
	{
		System.out.println("MeaningDomainTabItem prepareData() called");
		ModelSelectionEvent mse = (ModelSelectionEvent)this.getData();
	}

	@Override
	public void paint()
	{
		System.out.println("MeaningDomainTabItem paint() called");
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
		for (String concept: getConceptList()) {
			conceptText = new Text(conceptsArea, SWT.BORDER | SWT.READ_ONLY);
			conceptText.setText(concept);
			conceptText.setLayoutData(super.getGridData());
		}

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
