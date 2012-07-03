package gov.nih.nci.system.web;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.WritableApplicationService;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;
import gov.nih.nci.system.query.SDKQueryResult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.BeanUtils;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.mmbase.util.Encode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class RESTfulResource {

	public static Logger log = Logger.getLogger(HTTPQuery.class.getName());
	public static final String LESS_THAN = "<";
	public static final String GREATER_THAN = ">";
	public static final String NOT_EQUAL = "<>";

	WebApplicationContext ctx;
	private ClassCache classCache;
	ApplicationService applicationService;
	WritableApplicationService writableApplicationService;
	HTTPUtils httpUtils;
	int pageSize;
	private String cacoreStyleSheet;
	private String jsonStyleSheet;
	protected boolean secured = false;

	public RESTfulResource(@Context ServletContext context) {
		try {
			ctx = WebApplicationContextUtils.getWebApplicationContext(context);
			Properties systemProperties = (Properties) ctx
					.getBean("WebSystemProperties");

			cacoreStyleSheet = systemProperties
					.getProperty("resultOutputFormatter");
			jsonStyleSheet = systemProperties
					.getProperty("jsonOutputFormatter");
			classCache = (ClassCache) ctx.getBean("ClassCache");
			writableApplicationService = (WritableApplicationService) ctx
					.getBean("ApplicationServiceImpl");
			try {
				String pageCount = systemProperties.getProperty("rowCounter");
				if (pageCount != null) {
					pageSize = Integer.parseInt(pageCount);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				pageSize = 10000;
			}

			String securityEnabled = (String)systemProperties.getProperty("securityEnabled");
			secured = "yes".equalsIgnoreCase(securityEnabled) || "true".equalsIgnoreCase(securityEnabled);


			HibernateConfigurationHolder configurationHolder = (HibernateConfigurationHolder) ctx
					.getBean("HibernateConfigHolder");
			httpUtils = new HTTPUtils(writableApplicationService, classCache,
					pageSize, configurationHolder);
		} catch (Exception e) {
			log.error("Error in constructing REST resource: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	/**
	 * Get the list of all searchable Fields for the class
	 *
	 * @param className
	 * @return Field[] of all fields for the given class
	 */
	public List<Field> getSearchableFields(String className) {
		Field[] fields = null;
		List<Field> searchableFields = new ArrayList<Field>();

		try {
			Class klass = classCache.getClassFromCache(className);
			log.debug("Retrieved " + className + " from cache");

			fields = classCache.getAllFields(klass);
			String fieldType;

			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				fieldType = fields[i].getType().getName();

				if (classCache.isSearchable(fieldType)) {
					searchableFields.add(fields[i]);
				}

			}
		} catch (ClassNotFoundException e) {
			// Do nothing. Abstract class names have been purposely modified
			// with a " (abstract)" suffix on the UI, and thus are no longer
			// found in the cache
			// This is intentional; abstract classes cannot be used as a target
			// or
			// search criteria object since they cannot be instantiated via
			// Class.forName().newInstance();
			log.debug("Searchable fields not found for class: "
					+ className
					+ ".  This warning can be safely ignored if the class is abstract or an interface.");
		} catch (Exception e) {
			log.error(
					"Exception caught generating a list of searchable fields for class "
							+ className, e);
		}
		return searchableFields;
	}

	protected void validateCriteria(Map matrixParams, List<Field> searchFields)
			throws WebApplicationException {
		if (matrixParams == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>SEARCH_CRITERIA_1</code>");
			buffer.append("<message>Search criteria is missing</message>");
			buffer.append("<valid>");
			for(Field field : searchFields)
			{
				buffer.append("<attribute>");
				buffer.append(field.getName());
				buffer.append("</attribute>");
			}
			buffer.append("</valid>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}

		Iterator iter = matrixParams.keySet().iterator();
		List<String> invalidAttrs = new ArrayList<String>();

		while (iter.hasNext()) {
			boolean found = false;
			String fullName = (String) iter.next();
			String attrName = getAttributeName(fullName);
			for (Field field : searchFields) {
				if (field.getName().equals(attrName)) {
					found = true;
					break;
				}
			}
			if (!found)
				invalidAttrs.add(fullName);
		}

		if (invalidAttrs.size() > 0) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			String attrs = "";
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>SEARCH_CRITERIA_2</code>");
			buffer.append("<message>Invalid Search criteria</message>");
			buffer.append("<valid>");
			for(Field field : searchFields)
			{
				buffer.append("<attribute>");
				buffer.append(field.getName());
				buffer.append("</attribute>");
			}
			buffer.append("</valid>");
			buffer.append("<invalid>");
			for (String attr : invalidAttrs)
			{
				attrs = attr + " " + attrs;
				buffer.append("<attribute>");
				buffer.append(attr);
				buffer.append("</attribute>");

			}
			buffer.append("</invalid>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}
	}

	private String getAttributeName(String name)
	{
		if(name.indexOf(NOT_EQUAL) > 0)
			return name.substring(0, name.indexOf(NOT_EQUAL));
		else if(name.indexOf(GREATER_THAN) > 0)
			return name.substring(0, name.indexOf(GREATER_THAN));
		else if(name.indexOf(LESS_THAN) > 0)
			return name.substring(0, name.indexOf(LESS_THAN));
		else
			return name;
	}

	private String getAttributeValue(String name)
	{
		if(name.indexOf(NOT_EQUAL) >0)
			return name.substring(name.indexOf(NOT_EQUAL)+2, name.length());
		else if(name.indexOf(GREATER_THAN) > 0)
			return name.substring(name.indexOf(GREATER_THAN)+1, name.length());
		else if(name.indexOf(LESS_THAN) > 0)
			return name.substring(name.indexOf(LESS_THAN)+1, name.length());
		else
			return name;
	}

	private String getAttributeOperator(String name)
	{
		if(name.indexOf(NOT_EQUAL) > 0)
			return "<>";
		else if(name.indexOf(GREATER_THAN) > 0)
			return ">";
		else if(name.indexOf(LESS_THAN) > 0)
			return "<";
		else
			return "=";
	}


	protected ResourceLink getSelfLink(UriInfo uriInfo)
	{
			String fullpath = uriInfo.getAbsolutePath().toString();
			ResourceLink link = new ResourceLink("self", "application/xml", fullpath);
			return link;
	}

	protected ResourceLink getNextLink(UriInfo uriInfo, int resultsetCount, int totalResults, int maxCountPerQuery)
	{
		//System.out.println("resultsetCount: "+resultsetCount);
		//System.out.println("totalResults: "+totalResults);
		//System.out.println("maxCountPerQuery: "+maxCountPerQuery);
			if(resultsetCount == totalResults)
				return null;

			int startIndex = -1;
			int batchSize = -1;
			ResourceLink link = null;
			String bStart = uriInfo.getQueryParameters().getFirst("start");
			if (bStart != null)
				startIndex = Integer.parseInt(bStart);

			String bSize = uriInfo.getQueryParameters().getFirst("size");
			if (bSize != null)
			{
				batchSize = Integer.parseInt(bSize);
			}

			String startStr = null;
			String sizeStr = null;
			int newStart = 0;
			int newSize = maxCountPerQuery;

			//No start is given
			if(startIndex == -1)
			{
				newStart = resultsetCount+1;
			}
			else
			{
				if((startIndex + batchSize) > totalResults)
					return null;

				newStart = startIndex + resultsetCount;
				if(newStart > totalResults)
					return null;
			}

			//No start is given
			if(batchSize == -1)
			{
				newSize = maxCountPerQuery;
			}
			else
			{
				if(batchSize > maxCountPerQuery)
					newSize = maxCountPerQuery;
				else
					newSize = batchSize;
			}


			String fullpath = uriInfo.getAbsolutePath().toString();
			String path = removeStartSize(uriInfo, fullpath);

			String href = null;
			if(path.indexOf("?") > 0)
				href = path + "&start=" + newStart + "&size=" + newSize;
			else
				href = path + "?start=" + newStart + "&size=" + newSize;

			link = new ResourceLink("next", "application/xml", href);
			//System.out.println(link.toString());
			return link;

	}

	protected ResourceLink getPreviousLink(UriInfo uriInfo, int resultsetCount, int totalResults, int maxCountPerQuery)
	{
			if(resultsetCount == totalResults)
				return null;

			int startIndex = -1;
			int batchSize = -1;
			ResourceLink link = null;
			String bStart = uriInfo.getQueryParameters().getFirst("start");
			if (bStart != null)
				startIndex = Integer.parseInt(bStart);

			String bSize = uriInfo.getQueryParameters().getFirst("size");
			if (bSize != null)
			{
				batchSize = Integer.parseInt(bSize);
			}

			String startStr = null;
			String sizeStr = null;
			int newStart = 0;
			int newSize = maxCountPerQuery;

			//No start is given
			if(startIndex == -1)
			{
				return null;
			}
			else
			{
				if(startIndex >= resultsetCount)
				{
					newStart = startIndex - resultsetCount;
				}
				else
					return null;

			}
			if(newStart < 0)
				newStart = 0;

			//No start is given
			if(batchSize == -1)
			{
				newSize = maxCountPerQuery;
			}
			else
			{
				if(batchSize > maxCountPerQuery)
					newSize = maxCountPerQuery;
				else
					newSize = batchSize;
			}


			String fullpath = uriInfo.getAbsolutePath().toString();
			String path = removeStartSize(uriInfo, fullpath);

			String href = null;
			if(path.indexOf("?") > 0)
				href = path + "&start=" + newStart + "&size=" + newSize;
			else
				href = path + "?start=" + newStart + "&size=" + newSize;

			link = new ResourceLink("previous", "application/xml", href);
			//System.out.println(link.toString());
			return link;

	}

	private String removeStartSize(UriInfo uriInfo, String fullpath)
	{
			String bStart = uriInfo.getQueryParameters().getFirst("start");
			String startStr = null;
			String sizeStr = null;
			String preHref = null;
			if (bStart != null)
				startStr = "start="+bStart;

			String bSize = uriInfo.getQueryParameters().getFirst("size");
			if (bSize != null)
				sizeStr = "size="+bSize;

			if(startStr != null && fullpath.indexOf(startStr)>0)
			{
				String preHref1 = fullpath.substring(0, fullpath.indexOf(startStr));
				//System.out.println("preHref1: "+preHref1);
				if(preHref1.endsWith("&"))
					preHref1 = preHref1.substring(0, preHref1.length() -1);

				String preHref2 = fullpath.substring(fullpath.indexOf(startStr)+startStr.length(), fullpath.length());
				//System.out.println("preHref2: "+preHref2);
				preHref = preHref1 + preHref2;
				//System.out.println("preHref3: "+preHref1);

			}
			else
				preHref = fullpath;


			//System.out.println("preHref: "+preHref);

			if(sizeStr != null && preHref.indexOf(sizeStr)>0)
			{
				String preHref1 = preHref.substring(0, preHref.indexOf(sizeStr));
				//System.out.println("preHref4: "+preHref1);
				if(preHref1.endsWith("&"))
					preHref1 = preHref1.substring(0, preHref1.length() -1);

				String preHref2 = fullpath.substring(preHref.indexOf(sizeStr)+sizeStr.length(), preHref.length());
				//System.out.println("preHref5: "+preHref2);
				preHref = preHref1 + preHref2;
				//System.out.println("preHref6: "+preHref1);

			}
			else
			{
				if(preHref == null)
					preHref = fullpath;

			}

			//System.out.println("preHref****: "+preHref);
			return preHref;


	}

	protected HQLCriteria buildHQLCriteria(String className,
			List<Field> searchFields, Map matrixParams,
			UriInfo uriInfo) {
		int startIndex = -1;
		int totalSize = -1;
		//System.out.println("uriInfo.getPathParameters(): "+uriInfo.getPathParameters());
		//System.out.println("uriInfo.getQueryParameters(): "+uriInfo.getQueryParameters());
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		//System.out.println("bStart: "+bStart);
		if (bStart != null)
			startIndex = Integer.parseInt(bStart);

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		//System.out.println("bSize: "+bSize);
		if (bSize != null)
			totalSize = Integer.parseInt(bSize);

		//System.out.println("startIndex: "+startIndex);
		//System.out.println("totalSize: "+totalSize);

		String whereCriteria = "";
		List criteria = new ArrayList();
		List params = new ArrayList();

		List<String> invalidAttrs = new ArrayList<String>();
		Iterator iter = matrixParams.keySet().iterator();
		while (iter.hasNext()) {
			boolean found = false;
			String fullName = (String) iter.next();
			String attrName = getAttributeName(fullName);
			Object attrVal = null;
			String operator = "=";
			if(!fullName.equals(attrName))
			{
				attrVal = getAttributeValue(fullName);
				operator = getAttributeOperator(fullName);
			}
			else
				attrVal = matrixParams.get(fullName);

			String attrValue=null;
			if(attrVal instanceof java.util.ArrayList)
				attrValue = (String)((ArrayList)attrVal).get(0);
			else
				attrValue = (String)attrVal;

			Field field = null;
			Iterator fIter = searchFields.iterator();
			while (fIter.hasNext()) {
				field = (Field) fIter.next();
				if (field.getName().equals(attrName)) {
					found = true;
					break;
				}
			}

			if (found) {
				if (field.getType().getName().equals("java.lang.String")) {
					if((attrValue.indexOf("*") != -1) || (attrValue.indexOf("%") != -1))
					{
						//params.add(attrValue.replace("*", "%"));
						criteria.add(attrName + " like '"+attrValue.replace("*", "%")+"'");
					}
					else
					{
						params.add(attrValue);
						criteria.add(attrName + operator+"? ");
					}
				} else {
					Object paramValue = convertValues(field, attrValue);
					if (paramValue != null) {

						params.add(paramValue);
						criteria.add(attrName + operator+"? ");
					} else {
						  StringBuffer buffer = new StringBuffer();
						  ResponseBuilder builder = Response
						  .status(Status.INTERNAL_SERVER_ERROR);
						  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
						  buffer.append("<response>");
						  buffer.append("<type>ERROR</type>");
						  buffer.append("<code>INTERNAL_ERROR_2</code>");
						  buffer.append("<message>Failed to construct criteria</message>");
						  buffer.append("</response>");
						  builder.entity(buffer.toString());
						  throw new WebApplicationException(builder.build());
					}
				}
			} else {
				invalidAttrs.add(attrName);
			}
		}

		if (criteria.size() > 0)
			whereCriteria = " where ";
		else {
			ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			builder.type("application/xml");
			builder.entity("<message>Invalid Criteria</message>");
			throw new WebApplicationException(builder.build());
		}

		java.util.Iterator cIter = criteria.iterator();

		while (cIter.hasNext()) {
			String crtra = (String) cIter.next();
			whereCriteria = whereCriteria + crtra + " ";
			if (cIter.hasNext())
				whereCriteria = whereCriteria + " and ";
		}

		HQLCriteria hcriteria = null;
		if (whereCriteria.length() > 0)
			hcriteria = new HQLCriteria(
					"from "+ className +" a "
							+ whereCriteria, params, startIndex-1, totalSize);
		else
			hcriteria = new HQLCriteria(
					"from " + className + " a ",
					params, startIndex-1, totalSize);

		return hcriteria;
	}

	/**
	 * Converts the specified value to the field class type
	 *
	 * @param field
	 *            Specifies the field
	 * @param value
	 *            Specifies the values that needs to be stored
	 * @return returns an object with the new value
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object convertValues(Field field, Object value) throws WebApplicationException {

		String fieldType = field.getType().getName();
		String valueType = value.getClass().getName();
		Object convertedValue = null;
		try {
			if (fieldType.equals("java.lang.Long")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Long((String) value);
				}
			} else if (fieldType.equals("java.lang.Integer")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Integer((String) value);
				}
			} else if (fieldType.equals("java.lang.Float")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Float((String) value);
				}
			} else if (fieldType.equals("java.lang.Double")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Double((String) value);
				}
			} else if (fieldType.equals("java.lang.Boolean")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Boolean((String) value);
				}
			} else if (fieldType.equals("java.util.Date")) {
				SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
				if (valueType.equals("java.lang.String")) {
					convertedValue = format.parse((String) value);
				}
			} else if (fieldType.equals("java.net.URI")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new URI((String) value);
				}
			} else if (fieldType.equals("java.lang.Character")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new Character(((String) value).charAt(0));
				}
			} else if (field.getType().isEnum()) {
				if (valueType.equals("java.lang.String")) {
					Class enumKlass = Class.forName(fieldType);
					convertedValue = Enum.valueOf(enumKlass, (String) value);
				}
			} else {
				throw new Exception("type mismatch - " + valueType);
			}

		} catch (Exception ex) {
			  String msg = "Type mismatch " + field.getName() + " is of type - "
					+ fieldType + " \n " + ex.getMessage();
			 ex.printStackTrace();
			  log.error("ERROR : " + msg);
			  ResponseBuilder builder = Response
			  .status(Status.INTERNAL_SERVER_ERROR);
			  StringBuffer buffer = new StringBuffer();
			  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			  buffer.append("<response>");
			  buffer.append("<type>ERROR</type>");
			  buffer.append("<code>INTERNAL_ERROR_3</code>");
			  buffer.append("<message>Failed to construct criteria: "+ msg +"</message>");
			  buffer.append("</response>");
			  builder.entity(buffer.toString());
			  throw new WebApplicationException(builder.build());
		}

		return convertedValue;
	}


	public HQLCriteria getAssociationCriteria(Class sourceClass,
									String associationName,
									String id,
									int start,
									int size) throws WebApplicationException {

		try
		{
			String assocType = "";
			try{
				assocType = classCache.getAssociationType(sourceClass,associationName);
			}catch(Exception e)
			{
				throw new ApplicationException(e);
			}
			String hql = "";
			boolean isCollection = classCache.isCollection(sourceClass.getName(), associationName);
			String idName = classCache.getClassIdName(sourceClass);
			String assosIdName = classCache.getClassIdName(assocType);

			if(isCollection)
			{
				hql = "select dest from "+sourceClass.getName()+" as src inner join src."+associationName+" dest where dest."+assosIdName+" = ?";
			}
			else
			{
				hql = "select dest from "+assocType+" as dest,"+sourceClass.getName()+" as src where src."+associationName+"."+idName+"=dest."+assosIdName+" and dest."+assosIdName+" = ?";
			}

	//System.out.println("hql: "+hql);

			Field[] fields = classCache.getAllFields(sourceClass);
			Field idField = getIdField(fields,idName);
			List params = new ArrayList(1);
			params.add(convertValues(idField, id));

			HQLCriteria hqlCriteria = new HQLCriteria(hql, params, start, size);
			return hqlCriteria;
		} catch (Exception ex) {
			 ex.printStackTrace();
			  ResponseBuilder builder = Response
			  .status(Status.INTERNAL_SERVER_ERROR);
			  StringBuffer buffer = new StringBuffer();
			  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			  buffer.append("<response>");
			  buffer.append("<type>ERROR</type>");
			  buffer.append("<code>INTERNAL_ERROR_3</code>");
			  buffer.append("<message>Failed to construct criteria: "+ ex.getMessage() +"</message>");
			  buffer.append("</response>");
			  builder.entity(buffer.toString());
			  throw new WebApplicationException(builder.build());
		}

	}

	protected void query(
			@Context javax.servlet.http.HttpServletRequest request,
			@Context javax.servlet.http.HttpServletResponse response,
			HQLCriteria criteria, String targetClassName)
			throws WebApplicationException {
		try {
			httpUtils.setServletName(request.getRequestURL().toString());
			ServletOutputStream out = response.getOutputStream();
			Object[] results = httpUtils.getResultSet(criteria);

			if (results == null) {
				ResponseBuilder builder = Response.status(Status.NOT_FOUND);
				builder.type("application/xml");
				builder.entity("<error>Not found</error>");
				throw new WebApplicationException(builder.build());
			}

			httpUtils.setTargetClassName(targetClassName);
			executeFormatOutput(response, out, results, 1, httpUtils, "XML",
					false);
		} catch (IOException e) {
			log.error("Error in querying REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to Query due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		} catch (Exception e) {
			log.error("Error in querying REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Not found" + e.getMessage() + "</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	protected void query(
			@Context javax.servlet.http.HttpServletRequest request,
			@Context javax.servlet.http.HttpServletResponse response,
			String targetClassName, String targetPackageName,
			String criteriaValue, String roleName)
			throws WebApplicationException {
		try {
			httpUtils.setServletName(request.getRequestURL().toString());
			ServletOutputStream out = response.getOutputStream();
			String criteriaStr = targetClassName + "[@id=" + criteriaValue
					+ "]";
			String roleNameLower = roleName.substring(0, 1).toLowerCase()
					+ roleName.substring(1, roleName.length());
			Object[] results = httpUtils.getResultSet(targetClassName,
					targetPackageName, criteriaStr, roleNameLower);

			if (results == null) {
				ResponseBuilder builder = Response.status(Status.NOT_FOUND);
				builder.type("application/xml");
				builder.entity("<error>Not found</error>");
				throw new WebApplicationException(builder.build());
			}

			httpUtils.setTargetClassName(targetClassName);

			executeFormatOutput(response, out, results, 1, httpUtils, "XML",
					false);
		} catch (IOException e) {
			log.error("Error in querying REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to Query due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		} catch (Exception e) {
			log.error("Error in querying REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Not found" + e.getMessage() + "</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	protected ApplicationService getApplicationService() {
		return writableApplicationService;
	}

	public Object save(final Object obj) throws WebApplicationException {
		try {
			//final InsertExampleQuery sdkQuery = new InsertExampleQuery(obj);
			//writableApplicationService.executeQuery(sdkQuery);
			final InsertExampleQuery sdkQuery = new InsertExampleQuery(obj);
			sdkQuery.setCommit(true);
			SDKQueryResult queryResult = writableApplicationService.executeQuery(sdkQuery);
			return queryResult.getObjectResult();
		} catch (Exception e) {
			log.error("Error in Saving REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to Save due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	public void update(Object obj) throws WebApplicationException {
		try {
			System.out.println("in update................");
			//final UpdateExampleQuery sdkQuery = new UpdateExampleQuery(obj);
			//writableApplicationService.executeQuery(sdkQuery);
			final UpdateExampleQuery sdkQuery = new UpdateExampleQuery(obj);
			sdkQuery.setCommit(true);
			new BaseUtilWrapper() {

				@Override
				public List execute() throws Exception {
					writableApplicationService.executeQuery(sdkQuery);
					return null;
				}
			}.executeLogic();
		} catch (Exception e) {
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to update due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		}

	}

	public void delete(Object obj) throws WebApplicationException {
		try {
			System.out.println("in delete................");
			//final DeleteExampleQuery sdkQuery = new DeleteExampleQuery(obj);
			//writableApplicationService.executeQuery(sdkQuery);
			final DeleteExampleQuery sdkQuery = new DeleteExampleQuery(obj);
			sdkQuery.setCommit(true);
			new BaseUtilWrapper() {

				@Override
				public List execute() throws Exception {
					writableApplicationService.executeQuery(sdkQuery);
					return null;
				}
			}.executeLogic();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to update due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	public void delete(DeleteHQLQuery query) throws WebApplicationException {
		try {
			System.out.println("in delete.......DeleteHQLQuery query.........");
			writableApplicationService.executeQuery(query);
		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to delete due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	@SuppressWarnings("unchecked")
	private abstract class BaseUtilWrapper {
		public abstract List execute() throws Exception;

		public void executeLogic() {
			try {
				execute();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void executeFormatOutput(HttpServletResponse response,
			ServletOutputStream out, Object[] resultSet, int pageNumber,
			HTTPUtils httpUtils, String queryType) throws Exception {
		executeFormatOutput(response, out, resultSet, pageNumber, httpUtils,
				queryType, true);
	}

	protected void executeFormatOutput(HttpServletResponse response,
			ServletOutputStream out, Object[] resultSet, int pageNumber,
			HTTPUtils httpUtils, String queryType, boolean query)
			throws Exception {
		try {

			XMLOutputter xout = new XMLOutputter();
			org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet,
					pageNumber, query);

			if (queryType.endsWith("XML")) {
				response.setContentType("text/xml");
				xout.output(domDoc, out);
			} else if (queryType.endsWith("JSON")) {
				response.setContentType("application/x-javascript");
				if (httpUtils.getTargetPackageName() != null) {
					printDocument(domDoc, jsonStyleSheet, out, httpUtils);
				}
			} else {
				response.setContentType("text/html");
				if (httpUtils.getTargetPackageName() != null) {
					printDocument(domDoc, cacoreStyleSheet, out, httpUtils);
				}
			}

		} catch (Exception ex) {
			log.error("Print Results Exception: " + ex.getMessage());
			throw ex;
		}
	}


	/**
	 * Generates an HTML Error message based upon a given Exception
	 *
	 * @param Exception
	 *            The exception that should be used to generate an HTML error
	 *            message
	 * @return A string-based HTML error message containing the Exception
	 *         message.
	 */
	protected String getXMLErrorMsg(Exception ex, String query) {

		StringBuilder sb = new StringBuilder();

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<xlink:httpQuery xmlns:xlink=\"http://www.w3.org/1999/xlink\">")
				.append("<queryRequest>").append("<query>")
				.append("<queryString>" + query + "</queryString>")
				.append("<class></class>").append("</query>")
				.append("<criteria></criteria>").append("</queryRequest>")
				.append("<queryResponse>");

		String msg = ex.getMessage();
		Throwable tempEx = ex.getCause();
		while (tempEx != null) {
			msg += "\n\nCaused by: " + tempEx.getMessage();
			tempEx = tempEx.getCause();
		}

		sb.append(msg);

		sb.append("<error>" + msg + "</error>").append("</queryReponse>")
				.append("</xlink:httpQuery>");

		return sb.toString();
	}

	/**
	 * Generates an HTML Error message based upon a given Exception
	 *
	 * @param Exception
	 *            The exception that should be used to generate an HTML error
	 *            message
	 * @return A string-based HTML error message containing the Exception
	 *         message.
	 */
	protected String getHTMLErrorMsg(Exception ex) {

		StringBuilder sb = new StringBuilder();

		sb.append("<html>\n")
				.append("<head>\n")
				.append("<title>caCORE HTTP Servlet Error</title>\n")
				.append("</head>\n")
				.append("<body>\n")
				.append("<table height=\"100%\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n")
				.append("<tr valign=\"top\" align=\"left\">\n")
				.append("<td valign=\"top\" align=\"left\">\n")

				.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n")
				.append("<tr valign=\"top\" align=\"left\">\n")
				.append("<td valign=\"top\" align=\"left\">\n")
				.append("<tr>\n")
				.append("<td valign=\"top\" align=\"left\">\n")
				.append("<b><font size=6>caCORE HTTP Servlet Error:</font></b>\n")
				.append("</td>\n").append("</tr>\n").append("<tr>\n")
				.append("<td valign=\"top\" align=\"left\">\n")
				.append("<b><hr></b>\n").append("</td>\n").append("</tr>\n")
				.append("<tr>\n")
				.append("<td valign=\"top\" align=\"left\">\n")
				.append("<pre class=\"autoOverflow\">\n")
				.append("<font size=4 color=red><b><br><br>\n");

		String msg = ex.getMessage();
		Throwable tempEx = ex.getCause();
		while (tempEx != null) {
			msg += "<br><br>Caused by: " + tempEx.getMessage();
			tempEx = tempEx.getCause();
		}

		sb.append(msg);

		sb.append("</b></font>\n").append("</pre>\n").append("</td>\n")
				.append("</tr>\n").append("</td>\n").append("</tr>\n")
				.append("</table>\n");

		return sb.toString();
	}

	/**
	 * Generates an HTML Document for a given XML document with the given
	 * stylesheet specification
	 *
	 * @param doc
	 *            Specifies the XML document
	 * @param styleSheet
	 *            Specifies the stylesheet
	 * @return
	 * @throws Exception
	 */
	public void printDocument(Document doc, String styleSheet,
			OutputStream out, HTTPUtils httpUtils) throws Exception {
		try {
			InputStream styleIn = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(styleSheet);
			if (styleIn != null) {
				httpUtils.transform(doc, styleIn, out);
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}


	/**
	 * Returns the query syntax
	 *
	 * @return
	 */
	protected String getQuerySyntax() {
		String syntax = "<br><br><font color=black size=4><B>Syntax: </B><br>"
				+ "<font color=purple>query=</font>TargetClassName"
				+ "<font color=purple>&</font>CriteriaClassName"
				+ "<font color=purple>[@</font>attribute"
				+ "<font color=purple>=</font>value"
				+ "<font color=purple>][</font>association"
				+ "<font color=purple>[@</font>attribute"
				+ "<font color=purple>=</font>value"
				+ "<font color=purple>]]<font color=purple></font>";
		return syntax;

	}

	private Field getIdField(Field[] fields, String idName) throws Exception{
		Field id = null;
		for(int i=0; i<fields.length;i++){
			if(fields[i].getName().equalsIgnoreCase(idName)){
				if(!this.locateClass(fields[i].getType().getName())){
					id = fields[i];
					break;
				}
			}
		}
		return id;
	}

	public boolean locateClass(String className){
		//To make sure class is not a proxy generated by CGLIB
		if(className.indexOf("$")>1)
			className = className.substring(0, className.indexOf("$"));

		try {
			classCache.getClassFromCache(className);
		} catch(ClassNotFoundException e){
			return false;
		}

		return true;

	}

}
