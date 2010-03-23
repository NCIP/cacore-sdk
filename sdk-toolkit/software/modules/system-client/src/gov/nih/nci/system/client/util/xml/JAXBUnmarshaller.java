package gov.nih.nci.system.client.util.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class JAXBUnmarshaller implements gov.nih.nci.system.client.util.xml.Unmarshaller {
	
	private static Logger log = Logger.getLogger(JAXBUnmarshaller.class.getName());

	private boolean validate = true;
	private String contextName;
	private String packageName;
	private Class klazz;
	
	public JAXBUnmarshaller(boolean validate, String packageName)
	{
		this.validate = validate;
		this.contextName = packageName;
	}
	
	public JAXBUnmarshaller(boolean validate)
	{
		this.validate = validate;
		this.contextName = "";
	}
	
	public Object fromXML(Reader reader) throws XMLUtilityException {
		JAXBContext context=null;
		try
		{
			if (contextName == null){
				throw new XMLUtilityException("JAXB Context package name has not been set");
			}

			context = JAXBMarshaller.getJAXBContext(contextName);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	
	        if(validate){
	        	Schema schemaObj = JAXBMarshaller.getJAXBSchema();
				if (schemaObj == null){
					throw new XMLUtilityException("JAXB Validation Schema has not been set within the JAXB Marshaller");
				}
//	        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//	        	String schemaFileName = packageName + ".xsd";
//	        	log.debug("Unmarshalling using schema file name: " + schemaFileName);
//	    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(schemaFileName));
//	    		Schema schemaObj = sf.newSchema(schemaFile);
	        	log.debug("Unmarshalling using Validation Schema obtained from the JAXB Marshaller");
	    		unmarshaller.setSchema(schemaObj);
	    		
	        }
	        unmarshaller.setEventHandler(new ValidationHandler());
	        
	        return unmarshaller.unmarshal(reader);
		}
		catch(JAXBException e)
		{
			log.error("JAXBException caught Unmarshalling from: " + reader, e);
			log.debug("Unmarshalling using context: " + context.toString());

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
	
	class ValidationHandler implements ValidationEventHandler {
		
		public boolean handleEvent(ValidationEvent e) {
			log.error("Validation Exception Event while unmarshalling: " + e.getMessage());
			return true;
		}	
	}
	
}
