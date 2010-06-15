package gov.nih.nci.system.comm.common;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface ApplicationServiceProxy
{

	public abstract String authenticate(String userId, String password) throws ApplicationException;

	public abstract void logOut(String sessionKey);

	public abstract void setSearchCaseSensitivity(ClientInfo clientInfo);

	public abstract int getQueryRowCount(ClientInfo clientInfo, Object criteria, String targetClassName) throws ApplicationException;

	public abstract void setRecordsCount (ClientInfo clientInfo);

	public abstract List search(ClientInfo clientInfo, String path, List objList) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, String path, Object obj) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, Class targetClass, List objList) throws ApplicationException;

	public abstract List search(ClientInfo clientInfo, Class targetClass, Object obj) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException;

	public abstract List query(ClientInfo clientInfo, CQLQuery cqlQuery, String targetClassName) throws ApplicationException;
	
	/*@WRITABLE_API_START@*/
	public abstract Object createObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract Object updateObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract void removeObject(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
	/*@WRITABLE_API_START@*/
	public abstract List getObjects(ClientInfo clientInfo, Object domainobject) throws ApplicationException;
	/*@WRITABLE_API_END@*/
	
}