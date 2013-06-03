/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

        
package test.gov.nih.nci.restgen.client;

import gov.nih.nci.restgen.generated.client.Customer;

import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.FileEntity;
import org.apache.cxf.jaxrs.client.WebClient;

import org.apache.cxf.common.util.Base64Utility;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

public class CustomerServiceClient
{
	public static String BASE_URL = "http://localhost:29080/restful";
	@BeforeClass 
	public static void testSetup(){
	    // Preparation of the unit tests
	  }
	  
	  @AfterClass 
	  public static void testCleanup(){
	    // Teardown for data used by the unit tests
	  }	
	  
	  @Test
	public void testDelete() throws Exception
	{
		
	  try {
 
 		Customer searchObject = new Customer();
		String id = "1";
		
		String url = BASE_URL + "/customer/"+id;
		WebClient client = WebClient.create(url);
		
		Response response = client.delete();
		
		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			throw new RuntimeException("Request is not acceptable");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			throw new RuntimeException("Id not found");
		}
 		else if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatus());
		}
	  } catch (Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}

	  @Test
	public void testPost() throws Exception
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = BASE_URL + "/customer";
		WebClient client = WebClient.create(url);
		HttpPost postRequest = new HttpPost(url);
		File myFile = new File("CustomerXML.xml");						
 		if(!myFile.exists())
 		{
 			testGet();
 			myFile = new File("CustomerXML.xml");
 			if(!myFile.exists())
				return;
 		}
		
 		FileEntity input = new FileEntity(myFile);
 		input.setContentType("application/xml");
 		System.out.println("input: "+myFile);
 		postRequest.setEntity(input);
  
 		HttpResponse response = httpClient.execute(postRequest);
  
 		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));
  
 		String output;
 		System.out.println("Output from Server .... \n");
 		while ((output = br.readLine()) != null) {
 			System.out.println(output);
 		}
  
 		httpClient.getConnectionManager().shutdown();
	  } catch (Exception e) {
 		e.printStackTrace();
 		throw e;
 	  }
		
	}

	@Test
	public void testPut() throws Exception
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = BASE_URL + "/customer";
		HttpPut putRequest = new HttpPut(url);
		File myFile = new File("CustomerXML.xml");						
 		if(!myFile.exists())
 		{
 			testGet();
 			myFile = new File("CustomerXML.xml");
 			if(!myFile.exists())
				return;
 		}
		
 		FileEntity input = new FileEntity(myFile);
 		input.setContentType("application/xml");
 		putRequest.setEntity(input);
		
 		HttpResponse response = httpClient.execute(putRequest);
  
  		if(response.getEntity() != null)
  		{
			BufferedReader br = new BufferedReader(
				 new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
  		}
  		
 		httpClient.getConnectionManager().shutdown();
	  } catch (Exception e) {
 		e.printStackTrace();
 		throw e;
	  }
		
	}

	@Test
	public void testGet() throws Exception
	{
		
	  try {
 
		String name = "test";
		String url = BASE_URL + "/customer/"+name;
 
		WebClient client = WebClient.create(url);
		client.type("application/xml").accept("application/xml");		
		Response response = client.get();
 
		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			throw new RuntimeException("Request is not acceptable");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			throw new RuntimeException("Id not found");
		}
 		else if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatus());
		}
 
 		File myFile = new File("CustomerXML.xml");	
 			
		System.out.println("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);

		BufferedReader br = new BufferedReader(
                         new InputStreamReader(((InputStream)response.getEntity())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			myWriter.write(output);
			System.out.println(output);
		}
 
		myWriter.flush();
 		myWriter.close();
	  } catch (Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}	
	
}