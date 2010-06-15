package gov.nih.nci.system.applicationservice.impl;

import gov.nih.nci.common.net.Request;
import gov.nih.nci.common.net.Response;
import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.common.util.ListProxy;
import gov.nih.nci.common.util.NestedCriteria;
import gov.nih.nci.common.util.ObjectFactory;
import gov.nih.nci.common.util.Path2NestedCriteria;
import gov.nih.nci.common.util.PrintUtils;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.dao.DAO;
import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.system.dao.QueryException;
import gov.nih.nci.system.servicelocator.ServiceLocator;
import gov.nih.nci.system.servicelocator.ServiceLocatorException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;

/**
 * <!-- LICENSE_TEXT_START -->
 * Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
 * and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
 * in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 * 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
 * "This product includes software developed by the SAIC and the National Cancer Institute."
 * If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
 * wherever such third-party acknowledgments normally appear.
 * 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
 * 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
 * the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
 * 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
 * SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * ApplicationService presents various methods to query a HTTP server.
 * 
 * @author caBIO Team
 * @version 1.0
 */

public class ApplicationServiceBusinessImpl {

	private static String httpAddress;

	private static ApplicationServiceBusinessImpl applicationService = new ApplicationServiceBusinessImpl();

	private static int firstRow;

	private static int maxRecordsCount;

	private static int recordsCount;

	private boolean inputImplFlag;

	private static Logger log = Logger.getLogger(ApplicationServiceBusinessImpl.class.getName());

	private boolean caseSensitivityFlag; // by default it is case

	/**
	 * Creates a new ApplicationService instance with the HTTP server address
	 * 
	 * @param httpURL -
	 *            Specifies the http address for the server
	 * @return application service instance
	 */
	public static ApplicationServiceBusinessImpl getRemoteInstance(String httpURL) {
		httpAddress = httpURL;
		loadProperties();
		return applicationService;

	}

	/**
	 * 
	 * @return application service local instance
	 */
	public static ApplicationServiceBusinessImpl getLocalInstance() {
		httpAddress = null;
		loadProperties();
		return applicationService;

	}

	/**
	 * @return ApplicationService instance
	 */
	public static ApplicationServiceBusinessImpl getApplicationService() {
		return getLocalInstance();

	}

	/**
	 * @return ApplicationService instance
	 */
	public static ApplicationServiceBusinessImpl getInstance() {
		return applicationService;
	}

