/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public abstract class GroupPanel extends ActiveUI {
	public GroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data);
		((Group)super.getUIComposite()).setText(title);
	}
	
	protected Composite initUIComposite() {
		Composite parent = getParent();
		
		Group theGroup = new Group(parent, SWT.NONE);
		theGroup.setLayoutData(UIHelper.getCoverAllGridData());
		theGroup.setLayout(UIHelper.getOneColumnLayout());
		
		return theGroup;
	}
}
