/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

public abstract class ActiveViewPart extends ViewPart implements Listener {
	private Composite uiComposite;
	
	public Composite getUIComposite() {
		return uiComposite;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		uiComposite = parent;
		paint(parent);
	}
	
	public abstract void paint(Composite parent);
	
	public void handleEvent(Event event) {
	}
	
	public void publishEvent(int eventType, Event event) {
		SDKUIManager.getInstance().publishEvent(eventType, event);
	}
}
