/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen;

public class GenerationException extends Exception
{

	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	
	public GenerationException(String message, Exception e)
	{
		super(message,e);
	}


	public GenerationException(String message) {
		super(message);
	}
	
}