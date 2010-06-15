/*
 * Created on Jul 15, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.applicationservice;

import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class ApplicationService
{
	
	public static ApplicationService getRemoteInstance()
	{
		return ApplicationServiceProvider.getRemoteInstance();
	}
	
	public static ApplicationService getRemoteInstance(String URL)
	{
		return ApplicationServiceProvider.getRemoteInstance(URL);
	}
	
	public static ApplicationService getLocalInstance()
	{
		return ApplicationServiceProvider.getLocalInstance();
	}

	public static ApplicationService getInstance()
	{
		return ApplicationServiceProvider.getApplicationService();
	}
	
	protected abstract ApplicationService getBeanInstance();
	
	protected abstract ApplicationService getBeanInstance(String URL);
	
	public abstract void setSearchCaseSensitivity(boolean caseSensitivity);

	public abstract void setRecordsCount(int recordsCount) throws ApplicationException;
	
	public abstract int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException;

	public abstract List query(DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException;
	
	public abstract List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;

	public abstract List search(Class targetClass, Object obj) throws ApplicationException;

	public abstract List search(Class targetClass, List objList) throws ApplicationException;

	public abstract List search(String path, Object obj) throws ApplicationException;

	public abstract List search(String path, List objList) throws ApplicationException;

	/*@WRITABLE_API_START@*/
	public abstract Object createObject(Object object) throws ApplicationException;
	/*@WRITABLE_API_END@*/

	/*@WRITABLE_API_START@*/
	public abstract Object updateObject(Object object) throws ApplicationException;
	/*@WRITABLE_API_END@*/

	/*@WRITABLE_API_START@*/
	public abstract void removeObject(Object object) throws ApplicationException;
	/*@WRITABLE_API_END@*/

	/*@WRITABLE_API_START@*/
	public abstract List getObjects(Object object) throws ApplicationException;
	/*@WRITABLE_API_END@*/

}