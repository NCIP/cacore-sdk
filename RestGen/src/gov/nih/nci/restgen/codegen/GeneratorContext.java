/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.codegen;

import gov.nih.nci.restgen.mapping.model.Mapping;

import org.apache.log4j.Logger;

/**
 * Generator context to be used by Code generator. Context can be created by a caller
 * and construct generator with it. Generator can run using Mapping or Mapping xml.
 * Generator messages (info, warning and error messages) are set on Logger. 
 * @author konkapv
 *
 */
public class GeneratorContext {
	private Mapping mapping;
	private String mappingXMLPath;

	private static Logger log = Logger.getLogger(Generator.class);

	/**
	 * Constructor With Mapping
	 * @param mapping
	 */
	public GeneratorContext(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * Constructor with mapping xml
	 * @param mappingXMLPath
	 */
	public GeneratorContext(String mappingXMLPath) {
		this.mappingXMLPath = mappingXMLPath;
	}

	/**
	 * Get mapping
	 * @return
	 */
	public Mapping getMapping() {
		return mapping;
	}

	/**
	 * Set mapping
	 * @param mapping
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * Logger to log generator messages
	 * @return
	 */
	public Logger getLogger() {
		return log;
	}

	/**
	 * get mapping xml path
	 * @return String
	 */
	public String getMappingXMLPath() {
		return mappingXMLPath;
	}
}
