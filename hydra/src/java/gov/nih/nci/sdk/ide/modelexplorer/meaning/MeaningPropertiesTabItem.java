package gov.nih.nci.sdk.ide.modelexplorer.meaning;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.GridLayout;
import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;

public class MeaningPropertiesTabItem extends CategoryTabItem {

	public MeaningPropertiesTabItem(TabFolder parent, int style, Object data)
	{
		super(parent, style, data, Constants.TAB_Properties);
	}

	@Override
	public void paint()
	{
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());
		
		new Label(composite, SWT.NONE).setText("Domain Name");
		Text domainNameText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText("Bediako");
		domainNameText.setLayoutData(super.getGridData());

		new Label(composite, SWT.NONE).setText("Description");
		Text domainDescText = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText("Bediako Description");
		domainDescText.setLayoutData(super.getGridData());

		List propertiesList = new List(composite, SWT.NONE);
		propertiesList.add("Bediako");
		propertiesList.add("George");
		
		Composite conceptsArea = new Composite(composite, SWT.NONE);
		GridLayout cgd = (GridLayout)super.getLayout();
		cgd.numColumns = 1;
		conceptsArea.setLayout(cgd);
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public void prepareData() {}
}
