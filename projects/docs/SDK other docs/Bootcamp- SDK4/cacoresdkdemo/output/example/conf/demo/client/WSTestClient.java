import java.net.URL;
import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.utils.Options;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import gov.nih.nci.cabio.domain.ws.*;

import java.util.HashMap;

public class WSTestClient
{

	public static void main(String [] args) throws Exception {

		Service  service = new Service();
		Call     call    = (Call) service.createCall();

		/***************************************************************************************************************/

		QName qnGene = new QName("urn:ws.domain.cabio.nci.nih.gov", "Gene");
		call.registerTypeMapping(Gene.class, qnGene,
				new org.apache.axis.encoding.ser.BeanSerializerFactory(Gene.class, qnGene),
				new org.apache.axis.encoding.ser.BeanDeserializerFactory(Gene.class, qnGene));

		/****************************************************************************************************************/

		String url = "http://localhost:8080/example/ws/exampleService";

		call.setTargetEndpointAddress(new java.net.URL(url));
		call.setOperationName(new QName("exampleService", "queryObject"));
		call.addParameter("arg1", org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("arg2", qnGene, ParameterMode.IN);
		call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_ARRAY);

		gov.nih.nci.cabio.domain.ws.Gene gene = new gov.nih.nci.cabio.domain.ws.Gene();
		gene.setSymbol("IL*");



		try {
			Object[] resultList = (Object[])call.invoke(new Object[] { "gov.nih.nci.cabio.domain.ws.Gene", gene });

			System.out.println("Total objects found: " + resultList.length);

			if (resultList.length > 0) {
				for(int resultIndex = 0; resultIndex < resultList.length; resultIndex++) {
					Gene returnedGene = (Gene)resultList[resultIndex];
					System.out.println(
						"Symbol: " + returnedGene.getSymbol() + "\n" +
						"\tName " + returnedGene.getTitle() + "\n" +
						"");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
