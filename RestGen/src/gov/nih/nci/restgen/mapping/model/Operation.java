package gov.nih.nci.restgen.mapping.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Operation")
@XmlRootElement(name="Operation")
public class Operation {
	
	@XmlAttribute
	private String name;
	
	@XmlElementWrapper(name="inputs",
			namespace="gme://caCORE.caCORE/RESTfulWrapper/gov.nih.nci.restgen.mapping")
	List<Input> inputs;
	Output output;
	
	public List<Input> getInputs() {
		return inputs;
	}
	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}
	public Output getOutput() {
		return output;
	}
	public void setOutput(Output output) {
		this.output = output;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
