/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public abstract class ActiveUI implements Listener {
	private Composite parent;
	private int style;
	private Object data;
	private Composite uiComposite;
	
	public ActiveUI(Composite parent, int style, Object data) {
		this.parent = parent;
		this.style = style;
		this.data = data;
		
		this.uiComposite = initUIComposite();
	}
	
	public Composite getParent() {
		return parent;
	}

	public int getStyle() {
		return style;
	}

	public Object getData() {
		return data;
	}
	
	public Composite getUIComposite() {
		return uiComposite;
	}

	protected abstract Composite initUIComposite();
	
	public abstract void paint();
	
	@Override
	public void handleEvent(Event event) {
	}
	
	public void addListener(int eventType, Listener listener) {
		uiComposite.addListener(eventType, listener);
	}
	
	public void notifyListeners(int eventType, Event event) {
		uiComposite.notifyListeners(eventType, event);
	}
}
