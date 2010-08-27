package gov.nih.nci.sdk.core.generator;

public class ECOREElement {
	String type;
	String name;
	String param;
	boolean isVoid;
	String annotations;
	String operationName;

	public ECOREElement(String type, String name) {
		super();
		this.type = type;
		this.name = name;
		operationName = name.substring(0,1).toUpperCase()+name.substring(1, name.length());
		isVoid = ((type == null || type == "void") ? true : false);
	}

	public ECOREElement(String type, String name, String annotations, String param) {
		super();
		this.type = type;
		this.name = name;
		this.param = param;
		this.annotations = annotations;
		isVoid = ((type == null || type == "void") ? true : false);
	}

	
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public boolean getIsVoid() {
		return isVoid;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
