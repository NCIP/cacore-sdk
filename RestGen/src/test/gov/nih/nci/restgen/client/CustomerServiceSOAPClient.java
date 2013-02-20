package test.gov.nih.nci.restgen.client;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;

import org.junit.Test;

import gov.nih.nci.restgen.generated.client.NoSuchCustomerException;

public class CustomerServiceSOAPClient {
	
	@Test
	public void testSOAPService()
	{
		gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
		
		if(service.getWSDLDocumentLocation() == null)
		{
			java.net.URL url = null;
			try {
				url = new java.net.URL("http://localhost:29080/customerService/services/CustomerServicePort?WSDL");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			QName serviceName = new QName("http://customerservice.example.com/", "CustomerServiceService");
			service = new gov.nih.nci.restgen.generated.client.CustomerServiceService(url, serviceName);
		}
		
		gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
		try {
			java.util.List customers = port.getCustomersByName("test");
			assertNotNull(customers);
		} catch (NoSuchCustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
