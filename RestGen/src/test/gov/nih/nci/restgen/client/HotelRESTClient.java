/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.client;

import static org.junit.Assert.*;
import gov.nih.nci.ejb.Book;
import gov.nih.nci.restgen.generated.client.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelRESTClient {
	public static String BASE_URL = "http://localhost:29080/restful";

	@BeforeClass
	public static void testSetup() {
		// Preparation of the unit tests
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test
	public void testBooks() throws Exception {

		try {
			String url = BASE_URL + "/catalog/books";
			WebClient client = WebClient.create(url);

			Response response = client.get();
			 
			if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder(
						false);
				org.jdom2.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
			else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder(
						false);
				org.jdom2.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
	 		else if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatus());
			}
	 
	 		File myFile = new File("Books"+"XML.xml");	
	 			
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

	@Test
	public void testBook() throws Exception {

		try {
			String url = BASE_URL + "/catalog/book/test";
			WebClient client = WebClient.create(url);

			Response response = client.get();
			 
			if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder(
						false);
				org.jdom2.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
			else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
				InputStream is = (InputStream) response.getEntity();
				org.jdom2.input.SAXBuilder builder = new org.jdom2.input.SAXBuilder(
						false);
				org.jdom2.Document jDoc = builder.build(is);
				assertEquals(jDoc.getRootElement().getName(), "response");
			}
	 		else if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatus());
			}
	 
	 		File myFile = new File("Book"+"XML.xml");	
	 			
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
	
	@Test
	public void testPost() throws Exception {

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = BASE_URL + "/catalog/";
			//WebClient client = WebClient.create(url);
			HttpPost postRequest = new HttpPost(url);
			File myFile = new File("BookXML.xml");
			if (!myFile.exists()) {
				testBook();
				myFile = new File("BookXML.xml");
				if (!myFile.exists())
					return;
			}

			FileEntity input = new FileEntity(myFile);
			input.setContentType("application/xml");
			System.out.println("input: " + myFile);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

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
	public void testPut() throws Exception {

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			String url = BASE_URL + "/catalog/";
			HttpPut putRequest = new HttpPut(url);
			File myFile = new File("BookXML.xml");
			if (!myFile.exists()) {
				testBook();
				myFile = new File("BookXML.xml");
				if (!myFile.exists())
					return;
			}

			FileEntity input = new FileEntity(myFile);
			input.setContentType("application/xml");
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);

			if (response.getEntity() != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(response.getEntity().getContent())));

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
	public void testDelete() throws Exception {

		try {
			String url = BASE_URL + "/catalog/1";
			WebClient client = WebClient.create(url);
			
			Response response = client.delete();

			if (response.getStatus() == Status.NOT_ACCEPTABLE.getStatusCode()) {
				throw new RuntimeException("Request is not acceptable");
			} else if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
				throw new RuntimeException("Id not found");
			} else if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			if (response.getEntity() != null) {
				BufferedReader br = new BufferedReader(
                        new InputStreamReader(((InputStream)response.getEntity())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}