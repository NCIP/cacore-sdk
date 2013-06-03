/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.client;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import javax.xml.namespace.QName;

import org.junit.Test;

import gov.nih.nci.restgen.generated.client.Hotel;
import gov.nih.nci.restgen.generated.client.NoSuchCustomerException;

public class HotelSOAPClient {
	
	@Test
	public void testgetHotel()
	{
		gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
		
		if(service.getWSDLDocumentLocation() == null)
		{
			java.net.URL url = null;
			try {
				url = new java.net.URL("http://localhost:8080/HotelSOAPService/services/HotelService?wsdl");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			QName serviceName = new QName("http://hotel.example.com", "HotelService");
			service = new gov.nih.nci.restgen.generated.client.HotelService(url, serviceName);
		}
		
		gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
		try {
			Hotel hotel = port.getHotel("test");
			assertNotNull(hotel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
