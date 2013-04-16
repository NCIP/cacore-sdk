/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.client.util.xml;

public class XMLUtilityException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception rootException;

    public XMLUtilityException(String message, Throwable cause) {
        super(message);
        rootException = (Exception)cause;
    }
    
    public XMLUtilityException(String message) {
        super(message);
        rootException = null;
    }

    /**
     * Returns the root exception that caused the exception
     * in the xml serialization/deserialization routing.
     * Can also return the Exception from the underlying xml
     * serialization library.
     * @return root exception
     */
    public Exception getRootException() {
        return rootException;
    }

}
