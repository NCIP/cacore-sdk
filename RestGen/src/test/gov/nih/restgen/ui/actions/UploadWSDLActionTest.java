package test.gov.nih.restgen.ui.actions;

import static org.junit.Assert.*;


import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DropMode;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.tools.common.ToolException;
import org.apache.cxf.tools.common.toolspec.ToolSpec;
import org.apache.cxf.tools.validator.WSDLValidator;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.predic8.schema.Element;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Input;
import com.predic8.wsdl.Output;
import com.predic8.wsdl.Part;
import com.predic8.wsdl.Port;
import com.predic8.wsdl.PortType;
import com.predic8.wsdl.Service;
import com.predic8.wsdl.WSDLParser;

public class UploadWSDLActionTest {
	private String serviceName = "";
	private static String serviceEndPoint = "";
	private class Operation {
		 
		 private String name;
		 private String style;
		 private String portName;
		 ArrayList<String> InputTypes = new ArrayList<String>();
		 ArrayList<String> OutputTypes = new ArrayList<String>();
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}
		public ArrayList<String> getOutputTypes() {
			return OutputTypes;
		}
		public void setOutputTypes(ArrayList<String> outputTypes) {
			OutputTypes = outputTypes;
		}
		public ArrayList<String> getInputTypes() {
			return InputTypes;
		}
		public void setInputTypes(ArrayList<String> inputTypes) {
			InputTypes = inputTypes;
		}
		public String getPortName() {
			return portName;
		}
		public void setPortName(String portName) {
			this.portName = portName;
		}
		 
	 }

	
	@Test
	public void parseWSDLFile() throws Exception {
		
		File file = new File("WSDLFile.wsdl");
		/*File file = null;
		String serviceEndPoint = "";
		String serviceName = "";
		
        file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
                SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
        if ((file == null)||(!file.exists())||(!file.isFile())) return true;
        if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
        {
            JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "This file is not a WSDL file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a WSDL file", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		*/
		char[] specialChars = {'!','@',']','#','$','%','^','&','*'}; 
	       // enter the input string here....
	       String inputString = "file:///C:\\RESTFUL-WRAPPER\\sample-data\\data-02-07\\exampleService.zip\\exampleService.wsdl";
			if(inputString!=null)
			{
				
				char[] inputStringChars = inputString.toCharArray();
				boolean specialCharIsFound = false;  

				 for(int x = 0; x < inputStringChars.length; x++)  
				 {  
					 
				   for(int y = 0; y < specialChars.length; y++)
				   {
					   
						   if(inputStringChars[x]==specialChars[y]){  
							   specialCharIsFound = true;  
							   break;  
						   }	 
					   
				   }
				   
				 }

			       if( specialCharIsFound){
			         
			    	   System.out.println("Special characters found in the URL....");
			    	   return;
			       }
			
		// added below PV
		URLConnection  uCon = null;
        URL wsdlUrl = new URL(inputString);
        uCon = wsdlUrl.openConnection();
        InputStream is = uCon.getInputStream();
        OutputStream os = new FileOutputStream(file);
        int c;
        while ((c = is.read()) != -1) {
        	System.out.print((char) c);
        	os.write(c);
        }
        is.close();
        os.close();
        
		///////////
		}
			
		else
		{
				return;
		}
        
        ///
     // validate WSDL file
        try
        {
        /////////////////
        ArrayList<String> list = new ArrayList<String>(); 
        String[] pargs = list.toArray(new String[list.size()]); 
        FileInputStream toolspecStream = new FileInputStream(file);
            ToolSpec spec = new ToolSpec(toolspecStream,false);
            toolspecStream.close(); 
            WSDLValidator validator = new WSDLValidator(spec);
            validator.execute(false);
        
        }
        catch(ToolException ex)
        {
        	throw new Exception("WSDL parse error!!!");
        	
        }
        catch(Exception ex)
        {
        	throw new Exception("WSDL parse error!!!");
        	
        }
        //
        createTargetTree(file);
	return ;
		
		
	}

	
	public static String getServiceEndPoint() {
		return serviceEndPoint;
	}


	public static void setServiceEndPoint(String serviceEndPoint) {
		UploadWSDLActionTest.serviceEndPoint = serviceEndPoint;
	}


	public void createTargetTree(File file) throws Exception
	{
		
	    	if(!file.exists())
	    	{
	    		 System.out.println("WSDL file is not present at the path...WSDL file not found!!!");
	    		return;
	    	}
	    // Display WSDL details here once the WSDL file has been selected!!
	    // parse and keep the file in XML
	    InputStream is = new FileInputStream(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();
		//
	   //PV Validate WSDL file here
	    WSDLParser parser = new WSDLParser();
	    Definitions defs = parser.parse(file.getPath());
	    ArrayList<Operation> operationsList = new ArrayList<Operation>();
	    ArrayList<String> InputTypes = null;
	    ArrayList<String> OutputTypes = null;
	    String portName = "";
	    for (PortType pt : defs.getPortTypes())
	    {       
	    	
	    	System.out.println(pt.getName());
	    	for (com.predic8.wsdl.Operation op : pt.getOperations()) {
	    			System.out.println(" -" + op.getName());
	    			String inputType = "";
	    			String outputType = "";
	    			InputTypes = new ArrayList<String>();
	    			OutputTypes = new ArrayList<String>();
	    			String style = getSOAPOperationStyle(doc,op.getName());
	    			Operation opObject = new Operation();
	    			opObject.setName(op.getName());
	    			opObject.setStyle(style);
	    			Input input = op.getInput();
	    			if (input != null && input.getMessage() != null) {
	    		   		System.out.println("Input Message...."+input.getMessage());
	                    Collection<Part> parts = CastUtils.cast(input.getMessage().getParts());
	                    System.out.println("Parts size...."+input.getMessage());
	                    for (Part part : parts) {
	                    	System.out.println("Element part...."+part.getName());
	                    	if (part.getElement() != null && !"".equals(part.getElement())) {
	                    		
	                        	if(defs.getElement(part.getElement())!=null)
	                        	{
	                        		Element type = defs.getElement(part.getElement());
	                        		//inputType +=type.getType();
	                        		if(type.getType()!=null)
	                        		{
	                        			inputType = type.getType().toString();
	                        			if(inputType!=null && !inputType.equals(""))
	                        			{
	                        				InputTypes.add(inputType);
	                        			}
	                        		}
	                        	}
	                        	
	                        }
	                    	else
	                    	{
	                    		//inputType +=part.getType();
	                    		inputType = part.getType().toString();
	                    		if(inputType!=null && !inputType.equals(""))
	                    		{
	                    			InputTypes.add(inputType);
	                    		}
	                    		
	                    	}
	                    }
	                }
	                Output output = op.getOutput();
	                if (output != null && output.getMessage() != null) {
	                	System.out.println("Output Message...."+output.getMessage());
	                    Collection<Part> parts = CastUtils.cast(output.getMessage().getParts());
	                    for (Part part : parts) {
	                        if (part.getElement() != null && !"".equals(part.getElement())) {
	                        	Element type = defs.getElement(part.getElement());
	                        	outputType += type.getType(); 
	                        }
	                        else
	                    	{
	                        	outputType +=part.getType();
	                    	}
	                        
	                    }
	                }
	               
	                System.out.println("WSDL input type and output>>>>"+"...."+inputType+outputType);
	                OutputTypes.add(outputType);
	                opObject.setInputTypes(InputTypes);
	                opObject.setOutputTypes(OutputTypes);
	                // get port name here
	                portName = getSOAPOperationPortName(doc, defs.getServices());
	                // get port name here
	                opObject.setPortName(portName);
	                operationsList.add(opObject);
	    		}  
	    }
	    java.util.List<Service>  services = defs.getServices();
		for (Service svc : services)
		{
			serviceName = svc.getName();
			
			for (Port port: svc.getPorts())
			{
				
				serviceEndPoint= port.getAddress().getLocation();
				/*if(!ping(serviceEndPoint,REQUESTTIMEOUT))
				{
					throw new Exception("Request timed out WSDL end point invalid");
				}*/
				System.out.println("SOAP Endpoint : "  + port.getAddress().getLocation());
				
			}
		}
		setServiceName(serviceName);
		setServiceEndPoint(serviceEndPoint);
		
	  //PV Validate WSDL file here
	    
	    		
	}
	    
	    
	public String getServiceName() {
		return serviceName;
	}


	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	private String getSOAPOperationStyle(org.w3c.dom.Document doc, String operationName) {
		// TODO Auto-generated method stub
		String style = "";
		org.w3c.dom.NodeList listOfOperations = doc.getElementsByTagName("operation");
	    
	    for(int s=0; s<listOfOperations.getLength() ; s++){
	    	org.w3c.dom.Node nNode = listOfOperations.item(s);
	    		
	    	   
	 		   if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().equals(operationName)) {

	 			  org.w3c.dom.Element firstSessionElement = ( org.w3c.dom.Element)nNode;
	    		NodeList soapOperationsList = firstSessionElement.getElementsByTagName("soap:operation");
	    		if(soapOperationsList != null && soapOperationsList.getLength() > 0) {
	    			for(int i=0; i<soapOperationsList.getLength() ; i++){
	    				org.w3c.dom.Element el = (org.w3c.dom.Element)soapOperationsList.item(i);
	    				style = el.getAttribute("style");
	    			System.out.println("style value......>>>>>"+el.getAttribute("style"));
	    			}
	    		}
	    		break;
	 		   }
	    }
	    
	    return style;
	}

	private String getSOAPOperationPortName(org.w3c.dom.Document doc, java.util.List<Service>  services) {
		// TODO Auto-generated method stub
		String Binding = "";
		String portName = "";
		System.out.println("inside getSOAPOperationPortName>>>>>");
		org.w3c.dom.NodeList listOfBindings = doc.getElementsByTagName("wsdl:binding");
		for(int s=0; s<listOfBindings.getLength() ; s++){
	    	org.w3c.dom.Node nNode = listOfBindings.item(s);
	    	   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	 			  org.w3c.dom.Element firstSessionElement = ( org.w3c.dom.Element)nNode;
	 			  Binding = firstSessionElement.getAttribute("name");
	    		  NodeList soapOperationsList = firstSessionElement.getElementsByTagName("wsdl:operation");
	    		if(soapOperationsList != null && soapOperationsList.getLength() > 0) {
	    			for(int i=0; i<soapOperationsList.getLength(); i++){
	    			org.w3c.dom.Element el = (org.w3c.dom.Element)soapOperationsList.item(i);
	    			portName = getWSDLOperationPortName(services, Binding);
	    			System.out.println("port name value......>>>>>"+portName);
	    			
	    			}
	    		}
	    	
	 		   }
	    }
	    
	    return portName;
	}


	private String getWSDLOperationPortName(java.util.List<Service>  services, String binding)
	{
		String portName = "";
		System.out.println("BINDING>>>> : "  + binding);
		for (Service svc : services)
		{
			serviceName = svc.getName();
			
			for (Port port: svc.getPorts())
			{
				System.out.println("PORT BINDING...>>>> : "  + port.getBinding().getName());
				if(binding.equals(port.getBinding().getName()))
				{
					portName = port.getName();
				}
				
			}
		}
		
		return portName;
	}

		
	
}
