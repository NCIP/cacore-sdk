        
package test.gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;

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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKRESTfulTestBase;
import gov.nih.nci.system.applicationservice.ApplicationException;

public class ChainResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "Chain  RESTful Resource Test Case";
	}

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attributes are null
	 * 
	 * @throws Exception
	 */
	public void testGet() throws Exception
	{
		
	  try {
 
 		Chain searchObject = new Chain();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Chain obj = (Chain) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		String url = baseURL + "/rest/Chain/"+id;

 
		WebClient client = WebClient.create(url);
		client.type("application/xml").accept("application/xml");		
		Response response = client.get();
 
		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatus());
		}
 
 		File myFile = new File("Chain"+"XML.xml");	
 			
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

	public void testSearch() throws Exception
	{
		
	  try {
 
		
			
		String url = baseURL + "/rest/Chain/search;id=*";
		WebClient client = WebClient.create(url);
		client.type("application/xml").accept("application/xml");		
		Response response = client.get();

		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatus());
		}
 
 		File myFile = new File("Chain_Search"+"XML.xml");						
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
	  }
		
	}	



//***************************************************


	public void testChain1()
	{
		try
		{
	
		String url = baseURL + "/rest/Chain/search;id=*/pendant";
 
		WebClient client = WebClient.create(url);
		client.type("application/xml").accept("application/xml");		
		Response response = client.get();
 
		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatus());
		}
 
 		File myFile = new File("Chain_Search"+"XML.xml");						
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
 		
 		
		}
		catch(Exception e)
		{
			  e.printStackTrace();
			  ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
			  builder.type("application/xml");
			  StringBuffer buffer = new StringBuffer();
			  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			  buffer.append("<response>");
			  buffer.append("<type>ERROR</type>");
			  buffer.append("<code>INTERNAL_ERROR_4</code>");
			  buffer.append("<message>Failed to Query due to: "+e.getMessage()+"</message>");
			  buffer.append("</response>");
			  builder.entity(buffer.toString());
			  throw new WebApplicationException(builder.build());
		}

	}

	
	public void testgetPendant()
	{
		try
		{
		Chain searchObject = new Chain();
 		Collection results5 = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );
		String id = "";
		
		if(results5 != null && results5.size() > 0)
		{
			Chain obj = (Chain) ((List)results5).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
	
			String url = baseURL + "/rest/Chain/"+id+"/pendant";
			WebClient client = WebClient.create(url);
			client.type("application/xml").accept("application/xml");		
			Response response = client.get();

			if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
				org.jdom.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
			else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
						false);
				org.jdom.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
			else if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatus());
			}

			File myFile = new File("Chain_Search"+"XML.xml");						
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
 		
		}
		catch(Exception e)
		{
		  e.printStackTrace();
		  ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		  builder.type("application/xml");
		  StringBuffer buffer = new StringBuffer();
		  buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		  buffer.append("<response>");
		  buffer.append("<type>ERROR</type>");
		  buffer.append("<code>INTERNAL_ERROR_4</code>");
		  buffer.append("<message>Failed to Query due to: "+e.getMessage()+"</message>");
		  buffer.append("</response>");
		  builder.entity(buffer.toString());
		  throw new WebApplicationException(builder.build());
		}
			
		}
		
		

	


//********************************************************End

	public void testDelete() throws Exception
	{
		
	  try {
 
 		Chain searchObject = new Chain();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Chain obj = (Chain) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
		
		String url = baseURL + "/rest/Chain/"+id;
		WebClient client = WebClient.create(url);
		
		Response response = client.delete();
		
		if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
		else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
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


	public void testPost() throws Exception
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Chain";
		WebClient client = WebClient.create(url);
		HttpPost postRequest = new HttpPost(url);
		File myFile = new File("Chain"+"XML.xml");						
 		if(!myFile.exists())
 		{
 			testGet();
 			myFile = new File("Chain"+"XML.xml");
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


	public void testPut() throws Exception
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Chain";
		HttpPut putRequest = new HttpPut(url);
		File myFile = new File("Chain"+"XML.xml");						
 		if(!myFile.exists())
 		{
 			testGet();
 			myFile = new File("Chain"+"XML.xml");
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

	
}