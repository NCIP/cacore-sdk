        
package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey;

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

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKRESTfulTestBase;
import gov.nih.nci.system.applicationservice.ApplicationException;

public class LongPrimitiveKeyResourceTest extends SDKRESTfulTestBase
{
	public static String getTestCaseName()
	{
		return "LongPrimitiveKey  RESTful Resource Test Case";
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
 
 		LongPrimitiveKey searchObject = new LongPrimitiveKey();
 		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey",searchObject );
		String id = "";
		
		if(results != null && results.size() > 0)
		{
			LongPrimitiveKey obj = (LongPrimitiveKey) ((List)results).get(0);
		
				Long idVal = obj.getId();
				id = new Long(idVal).toString();
			
		}
		else
			return;

		if(id.equals(""))
			return;
			
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = baseURL + "/rest/LongPrimitiveKey/"+id;
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/xml");

 
		HttpResponse response = httpClient.execute(getRequest);
 
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
 
 		File myFile = new File("LongPrimitiveKey"+"XML.xml");						
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
	  } catch (ClientProtocolException e) {
 
		e.printStackTrace();
 
	  } catch (IOException e) {
 
		e.printStackTrace();
	  }
		
	}

}