package gov.nih.nci.common.util;


//import org.hibernate.criterion.*;
//import org.hibernate.impl.*;
//import org.hibernate.impl.*;
//
//import org.hibernate.*;

//import net.sf.hibernate.expression.*;
//import net.sf.hibernate.*;
//import net.sf.hibernate.impl.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.*;

import org.apache.log4j.*;
/**
  * <!-- LICENSE_TEXT_START -->
The caBIO Software License, Version 3.1 Copyright 2001-2006 Science Applications International Corporation (SAIC)  
Copyright Notice.  The software subject to this notice and license includes both human readable source code form and machine readable, binary, object code form (the caBIO Software).  The caBIO Software was developed in conjunction with the National Cancer Institute (NCI) by NCI employees and employees of SAIC.  To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.    
This caBIO Software License (the License) is between NCI and You.  You (or Your) shall mean a person or an entity, and all other entities that control, are controlled by, or are under common control with the entity.  Control for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity, whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) beneficial ownership of such entity.  
This License is granted provided that You agree to the conditions described below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights in the caBIO Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly display, publicly perform, and prepare derivative works of the caBIO Software; (ii) distribute and have distributed to and by third parties the caBIO Software and any modifications and derivative works thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such rights to further third parties.  For sake of clarity, and not by way of limitation, NCI shall have no right of accounting or right of payment from You or Your sublicensees for the rights granted under this License.  This License is granted at no charge to You.
1.	Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions and the disclaimer and limitation of liability of Article 6, below.  Your redistributions in object code form must reproduce the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials provided with the distribution, if any.
2.	Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: This product includes software developed by SAIC and the National Cancer Institute.  If You do not include such end-user documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments normally appear.
3.	You may not use the names "The National Cancer Institute", "NCI" Science Applications International Corporation and "SAIC" to endorse or promote products derived from this Software.  This License does not authorize You to use any trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with the terms of this License.
4.	For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and into any third party proprietary programs.  However, if You incorporate the Software into third party proprietary programs, You agree that You are solely responsible for obtaining any permission from such third parties required to incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including without limitation Your end-users, of their obligation to secure any required permissions from such third parties before incorporating the Software into such third party proprietary software programs.  In the event that You fail to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to the extent prohibited by law, resulting from Your failure to obtain such permissions.
5.	For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, and distribution of the Work otherwise complies with the conditions stated in this License.
6.	THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

  * <!-- LICENSE_TEXT_END -->
 */
/**
 * SearchUtils presents various methods to build and modify a hibernate criteria.
 * @author caBIO Team
 * @version 1.0
 */

public class SearchUtils {
    
    private static Logger log= Logger.getLogger(SearchUtils.class.getName());
    
	private static Properties roleLookupProp;
	 
