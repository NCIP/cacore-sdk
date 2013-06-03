/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen;

import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;


/**
 * Validates the UML model.  
 * @author Satish Patel
 *
 */
public interface Validator
{
	
	/**
	 * Validates the UML model. If it finds any problem with the <code>model</model> then it puts appripriate error message in the
	 * GeneratorErrors and returns it. If there are no errors, it returns empty GeneratorErrors object
	 * 
	 * @param model
	 * @return
	 */
	public abstract GeneratorErrors validate(UMLModel model);
	
	/**
	 * Determines if the generator should execute the Validator or not
	 * @return
	 */
	public Boolean isEnabled();
	
	/**
	 * Return the user configurable (via CodegenConfig.xml) name of the Validator
	 * @return
	 */
	public String getName();	

}