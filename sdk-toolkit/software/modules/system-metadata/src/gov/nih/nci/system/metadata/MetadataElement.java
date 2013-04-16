/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.metadata;

import java.util.HashMap;
import java.util.Map;

public class MetadataElement {
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(String name, String value)
	{
		if(attributes == null)
			attributes = new HashMap();
		
		attributes.put(name, value);
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Name: "+name);
		buffer.append("\nclass name: "+className);
		buffer.append("\full name: "+fullName);
		buffer.append("\nPublic Id: "+publicId);
		buffer.append("\nVersion Id: "+versionId);
		buffer.append("\nattributes: "+attributes.toString());
		java.util.Iterator<String> iter = attributes.keySet().iterator();
		while(iter.hasNext())
		{
			String attrName = iter.next();
			buffer.append("\nattribute name: "+attrName);
			buffer.append("\nattribute value: "+attributes.get(attrName));
		}
		return buffer.toString();
	}
	
	public String publicId;
	public String versionId;
	public String fullName;
	public String className;
	public String name;
	public Map<String, String> attributes;

}
