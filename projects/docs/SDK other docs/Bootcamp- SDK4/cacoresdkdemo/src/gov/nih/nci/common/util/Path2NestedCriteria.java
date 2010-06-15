package gov.nih.nci.common.util;
import gov.nih.nci.common.util.RoleLookupProperties;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;

import org.apache.log4j.Logger;

public class Path2NestedCriteria
{
	private static Logger log = Logger.getLogger(Path2NestedCriteria.class);
	
	private static Properties roleLookupProp;
	 
	static
	{
		try
		{	
			roleLookupProp = RoleLookupProperties.getInstance().getProperties();
		}
		catch(Exception e)
		{
		    log.error("IOException: " + e.getMessage());
		}
	}
	
	public static NestedCriteria createNestedCriteria(List pathList, List objList) throws Exception {
		NestedCriteria criteria = null;
		Stack stack1 = new Stack();
	
		for(int i = 0; i< pathList.size(); i++)
			stack1.push(pathList.get(i));
		
		String stackTop = (String)stack1.peek();
		String example = (objList.get(0)).getClass().getName();
			
		if(!stackTop.equals(example))
			stack1.push(example);
			
		if(stack1.size()==1)
			stack1.push(stack1.peek());
		
			while(stack1.size()>1)
			{
				String src = (String)stack1.pop();
				String dest = (String)stack1.pop();
				NestedCriteria newCriteria = new NestedCriteria();
				String roleName = null;
				if (!dest.equals(src) && !noInheritent(src, dest)) {
					roleName = getRoleName(newCriteria,Class.forName(src), Class.forName(dest).newInstance());
					if (roleName == null) 
						throw new Exception("No association found from " + src + " to " + dest+ ", please double check your query path.");
				}
				//roleName = (!src.equals(dest))?getRoleName(newCriteria,Class.forName(src), Class.forName(dest).newInstance()):"";

				newCriteria.setSourceObjectName(src);
				newCriteria.setTargetObjectName(dest);
				newCriteria.setRoleName(roleName);
				newCriteria.setInternalNestedCriteria(criteria);
				if(criteria==null)
					newCriteria.setSourceObjectList(objList);
				criteria = newCriteria;
				stack1.push(dest);
			}
		return criteria;
	}

	private static boolean noInheritent(String sourceName, String targetName) {
		try {
			if (Class.forName(sourceName).getSuperclass().getName().equals(targetName)
					|| Class.forName(targetName).getSuperclass().getName().equals(sourceName))
				return true;
			return false;
		} catch (Exception e) {
			log.error("Exception: ", e);
			return false;
		}
	}
	
    private static String getRoleName(NestedCriteria criteria,Class searchClass, Object criterion) throws Exception{

        String criterionClassName 	= criterion.getClass().getName();
        String roleName 			= null;
        Field[] fields 				= searchClass.getDeclaredFields();

        if (searchClass.getSuperclass() != null)
        	roleName = getRoleName(criteria,searchClass.getSuperclass(), criterion);
        
        if (roleName != null)
        	return roleName;
        
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
                        		String returnType = roleLookupProp.getProperty(fieldName+searchClass.getName());
                            	if ((returnType != null) && (returnType.equals(criterionClassName)))
                                {
                                	roleName = fieldName;
                                	criteria.setTargetCollection(true);
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
            throw ex;
        }
        return roleName;
    }

    private static boolean isCollectionType(Class inputClass)
	{
		if (inputClass.getName().equals("java.util.Collection"))
			return true;

		Class[] interfaces = inputClass.getInterfaces();
		for(int i= 0; i<interfaces.length; i++)
			if (interfaces[i].getName().equals("java.util.Collection"))
				return true;
		return false;
	}	
}