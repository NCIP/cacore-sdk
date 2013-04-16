/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.PropertyVO;

import java.util.ArrayList;
import java.util.List;

public class GeneratorInfoVO {
	private String name = "";
	private String description = "";
	private java.util.Properties properties = new java.util.Properties();
	
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
	public java.util.Properties getProperties() {
		return properties;
	}
	
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(java.util.Properties properties) {
		this.properties = properties;
	}
	
	/**
	 * Adds a new property.
	 */
	public void addProperty(String _name, String _value) {
		properties.setProperty(_name, _value);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append(", ");
		sb.append("description=").append(name).append(", ");
		sb.append("properties=").append(properties);
		return sb.toString();
	}
}