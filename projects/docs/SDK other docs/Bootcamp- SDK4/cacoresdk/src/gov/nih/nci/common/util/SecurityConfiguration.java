package gov.nih.nci.common.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public class SecurityConfiguration
{
	private static Logger log= Logger.getLogger(SecurityConfiguration.class.getName());
	private static String applicationName;
	private static Integer securityLevel;
	private static Long securitySessionTimeout;
	private static boolean authenticationDisabled;
	
	static 
	{
		try
		{
			Properties _properties = new Properties();
			_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));
			applicationName = _properties.getProperty("CSM_APPLICATION_NAME");
			String secLevel = _properties.getProperty("CSM_DEFAULT_SECURITY_LEVEL");
			String sessionTimeout = _properties.getProperty("CSM_DEFAULT_SESSION_TIMEOUT");
			String authDisabled = _properties.getProperty("CSM_AUTHENTICATION_DISABLED");
			
			if(secLevel!=null)
			{
				try
				{
					securityLevel=new Integer(secLevel);
				}
				catch(NumberFormatException e)
				{
					securityLevel=new Integer(0);
				}
			}
			else
			{
				securityLevel=new Integer(0);
			}
			
			if(sessionTimeout!=null)
			{
				try
				{
					securitySessionTimeout=new Long(sessionTimeout);
				}
				catch(NumberFormatException e)
				{
					securitySessionTimeout=new Long(60000);
				}
			}
			else
			{
				securitySessionTimeout=new Long(60000);
			}
			
			if(authDisabled!=null && "yes".equalsIgnoreCase(authDisabled))
				authenticationDisabled=true;
			else
				authenticationDisabled=false;
			
		}
		catch(Exception e)
		{
			log.error("Error while reading the CORESystem.properties file.");
			securitySessionTimeout=new Long(60000);
			applicationName="";
			securityLevel=new Integer(0);
			authenticationDisabled=false;
		}
		
	}
	
	
	private SecurityConfiguration(){}
	
	public static String getApplicationName()
	{
		return applicationName;
	}

	public static Integer getSecurityLevel()
	{
		return securityLevel;
	}

	public static Long getSecuritySessionTimeout()
	{
		return securitySessionTimeout;
	}

	public static boolean isAuthenticationDisabled()
	{
		return authenticationDisabled;
	}
	
	
}