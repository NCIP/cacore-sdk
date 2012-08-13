        
package test.gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;

import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class AssistantProfessorResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "AssistantProfessor  RESTful Resource Test Case";
	}

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attributes are null
	 * 
	 * @throws ApplicationException
	 */
	public void testGet() throws ApplicationException
	{
		
	  try {
 
 		AssistantProfessor searchObject = new AssistantProfessor();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			AssistantProfessor obj = (AssistantProfessor) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor/"+id;
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("AssistantProfessor"+"XML.xml");						
		System.out.println("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			myWriter.write(output);
			System.out.println(output);
		}
 
		httpClient.getConnectionManager().shutdown();
		myWriter.flush();
 		myWriter.close();
	  } catch (Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}

	public void testSearch() throws ApplicationException
	{
		
	  try {
 
		
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor/search;id=*";
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("AssistantProfessor_Search"+"XML.xml");						
		System.out.println("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			myWriter.write(output);
			System.out.println(output);
		}
 
		httpClient.getConnectionManager().shutdown();
		myWriter.flush();
 		myWriter.close();
 		
	  } catch (Exception e) {
		e.printStackTrace();
	  }
		
	}	



//***************************************************


	public void testAssistantProfessor1()
	{
		try
		{
	
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor/search;id=*/assistantCollection";
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("AssistantProfessor_Search"+"XML.xml");						
		System.out.println("writing data to file "+myFile.getAbsolutePath());
		FileWriter myWriter = new FileWriter(myFile);

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));
 
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			myWriter.write(output);
			System.out.println(output);
		}
 
		httpClient.getConnectionManager().shutdown();
		myWriter.flush();
 		myWriter.close();
 		
 		
		}
		catch(Exception e)
		{
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

	

					
			
	public void testgetAssistantCollection()
	{
		try
		{
		AssistantProfessor searchObject = new AssistantProfessor();
 		Collection results4 = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor",searchObject );
		String id = "";
		
		if(results4 != null && results4.size() > 0)
		{
			AssistantProfessor obj = (AssistantProfessor) ((List)results4).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			

			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = baseURL + "/rest/AssistantProfessor/"+id+"/assistantCollection";
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/xml");


			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			File myFile = new File("AssistantProfessor_Search"+"XML.xml");						
			System.out.println("writing data to file "+myFile.getAbsolutePath());
			FileWriter myWriter = new FileWriter(myFile);

			BufferedReader br = new BufferedReader(
				 new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				myWriter.write(output);
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();
			myWriter.flush();
			myWriter.close();
		}
		catch(Exception e)
		{
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

	public void testDelete() throws ApplicationException
	{
		
	  try {
 
 		AssistantProfessor searchObject = new AssistantProfessor();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			AssistantProfessor obj = (AssistantProfessor) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor/"+id;
		HttpDelete deleteRequest = new HttpDelete(url);
		//deleteRequest.addHeader("accept", "application/xml");
 
		HttpResponse response = httpClient.execute(deleteRequest);
		
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
	  } catch (Exception e) {
		e.printStackTrace();
		throw e;
	  }
		
	}


	public void testPost() throws ApplicationException
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor";
		HttpPost postRequest = new HttpPost(url);
		File myFile = new File("AssistantProfessor"+"XML.xml");						
		
 		FileEntity input = new FileEntity(myFile);
 		input.setContentType("application/xml");
 		System.out.println("input: "+myFile);
 		postRequest.setEntity(input);
  
 		HttpResponse response = httpClient.execute(postRequest);
  
// 		if (response.getStatusLine().getStatusCode() != 201) {
// 			throw new RuntimeException("Failed : HTTP error code : "
// 				+ response.getStatusLine().getStatusCode());
// 		}
  
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


	public void testPut() throws ApplicationException
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/AssistantProfessor";
		HttpPut putRequest = new HttpPut(url);
		File myFile = new File("AssistantProfessor"+"XML.xml");						
		
 		FileEntity input = new FileEntity(myFile);
 		input.setContentType("application/xml");
 		putRequest.setEntity(input);
		
 		HttpResponse response = httpClient.execute(putRequest);
  
// 		if (response.getStatusLine().getStatusCode() != 201) {
// 			throw new RuntimeException("Failed : HTTP error code : "
// 				+ response.getStatusLine().getStatusCode());
// 		}
  
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