/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.server.mgmt;

import java.util.Iterator;
import java.util.Set;

/**
 * This class is a daemon thread which runs in the background monitoring
 * the {@link UserSession}. It goes through the internal cache of the
 * {@link SessionManager} and removes all the sessions that have expired   
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class SessionMonitor extends Thread
{

	/**
	 * Default Constructor. It starts the thread as a daemon running in the background
	 */
	public SessionMonitor()
	{
		this.start();
	}

	/**
	 * This methods obtains all the session keys from the {@link SessionManager}'s 
	 * internal cache. It then retrieves the {@link UserSession} object passing each
	 * of the session keys. It checks if the session was last access within the timeout 
	 * period. If not then it kills the user session.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		monitor();
	}

	private void monitor()
	{
		SessionManager sessionManager = SessionManager.getInstance();
		UserSession keys[] = sessionManager.getSessionKeySet();
		
		if(keys.length==0) return;
		
		for(int i=0;i<keys.length;i++)
		{
			UserSession userSession = keys[i];
			String sessionKey = (String) userSession.get(keys[i]);
			if (System.currentTimeMillis() - userSession.getLastAccessedTime() > sessionManager.getTimeOut())
			{
				sessionManager.killSession(sessionKey);
			}
		}
/*
 		Set keys = sessionManager.getSessionKeySet();
 		while (it.hasNext())
		{
			String sessionKey = (String) it.next();
			UserSession userSession = sessionManager.getSessionForMonitoring(sessionKey);
			if (System.currentTimeMillis() - userSession.getLastAccessedTime() > sessionManager.getTimeOut())
			{
				sessionManager.killSession(sessionKey);
			}
		}
*/
	}

}
