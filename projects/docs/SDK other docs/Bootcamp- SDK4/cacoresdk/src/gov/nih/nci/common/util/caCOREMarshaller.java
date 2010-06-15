package gov.nih.nci.common.util;

import gov.nih.nci.common.exception.XMLUtilityException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import java.io.InputStream;


public class caCOREMarshaller implements gov.nih.nci.common.util.Marshaller {
	public static final String PROPERTIES_FILENAME = "xml.properties";
    public static final String PROPERTIES_MAPPING_KEY = "mapping-file";
    public static final String PROPERTIES_VALIDATION_KEY = "validation";
	
    private Marshaller marshaller;
    private Mapping mapping;
    private Properties _properties;
    private static Logger log= Logger.getLogger(caCOREMarshaller.class.getName());

    /* Validation is turned off by default to improve performance */
    private boolean validation;

    /**
     * Creates and XMLUtility instance
     */
    public caCOREMarshaller() {
    }
    private String loadProperty(String key) throws IOException{
        if(_properties == null){
            try {
                _properties = new Properties();
                _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.PROPERTIES_FILENAME));
            } catch (IOException e) {
            	log.error("Could not load xml.properties: \n " + e.getMessage());
                throw new IOException("Error loading " + caCOREMarshaller.PROPERTIES_FILENAME + " file. Please make sure the file is in your classpath.");
            }
        }
         return _properties.getProperty(key);
    }

    /**
     * Validation is turned off by default to improve performance
     * @return boolean
     */
    public boolean isValidation() {
        try {
            validation =  loadProperty(caCOREMarshaller.PROPERTIES_VALIDATION_KEY).equals("true");
        } catch (IOException e) {
        	log.error("Could not load xml validation property: \n " + e.getMessage());
            // do nothing, validation is false by default
        }
        return validation;
    }
    /**
     * Validation is turned off by default to improve performance
     * @param validation
     */
    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    /**
     * Implementations can set their own castor mapping
     * to override default behavious of caCORE xml serialization/deserialization
     * framework.
     * @param mapping castor Mapping file to be used
     *
     */
    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    /**
     *
     * @return default mapping file being used for xml serialziation/deserialization
     */
    public Mapping getMapping() throws XMLUtilityException {
        /* if no mapping file explicity specified then load the default */
        if(mapping == null){
            try {
            	EntityResolver resolver = new EntityResolver() {
            	    public InputSource resolveEntity(String publicId, String systemId) {
            	        if ( publicId.equals( "-//EXOLAB/Castor Object Mapping DTD Version 1.0//EN" ) ) {
            	            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mapping.dtd");
            	            return new InputSource( in );
            	        }
            	        return null;
            	    }
            	};

                org.xml.sax.InputSource mappIS = new org.xml.sax.InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(loadProperty(this.PROPERTIES_MAPPING_KEY)));
	        	Mapping localMapping = new Mapping();
                localMapping.setEntityResolver(resolver);
                localMapping.loadMapping(mappIS);
                return localMapping;
            }catch (IOException e) {
                log.error("Error reading default xml mapping file " + e.getMessage());  //To change body of catch statement use File | Settings | File Templates.
                throw new XMLUtilityException("Error reading default xml mapping file " + e.getMessage(), e);
            }
        }
       	return mapping;
    }
    
    public String toXML(Object beanObject) throws XMLUtilityException {
    	StringWriter strWriter = new StringWriter();
        this.toXML(beanObject, strWriter);
        return strWriter.toString();
    }
	
	public synchronized void toXML(Object beanObject, java.io.Writer stream) throws XMLUtilityException{

        org.exolab.castor.xml.Marshaller marshaller = null;
        try {
        	
            marshaller = new org.exolab.castor.xml.Marshaller(stream);
        } catch (IOException e) {
            log.error("Output stream invalid " + e.getMessage());
            throw new XMLUtilityException("Output stream invalid "  + e.getMessage(), e);
        }
        try{
        		marshaller.setMapping(this.getMapping());
        }catch (MappingException e) {
            log.error("The mapping file is invalid:  e.getMessage()", e);
            throw new XMLUtilityException("The mapping file is invalid "  + e.getMessage(), e);
        }

        try {
            /** Disabled to improve performance **/
        	marshaller.setMarshalAsDocument(true);
        	marshaller.setDebug(true);
        	marshaller.setSuppressNamespaces(false);
        	//marshaller.setNamespaceMapping("", "gme://caCORE.cabig/3.1/gov.nih.nci.cabio.domain");
            marshaller.setValidation(this.isValidation());
            marshaller.marshal(beanObject);
        } catch (MarshalException e) {
        	log.error("Error in marshalling object: " + e.getMessage());
            throw new XMLUtilityException(e.getMessage(),e);
        } catch (ValidationException e) {
        	log.error("Error in xml validation of marshalled object: " + e.getMessage());
            throw new XMLUtilityException(e.getMessage(),e);
        }
	}
	
	public Object getBaseMarshaller(){
		return (Object)this.getMarshaller();
	}
	
	public Marshaller getMarshaller(){
		return marshaller;
	}
}
