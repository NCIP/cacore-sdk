package test.gov.nih.nci.system.web.util;

import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.web.SDKQuery;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.cxf.jaxrs.client.WebClient;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;

public class RESTFulCXFTest {
	public static void main(String[] args) {
		java.util.List provider = new java.util.ArrayList(1);
		JAXBContextProvider prov = new JAXBContextProvider();
		provider.add(prov);
		WebClient client = WebClient.create("http://localhost:29080/example/cxf", provider);
		
		PrivateTeacher searchObject = new PrivateTeacher();
		java.util.List list = new java.util.ArrayList(1);
		list.add(searchObject);
		SDKQuery query = new SDKQuery("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher", list);
		
		Response r = client.path("RESTful/XML").accept(MediaType.APPLICATION_XML).post(query);
		String queryStr = "Bank&Credit[@id=3]&roleName=issuingBank";
		
		try
		{
		//Response r = client.path("RESTful/XML").query("query", queryStr).accept(MediaType.APPLICATION_XML).get();
        
		InputStream is = (InputStream)r.getEntity();
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(
				new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
				System.out.println(writer.toString());
		}		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:29080/example/cxf").build();
	}

}