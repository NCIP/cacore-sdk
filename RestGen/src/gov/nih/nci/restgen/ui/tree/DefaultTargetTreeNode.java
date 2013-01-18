/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.tree;

/**
 * This class extends the default mutable tree node as the tree node used to
 * construct Target Tree for right-pane MetaData.
 * One of primary reasons to have a distinct class is for differentiation purpose for future use of instanceof, for example.
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE
 * @since     CMTS v1.0
 * @version    $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 *
 */
public class DefaultTargetTreeNode extends DefaultMappableTreeNode
{
	String ImplementationType = ""; // can be either SOAP or EJB
	String serviceName = ""; // WSDL service name 
	String endPoint = ""; // WSDL end point
	String clientType = ""; // for EJB it can be remote/local
	String operationName = "";// operation name for EJB or WSDL
	String inputType = ""; //input for the operation
	String outputType = "";//output for the operation
	
	public DefaultTargetTreeNode(Object userObject, boolean allowsChildren)
	{
		super(userObject, allowsChildren);
	}

	public DefaultTargetTreeNode(Object userObject)
	{
		super(userObject);
	}

	public String getImplementationType() {
		return ImplementationType;
	}

	public void setImplementationType(String implementationType) {
		ImplementationType = implementationType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
}

/**
 * HISTORY: $Log: not supported by cvs2svn $
 */

