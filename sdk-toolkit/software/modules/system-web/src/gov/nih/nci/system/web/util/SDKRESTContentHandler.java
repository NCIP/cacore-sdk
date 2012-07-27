package gov.nih.nci.system.web.util;

import gov.nih.nci.codegen.util.TransformerUtils;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.web.ResourceLink;
import gov.nih.nci.system.web.CollectionBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.dom4j.io.DocumentResult;
import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.thoughtworks.xstream.XStream;

@Provider
@Produces("application/xml")
@Consumes("application/xml")
public class SDKRESTContentHandler implements MessageBodyReader,
		MessageBodyWriter {

	private static Logger log = Logger.getLogger(SDKRESTContentHandler.class);

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
		InputStreamReader reader = new InputStreamReader(is);
		try {
			//System.out.println("read from .............: "+is);
		//System.out.println("readFrom......................."+type.getName());
		//System.out.println("readFrom......................."+genericType);
		//System.out.println("readFrom......................."+httpHeaders);
			String packageName = type.getName().substring(0, type.getName().lastIndexOf("."));
			//System.out.println("packageName: "+packageName);

			Unmarshaller unmarshaller = new JAXBUnmarshaller(true,
					packageName);
			return unmarshaller.fromXML(reader);
		} catch (XMLUtilityException e) {
			// TODO Auto-generated catch block
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
		try {
			//System.out.println("In writing...."+target);
			//System.out.println("In writing...."+type);
			//System.out.println("In writing...."+genericType);
			if(target == null)
				return;

			OutputStreamWriter writer = new OutputStreamWriter(os);
			if(target instanceof java.lang.String)
			{
				writer.write(target.toString());
				writer.flush();
				return;
			}
			boolean includeAssociations = true;
			//SDKResponse response = (SDKResponse) target;

			//if(!response.isCollection())
			//Object convertedObj = null;
			if(!(target instanceof CollectionBean))
			{
				//Map responseObj = response.getResponse();
				//Object targetObj = responseObj.keySet().iterator().next();
				//List<ResourceLink> objLinks = (List<ResourceLink>)responseObj.get(targetObj);


				Object convertedObj = XMLUtility.convertFromProxy(target,
						false);


				String namespace = "gme://caCORE.caCORE/4.5/";
				try
				{
					Method method = type.getDeclaredMethod("getNamespacePrefix", (Class[])null);
					namespace = (String)method.invoke(target, null);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					log.error("ERROR: ", e);
				}

				List<ResourceLink> links = null;
				try
				{
					Method method = type.getDeclaredMethod("getLinks", (Class[])null);
					links = (List)method.invoke(target, null);
					//System.out.println("links: "+links);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					log.error("ERROR: ", e);
				}

				String packageName = convertedObj.getClass().getPackage().getName();
				//String packageName = type.getName().substring(0, type.getName().lastIndexOf("."));
				//System.out.println("packageName: "+packageName);
				StringWriter strWriter = new StringWriter();
				Marshaller marshaller = new JAXBMarshaller(true,
						packageName, namespace);
				marshaller.toXML(convertedObj, strWriter);
				Reader in = new StringReader(strWriter.toString());
				//System.out.println("strWriter.toString(): "+strWriter.toString());
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(in);
				Element rootEle = doc.getRootElement();
				if(links != null)
				{
					for(ResourceLink link: links)
					{
						Element linkElement = new Element("link");
						linkElement.setAttribute("ref", link.getRelationship());
						linkElement.setAttribute("type", link.getType());
						linkElement.setAttribute("href", link.getHref());
						//linkElement.setText(link.toString());
						rootEle.addContent(linkElement);
					}
				}
				XMLOutputter outputter = new XMLOutputter();
				outputter.output(doc, writer);

			}
			else
			{
				handleCollection((CollectionBean)target, writer, type);
			}

		} catch (XMLUtilityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (SecurityException e) {
			log.error("ERROR: ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (IllegalArgumentException e) {
			log.error("ERROR: ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (IllegalAccessException e) {
			log.error("ERROR: ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (InvocationTargetException e) {
			log.error("ERROR: ", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebApplicationException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleCollection(CollectionBean collectionObj, OutputStreamWriter writer, Class type) throws XMLUtilityException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, JDOMException
	{

		String collectionType = collectionObj.getType();
		gov.nih.nci.system.client.proxy.ListProxy proxy = null;
		String getMethod = "get"+collectionType.substring(collectionType.lastIndexOf(".")+1, collectionType.length())+"s";
		//System.out.println("getMethod: "+getMethod);

		try
		{
			Method method = collectionObj.getClass().getDeclaredMethod(getMethod, (Class<?>[])null);
			proxy = (gov.nih.nci.system.client.proxy.ListProxy)method.invoke(collectionObj, null);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("ERROR: ", e);
		}



		boolean includeAssociations = true;
		List results = new ArrayList();
		String targetClassName = proxy.getTargetClassName();
		//System.out.println("targetClassName: "+targetClassName);
		//Object convertedObj = null;
		int counter = proxy.size();
		String packageName = "";
		boolean isFirst = true;
		String namespace = "gme://caCORE.caCORE/4.5/";
		StringBuffer outputStr = new StringBuffer();

		//String packageName = type.getName().substring(0, type.getName().lastIndexOf("."));
		//System.out.println("packageName: "+packageName);


		Object obj = proxy.get(0);
		String collectionFullName = obj.getClass().getName();
		String collectionName = collectionFullName.substring(collectionFullName.lastIndexOf(".")+1, collectionFullName.length())+"s";
		org.jdom.Element httpQuery = new org.jdom.Element(collectionName,namespace);
		Collection<ResourceLink> collectionLinks = collectionObj.getLinks();
		if(collectionLinks != null)
		{
			for(ResourceLink link : collectionLinks)
			{
				Element linkElement = new Element("link");
				linkElement.setAttribute("ref", link.getRelationship());
				linkElement.setAttribute("type", link.getType());
				linkElement.setAttribute("href", link.getHref());
				//linkElement.setText(link.toString());
				httpQuery.addContent(linkElement);
				//httpQuery.addContent(link.toString());
			}
		}

		for (int i = 0; i < counter; i++) {
			//System.out.println("Adding: "+proxy.get(i));
			obj = proxy.get(i);
			if(obj instanceof ResourceLink)
			{
				ResourceLink link = (ResourceLink)obj;
				Element linkElement = new Element("link");
				linkElement.setAttribute("ref", link.getRelationship());
				linkElement.setAttribute("type", link.getType());
				linkElement.setAttribute("href", link.getHref());
				//linkElement.setText(link.toString());
				httpQuery.addContent(linkElement);
				//httpQuery.addContent(((ResourceLink)obj).toString());
				continue;
			}

			Marshaller marshaller = new JAXBMarshaller(false,
				packageName, namespace);

			List<ResourceLink> links = null;
			try
			{
				Method method = obj.getClass().getDeclaredMethod("getLinks", (Class[])null);
				links = (List)method.invoke(obj, null);
				//System.out.println("links: "+links);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error("ERROR: ", e);
			}

			Object convertedObj = XMLUtility.convertFromProxy(obj,
					false);
			packageName = convertedObj.getClass().getPackage().getName();
			//results.add(convertedObj);
			if(isFirst)
			{
				try
				{
					Method method = convertedObj.getClass().getDeclaredMethod("getNamespacePrefix", (Class<?>[])null);
					namespace = (String)method.invoke(convertedObj, null);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					log.error("ERROR: ", e);
				}
				isFirst = false;
			}

			StringWriter strWriter = new StringWriter();
			DocumentResult dr = new DocumentResult();
			marshaller.toXML(convertedObj, strWriter);
			//outputStr.append(strWriter.toString());
			Reader in = new StringReader(strWriter.toString());
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(in);

			//System.out.println("strWriter.toString()(): "+strWriter.toString());
			try
			{
				Element rootEle = (Element)doc.getRootElement().clone();
				if(links != null)
				{
					for(ResourceLink link: links)
					{
						//System.out.println("Adding Link: "+link.toString());
						Element linkElement = new Element("link");
						linkElement.setAttribute("ref", link.getRelationship());
						linkElement.setAttribute("type", link.getType());
						linkElement.setAttribute("href", link.getHref());
						//linkElement.setText(link.toString());
						rootEle.addContent(linkElement);
					}
				}
				httpQuery.addContent(rootEle);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//System.out.println("httpQuery()(): "+httpQuery.toString());
		}
		//httpQuery.setText(outputStr.toString());
		//System.out.println("Output: "+httpQuery.toString());
		org.jdom.Document xmlDoc = new org.jdom.Document(httpQuery);
		XMLOutputter outputter = new XMLOutputter();
		outputter.output(xmlDoc, writer);
	}


	public static void printObject(Object obj, Class klass, boolean includeAssociation) throws Exception {
		System.out.println("\nPrinting "+ klass.getName());
		Method[] methods = klass.getMethods();
		for(Method method:methods)
		{
			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
			{
				System.out.print("\t"+method.getName().substring(3)+":");
				Object val = null;
				try {
				val = method.invoke(obj, (Object[])null);
				} catch(Exception e){
					val = "ERROR - unable to determine value";

				}
				if (val instanceof java.util.Set) {
					Collection list = (Collection)val;
					for(Object object: list){
						System.out.println(object.getClass().getName()+":");
						if (includeAssociation){
							printObject(object, object.getClass(), false);
						} else {
							System.out.println(" -- association has been excluded");
						}
					}
					System.out.println("size="+((Collection)val).size());
				}
				else if(val instanceof ArrayList)
				{
					Collection list = (ArrayList) val;
					//System.out.println("\nPrinting Collection.....");
					for(Object object: list){
						//System.out.println(object.getClass().getName()+":");
						if (includeAssociation){
							printObject(object, object.getClass(), false);
						} else {
							System.out.println(" -- association has been excluded");
						}
					}
				}
				else if(val != null && val.getClass().getName().startsWith("gov.nih.nci"))
				{
					if (includeAssociation){
						printObject(val, val.getClass(), false);
					} else {
						System.out.println(" -- association has been excluded");
					}
				}
				else
					System.out.println(val);
			}
		}
	}

	private void printObject(Object obj, Class klass) throws Exception {
		printObject(obj,klass,false);
	}




}