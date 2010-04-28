package gov.nih.nci.system.client.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class JAXBUnmarshaller implements gov.nih.nci.system.client.util.xml.Unmarshaller {
	
	private static Logger log = Logger.getLogger(JAXBUnmarshaller.class.getName());

	private Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	private Schema schemaObj;
	private SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	
	private boolean validate = true;
	private String contextName;
	private String packageName;
	private Class klazz;
	
	private boolean useContextName = false;
	private boolean usePackageName = false;
	private boolean useClassPackageName = false;
	
	private boolean hasBeenInvokedWithoutContext = true;
	
	private List<ValidationEvent> validationEvents = new ArrayList<ValidationEvent>();
	
	public JAXBUnmarshaller(boolean validate, String contextName)
	{
		this.validate = validate;
		if (contextName != null && contextName.length() >0) {
			this.contextName = contextName;
			useContextName = true;
			hasBeenInvokedWithoutContext = false;
			
			//Initialize JAXB Context
			try {
				getJAXBContext(contextName);
			} catch (JAXBException e) {
				log.error("Unable to initialize Unmarshaller's JAXB Context using contextName: " + contextName,e);
			}

			//Initialize Schemas
			//initializeSchemaFactory();
			
			log.debug("Unmarshaller has been initialized using context: " + jaxbContextMap.get(contextName));
		} else {
			hasBeenInvokedWithoutContext = true;
			log.warn("A valid JAXB context name has not been supplied during Unmarshaller instantiation.  Make sure to supply either a valid package name or template object during subsequent calls to fromXml() methods");
		}

	}
	
	public JAXBUnmarshaller(boolean validate)
	{
		this.validate = validate;
		this.contextName = "";

		hasBeenInvokedWithoutContext = true;
	}
	
	public Object fromXML(Reader reader) throws XMLUtilityException {
		JAXBContext context = null;
		SAXSource saxSource = null;
		try
		{
			if (!useContextName && hasBeenInvokedWithoutContext) {
				throw new XMLUtilityException("No context name was supplied during Unmarshaller instantiation.  Consequently, you must use one of the fromXML() methods that supplies either the template object class or ojbect package name.");
			}
			
			if (useContextName){
				log.debug("Getting JAXB Context using Context name: " + contextName);
				if ( (contextName == null || !(contextName.length() >0)) ){
					throw new XMLUtilityException("Supplied context name seems invalid.  Ensure a non-null, non-empty context name.");
				}
				context = getJAXBContext(contextName);
			} else if (usePackageName){
				log.debug("Getting JAXB Context using Package name: " + packageName);
				if ( (packageName == null || !(packageName.length() >0)) ){
					throw new XMLUtilityException("Supplied Package name seems invalid.  Ensure a non-null, non-empty package name.");
				}
				context = getJAXBContext(packageName);
			} else if (useClassPackageName){
				if ( (klazz == null) ){
					throw new XMLUtilityException("Supplied Class template seems invalid while getting JAXB context using the class package name derived from the template object passed in.  Ensure a non-null, non-empty class has been set.");
				}
				log.debug("Getting JAXB Context using template object's package name: " + klazz.getPackage().getName());
				context = getJAXBContext(klazz.getPackage().getName());
			} else {
				log.error("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied." );
				throw new XMLUtilityException("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied.");
			}
			
			if (context == null) {
				log.error("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied." );
				throw new XMLUtilityException("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied.");
			}

	        Unmarshaller unmarshaller = context.createUnmarshaller();
	
	        if(validate){	        	
	        	//reset validation events list
	        	validationEvents = new ArrayList<ValidationEvent>();
	    		
	    		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                parserFactory.setNamespaceAware(true);
                parserFactory.setValidating(true);
                SAXParser saxParser = parserFactory.newSAXParser();

                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setFeature("http://apache.org/xml/features/validation/schema", true);
                xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
                xmlReader.setFeature("http://xml.org/sax/features/xmlns-uris", true);
                xmlReader.setFeature("http://apache.org/xml/features/honour-all-schemaLocations", true);

                InputSource inSrc = new InputSource(reader);
                EntityResolver entityResolver = new EntityResolver() {
                	public InputSource resolveEntity(String publicId, String systemId) {
                		String xsdPath = null;
                		log.debug("Entity resolving systemID... " + systemId);

                		if(systemId != null)
                		{
                			xsdPath = systemId.substring(systemId.lastIndexOf("/")+1); 
                			if(publicId == null)
                				publicId = xsdPath.substring(0, xsdPath.length()-4);
                		}

                		log.debug("Entity resolving publicId... " + publicId);
                		log.debug("Entity resolving to xsd... " + xsdPath);

                		// InputSource source = new
                		// InputSource(Thread.currentThread().getContextClassLoader()
                		// .getResourceAsStream(xsdPath));

                		InputSource source = new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(xsdPath));
                		source.setSystemId(systemId);
                		source.setPublicId(publicId);
                		return source;
                	}
                };  
                xmlReader.setEntityResolver(entityResolver);

                saxSource = new SAXSource(xmlReader, inSrc); 
                log.debug("Entity resolver: " + entityResolver);  		
	        }
	        unmarshaller.setEventHandler(new ValidationHandler());
	        
	        hasBeenInvokedWithoutContext = true;
	        
	        return unmarshaller.unmarshal(saxSource);
		}
		catch(JAXBException e)
		{
			log.error("JAXBException caught Unmarshalling from: " + reader, e);
			if (context == null){
				log.debug("Unmarshalling using null context");
			} else {
				log.debug("Unmarshalling using context: " + context.toString());
			}

			throw new XMLUtilityException(e.getMessage(), e);
		}	
		catch(Exception e)
		{
			log.error("Exception caught unmarshalling from reader "+reader, e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public synchronized Object fromXML(Class klazz, java.io.Reader reader) throws XMLUtilityException {
		useContextName = false;
		usePackageName = false;
		useClassPackageName = true;
		
		hasBeenInvokedWithoutContext = false;
		
		this.klazz = klazz;
		
		return fromXML(reader);
	}
	
	public synchronized Object fromXML(Class klazz, java.io.File xmlFile) throws XMLUtilityException {
		useContextName = false;
		usePackageName = false;
		useClassPackageName = true;
		
		hasBeenInvokedWithoutContext = false;
		
		this.klazz = klazz;
		
		return fromXML(xmlFile);
	}	

	public synchronized Object fromXML(String packageName, java.io.File xmlFile) throws XMLUtilityException {
		useContextName = false;
		usePackageName = true;
		useClassPackageName = false;
		
		hasBeenInvokedWithoutContext = false;
		
		this.packageName = packageName;
		
		return fromXML(xmlFile);
	}		
	
	public synchronized Object fromXML(String packageName, Reader reader) throws XMLUtilityException {
		useContextName = false;
		usePackageName = true;
		useClassPackageName = false;
		
		hasBeenInvokedWithoutContext = false;
		
		this.packageName = packageName;
		
		return fromXML(reader);
	}	

	public synchronized Object fromXML(File file) throws XMLUtilityException {
		// hasBeenInvokedWithoutContext will be set to true by default if this method is called directly without a context class or package name
		try {
			FileReader fRead = new FileReader(file);
			return fromXML(fRead);
		} catch (FileNotFoundException e) {
			log.error(e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public Object getBaseUnmarshaller() {
		return this;
	}	
	
	public Schema getJAXBSchema() throws XMLUtilityException {	
		String schemaFileName;
		Source schemaFile;
		
		if (useContextName){
			log.debug("Getting JAXB Schema using Context name: " + contextName);
			if ( (contextName == null || !(contextName.length() >0)) ){
				throw new XMLUtilityException("Supplied context name seems invalid.  Ensure a non-null, non-empty context name.");
			}
			
			// schema object was set during Unmarshaller instantiation
			return schemaObj;
		} else if (usePackageName){
			log.debug("Getting JAXB Schema using Package name: " + packageName);
			if ( (packageName == null || !(packageName.length() >0)) ){
				throw new XMLUtilityException("Supplied Package name seems invalid.  Ensure a non-null, non-empty package name.");
			}
        	schemaFileName = packageName + ".xsd";
        	log.debug("Unmarshalling using schema file name: " + schemaFileName);
    		schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));
    		
    		Schema tempSchemaObj=null;
    		try {
				tempSchemaObj = sf.newSchema(schemaFile);
			} catch (SAXException e) {
				log.error("Error trying to get a JAXB Schema using the supplied package name: " + packageName);
				throw new XMLUtilityException("Error trying to get a JAXB Schema using the supplied package name: " + packageName, e);
			}
    		return(tempSchemaObj);
		} else if (useClassPackageName){
			if ( (klazz == null) ){
				throw new XMLUtilityException("Supplied Class template seems invalid while getting JAXB context using the class package name derived from the template object passed in.  Ensure a non-null, non-empty class has been set.");
			}
			
			String klazzPackageName = klazz.getPackage().getName();
			log.debug("Getting JAXB Schema using template object: " + klazzPackageName);
        	schemaFileName = klazz.getPackage().getName() + ".xsd";
        	log.debug("Unmarshalling using schema file name: " + schemaFileName);
    		schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));

    		Schema tempSchemaObj=null;
    		try {
				tempSchemaObj = sf.newSchema(schemaFile);
			} catch (SAXException e) {
				log.error("Error trying to get a JAXB Schema using the supplied class package name derived from the template object passed in: " + klazzPackageName);
				throw new XMLUtilityException("Error trying to get a JAXB Schema using the supplied class package name derived from the template object passed in: " + klazzPackageName, e);
			}
    		return(tempSchemaObj);
		} 

		// default
		log.error("Attempts to set the JAXB Schema using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied." );
		throw new XMLUtilityException("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied.");

	}	
	
	public JAXBContext getJAXBContext() throws JAXBException{
		log.debug("Getting JAXB context using Context name: " + contextName);
		return jaxbContextMap.get(contextName);	
	}	
	
	public JAXBContext getJAXBContext(String contextName) throws JAXBException{
		log.debug("Getting JAXB context using context name: " + contextName);
		JAXBContext context;
		if(!jaxbContextMap.containsKey(contextName))
		{
			context = JAXBContext.newInstance(contextName);
			jaxbContextMap.put(contextName, context);
			return context;
		}
		else
			return jaxbContextMap.get(contextName);	
	}

	// todo :: refactor to a "utils" class
	private void initializeSchemaFactory() {
		this.sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		
		List<Source> sourceList = new ArrayList<Source>();
		
		String schemaFileName=null;		
		
		StringTokenizer st = new StringTokenizer(contextName, ":");
		while (st.hasMoreTokens()) {
			schemaFileName = st.nextToken() + ".xsd";
			log.debug("schemaFileName: " + schemaFileName);
			sourceList.add(new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName)));
		}

		try {
			sf.setErrorHandler(new SchemaErrorHandler());
			Source[] tempSourceList = (Source[]) sourceList.toArray(new Source[sourceList.size()]);
			if (tempSourceList != null) {
				log.debug("Number of Sources found: " + tempSourceList.length);
			}
			schemaObj = sf.newSchema((Source[]) sourceList.toArray(new Source[sourceList.size()]));
			log.debug("JAXB Validation Schema has been initialized: " + schemaObj);
		} catch (SAXException e) {
			log.error("Error initializing Schema Factory",e);
		}
		
//		log.debug("JAXB Validation Schema: " + getJAXBSchema());
	}
	
	class ValidationHandler implements ValidationEventHandler {
		
		public boolean handleEvent(ValidationEvent e) {
			log.error("Validation Exception Event while unmarshalling: " + e.getMessage());
			if (validationEvents == null ){
				validationEvents = new ArrayList<ValidationEvent>();
			}
			validationEvents.add(e);
			return true;
		}	
	}
	
	public boolean hasValidationExceptions() {
		if (validationEvents == null || validationEvents.isEmpty()){
			return false;
		}
		
		return true;
	}
	
	class SchemaErrorHandler implements ErrorHandler {
		
		public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
			log.error("Fatal Error while initializing Schema Factory: ",e);
		}
		
		public void error(org.xml.sax.SAXParseException e) throws SAXException {
			log.error("Error while initializing Schema Factory: ",e);
		}
		
		public void warning(org.xml.sax.SAXParseException e) throws SAXException {
			log.error("Warning while initializing Schema Factory: ",e);
		}		
	}
	
}
