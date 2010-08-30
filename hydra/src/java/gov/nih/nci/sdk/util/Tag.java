package gov.nih.nci.sdk.util;

import gov.nih.nci.sdk.modelconverter.util.ModelConverterUtil;

import org.eclipse.emf.ecore.impl.EAnnotationImpl;

/**
 * Tag
 * 
 * @author John Chen
 *
 */
public class Tag extends EAnnotationImpl {
	private String name;
	private String value;
	
	public Tag(String name, String value) {
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
	
	/**
	 * Checks if the tag is for SDK code generation.
	 */
	public boolean isSDKTag() {
		return ModelConverterUtil.isSDKTag(name);
	}
}
