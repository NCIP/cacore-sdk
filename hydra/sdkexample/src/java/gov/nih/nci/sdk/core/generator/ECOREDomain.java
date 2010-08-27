package gov.nih.nci.sdk.core.generator;

import java.util.List;

public class ECOREDomain {

	String name;
	String packageName;
	List attributes;
	List operations;
	List references;
	List annotations;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public List getAttributes() {
		return attributes;
	}
	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}
	public List getOperations() {
		return operations;
	}
	public void setOperations(List operations) {
		this.operations = operations;
	}
	public List getReferences() {
		return references;
	}
	public void setReferences(List references) {
		this.references = references;
	}
	public List getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List annotations) {
		this.annotations = annotations;
	}
	
	
}
