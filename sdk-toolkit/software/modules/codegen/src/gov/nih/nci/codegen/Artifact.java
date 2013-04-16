/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen;


/**
 * Provides a way to access the generated output
 * 
 * @author Satish Patel
 *
 */
public interface Artifact
{
	/**
	 * Returns the generated content
	 * 
	 * @return the content
	 */
	public String getContent();
	
	/**
	 * Returns the name of the component from which the artifact was generated
	 * 
	 * @return
	 */
	public String getSourceName();

	
}