package gov.nih.nci.system.web;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.WritableApplicationService;
import gov.nih.nci.system.client.proxy.ApplicationServiceProxy;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.web.util.HTTPUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import org.acegisecurity.Authentication;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.aopalliance.aop.Advice;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.springframework.aop.Advisor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Super class for all RESTful resources providing common functionality
 * 
 * 
 */
public class RESTfulResource {

	public static Logger log = Logger
			.getLogger(RESTfulResource.class.getName());
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
	protected boolean isoEnabled = false;
	String isoprefix = "gov.nih.nci.iso21090.";

	/*
	 * Initialize resource with ApplicationService and other properties
	 */
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
			applicationService = (ApplicationService) ctx
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

			String securityEnabled = (String) systemProperties
					.getProperty("securityEnabled");
			secured = "yes".equalsIgnoreCase(securityEnabled)
					|| "true".equalsIgnoreCase(securityEnabled);

			String isoEnabledStr = (String) systemProperties
					.getProperty("enableISO21090DataTypes");
			isoEnabled = "yes".equalsIgnoreCase(isoEnabledStr)
					|| "true".equalsIgnoreCase(isoEnabledStr);

			HibernateConfigurationHolder configurationHolder = (HibernateConfigurationHolder) ctx
					.getBean("HibernateConfigHolder");
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

	public List<Object> getSearchableIsoDataTypeFields(String className,
			String fieldName) {
		Map<String, List<Object>> searchableFieldsMap = classCache
				.getSearchableFieldsMap().get(className);

		if (searchableFieldsMap == null) {
			log.error("No searchable ISO data type fields found for class: '"
					+ className + "' and field: '" + fieldName + "'");
			return (List<Object>) (new ArrayList<Object>());
		}

		List<Object> searchableFields = searchableFieldsMap.get(fieldName);

		if (searchableFields == null || searchableFields.isEmpty()) {
			log.error("No searchable ISO data type fields found for class: '"
					+ className + "' and field: '" + fieldName + "'");
			return (List<Object>) (new ArrayList<Object>());
		}

		return searchableFields;
	}

