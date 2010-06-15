package gov.nih.nci.common.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

import gov.nih.nci.common.exception.XMLUtilityException;

/**
 * Class used to convert domain objects to and from XML.
 */
public class XMLUtility {

    public static final String PROPERTIES_FILENAME = "xml.properties";
    public static final String PROPERTIES_MARSHALLING_KEY = "marshaller";
    public static final String PROPERTIES_UNMARSHALLING_KEY = "unmarshaller";

    private static gov.nih.nci.common.util.Marshaller marshaller;
    private static gov.nih.nci.common.util.Unmarshaller unmarshaller;
    private static Logger log= Logger.getLogger(XMLUtility.class.getName());
    private static Properties _properties;

    /* Validation is turned off by default to improve performance */

    /**
     * Creates and XMLUtility instance
     */
    static
    {
		setMarshaller();
		setUnmarshaller();
    }
    
    public XMLUtility() {
		//setMarshaller();
		//setUnmarshaller();
    }

    private static String loadProperty(String key) throws IOException{
	        if(_properties == null){
	            try {
	                _properties = new Properties();
	                _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILENAME));
	            } catch (IOException e) {
	            	log.error("Could not load xml.properties: \n " + e.getMessage());
	                throw new IOException("Error loading " + caCOREMarshaller.PROPERTIES_FILENAME + " file. Please make sure the file is in your classpath.");
	            }
	        }
	         return _properties.getProperty(key);
    }


    /**
     * Loads default properties for the XML framework
     */

    public static void setMarshaller(gov.nih.nci.common.util.Marshaller m) {
    	marshaller = m;
    }

    private static void setMarshaller() {
            try {
		    String marshallerProperty = loadProperty(XMLUtility.PROPERTIES_MARSHALLING_KEY);
		    if (marshallerProperty != null) {
				//System.out.println("Found marshaller\n");
				Class newObjClass = Class.forName(marshallerProperty);
		        marshaller = (gov.nih.nci.common.util.Marshaller) newObjClass.newInstance();
		    } else {
				marshaller = new gov.nih.nci.common.util.caCOREMarshaller();
		    }
		} catch (Exception ex) {
			log.error("Could not setMarshaller: \n " + ex.getMessage());
		}
    }

    private static void setUnmarshaller(gov.nih.nci.common.util.Unmarshaller um) {
    	unmarshaller = um;
    }

    public static void setUnmarshaller() {
            try {
			String unmarshallerProperty = loadProperty(XMLUtility.PROPERTIES_UNMARSHALLING_KEY);
			if (unmarshallerProperty != null) {
				//System.out.println("Found unmarshaller\n");
				Class newObjClass = Class.forName(unmarshallerProperty);
			    unmarshaller = (gov.nih.nci.common.util.Unmarshaller) newObjClass.newInstance();
			} else {
				unmarshaller = new gov.nih.nci.common.util.caCOREUnmarshaller();
			}
		} catch (Exception ex) {
				log.error("Could not setUnmarshaller: \n " + ex.getMessage());
		}
    }

    public gov.nih.nci.common.util.Marshaller getMarshaller() {
    	return marshaller;
    }

    public gov.nih.nci.common.util.Unmarshaller getUnmarshaller() {
    	return unmarshaller;
    }

    /**
     *
     * Serializes a caCORE object into xml
     * @param beanObject The caCORE object to serialize into xml
     */
    public static String toXML(Object beanObject) throws XMLUtilityException{
        StringWriter strWriter = new StringWriter();
        toXML(beanObject, strWriter);
        return strWriter.toString();
    }

    public static void toXML(Object beanObject, Writer stream) throws XMLUtilityException{
    	marshaller.toXML(beanObject,stream);
    }

    /**
     * Instanties an object from an xml File that contains the serialized
     * output of that object.
     * @param xmlFile
     * @return
     * @throws XMLUtilityException
     */
      public static Object fromXML(File xmlFile) throws XMLUtilityException{
    	Object beanObject = null;
    	beanObject = unmarshaller.fromXML(xmlFile);
    	return beanObject;
    }


    /**
     * Instantiates an object from xml input that contains the serialized
     * output of that object.
     * @param input Reader type
     * @return Instantiated object
     * @throws XMLUtilityException
     */

    public static Object fromXML(Reader input) throws XMLUtilityException {
        Object beanObject;
        beanObject = unmarshaller.fromXML(input);
        return beanObject;
    }
}
