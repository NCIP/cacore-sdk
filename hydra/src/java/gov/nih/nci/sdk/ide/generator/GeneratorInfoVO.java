package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.PropertyVO;

import java.util.ArrayList;
import java.util.List;

public class GeneratorInfoVO {
	private String name = "";
	private String description = "";
	private List<PropertyVO> properties = new ArrayList<PropertyVO>();
	
	/**
	 * @return the generator name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the generator name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the generator description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description generator description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the properties
	 */
	public List<PropertyVO> getProperties() {
		return properties;
	}
	
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<PropertyVO> properties) {
		this.properties = properties;
	}
	
	/**
	 * Adds a ne property.
	 */
	public void addProperty(PropertyVO property) {
		properties.add(property);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append(", ");
		sb.append("description=").append(name).append(", ");
		sb.append("properties=").append(properties);
		return sb.toString();
	}
}
