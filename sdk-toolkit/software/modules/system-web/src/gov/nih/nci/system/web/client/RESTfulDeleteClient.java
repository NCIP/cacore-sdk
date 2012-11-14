package gov.nih.nci.system.web.client;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.client.WebClient;

public class RESTfulDeleteClient {
	String userName;
	String password;
	
	public RESTfulDeleteClient()
	{
	}

	public RESTfulDeleteClient(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public Response delete(String url) {
	  try {
			if (url == null) {
				System.out
						.println("Invalid URL");
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>INVALID_URL</code>");
			buffer.append("<message>Invalid URL: "+url+"</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			return builder.build();
			}
			WebClient client = WebClient.create(url);
		client.type("application/xml").accept("application/xml");
	   	if(userName != null && password != null)
	   	{
	   		String base64encodedUsernameAndPassword = new String(
				Base64.encodeBase64((userName + ":" + password)
						.getBytes()));
	   		client.header("Authorization", "Basic "
				+ base64encodedUsernameAndPassword);
	   	}
		
		return client.delete();

	  } catch (Exception e) {
		e.printStackTrace();
	  }
	  return null;
	}
}