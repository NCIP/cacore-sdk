package gov.nih.nci.system.client.util.xml;

import java.io.File;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * Class used to convert domain objects to and from XML.
 */
public class XMLUtility {

	private static Logger log = Logger.getLogger(XMLUtility.class.getName());

	private Marshaller marshaller;

	private Unmarshaller unmarshaller;

	public XMLUtility(Marshaller marshaller,
			Unmarshaller unmarshaller) {
		
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}

	/**
	 * 
	 * Serializes an object into xml
	 * 
	 * @param beanObject
	 *            The caCORE object to serialize into xml
	 */
	public String toXML(Object beanObject) throws XMLUtilityException {
		StringWriter strWriter = new StringWriter();
		toXML(beanObject, strWriter);
		return strWriter.toString();
	}

	public void toXML(Object beanObject, Writer stream)
			throws XMLUtilityException {
		marshaller.toXML(beanObject, stream);
	}
	

	public void toXML(Object beanObject, Writer stream, String namespacePrefix)
			throws XMLUtilityException {
		
		if (!(unmarshaller instanceof JAXBUnmarshaller)){
			throw new XMLUtilityException("Invalid method invocation.  This method is only valid when the Marshaller is a JAXBMarshaller instance");
		}
		
		((JAXBMarshaller)marshaller).toXML(beanObject, stream, namespacePrefix);
	}
	
	static public Object convertFromProxy(Object obj, boolean getAssociation) throws XMLUtilityException {
		
		Object convertedObject = null;
		try {
			CastorDomainObjectFieldHandler handler = new CastorDomainObjectFieldHandler();
			convertedObject = handler.convertObject(obj,getAssociation);
		} catch(Exception e){
			log.error("Exception caught trying to convert from proxy to domain object: ", e);
			throw new XMLUtilityException("Exception caught trying to convert from proxy to domain object: ", e);
		}
		
		return convertedObject;
	}

	
	static public Collection convertFromProxy(Object obj) throws XMLUtilityException {
		
		Collection convertedCollection = null;
		try {
			CastorCollectionFieldHandler handler = new CastorCollectionFieldHandler();
			convertedCollection = (Collection)handler.convertUponGet(obj);
		} catch(Exception e){
			log.error("Exception caught trying to convert from proxy to domain object: ", e);
			throw new XMLUtilityException("Exception caught trying to convert from proxy to domain object: ", e);
		}
		
		return convertedCollection;
	}
	/**
	 * Instantiates an object from an XML File that contains the serialized output
	 * of that object.
	 * 
	 * @param xmlFile
	 * @return
	 * @throws XMLUtilityException
	 */
	public Object fromXML(File xmlFile) throws XMLUtilityException {
		Object beanObject = null;
		beanObject = unmarshaller.fromXML(xmlFile);
		return beanObject;
	}
	
	/**
	 * Instantiates an object from an XML File that contains the serialized output
	 * of that object. This method should only be used with a JAXBUnmarshaller instance
	 * 
	 * @param xmlFile
	 * @return
	 * @throws XMLUtilityException
	 */	
	public Object fromXML(Class klazz, File xmlFile) throws XMLUtilityException {
		
		if (!(unmarshaller instanceof JAXBUnmarshaller)){
			throw new XMLUtilityException("Invalid method invocation.  This method is only valid when the unmarshaller is a JAXBUnmarshaller instance");
		}
		
		Object beanObject = null;
		beanObject = ((JAXBUnmarshaller)unmarshaller).fromXML(klazz,xmlFile);
		return beanObject;
	}
	
	/**
	 * Instantiates an object from an XML File that contains the serialized output
	 * of that object. This method should only be used with a JAXBUnmarshaller instance
	 * 
	 * @param xmlFile
	 * @return
	 * @throws XMLUtilityException
	 */	
	public Object fromXML(String packageName, File xmlFile) throws XMLUtilityException {
		
		if (!(unmarshaller instanceof JAXBUnmarshaller)){
			throw new XMLUtilityException("Invalid method usage.  This method is only valid when the Marshaller is a JAXBUnmarshaller instance");
		}
		
		Object beanObject = null;
		beanObject = ((JAXBUnmarshaller)unmarshaller).fromXML(packageName,xmlFile);
		return beanObject;
	}	

	/**
	 * Instantiates an object from xml input that contains the serialized output
	 * of that object.
	 * 
	 * @param input
	 *            Reader type
	 * @return Instantiated object
	 * @throws XMLUtilityException
	 */
	public Object fromXML(Reader input) throws XMLUtilityException {
		Object beanObject;
		beanObject = unmarshaller.fromXML(input);
		return beanObject;
	}
}
