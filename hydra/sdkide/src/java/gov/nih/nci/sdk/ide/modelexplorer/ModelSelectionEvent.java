package gov.nih.nci.sdk.ide.modelexplorer;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class ModelSelectionEvent extends Event {
	private EClass eClass;
	private String className;
	private String category;
	private Date timestamp;
	
	public ModelSelectionEvent(EClass eClass, String className, String category) {
		this.eClass = eClass;
		this.className = className;
		this.category = category;
		this.timestamp = new Date();
		super.type = SWT.Selection;
	}
	
	public EClass geteClass() {
		return eClass;
	}

	public String getClassName() {
		return className;
	}

	public String getCategory() {
		return category;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("className=").append(className).append(", ");
		sb.append("category=").append(category);
		return sb.toString();
	}
}
