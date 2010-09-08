package gov.nih.nci.sdk.ide.modelexplorer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.widgets.Event;

public class ModelSelectionEvent extends Event {
	private EClass eClass;
	private String category;
	
	public ModelSelectionEvent(EClass eClass, String category) {
		this.eClass = eClass;
		this.category = category;
	}
	
	public EClass geteClass() {
		return eClass;
	}

	public String getCategory() {
		return category;
	}
}
