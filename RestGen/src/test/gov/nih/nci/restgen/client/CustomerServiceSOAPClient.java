package test.gov.nih.nci.restgen.client;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;

import org.junit.Test;

import gov.nih.nci.restgen.generated.client.NoSuchCustomerException;

public class CustomerServiceSOAPClient {
	
	@Test
	public void testgetCustomers()
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
			java.util.List customers = port.getCustomers();
			assertNotNull(customers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testgetCustomerByName()
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
			gov.nih.nci.restgen.generated.client.Customer customer = port.getCustomerByName("customer1");
			assertNotNull(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testdeleteCustomerByName()
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
			port.deleteCustomer("customer1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
