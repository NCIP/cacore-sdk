package gov.nih.nci.common.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.GeneralizedFieldHandler;

/**
 * The FieldHandler for the Date class
 *
 */
public class CastorDomainObjectFieldHandler
    extends GeneralizedFieldHandler
{

    private static Logger log = Logger.getLogger(CastorDomainObjectFieldHandler.class);

    /**
     * Creates a new MyDateHandler instance
     */
    public CastorDomainObjectFieldHandler() {
        super();
        setCollectionIteration(false);
        log.debug("CastorDomainObjectFieldHandler()");
    }

    /**
     * This method is used to convert the value when the
     * getValue method is called. The getValue method will
     * obtain the actual field value from given 'parent' object.
     * This convert method is then invoked with the field's
     * value. The value returned from this method will be
     * the actual value returned by getValue method.
     *
     * @param value the object value to convert after
     *  performing a get operation
     * @return the converted value.
     */
    public Object convertUponGet(Object value) {
    	
    	log.debug("Value: " + value);
        if (value == null) return null;
        
        String setMethodName, getMethodName;
        Class klass = value.getClass();
        Method[] methods = klass.getDeclaredMethods();
        Method tempMethod;
        
    	log.debug("Number of methods: " + methods.length);
   	
    	HashSet tempHS = new java.util.HashSet();
        Object[] args = {tempHS};
        Class[] parameterTypes = {Collection.class};
    	log.debug("args array initialized: " + args[0].getClass().getName());

        for (int i=0; i < methods.length; i++){
        	
        	tempMethod = methods[i];

        	if ("java.util.Collection".equalsIgnoreCase(tempMethod.getReturnType().getName())){
        		try {
        			
        			getMethodName = tempMethod.getName();
                	log.debug("getMethodName: " + getMethodName);
                	setMethodName = 's' + getMethodName.substring(1);
                	log.debug("setMethodName: " + setMethodName);
                	
                	tempMethod = klass.getDeclaredMethod(setMethodName, parameterTypes);
        			tempMethod.invoke(value, args);
            		log.debug("Successfully set Collection Attribute to empty HashSet for method " + tempMethod.getName());
        		} catch (Exception e) {
        			log.error("Exception: " + e.getMessage(), e);
        			log.debug("Exception: " + e.getMessage());
        			log.debug("Collection Attribute to empty HashSet for method " + tempMethod.getName());
        		}
        	}
        }

        return value;
    }


    /**
     * This method is used to convert the value when the
     * setValue method is called. The setValue method will
     * call this method to obtain the converted value.
     * The converted value will then be used as the value to
     * set for the field.
     *
     * @param value the object value to convert before
     *  performing a set operation
     * @return the converted value.
     */
    public Object convertUponSet(Object value) {
    	log.debug("*** Domain convertUponSet() called - do nothing ***");
    	log.debug("*** Domain Value: " + value);
    	log.debug("*** Domain Value class: " + value.getClass().getName());
    	log.debug("*** Domain Value size: " + ((HashSet)value).size());
    	return value;
    }   
    public Class getFieldType() {
    	log.debug("*** Domain getFieldType() - returning null ***");   	
        return null;
    }

}
