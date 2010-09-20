package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.emf.ecore.EPackage;

public abstract class ActiveUI implements Listener {
	private Composite parent;
	private int style;
	private Object data;
	private Composite uiComposite;
	private EPackage ePackage; //Every significant widget in the application should have access to the EPackage.
	
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

	public EPackage getEPackage()
	{
		return ePackage;
	}

	protected void setEPackage(EPackage _ePackage) { ePackage = _ePackage; }

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