	/*
	 * Validate given criteria. If not valid, respond with XML including valid
	 * fields If ISO is enabled, attribute name can have sub attribute
	 * 
	 * @param Map matrix params from the request
	 * 
	 * @param List<Field> searchable fields for a selected domain class
	 */
	protected void validateCriteria(String className, Map matrixParams,
			List<Field> searchFields) throws WebApplicationException {
		List<String> validAttrs = new ArrayList();
		// System.out.println("Class cache: " + classCache.toString());
		if (isoEnabled) {
			for (Field field : searchFields) {
				// System.out.println("field...." + field.getName());
				List isoFields = getSearchableIsoDataTypeFields(className,
						field.getName());
				System.out.println("ISO Data types are enabled....isoFields: "
						+ isoFields);
				List<String> fNames = getSearchableIsoDataTypeFieldsWithFN(
						field, isoFields);
				validAttrs.addAll(fNames);
			}

		} else {
			for (Field field : searchFields)
				validAttrs.add(field.getName());
		}

		System.out.println("validAttrs: " + validAttrs);
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
			for (String attrName : validAttrs) {
				buffer.append("<attribute>");
				buffer.append(attrName);
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
			if (!validAttrs.contains(attrName))
				invalidAttrs.add(fullName);
		}

		System.out.println("invalidAttrs: " + invalidAttrs);
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
			for (String attrName : validAttrs) {
				buffer.append("<attribute>");
				buffer.append(attrName);
				buffer.append("</attribute>");
			}

			buffer.append("</valid>");
			buffer.append("<invalid>");
			for (String attr : invalidAttrs) {
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

	/**
	 * Convert ISO attr parts into attribute names [{part_0=[value, code,
	 * codeSystem, {type=[AL]}]} resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type
	 * 
	 * @param attrName
	 * @param attrs
	 * @return
	 */
	private List<String> convertISOPart(String attrName, Map attrs,
			List<String> fnAttrs) {
		// List of ISO fields
		Iterator iter = attrs.keySet().iterator();
		while (iter.hasNext()) {
			// part_0
			String keyName = (String) iter.next();
			// System.out.println("keyName: " + keyName);
			Object value = attrs.get(keyName);
			// System.out.println("value: " + value);

			if (value instanceof java.lang.String) {
				// value, code, codeSystem
				// System.out.println("instanceof java.lang.String: ");
				fnAttrs.add(attrName + "." + ((String) value));
			}
			// {type=[AL]}
			else if (value instanceof java.util.List) {
				// System.out.println("instanceof java.util.List: ");
				java.util.List attrList = (java.util.List) value;
				Iterator valueIter = attrList.iterator();
				while (valueIter.hasNext()) {
					Object valueObj = valueIter.next();
					// System.out.println("instanceof java.util.List: valueObj "
					// + valueObj);
					if (valueObj instanceof String) {
						// System.out.println("instanceof String: valueObj "
						// + valueObj);
						fnAttrs.add(keyName + "." + ((String) valueObj));

						/*
						 * if(attrs.size() == 1) fnAttrs.add(keyName); else
						 * fnAttrs.add(keyName + "." + ((String) valueObj));
						 */
					} else if (valueObj instanceof java.util.Map) {
						// System.out.println("instanceof String: valueObj "
						// + valueObj);
						Iterator mapIter = ((java.util.Map) valueObj).keySet()
								.iterator();
						while (mapIter.hasNext()) {
							String subkeyName = (String) mapIter.next();
							fnAttrs.add(keyName + "." + subkeyName);
						}
					}
				}
			} else {
				// System.out.println("value: instance of do not know " +
				// value);
			}
		}
		// System.out.println("convertISOPart: " + fnAttrs);
		return fnAttrs;
	}

	/**
	 * Convert ISO attr parts into attribute names Expected formats: List< Map<
	 * String, List<?> > > ? can be String or a Map<String, String>
	 * [{part_0=[value, code, codeSystem, {type=[AL]}]}, {part_1=[value, code,
	 * codeSystem, {type=[AL]}]}] resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type, part_1.value, part_1.code,
	 * part_1.codeSystem, part_1.type
	 * 
	 * @param field
	 * @param attrs
	 * @return
	 */
	private List<String> getSearchableIsoDataTypeFieldsForAd(Field field,
			List attrs) {
		String fieldName = field.getName();
		System.out.println("fieldName: "+fieldName);
		String fieldNameWithoutU = fieldName;
		if(fieldName.indexOf("_") > 0)
			fieldNameWithoutU = fieldName.substring(0, fieldName.indexOf("_")) + fieldName.substring(fieldName.indexOf("_")+1, fieldName.length());
		System.out.println("fieldNameWithoutU: "+fieldNameWithoutU);
	
		List<String> fnAttrs = new ArrayList();
		// List of ISO fields
		Iterator iter = attrs.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			Map attrMap = (Map) obj;
			Iterator mapIter = attrMap.keySet().iterator();
			while (mapIter.hasNext()) {
				// part_0
				String keyName = (String) mapIter.next();
				System.out.println("keyName: "+keyName);
				String keyNameWithoutU = keyName;
				if(keyName.indexOf("_") > 0)
					keyNameWithoutU = keyName.substring(0, keyName.indexOf("_")) + keyName.substring(keyName.indexOf("_")+1, keyName.length());
				System.out.println("keyNameWithoutU: "+keyNameWithoutU);
				// value, code, codeSystem, {type=[AL]}
				Object mapKeyObj = attrMap.get(keyName);
				// System.out.println("instanceof java.util.List*******"
				// + mapKeyObj);
				List mapKeyObjValue = (List) attrMap.get(keyName);
				Iterator mapKeyObjValueIter = mapKeyObjValue.iterator();

				while (mapKeyObjValueIter.hasNext()) {
					List<String> subAttrNames = new ArrayList();
					Object mapKeyObjValueObj = mapKeyObjValueIter.next();
					if (mapKeyObjValueObj instanceof String) {
						fnAttrs.add(fieldName + "." + keyNameWithoutU + "."
								+ mapKeyObjValueObj);
					} else {
						// System.out.println("instanceof not String *******"
						// + mapKeyObj);
						java.util.Map mapKeyObjValueMap = (java.util.Map) mapKeyObjValueObj;
						String key = (String) mapKeyObjValueMap.keySet()
								.iterator().next();
						fnAttrs.add(fieldName + "." + keyNameWithoutU + "." + key);
					}
				}

			}
		}
		 System.out.println("returning Ad fnAttrs: " + fnAttrs);
		return fnAttrs;
	}

	/**
	 * Convert ISO attr parts into attribute names Expected formats: List< Map<
	 * String, List<?> > > ? can be String or a Map<String, String>
	 * [{part_0=[value, code, codeSystem, {type=[AL]}]}, {part_1=[value, code,
	 * codeSystem, {type=[AL]}]}] resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type, part_1.value, part_1.code,
	 * part_1.codeSystem, part_1.type
	 * 
	 * @param field
	 * @param attrs
	 * @return
	 */
	private List<String> getSearchableIsoDataTypeFieldsForSc(Field field,
			List attrs) {
		String fieldName = field.getName();
		List<String> fnAttrs = new ArrayList();
		// List of ISO fields
		Iterator iter = attrs.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof String) {
				fnAttrs.add(fieldName + "." + obj);
			} else {
				Map attrMap = (Map) obj;
				Iterator mapIter = attrMap.keySet().iterator();
				while (mapIter.hasNext()) {
					// part_0
					String keyName = (String) mapIter.next();

					// value, code, codeSystem, {type=[AL]}
					Object mapKeyObj = attrMap.get(keyName);
					// //System.out.println("instanceof java.util.List*******"
					// + mapKeyObj);
					List mapKeyObjValue = (List) attrMap.get(keyName);
					Iterator mapKeyObjValueIter = mapKeyObjValue.iterator();

					while (mapKeyObjValueIter.hasNext()) {
						List<String> subAttrNames = new ArrayList();
						Object mapKeyObjValueObj = mapKeyObjValueIter.next();
						if (mapKeyObjValueObj instanceof String) {
							fnAttrs.add(fieldName + "." + keyName + "."
									+ mapKeyObjValueObj);
						} else {
							// //System.out.println("instanceof not String *******"
							// + mapKeyObj);
							java.util.Map mapKeyObjValueMap = (java.util.Map) mapKeyObjValueObj;
							String key = (String) mapKeyObjValueMap.keySet()
									.iterator().next();
							fnAttrs.add(fieldName + "." + keyName + "." + key);
						}
					}

				}
			}
		}
		// System.out.println("returning Ad fnAttrs: " + fnAttrs);
		return fnAttrs;
	}

	/**
	 * Convert ISO attr parts into attribute names
	 * 
	 * Expected formats: List< Map< String, List< Map< String, List<?> > > > > ?
	 * can be String or a Map<String, String>
	 * [{item<gov.nih.nci.iso21090.Ad>=[{part_0=[value, code, codeSystem,
	 * {type=[AL]}]}, {part_1=[value, code, codeSystem, {type=[AL]}]}]}]
	 * resulting: item.part_0.value, item.part_0.code, item.part_0.codeSystem,
	 * item.part_0.type, item.part_1.value, item.part_1.code,
	 * item.part_1.codeSystem, item.part_1.type
	 * 
	 * @param attrs
	 * @return
	 */
	private List<String> getSearchableIsoDataTypeFieldsForDsetAd(Field field,
			List attrs) {

		String fieldName = field.getName();
		List<String> fnAttrs = new ArrayList();
		// List of ISO fields
		Iterator iter = attrs.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof java.lang.String) {
				// //System.out.println("instanceof java.lang.String*******" +
				// obj);
				fnAttrs.add((String) obj);
			} else if (obj instanceof java.util.Map) {
				// //System.out.println("instanceof java.util.Map*******" +
				// obj);
				Map attrMap = (Map) obj;
				Iterator mapIter = attrMap.keySet().iterator();
				while (mapIter.hasNext()) {
					// part_0
					// item<gov.nih.nci.iso21090.Ad>
					String keyName = (String) mapIter.next();
					String subAttrName = keyName;
					if (keyName.indexOf("<") > 0) {
						subAttrName = keyName
								.substring(0, keyName.indexOf("<"));
					}

					// value, code, codeSystem, {type=[AL]}
					// [{part_0=[value, code, codeSystem, {type=[AL]}]},
					// {part_1=[value, code, codeSystem, {type=[AL]}]}]
					Object mapKeyObj = attrMap.get(keyName);
					if (mapKeyObj instanceof java.util.List) {
						// //System.out.println("instanceof java.util.List*******"
						// + mapKeyObj);
						List mapKeyObjValue = (List) attrMap.get(keyName);
						Iterator mapKeyObjValueIter = mapKeyObjValue.iterator();

						while (mapKeyObjValueIter.hasNext()) {
							List<String> subAttrNames = new ArrayList();
							Object mapKeyObjValueObj = mapKeyObjValueIter
									.next();
							if (mapKeyObjValueObj instanceof String) {
								fnAttrs.add(fieldName + "." + subAttrName + "."
										+ mapKeyObjValueObj);
							} else {
								// System.out
								// .println("instanceof not String *******"
								// + mapKeyObj);
								convertISOPart(subAttrName,
										(java.util.Map) mapKeyObjValueObj,
										subAttrNames);
								for (String partAttrName : subAttrNames) {
									fnAttrs.add(fieldName + "." + subAttrName
											+ "." + partAttrName);
								}
							}
						}

					} else if (mapKeyObj instanceof java.util.Map) {
						// //System.out.println("instanceof java.util.Map ----- "
						// + mapKeyObj);
						convertISOPart(subAttrName, (java.util.Map) mapKeyObj,
								fnAttrs);
					} else {
						// System.out.println("instanceof I do not know ----- "
						// + mapKeyObj);
					}

				}
			}
		}
		// System.out.println("returning DsetAd fnAttrs: " + fnAttrs);
		/*
		 * List returnList = new ArrayList(); for(String attName : fnAttrs) {
		 * returnList.add(fieldName+"."+attName); }
		 */
		return fnAttrs;
	}

	/**
	 * Convert ISO attr parts into attribute names Expected formats: List< Map<
	 * String, List<?> > > ? can be String or a Map<String, String>
	 * [{part_0=[value, code, codeSystem, {type=[AL]}]}, {part_1=[value, code,
	 * codeSystem, {type=[AL]}]}] resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type, part_1.value, part_1.code,
	 * part_1.codeSystem, part_1.type
	 * 
	 * Expected formats: List< Map< String, List< Map< String, List<?> > > > > ?
	 * can be String or a Map<String, String>
	 * [{item<gov.nih.nci.iso21090.Ad>=[{part_0=[value, code, codeSystem,
	 * {type=[AL]}]}, {part_1=[value, code, codeSystem, {type=[AL]}]}]}]
	 * resulting: item.part_0.value, item.part_0.code, item.part_0.codeSystem,
	 * item.part_0.type, item.part_1.value, item.part_1.code,
	 * item.part_1.codeSystem, item.part_1.type
	 * 
	 * 
	 * @param attrs
	 * @return
	 */
	private List<String> getSearchableIsoDataTypeFieldsForCd(Field field,
			List attrs) {

		String typeName = field.getType().getName();
		String fieldName = field.getName();
		List<String> fnAttrs = new ArrayList();
		// List of ISO fields
		Iterator iter = attrs.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof java.lang.String) {
				String value = (String) obj;

				if (field.getName().equals(value))
					fnAttrs.add(value);
				else
					fnAttrs.add(field.getName() + "." + value);
				// System.out.println("instanceof java.lang.String*******" +
				// obj);
			} else if (obj instanceof java.util.Map) {
				Map valueMap = (java.util.Map) obj;
				Iterator valueIter = valueMap.keySet().iterator();
				while (valueIter.hasNext()) {
					String key = (String) valueIter.next();
					List keyValues = (java.util.List) valueMap.get(key);
					Iterator listIter = keyValues.iterator();
					while (listIter.hasNext()) {
						String keyValueName = (String) listIter.next();
						fnAttrs.add(field.getName() + "." + key + "."
								+ keyValueName);
					}
				}
				// //System.out.println("Don't know-----------------------");
			}
		}
		// System.out.println("returning fnAttrs: " + fnAttrs);
		return fnAttrs;
	}

	/**
	 * Convert ISO attr parts into attribute names Expected formats: List< Map<
	 * String, List<?> > > ? can be String or a Map<String, String>
	 * [{part_0=[value, code, codeSystem, {type=[AL]}]}, {part_1=[value, code,
	 * codeSystem, {type=[AL]}]}] resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type, part_1.value, part_1.code,
	 * part_1.codeSystem, part_1.type
	 * 
	 * Expected formats: List< Map< String, List< Map< String, List<?> > > > > ?
	 * can be String or a Map<String, String>
	 * [{item<gov.nih.nci.iso21090.Ad>=[{part_0=[value, code, codeSystem,
	 * {type=[AL]}]}, {part_1=[value, code, codeSystem, {type=[AL]}]}]}]
	 * resulting: item.part_0.value, item.part_0.code, item.part_0.codeSystem,
	 * item.part_0.type, item.part_1.value, item.part_1.code,
	 * item.part_1.codeSystem, item.part_1.type
	 * 
	 * 
	 * @param attrs
	 * @return
	 */
	private List<String> getSearchableIsoDataTypeFieldsWithFN(Field field,
			List attrs) {

		String typeName = field.getType().getName();
		// System.out.println("typeName: " + typeName);
		if (typeName.equals("gov.nih.nci.iso21090.Ad")
				|| typeName.equals("gov.nih.nci.iso21090.En")
				|| typeName.equals("gov.nih.nci.iso21090.EnOn")
				|| typeName.equals("gov.nih.nci.iso21090.EnPn"))
			return getSearchableIsoDataTypeFieldsForAd(field, attrs);
		else if (typeName.equals("gov.nih.nci.iso21090.DSet"))
			return getSearchableIsoDataTypeFieldsForDsetAd(field, attrs);
		else if (typeName.equals("gov.nih.nci.iso21090.Cd"))
			return getSearchableIsoDataTypeFieldsForCd(field, attrs);
		else if (typeName.equals("gov.nih.nci.iso21090.Sc")
				|| typeName.equals("gov.nih.nci.iso21090.Ivl"))
			return getSearchableIsoDataTypeFieldsForSc(field, attrs);

		String fieldName = field.getName();
		List<String> fnAttrs = new ArrayList();
		// List of ISO fields
		Iterator iter = attrs.iterator();
		while (iter.hasNext()) {
			Object obj = iter.next();
			if (obj instanceof java.lang.String) {
				String value = (String) obj;

				if (field.getName().equals(value))
					fnAttrs.add(value);
				else
					fnAttrs.add(field.getName() + "." + value);
				// System.out.println("instanceof java.lang.String*******" +
				// obj);
			}
		}
		// System.out.println("returning fnAttrs: " + fnAttrs);
		return fnAttrs;
	}

	private String getAttributeName(String name) {
		if (name.indexOf(NOT_EQUAL) > 0)
			return name.substring(0, name.indexOf(NOT_EQUAL));
		else if (name.indexOf(GREATER_THAN) > 0)
			return name.substring(0, name.indexOf(GREATER_THAN));
		else if (name.indexOf(LESS_THAN) > 0)
			return name.substring(0, name.indexOf(LESS_THAN));
		// else if (isoEnabled && name.indexOf(".") > 0)
		// return name.substring(0, name.indexOf("."));
		else
			return name;
	}

	private String getAttributeValue(String name) {
		if (name.indexOf(NOT_EQUAL) > 0)
			return name.substring(name.indexOf(NOT_EQUAL) + 2, name.length());
		else if (name.indexOf(GREATER_THAN) > 0)
			return name
					.substring(name.indexOf(GREATER_THAN) + 1, name.length());
		else if (name.indexOf(LESS_THAN) > 0)
			return name.substring(name.indexOf(LESS_THAN) + 1, name.length());
		else
			return name;
	}

	private String getAttributeOperator(String name) {
		if (name.indexOf(NOT_EQUAL) > 0)
			return "<>";
		else if (name.indexOf(GREATER_THAN) > 0)
			return ">";
		else if (name.indexOf(LESS_THAN) > 0)
			return "<";
		else
			return "=";
	}

	protected ResourceLink getSelfLink(UriInfo uriInfo) {
		String fullpath = uriInfo.getAbsolutePath().toString();
		ResourceLink link = new ResourceLink("self", "application/xml",
				fullpath);
		return link;
	}

	protected ResourceLink getNextLink(UriInfo uriInfo, int resultsetCount,
			int totalResults, int maxCountPerQuery) {
		// //System.out.println("resultsetCount: "+resultsetCount);
		// //System.out.println("totalResults: "+totalResults);
		// //System.out.println("maxCountPerQuery: "+maxCountPerQuery);
		if (resultsetCount == totalResults)
			return null;

		int startIndex = -1;
		int batchSize = -1;
		ResourceLink link = null;
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		if (bStart != null)
			startIndex = Integer.parseInt(bStart);

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		if (bSize != null) {
			batchSize = Integer.parseInt(bSize);
		}

		String startStr = null;
		String sizeStr = null;
		int newStart = 0;
		int newSize = maxCountPerQuery;

		// No start is given
		if (startIndex == -1) {
			newStart = resultsetCount + 1;
		} else {
			if ((startIndex + batchSize) > totalResults)
				return null;

			newStart = startIndex + resultsetCount;
			if (newStart > totalResults)
				return null;
		}

		// No start is given
		if (batchSize == -1) {
			newSize = maxCountPerQuery;
		} else {
			if (batchSize > maxCountPerQuery)
				newSize = maxCountPerQuery;
			else
				newSize = batchSize;
		}

		String fullpath = uriInfo.getAbsolutePath().toString();
		String path = removeStartSize(uriInfo, fullpath);

		String href = null;
		if (path.indexOf("?") > 0)
			href = path + "&start=" + newStart + "&size=" + newSize;
		else
			href = path + "?start=" + newStart + "&size=" + newSize;

		link = new ResourceLink("next", "application/xml", href);
		// //System.out.println(link.toString());
		return link;

	}

	protected ResourceLink getPreviousLink(UriInfo uriInfo, int resultsetCount,
			int totalResults, int maxCountPerQuery) {
		if (resultsetCount == totalResults)
			return null;

		int startIndex = -1;
		int batchSize = -1;
		ResourceLink link = null;
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		if (bStart != null)
			startIndex = Integer.parseInt(bStart);

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		if (bSize != null) {
			batchSize = Integer.parseInt(bSize);
		}

		String startStr = null;
		String sizeStr = null;
		int newStart = 0;
		int newSize = maxCountPerQuery;

		// No start is given
		if (startIndex == -1) {
			return null;
		} else {
			if (startIndex >= resultsetCount) {
				newStart = startIndex - resultsetCount;
			} else
				return null;

		}
		if (newStart < 0)
			newStart = 0;

		// No start is given
		if (batchSize == -1) {
			newSize = maxCountPerQuery;
		} else {
			if (batchSize > maxCountPerQuery)
				newSize = maxCountPerQuery;
			else
				newSize = batchSize;
		}

		String fullpath = uriInfo.getAbsolutePath().toString();
		String path = removeStartSize(uriInfo, fullpath);

		String href = null;
		if (path.indexOf("?") > 0)
			href = path + "&start=" + newStart + "&size=" + newSize;
		else
			href = path + "?start=" + newStart + "&size=" + newSize;

		link = new ResourceLink("previous", "application/xml", href);
		// //System.out.println(link.toString());
		return link;

	}

	private String removeStartSize(UriInfo uriInfo, String fullpath) {
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		String startStr = null;
		String sizeStr = null;
		String preHref = null;
		if (bStart != null)
			startStr = "start=" + bStart;

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		if (bSize != null)
			sizeStr = "size=" + bSize;

		if (startStr != null && fullpath.indexOf(startStr) > 0) {
			String preHref1 = fullpath.substring(0, fullpath.indexOf(startStr));
			// //System.out.println("preHref1: "+preHref1);
			if (preHref1.endsWith("&"))
				preHref1 = preHref1.substring(0, preHref1.length() - 1);

			String preHref2 = fullpath.substring(fullpath.indexOf(startStr)
					+ startStr.length(), fullpath.length());
			// //System.out.println("preHref2: "+preHref2);
			preHref = preHref1 + preHref2;
			// //System.out.println("preHref3: "+preHref1);

		} else
			preHref = fullpath;

		// //System.out.println("preHref: "+preHref);

		if (sizeStr != null && preHref.indexOf(sizeStr) > 0) {
			String preHref1 = preHref.substring(0, preHref.indexOf(sizeStr));
			// //System.out.println("preHref4: "+preHref1);
			if (preHref1.endsWith("&"))
				preHref1 = preHref1.substring(0, preHref1.length() - 1);

			String preHref2 = fullpath.substring(preHref.indexOf(sizeStr)
					+ sizeStr.length(), preHref.length());
			// //System.out.println("preHref5: "+preHref2);
			preHref = preHref1 + preHref2;
			// //System.out.println("preHref6: "+preHref1);

		} else {
			if (preHref == null)
				preHref = fullpath;

		}

		// //System.out.println("preHref****: "+preHref);
		return preHref;

	}

	protected HQLCriteria buildHQLCriteria(String className,
			List<Field> searchFields, Map matrixParams, UriInfo uriInfo) {
		int startIndex = -1;
		int totalSize = -1;
		validateCriteria(className, matrixParams, searchFields);
		// System.out.println("uriInfo.getPathParameters(): "
		// + uriInfo.getPathParameters());
		// System.out.println("uriInfo.getQueryParameters(): "
		// + uriInfo.getQueryParameters());
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		// System.out.println("bStart: " + bStart);
		if (bStart != null)
			startIndex = Integer.parseInt(bStart);

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		// System.out.println("bSize: " + bSize);
		if (bSize != null)
			totalSize = Integer.parseInt(bSize);

		// //System.out.println("startIndex: "+startIndex);
		// //System.out.println("totalSize: "+totalSize);

		HQLCriteria hcriteria = null;
		Map<String, List> whereMap = buildWhereCriteria(className,
				searchFields, matrixParams, uriInfo, null);
		String whereCriteria = whereMap.keySet().iterator().next();
		List params = whereMap.get(whereCriteria);
		if (whereCriteria.length() > 0)
			hcriteria = new HQLCriteria("from " + className + " a " + " where "
					+ whereCriteria, params, startIndex - 1, totalSize);
		else
			hcriteria = new HQLCriteria("from " + className + " a ", params,
					startIndex - 1, totalSize);

		return hcriteria;
	}

	public HQLCriteria getAssociationCriteria(Class sourceClass,
			String associationName, int start, int size,
			List<Field> searchFields, Map matrixParams, UriInfo uriInfo)
			throws WebApplicationException {

		try {
			validateCriteria(sourceClass.getName(), matrixParams, searchFields);
			String assocType = "";
			try {
				assocType = classCache.getAssociationType(sourceClass,
						associationName);
			} catch (Exception e) {
				throw new ApplicationException(e);
			}
			String hql = "";
			boolean isCollection = classCache.isCollection(
					sourceClass.getName(), associationName);
			String idName = classCache.getClassIdName(sourceClass);
			String assosIdName = classCache.getClassIdName(assocType);

			String whereCriteria = null;
			List params = null;

			if (isCollection) {
				Map<String, List> whereMap = buildWhereCriteria(
						sourceClass.getName(), searchFields, matrixParams,
						uriInfo, "src");

				if (whereMap.size() == 0) {
					ResponseBuilder builder = Response.status(Status.NOT_FOUND);
					builder.type("application/xml");
					builder.entity("<message>Invalid Criteria</message>");
					throw new WebApplicationException(builder.build());
				}

				whereCriteria = whereMap.keySet().iterator().next();
				params = whereMap.get(whereCriteria);

				hql = "select dest from " + sourceClass.getName()
						+ " as src inner join src." + associationName
						+ " dest where " + whereCriteria;
				// System.out.println("hql: collection: " + hql);
			} else {
				Map<String, List> whereMap = buildWhereCriteria(
						sourceClass.getName(), searchFields, matrixParams,
						uriInfo, "src");

				whereCriteria = whereMap.keySet().iterator().next();
				params = whereMap.get(whereCriteria);

				if (whereMap.size() == 0) {
					ResponseBuilder builder = Response.status(Status.NOT_FOUND);
					builder.type("application/xml");
					builder.entity("<message>Invalid Criteria</message>");
					throw new WebApplicationException(builder.build());
				}

				hql = "select dest from " + assocType + " as dest,"
						+ sourceClass.getName() + " as src where "
						+ whereCriteria + " and src." + associationName + "."
						+ idName + "=dest." + assosIdName;
				// System.out.println("hql2: " + hql);
			}

			// //System.out.println("hql: "+hql);

			// Field[] fields = classCache.getAllFields(sourceClass);
			// Field idField = getIdField(fields, idName);
			// params.add(convertValues(idField, id));

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
			buffer.append("<message>Failed to construct criteria: "
					+ ex.getMessage() + "</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}

	}

	public HQLCriteria getAssociationCriteria(Class sourceClass,
			String associationName, String id, int start, int size)
			throws WebApplicationException {

		try {
//			validateCriteria(sourceClass.getName(), matrixParams, searchFields);
			String assocType = "";
			try {
				assocType = classCache.getAssociationType(sourceClass,
						associationName);
			} catch (Exception e) {
				throw new ApplicationException(e);
			}
			String hql = "";
			boolean isCollection = classCache.isCollection(
					sourceClass.getName(), associationName);
			String idName = classCache.getClassIdName(sourceClass);
			String assosIdName = classCache.getClassIdName(assocType);

			if (isCollection) {
				hql = "select dest from " + sourceClass.getName()
						+ " as src inner join src." + associationName
						+ " dest where dest." + assosIdName + " = ?";
			} else {
				hql = "select dest from " + assocType + " as dest,"
						+ sourceClass.getName() + " as src where src."
						+ associationName + "." + idName + "=dest."
						+ assosIdName + " and dest." + assosIdName + " = ?";
			}

			// //System.out.println("hql: "+hql);

			Field[] fields = classCache.getAllFields(sourceClass);
			Field idField = getIdField(fields, idName);
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
			buffer.append("<code>INTERNAL_ERROR_4</code>");
			buffer.append("<message>Failed to construct criteria: "
					+ ex.getMessage() + "</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}

	}

	/**
	 * Build where criteria for query based on given search matrix parameters
	 * 
	 * @param className
	 * @param searchFields
	 * @param matrixParams
	 * @param uriInfo
	 * @param alias
	 * @return
	 */
	protected Map<String, List> buildWhereCriteria(String className,
			List<Field> searchFields, Map matrixParams, UriInfo uriInfo,
			String alias) {
		System.out
				.println("Building where criteria **************************");
		int startIndex = -1;
		int totalSize = -1;
		System.out.println("uriInfo.getPathParameters(): "
				+ uriInfo.getPathParameters());
		System.out.println("uriInfo.getQueryParameters(): "
				+ uriInfo.getQueryParameters());
		String bStart = uriInfo.getQueryParameters().getFirst("start");
		// //System.out.println("bStart: "+bStart);
		if (bStart != null)
			startIndex = Integer.parseInt(bStart);

		String bSize = uriInfo.getQueryParameters().getFirst("size");
		// //System.out.println("bSize: "+bSize);
		if (bSize != null)
			totalSize = Integer.parseInt(bSize);

		// //System.out.println("startIndex: "+startIndex);
		// //System.out.println("totalSize: "+totalSize);

		String whereCriteria = "";
		List criteria = new ArrayList();
		List params = new ArrayList();

		
		List<String> invalidAttrs = new ArrayList<String>();
		Iterator iter = matrixParams.keySet().iterator();
		while (iter.hasNext()) {
			boolean found = false;
			String fullName = (String) iter.next();
			String attrName = getAttributeName(fullName);
			System.out.println("Building where criteria attrName " + attrName);
			Object attrVal = null;
			String operator = "=";
			if (!fullName.equals(attrName)) {
				attrVal = getAttributeValue(fullName);
				operator = getAttributeOperator(fullName);
			} else
				attrVal = matrixParams.get(fullName);
			System.out.println("Building where criteria operator " + operator);
			System.out.println("Building where criteria attrVal " + attrVal);
			String attrValue = null;
			if (attrVal instanceof java.util.ArrayList)
				attrValue = (String) ((ArrayList) attrVal).get(0);
			else
				attrValue = (String) attrVal;

			Field field = null;
			Iterator fIter = searchFields.iterator();
			while (fIter.hasNext()) {
				field = (Field) fIter.next();
				System.out.println("field...." + field.getName());
				if (isoEnabled) {
					List isoFields = getSearchableIsoDataTypeFields(className,
							field.getName());
					List<String> fNames = getSearchableIsoDataTypeFieldsWithFN(
							field, isoFields);

					if (fNames.contains(attrName)) {
						found = true;
						break;
					}
				} else {
					if (field.getName().equals(attrName)) {
						found = true;
						break;
					}
				}
			}

			if (found) {
				System.out.println("field.getType().getName(): "
						+ field.getType().getName());
				if (field.getType().getName().equals("java.lang.String")) {
					if ((attrValue.indexOf("*") != -1)
							|| (attrValue.indexOf("%") != -1)) {
						// params.add(attrValue.replace("*", "%"));
						criteria.add(((alias != null) ? alias + "." : "")
								+ attrName + " like '"
								+ attrValue.replace("*", "%") + "'");
					} else {
						params.add(attrValue);
						criteria.add(((alias != null) ? alias + "." : "")
								+ attrName + operator + "? ");
					}
				} else {
					if (attrValue.equals("*")) {
						criteria.add(((alias != null) ? alias + "." : "")
								+ attrName + " like '%' ");
					} else {
						Object paramValue = convertValues(field, attrName, attrValue);
						if (paramValue != null) {

							params.add(paramValue);
							criteria.add(((alias != null) ? alias + "." : "")
									+ attrName + operator + "? ");
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
				}
			} else {
				invalidAttrs.add(attrName);
			}
		}

		if (criteria.size() == 0) {
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

		Map returnMap = new HashMap();
		returnMap.put(whereCriteria, params);
		return returnMap;
	}

	/*
	public Object convertISOValues(Field field, Object value) {
		String fieldType = field.getType().getName();
		String valueType = value.getClass().getName();
		System.out.println("convertValues valueType: " + valueType);
		System.out.println("convertValues fieldType: " + fieldType);
		Object convertedValue = null;

		if (fieldType.equals("gov.nih.nci.iso21090.Bl")) {
			convertedValue = new gov.nih.nci.iso21090.Bl();
			((gov.nih.nci.iso21090.Bl) convertedValue).setValue(new Boolean(
					(String) value));
		} else if (fieldType.equals("gov.nih.nci.iso21090.Ed")) {
			convertedValue = new gov.nih.nci.iso21090.Ed();
			((gov.nih.nci.iso21090.Ed) convertedValue).setValue((String) value);
		} else if (fieldType.equals("gov.nih.nci.iso21090.EdText")) {
			convertedValue = new gov.nih.nci.iso21090.EdText();
			((gov.nih.nci.iso21090.EdText) convertedValue)
					.setValue((String) value);
		} else if (fieldType.equals("gov.nih.nci.iso21090.EdText")) {
			convertedValue = new gov.nih.nci.iso21090.EdText();
			((gov.nih.nci.iso21090.EdText) convertedValue)
					.setValue((String) value);
		}
		return convertedValue;
	}
	*/

	public Object getTypeObject(Field field, Object parent, String attr) throws Exception
	{
		Object value = null;
		Method getterMethod = getAttributeGetMethodName(parent,
				attr);
		value = getterMethod.invoke(parent);
		
		return value;

	}

	private Method getAttributeGetMethodName(Object attObject, String attName) {
		Method m = getMethod(
				attObject.getClass(),
				"get" + attName.substring(0, 1).toUpperCase()
						+ attName.substring(1));
		return m;
	}

	/**
	 * Returns the method specified by the method name
	 * @param critClass
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Method getMethod(Class critClass, String methodName) {
		Method[] methods = getAllMethods(critClass);
		Method method = null;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equalsIgnoreCase(methodName)) {
				method = methods[i];
				break;
			}
		}
		return method;
	}
	
	/**
	 * Gets all the methods for a given class
	 * @param resultClass
	 *            - Specifies the class name
	 * @return - Returns all the methods
	 */
	@SuppressWarnings({"rawtypes"})
	public Method[] getAllMethods(Class resultClass) {
		List<Method> methodList = new ArrayList<Method>();
		try {
			while (resultClass != null && !resultClass.isInterface()
					&& !resultClass.isPrimitive()) {
				Method[] method = resultClass.getDeclaredMethods();
				for (int i = 0; i < method.length; i++) {
					method[i].setAccessible(true);
					methodList.add(method[i]);
				}
				if (!resultClass.getSuperclass().getName()
						.equalsIgnoreCase("java.lang.Object")) {
					resultClass = resultClass.getSuperclass();
				} else {
					break;
				}
			}
		} catch (Exception ex) {
			log.error("ERROR: " + ex.getMessage());
		}
		Method[] methods = new Method[methodList.size()];
		for (int i = 0; i < methodList.size(); i++) {
			methods[i] = methodList.get(i);
		}
		return methods;
	}

	public Object convertISOValues(Field field, String attrName, Object value) throws WebApplicationException{
		String fieldType = field.getType().getName();
		String valueType = value.getClass().getName();
		
		if(attrName.indexOf(".") == -1)
		{
			String msg = "Invalid attribute name: "+attrName;

			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>INTERNAL_ERROR_7</code>");
			buffer.append("<message>Failed to construct criteria: " + msg
					+ "</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}
		
		String subAttrName = attrName;
		Object attr = null;
		while(true)
		{
			int index = subAttrName.indexOf(".");
			if(index != -1)
			{
				String part = subAttrName.substring(0, subAttrName.indexOf("."));
				
			}
		}
		System.out.println("convertValues valueType: " + valueType);
		System.out.println("convertValues fieldType: " + fieldType);
		Object convertedValue = null;

		if (fieldType.equals("gov.nih.nci.iso21090.Bl")) {
			convertedValue = new gov.nih.nci.iso21090.Bl();
			((gov.nih.nci.iso21090.Bl) convertedValue).setValue(new Boolean(
					(String) value));
		} else if (fieldType.equals("gov.nih.nci.iso21090.Ed")) {
			convertedValue = new gov.nih.nci.iso21090.Ed();
			((gov.nih.nci.iso21090.Ed) convertedValue).setValue((String) value);
		} else if (fieldType.equals("gov.nih.nci.iso21090.EdText")) {
			convertedValue = new gov.nih.nci.iso21090.EdText();
			((gov.nih.nci.iso21090.EdText) convertedValue)
					.setValue((String) value);
		} else if (fieldType.equals("gov.nih.nci.iso21090.EdText")) {
			convertedValue = new gov.nih.nci.iso21090.EdText();
			((gov.nih.nci.iso21090.EdText) convertedValue)
					.setValue((String) value);
		}
		return convertedValue;
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
	public Object convertValues(Field field, String attrName, Object value)
			throws WebApplicationException {

		if (field.getType().getName().startsWith(isoprefix)) {
			return convertISOValues(field, attrName, value);
		}

		String fieldType = field.getType().getName();
		String valueType = value.getClass().getName();
		Class fieldClass = field.getClass();
		
		System.out.println("convertValues valueType: " + valueType);
		System.out.println("convertValues fieldType: " + fieldType);
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
			} else if (fieldType.equals("gov.nih.nci.iso21090.Ii")) {
				if (valueType.equals("java.lang.String")) {
					convertedValue = new gov.nih.nci.iso21090.Ii();
					((gov.nih.nci.iso21090.Ii) convertedValue)
							.setExtension((String) value);
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
			buffer.append("<code>INTERNAL_ERROR_5</code>");
			buffer.append("<message>Failed to construct criteria: " + msg
					+ "</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}

		return convertedValue;
	}

	
	/**
	 * Returns the field for a given attribute name
	 * @param className specifies the class name
	 * @param attributeName - specifies the attribute name
	 * @return
	 * @throws Exception
	 */	
	@SuppressWarnings("rawtypes")
	public Field getField(Class className, String attributeName)
			throws Exception {
		Field attribute = null;
		Field[] fields = classCache.getAllFields(className);
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(attributeName)) {
				fields[i].setAccessible(true);
				attribute = fields[i];
				break;
			}
		}
		if (attribute == null) {
			throw new Exception("Invalid field name - " + attributeName);
		}
		return attribute;
	}
	
	public Object getAttribute(Field field, String attributeName, Class rootClass) throws Exception
	{
		Object attribute = null;
		
		String fieldName = field.getClass().getName()+"."+field.getName();
		System.out.println("attributeName "+attributeName);
		System.out.println("fieldName "+fieldName);
		if(attributeName.equals(fieldName))
		{
			attribute = Class.forName(fieldName).newInstance();
			System.out.println("Equal returning "+attribute);
			return attribute;
		}
		else if(attributeName.indexOf(fieldName) == -1)
			throw new Exception("Invalid field and attribute combination.  Field: "+fieldName + " ; attribute:"+attributeName);
		
		String subAttributeName = attributeName.substring(attributeName.indexOf(fieldName)+1, attributeName.length());
		System.out.println("subAttributeName "+subAttributeName);
		
		return attribute;
	}
	
	protected ApplicationService getApplicationService() throws Exception {
		// return ApplicationServiceProvider.getApplicationService();
		return applicationService;
	}

	protected ApplicationService getApplicationService(String username,
			String password) throws Exception {
		System.out
				.println("getApplicationService(String username, String password) ------");
		Authentication auth = new UsernamePasswordAuthenticationToken(username,
				password);
		if (applicationService instanceof org.springframework.aop.framework.Advised) {
			System.out
					.println("getApplicationService(String username, String password) --aop----");
			org.springframework.aop.framework.Advised proxy = (org.springframework.aop.framework.Advised) applicationService;
			for (Advisor advisor : proxy.getAdvisors()) {
				Advice advice = advisor.getAdvice();
				if (advice instanceof ApplicationServiceProxy) {
					ApplicationServiceProxy asp = (ApplicationServiceProxy) advice;
					asp.setApplicationService(applicationService);
					asp.setAuthentication(auth);
				}
			}
		}
		return applicationService;
		// return ApplicationServiceProvider.getApplicationService(username,
		// password);
	}

	/*
	 * Save Object by calling writalbe API
	 * 
	 * @param Object
	 */
	public Object save(final Object obj) throws WebApplicationException {
		try {
			final InsertExampleQuery sdkQuery = new InsertExampleQuery(obj);
			sdkQuery.setCommit(true);
			SDKQueryResult queryResult = ((WritableApplicationService) applicationService)
					.executeQuery(sdkQuery);
			return queryResult.getObjectResult();
		} catch (ApplicationException e) {
			log.error("Error in Saving REST resource: " + e.getMessage());
			ResponseBuilder builder = Response
					.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to Save due to: " + e.getMessage()
					+ "</error>");
			throw new WebApplicationException(builder.build());
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

	/*
	 * Update Object by calling writalbe API
	 * 
	 * @param Object
	 */
	public void update(Object obj) throws WebApplicationException {
		try {
			final UpdateExampleQuery sdkQuery = new UpdateExampleQuery(obj);
			sdkQuery.setCommit(true);
			new BaseUtilWrapper() {

				@Override
				public List execute() throws Exception {
					((WritableApplicationService) applicationService)
							.executeQuery(sdkQuery);
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

	/*
	 * Delete Object by calling writalbe API
	 * 
	 * @param Object
	 */
	public void delete(Object obj) throws WebApplicationException {
		try {
			final DeleteExampleQuery sdkQuery = new DeleteExampleQuery(obj);
			sdkQuery.setCommit(true);
			new BaseUtilWrapper() {

				@Override
				public List execute() throws Exception {
					((WritableApplicationService) applicationService)
							.executeQuery(sdkQuery);
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

	/*
	 * Delete Object by calling writalbe API
	 * 
	 * @param Object
	 */
	public void delete(DeleteHQLQuery query) throws WebApplicationException {
		try {
			((WritableApplicationService) applicationService)
					.executeQuery(query);
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

	private Field getIdField(Field[] fields, String idName) throws Exception {
		Field id = null;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(idName)) {
				if (!this.locateClass(fields[i].getType().getName())) {
					id = fields[i];
					break;
				}
			}
		}
		return id;
	}

	public boolean locateClass(String className) {
		// To make sure class is not a proxy generated by CGLIB
		if (className.indexOf("$") > 1)
			className = className.substring(0, className.indexOf("$"));

		try {
			classCache.getClassFromCache(className);
		} catch (ClassNotFoundException e) {
			return false;
		}

		return true;

	}

}
