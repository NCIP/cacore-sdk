package gov.nih.nci.restgen.mapping.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Implementation")
@XmlRootElement(name="Implementation")
public class Implementation {
	public final static String EJB="EJB";
	public final static String SOAP_SERVICE="SOAP_SERVICE";
	@XmlAttribute
	private String type;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String clientType;
	
	@XmlAttribute
	private String path;
	
	@XmlAttribute
	private String classpath;
	
	private Operation operation;
	
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
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public static String getEjb() {
		return EJB;
	}
	public static String getSoapService() {
		return SOAP_SERVICE;
	}
}
