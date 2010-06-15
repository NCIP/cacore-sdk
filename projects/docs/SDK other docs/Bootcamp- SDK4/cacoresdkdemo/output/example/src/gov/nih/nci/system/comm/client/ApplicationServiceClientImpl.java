package gov.nih.nci.system.comm.client;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.comm.common.ApplicationServiceProxy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.InputStreamResource;

public class ApplicationServiceClientImpl extends ApplicationService
{

	private static ApplicationServiceProxy	applicationServiceProxy;
	private static ApplicationService applicationService;
	private static int recordsCount;
	private static int maxRecordsCount;
	private static boolean caseSensitivity;	
	
	private static Logger log= Logger.getLogger(ApplicationServiceClientImpl.class.getName());	

	/**
	 * Default Constructor. Obtains the Remote instance of {@link 
	 * ApplicationService} and caches it.
	 */
	public ApplicationServiceClientImpl()
	{
		try
		{
			Properties _properties = new Properties();
			_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));
			String rsPerQuery = _properties.getProperty("RECORDSPERQUERY");
			String maxRsPerQuery = _properties.getProperty("MAXRECORDSPERQUERY");
			
			if (rsPerQuery != null)
			{
				log.info("RECORDSPERQUERY property found : "+rsPerQuery);
				recordsCount = new Integer(rsPerQuery).intValue();
			}
			else
			{
				log.error("RECORDSPERQUERY property not found. Using default");
				recordsCount = Constant.RESULT_COUNT_PER_QUERY;
			}
			if (maxRsPerQuery != null)
			{
				log.info("MAXRECORDSPERQUERY property found : "+maxRsPerQuery);
				maxRecordsCount = new Integer(maxRsPerQuery).intValue();
			}
			else
			{
				log.error("MAXRECORDSPERQUERY property not found. Using default");
				maxRecordsCount = Constant.MAX_RESULT_COUNT_PER_QUERY;
			}
		}
		catch (IOException e)
		{
			log.error("IOException: ", e);
		}
		catch (Exception ex)
		{
			log.error("Exception: ", ex);
		}
	}
	
	//@Override
	protected ApplicationService getBeanInstance()
	{
		applicationServiceProxy = getRemoteServiceFromClassPath();
		applicationService = new ApplicationServiceClientImpl();
		return applicationService;
	}

	//@Override
	protected ApplicationService getBeanInstance(String URL)
	{
		applicationServiceProxy = getRemoteServiceFromPath(URL);
		ClientSession.getInstance(applicationServiceProxy);
		return new ApplicationServiceClientImpl();
	}
	
	private static ApplicationServiceProxy getRemoteServiceFromPath(String URL)
	{
		String xmlFileString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\"><beans><bean id=\"remoteService\" class=\"org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean\"><property name=\"serviceUrl\"><value>" + URL + "</value></property><property name=\"serviceInterface\"><value>gov.nih.nci.system.comm.common.ApplicationServiceProxy</value></property></bean></beans>";
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		InputStream inputStream = new ByteArrayInputStream(xmlFileString.getBytes());
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		xmlReader.loadBeanDefinitions(inputStreamResource);
		ctx.refresh();
		ApplicationServiceProxy applicationServiceProxy = (ApplicationServiceProxy) ctx.getBean(Constant.REMOTE_APPLICATION_SERVICE);
		return applicationServiceProxy;
	}
	
	private static ApplicationServiceProxy getRemoteServiceFromClassPath()
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.REMOTE_SERVICE_FILE_NAME);
		ApplicationServiceProxy applicationServiceProxy = (ApplicationServiceProxy) ctx.getBean(Constant.REMOTE_APPLICATION_SERVICE);
		return applicationServiceProxy;
	}
	
	private ClientInfo getClientInfo()
	{
		ClientSession cs = ClientSession.getInstance();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setUserName(cs.getSessionKey());
		clientInfo.setRecordsCount(ApplicationServiceClientImpl.recordsCount);
		clientInfo.setCaseSensitivity(ApplicationServiceClientImpl.caseSensitivity);
		return clientInfo;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setRecordsCount(int)
	 */
	//@Override
	public void setRecordsCount(int recordsCount) throws ApplicationException
	{
		if (recordsCount > maxRecordsCount)
			throw new ApplicationException("Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = " + recordsCount + " MAXRECORDSPERQUERY = " + maxRecordsCount);
		else
			ApplicationServiceClientImpl.recordsCount = recordsCount;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setSearchCaseSensitivity(boolean)
	 */
	public void setSearchCaseSensitivity(boolean caseSensitivity)
	{
		ApplicationServiceClientImpl.caseSensitivity = caseSensitivity;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String, java.util.List)
	 */
	public List search(String path, List objList) throws ApplicationException
	{
		return applicationServiceProxy.search(getClientInfo(), path, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String, java.lang.Object)
	 */
	public List search(String path, Object obj) throws ApplicationException
	{
		return applicationServiceProxy.search(getClientInfo(), path, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class, java.util.List)
	 */
	public List search(Class targetClass, List objList) throws ApplicationException
	{
		return applicationServiceProxy.search(getClientInfo(), targetClass, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class, java.lang.Object)
	 */
	public List search(Class targetClass, Object obj) throws ApplicationException
	{
		return applicationServiceProxy.search(getClientInfo(), targetClass, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object, int, int, java.lang.String)
	 */
	public List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		return applicationServiceProxy.query(getClientInfo(), criteria, firstRow, resultsPerQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(org.hibernate.criterion.DetachedCriteria, java.lang.String)
	 */
	public List query(DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException
	{
		return applicationServiceProxy.query(getClientInfo(), detachedCriteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(gov.nih.nci.common.util.HQLCriteria, java.lang.String)
	 */
	public List query(HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException
	{
		return applicationServiceProxy.query(getClientInfo(), hqlCriteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(gov.nih.nci.query.cql.CQLQuery, java.lang.String)
	 */
	public List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException
	{
		return applicationServiceProxy.query(getClientInfo(), cqlQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getQueryRowCount(java.lang.Object, java.lang.String)
	 */
	public int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException
	{
		return applicationServiceProxy.getQueryRowCount(getClientInfo(), criteria, targetClassName);
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getObjects(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(Object object) throws ApplicationException
	{
		return applicationServiceProxy.getObjects(getClientInfo(), object);
	}
	*/
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#createObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(Object object) throws ApplicationException
	{
		return applicationServiceProxy.createObject(getClientInfo(), object);
	}
	*/
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#updateObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(Object object) throws ApplicationException
	{
		return applicationServiceProxy.updateObject(getClientInfo(), object);
	}
	*/
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#removeObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(Object object) throws ApplicationException
	{
		applicationServiceProxy.removeObject(getClientInfo(), object);
	}
	*/
	
}