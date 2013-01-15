package gov.nih.nci.restgen.mapping.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Method")
@XmlRootElement(name="Method")
public class Method {
	
	@XmlAttribute
	private String name;
	
	private Implementation implementation;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Implementation getImplementation() {
		return implementation;
	}
	
	public void setImplementation(Implementation implementation) {
		this.implementation = implementation;
	}

}
