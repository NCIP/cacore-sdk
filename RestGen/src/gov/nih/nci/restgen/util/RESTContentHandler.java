package gov.nih.nci.restgen.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

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
		InputStreamReader reader = new InputStreamReader(is);
		try {
			String packageName = type.getName().substring(0,
					type.getName().lastIndexOf("."));

			gov.nih.nci.restgen.mapping.JAXBUnmarshaller unmarshaller = new gov.nih.nci.restgen.mapping.JAXBUnmarshaller(
					true, packageName);
			return unmarshaller.fromXML(reader);
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
		try {
			if (target == null)
				return;
			writer = new OutputStreamWriter(os);
			if (target instanceof java.lang.String) {
				writer.write(target.toString());
				writer.flush();
				return;
			}
			strWriter = new StringWriter();
			gov.nih.nci.restgen.mapping.JAXBMarshaller marshaller = new gov.nih.nci.restgen.mapping.JAXBMarshaller(
					true, type.getPackage().getName(), null);

			marshaller.toXML(new JAXBElement(new QName("uri", "local"), type,
					target), strWriter);
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
}