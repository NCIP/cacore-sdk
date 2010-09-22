package gov.nih.nci.sdk.ide.core;

/**
 * PropertyVO class
 * 
 * @author John Chen
 */
public class PropertyVO {
	private String name = "";
	private String value = "";
	private String defaultValue = "";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append(", ");
		sb.append("value=").append(value).append(", ");
		sb.append("defaultValue=").append(defaultValue);
		return sb.toString();
	}
}
