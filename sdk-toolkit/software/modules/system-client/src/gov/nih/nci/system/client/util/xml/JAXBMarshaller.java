package gov.nih.nci.system.client.util.xml;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class JAXBMarshaller implements gov.nih.nci.system.client.util.xml.Marshaller {
	
	private static Logger log = Logger.getLogger(JAXBMarshaller.class.getName());

	private Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	private Schema schemaObj;
	private SchemaFactory sf;

	private boolean validate = true;
	private boolean includeXmlDeclaration = true;
	private String contextName;
	
	private boolean useContextName = false;
	
	public JAXBMarshaller(boolean validate, boolean includeXmlDeclaration)
	{
		this.validate = validate;
		this.includeXmlDeclaration = includeXmlDeclaration;
	}
	
	public JAXBMarshaller(boolean validate, boolean includeXmlDeclaration, String contextName)
	{
		this.validate = validate;
		this.includeXmlDeclaration = includeXmlDeclaration;
		
		if (contextName != null && contextName.length() >0) {
			useContextName = true;
			this.contextName = contextName;
			
			//Initialize JAXB Context
			try {
				getJAXBContext(contextName);
			} catch (JAXBException e) {
				log.error("Unable to initialize JAXB Context using contextName: " + contextName,e);
			}

			//Initialize Schemas
			initializeSchemaFactory();
			
			log.debug("Marshaller has been initialized using context: " + jaxbContextMap.get(contextName));
		} else {
			log.warn("A valid JAXB context name has not been supplied during Marshaller instantiation.  The object supplied during calls to the toXml() methods will be used instead.");
		}
	}
	
	public void toXML(Object object, Writer writer) throws XMLUtilityException{
		JAXBContext context = null;
		try {
			if (object == null){
				throw new XMLUtilityException("Error:  Object supplied to the marshaller is null.  The marshalling process has been aborted.");
			}
			
			if (useContextName) {
				context = getJAXBContext();
			}
			
			if (context == null){
				log.warn("An attempt to use the context name supplied during Marshaller instantiation has failed.  Using the supplied object's class package name instead: "+object.getClass().getPackage().getName());
				context = getJAXBContext(object.getClass().getPackage().getName());
				
				if (context == null){
					log.error("Error:  Unable to determine the JAXB context using the supplied object's class package name.");
					throw new XMLUtilityException("Error:  Unable to determine the JAXB context using the supplied object's class package name.");
				}
				
		        if (validate){
		        	log.info("Marshaller validation is enabled.  Creating schema object based upon the supplied marshalling object's class package name: " + object.getClass().getPackage().getName());
		        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		        	String schemaFileName = object.getClass().getPackage().getName() + ".xsd";
		        	log.debug("Marshalling using schema file name: " + schemaFileName);
		    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));
		    		schemaObj = sf.newSchema(schemaFile);
		        	log.debug("Validation is enabled.  Schema object has been set using individual schema file "+schemaFileName);
		        }
			}
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			if (includeXmlDeclaration){
				log.info("Marshaller is including the XML Declaration");
				m.setProperty(Marshaller.JAXB_FRAGMENT, false);
			} else {
				log.info("Marshaller is excluding the XML Declaration");
				m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			}
			
	        if (validate){
//	        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//	        	String schemaFileName = object.getClass().getPackage().getName() + ".xsd";
//	        	log.debug("Marshalling using schema file name: " + schemaFileName);
//	    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));
//	    		schemaObj = sf.newSchema(schemaFile);
	        	log.debug("Validation is enabled.  Setting Schema to: "+schemaObj);
	    		m.setSchema(schemaObj);
	        }
			m.marshal(object, writer);
		}
		catch(JAXBException e)
		{
			log.error("JAXBException caught marshalling " + object.getClass().getName(), e);
			log.debug("Marshalling using context: " + context);
			throw new XMLUtilityException(e.getMessage(), e);
		}
		catch(Exception e)
		{
			log.error("Exception caught marshalling " + object.getClass().getName(), e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public String toXML(Object object) throws XMLUtilityException {
		StringWriter strWriter = new StringWriter();
		toXML(object, strWriter);
		return strWriter.toString();
	}
	
	public JAXBContext getJAXBContext() throws JAXBException{
		if (useContextName){
			log.debug("Getting JAXB context using contextName: " + contextName);
		} else {
			log.warn("A JAXB context name was not supplied during Marshaller instantiation.  Will attempt to use the object's class package name instead");
			return null;
		}
		
		return jaxbContextMap.get(contextName);	
	}		
	
	public Schema getJAXBSchema() {
		log.debug("Getting JAXB Schema" );
		return schemaObj;	
	}

	public JAXBContext getJAXBContext(String contextName) throws JAXBException{
		log.debug("Getting JAXB context using contextName: " + contextName);
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

	public Object getBaseMarshaller() throws XMLUtilityException {
		return this;
	}
	
	// todo :: refactor to a common "utils" class
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
		
		log.debug("JAXB Validation Schema: " + getJAXBSchema());
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
