/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.codegen;

/**
 * GeneratorException thrown by Generator
 * @author konkapv
 *
 */
public class GeneratorException extends Exception {
	private static final long serialVersionUID = 4855421836404678121L;

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(String message, Throwable t) {
		super(message, t);
	}
}
