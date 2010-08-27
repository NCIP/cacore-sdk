package gov.nih.nci.sdk.core.generator;

public class ECOREOperation {

	String name;
	String returnType;
	String params;
	String annotations;
	
	public ECOREOperation(String name, String returnType, String params,
			String annotations) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.params = params;
		this.annotations = annotations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getAnnotations() {
		return annotations;
	}

	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}
	
	
}
