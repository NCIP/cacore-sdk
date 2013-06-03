/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.generator;

import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;

public class DomainSelectionEvent extends Event {
	private String packageName;
	private List<String> modelNames;
	private Date timestamp;
	
	public DomainSelectionEvent(List<String> modelNames) {
		this.modelNames = modelNames;
		this.timestamp = new Date();
		super.type = SWT.Selection;
	}
	
	public DomainSelectionEvent(String packageName, List<String> modelNames) {
		this.packageName = packageName;
		this.modelNames = modelNames;
		this.timestamp = new Date();
		super.type = SWT.Selection;
	}

	public String getPackageName() {
		return packageName;
	}

	public List<String> getModelNames() {
		return modelNames;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getEventName() {
		return this.getClass().getName();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("packageName=").append(packageName).append(", ");
		sb.append("modelNames=").append(modelNames);
		return sb.toString();
	}
}
