package gov.nih.nci.system.server.mgmt;

import java.util.Hashtable;

/**
 * This class acts as a session storage object for the user. It stores the
 * User id and the last time the session was accessed time. It also provides
 * capabilities to add and remove user related attributes.
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class UserSession extends Hashtable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long lastAccessedTime;
	
	/**
	 * Contructs the {@link UserSession} object for the given id. It also
	 * stores the current time in milliseconds as the time when this object was
	 * accessed last.
	 * 
	 * @param userId The login id of the user for which the session is instantiated
	 */
	public UserSession(String userId)
	{
		super();
		super.put("userId", userId);
		this.setLastAccessedTime(System.currentTimeMillis());
	}
	
	/**
	 * Sets the time (in milliseconds) as the time when this object was accessed last
	 * 
	 * @param time The time when this object was last accessed. It should be in milliseconds.
	 */
	public synchronized void setLastAccessedTime(long time)
	{
		this.lastAccessedTime = time;
	}
	
	/**
	 * This method returns the time in milliseconds when this ojbect was last accessed
	 * 
	 * @return Returns the time in milliseconds when this object was last accessed
	 */
	public long getLastAccessedTime()
	{
		return lastAccessedTime;
	}
	
	/**
	 * This methods accepts a name-value pair and stores the same in the {@link UserSession}
	 * hastable object.
	 * 
	 * @param key The name of the object to be stored
	 * @param value The actual object which is to be stored
	 */
	public void setAttribute(String key, Object value)
	{
		this.setLastAccessedTime(System.currentTimeMillis());
		super.put(key, value);
	}

	/**
	 * This method retrieves the stored attribute value with the passed name as the key from
	 * the {@link UserSession} object
	 *  
	 * @param key Name of the attribute which is to be retrieves from the UserSession object 
	 * @return The object retrieved for the passed key from the UserSession object
	 */
	public Object getAttribute(String key)
	{
		this.setLastAccessedTime(System.currentTimeMillis());
		return super.get(key);
	}
	
	/**
	 * This method removes the stored attribute value with the passed name as the key from
	 * the {@link UserSession} object
	 *  
	 * @param key Name of the attribute which is to be removed from the UserSession object 
	 */
	public void removeAttribute(String key)
	{
		this.setLastAccessedTime(System.currentTimeMillis());
		super.remove(key);
	}
	
	/**
	 * This method returns the user id which is associated with this
	 * {@link UserSession} object
	 * @return The user id associated with this UserSession
	 */
	public String getUserId()
	{
		return (String) this.getAttribute("userId");
	}

}
