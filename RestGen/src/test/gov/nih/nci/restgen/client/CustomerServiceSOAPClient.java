package test.gov.nih.nci.restgen.client;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;

import gov.nih.nci.restgen.generated.client.NoSuchCustomerException;

public class CustomerServiceSOAPClient {
	public static void main(String args[])
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
			port.getCustomersByName("test");
		} catch (NoSuchCustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
