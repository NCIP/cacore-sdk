/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.mapping.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Options")
@XmlRootElement(name="Options")
public class Options {
	public final static String EJB="EJB";
	public final static String SOAP_SERVICE="SOAP_SERVICE";
	
	@XmlAttribute
	private String outputPath;
	
	@XmlAttribute
	private String rootPath;

	@XmlAttribute
	private String wrapperType;
	
	@XmlAttribute
	private String wsdlLocation;

	@XmlAttribute
	private String ejbLocation;
	
	@XmlAttribute
	private String wsdlBindingFile;
	
	public String getWsdlBindingFile() {
		return wsdlBindingFile;
	}

	public void setWsdlBindingFile(String wsdlBindingFile) {
		this.wsdlBindingFile = wsdlBindingFile;
	}

	public String getWsdlLocation() {
		return wsdlLocation;
	}

	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}

	public String getEjbLocation() {
		return ejbLocation;
	}

	public void setEjbLocation(String ejbLocation) {
		this.ejbLocation = ejbLocation;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getWrapperType() {
		return wrapperType;
	}

	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
}
