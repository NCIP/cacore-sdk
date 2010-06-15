package gov.nih.nci.system.comm.client;

import java.util.Properties;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.AuthenticationException;
import gov.nih.nci.system.comm.common.ApplicationServiceProxy;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientSession
{
	private static Logger log= Logger.getLogger(ClientSession.class.getName());	
	private String sessionKey;
	private ApplicationServiceProxy applicationServiceProxy;
    private static ThreadLocal threadInstance= new ThreadLocal() {
        protected synchronized Object initialValue() {
            return null;
        }
   	};

   	private static ClientSession localInstance;

	private static String SECURITY_POLICY_THREAD="1";
	private static String SECURITY_POLICY_INSTNACE="2";
	private static String securityPolicy;

	static{
		loadSecurityPolicy();
	}
	
    
    static{
    }
	 
     private static ClientSession getCSInstance() {
     	if(SECURITY_POLICY_THREAD.equals(securityPolicy))
     		return ((ClientSession) threadInstance.get());
     	else
     		return localInstance;
     }

 	private static void setCSInstance(ClientSession cs) {
     	if(SECURITY_POLICY_THREAD.equals(securityPolicy))
     		threadInstance.set(cs);
     	else
     		localInstance = cs;
    }
     
     
     private static void loadSecurityPolicy()
     {
    	 try
    	 {
	    	 Properties _properties = new Properties();
	    	 _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));
	    	 String secPolicy = _properties.getProperty("SECURITY_POLICY");
	    	 if(secPolicy!=null)
	    	 {
	    		if((SECURITY_POLICY_THREAD).equals(secPolicy))
	    		{
	    			securityPolicy=SECURITY_POLICY_THREAD;
	    			log.info("Thread level security policy defined in CORESystem.properties file. Switching security policy to Thread based");
	    		}
	    		else if((SECURITY_POLICY_INSTNACE).equals(secPolicy))
	    		{
	    			securityPolicy=SECURITY_POLICY_INSTNACE;
	    			log.info("JVM/Classloader level security policy defined in CORESystem.properties file. Switching security policy to JVM/Classloader");
	    		}
	    		else
	    		{
	    			securityPolicy=SECURITY_POLICY_INSTNACE;
	    			log.info("Unknown security policy defined in CORESystem.properties file. Keeping security policy to default (JVM/Classloader)");
	    		}
	    	 }
	    	 else
	    	 {
	    		 securityPolicy=SECURITY_POLICY_INSTNACE;
	    		 log.info("No security policy defined in CORESystem.properties file. Keeping security policy to default (JVM/Classloader)");
	    	 }
    	 }
    	 catch(Exception e)
    	 {
    		 securityPolicy=SECURITY_POLICY_INSTNACE;
    		 log.error("Error while reading the CORESystem.properties file. Keeping security policy to default (JVM/Classloader)");
    	 }
	}



	public static synchronized ClientSession getInstance()
	{
		ClientSession instance = getCSInstance();
		
		if (instance == null)
		{
			instance = new ClientSession();
			setCSInstance(instance);
		}

		return instance;
	}

	public static synchronized ClientSession getInstance(String securityKey)
	{
		ClientSession instance = getInstance();
		instance.setSessionKey(securityKey);

		return instance;
	}
	
	
	public static synchronized ClientSession getInstance(ApplicationServiceProxy applicationServiceProxy)
	{
		ClientSession instance = getCSInstance();
		if (instance == null)
		{
			instance = new ClientSession(applicationServiceProxy);
			setCSInstance(instance);
		}

		return instance;
	}

	private ClientSession()
	{
		if (null == applicationServiceProxy)
		{
			applicationServiceProxy = getApplicationServiceProxyFromClassPath();
		}
	}
	
	private ClientSession(ApplicationServiceProxy applicationServiceProxy)
	{
		if (null == this.applicationServiceProxy)
		{
			this.applicationServiceProxy = applicationServiceProxy;
		}
	}
	
	private ApplicationServiceProxy getApplicationServiceProxyFromClassPath()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(Constant.REMOTE_SERVICE_FILE_NAME);
		ApplicationServiceProxy applicationServiceProxy = (ApplicationServiceProxy) applicationContext.getBean(Constant.REMOTE_APPLICATION_SERVICE);
		return applicationServiceProxy;
	}

	
	public boolean startSession(String userId, String password) throws ApplicationException
	{
		boolean authenticated = false;
		String sessionKey_from_server = null;
		try
		{
			sessionKey_from_server = applicationServiceProxy.authenticate(userId, password);
			if (sessionKey_from_server != null)
			{
				authenticated = true;
				sessionKey = sessionKey_from_server;
			}
			else
			{
				authenticated = false;
				sessionKey = sessionKey_from_server;
			}
		}
		catch (Exception ex)
		{
			authenticated = false;
			sessionKey = null;
			throw new AuthenticationException("Error in authenticating user credentials");
		}
		return authenticated;
	}

	public void terminateSession()
	{
		applicationServiceProxy.logOut(sessionKey);
		this.sessionKey=null;
	}

	public String getSessionKey()
	{
		return sessionKey;
	}

	private void setSessionKey(String sessionKey)
	{
		this.sessionKey=sessionKey;
	}
	
}