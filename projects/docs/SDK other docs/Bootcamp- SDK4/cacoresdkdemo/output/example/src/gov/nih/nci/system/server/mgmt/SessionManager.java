/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.server.mgmt;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.SecurityConfiguration;

import java.util.Hashtable;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * This class acts as a Session Manager. It instantiates the
 * user sessions and caches them. It manages the life cycle of these
 * user sessions by keeping only the active sessions in its cache and 
 * expiring the sessions which have been inactive for the pre-determined 
 * period. It also provides methods to terminate the user session. This
 * class is a <code>Singleton</code> and only single instance is maintained.
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class SessionManager
{
	private static SessionManager sessionManager;
	private Hashtable sessions;
	private long timeOut = SecurityConfiguration.getSecuritySessionTimeout().longValue();
	
	private static Logger log = Logger.getLogger(SessionManager.class.getName());	

	/**
	 * This method returns the cachced instance of the {@link SessionManager} 
	 * if it is not instantiated else it instantiates the same and returns it.
	 *  
	 * @return The singleton instance of SessionManager
	 */
	public static synchronized SessionManager getInstance()
	{
		if (sessionManager == null)
		{
			sessionManager = new SessionManager();
		}
		return sessionManager;
	}

	/**
	 * Private Constructor. It instantiates the has internal cache to store
	 * the user sessions. It also initalizes the key generator. It then reads
	 * the default timeout from the session property <code>CSM.Security.Session.Timeout</code>
	 */
	private SessionManager()
	{
		sessions = new Hashtable();
		SessionMonitor SessionMonitor = new SessionMonitor();
		try
		{
			String str = System.getProperty(Constant.CSM_SECURITY_SESSION_TIMEOUT);
			Long l = new Long(str);
			timeOut = l.longValue();
		}
		catch (Exception ex)
		{
			timeOut = SecurityConfiguration.getSecuritySessionTimeout().longValue();
		}
	}

	/**
	 * This method instantiates a {@link UserSession} object for the passes user id.
	 * It also generates a unique key and caches the user object using the user Id as key
	 * and the {@link UserSession} as the value 
	 * @param userId The login id of the user trying to log in 
	 * @return The userId associated with the user session representing the user
	 */
	public String initSession(String userId)
	{
		cleanUp();
		UserSession userSession = new UserSession(userId);
		sessions.put(userId, userSession);
		return userId;
	}
	
	/**
	 * This methods kills the user session associated with the passed User Name session 
	 * key and removes the user session object from its cache
	 * @param userName The User Name session key associated with the user session
	 */
	public void killSession(String userName)
	{
		if (this.isBlank(userName))
		{
			return;
		}
		if (sessions.containsKey(userName))
		{
			sessions.remove(userName);
		}
	}
	
	/**
	 * This methods looks up in the internal cache of the SessionManager and returns
	 * the {@link UserSession} object associated with the passed session key. If the 
	 * user is in session then the last accessed property is set to current, thereby 
	 * extending the session.
	 * 
	 * @param userName The User Name session key associated with the user session
	 * @return The (@link UserSession) object for the session key passed
	 */
	public UserSession getSession(String userName)
	{
		UserSession userSession = (UserSession) sessions.get(userName);
		if (userSession != null)
		{
			userSession.setLastAccessedTime(System.currentTimeMillis());
		}
		return userSession;
	}

	/**
	 * This method returns the List of the all the session keys from the internal cache
	 * 
	 * @return The set of all the session keys from the internal cache
	 */
	protected UserSession[] getSessionKeySet()
	{
		//return sessions.keySet();
		synchronized(sessions)
		{
			UserSession[] sessionArray = new UserSession[sessions.size()]; 
			return (UserSession[])sessions.values().toArray(sessionArray);
		}
	}

	protected long getTimeOut()
	{
		return this.timeOut;
	}	
	
	/**
	 * This method returns the {@link UserSession} ojbect just for the purpose of 
	 * monitoring. It doesnt update the last accessed time like the 
	 * {@link SessionManager#getSession(String)} method
	 * 
	 * @param userName The User Name session key associated with the user session
	 * @return The (@link UserSession) object for the session key passed
	 */
	protected UserSession getSessionForMonitoring(String userName)
	{
		UserSession userSession = (UserSession) sessions.get(userName);
		return userSession;
	}

	/**
	 * This methods checks whether the user session associated with the User Name 
	 * session key passed is active or not. It returns a <code>true</code> if the 
	 * user is in session or returns a <code>false</code>. If the user is in session 
	 * then the last accessed property is set to current, thereby extending the session.
	 * 
	 * @param userName the User Name session key associated with the user session
	 * @return true if user session is active else returns a false
	 */
	public boolean isUserInSession(String userName)
	{
		boolean inSession = false;
		if (sessions.containsKey(userName))
		{
			UserSession userSession = (UserSession) sessions.get(userName);
			if (System.currentTimeMillis() - userSession.getLastAccessedTime() > timeOut)
			{
				this.killSession(userName);
			}
			else
			{
				inSession = true;
			}
		}
		return inSession;
	}

	private void cleanUp()
	{
		if (sessions.size() > 200)
		{
			new SessionMonitor();
		}
	}

	private boolean isBlank(String str)
	{
		boolean test = false;

		if (str == null)
		{
			test = true;
		}
		else
		{
			String str1 = str.trim();
			if (str1.equals(""))
			{
				test = true;
			}
		}
		return test;
	}

}
