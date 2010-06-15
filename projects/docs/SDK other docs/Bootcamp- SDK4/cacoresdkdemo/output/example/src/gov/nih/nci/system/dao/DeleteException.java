/*
 * Created on Jun 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.dao;

/**
 * This exception is thrown by all the methods of the {@link ApplicationService}
 * interface. This exception contains the actual error or the error message of 
 * the business error that occured during processing the request.
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class DeleteException extends DAOException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor. Constructs the (@link DeleteException) object 
	 */
	public DeleteException()
	{
		super();
	}
	/**
	 * Constructs the {@link DeleteException} object with the passed message 
	 * @param message The message which is describes the exception caused
	 */
	public DeleteException(String message)
	{
		super(message);
	}
	/**
	 * Constructs the {@link DeleteException} object with the passed message.
	 * It also stores the actual exception that occured 
	 * @param message The message which describes the exception
	 * @param cause The actual exception that occured
	 */
	public DeleteException(String message, Throwable cause)
	{
		super(message, cause);
	}
	/**
	 * Constructs the {@link DeleteException} object storing the actual 
	 * exception that occured 
	 * @param cause The actual exception that occured
	 */
	public DeleteException(Throwable cause)
	{
		super(cause);
	}
}
