package gov.nih.nci.system.web.util;

import gov.nih.nci.system.util.ClassCache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class RESTUtil {

	public static Logger log = Logger.getLogger(RESTUtil.class.getName());

	/**
	 * Get the list of all searchable Fields for the class
	 * 
	 * @param className
	 * @return Field[] of all searchable fields for the given class
	 */
	public static List<Field> getSearchableFields(String className,
			ClassCache classCache) {
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

	public static List<Object> getSearchableIsoDataTypeFields(String className,
			String fieldName, ClassCache classCache) {
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

	/**
	 * Convert ISO attr parts into attribute names [{part_0=[value, code,
	 * codeSystem, {type=[AL]}]} resulting: part_0.value, part_0.code,
	 * part_0.codeSystem, part_0.type
	 * 
	 * @param attrName
	 * @param attrs
	 * @return
	 */
	public static void convertISOPart(String attrName, Map attrs,
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
	public static List<String> getSearchableIsoDataTypeFieldsForAd(Field field,
			List attrs) {
		String fieldName = field.getName();
		//System.out.println("fieldName: " + fieldName);
		String fieldNameWithoutU = fieldName;
		if (fieldName.indexOf("_") > 0)
			fieldNameWithoutU = fieldName.substring(0, fieldName.indexOf("_"))
					+ fieldName.substring(fieldName.indexOf("_") + 1,
							fieldName.length());
		//System.out.println("fieldNameWithoutU: " + fieldNameWithoutU);

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
				//System.out.println("keyName: " + keyName);
				String keyNameWithoutU = keyName;
				if (keyName.indexOf("_") > 0)
					keyNameWithoutU = keyName
							.substring(0, keyName.indexOf("_"))
							+ keyName.substring(keyName.indexOf("_") + 1,
									keyName.length());
				//System.out.println("keyNameWithoutU: " + keyNameWithoutU);
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
						fnAttrs.add(fieldName + "." + keyNameWithoutU + "."
								+ key);
					}
				}

			}
		}
		//System.out.println("returning Ad fnAttrs: " + fnAttrs);
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
	public static List<String> getSearchableIsoDataTypeFieldsForSc(Field field,
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
	public static List<String> getSearchableIsoDataTypeFieldsForDsetAd(
			Field field, List attrs) {

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
					// item<gov.nih.nci.iso21090.Ad>
					String keyName = (String) mapIter.next();
					String subAttrName = keyName;
					if (keyName.indexOf("<") > 0) {
						subAttrName = keyName
								.substring(0, keyName.indexOf("<"));
					}

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

									String newPartName = partAttrName;
									//System.outut.println("newPartName: "
									//		+ newPartName);
									if (newPartName.indexOf("part_") != -1) {
										newPartName = "part"
												+ partAttrName
														.substring(
																partAttrName
																		.indexOf("part_") + 5,
																partAttrName
																		.length());
										//System.out.println("newPartName: "
										//		+ newPartName);
									}

									fnAttrs.add(fieldName + "." + subAttrName
											+ "." + newPartName);
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
	public static List<String> getSearchableIsoDataTypeFieldsForCd(Field field,
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
	public static List<String> getSearchableIsoDataTypeFieldsWithFN(
			Field field, List attrs) {

		String typeName = field.getType().getName();
		//System.out.println("typeName: " + typeName);
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

	/**
	 * Returns the field for a given attribute name
	 * 
	 * @param className
	 *            specifies the class name
	 * @param attributeName
	 *            - specifies the attribute name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Field getField(Class className, String attributeName,
			ClassCache classCache) throws Exception {
		Field attribute = null;
		Field[] fields = classCache.getAllFields(className);
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(attributeName)) {
				fields[i].setAccessible(true);
				attribute = fields[i];
				break;
			}
		}
		return attribute;
	}

	public static Object getAttribute(Field field, String attributeName,
			Class rootClass) throws Exception {
		Object attribute = null;

		String fieldName = field.getDeclaringClass().getName() + "."
				+ field.getName();
		//System.out.println("attributeName " + attributeName);
		//System.out.println("fieldName " + fieldName);
		if (attributeName.equals(fieldName)) {
			attribute = Class.forName(fieldName).newInstance();
			//System.out.println("Equal returning " + attribute);
			return attribute;
		} else if (attributeName.indexOf(fieldName) == -1)
			throw new Exception(
					"Invalid field and attribute combination.  Field: "
							+ fieldName + " ; attribute:" + attributeName);

		String subAttributeName = attributeName.substring(
				attributeName.indexOf(fieldName) + 1, attributeName.length());
		//System.out.println("subAttributeName " + subAttributeName);

		return attribute;
	}

	/**
	 * Returns the method specified by the method name
	 * 
	 * @param critClass
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Method getMethod(Class critClass, String methodName) {
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
	 * 
	 * @param resultClass
	 *            - Specifies the class name
	 * @return - Returns all the methods
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Method[] getAllMethods(Class resultClass) {
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

	public static Field getIdField(Field[] fields, String idName,
			ClassCache classCache) throws Exception {
		Field id = null;
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equalsIgnoreCase(idName)) {
				if (!locateClass(fields[i].getType().getName(), classCache)) {
					id = fields[i];
					break;
				}
			}
		}
		return id;
	}

	public static boolean locateClass(String className, ClassCache classCache) {
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