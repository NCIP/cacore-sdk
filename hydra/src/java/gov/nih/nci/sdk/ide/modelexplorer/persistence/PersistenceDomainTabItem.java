package gov.nih.nci.sdk.ide.modelexplorer.persistence;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

public class PersistenceDomainTabItem extends CategoryTabItem {
	private String domainName;
	private String tableName;
	private String domainDesc;

	public PersistenceDomainTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Domain);
	}
	
	@Override
	protected void prepareData() {
		ModelSelectionEvent mse = (ModelSelectionEvent)this.getData();
		domainName = mse.getFullModelName();
		
		tableName = "PERSON";
		
		domainDesc = mse.getFullModelName();
		
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
		domainDesc += "\nThis is a very very long description which should come from UML.";
	}

	@Override
	public void paint() {
		Composite composite = super.getUIComposite();
		composite.setLayout(super.getLayout());
		
		new Label(composite, SWT.NONE).setText("Table Name");
		Text domainNameText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(tableName);
		domainNameText.setLayoutData(super.getGridData());
		
		new Label(composite, SWT.NONE).setText("Description");
		Text domainDescText = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(domainDesc);
		domainDescText.setLayoutData(super.getGridData());

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
