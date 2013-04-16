/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.ResponseBuilder;

public class RESTfulCreateClient {
	String userName;
	String password;
	public RESTfulCreateClient()
	{

	}

	public RESTfulCreateClient(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public Response create(File fileLoc, String url)
	{
		try {
			if (url == null) {
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

			if (fileLoc == null || !fileLoc.exists()) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>INVALID_FILE</code>");
			buffer.append("<message>Invalid File given to read</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			return builder.build();
			}

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut(url);

			FileEntity input = new FileEntity(fileLoc);
			input.setContentType("application/xml");
		   	if(userName != null && password != null)
		   	{
		   		String base64encodedUsernameAndPassword = new String(
					Base64.encodeBase64((userName + ":" + password)
							.getBytes()));
		   		putRequest.addHeader("Authorization", "Basic "
					+ base64encodedUsernameAndPassword);
		   	}
			
			putRequest.setEntity(input);
			HttpResponse response = httpClient.execute(putRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			while ((output = br.readLine()) != null) {
				buffer.append(output);
			}

			ResponseBuilder builder = Response.status(Status.CREATED);
			builder.type("application/xml");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			httpClient.getConnectionManager().shutdown();
			return builder.build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Response create(Object instance, String url)
	{
		try {
			if (url == null) {
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

			if (instance == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>INVALID_INSTANCE</code>");
			buffer.append("<message>Invalid instance given to create</message>");
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
			Response r = client.put(instance);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
}