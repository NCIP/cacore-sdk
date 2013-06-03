/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.client.util.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class JAXBMarshaller implements gov.nih.nci.system.client.util.xml.Marshaller {
	
	private static Logger log = Logger.getLogger(JAXBMarshaller.class.getName());

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

			if (namespacePrefix == null || namespacePrefix.length() < 1){
				log.error("Error: Namespace Prefix is required but has not been set");
				throw new XMLUtilityException("Error: Namespace Prefix is required but has not been set");				
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
			
	        String schemaLocation = namespacePrefix + object.getClass().getPackage().getName() + " " + object.getClass().getPackage().getName() + ".xsd";
	        log.debug("Setting JAXB_SCHEMA_LOCATION to " + schemaLocation);
	        m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
			m.marshal(object, writer);
			//System.out.println(writer.toString());
		} catch(JAXBException e) {
			log.error("JAXBException caught marshalling " + object.getClass().getName(), e);
			log.debug("Marshalling using context: " + context);
			throw new XMLUtilityException(e.getMessage(), e);
		} catch(Exception e) {
			log.error("Exception caught marshalling " + object.getClass().getName(), e);
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
			log.error("Fatal Error while initializing Schema Factory: ",e);
		}
		
		public void error(org.xml.sax.SAXParseException e) throws SAXException {
			log.error("Error while initializing Schema Factory: ",e);
		}
		
		public void warning(org.xml.sax.SAXParseException e) throws SAXException {
			log.error("Warning while initializing Schema Factory: ",e);
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
				log.error("Unable to initialize JAXB Context using contextName: " + contextName,e);
			}
			
			log.debug("Marshaller has been initialized using context: " + jaxbContextMap.get(contextName));
		} else {
			useContextName = false;
			log.warn("A valid JAXB context name has not been supplied during Marshaller instantiation.  The object supplied during calls to the toXml() methods will be used instead.");
		}
	}
	
	private JAXBContext getJAXBContext() throws JAXBException{
		if (useContextName){
			log.debug("Getting JAXB context using contextName: " + contextName);
		} else {
			log.warn("A JAXB context name was not supplied during Marshaller instantiation.  Will attempt to use the object's class package name instead");
			return null;
		}
		
		return jaxbContextMap.get(contextName);	
	}		

	private JAXBContext getJAXBContext(String contextName) throws JAXBException{
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
	
}
