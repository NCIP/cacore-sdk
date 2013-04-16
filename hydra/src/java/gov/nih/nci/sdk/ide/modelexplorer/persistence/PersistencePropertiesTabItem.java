/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer.persistence;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

import gov.nih.nci.sdk.ide.core.CategoryTabItem;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;

public class PersistencePropertiesTabItem extends CategoryTabItem {

	public PersistencePropertiesTabItem(TabFolder parent, int style, Object data) {
		super(parent, style, data, Constants.TAB_Properties);
	}

	@Override
	public void paint() {
		Composite composite = super.getUIComposite();
		composite.setLayout(UIHelper.getTwoColumnLayout());
		
		Button button = new Button(composite, SWT.PUSH);
		button.setText("Replace me for " + super.getTitle());
		button.setLayoutData(UIHelper.getCoverAllGridData());
		UIHelper.setWhiteBackground(button);
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
