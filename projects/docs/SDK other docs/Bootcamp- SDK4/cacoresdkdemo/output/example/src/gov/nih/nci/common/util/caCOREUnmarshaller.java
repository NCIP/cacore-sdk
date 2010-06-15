package gov.nih.nci.common.util;
import gov.nih.nci.common.exception.XMLUtilityException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import java.io.InputStream;


public class caCOREUnmarshaller implements gov.nih.nci.common.util.Unmarshaller {
	
	public static final String PROPERTIES_FILENAME = "xml.properties";
    public static final String PROPERTIES_MAPPING_KEY = "unmarshaller-mapping-file";
    public static final String PROPERTIES_VALIDATION_KEY = "validation";
	
    private Unmarshaller unmarshaller;
    private Mapping mapping;
    private Properties _properties;
    private static Logger log= Logger.getLogger(caCOREUnmarshaller.class.getName());

    /* Validation is turned off by default to improve performance */
    private boolean validation;

    /**
     * Creates and XMLUtility instance
     */
    
    public caCOREUnmarshaller() {
    }
    
    private String loadProperty(String key) throws IOException{
        if(_properties == null){
            try {
                _properties = new Properties();
                _properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.PROPERTIES_FILENAME));
            } catch (IOException e) {
            	log.error("Could not load xml.properties: \n " + e.getMessage());
                throw new IOException("Error loading " + caCOREUnmarshaller.PROPERTIES_FILENAME + " file. Please make sure the file is in your classpath.");
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
            validation =  loadProperty(caCOREUnmarshaller.PROPERTIES_VALIDATION_KEY).equals("true");
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
     * to override default behaviour of caCORE xml serialization/deserialization
     * framework.
     * @param mapping castor Mapping file to be used
     *
     */
    public void setMapping(Mapping mapping) {
    	log.debug("setMapping called");
        this.mapping = mapping;
    }

    /**
     *
     * @return default mapping file being used for xml serialziation/deserialization
     */
    public Mapping getMapping() throws XMLUtilityException {
        /* if no mapping file explicity specified then load the default */
    	log.debug("mapping is null? " + (mapping==null));
        if(mapping == null){
        	log.info("mapping is null; will try to load it");
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
            } catch (IOException e) {
                log.error("Error reading default xml mapping file ", e);  //To change body of catch statement use File | Settings | File Templates.
                throw new XMLUtilityException("Error reading default xml mapping file ", e);
            }
        }
        return mapping;
    }
    public synchronized Object fromXML(java.io.Reader input)throws XMLUtilityException{
    	Object beanObject;

        org.exolab.castor.xml.Unmarshaller unmarshaller = null;
        try {
        	log.debug("Creating unmarshaller");
            unmarshaller = new org.exolab.castor.xml.Unmarshaller(this.getMapping());
        } catch (MappingException e) {
        	log.error("XML mapping file is invalid: ", e);
            throw new XMLUtilityException("XML mapping file invalid: ", e);
        } catch (Exception e){
        	log.error("General Exception caught trying to create unmarshaller: ", e);
        	throw new XMLUtilityException("General Exception caught trying to create unmarshaller: ", e);
        }

        try {
        	log.debug("About to unmarshal from input ");
            beanObject = unmarshaller.unmarshal(input);
        } catch (MarshalException e) {
        	log.error("Error marshalling input: ", e);
            throw new XMLUtilityException("Error unmarshalling xml input: " + e.getMessage(),e);
        } catch (ValidationException e) {
        	log.error("Error in xml validation when unmarshalling xml input: ", e);
            throw new XMLUtilityException("Error in xml validation when unmarshalling xml input: ", e);
        }
        return beanObject;
    }

    public Object fromXML(java.io.File xmlFile)throws XMLUtilityException {
    	Object beanObject = null;
        try {
        	log.debug("Reading from file: " + xmlFile.getName());
            FileReader fRead = new FileReader(xmlFile);
            beanObject = fromXML(fRead);
        } catch (FileNotFoundException e) {
            log.error("XML input file invalid: " + e.getMessage());
            throw new XMLUtilityException("XML input file invalid: " + e.getMessage(),e);
        }
        return beanObject;
    }
    public Object getBaseUnmarshaller(){
    	return this.getUnmarshaller();
    }
    
    public Unmarshaller getUnmarshaller() {
    	return this.unmarshaller;
    }
    
    
    
    
    
    
}