	{
		try{	
			
			roleLookupProp = RoleLookupProperties.getInstance().getProperties();
		}
		catch(Exception e)
		{
		    log.error("IOException: " + e.getMessage());
			System.out.println("Could not find roleLookup.properties ");
		}
	}

/**
 	* Returns the role name for the specified class and object
 	* @param searchClass 	Specifies a class
 	* @param criterion		Specifies an object
 	* return				Returns the role between the specified class and object
 */
    public String getRoleName(Class searchClass, Object criterion) throws Exception{

	   		 		String searchClassName 		= searchClass.getName();
			        String searchBeanName		= searchClassName.substring(searchClassName.lastIndexOf(Constant.DOT)+1);
			        String criterionClassName 	= criterion.getClass().getName();
			        String criterionBeanName	= criterionClassName.substring(criterionClassName.lastIndexOf(Constant.DOT)+1);

			        String roleName 			= null;
			        Field[] fields 				= searchClass.getDeclaredFields();

			        // first, check if the super class has the association with criterion object
			        if (searchClass.getSuperclass() != null)
			        {
			        	Class superClass = searchClass.getSuperclass();
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

			                    String match = null;
//			                    if(criterionBeanName.indexOf("Impl")>0){
//					    		    match = criterionBeanName.substring(0,criterionBeanName.indexOf("Impl"));
//					    		}
//					    	    else{
					    	        match = criterionBeanName;
//					    	    }

			                    if(!typeClass.isPrimitive())
			                    {
			                        if(!typeClass.isArray())
			                        {
			                        	if(isCollectionType(typeClass))
			                        	{
			                        		String returnType = roleLookupProp.getProperty(fieldName+searchClass.getName());
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

//	private String getFullyQualifiedObjectName(String targetName)
//	{
//		if (targetName.indexOf("impl") < 0)
//		{
//			String packageName = targetName.substring(0, targetName.lastIndexOf(Constant.DOT))+".impl.";
//			String beanName = targetName.substring(targetName.lastIndexOf(Constant.DOT)+1)+"Impl";
//			String newName = packageName + beanName;
//			return newName;
//		}
//		else
//		{
//			return targetName;
//		}
//	}

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
 * Generates nested search criteria
 * @param packageName
 * @param criteriaList
 * @return
 * @throws Exception
 */    
    private Object buildCriteria(String packageName, List criteriaList) throws Exception{


       Object criteriaObject = null;

        try{
            criteriaObject = getCriteria((String)criteriaList.get(0),packageName);
            if(criteriaList.size()>0){
                for(int i=1; i<criteriaList.size(); i++){
                    String critString = (String)criteriaList.get(i);                    
                    Object assObject = getCriteria(critString, packageName);
                    if(criteriaObject.getClass().getName().equals(assObject.getClass().getName())){
                        throw new Exception( critString +" is not an association of "+ criteriaObject.getClass().getName());
                    }
                    Method method = getRoleMethod(criteriaObject, assObject);                   
                     if(method != null){
                            method.invoke(criteriaObject, new Object[]{assObject});
                            }
                        }


                    }

            }catch(Exception ex){
                log.error("ERROR : "+ ex.getMessage());
                throw new Exception("ERROR : "+ ex.getMessage());
            }

       return criteriaObject;
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
            Field[] assFields = getAllFields(assObject.getClass());
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
    public Object buildSearchCriteria(String packageName, List criteriaList) throws Exception{

        Object criteriaObject = null;
        Object assObject = null;
        int counter = criteriaList.size();
         try{

             if(criteriaList.size()>1){
                 criteriaObject = getCriteria((String)criteriaList.get(counter-1),packageName);
                 for(int i=counter-2; i>=0; i--){
                     assObject = criteriaObject;
                     String critString = (String)criteriaList.get(i);
                     System.out.println("Crit string: "+ critString);
                     criteriaObject= getCriteria(critString, packageName);
                     if(criteriaObject.getClass().getName().equals(assObject.getClass().getName())){
                         criteriaObject = getCriteriaValue(criteriaObject, assObject);                         
                     }
                     else{
                         Method method = null;
                         try{
                             method = getRoleMethod(criteriaObject, assObject);
                         }catch(Exception ex){
                             throw new Exception( critString +" is not an association of "+ criteriaObject.getClass().getName());
                         }
                         
                         
                         if(method != null){                          
                             Class[] types = method.getParameterTypes();
                             if(types.length > 0){
                                 if(types[0].getName().endsWith("Collection")){                                  
                                     List assObjectList = new ArrayList();
                                     assObjectList.add(assObject);
                                     method.invoke(criteriaObject, new Object[]{assObjectList});
                                 }
                                 else{
                                     method.invoke(criteriaObject, new Object[]{assObject});
                                 }
                             }
                               
                            }
                     }
                     
                       }

                     }
             else if(criteriaList.size()==1){
                 criteriaObject = getCriteria((String)criteriaList.get(0),packageName);
             }
             else{
                 throw new Exception("Criteria not defined");
             }

            }catch(Exception ex){
                log.error("ERROR : "+ ex.getMessage());
                throw new Exception("ERROR :  "+ ex.getMessage());
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
            if(criteriaString.indexOf(Constant.LEFT_BRACKET)>0){
                critClassName = criteriaString.substring(0,criteriaString.indexOf(Constant.LEFT_BRACKET));
            }
            else{
                critClassName = criteriaString;
            }
            
            if(critClassName.indexOf(Constant.DOT)>0){
                critObject = Class.forName(critClassName).newInstance();
            }
            else{
                critObject = Class.forName(packageName +Constant.DOT+critClassName).newInstance();
            }
            
            String attString = null;
            List attList = new ArrayList();
            if(criteriaString.indexOf(Constant.LEFT_BRACKET)>=0){
                attString = criteriaString.substring(criteriaString.indexOf(Constant.LEFT_BRACKET),criteriaString.lastIndexOf(Constant.RIGHT_BRACKET)+1);
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
    private List getAttributeCollection(String attString) throws Exception{
        
        List attList = new ArrayList();
        int startCounter =0;
        int startIndex =0;
        int endCounter =0;
        for(int i=0; i<attString.length(); i++){
            if(attString.charAt(i)==Constant.LEFT_BRACKET){
                startCounter++;
            }
            else if(attString.charAt(i)==Constant.RIGHT_BRACKET){
                endCounter++;
            }
        }
        if(startCounter != endCounter){
            throw new Exception("Invalid format: '[' parenthesis does not match number of ']' parenthesis");
        }
        try{
            if(attString.indexOf("][")<1){
                String att = attString.substring(1,attString.lastIndexOf(Constant.RIGHT_BRACKET));           
                attList.add(att);
            }
            else{
                if(attString.charAt(0)==Constant.LEFT_BRACKET){                
                    startCounter = 1;
                    endCounter =0;
                    startIndex = 1;
                }
                else{
                    throw new Exception("Invalid Query format " + attString);
                }

                int count = attString.length();
                for(int i=1; i<count;i++){                
                    if(attString.charAt(i)==Constant.RIGHT_BRACKET){
                        endCounter++;
                        if(startCounter == endCounter){                        
                            String att = attString.substring(startIndex,i);
                            attList.add(att);                        
                            startIndex = i+2;
                            if(startIndex < count){
                                startCounter = 0;
                                endCounter =0;
                            }

                        }
                    }
                    else if(attString.charAt(i)==Constant.LEFT_BRACKET){
                        startCounter++;
                    }                
                }
            }

        }catch(Exception ex){
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
    private Object getAttributeCriteria(String att, Object critObject, String packageName)throws Exception{
        
        try{
            String attRole = null;
            if(att.indexOf(Constant.LEFT_BRACKET)>1){
                attRole = att.substring(0,att.indexOf(Constant.LEFT_BRACKET));    
            }

            Method critAttMethod = null;
                if(attRole == null){
                    String attName = att.substring(att.indexOf(Constant.AT)+1, att.indexOf(Constant.EQUAL));
                    String attValue = att.substring(att.indexOf(Constant.EQUAL)+1);
                    Field critField = getField(critObject.getClass(), attName);
                    critAttMethod = getAttributeSetMethodName(critObject, attName);                
                    Object value = getFieldValue(critField, attValue);
                    if( critAttMethod!= null){                    
                        critAttMethod.invoke(critObject,new Object[]{value});
                        }                
                }
                else{
                    String ontologyRole = null;
                    List attValueList = new ArrayList();
                    if(attRole.indexOf("Ontology")>0 && (attRole.startsWith("parent")|| attRole.startsWith("child"))){
                        Object ontologyObject = buildOntology(att, packageName);
                        Method ontologyMethod = getMethod(critObject.getClass(), "set" + attRole.substring(0,1).toUpperCase() + attRole.substring(1));
                        
                        if(ontologyMethod != null){
                            if(attRole.endsWith("Collection")){
                                List ontologyList = new ArrayList();
                                ontologyList.add(ontologyObject);
                                ontologyMethod.invoke(critObject,new Object[]{ontologyList});                                                               
                            }
                            else{
                                ontologyMethod.invoke(critObject,new Object[]{ontologyObject});
                            }
                        }                                           
                    }
                    else{
                        String roleClassName = getRoleClassName(attRole);

                        if(roleClassName.indexOf(Constant.DOT)<0){
                            roleClassName = packageName +Constant.DOT + roleClassName;                            
                        }                
                        Method roleMethod = null;
                        if(ontologyRole != null){                    
                            roleMethod = getMethod(critObject.getClass(), "set"+ attRole.substring(0,1).toUpperCase() + attRole.substring(1));
                            }
                        else{
                            roleMethod = getMethod(critObject.getClass(), "set"+ attRole.substring(0,1).toUpperCase() + attRole.substring(1));
                            }

                        Object roleObject = Class.forName(roleClassName).newInstance();
                        List roleClassCollection = new ArrayList();
                        int count = 0;
                        for(int i=0; i< att.length(); i++){
                            if(att.charAt(i)==Constant.LEFT_BRACKET){
                                count++;
                            }
                        }
                        if(count>1){                    
                            List attList = getAttributeCollection(att.substring(att.indexOf(Constant.LEFT_BRACKET)));
                            for(int i=0; i<attList.size(); i++){
                                String critAtt = (String)attList.get(i);                        
                                String attName = critAtt.substring(1, critAtt.indexOf(Constant.EQUAL));
                                String attValue = critAtt.substring(critAtt.indexOf(Constant.EQUAL)+1);
                                Field roleAttField = getField(Class.forName(roleClassName), attName);
                                Method roleAttMethod = getAttributeSetMethodName(Class.forName(roleClassName).newInstance(), attName);
                                if(attValue.indexOf(Constant.COMMA)<1){
                                    if(roleMethod != null){
                                        try{
                                        Object value = new Object();
                                        value = getFieldValue(roleAttField, attValue);
                                        roleAttMethod.invoke(roleObject,new Object[]{getFieldValue(roleAttField, attValue)});
                                        }catch(Exception e){
                                            throw new Exception("Unable to set value for " + attName +" - "+e.getMessage());
                                        }
                                    }
                                }

                                if(roleObject != null && attRole.indexOf("Collection")>0){
                                    roleClassCollection.add(roleObject);
                                }

                            }
                        }
                        else{
                            String attName = att.substring(att.indexOf(Constant.AT)+1, att.indexOf(Constant.EQUAL));
                            String attValue = att.substring(att.indexOf(Constant.EQUAL)+1, att.indexOf(Constant.RIGHT_BRACKET));
                            Field roleAttField = getField(roleObject.getClass(), attName);
                            Method roleAttMethod = getAttributeSetMethodName(roleObject, attName);
                            if(attRole.indexOf("Collection")>0){
                                Object value = getFieldValue(roleAttField,attValue);
                                roleAttMethod.invoke(roleObject,new Object[]{value});
                                roleClassCollection.add(roleObject);
                            }
                            else{                        
                                Object value = getFieldValue(roleAttField, attValue);
                                roleAttMethod.invoke(roleObject,new Object[]{value});
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
                                    }
                                    else{
                                        roleMethod.invoke(critObject, new Object[] {roleClassCollection});
                                    }
                                }
                                else{
                                    throw new Exception("Invalid arguments passed over to method : "+ roleMethod);
                                }
                                
                            }catch(Exception ex){
                                throw new Exception("Cannot invoke method - " + roleMethod.getName());
                            }
                        }else{
                            throw new Exception("Unable to generate search criteria");
                        }

                    }

               }

            }catch(Exception ex){
            throw new Exception(ex.getMessage());
            }
        return critObject;
        }

    /**
     * Returns the field for a given attribute name
     * @param className specifies the class name
     * @param attributeName - specifies the attribute name
     * @return
     * @throws Exception
     */
    public Field getField(Class className, String attributeName)throws Exception{  
        Field attribute = null;
            Field[] fields = getAllFields(className);
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
    public Object convertValues(Field field, Object value) throws Exception{
    String fieldType = field.getType().getName();
    String valueType = value.getClass().getName();
    Object convertedValue = null;
    try{
        if(fieldType.equals("java.lang.Long")){
            if(valueType.equals("java.lang.String")){
                convertedValue = new Long((String)value);
                }
            }
        else if(fieldType.equals("java.lang.Integer")){
            if(valueType.equals("java.lang.String")){
                convertedValue = new Integer((String)value);
                }
            }
        else if(fieldType.equals("java.lang.Float")){
            if(valueType.equals("java.lang.String")){
                convertedValue = new Float((String)value);
                }
            }
        else if(fieldType.equals("java.lang.Double")){
            if(valueType.equals("java.lang.String")){
                convertedValue = new Double((String)value);
                }
            }
        else if(fieldType.equals("java.lang.Boolean")){
            if(valueType.equals("java.lang.String")){
                convertedValue = new Boolean((String)value);
                }
            }
        else if(fieldType.equals("java.util.Date")){
            SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
            if(valueType.equals("java.lang.String")){
                convertedValue = format.parse((String)value);
                }
        }
        else{
            throw new Exception("type mismatch - "+valueType);
            }


    }catch(Exception ex){
        String msg = "type mismatch " + field.getName() + " is of type - "+ fieldType + " \n "+ ex.getMessage();
        log.error("ERROR : "+ msg);
        throw new Exception(msg);
        }

    return convertedValue;
    }

    /**
     * Returns a class name for a given role
     * @param attRole
     * @return
     */
    public String getOntologyRoleName (String attRole){    
        String ontologyRole = null;
        if(attRole.startsWith("child")){
            ontologyRole = attRole.substring("child".length());
            }
        else if(attRole.startsWith("parent")){
            ontologyRole = attRole.substring("parent".length());
            }
        if(ontologyRole != null){
            if(ontologyRole.indexOf("Relationship")>=0){
                ontologyRole = ontologyRole.substring(0, ontologyRole.indexOf("Relationship"));
                }
            else if(ontologyRole.endsWith("Collection")){
                ontologyRole = ontologyRole.substring(0, ontologyRole.indexOf("Collection"));
                }
        }

        return ontologyRole;
    }

    /**
     * Returns the class name for a given role
     * @param attRole
     * @return
     */
    public String getRoleClassName(String attRole){
        
        String attClassName = null;
        if(attRole.indexOf("Ontology")>=0 && (attRole.startsWith("child")|| attRole.startsWith("parent"))){        
            attClassName  = getOntologyRoleName(attRole);
            }
        else if(attRole.indexOf("Collection")>0){
            attClassName = attRole.substring(0,1).toUpperCase() + attRole.substring(1,attRole.indexOf("Collection"));
        }else{
            attClassName = attRole.substring(0,1).toUpperCase() + attRole.substring(1);
        }        
      return attClassName;
    }

  
    /**
     * Gets all the fields for a given class
     * @param resultClass - Specifies the class name
     * @return - returns all the fields of a class
     */
    public Field[] getAllFields(Class resultClass){
        List fieldList = new ArrayList();
        try{

        while(resultClass != null && !resultClass.isInterface() && !resultClass.isPrimitive()){
            Field[] fields = resultClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true);
                        String fieldName = fields[i].getName();
                        fieldList.add(fields[i]);
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

        Field[] fields = new Field[fieldList.size()];
        for(int i=0;i<fieldList.size(); i++){
            fields[i]= (Field)fieldList.get(i);
            }
            return fields;
        }

    /**
     * Gets all the methods for a given class
     * @param resultClass - Specifies the class name
     * @return - Returns all the methods
     */


    public Method[] getAllMethods(Class resultClass){

        List methodList = new ArrayList();
        try{
        while(resultClass != null && !resultClass.isInterface() && !resultClass.isPrimitive()){
            Method[] method = resultClass.getDeclaredMethods();
            for (int i = 0; i < method.length; i++) {
                        method[i].setAccessible(true);
                        String methodName = method[i].getName();
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
     * Generates ontology search criteria
     * @param queryString - Specifies the query string
     * @param packageName - specifies the package name
     * @return ontology criteria object
     * @throws Exception
     */
    public Object buildOntology(String queryString, String packageName) throws Exception{
        
        String attName = queryString.substring(queryString.indexOf(Constant.AT)+1,queryString.indexOf(Constant.EQUAL));
        String attValue = queryString.substring(queryString.indexOf(Constant.EQUAL)+1,queryString.lastIndexOf(Constant.RIGHT_BRACKET));
        String setMethodName = "set"+attName.substring(0,1).toUpperCase() + attName.substring(1);
        String roleName = queryString.substring(0, queryString.indexOf(Constant.LEFT_BRACKET));
        
        Object ontologyCriteria = null;
        String ontologyClassName = null;
        
        if(roleName.startsWith("child")){
            ontologyClassName = roleName.substring("child".length());
        }
        else if(roleName.startsWith("parent")){
            ontologyClassName = roleName.substring("parent".length());
        }
        if(ontologyClassName.endsWith("Collection")){
            ontologyClassName = ontologyClassName.substring(0,ontologyClassName.indexOf("Collection"));
        }

        if(ontologyClassName.indexOf(Constant.DOT)<1){
            ontologyClassName = packageName + Constant.DOT +ontologyClassName;
        }
        
        Field field = getField(Class.forName(ontologyClassName), attName);
        
        Method method = getMethod(Class.forName(ontologyClassName), setMethodName);
        
        Object value = null;
        if(method != null){
            if(!field.getType().getName().endsWith("String")){
                value = convertValues(field, attValue);
            }
            else{
                value = attValue;
            }
            ontologyCriteria = Class.forName(ontologyClassName).newInstance();
            method.invoke(ontologyCriteria, new Object[]{value});
            
            Field[] ofields = this.getAllFields(Class.forName(ontologyClassName)); 
            for(int i=0; i<ofields.length; i++){
                Field ofield = ofields[i];
                ofield.setAccessible(true);
            }
        }
        return ontologyCriteria;
    }
    /**
     * Returns the target class name for the specified class and role names
     * @param className specifies the class name
     * @param roleName specifies role name
     * @return
     * @throws Exception
     */
    public String getTargetClassName(String className, String roleName) throws Exception{
        String targetClassName = null;
        String target = null;
        Set roles = roleLookupProp.keySet();
        if(roleLookupProp.getProperty(roleName+className)!= null){
            String searchName = roleName+className;
            targetClassName = roleLookupProp.getProperty(searchName);            
            if(targetClassName == null){
                for(Iterator i= roles.iterator(); i.hasNext();){
                    String key = (String)i.next();
                    String value = (String)roleLookupProp.get(key);
                    if(key.equals(searchName)){
                        targetClassName = value;                        
                        break;
                    }
                }
            }
        }
                    
            String searchBean = roleName;
            if(targetClassName == null){            
                if(roleName.indexOf("Collction")>0){
                    searchBean = searchBean.substring(0,searchBean.lastIndexOf("Collection"));
                }
                searchBean = searchBean.substring(0,1).toUpperCase() + searchBean.substring(1);
                    
                    for(Iterator i= roles.iterator(); i.hasNext();){
                        String key = (String)i.next();
                        String value = (String)roleLookupProp.get(key);
                        if(key.endsWith(searchBean) && value.equals(className)){
                            target= key;                            
                            break;
                        }
                    }             
            }
            if(target != null){
                if(target.indexOf("Collection")>0){
                    targetClassName = target.substring(target.indexOf("Collection")+"Collection".length());                    
                }
            }      
            if(targetClassName == null){
                Class superClass = Class.forName(className).getSuperclass();
                if(!superClass.getName().endsWith("Object") && superClass != null){
                    targetClassName = getTargetClassName(superClass.getName(), roleName);
                }
            }  
        return targetClassName;
        
    }

}

