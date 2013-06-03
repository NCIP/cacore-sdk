/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
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

import javax.ws.rs.core.Response;

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
import gov.nih.nci.system.web.client.RESTfulCreateClient;

public class OrderLineCreateClient {
	public static void main(String[] args) {
		try {
			OrderLine line = new OrderLine();
			line.setName("Microsoft");

			if (args == null || args.length != 1) {
				System.out
						.println("Usage: RESTFulReadClient <RESTful resource URL>");
				return;
			}
			String url = args[0];
			
			File myFile = marshall(line);
			RESTfulCreateClient client = new RESTfulCreateClient();
			Response response = client.create(myFile, url);
			String resStr = (String) response.getEntity();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static File marshall(OrderLine line) throws IOException, XMLUtilityException
	{
		String namespacePrefix = "gme://caCORE.caCORE/3.2/";
		String jaxbContextName = "gov.nih.nci.cacoresdk.domain.onetoone.bidirectional";
		Marshaller marshaller = new JAXBMarshaller(true,jaxbContextName,namespacePrefix);
		Unmarshaller unmarshaller = new JAXBUnmarshaller(true,jaxbContextName);
		XMLUtility myUtil = new XMLUtility(marshaller, unmarshaller);
		File myFile = new File("OrderLine.xml"); 
		FileWriter myWriter = new FileWriter(myFile);
		myUtil.toXML(line, myWriter);
		myWriter.close();
		return myFile;
	}
}