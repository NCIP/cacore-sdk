package gov.nih.nci.restgen.mapping.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Resource")
@XmlRootElement(name="Resource")
public class Resource {

	@XmlAttribute
	private String name;
	
	@XmlElementWrapper(name="operations")
	private List<Method> methods;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setOperations(List<Method> methods) {
		this.methods = methods;
	}
	
}
