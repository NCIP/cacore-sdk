package test.gov.nih.nci.restgen.client;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import gov.nih.nci.restgen.codegen.WebserviceClientGenerator;
import gov.nih.nci.restgen.generated.WSQueryImpl;
import gov.nih.nci.restgen.mapping.XMLUtilityException;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SDKExampleSOAPClient {
	@BeforeClass 
	public static void testSetup(){
	    // Preparation of the unit tests
	  }
	  
	  @AfterClass 
	  public static void testCleanup(){
	    // Teardown for data used by the unit tests
	  }	
	  
	  @Test
	  public void testExampleService() throws XMLUtilityException, Exception
	  {
		  
		  //JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		  //URL wsdlURL = new URL("http://localhost:29080/example/services/exampleService?WSDL");
		  //Client client = dcf.createClient(wsdlURL);

		  //Object[] res = client.invoke("echo", "test echo");		  
		  
		  URL wsdlURL = new URL("http://localhost:29080/example/services/exampleService?WSDL");
		  QName SERVICE_NAME = new QName("http://localhost:29080/example/services/exampleService", "WSQueryImplService");
		  Service service = Service.create(wsdlURL, SERVICE_NAME);
		  WSQueryImpl client = service.getPort(WSQueryImpl.class);
		  Class klass = Class.forName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		  Object o = klass.newInstance();
		  client.queryObject("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher", o);
		  
		 // WSQueryImplService service = new WSQueryImplService();
		 // WSQueryImpl client = service.getExampleService();
		 // Class klass = Class.forName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		 // Object o = klass.newInstance();
		 // client.queryObject("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher", o);
		  /*
	        try {
	        	localhost.example.services.exampleservice.WSQueryImplServiceStub stub = 
	                    new localhost.example.services.exampleservice.WSQueryImplServiceStub();
	        	localhost.example.services.exampleservice.WSQueryImplServiceStub.Query query = 
	        			new localhost.example.services.exampleservice.WSQueryImplServiceStub.Query();
	        	query.setTargetClassName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
	        	Class klass = Class.forName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
	        	Object o = klass.newInstance();
	        	query.setCriteria(o);
	        	query.setStartIndex(1);
	        	localhost.example.services.exampleservice.WSQueryImplServiceStub.QueryResponse response = stub.query(query);
				System.out.println("Return "+response.getQueryReturn());
	        } catch (org.apache.axis2.AxisFault
				e) {
				e.printStackTrace();
	        } catch (java.rmi.RemoteException
				e) {
				e.printStackTrace();
	        }
		  */
	  }
	  
	  
		public void etestClientGenerator() throws Exception {
			String outputFolder = "C:\\DEV\\RestGen\\src";
			URL url = new URL("http://localhost:29080/example/services/exampleService?WSDL");
			File file = new File(
					"C:\\DEV\\RestGen\\examples\\sdk\\exampleService.wsdl");
			//String url = "http://localhost:29080/example/services/exampleService?WSDL";
			String namespace = "http://localhost:29080/example/services/exampleService";

			WebserviceClientGenerator generator = new WebserviceClientGenerator(
					outputFolder,
					url.toString(),
					namespace);
			generator.generate();
		}
	  
}
