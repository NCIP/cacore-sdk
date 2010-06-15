/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.server.mgmt;

import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.AuthenticationException;
import gov.nih.nci.system.applicationservice.AuthorizationException;

import org.apache.log4j.Logger;

/**
 * This class is used to inject security into the automated generated
 * code. This class instantiates the <code>CSM APIs</code> and exposes 
 * its <code>Authentication</code> and <code>Authorization</code> services.
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class SecurityEnabler
{
	private static Logger log = Logger.getLogger(SecurityEnabler.class.getName());
	private String applicationContextName;
	private static AuthorizationManager authorizationManager;
	private static AuthenticationManager authenticationManager;
	private static int securityLevel = 999;

	/**
	 * Constructs an instance of the SecurityEnabler class for the 
	 * passed <code>ApplicationContextName</code>
	 * 
	 * @param applicationContextName The name of the application
	 */
	public SecurityEnabler(String applicationContextName)
	{
		this.applicationContextName = applicationContextName;
	}

	private AuthorizationManager getAuthorizationManager()
	{
		if (null == authorizationManager)
			try
			{
				authorizationManager = SecurityServiceProvider.getAuthorizationManager(applicationContextName);
			}
			catch (CSException e)
			{
				log.error("CSException: ", e);
			}
		return authorizationManager;
	}

	private AuthenticationManager getAuthenticationManager()
	{
		if (null == authenticationManager)
			try
			{
				authenticationManager = SecurityServiceProvider.getAuthenticationManager(applicationContextName);
			}
			catch (CSException e)
			{
				log.error("CSException: ", e);
			}
		return authenticationManager;
	}

	/**
	 * This method accepts the user credentials and verifies if they are valid or not.
	 * It obtains a <code>CSM's AuthenticationManager</code> for the 
	 * <code>ApplicationContextName</code>. The <code>CSM's AuthenticationManager</code>
	 * loads the configured <code>LoginModule</code> for the Application and use the 
	 * same to connect to the configured credential providers. It then authenticates
	 * the user credentials and returns a boolean true if authentication succeeded.
	 * 
	 * @param userId The login id for the user
	 * @param password The password for the user
	 * @return Returns a unique session key for that particular user if the credentials
	 *         are valid else returns a <code>NULL</code> object
	 * @throws ApplicationException If there was an error in user authentication 
	 */
	public String authenticate(String userId, String password) throws ApplicationException
	{
		String userName = null;
		/**
		 * Call Authentication Manager here.
		 */
		if (this.getSecurityLevel() == 0)
		{
			return userName;
		}

		boolean authenticated = false;
		if(!SecurityConfiguration.isAuthenticationDisabled())
		{
			if (null == authenticationManager)
			{
				authenticationManager = getAuthenticationManager();
			}
			try
			{
				authenticated = authenticationManager.login(userId, password);
			}
			catch (Exception ex)
			{
				authenticated = false;
				log.error("Could not authenticate the user: " + ex.getMessage());
				throw new AuthenticationException("Could not authenticate the user", ex);
			}
		}
		else //Authentication disabled
		{
			authenticated=true;
		}
		if (authenticated)
		{
			SessionManager sm = SessionManager.getInstance();
			userName = sm.initSession(userId);
		}
		return userName;
	}

	/**
	 * This methods checks whether the user session associated with the User Name 
	 * session key passed is active or not. It returns a <code>true</code> if the 
	 * user is in session or returns a <code>false</code>. If the user is in session then 
	 * the last accessed property is set to current, thereby extending the session.
	 * @param userName the User Name session key associated with the user session
	 * @return true if user session is active else returns a false
	 */
	public boolean isUserInSession(String userName)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		return sessionManager.isUserInSession(userName);
	}

	/**
	 * This method verifies whether the user has permission to perform the given
	 * operation on the passed object or not. This method instantiates the <code>
	 * CSM's AuthorizationManager</code>. The <code>AuthorizationManager</code> uses
	 * the configured hibernate file to connect to the <code>Authorization Schema</code>
	 * for that application. It retrieves the user name from the user session objects
	 * from the Tread Local Variable. It then calls the <code>checkPermission</code> method 
	 * of the <code>AuthorizationMananger</code> passing the user name, object and the 
	 * privilege. This method returns <code>true</code> if the user has authorization
	 * 
	 * @param protectionElementName The object name which the user is trying to access
	 * @param privilege the operation that the user is trying to perform on the object
	 * @return true if the user has access to perform operation on the object
	 * @throws ApplicationException If there was an error in user authorization
	 */
	public boolean hasAuthorization(String protectionElementName, String privilege) throws ApplicationException
	{
		boolean authorized = false;
		
		String userName = ClientInfoThreadVariable.getUserName();
		String password = ClientInfoThreadVariable.getPassword();
		
		if (this.isBlank(userName))
		{
			log.error("User is not logged in; sessionKey is blank");
			throw new AuthorizationException("User is not logged in");
		}
		if (!this.isUserInSession(userName))
		{
			String userId = null;
			if(!isBlank(password))
				userId = this.authenticate(userName,password);
			if (userId == null)
			{
				authorized = false;
				log.error("User is not in session");
				throw new AuthorizationException("User is not in session");
			}
			else
			{
				log.warn("User session renewed on expiration");
			}	
		}
		SessionManager sessionManager = SessionManager.getInstance();
		UserSession userSession = sessionManager.getSession(userName);
		if (userSession == null)
		{
			authorized = false;
			log.error("User is not in session");
			throw new AuthorizationException("User is not in session");
		}
		String userId = userSession.getUserId();
		/**
		 * Call AuthorizationManager here
		 */
		if (null == authorizationManager)
		{
			authorizationManager = getAuthorizationManager();
		}
		try
		{
			authorized = authorizationManager.checkPermission(userId, protectionElementName, privilege);
		}
		catch (Exception ex)
		{
			log.error("User permission check failed: " + ex.getMessage());
			authorized = false;
		}
		return authorized;
	}

	/**
	 * This method returns the security level set for the application. It reads the <code>
	 * {ApplicationContextName}.CSM.Security.Level</code> from the system properties and 
	 * returns the same. The default security level is decided by the configuration done
	 * at the compile time.
	 * 
	 * @return the security level defined in the system properties or the default set 
	 *         during the compile time.
	 */
	public int getSecurityLevel()
	{
		if (securityLevel == 999)
		{
			String str = System.getProperty(applicationContextName + Constant.CSM_SECURITY_LEVEL);
			try
			{
				Integer it = new Integer(str);
				int level = it.intValue();
				if (level > 2 || level < 0)
				{
					securityLevel = 1;
				}
				else
				{
					securityLevel = level;
				}
			}
			catch (Exception ex)
			{
				log.error("No Security level found; setting a default security level");
				securityLevel = SecurityConfiguration.getSecurityLevel().intValue();
			}
		}
		return securityLevel;
	}

	/**
	 * This methods kills the user session associated with the passed session key
	 * and logs off the user from the server
	 * @param sessionKey The key associated with the user session
	 */
	public void logOut(String userName)
	{
		SessionManager sessionManager = SessionManager.getInstance();
		sessionManager.killSession(userName);
	}

	private boolean isBlank(String string)
	{
		boolean test = false;

		if (string == null)
		{
			test = true;
		}
		else
		{
			String str1 = string.trim();
			if (str1.equals(""))
			{
				test = true;
			}
		}
		return test;
	}

}
