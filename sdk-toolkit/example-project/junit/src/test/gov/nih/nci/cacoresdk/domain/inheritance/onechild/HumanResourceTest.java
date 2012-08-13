        
package test.gov.nih.nci.cacoresdk.domain.inheritance.onechild;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human;

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

public class HumanResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "Human  RESTful Resource Test Case";
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
 
 		Human searchObject = new Human();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Human obj = (Human) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Human/"+id;
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("Human"+"XML.xml");						
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

	public void testSearch() throws ApplicationException
	{
		
	  try {
 
		
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Human/search;id=*";
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("Human_Search"+"XML.xml");						
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



//********************************************************End

	public void testDelete() throws ApplicationException
	{
		
	  try {
 
 		Human searchObject = new Human();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Human obj = (Human) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Human/"+id;
		HttpDelete deleteRequest = new HttpDelete(url);
		//deleteRequest.addHeader("accept", "application/xml");
 
		HttpResponse response = httpClient.execute(deleteRequest);
		
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }
		
	}


	public void testPost() throws ApplicationException
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Human";
		HttpPost postRequest = new HttpPost(url);
		File myFile = new File("Human"+"XML.xml");						
		
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
 	  }
		
	}


	public void testPut() throws ApplicationException
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Human";
		HttpPut putRequest = new HttpPut(url);
		File myFile = new File("Human"+"XML.xml");						
		
 		FileEntity input = new FileEntity(myFile);
 		input.setContentType("application/xml");
 		putRequest.setEntity(input);
		
 		HttpResponse response = httpClient.execute(putRequest);
  
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
	  }
		
	}

	
}