package gov.nih.nci.system.applicationservice;

import java.util.Properties;

import org.apache.log4j.Logger;



/**
 * This class acts as a Factory for the creating an appropriate instance of the 
 * {@link ApplicationService} Interface and return it to the client.
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class ApplicationServiceProvider
{

	private static Logger log= Logger.getLogger(ApplicationServiceProvider.class.getName());
	private static ApplicationService applicationService;
	private static boolean isThickClient;
	
    static
    {
    	try
    	{
    		Properties _properties = new Properties();
    		_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));
    		String clientType = _properties.getProperty("CLIENT_TYPE");
    		if(clientType!=null)
    		{
    			if(("Thick").equals(clientType))
	    		{
    				isThickClient=true;
	    			log.info("Using Thick Client mode");
	    		}
	    		else
	    		{
	    			isThickClient=false;
	    		}
	    	 }
	    	 else
	    	 {
	    		 isThickClient=false;
	    	 }
    	}
    	catch(Exception e)
    	{
    		isThickClient=false;
    		log.error("Error while reading the CORESystem.properties file.");
    	}
    }	
	
	
	/**
	 * Default constructor. Does nothing
	 */	
	public ApplicationServiceProvider()
	{
	}

	/**
	 * This methods returns appropriate instance of the {@link ApplicationService}
	 * depending upon from where the client is trying to access it. If the client is
	 * a remote instance then it instantiate approriate remote implementation of the
	 * {@link ApplicationService} interface and chache it. It the methods detects that
	 * the client is local then it instantiates a local implementation of the (@link 
	 * ApplicationService) interface and chache it. If it is unable to instantiate
	 * either the appropriate implementation then it returns a <code>NULL</code>
	 * @return The appropriate instance of 
	 */
	public synchronized static ApplicationService getApplicationService()
	{
		if (applicationService == null)
		{
			if(isThickClient)
			{
				applicationService = getRemoteInstance();
			}
			else
			{
				applicationService = getLocalInstance();
				if (applicationService == null)
					applicationService = getRemoteInstance();
			}
		}
		return applicationService;
	}
	
	public synchronized static ApplicationService getLocalInstance()
	{
		if (applicationService == null)
		{
			try 
			{
				Class applicationServiceImplClass = Class.forName("gov.nih.nci.system.applicationservice.impl.ApplicationServiceImpl");
				try 
				{
					applicationService = (ApplicationService)applicationServiceImplClass.newInstance();
				} 
				catch (InstantiationException e1) 
				{
					log.error("Instantiation Error in Initailizing the Local Implementation of Application Service");
				} 
				catch (IllegalAccessException e1)
				{
					log.error("Security Error in Initailizing the Local Implementation of Application Service");
				}
			} 
			catch (ClassNotFoundException e)
			{
				log.error("Fatal Error in Initailizing Either Client or Server Implementation of Application Service");
			}
		}
		return applicationService;
	}
	
	public synchronized static ApplicationService getRemoteInstance()
	{
		if (applicationService == null)
		{
			try 
			{
				Class applicationServiceClientImplClass = Class.forName("gov.nih.nci.system.comm.client.ApplicationServiceClientImpl");
				try 
				{
					applicationService = (ApplicationService)applicationServiceClientImplClass.newInstance();
					applicationService = applicationService.getBeanInstance();
				}
				catch (InstantiationException e1) 
				{
					log.error("Instantiation Error in Initailizing the Remote Implementation of Application Service");
				}
				catch (IllegalAccessException e1) 
				{
					log.error("Security Error in Initailizing the Remote Implementation of Application Service");
				}				
			}
			catch (ClassNotFoundException e1)
			{
				log.error("Fatal Error in Initailizing Either Client or Server Implementation of Application Service");
			}
		}
		return applicationService;
	}

	public synchronized static ApplicationService getRemoteInstance(String URL)
	{
		//if (applicationService == null) Commented this line because user specifically is asking for a new instance. But this will still not work in multi threaded environment
		{
			try 
			{
				Class applicationServiceClientImplClass = Class.forName("gov.nih.nci.system.comm.client.ApplicationServiceClientImpl");
				try 
				{
					applicationService = (ApplicationService)applicationServiceClientImplClass.newInstance();
					applicationService = applicationService.getBeanInstance(URL);
				} 
				catch (InstantiationException e1) 
				{
					log.error("Instantiation Error in Initailizing the Remote Implementation of Application Service");
				}
				catch (IllegalAccessException e1) 
				{
					log.error("Security Error in Initailizing the Remote Implementation of Application Service");
				}				
			} 
			catch (ClassNotFoundException e1)
			{
				log.error("Fatal Error in Initailizing Either Client or Server Implementation of Application Service");
			}
		}
		return applicationService;
	}
}
