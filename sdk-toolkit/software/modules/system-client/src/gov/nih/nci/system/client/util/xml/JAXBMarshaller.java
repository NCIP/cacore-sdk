package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.system.client.util.xml.XMLUtilityException;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class JAXBMarshaller implements gov.nih.nci.system.client.util.xml.Marshaller {
	
	private static Logger log = Logger.getLogger(JAXBMarshaller.class.getName());

	public static Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();

	private boolean validate = true;
	
	public JAXBMarshaller(boolean validate)
	{
		this.validate = validate;
	}
	
	public static JAXBContext getJAXBContext(String contextName) throws JAXBException{
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

	public String toXML(Object object) throws XMLUtilityException {
		StringWriter strWriter = new StringWriter();
		toXML(object, strWriter);
		return strWriter.toString();
	}

	public void toXML(Object object, Writer writer) throws XMLUtilityException{
		try{
			JAXBContext context = getJAXBContext(object.getClass().getPackage().getName());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        if(validate){
	        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(object.getClass().getPackage().getName() + ".xsd"));
	    		Schema schemaObj = sf.newSchema(schemaFile);
	    		m.setSchema(schemaObj);
	        }
			m.marshal(object, writer);
		}
		catch(JAXBException e)
		{
			log.error("JAXBException caught marshalling " + object.getClass().getName(), e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
		catch(SAXException e)
		{
			log.error("SAXException caught marshalling " + object.getClass().getName(), e);
			throw new XMLUtilityException(e.getMessage(), e);
		}
		
	}
}
