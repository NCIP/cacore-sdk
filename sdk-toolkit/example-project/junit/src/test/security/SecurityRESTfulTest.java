/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.security;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.client.WebClient;

public class SecurityRESTFulTest extends SDKSecurityTestBase {
	protected String baseURL = "http://localhost:21080/examplerest";

	public static String getTestCaseName() {
		return "RESTFul Resource Security Test Case";
	}

	/**
	 * Uses Nested Search Criteria for search Verifies that the results are
	 * returned Verifies size of the result set Verifies that none of the
	 * attributes are null
	 *
	 * @throws Exception
	 */
	public void testGet() throws Exception {

		try {

			String url = baseURL + "/rest/Bank/1";

			WebClient client = WebClient.create(url);
			client.type("application/xml").accept("application/xml");
			String userName = "SDKUser1";
			String password = "Psat123!@#";
			String base64encodedUsernameAndPassword = new String(
					Base64.encodeBase64((userName + ":" + password).getBytes()));
			client.header("Authorization", "Basic "
					+ base64encodedUsernameAndPassword);

			Response response = client.get();
			assertEquals(response.getStatus(), 200);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Uses Nested Search Criteria for search Verifies that the results are
	 * returned Verifies size of the result set Verifies that none of the
	 * attributes are null
	 *
	 * @throws Exception
	 */
	public void testPrivateTeacher() throws Exception {

		try {

			String url = baseURL + "/rest/PrivateTeacher/1";

			WebClient client = WebClient.create(url);
			client.type("application/xml").accept("application/xml");
			String userName = "SDKUser1";
			String password = "Psat123!@#";
			String base64encodedUsernameAndPassword = new String(
					Base64.encodeBase64((userName + ":" + password).getBytes()));
			client.header("Authorization", "Basic "
					+ base64encodedUsernameAndPassword);

			Response response = client.get();
			assertEquals(response.getStatus(),
					Status.INTERNAL_SERVER_ERROR.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
