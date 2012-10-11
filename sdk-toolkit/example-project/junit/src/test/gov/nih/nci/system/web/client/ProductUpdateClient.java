package test.gov.nih.nci.system.web.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.web.client.RESTfulUpdateClient;

public class ProductUpdateClient {
	public static void main(String[] args) {
		try {
			Product product = new Product();
			product.setName("IPad2");
			OrderLine line = new OrderLine();
			line.setName("IPad");
			line.setId(Integer.valueOf(6));
			product.setLine(line);
			

			if (args == null || args.length != 1) {
				System.out
						.println("Usage: RESTFulReadClient <RESTful resource URL>");
				return;
			}
			String url = args[0];
			
			File myFile = marshall(product);
			RESTfulUpdateClient client = new RESTfulUpdateClient();
			client.update(myFile, url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static File marshall(Product product) throws IOException, XMLUtilityException
	{
		String namespacePrefix = "gme://caCORE.caCORE/3.2/";
		String jaxbContextName = "gov.nih.nci.cacoresdk.domain.onetoone.bidirectional";
		Marshaller marshaller = new JAXBMarshaller(true,jaxbContextName,namespacePrefix);
		Unmarshaller unmarshaller = new JAXBUnmarshaller(true,jaxbContextName);
		XMLUtility myUtil = new XMLUtility(marshaller, unmarshaller);
		File myFile = new File("Product.xml"); 
		FileWriter myWriter = new FileWriter(myFile);
		myUtil.toXML(product, myWriter);
		myWriter.close();
		return myFile;
	}
}