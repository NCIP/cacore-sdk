package gov.nih.nci.system.comm.server;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.comm.common.ApplicationServiceProxy;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationServiceServerImpl implements ApplicationServiceProxy
{

	private ApplicationService applicationService;
	private SecurityEnabler securityEnabler;

	/**
	 * Default Constructor it takes in 
	 */
	public ApplicationServiceServerImpl()
	{
		securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());
		ApplicationContext ctx = new ClassPathXmlApplicationContext(Constant.APPLICATION_SERVICE_FILE_NAME);
		applicationService = (ApplicationService) ctx.getBean(Constant.APPLICATION_SERVICE);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#authenticate(java.lang.String, java.lang.String)
	 */
	public String authenticate(String userId, String password) throws ApplicationException
	{
		return securityEnabler.authenticate(userId, password);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#logOut(java.lang.String)
	 */
	public void logOut(String sessionKey)
	{
		securityEnabler.logOut(sessionKey);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#setSearchCaseSensitivity(java.lang.String, boolean)
	 */
	public void setSearchCaseSensitivity(ClientInfo clientInfo)
	{
		// do nothing
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#setSearchCaseSensitivity(java.lang.String, boolean)
	 */
	public void setRecordsCount(ClientInfo clientInfo)
	{
		// do nothing
	}	

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.String, java.util.List)
	 */
	public List search(ClientInfo clientInfo, String path, List objList) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(path, objList);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.String, java.lang.Object)
	 */
	public List search(ClientInfo clientInfo, String path, Object obj) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);		
		return applicationService.search(path, obj);

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.Class, java.util.List)
	 */
	public List search(ClientInfo clientInfo, Class targetClass, List objList) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(targetClass, objList);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#search(gov.nih.nci.common.util.ClientInfo, java.lang.Class, java.lang.Object)
	 */
	public List search(ClientInfo clientInfo, Class targetClass, Object obj) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.search(targetClass, obj);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, java.lang.Object, int, int, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(criteria, firstRow, resultsPerQuery, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, org.hibernate.criterion.DetachedCriteria, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(detachedCriteria, targetClassName);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, gov.nih.nci.common.util.HQLCriteria, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(hqlCriteria, targetClassName);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#query(gov.nih.nci.common.util.ClientInfo, gov.nih.nci.query.cql.CQLQuery, java.lang.String)
	 */
	public List query(ClientInfo clientInfo, CQLQuery cqlQuery, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.query(cqlQuery, targetClassName);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getQueryRowCount(gov.nih.nci.common.util.ClientInfo, java.lang.Object, java.lang.String)
	 */
	public int getQueryRowCount(ClientInfo clientInfo, Object criteria, String targetClassName) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.getQueryRowCount(criteria, targetClassName);
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#createObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */	
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.createObject(domainobject);
	}
	
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#updateObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.updateObject(domainobject);
	}
	
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#removeObject(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		applicationService.removeObject(domainobject);
	}
	*/

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.comm.common.ApplicationServiceProxy#getObjects(gov.nih.nci.common.util.ClientInfo, java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(ClientInfo clientInfo, Object domainobject) throws ApplicationException
	{
		ClientInfoThreadVariable.setClientInfo(clientInfo);
		return applicationService.getObjects(domainobject);
	}
	*/

}