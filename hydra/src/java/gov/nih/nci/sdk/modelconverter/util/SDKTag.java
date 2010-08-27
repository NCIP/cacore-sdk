package gov.nih.nci.sdk.modelconverter.util;

import org.eclipse.emf.ecore.impl.EAnnotationImpl;

public class SDKTag extends EAnnotationImpl {
	private String name;
	private String value;
	
	public SDKTag(String name, String value) {
		super();
		this.name = name;
		this.value = value;
		super.getDetails().put(name, value);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
