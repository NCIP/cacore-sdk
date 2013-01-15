package gov.nih.nci.restgen.mapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class JAXBUnmarshaller implements gov.nih.nci.restgen.mapping.Unmarshaller {


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
				e.printStackTrace();
			}
		} else {
			hasBeenInvokedWithoutContext = true;
		}

	}

	public JAXBUnmarshaller(boolean validate)
	{
		this.validate = validate;
		this.contextName = "";

		hasBeenInvokedWithoutContext = true;
	}

	public Object fromXML(InputStream stream) throws XMLUtilityException {
		InputStreamReader in= new InputStreamReader(stream);
		return fromXML(in, null);
	}

	public Object fromXML(Reader reader) throws XMLUtilityException {
		return fromXML(reader, null);
	}
	public Object fromXML(Reader reader, final String namespacePrefix) throws XMLUtilityException {
		JAXBContext context = null;
		SAXSource saxSource = null;
		try
		{
			//log.debug(reader.toString());
			if (!useContextName && hasBeenInvokedWithoutContext) {
				throw new XMLUtilityException("No context name was supplied during Unmarshaller instantiation.  Consequently, you must use one of the fromXML() methods that supplies either the template object class or ojbect package name.");
			}

			if (useContextName){
				if ( (contextName == null || !(contextName.length() >0)) ){
					throw new XMLUtilityException("Supplied context name seems invalid.  Ensure a non-null, non-empty context name.");
				}
				context = getJAXBContext(contextName);
			} else if (usePackageName){
				if ( (packageName == null || !(packageName.length() >0)) ){
					throw new XMLUtilityException("Supplied Package name seems invalid.  Ensure a non-null, non-empty package name.");
				}
				context = getJAXBContext(packageName);
			} else if (useClassPackageName){
				if ( (klazz == null) ){
					throw new XMLUtilityException("Supplied Class template seems invalid while getting JAXB context using the class package name derived from the template object passed in.  Ensure a non-null, non-empty class has been set.");
				}
				context = getJAXBContext(klazz.getPackage().getName());
			} else {
				throw new XMLUtilityException("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied.");
			}

			if (context == null) {
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

                		if(systemId != null)
                		{
                			xsdPath = systemId.substring(systemId.lastIndexOf("/")+1);

                			if(publicId == null)
                				publicId = xsdPath.substring(0, xsdPath.length()-4);
                		}

						if(namespacePrefix != null)
							xsdPath = namespacePrefix + "_" + xsdPath;

                		InputSource source = new InputSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(xsdPath));
                		source.setSystemId(systemId);
                		source.setPublicId(publicId);
                		return source;
                	}
                };
                xmlReader.setEntityResolver(entityResolver);

                saxSource = new SAXSource(xmlReader, inSrc);
	        }
	        unmarshaller.setEventHandler(new ValidationHandler());

	        hasBeenInvokedWithoutContext = true;

	        return unmarshaller.unmarshal(saxSource);
		}
		catch(JAXBException e)
		{
			throw new XMLUtilityException(e.getMessage(), e);
		}
		catch(Exception e)
		{
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
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public synchronized Object fromXML(File file, final String namespacePrefix) throws XMLUtilityException {
		// hasBeenInvokedWithoutContext will be set to true by default if this method is called directly without a context class or package name
		try {
			FileReader fRead = new FileReader(file);
			return fromXML(fRead, namespacePrefix);
		} catch (FileNotFoundException e) {
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
			if ( (contextName == null || !(contextName.length() >0)) ){
				throw new XMLUtilityException("Supplied context name seems invalid.  Ensure a non-null, non-empty context name.");
			}

			// schema object was set during Unmarshaller instantiation
			return schemaObj;
		} else if (usePackageName){
			if ( (packageName == null || !(packageName.length() >0)) ){
				throw new XMLUtilityException("Supplied Package name seems invalid.  Ensure a non-null, non-empty package name.");
			}
        	schemaFileName = packageName + ".xsd";
    		schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));

    		Schema tempSchemaObj=null;
    		try {
				tempSchemaObj = sf.newSchema(schemaFile);
			} catch (SAXException e) {
				throw new XMLUtilityException("Error trying to get a JAXB Schema using the supplied package name: " + packageName, e);
			}
    		return(tempSchemaObj);
		} else if (useClassPackageName){
			if ( (klazz == null) ){
				throw new XMLUtilityException("Supplied Class template seems invalid while getting JAXB context using the class package name derived from the template object passed in.  Ensure a non-null, non-empty class has been set.");
			}

			String klazzPackageName = klazz.getPackage().getName();
        	schemaFileName = klazz.getPackage().getName() + ".xsd";
    		schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));

    		Schema tempSchemaObj=null;
    		try {
				tempSchemaObj = sf.newSchema(schemaFile);
			} catch (SAXException e) {
				throw new XMLUtilityException("Error trying to get a JAXB Schema using the supplied class package name derived from the template object passed in: " + klazzPackageName, e);
			}
    		return(tempSchemaObj);
		}

		// default
		throw new XMLUtilityException("Attempts to set the JAXB Context using either a context name, package name, or class package name derived from the template object have failed. Ensure a non-null, non-empty context name, package name, or template object has been supplied.");

	}

	public JAXBContext getJAXBContext() throws JAXBException{
		return jaxbContextMap.get(contextName);
	}

	public JAXBContext getJAXBContext(String contextName) throws JAXBException{
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
			sourceList.add(new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName)));
		}

		try {
			sf.setErrorHandler(new SchemaErrorHandler());
			Source[] tempSourceList = (Source[]) sourceList.toArray(new Source[sourceList.size()]);
			schemaObj = sf.newSchema((Source[]) sourceList.toArray(new Source[sourceList.size()]));
		} catch (SAXException e) {
			e.printStackTrace();
		}

//		log.debug("JAXB Validation Schema: " + getJAXBSchema());
	}

	class ValidationHandler implements ValidationEventHandler {

		public boolean handleEvent(ValidationEvent e) {
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
		}

		public void error(org.xml.sax.SAXParseException e) throws SAXException {
		}

		public void warning(org.xml.sax.SAXParseException e) throws SAXException {
		}
	}

}
