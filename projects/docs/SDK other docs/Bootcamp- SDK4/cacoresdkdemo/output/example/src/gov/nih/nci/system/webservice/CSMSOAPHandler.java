package gov.nih.nci.system.webservice;

import java.util.Iterator;

import gov.nih.nci.common.util.ClientInfo;
import gov.nih.nci.common.util.ClientInfoThreadVariable;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.SecurityConfiguration;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;

import javax.wsdl.extensions.soap.SOAPHeaderFault;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;


public class CSMSOAPHandler extends BasicHandler
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SecurityEnabler securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());

	public void invoke(MessageContext messageContext) throws AxisFault
	{
		if (securityEnabler.getSecurityLevel() > 0)
		{
            Message message = messageContext.getRequestMessage();
            SOAPEnvelope  envelope = message.getSOAPEnvelope();
            SOAPHeader header = null;
			try
			{
				header = envelope.getHeader();
			}
			catch (SOAPException e)
			{
				e.printStackTrace();
			}
			Iterator iterator = header.examineAllHeaderElements();
			SOAPHeaderElement headerElement ;
			while (iterator.hasNext())
			{
				headerElement = (SOAPHeaderElement) iterator.next();
				if (headerElement.getNodeName().equals("csm:CSMSecurityHeader"));
				{
					String username = null;
					String password = null;
					Iterator childElementsIterator = headerElement.getChildElements();
					while (childElementsIterator.hasNext())
					{
						SOAPElement element = (SOAPElement)childElementsIterator.next();
						if (element.getNodeName().equals("csm:username"))
							username = element.getValue();
						if (element.getNodeName().equals("csm:password"))
							password = element.getValue();
					}
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setUserName(username);
					clientInfo.setPassword(password);
					ClientInfoThreadVariable.setClientInfo(clientInfo);
				}
			}
			return;
		}
	}
}
