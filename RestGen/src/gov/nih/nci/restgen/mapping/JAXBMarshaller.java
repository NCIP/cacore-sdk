package gov.nih.nci.restgen.mapping;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class JAXBMarshaller implements gov.nih.nci.restgen.mapping.Marshaller {
	
	private Map<String, JAXBContext> jaxbContextMap = new HashMap<String, JAXBContext>();
	private boolean includeXmlDeclaration = true;
	private String contextName;
	private String namespacePrefix;
	
	private boolean useContextName = false;
	
	public JAXBMarshaller(boolean includeXmlDeclaration)
	{
		this.includeXmlDeclaration = includeXmlDeclaration;
	}
	
	public JAXBMarshaller(boolean includeXmlDeclaration, String contextName)
	{
		this.includeXmlDeclaration = includeXmlDeclaration;
		setContextName(contextName);
	}
	
	public JAXBMarshaller(boolean includeXmlDeclaration, String contextName, String namespacePrefix)
	{
		this.includeXmlDeclaration = includeXmlDeclaration;
		setContextName(contextName);
		this.namespacePrefix = namespacePrefix;
	}
	
	public void toXML(Object object, Writer writer, String namespacePrefix) throws XMLUtilityException {
		this.namespacePrefix = namespacePrefix;
		toXML(object,writer);
	}
	
	public void toXML(Object object, Writer writer) throws XMLUtilityException {
		JAXBContext context = null;
		try {
			if (object == null){
				throw new XMLUtilityException("Error:  Object supplied to the marshaller is null.  The marshalling process has been aborted.");
			}

			if (useContextName) {
				context = getJAXBContext();
			}
			
			if (context == null){
				context = getJAXBContext(object.getClass().getPackage().getName());
				
				if (context == null){
					throw new XMLUtilityException("Error:  Unable to determine the JAXB context using the supplied object's class package name.");
				}
			}
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			if (includeXmlDeclaration){
				m.setProperty(Marshaller.JAXB_FRAGMENT, false);
			} else {
				m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			}
			
			if(namespacePrefix != null)
			{
				String schemaLocation = namespacePrefix + object.getClass().getPackage().getName() + " " + object.getClass().getPackage().getName() + ".xsd";
				m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
			}
			m.marshal(object, writer);
			//System.out.println(writer.toString());
		} catch(JAXBException e) {
			throw new XMLUtilityException(e.getMessage(), e);
		} catch(Exception e) {
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public void toXML(JAXBElement element, Writer writer) throws XMLUtilityException {
		JAXBContext context = null;
		try {
			if (element == null){
				throw new XMLUtilityException("Error:  Object supplied to the marshaller is null.  The marshalling process has been aborted.");
			}

			if (useContextName) {
				context = getJAXBContext();
			}
			
			if (context == null){
				context = getJAXBContext(element.getValue().getClass().getPackage().getName());
				
				if (context == null){
					throw new XMLUtilityException("Error:  Unable to determine the JAXB context using the supplied object's class package name.");
				}
			}
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			if (includeXmlDeclaration){
				m.setProperty(Marshaller.JAXB_FRAGMENT, false);
			} else {
				m.setProperty(Marshaller.JAXB_FRAGMENT, true);
			}
			
			if(namespacePrefix != null)
			{
				String schemaLocation = namespacePrefix + element.getValue().getClass().getPackage().getName() + " " + object.getClass().getPackage().getName() + ".xsd";
				m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
			}
			m.marshal(element.getValue(), writer);
			//System.out.println(writer.toString());
		} catch(JAXBException e) {
			throw new XMLUtilityException(e.getMessage(), e);
		} catch(Exception e) {
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}
	
	public String toXML(Object object) throws XMLUtilityException {
		StringWriter strWriter = new StringWriter();
		toXML(object, strWriter);
		return strWriter.toString();
	}
	
	public Object getBaseMarshaller() throws XMLUtilityException {
		return this;
	}
	
	class SchemaErrorHandler implements ErrorHandler {
		
		public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
		}
		
		public void error(org.xml.sax.SAXParseException e) throws SAXException {
		}
		
		public void warning(org.xml.sax.SAXParseException e) throws SAXException {
		}		
	}
	
	private void setContextName(String contextName){
		if (contextName != null && contextName.length() > 0) {
			useContextName = true;
			this.contextName = contextName;
			
			//Initialize JAXB Context
			try {
				getJAXBContext(contextName);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			useContextName = false;
		}
	}
	
	private JAXBContext getJAXBContext() throws JAXBException{
		if (!useContextName){
			return null;
		}
		
		return jaxbContextMap.get(contextName);	
	}		

	private JAXBContext getJAXBContext(String contextName) throws JAXBException{
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
	
}
