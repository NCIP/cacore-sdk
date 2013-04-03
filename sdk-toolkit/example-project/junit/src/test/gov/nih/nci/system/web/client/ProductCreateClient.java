package test.gov.nih.nci.system.web.client;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;
import gov.nih.nci.system.client.util.xml.JAXBMarshaller;
import gov.nih.nci.system.client.util.xml.JAXBUnmarshaller;
import gov.nih.nci.system.client.util.xml.Marshaller;
import gov.nih.nci.system.client.util.xml.Unmarshaller;
import gov.nih.nci.system.client.util.xml.XMLUtility;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.web.client.RESTfulCreateClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProductCreateClient {
	public static void main(String[] args) {
		try {
			Product product = new Product();
			product.setName("Windows XP1");
			OrderLine line = new OrderLine();
			line.setName("Orderline_Name5");
			line.setId(Integer.valueOf(5));
			product.setLine(line);
			

			if (args == null || args.length != 1) {
				System.out
						.println("Usage: RESTFulReadClient <RESTful resource URL>");
				return;
			}
			String url = args[0];
			
			//File myFile = marshall(product);
			RESTfulCreateClient client = new RESTfulCreateClient();
			client.create(product, url);
			//client.create(myFile, url);
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