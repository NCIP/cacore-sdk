package gov.nih.nci.sdk.ide.modelexplorer;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class ModelSelectionEvent extends Event {
	private String modelName;
	private String category;
	private Date timestamp;
	
	public ModelSelectionEvent(String modelName, String category) {
		this.modelName = modelName;
		this.category = category;
		this.timestamp = new Date();
		super.type = SWT.Selection;
	}

	public String getModelName() {
		return modelName;
	}

	public String getCategory() {
		return category;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getEventName() {
		return modelName + " - " + category;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("className=").append(modelName).append(", ");
		sb.append("category=").append(category);
		return sb.toString();
	}
}
