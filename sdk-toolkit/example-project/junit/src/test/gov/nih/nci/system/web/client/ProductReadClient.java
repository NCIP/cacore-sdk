/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.system.web.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import gov.nih.nci.system.web.client.RESTfulReadClient;

public class ProductReadClient {
	public static void main(String[] args) {
		InputStream is = null;
		try {
			if (args == null || args.length != 1) {
				System.out
						.println("Usage: RESTFulReadClient <RESTful resource URL>");
				return;
			}
			String url = args[0];
			RESTfulReadClient client = new RESTfulReadClient();
			Response response = client.read(url);
			
			is = (InputStream) response.getEntity();
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			System.out.println(outputter.outputString(jDoc));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if(is != null)
				is.close();
		}

	}
}