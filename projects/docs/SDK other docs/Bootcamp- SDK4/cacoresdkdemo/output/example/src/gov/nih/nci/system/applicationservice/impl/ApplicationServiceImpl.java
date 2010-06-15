package gov.nih.nci.system.applicationservice.impl;

import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.AuthorizationException;
import gov.nih.nci.system.dao.WritableDAO;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ApplicationServiceImpl extends ApplicationService
{
	private static Logger log = Logger.getLogger(ApplicationServiceImpl.class.getName());
	private ApplicationServiceBusinessImpl applicationServiceBusinessImpl = null;
	private WritableDAO writableDAO = null;
	private SecurityEnabler securityEnabler = null;

	
	/**
	 * Default Constructor. It obtains appropriate implementation of the
	 * {@link ApplicationService}interface and caches it. It also instantiates
	 * the instance of writableDAO and caches it.
	 */
	public ApplicationServiceImpl()
	{
		this.applicationServiceBusinessImpl = ApplicationServiceBusinessImpl.getLocalInstance();
		this.writableDAO = new WritableDAO();
		this.securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getBeanInstance()
	 */
	//@Override
	protected ApplicationService getBeanInstance()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getBeanInstance(java.lang.String)
	 */
	//@Override
	protected ApplicationService getBeanInstance(String URL)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setRecordsCount(int)
	 */
	//@Override
	public void setRecordsCount(int recordsCount) throws ApplicationException
	{
		try
		{
			this.applicationServiceBusinessImpl.setRecordsCount(recordsCount);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#setSearchCaseSensitivity(boolean)
	 */
	public void setSearchCaseSensitivity(boolean caseSensitivity)
	{
		this.applicationServiceBusinessImpl.setSearchCaseSensitivity(caseSensitivity);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getQueryRowCount(java.lang.Object,
	 *      java.lang.String)
	 */
	public int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (!securityEnabler.hasAuthorization(targetClassName, "READ"))
				throw new AuthorizationException("User does not have privilege to perform a READ on " + targetClassName+ " object");
		}
		try
		{
			return this.applicationServiceBusinessImpl.getQueryRowCount(criteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      java.lang.String)
	 */
	public List query(DetachedCriteria detachedcriteria, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(detachedcriteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      java.lang.String)
	 */
	public List query(HQLCriteria hqlcriteria, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(hqlcriteria, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}	

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(gov.nih.nci.query.cql.CQLQuery, java.lang.String)
	 */
	public List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException
	{
		List list = null;	
		try
		{
			list = applicationServiceBusinessImpl.query(cqlQuery, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}		
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#query(java.lang.Object,
	 *      int, int, java.lang.String)
	 */
	public List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException
	{
		List list = null;
		try
		{
			list = this.applicationServiceBusinessImpl.query(criteria, firstRow, resultsPerQuery, targetClassName);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
		if (securityEnabler.getSecurityLevel() > 0)
		{
			if (list.size() != 0)
				targetClassName.concat(Constant.COMMA + list.get(0).getClass().getName());
			StringTokenizer tokenPath = new StringTokenizer(targetClassName, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		return list;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class,
	 *      java.lang.Object)
	 */
	public List search(Class targetClass, Object obj) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = targetClass.getName();
			if (obj != null)
				newPath = newPath.concat(Constant.COMMA + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(targetClass, obj);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.Class,
	 *      java.util.List)
	 */
	public List search(Class targetClass, List objList) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = targetClass.getName();
			if (objList.size() != 0)
				newPath = newPath.concat(Constant.COMMA + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}		
		try
		{
			return this.applicationServiceBusinessImpl.search(targetClass, objList);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String,
	 *      java.lang.Object)
	 */
	public List search(String path, Object obj) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = path;
			if (obj != null)
				newPath = newPath.concat(Constant.COMMA + obj.getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(path, obj);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#search(java.lang.String,
	 *      java.util.List)
	 */
	public List search(String path, List objList) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String newPath = path;
			if (objList.size() != 0)
				newPath = newPath.concat(Constant.COMMA + objList.get(0).getClass().getName());
			newPath = newPath.replaceAll("Impl","");
			newPath = newPath.replaceAll("impl.","");
			StringTokenizer tokenPath = new StringTokenizer(newPath, ",");
			while (tokenPath.hasMoreTokens())
			{
				String domainObjectName =  tokenPath.nextToken().trim();
				if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
					throw new AuthorizationException("User does not have privilege to perform a READ on " + domainObjectName+ " object");
			}
		}
		try
		{
			return this.applicationServiceBusinessImpl.search(path, objList);
		}
		catch (Exception e)
		{
			log.error("Exception: ", e);
			throw new ApplicationException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#createObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object createObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "CREATE"))
			{
				throw new AuthorizationException("User does not have privilege to CREATE " + domainObjectName + " object");
			}
		}
		return writableDAO.createObject(domainobject);
	}
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#updateObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public Object updateObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "UPDATE"))
			{
				throw new AuthorizationException("User does not have privilege to UPDATE " + domainObjectName + " object");
			}
		}
		return writableDAO.updateObject(domainobject);
	}
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#removeObject(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public void removeObject(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "DELETE"))
			{
				throw new ApplicationException("User does not have privilege to DELETE " + domainObjectName + " object");
			}
		}
		writableDAO.removeObject(domainobject);
	}
	*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.system.applicationservice.ApplicationService#getObjects(java.lang.Object)
	 */
	/*
	// NOTE: Use only "//" for comments in the following method
	public List getObjects(Object domainobject) throws ApplicationException
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
			String domainObjectName = domainobject.getClass().getName();
			if (!securityEnabler.hasAuthorization(domainObjectName, "READ"))
			{
				throw new ApplicationException("User does not have privilege to READ " + domainObjectName + " object");
			}
		}
		return writableDAO.getObjects(domainobject);
	}
	*/

}
