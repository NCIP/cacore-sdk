/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SDKQuery")
public class SDKQuery {

	private static final long serialVersionUID = 1L;
	private String pathString;
	private List parameters;	
	
	public SDKQuery()
	{
	}

	public SDKQuery(String pathString)
	{
		this.pathString = pathString;
	}
	
	public SDKQuery(String pathString, List parameters)
	{
		this.pathString = pathString;
		this.parameters = parameters;
	}	

	@XmlAttribute
	public String getpathString()
	{
		return this.pathString;
	}
	
	public void setPathString(String pathString) {
		this.pathString = pathString;
	}
	
	@XmlAttribute
	public List getParameters() {
		return parameters;
	}
	
	public void setParameters(List parameters) {
		this.parameters = parameters;
	}

	
}