	/**
	 * @param i
	 * @throws ApplicationException
	 */
	public void setRecordsCount(int i) throws ApplicationException {
		if (i > maxRecordsCount) {
			log.error("Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ i + " MAXRECORDSPERQUERY = " + maxRecordsCount);

			throw new QueryException(
					"Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ i + " MAXRECORDSPERQUERY = " + maxRecordsCount);

		} else
			recordsCount = i;
	}

	private static void loadProperties() {

		try {
			Properties _properties = new Properties();

			_properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("CORESystem.properties"));

			String rsPerQuery = _properties.getProperty("RECORDSPERQUERY");
			String maxRsPerQuery = _properties.getProperty("MAXRECORDSPERQUERY");
			if (rsPerQuery != null) {
				recordsCount = new Integer(rsPerQuery).intValue();
			} else {
				recordsCount = Constant.RESULT_COUNT_PER_QUERY;
			}

			if (maxRsPerQuery != null) {
				maxRecordsCount = new Integer(maxRsPerQuery).intValue();

			} else {
				maxRecordsCount = Constant.MAX_RESULT_COUNT_PER_QUERY;
			}

		} catch (IOException e) {
			log.error("IOException: ", e);
		} catch (Exception ex) {
			log.error("Exception: ", ex);
		}
	}

	/**
	 * @param criteria
	 * @return total count for the query
	 * @throws ApplicationException
	 * 
	 */
	public int getQueryRowCount(Object criteria, String targetClassName) throws ApplicationException {
		Integer count = null;
		Response response = new Response();
		Request request = new Request(criteria);
		request.setIsCount(Boolean.TRUE);
		request.setDomainObjectName(targetClassName);

		response = query(request);
		count = response.getRowCount();

		if (count != null)
			return count.intValue();
		else
			return 0;
	}

	public List query(DetachedCriteria detachedCriteria, String targetClassName) throws ApplicationException {
		return privateQuery((Object) detachedCriteria, targetClassName);
	}

	private List query(NestedCriteria nestedCriteria, String targetClassName) throws ApplicationException {
		return privateQuery((Object) nestedCriteria, targetClassName);
	}

	public List query(HQLCriteria hqlCriteria, String targetClassName) throws ApplicationException {
		return privateQuery((Object) hqlCriteria, targetClassName);
	}

	public List query(CQLQuery cqlQuery, String targetClassName) throws ApplicationException {
		return privateQuery((Object) cqlQuery, targetClassName);
	}
	/**
	 * Gets the result list for the specified Hibernate Criteria from the
	 * HTTPClient.
	 * 
	 * @param criteria
	 *            Specified Hibernate criteria
	 * @return gets the result list
	 * @throws ApplicationException
	 */

	// public List query(Object criteria, String targetClassName) throws
	// Exception{
	private List privateQuery(Object criteria, String targetClassName) throws ApplicationException {

		List results = null;
		List resultList = new ListProxy();
		Response response = new Response();
		Request request = new Request(criteria);
		request.setIsCount(Boolean.FALSE);
		request.setFirstRow(new Integer(firstRow));

		int localRecordsCount = recordsCount;
		if (ClientInfoThreadVariable.getRecordsCount() > 0)
			localRecordsCount = ClientInfoThreadVariable.getRecordsCount();

		if ((maxRecordsCount > 0) && (localRecordsCount > maxRecordsCount)) {
			log.error("Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ localRecordsCount + " MAXRECORDSPERQUERY = " + maxRecordsCount);
			throw new QueryException(
					"Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ localRecordsCount + " MAXRECORDSPERQUERY = " + maxRecordsCount);
		} else if (localRecordsCount <= 0) {
			request.setRecordsCount(new Integer(Constant.RESULT_COUNT_PER_QUERY));
			recordsCount = Constant.RESULT_COUNT_PER_QUERY;
		} else if (localRecordsCount > 0) {
			request.setRecordsCount(new Integer(localRecordsCount));
		}
		request.setDomainObjectName(targetClassName);
		
		response = query(request);
		results = (List) response.getResponse();

		resultList.clear();
		// Set the value for ListProxy
		if (results != null) {
			resultList.addAll(results);
		}
		ListProxy myProxy = (ListProxy) resultList;
		myProxy.setOriginalStart(firstRow);
		myProxy.setMaxRecordsPerQuery(localRecordsCount);
		myProxy.setOriginalCriteria(criteria);
		myProxy.setServerAddress(httpAddress);
		myProxy.setTargetClassName(targetClassName);

		return resultList;

	}

	/**
	 * @param criteria
	 * @param firstRow
	 * @param resultsPerQuery
	 * @param targetClassName
	 * @return List
	 * @throws ApplicationException
	 */
	public List query(Object criteria, int firstRow, int resultsPerQuery, String targetClassName) throws ApplicationException {
		List results = null;
		Response response = new Response();
		Request request = new Request(criteria);
		request.setIsCount(Boolean.valueOf(false));
		request.setFirstRow(new Integer(firstRow));

		if (resultsPerQuery > 0 && resultsPerQuery < maxRecordsCount) {
			request.setRecordsCount(new Integer(resultsPerQuery));
		}
		if ((maxRecordsCount > 0) && (resultsPerQuery > maxRecordsCount)) {
			log.error("Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ resultsPerQuery + " MAXRECORDSPERQUERY = " + maxRecordsCount);
			throw new QueryException(
					"Illegal Value for RecordsCount: RECORDSPERQUERY cannot be greater than MAXRECORDSPERQUERY. RECORDSPERQUERY = "
							+ resultsPerQuery + " MAXRECORDSPERQUERY = " + maxRecordsCount);
		}
		request.setDomainObjectName(targetClassName);

		response = query(request);
		results = (List) response.getResponse();

		return results;
	}


	/**
	 * Prints a list of objects on the Standard Output Device
	 * 
	 * @param resultList -
	 *            Specifies the List of objects that needs to be printed
	 */

	public void printResults(List resultList) {
		if (resultList.size() < 1) {
			log.debug("No records found");
		} else {
			PrintUtils printer = new PrintUtils();
			printer.printResults(resultList);
		}
	}


	/**
	 * Prints a list of objects in a tree like structure to the Standard Output
	 * Device
	 * 
	 * @param resultList -
	 *            Specifies the List of objects that needs to be printed
	 */
	public void printTree(List resultList) {
		if (resultList.size() < 1) {
			log.debug("resultList.size < 1");
		} else {
			PrintUtils printer = new PrintUtils();
			printer.printTree(resultList);
		}

	}

	public List search(Class targetClass, Object obj) throws ApplicationException {
		return search(targetClass.getName(), obj);
	}

	public List search(Class targetClass, List objList) throws ApplicationException {
		return search(targetClass.getName(), objList);
	}

	public List search(String path, Object obj) throws ApplicationException {
		List list = new ArrayList();
		list.add(obj);
		return search(path, list);
	}

	public List search(String path, List objList) throws ApplicationException {

		try{
			boolean inputImplFlag = containsImplInTarget(path);
			List pathList = preparePathList(path);
			
			NestedCriteria crit = Path2NestedCriteria.createNestedCriteria(pathList, convertImpl(objList));
			crit.setSearchCaseSensitivity(caseSensitivityFlag);
			List results = query(crit, crit.getTargetObjectName());
			if (inputImplFlag) 
				return convertToImpl(results);
			
			return results;
		}
		catch(Exception e)
		{
			String msg = "Error while executing nested search criteria query";
			log.error(msg,e);
			throw new ApplicationException(msg,e); 
		}
	}
	
	private List preparePathList(String path)
	{
		List pathList = new ArrayList();
		
		StringTokenizer tokens = new StringTokenizer(path, ",");
		while (tokens.hasMoreTokens()) {
			pathList.add(convertPathName(tokens.nextToken().trim()));
		}
		return pathList;
	}
	
	private boolean containsImplInTarget(String path)
	{
		StringTokenizer tokens = new StringTokenizer(path, ",");
		if (tokens.hasMoreTokens()) {
			String target = tokens.nextToken();
			if (target.indexOf(".impl.") > 0) 
				return true;
		}
			return false;
	}

	// Assume the passing name is either Interface or Impl class's name
	public static String getFullQName(String name) throws ApplicationException {
		try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			log.error("ERROR: Class " + name + " does not exist.  Please check the package and class name.",e);
			throw new QueryException("ERROR: Class " + name + " does not exist.  Please check the package and class name.",e);
		}

		// assume it is already a full qualified name if the name contains
		// ".impl." and "Impl"
		if ((name.indexOf(".impl.") > 0) && (name.indexOf("Impl") > 0)) {
			return name;
		} else {
			String full = name.substring(0, name.lastIndexOf(Constant.DOT)) + ".impl."
					+ name.substring(name.lastIndexOf(Constant.DOT) + 1) + "Impl";
			return full;
		}
	}

	public void setSearchCaseSensitivity(boolean caseSensitivity) {
		this.caseSensitivityFlag = caseSensitivity;
	}


	private Object convertImpl(Object implObject) {
		try {
			if(implObject.getClass().getName().indexOf(".impl.") <= 0) return implObject;
			
			Class objKlass = implObject.getClass();

			log.debug("ApplicationService.copyValue: objKlass.getName = " + objKlass.getName());
			Class newObjClass = objKlass.getSuperclass();
			log.debug("ApplicationService.copyValue(): new object name = " + newObjClass.getName());

			Object newObject = newObjClass.newInstance();

			// the concrete class 's superclass -- noimpl class
			copyValue(newObject, implObject, objKlass.getSuperclass());
			return newObject;
		} catch (Exception e) {
			log.error("Exception: ", e);
			return null;
		}

	}

	private List convertImpl(List objList) {
		List newList = new ArrayList();
		for (Iterator iter = objList.iterator(); iter.hasNext();) {
			newList.add(convertImpl(iter.next()));
		}
		return newList;
	}

	private Object copyValue(Object newObject, Object obj, Class objKlass) {
		try {
			Field[] newObjFields = objKlass.getDeclaredFields();
			for (int i = 0; i < newObjFields.length; i++) {
				Object fieldValue = null;
				Field field = newObjFields[i];
				field.setAccessible(true);
				String fieldName = field.getName();
				if (fieldName.equals("serialVersionUID"))
					continue;

				String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				// orig's getter method
				Method getterMethod = objKlass.getMethod(getterMethodName, (Class[])null);
				// new object setter method
				Method setterMethod = newObject.getClass().getMethod(setterMethodName,
						new Class[] { getterMethod.getReturnType() });

				// if (field.getType().getName().indexOf("gov.nih.nci") > -1)
				if (!field.getType().isPrimitive() && !field.getType().getName().startsWith("java.")) {
					fieldValue = field.get(obj);
					if (fieldValue != null) {
						if (fieldValue.getClass().getName().indexOf(".impl.") > -1) {
							fieldValue = convertImpl(fieldValue);
						}
					}
				} else {
					fieldValue = getterMethod.invoke(obj,(Object[])null);
					if (fieldValue instanceof Collection) {
						Collection oldValue = (Collection) fieldValue;
						Collection newValue = new ArrayList();
						for (Iterator iter = oldValue.iterator(); iter.hasNext();) {
							Object element = iter.next();
							if (obj.getClass().getName().indexOf(".impl.") > -1) {
								newValue.add(convertImpl(element));
							} else {
								newValue.add(element);
							}
						}
						fieldValue = newValue;
					}
				}
				log.debug("ApplicationService.copyValue(): fieldname = " + fieldName + " | fieldValue = " + fieldValue);

				setterMethod.invoke(newObject, new Object[] { fieldValue });
			}
			// the superclass
			objKlass = objKlass.getSuperclass();
			while (objKlass != null && !objKlass.equals(Object.class) && !objKlass.isInterface()) {
				copyValue(newObject, obj, objKlass);
				objKlass = objKlass.getSuperclass();
			}

		} catch (Exception e) {
			log.error("ApplicationService.copyValue: ApplicationService Error", e);
			return null;
		}
		return newObject;
	}

	private List convertToImpl(List results) {
		List newList = new ArrayList();
		for (Iterator iter = results.iterator(); iter.hasNext();) {
			Object resultObj = iter.next();
			try {
				Class objKlass = resultObj.getClass();

				log.debug("ApplicationService.copyValue: objKlass.getName = " + objKlass.getName());
				String resultObjName = objKlass.getName();
				log.debug("ApplicationService.convertToImpl(): resultObjName = " + resultObjName);
				String newImplObjName = resultObjName.substring(0, resultObjName.lastIndexOf(Constant.DOT)) + ".impl."
						+ resultObjName.substring(resultObjName.lastIndexOf(Constant.DOT) + 1) + "Impl";
				Class newObjClass = Class.forName(newImplObjName);
				log.debug("ApplicationService.copyValue(): new object name = " + newObjClass.getName());

				Object newObject = newObjClass.newInstance();

				// the concrete class 's superclass -- noimpl class
				copyValueToImpl(newObject, resultObj, objKlass);
				newList.add(newObject);
			} catch (Exception e) {
				log.error("Exception: ", e);
			}
		}
		return newList;
	}

	private Object copyValueToImpl(Object newObject, Object obj, Class objKlass) {
		try {
			Field[] newObjFields = objKlass.getDeclaredFields();
			for (int i = 0; i < newObjFields.length; i++) {
				Object fieldValue = null;
				Field field = newObjFields[i];
				field.setAccessible(true);
				String fieldName = field.getName();
				if (fieldName.equals("serialVersionUID"))
					continue;

				String getterMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				// orig's getter method
				Method getterMethod = objKlass.getMethod(getterMethodName,(Class[])null);
				// new object setter method
				Method setterMethod = newObject.getClass().getMethod(setterMethodName,
						new Class[] { getterMethod.getReturnType() });

				fieldValue = field.get(obj);

				setterMethod.invoke(newObject, new Object[] { fieldValue });
			}
			// the superclass
			objKlass = objKlass.getSuperclass();
			while (objKlass != null && !objKlass.equals(Object.class) && !objKlass.isInterface()) {
				copyValue(newObject, obj, objKlass);
				objKlass = objKlass.getSuperclass();
			}

		} catch (Exception e) {
			log.error("ApplicationService.copyValue: ApplicationService Error", e);
			return null;
		}
		return newObject;
	}

	private String convertPathName(String oldName) {
		log.debug("ApplicationService.convertPathName(): oldName = " + oldName);
		String temp = oldName.replaceAll(".impl.", ".");
		if (temp.endsWith("Impl")) {
			temp = temp.substring(0, temp.length() - 4);
		}
		log.debug("ApplicationService.convertPathName(): temp = " + temp);
		return temp;

	}
	
	private Response query(gov.nih.nci.common.net.Request request) throws ApplicationException
	{
		ServiceLocator serviceLocator = null;
		request.setCaseSensitivity(new Boolean(caseSensitivityFlag));
		try
		{
			String domainObjectName = request.getDomainObjectName();
			serviceLocator = (ServiceLocator)ObjectFactory.getObject("ServiceLocator");
			request.setConfig(serviceLocator.getDataSourceCollection(domainObjectName));
			String dataSource = serviceLocator.getDataSource(domainObjectName);
			DAO dao = (DAO) ObjectFactory.getObject(dataSource);
			return dao.query(request);
		} 
		catch(ServiceLocatorException slEx)
		{
			log.error("No data source found",slEx);
			throw new ApplicationException(" No data source was found " , slEx);
		}
		catch(DAOException daoException)
		{
			log.error("Error while getting and querying DAO",daoException);
			throw daoException;
		}
		catch (ApplicationException e1)
		{
			log.fatal("Unable to locate Service Locator :",e1);
			throw new ApplicationException("Unable to locate Service Locator :",e1);
		}		
		catch(Exception exception)
		{
			log.error("Exception while getting datasource information "+ exception.getMessage());
			throw new ApplicationException("Exception in Base Delegate while getting datasource information: ", exception);
		}
	}
}

// $Log: ApplicationServiceBusinessImpl.java,v $
// Revision 1.16  2006/11/28 20:50:19  satish79
// Defect fix for CQL
//
// Revision 1.15  2006/10/26 22:04:54  modik
// Merging the changes for enabling security for all interfaces.
//
// Revision 1.14  2006/10/26 14:22:37  satish79
// type casted null parameters to avoid any warnings
//
// Revision 1.13  2006/10/19 14:53:10  satish79
// Implemented CQL enhancement
//
// Revision 1.12  2006/10/03 20:11:11  satish79
// Integrated new NestedCriteria2HQL to support Hibernate version 3.1.3
//
// Revision 1.10  2006/09/28 22:02:57  satish79
// Changes for new service locator
//
// Revision 1.9  2006/09/26 14:22:58  satish79
// changes in the log statements
//
// Revision 1.8  2006/09/13 20:26:06  satish79
// Modified exception handling mechanism
//
// Revision 1.7  2006/09/13 05:19:45  ddumitru
// *** empty log message ***
//
// Revision 1.6  2006/09/12 18:35:26  satish79
// Changes made for Removing BaseDelegate and Introducing Spring
//
// Revision 1.5  2006/09/12 00:25:27  ddumitru
// Simplified interaction between Application Service and Persistence layers.
//
// The following files were deleted:
//
// DAOFactory
// ORMDAOFactory
// BaseDelegate
// DelegateException
// InterfaceProxy
//
// The ApplicationServiceBusinessImpl now gets a handle to the appropriate DAO implementation using the Spring Framework.
//
// Revision 1.4  2006/09/11 17:10:30  ddumitru
// Replaced calls to InterfaceProxy (BaseDelegate), which was deleted along with 
// DAO Factory related classes in order to simplify interaction between the Application
// Service and Persistence layers.
//
// Revision 1.4  2006/09/05 23:01:30  ddumitru
// Performance Enhancements:
// 	Removed unnecessary casting.
// 	Removed unnecessary field initialization.
// 	Removed unnecessary object creation.
// 	Used char vs. String where appropriate, as char is more efficient.
//
// Revision 1.3  2006/08/15 07:13:11  ddumitru
// Exception Handling Changes
//
// Revision 1.2  2006/06/14 14:01:18  connellm
// Replaced "replace" with "replaceAll" for JDK 1.4 compatability.
//
// Revision 1.1  2006/05/10 19:26:51  connellm
// Initial check in of code to support the splitting of the SDk from caCORE.
//
// Revision 1.5  2006/03/29 21:18:37  masondo
// Removed HTTPClient
//
// Revision 1.4 2006/02/06 21:18:42 modik
// Modified Code to
// 1. Read the client side CORESystem.Properties File
// 2. Able to set the values from client to server for Records Count and Case
// Sensitivity
//
// Revision 1.3 2006/01/11 21:23:13 zengje
// Changed the hard code searching for "gov.nih.nci" to eliminate the primitive
// type and type starts with "java."
//
// Revision 1.2 2005/12/30 20:47:07 modik
// Commiting after merge
//
// Revision 1.48 2005/12/13 22:11:46 zengje
// Added the convertToImpl() method to avoid possible classcasting error for
// current user.
//
// Revision 1.47 2005/12/13 14:35:28 masondo
// Fixed the ApplicationService intial block. The code was not initializing the
// ApplicationService only in the local block. The reason it worked was the code
// is always executed in the static block
//
// Revision 1.46 2005/12/12 20:32:12 zengje
// 1. Changed query(NestedCriteria, String) to private.
// 2. Remove the hard code upper limit for max record per query
// 3. Convert target name and path to non-impl
// 4. Convert criterion objects to non-impl.
//
// Use non-impl object to search for public search method.
//
// If user used DetachedCriteria and Impl class, it will use the Impl set to do
// the query.
//
// Revision 1.45 2005/11/14 22:11:23 zengje
// Added pulic methods query(DetachedCriteria, String); query(NestedCriteria,
// String), and query(HQLCriteria, String). Changed query(Object, String) to
// private method.
//
// Revision 1.44 2005/10/26 17:15:31 zengje
// Added interface setSearchCaseSensitivity(boolean)
//
// Revision 1.43 2005/10/25 19:47:51 muhsins
// Modified code to compile with jdk 1.5_04
//
// Revision 1.42 2005/07/15 21:05:32 zengje
// Modified the logging/Exception message
//
// Revision 1.41 2005/07/12 17:29:57 lethai
// added logging messages
//
// Revision 1.40 2005/07/08 20:26:38 zengje
// removed unused import statment. Switch ServerSearchutil to SearchUtil
//
// Revision 1.39 2005/07/08 19:46:31 lethai
// modified setRecordsCount to throw exception when the value is greater than
// 5000.
//
// Revision 1.38 2005/07/08 18:55:35 zengje
// Changed hqlSearch to search, and removed the DetachedCriteria stuff.
//
// Revision 1.37 2005/07/07 18:33:15 lethai
// Modified query method to fix bug# 802 (ListProxy.subList)
//
// Revision 1.36 2005/07/05 18:48:52 lethai
// added code to check for maxrecordsperquery cannot be more than 5000
//
// Revision 1.35 2005/06/29 14:23:05 lethai
// added fix for nullpointerexception on rowcount value.
//
// Revision 1.34 2005/06/28 18:00:18 lethai
// comment out debug message
//
// Revision 1.33 2005/06/23 18:23:43 lethai
// removed codes that checks and removes duplicate records. Do not need it
// anymore because Server side code takes care of duplicate records by filtering
// uisng Set.
//
// Revision 1.32 2005/06/17 13:17:45 zengje
// removed some comments
//
// Revision 1.31 2005/06/09 15:46:22 zengje
// Modified to work with List of objects.
//
// Revision 1.30 2005/06/02 15:09:00 zengje
// Added hqlSearch(String, Object) for Nested Criteria/HQL search.
// Replaced DetachedCriteria in getRowCount() with Object.
// There are a lot of debug statement in this version, which will be removed
// later.
//
// Revision 1.29 2005/05/31 18:20:35 lethai
// Removed setHasAllRecords for ListProxy.
//
// Revision 1.28 2005/05/26 15:44:26 muhsins
// Criteria have been changed to Hibernate3 DetachedCriteria
//
// Revision 1.27 2005/05/25 16:44:52 lethai
// Modified to solve large result set problem, bug # 740, 739
//
// Revision 1.25 2005/05/06 21:33:31 shanbhak
// Replaced InterfaceProxy with BaseDelegate class for Local instance to avoid
// classCastException.
//