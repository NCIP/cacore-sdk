package gov.nih.nci.system.client.util.xml;

import gov.nih.nci.system.client.util.xml.XMLUtilityException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class JAXBUnmarshaller implements gov.nih.nci.system.client.util.xml.Unmarshaller {

	private boolean validate = true;
	private String packageName;
	private Class klazz;
	
	public JAXBUnmarshaller(boolean validate, String packageName)
	{
		this.validate = validate;
		this.packageName = packageName;
	}
	
	public JAXBUnmarshaller(boolean validate)
	{
		this.validate = validate;
		this.packageName = "";
	}
	
	public Object fromXML(Reader reader) throws XMLUtilityException {
		try
		{
			if (packageName == null){
				throw new XMLUtilityException("JAXB Context package name has not been set");
			}
			JAXBContext context = JAXBMarshaller.getJAXBContext(packageName);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	
	        if(validate){
	        	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    		Source schemaFile = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream(packageName + ".xsd"));
	    		Schema schemaObj = sf.newSchema(schemaFile);
	    		unmarshaller.setSchema(schemaObj);
	        }
	        return unmarshaller.unmarshal(reader);
		}
		catch(JAXBException e)
		{
			throw new XMLUtilityException(e.getMessage(), e);
		}
		catch(SAXException e)
		{
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
			throw new XMLUtilityException(e.getMessage(), e);
		}
	}

	public Object getBaseUnmarshaller() {
		return this;
	}
	
}
