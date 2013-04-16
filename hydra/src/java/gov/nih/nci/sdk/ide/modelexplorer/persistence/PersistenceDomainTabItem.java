/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer.persistence;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

public class PersistenceDomainTabItem extends CategoryTabItem {
	private String tableName;
	private String domainDesc;

	public PersistenceDomainTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Domain);
	}

	@Override
	public void paint() {
		ModelSelectionEvent mse = (ModelSelectionEvent)this.getData();
		
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
		
		Composite composite = super.getUIComposite();
		composite.setLayout(UIHelper.getTwoColumnLayout());
		
		Label nameLabel = new Label(composite, SWT.NONE);
		nameLabel.setText("Table Name");
		UIHelper.setWhiteBackground(nameLabel);
		Text domainNameText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		domainNameText.setText(tableName);
		domainNameText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(domainNameText);
		
		Label descLabel = new Label(composite, SWT.NONE);
		descLabel.setText("Description");
		UIHelper.setWhiteBackground(descLabel);
		Text domainDescText = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		domainDescText.setText(domainDesc);
		domainDescText.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(domainDescText);

		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
