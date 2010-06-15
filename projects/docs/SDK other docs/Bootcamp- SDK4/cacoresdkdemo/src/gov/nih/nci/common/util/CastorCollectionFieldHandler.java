package gov.nih.nci.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.FieldDescriptor;
import org.exolab.castor.mapping.GeneralizedFieldHandler;



/**
 * The FieldHandler for the Date class
 *
 */
public class CastorCollectionFieldHandler
	extends GeneralizedFieldHandler
{

    private static Logger log = Logger.getLogger(CastorCollectionFieldHandler.class);

    /**
     * Creates a new MyDateHandler instance
     */
    public CastorCollectionFieldHandler() {
        super();
        setCollectionIteration(false);
        log.debug("CastorCollectionFieldHandler()");
    }
    
    
    public Object convertUponGet(Object value) {
    	log.debug("*** convertUponGet(Object value) called ***");
    	log.debug("Value: " + value);
    	log.debug("Value.class: " + value.getClass().getName());
    	
        if (value == null) return null;
        
        String setMethodName, getMethodName;
        
        Class klass;
        Method[] methods;
        Method tempMethod;
        //Collection tempCollection = (Collection)value;
        //log.debug("Collection size: " + tempCollection.size());
        Object tempObject = null;
        
        java.util.Collection tempCollection = new ArrayList();
    	HashSet tempHS = new java.util.HashSet();
        Object[] args = {tempHS};
        Class[] parameterTypes = {Collection.class};
    	log.debug("args array initialized: " + args[0].getClass().getName());       

        
        //Iterator collIterator = tempCollection.iterator();
        Enumeration collIterator = (Enumeration)value;
        
        while (collIterator.hasMoreElements()){

        	//tempObject = (Object)collIterator.next(); 
        	try {
        		//tempObject = CastorCollectionFieldHandler.deepCopy((Object)collIterator.nextElement()); 
        		tempCollection.add(CastorCollectionFieldHandler.deepCopy((Object)collIterator.nextElement()));
        	} catch (Exception e) {
        		log.debug("Exception caught trying to deepCopy object");
        	}
        }
        
        log.debug("*** Pre-final tempCollection.size(): " + tempCollection.size());
        
        Iterator iter = tempCollection.iterator();
        while (iter.hasNext()){  
        	tempObject = iter.next();
            klass = tempObject.getClass();
	        methods = klass.getDeclaredMethods();
	        
	    	log.debug("Number of methods: " + methods.length);
	
	        for (int i=0; i < methods.length; i++){
	        	
	        	tempMethod = methods[i];
	
	        	log.debug("tempMethod[" + i + "].getName(): " + tempMethod.getName());
	        	log.debug("tempMethod[" + i + "].getReturnType().getName(): " + tempMethod.getReturnType().getName());
	        	
	        	if ("java.util.Collection".equalsIgnoreCase(tempMethod.getReturnType().getName())){
	        		try {
	        			
	        			getMethodName = tempMethod.getName();
	                	log.debug("getMethodName: " + getMethodName);
	                	setMethodName = 's' + getMethodName.substring(1);
	                	log.debug("setMethodName: " + setMethodName);
	                	
	                	tempMethod = klass.getDeclaredMethod(setMethodName, parameterTypes);
	        			tempMethod.invoke(tempObject, args);
	            		log.debug("Successful: Collection Attribute set to empty HashSet for method " + tempMethod.getName());
	            		//tempCollection.add(tempObject);
	        		} catch (Exception e) {
	        			//log.error("Exception: " + e.getMessage(), e);
	        			log.debug("Exception: " + e.getMessage());
	        			log.debug("Unsuccessful:  Collection Attribute NOT set to empty HashSet for method " + tempMethod.getName());
	        		}
	        	}
	        }
        }
        
        //return value;
        log.debug("*** final tempCollection.size(): " + tempCollection.size());
        if (tempCollection.size() == 0){return null;}
        return tempCollection;
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
    	log.debug("*** convertUponSet() called - do nothing ***");
    	log.debug("*** Value: " + value);
    	log.debug("*** Value class: " + value.getClass().getName());
    	log.debug("*** Value size: " + ((ArrayList)value).size());
        return value;
    }

    /**
     * Returns the class type for the field that this
     * GeneralizedFieldHandler converts to and from. This
     * should be the type that is used in the
     * object model.
     *
     * @return the class type of the field
     */
    public Class getFieldType() {
    	log.debug("*** getFieldType() called ***");
    	FieldDescriptor fd = getFieldDescriptor();
    	log.debug("*** FieldDescriptor(fd): " + getFieldDescriptor());
//    	log.debug("*** fd.getFieldName(): " + fd.getFieldName());
//    	log.debug("*** fd.getFieldType(): " + fd.getFieldType());
//    	log.debug("*** fd.getFieldType().getName(): " + fd.getFieldType().getName());
//    	log.debug("*** fd.getFieldType().getClass().getName(): " + fd.getFieldType().getClass().getName());
//    	return fd.getFieldType().getClass();
        //return ArrayList.class;
    	return null;
    }

    /**
     * Creates a new instance of the object described by
     * this field.
     *
     * @param parent The object for which the field is created
     * @return A new instance of the field's value
     * @throws IllegalStateException This field is a simple
     *  type and cannot be instantiated
     */
    public Object newInstance( Object parent )
        throws IllegalStateException
    {
    	log.debug("*** newInstance() called ***");
		log.debug("**** setValue parent.getClass().getName(): " + parent.getClass().getName());
    	log.debug("*** END newInstance() ***\n");
        return null;
    }
    
   static public Object deepCopy(Object oldObj) throws Exception
   {
	  log.debug("deepCopy called");
      ObjectOutputStream oos = null;
      ObjectInputStream ois = null;
      try
      {
         ByteArrayOutputStream bos = 
               new ByteArrayOutputStream(); // A
         oos = new ObjectOutputStream(bos); // B
         // serialize and pass the object
         oos.writeObject(oldObj);   // C
         oos.flush();               // D
         ByteArrayInputStream bin = 
               new ByteArrayInputStream(bos.toByteArray()); // E
         ois = new ObjectInputStream(bin);                  // F
         // return the new object
         return ois.readObject(); // G
      }
      catch(Exception e)
      {
         log.debug("Exception in ObjectCloner = " + e);
         throw(e);
      }
      finally
      {
         oos.close();
         ois.close();
      }
   }
       

}
