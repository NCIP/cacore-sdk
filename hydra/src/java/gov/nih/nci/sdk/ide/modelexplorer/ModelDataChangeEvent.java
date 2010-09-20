package gov.nih.nci.sdk.ide.modelexplorer;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class ModelDataChangeEvent extends Event {
	private Date timestamp;
	
	public ModelDataChangeEvent(Object data) {
		super.data = data;
		super.type = SWT.SetData;
		timestamp = new Date();
	}

	public Object getData() {
		return super.data;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getEventName() {
		return this.getClass().getSimpleName();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("data=").append(super.data);
		return sb.toString();
	}
}
