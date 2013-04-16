/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.converter;

/**
 * ConverterInfoVO class.
 * 
 * @author John Chen
 */
public class ConverterInfoVO {
	private String name = "";
	private String desc = "";
	private String creator = "";
	private String className = "";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public void setDescription(String desc) {
		this.desc = desc;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append(", ");
		sb.append("desc=").append(desc).append(", ");
		sb.append("creator=").append(creator).append(", ");
		sb.append("className=").append(className);
		return sb.toString();
	}
}
