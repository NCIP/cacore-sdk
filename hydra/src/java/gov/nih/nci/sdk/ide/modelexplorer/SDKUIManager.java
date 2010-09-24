package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.converter.SDKModelConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * SDKUIManager has overall responsibility on ide.
 * 
 * @author John Chen
 */
public class SDKUIManager {
	private static SDKUIManager me;
	
	private EPackage rootEPackage;
	
	private Map<Integer, List<Listener>> listenersMap = new HashMap<Integer, List<Listener>>();
	private Event lastSelection;
	
	private SDKUIManager() {
	}
	
	public static SDKUIManager getInstance() {
		if (me == null) me = new SDKUIManager();
		return me;
	}
	
	public EPackage getRootEPackage() {
		return rootEPackage;
	}
	
	public void setRootEPackage(EPackage rootEPackage) {
		this.rootEPackage = rootEPackage;
		ModelDataChangeEvent mdcEvent = new ModelDataChangeEvent(rootEPackage);
		notifyListeners(SWT.SetData, mdcEvent);
	}
	
	public void notifyListeners(int eventType, Event event) {
		List<Listener> listeners = listenersMap.get(eventType);
		if (listeners != null) {
			for (Listener listener: listeners) {
				listener.handleEvent(event);
			}
		}
		else {
			if (eventType == SWT.Selection) lastSelection = event;
		}
	}
	
	public void registerAsListener(int eventType, Listener listener, boolean single) {
		if (listener == null) return;
		
		List<Listener> listeners = listenersMap.get(eventType);
		if (listeners == null) {
			listeners = new ArrayList<Listener>();
			listenersMap.put(eventType, listeners);
		}
		
		if (single) {
			Iterator<Listener> it = listeners.iterator();
			while(it.hasNext()) {
				if (listener.getClass().getName().equals(it.next().getClass().getName())) {
					it.remove();
				}
			}
		}
		
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	public void registerAsListener(int eventType, Listener listener) {
		registerAsListener(eventType, listener, true);
	}
	
	public void lastSelection() {
		if (lastSelection != null) {
			notifyListeners(SWT.Selection, lastSelection);
		}
	}
	
	public void publishEvent(int eventType, Event event) {
		notifyListeners(eventType, event);
	}
}
