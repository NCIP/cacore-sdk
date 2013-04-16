/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen;

import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;

/**
 * Component which transforms the model into artifact(s)
 * 
 * @author Satish Patel
 */
public interface Transformer
{
	
	/**
	 * Perform validation of the model.
	 * @param model
	 * @return
	 */
	public GeneratorErrors validate(UMLModel model);
	
	/**
	 * Executes the transformer and generate the artifact(s) from the model
	 * @param model
	 * @return
	 */
	public GeneratorErrors execute(UMLModel model);
	
	/**
	 * Determines if the generator should execute the Transformer or not
	 * @return
	 */
	public Boolean isEnabled();
	
	/**
	 * Return the user configurable (via CodegenConfig.xml) name of the Transformer
	 * @return
	 */
	public String getName();
	
}