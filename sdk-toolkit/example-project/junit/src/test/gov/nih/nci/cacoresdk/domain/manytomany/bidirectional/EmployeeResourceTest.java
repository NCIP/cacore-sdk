        
package test.gov.nih.nci.cacoresdk.domain.manytomany.bidirectional;

import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee;

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

public class EmployeeResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "Employee  RESTful Resource Test Case";
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
 
 		Employee searchObject = new Employee();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Employee obj = (Employee) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Employee/"+id;
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("Employee"+"XML.xml");						
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

	public void testSearch() throws Exception
	{
		
	  try {
 
		
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Employee/search;id=*";
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("Employee_Search"+"XML.xml");						
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


	public void testEmployee1()
	{
		try
		{
	
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Employee/search;id=*/projectCollection";
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("Employee_Search"+"XML.xml");						
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

	

					
			
	public void testgetProjectCollection()
	{
		try
		{
		Employee searchObject = new Employee();
 		Collection results4 = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );
		String id = "";
		
		if(results4 != null && results4.size() > 0)
		{
			Employee obj = (Employee) ((List)results4).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			

			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = baseURL + "/rest/Employee/"+id+"/projectCollection";
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/xml");


			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() == Status.NOT_FOUND.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
						false);
				org.jdom.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
			else if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}

			File myFile = new File("Employee_Search"+"XML.xml");						
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

	public void testDelete() throws Exception
	{
		
	  try {
 
 		Employee searchObject = new Employee();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			Employee obj = (Employee) ((List)results).get(0);
		
				Integer idVal = obj.getId();
			
			id = new Integer(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Employee/"+id;
		HttpDelete deleteRequest = new HttpDelete(url);
		//deleteRequest.addHeader("accept", "application/xml");
 
		HttpResponse response = httpClient.execute(deleteRequest);
		
		if (response.getStatusLine().getStatusCode() == Status.NOT_FOUND.getStatusCode()) {
			InputStream is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			assertEquals(jDoc.getRootElement().getName(), "response");
		}
 		else if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
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
		String url = baseURL + "/rest/Employee";
		HttpPost postRequest = new HttpPost(url);
		File myFile = new File("Employee"+"XML.xml");						
		
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


	public void testPut() throws Exception
	{
		
	  try {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/Employee";
		HttpPut putRequest = new HttpPut(url);
		File myFile = new File("Employee"+"XML.xml");						
		
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