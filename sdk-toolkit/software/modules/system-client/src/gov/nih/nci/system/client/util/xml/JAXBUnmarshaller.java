package gov.nih.nci.system.client.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.transform.JDOMSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class JAXBUnmarshaller implements gov.nih.nci.system.client.util.xml.Unmarshaller {
	
	private static Logger log = Logger.getLogger(JAXBUnmarshaller.class.getName());

	private Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	private Schema schemaObj;
	private SchemaFactory sf;
	
	private boolean validate = true;
	private String contextName;
	private String packageName;
	private Class klazz;
	
	public JAXBUnmarshaller(boolean validate, String contextName)
	{
		this.validate = validate;
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
	}
	
	public JAXBUnmarshaller(boolean validate)
	{
		this.validate = validate;
		this.contextName = "";
	}
	
	public Object fromXML(Reader reader) throws XMLUtilityException {
		JAXBContext context=null;
		Source source = null;
		try
		{
			if (contextName == null){
				throw new XMLUtilityException("JAXB Context package name has not been set");
			}

			context = getJAXBContext();
//			context = getJAXBContext(contextName);
//			context = getJAXBContext(packageName);
			log.debug("Unmarshalling using context: " + context.toString());
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	
	        if(validate){
	        	Schema schemaObj = getJAXBSchema();
				if (schemaObj == null){
//					log.warn("JAXB Validation Schema has not been set within the JAXB Marshaller.  Will attempt to create Schema based upon class package name."); 
					throw new XMLUtilityException("JAXB Validation Schema has not been set within the JAXB Marshaller");
				}
//	        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//	        	String schemaFileName = packageName + ".xsd";
//	        	log.debug("Unmarshalling using schema file name: " + schemaFileName);
//	    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));
//	    		schemaObj = sf.newSchema(schemaFile);
	        	log.debug("Unmarshalling using Validation Schema");
	    		unmarshaller.setSchema(schemaObj);
	    		
	    		SAXBuilder sb = new SAXBuilder(false);
                Document doc = sb.build(reader);
                setNamespace(doc.getRootElement(), Namespace.getNamespace("gme://caCORE.caCORE/3.2/gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable"), true);
                source = new JDOMSource(doc);
	    		
	        }
	        unmarshaller.setEventHandler(new ValidationHandler());
	        
	        return unmarshaller.unmarshal(source);
		}
		catch(JAXBException e)
		{
			log.error("JAXBException caught Unmarshalling from: " + reader, e);
			log.debug("Unmarshalling using context: " + context.toString());

			throw new XMLUtilityException(e.getMessage(), e);
		}	
		catch(Exception e)
		{
			log.error("Exception caught unmarshalling from reader "+reader, e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}
	
//	private Object unmarshall(Class klazz, java.io.Reader reader) throws JAXBException
//	{
//        JAXBContext context = JAXBContext.newInstance(klazz);
//        Unmarshaller u = context.createUnmarshaller();
//        return u.unmarshal(reader);
//	}
	
	public synchronized Object fromXML(Class klazz, java.io.Reader reader) throws XMLUtilityException {
		this.klazz = klazz;
		
		return fromXML(reader);
	}
	
	public synchronized Object fromXML(Class klazz, java.io.File xmlFile) throws XMLUtilityException {
		this.klazz = klazz;
		
		return fromXML(xmlFile);
	}	

	public synchronized Object fromXML(String packageName, java.io.File xmlFile) throws XMLUtilityException {
		this.packageName = packageName;
		
		return fromXML(xmlFile);
	}		
	
	public synchronized Object fromXML(String packageName, Reader reader) throws XMLUtilityException {
		this.packageName = packageName;
		
		return fromXML(reader);
	}	

	public Object fromXML(File file) throws XMLUtilityException {
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
	
	public Schema getJAXBSchema() {
		log.debug("Getting JAXB Schema" );
		return schemaObj;	
	}	
	
	public JAXBContext getJAXBContext() throws JAXBException{
		log.debug("Getting JAXB context using contextName: " + contextName);
		return jaxbContextMap.get(contextName);	
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
	
	private void setNamespace(Element elem, Namespace ns, boolean recurse) {
        //elem.setNamespace(ns);
        String name = elem.getName();
        System.out.println("name: "+name);
        
        Collection<Namespace> names = elem.getAdditionalNamespaces();
        //Collection<Namespace> newNames = new ArrayList();
        System.out.println("names: "+names);
        
        for(Namespace nspace : names)
        {
        	if(nspace.getURI().trim().length() == 0)
        		continue;
        		//elem.removeNamespaceDeclaration(nspace);
        }
        //elem.setaetNamespace(newNames);
        
        if (recurse) {
                for (Object o : elem.getChildren()) {
                        setNamespace((Element) o, ns, recurse);
                }
        }
    }
	
	
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
	
	
	class ValidationHandler implements ValidationEventHandler {
		
		public boolean handleEvent(ValidationEvent e) {
			log.error("Validation Exception Event while unmarshalling: " + e.getMessage());
			return true;
		}	
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
