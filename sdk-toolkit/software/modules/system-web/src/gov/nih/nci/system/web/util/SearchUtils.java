package gov.nih.nci.system.web.util;

import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * SearchUtils presents various methods to build and modify a hibernate criteria.
 * @author SDK Team
 */

public class SearchUtils {

	private static Logger log = Logger.getLogger(SearchUtils.class.getName());
	String isoprefix = "gov.nih.nci.iso21090.";
	private ClassCache classCache;	
	
	public SearchUtils(ClassCache classCache){
		this.classCache = classCache;
	}

	/**
	 * Returns the role name for the specified class and object
	 * @param searchClass 	Specifies a class
	 * @param criterion		Specifies an object
	 * return				Returns the role between the specified class and object
	 */
	public String getRoleName(Class searchClass, Object criterion) throws Exception{

		String criterionClassName 	= criterion.getClass().getName();

		String roleName 			= null;
		Field[] fields 				= searchClass.getDeclaredFields();

		// first, check if the super class has the association with criterion object
		if (searchClass.getSuperclass() != null)
		{
			roleName = getRoleName(searchClass.getSuperclass(), criterion);
		}

		// if the superclass has association with the criterionobject,
		// use the superclass's asscoiation as the subclass.
		if (roleName != null)
		{
			return roleName;
		}

		try{
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);

				String fieldName 	= fields[i].getName();
				String fieldType 	= fields[i].getType().getName();
				Class typeClass 	= fields[i].getType();

				if(!typeClass.isPrimitive())
				{
					if(!typeClass.isArray())
					{
						if(isCollectionType(typeClass))
						{
							String returnType = classCache.getReturnType(fields[i].getGenericType().toString());
							if ((returnType != null) && (returnType.equals(criterionClassName)))
							{
								roleName = fieldName;
								break;
							}

						} 
						else
						{
							if (fieldType.equals(criterionClassName))
							{
								roleName = fieldName;
								break;
							}
						}
					}
				}
			}
		}catch(Exception ex){
			log.error("ERROR: ", ex);
			throw ex;
		}
		return roleName;
	}

	private boolean isCollectionType(Class inputClass)
	{
		boolean flag = false;
		if (inputClass.getName().equals("java.util.Collection"))
		{
			flag = true;
		}
		else
		{
			Class[] interfaces = inputClass.getInterfaces();
			for(int i= 0; i<interfaces.length; i++)
			{
				if (interfaces[i].getName().equals("java.util.Collection"))
				{
					flag = true;
					break;
				}
			}			
		}
		return flag;
	}

	/**
	 * Returns the criteria value 
	 * @param assObject
	 * @param critObject
	 * @return
	 * @throws Exception
	 */
	public Object getCriteriaValue(Object assObject, Object critObject) throws Exception{
		if(critObject.getClass().getName().equals(assObject.getClass().getName())){
			Field[] assFields = classCache.getAllFields(assObject.getClass());
			for(int i=0; i<assFields.length; i++){                
				if(assFields[i].getName().equalsIgnoreCase("serialVersionUID")){
					continue;
				}
				try{
					if(assFields[i].get(assObject)!=null){                        
						Object value = assFields[i].get(assObject);
						Field critField = getField(critObject.getClass(), assFields[i].getName());
						if(value != null){                           
							critField.set(critObject, value);                           
						}
					}  
				}catch(Exception ex){
					log.error(ex.getMessage());
				}

			}
		}

		return critObject;
	}

	/**
	 * Generates nested search criteria
	 * @param packageName
	 * @param criteriaList
	 * @return
	 * @throws Exception
	 */
	public Object buildSearchCriteria(String packageName, List<String> criteriaList) throws Exception{
		Object criteriaObject = null;
		Object assObject = null;
		int counter = criteriaList.size();
		try {
			if (criteriaList.size() > 1) {
				criteriaObject = getCriteria((String) criteriaList.get(counter - 1), packageName);
				for (int i = counter - 2; i >= 0; i--) {
					assObject = criteriaObject;
					String critString = (String) criteriaList.get(i);
					log.debug("Crit string: " + critString);
					criteriaObject = getCriteria(critString, packageName);
					if (criteriaObject.getClass().getName().equals(assObject.getClass().getName())) {
						criteriaObject = getCriteriaValue(criteriaObject,assObject);
					} else {
						Method method = null;
						try {
							method = getRoleMethod(criteriaObject, assObject);
						} catch (Exception ex) {
							throw new Exception(critString+ " is not an association of "+ criteriaObject.getClass().getName());
						}

						if (method != null) {
							Class[] types = method.getParameterTypes();
							if (types.length > 0) {
								if (types[0].getName().endsWith("Collection")) {
									List<Object> assObjectList = new ArrayList<Object>();
									assObjectList.add(assObject);
									method.invoke(criteriaObject,new Object[] { assObjectList });
								} else {
									method.invoke(criteriaObject,new Object[] { assObject });
								}
							}
						}
					}
				}
			} else if (criteriaList.size() == 1) {
				criteriaObject = getCriteria((String) criteriaList.get(0),packageName);
			} else {
				throw new Exception("Criteria not defined");
			}
		} catch (Exception ex) {
			log.error("ERROR : " + ex.getMessage());
			throw new Exception("ERROR :  " + ex.getMessage());
		}
		return criteriaObject;
	}

	/**
	 * Generates Search Criteria
	 * @param criteriaString
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	private Object getCriteria(String criteriaString, String packageName) throws Exception{

		Object critObject = null;
		try{
			String critClassName = null;
			if(criteriaString.indexOf(SystemConstant.LEFT_BRACKET)>0){
				critClassName = criteriaString.substring(0,criteriaString.indexOf(SystemConstant.LEFT_BRACKET));
			}
			else{
				critClassName = criteriaString;
			}

			if(critClassName.indexOf(SystemConstant.DOT)>0){
				critObject = Class.forName(critClassName).newInstance();
			}
			else{
				critObject = Class.forName(packageName +SystemConstant.DOT+critClassName).newInstance();
			}

			String attString = null;
			List attList = new ArrayList();
			if(criteriaString.indexOf(SystemConstant.LEFT_BRACKET)>=0){
				attString = criteriaString.substring(criteriaString.indexOf(SystemConstant.LEFT_BRACKET),criteriaString.lastIndexOf(SystemConstant.RIGHT_BRACKET)+1);
			}

			if(attString!= null){
				attList = getAttributeCollection(attString);
			}

			for(int i=0; i<attList.size(); i++){
				String att = (String)attList.get(i);               
				critObject = getAttributeCriteria(att, critObject, packageName);
			}
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}

		return critObject;
	}

	/**
	 * Returns the get method  for the given association
	 * @param criteriaObject
	 * @param assObject
	 * @return
	 * @throws Exception
	 */
	private Method getRoleMethod(Object criteriaObject, Object assObject) throws Exception{
		Method roleMethod = null;
		String roleName = getRoleName(criteriaObject.getClass(), assObject);
		if(roleName != null){
			String setMethod = "set"+roleName.substring(0,1).toUpperCase() + roleName.substring(1);
			roleMethod = getMethod(criteriaObject.getClass(), setMethod);
		}
		return roleMethod;
	}

	/**
	 * Returns the method specified by the method name
	 * @param critClass
	 * @param methodName
	 * @return
	 */
	private Method getMethod(Class critClass, String methodName){
		Method[] methods = getAllMethods(critClass);
		Method method = null;
		for(int i=0; i<methods.length; i++){
			if(methods[i].getName().equalsIgnoreCase(methodName)){
				method = methods[i];            
				break;
			}
		}
		return method;
	}

	/**
	 * Returns list of search attributes
	 * @param attString
	 * @return
	 * @throws Exception
	 */
	private List getAttributeCollection(String attString) throws Exception {
		List<String> attList = new ArrayList<String>();
		int startCounter = 0;
		int startIndex = 0;
		int endCounter = 0;
		//left;right brackets validation
		for (int i = 0; i < attString.length(); i++) {
			if (attString.charAt(i) == SystemConstant.LEFT_BRACKET) {
				startCounter++;
			} else if (attString.charAt(i) == SystemConstant.RIGHT_BRACKET) {
				endCounter++;
			}
		}
		if (startCounter != endCounter) {
			throw new Exception(
					"Invalid format: '[' parenthesis does not match number of ']' parenthesis");
		}
		
		try {
			if (attString.indexOf("][") < 1) {
				String att = attString.substring(1, attString.lastIndexOf(SystemConstant.RIGHT_BRACKET));
				attList.add(att);
			} else {
				if (attString.charAt(0) == SystemConstant.LEFT_BRACKET) {
					startCounter = 1;
					endCounter = 0;
					startIndex = 1;
				} else {
					throw new Exception("Invalid Query format " + attString);
				}
				int count = attString.length();
				for (int i = 1; i < count; i++) {
					if (attString.charAt(i) == SystemConstant.RIGHT_BRACKET) {
						endCounter++;
						if (startCounter == endCounter) {
							String att = attString.substring(startIndex, i);
							attList.add(att);
							startIndex = i + 2;
							if (startIndex < count) {
								startCounter = 0;
								endCounter = 0;
							}
						}
					} else if (attString.charAt(i) == SystemConstant.LEFT_BRACKET) {
						startCounter++;
					}
				}
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
		return attList;
	}


	/**
	 * Generates a criteria object
	 * @param att - specifies the attribute
	 * @param critObject - specifies the criteria 
	 * @param packageName - specifies the package name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private Object getAttributeCriteria(String att, Object critObject, String packageName)throws Exception{

		try{
			String attRole = null;
			//@id=3 example-project
			//@id=[@extension=1] iso-example
			//Deck[@id=[@extension=1]] --- nested criteria multiple params
			boolean condition1 = att.indexOf(Character.toString(SystemConstant.EQUAL)+Character.toString(SystemConstant.LEFT_BRACKET))>0;
			boolean condition2 = att.indexOf(SystemConstant.LEFT_BRACKET)>1;
			boolean isISOProjectWithRole=att.indexOf("@")>0;
			boolean isExampleProjectWithRole = !condition1 && condition2;

			if (isExampleProjectWithRole || isISOProjectWithRole) {
				attRole = att.substring(0, att.indexOf(SystemConstant.LEFT_BRACKET));
			}
			if(attRole == null){
					critObject= getCriteriaObject(att,critObject);
			}
			else{
				String roleClassName = getRoleClassName(attRole);
				if(roleClassName.indexOf(SystemConstant.DOT)<0){
					roleClassName = packageName +SystemConstant.DOT + roleClassName;                            
				}                
				Method roleMethod = null;
				String methodName = attRole.substring(0,1).toUpperCase() + attRole.substring(1);
				roleMethod = getMethod(critObject.getClass(), "set" + methodName);
				Object roleObject = Class.forName(roleClassName).newInstance();
				List<Object> roleClassCollection = new ArrayList<Object>();
				int count = 0;
				for(int i=0; i< att.length(); i++){
					if(att.charAt(i)==SystemConstant.LEFT_BRACKET){
						count++;
					}
				}
				if(count>1){   
					//att= Deck[@id=[@extension=1]]
					//attr= [@id=[@extension=1]]
					List attList = getAttributeCollection(att.substring(att.indexOf(SystemConstant.LEFT_BRACKET)));
					for(int i=0; i<attList.size(); i++){
						String critAtt = (String)attList.get(i);  						
						roleObject=getCriteriaObject(critAtt, roleObject);
						if(roleObject != null && attRole.indexOf("Collection")>0){
							roleClassCollection.add(roleObject);
						}
					}
				}else{					
					String critAttr = att.substring(att.indexOf(SystemConstant.AT), att.indexOf(SystemConstant.RIGHT_BRACKET));
					roleObject=getCriteriaObject(critAttr, roleObject);
					if(attRole.indexOf("Collection")>0){
						roleClassCollection.add(roleObject);
					}					
				}
				if(attRole.indexOf("Collection")<1 && roleObject != null){                    
					roleMethod.invoke(critObject, new Object[]{roleObject});                        
				}else if(roleClassCollection.size()>0){                    
					try{
						Class types[] = roleMethod.getParameterTypes();
						if(types[0] != null){
							if(types[0].getName().endsWith("Vector")){
								Vector vector = new Vector();
								for(int i=0; i<roleClassCollection.size(); i++){
									vector.add(roleClassCollection.get(i));
								} 
								roleMethod.invoke(critObject, new Object[] {vector});
							}else{
								roleMethod.invoke(critObject, new Object[] {roleClassCollection});
							}
						}else{
							throw new Exception("Invalid arguments passed over to method : "+ roleMethod);
						}
					}catch(Exception ex){
						 log.error("Exception: ", ex);
						throw new Exception("Cannot invoke method - " + roleMethod.getName());
					}
				}else{
					throw new Exception("Unable to generate search criteria");
				}
			}
		}catch(Exception ex){
			 log.error("Exception: ", ex);
			throw ex;
		}
		return critObject;
	}	

	private String[] splitQueryCriteria(String queryCriteria) {
		ArrayList<String> subCriteria = new ArrayList<String>();
		char[] characters = queryCriteria.toCharArray();
		int count = 0;
		int lastIndex = 0;
		for (int i = 0; i < characters.length; i++) {
			if (characters[i] == '[')
				count++;
			if (characters[i] == ']')
				count--;
			if (count == 0) {
				String temp = queryCriteria.substring(lastIndex, i + 1);
				lastIndex = i + 1;
				subCriteria.add(temp);
			}
		}
		String temp[] = new String[subCriteria.size()];
		subCriteria.toArray(temp);
		return temp;
	}

	@SuppressWarnings("unchecked")
	private void processQueryCriteria(String queryCriteria,Object rootObject,StringBuffer tempISOParamType) throws Exception{
		String[] splitCriteria = splitQueryCriteria(queryCriteria);		
		for (String criteria : splitCriteria) {
			String temp = criteria;
			if (temp.startsWith("[@")) {
				temp = temp.substring(2);
			}
			if (temp.endsWith("]")) {
				temp = temp.substring(0, temp.length() - 1);
			}
			String attrName = temp.substring(0, temp.indexOf('='));
			String value = temp.substring(temp.indexOf('=') + 1);
			if (value.indexOf('[') >= 0) {
				Object tempObject2=createObject(rootObject, attrName,null,tempISOParamType);
				Method m=getAttributeGetMethodName(tempObject2, attrName);
				Object tempObject=m.invoke(tempObject2);
				Class klass=tempObject.getClass();
				if (klass.isAssignableFrom(java.util.HashSet.class)) {
					Set set = (Set) tempObject;
					if("".equals(tempISOParamType.toString()) ){
						throw new Exception("Invalid Query Criteria ");
					}
					tempObject=Class.forName(tempISOParamType.toString().trim()).newInstance();
					set.add(tempObject);					
				}
				processQueryCriteria(value, tempObject, tempISOParamType);
			}else{
				createObject(rootObject, attrName,value,tempISOParamType);
			}
		}
	}
	
	private Object getCriteriaObject(String att,Object rootObject) throws Exception{
		StringBuffer tempISOParamType = new StringBuffer();
		processQueryCriteria("["+att+"]",rootObject,tempISOParamType);
		return rootObject;
	}
	
	private Object createObject(Object childObject, String attribute,
			String attributeValue,StringBuffer tempISOParamType) throws Exception{
		Field field = getField(childObject.getClass(), attribute);
		Method attMethod = getAttributeSetMethodName(childObject, attribute);
		Object value = null;
		String fieldName = field.getType().getName();
		
		if(field.getType().isEnum()){
			value = getFieldValue(field, attributeValue);
		}else if (fieldName.startsWith(isoprefix)) {
			Method getterMethod = getAttributeGetMethodName(childObject,attribute);
			value = getterMethod.invoke(childObject);
			if (value == null) {
				Type[] genericParameterTypes = attMethod.getGenericParameterTypes();
				value = getFieldTypeObject(genericParameterTypes,field,tempISOParamType); 
			}
		} else if (field.getType().getName().startsWith("java.util.Set")) {
			Method getterMethod = getAttributeGetMethodName(childObject,attribute);
			value = getterMethod.invoke(childObject);
			if (value == null) {
				value=new HashSet();
			}
		} else {
			value = getFieldValue(field, attributeValue);
		}
		try {
			attMethod.invoke(childObject, new Object[] { value });
		} catch (Exception e) {
		    log.error("Exception: ", e);
		    throw e;
		}		
		return childObject;
	}

	private Object getFieldTypeObject(Type[] genericParameterTypes,Field field,StringBuffer classISOParamType) throws Exception{				
		Object fieldTypeObject=null;				
		for(Type genericParameterType : genericParameterTypes){
			if (genericParameterType instanceof TypeVariable<?>) {
				fieldTypeObject = Class.forName(classISOParamType.toString()).newInstance();				
			}else if(genericParameterType instanceof ParameterizedType){
			    ParameterizedType pType = (ParameterizedType) genericParameterType;
			    String paramString = pType.toString();
			    int beginIndex=paramString.indexOf("<");
			    int lastIndex=paramString.indexOf(">");
			    String isoParameter = paramString.substring(beginIndex+1,lastIndex);
				classISOParamType.append(isoParameter);

				int index=paramString.indexOf('<');
				fieldTypeObject = Class.forName(paramString.substring(0,index)).newInstance();
			}else{
				String fieldName = field.getType().getName();
				boolean isClassISOParamTS = fieldName.equals("gov.nih.nci.iso21090.Qty") && classISOParamType.toString().equals("gov.nih.nci.iso21090.Ts");
				boolean isClassISOParamPQ = fieldName.equals("gov.nih.nci.iso21090.Qty") && classISOParamType.toString().equals("gov.nih.nci.iso21090.Pq");
				boolean isClassISOParamReal = fieldName.equals("gov.nih.nci.iso21090.Qty") && classISOParamType.toString().equals("gov.nih.nci.iso21090.Real");
				boolean isClassISOParamInt = fieldName.equals("gov.nih.nci.iso21090.Qty") && classISOParamType.toString().equals("gov.nih.nci.iso21090.Int");
				if (isClassISOParamTS || isClassISOParamPQ) {
					fieldName = "gov.nih.nci.iso21090.Pq";
				} else if (isClassISOParamReal || isClassISOParamInt) {
					fieldName = classISOParamType.toString();
				}
				fieldTypeObject=Class.forName(fieldName).newInstance();
			}
			break;
		}
		return fieldTypeObject;
	}

	private Method getAttributeGetMethodName(Object attObject, String attName){    
		Method m = getMethod(attObject.getClass(), "get"+ attName.substring(0,1).toUpperCase() + attName.substring(1));
		return m;
	}

	/**
	 * Returns the field for a given attribute name
	 * @param className specifies the class name
	 * @param attributeName - specifies the attribute name
	 * @return
	 * @throws Exception
	 */
	public Field getField(Class className, String attributeName)throws Exception {  
		Field attribute = null;
		Field[] fields = classCache.getAllFields(className);
		for(int i=0; i<fields.length; i++){           
			if(fields[i].getName().equalsIgnoreCase(attributeName)){
				fields[i].setAccessible(true);
				attribute = fields[i];
				break;
			}
		}
		if(attribute == null){
			throw new Exception ("Invalid field name - "+ attributeName);
		}
		return attribute;
	}

	/**
	 * Returns a Method for a given attribute in the specified class
	 * @param attObject 
	 * @param attName
	 * @return
	 */
	private Method getAttributeSetMethodName(Object attObject, String attName){    
		Method m = getMethod(attObject.getClass(), "set"+ attName.substring(0,1).toUpperCase() + attName.substring(1));
		return m;
	}

	/**
	 * Returns a value for the specified field
	 * @param field
	 * @param attValue
	 * @return
	 * @throws Exception
	 */
	private Object getFieldValue(Field field, String attValue) throws Exception{       
		Object value = null;
		if(field.getType().getName().equalsIgnoreCase("java.lang.String")){
			value = attValue;
		}
		else{
			value = convertValues(field, attValue);
		}
		return value;
	}

	/**
	 * Converts the specified value to the field class type
	 * @param field  Specifies the field
	 * @param value  Specifies the values that needs to be stored
	 * @return  returns an object with the new value
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object convertValues(Field field, Object value) throws Exception {
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
					convertedValue = new URI((String)value);
				}
			} else if (field.getType().isEnum()) {
				if (valueType.equals("java.lang.String")) {
					Class enumKlass=Class.forName(fieldType);
					convertedValue = Enum.valueOf(enumKlass, (String)value);
				}
			} else {
				throw new Exception("type mismatch - " + valueType);
			}

		} catch (Exception ex) {
			String msg = "type mismatch " + field.getName() + " is of type - "
					+ fieldType + " \n " + ex.getMessage();
			log.error("ERROR : " + msg);
			throw new Exception(msg);
		}

		return convertedValue;
	}

	/**
	 * Returns the class name for a given role
	 * @param attRole
	 * @return
	 */
	public String getRoleClassName(String attRole){

		String attClassName = null;
		if(attRole.indexOf("Collection")>0){
			attClassName = attRole.substring(0,1).toUpperCase() + attRole.substring(1,attRole.indexOf("Collection"));
		}else{
			attClassName = attRole.substring(0,1).toUpperCase() + attRole.substring(1);
		}        
		return attClassName;
	}

	/**
	 * Gets all the methods for a given class
	 * @param resultClass - Specifies the class name
	 * @return - Returns all the methods
	 */
	public Method[] getAllMethods(Class resultClass){

		List<Method> methodList = new ArrayList<Method>();
		try{
			while(resultClass != null && !resultClass.isInterface() && !resultClass.isPrimitive()){
				Method[] method = resultClass.getDeclaredMethods();
				for (int i = 0; i < method.length; i++) {
					method[i].setAccessible(true);
					methodList.add(method[i]);
				}

				if(!resultClass.getSuperclass().getName().equalsIgnoreCase("java.lang.Object")){
					resultClass = resultClass.getSuperclass();
				}
				else{
					break;
				}
			}
		}catch(Exception ex){
			log.error("ERROR: " + ex.getMessage());
		}

		Method[] methods = new Method[methodList.size()];
		for(int i=0;i<methodList.size(); i++){
			methods[i]= (Method)methodList.get(i);
		}
		return methods;
	}

	/**
	 * Returns the target class name for the specified class and role names
	 * @param className specifies the class name
	 * @param roleName specifies role name
	 * @return
	 * @throws Exception
	 */
	public String getTargetClassName(String className, String roleName) throws Exception{	
		return classCache.getReturnType(className, roleName);
	}
}