package gov.nih.nci.restgen.util;

import gov.nih.nci.restgen.ui.common.ActionConstants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;

import test.gov.nih.nci.restgen.client.Book;

@Provider
@Produces("application/xml")
@Consumes("application/xml")
public class RESTContentHandler implements MessageBodyReader, MessageBodyWriter {

	private static Logger log = Logger.getLogger(RESTContentHandler.class);

	public boolean isReadable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return Serializable.class.isAssignableFrom(type);
	}

	public boolean isWriteable(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return Serializable.class.isAssignableFrom(type);
	}

	public Object readFrom(Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, InputStream is) throws IOException,
			WebApplicationException {
		try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller u = context.createUnmarshaller();
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(is);        

            try
            {
                JAXBElement je3 = (JAXBElement)u.unmarshal(xmlr, type);
                return je3.getValue();
            }
            catch(UnmarshalException e)
            {
            	e.printStackTrace();
                throw new JAXBException("Failed to unmarshall : " + type.getName() + " due to; " + e.getMessage());
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            	throw new JAXBException("Failed to unmarshall : " + type.getName() + " due to; " + e.getMessage());
            }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public long getSize(Object o, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	public void writeTo(Object target, Class type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap httpHeaders, OutputStream os) throws IOException,
			WebApplicationException {
		OutputStreamWriter writer = null;
		StringWriter strWriter = null;
		//System.out.println("Write to................");
		try {
			if (target == null)
				return;
			writer = new OutputStreamWriter(os);
			if (target instanceof java.lang.String) {
				writer.write(target.toString());
				writer.flush();
				return;
			}
			//System.out.println(" Package " + type.getPackage().getName());
			if(target instanceof java.util.List)
			{
				//System.out.println("Handling Collection..");
				marshallTypeCollection((List)target, type, writer);
			}
			else
			{
				JAXBContext jc = JAXBContext.newInstance( type.getPackage().getName() );
				Marshaller u = jc.createMarshaller();
				u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
				String qName = type.getName();
				if(type.getName().indexOf(".") > 0)
				{
					String temp = type.getName().substring(type.getName().lastIndexOf(".")+1);
					qName = (temp.charAt(0)+"").toLowerCase() + temp.substring(1);
				}
				
				u.marshal(new JAXBElement(new QName(qName),type, target), writer);
			}
			//strWriter = new StringWriter();
			//gov.nih.nci.restgen.mapping.JAXBMarshaller marshaller = new gov.nih.nci.restgen.mapping.JAXBMarshaller(
			//		true, type.getPackage().getName(), null);

			//marshaller.toXML(new JAXBElement(new QName("uri", "local"), type,
			//		target), strWriter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		} finally {
			if (writer != null) {
				writer.close();
				writer = null;
			}
			if (strWriter != null) {
				strWriter.close();
				strWriter = null;
			}
		}
	}
	
	//marshall collection of T
	private  <T> void marshallTypeCollection(List<T> value,
	        Class<T> clzz, OutputStreamWriter os) {
	    try {
	    	if(value == null || value.size() == 0)
	    		return;
	    	
	        Object obj = value.get(0);
	        String className = obj.getClass().getName();
			String qName = obj.getClass().getName();
			if(clzz.getName().indexOf(".") > 0)
			{
				String temp = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1);
				qName = (temp.charAt(0)+"").toLowerCase() + temp.substring(1, temp.length());
			}
			String collectionName = className.substring(className.lastIndexOf(".")+1)+"s";
			org.jdom2.Element httpQuery = new org.jdom2.Element(collectionName, "");

			Iterator iterator = value.iterator();
			JAXBContext jc = JAXBContext.newInstance( obj.getClass().getPackage().getName());
			Marshaller u = jc.createMarshaller();
			u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			StringWriter strWriter = null;
			Reader in = null;

			try {
			while(iterator.hasNext())
			{
				Object objValue = iterator.next();
				strWriter = new StringWriter();
				u.marshal(new JAXBElement(new QName(qName),obj.getClass(), objValue), strWriter);
				in = new StringReader(strWriter.toString());
				org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder();
				org.jdom2.Document	doc = builder.build(in);
				org.jdom2.Element rootEle = (org.jdom2.Element)doc.getRootElement().clone();
				httpQuery.addContent(rootEle);
				strWriter.flush();
				in.close();
				strWriter.close();
			}
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			org.jdom2.Document xmlDoc = new org.jdom2.Document(httpQuery);
			org.jdom2.output.XMLOutputter outputter = new org.jdom2.output.XMLOutputter();
			try {
				outputter.output(xmlDoc, os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	}

	//marshall collection of T
	private  <T> void marshallXmlTypeCollection(List<T> value,
	        Class<T> clzz, OutputStreamWriter os) {
	    try {
	        ListHolder<T> holder = new ListHolder<T>(value);
	        Class[] classes = new Class[2];
	        classes[0] = ListHolder.class;
	        Object obj = value.get(0);
	        //System.out.println("obj.getClass() "+obj.getClass());
	        classes[1] = obj.getClass();
	        JAXBContext context = JAXBContext.newInstance(
	                classes);
	        Marshaller m = context.createMarshaller();
	        m.setProperty("jaxb.formatted.output", true);

	        m.marshal(holder, os);
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	}
	
	//unmarshall collection of T
	@SuppressWarnings("unchecked")
	public static <T> List<T> unmarshallXmlTypeCollection(Class<T> clzz,
	        InputStream input) {
	    try {
	        JAXBContext context = JAXBContext.newInstance(ListHolder.class, clzz);
	        Unmarshaller u = context.createUnmarshaller();

	        ListHolder<T> holder = (ListHolder<T>) u.unmarshal(new StreamSource(input));

	        return holder.getValue();
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }

	    return null;
	}	
}