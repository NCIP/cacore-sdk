package gov.nih.nci.common.exception;

/**
 * Created by IntelliJ IDEA.
 * User: kherm
 * Date: Oct 24, 2005
 * Time: 11:19:08 AM
 * To change this template use File | Settings | File Templates.
 */
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
