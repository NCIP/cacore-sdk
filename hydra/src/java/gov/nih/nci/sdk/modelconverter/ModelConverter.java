/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.modelconverter;

import org.eclipse.emf.ecore.EPackage;

public interface ModelConverter {

	/**
	 * Converts the file content to Ecore model.
	 * 
	 * @param filePath
	 *            full file path to the file.
	 * @return a root instance of EPackage or <tt>null</tt> if there is none.
	 * @throws Exception
	 */
	public EPackage convert(String filePath) throws Exception;
}
