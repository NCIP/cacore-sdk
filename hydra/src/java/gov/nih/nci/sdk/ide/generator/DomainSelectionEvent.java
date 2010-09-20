package gov.nih.nci.sdk.ide.generator;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class DomainSelectionEvent extends Event {
	private String packageName;
	private String modelName;
	private Date timestamp;
	
	public DomainSelectionEvent(String packageName, String modelName) {
		this.packageName = packageName;
		this.modelName = modelName;
		this.timestamp = new Date();
		super.type = SWT.Selection;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getModelName() {
		return modelName;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getFullModelName() {
		return (packageName == null || "".equals(packageName)) ? modelName
				: (packageName + "." + modelName);
	}
	
	public String getEventName() {
		return getFullModelName();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("packageName=").append(packageName).append(", ");
		sb.append("modelName=").append(modelName);
		return sb.toString();
	}
}
