/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.actions;


import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.tools.common.ToolException;
import org.apache.cxf.tools.common.toolspec.ToolSpec;
import org.apache.cxf.tools.validator.WSDLValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.predic8.schema.Element;
import com.predic8.wsdl.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;




/**
 * This class defines the exit action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class UploadWSDLAction extends AbstractContextAction
{
    private static final String COMMAND_NAME = ActionConstants.UPLOADWSDL;
    private static final Character COMMAND_MNEMONIC = new Character('U');
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Upload WSDL file";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".wsdl";
	private static final int REQUESTTIMEOUT=400000;
	private JTree tree;
	private String serviceName = "";
	private static String serviceEndPoint = "";
	private Boolean verbose = true; 
	private Boolean quiet = true;
    //hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK, false);

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
	
	
    private MainFrameContainer mainFrame;
    
    

    /**
     * Defines an <code>Action</code> object with a default
     * description string and default icon.
     */
    public UploadWSDLAction(MainFrameContainer mainFrame)
    {
        this(COMMAND_NAME, mainFrame);
    }

    public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
     * Defines an <code>Action</code> object with the specified
     * description string and a default icon.
     */
    public UploadWSDLAction(String name, MainFrameContainer mainFrame)
    {
        this(name, (Icon) null, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a the specified icon.
     */
    public UploadWSDLAction(String name, Icon icon, MainFrameContainer mainFrame)
    {
        super(name, icon);
        this.mainFrame = mainFrame;
        setMnemonic(COMMAND_MNEMONIC);
        //hotkey//setAcceleratorKey(ACCELERATOR_KEY_STROKE);
        setActionCommandType(DESKTOP_ACTION_TYPE);
        //do not know how to set the icon location name, or just do not matter.
    }

    public static String getServiceEndPoint() {
		return serviceEndPoint;
	}

	public static void setServiceEndPoint(String serviceEndPoint) {
		serviceEndPoint = serviceEndPoint;
	}
	/**
     * The abstract function that descendant classes must be overridden to provide customsized handling.
     *
     * @param e
     * @return true if the action is finished successfully; otherwise, return false.
     * @throws Exception 
     */
    protected boolean doAction(ActionEvent e) throws Exception
    {
    				// open WSDL here PV
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
    			       
    			       String inputString = JOptionPane.showInputDialog(null, "Please enter the URL for WSDL file : ", 
    							"WSDL file upload", 1);
    			       try
    			       {
    					if(inputString!=null)
    					{
    						mainFrame.getMainFrame().getMappingMainPanel().setWsdlURL(inputString);
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
    					         
    					    	   JOptionPane.showMessageDialog(mainFrame.getMainFrame().getMappingMainPanel(), "Please enter a valid path...", "Invalid Path Entry!!!", JOptionPane.ERROR_MESSAGE);
    					       }
    					
    				// added below PV
    				URLConnection  uCon = null;
    		        URL wsdlUrl = new URL(inputString);
    		        uCon = wsdlUrl.openConnection();
    		        InputStream is = uCon.getInputStream();
    		        OutputStream os = new FileOutputStream(file);
    		        int c;
    		        while ((c = is.read()) != -1) {
    		        	//System.out.print((char) c);
    		        	os.write(c);
    		        }
    		        is.close();
    		        os.close();
    		        
   				///////////
    				}
    					
    				else
    				{
    						return false;
    				}
    			  }
    			    catch(MalformedURLException ex)
    			    {
    			    	   throw new Exception("WSDL URL is invalid!!!");
    			    }
    			    catch(Exception ex)
       			    {
       			    	   throw ex;
       			    }
    	            mainFrame.getMainFrame().getMappingMainPanel().setMappingTargetFile(file);
    	            mainFrame.getMainFrame().getMappingMainPanel().setTargetFileType("WSDL");
    	            /// clear the panels here
    	            
    	            if(mainFrame.getMainFrame().getMappingMainPanel().getTargetTree()!=null)
    	    		{
    	            	mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraphController().handleDeleteAll();
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setViewportView(null);
    	    			mainFrame.getMainFrame().getMappingMainPanel().setTargetTree(null);
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder(""));
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("");
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetButtonPanel().removeAll();
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetRadioButtonPanel().setBorder(BorderFactory.createTitledBorder(""));
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetRadioButtonPanel().removeAll();
    	    			mainFrame.getMainFrame().getMappingMainPanel().getTargetButtonPanel().updateUI();
    	    			//mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setBackground(new Color(212,208,200));
    	    			
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
    			return true;

    }

@SuppressWarnings("deprecation")
public void createTargetTree(File file) throws Exception
{
	
    try
    {
    	if(!file.exists())
    	{
    		JOptionPane.showMessageDialog(mainFrame.getMainFrame().getMappingMainPanel(), "WSDL file is not present at the path...", "WSDL file not found!!!", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    // Display WSDL details here once the WSDL file has been selected!!
    mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder("SOAP Webservice"));
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
    java.util.List<Binding> Bindings =  defs.getBindings();
    ArrayList<Operation> operationsList = new ArrayList<Operation>();
    ArrayList<String> InputTypes = null;
    ArrayList<String> OutputTypes = null;
    String portName = "";
    for (PortType pt : defs.getPortTypes())
    {       
    	
    	//System.out.println(pt.getName());
    	for (com.predic8.wsdl.Operation op : pt.getOperations()) {
    			//System.out.println(" -" + op.getName());
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
    		   		//System.out.println("Input Message...."+input.getMessage());
                    Collection<Part> parts = CastUtils.cast(input.getMessage().getParts());
                    //System.out.println("Parts size...."+input.getMessage());
                    for (Part part : parts) {
                    	//System.out.println("Element part...."+part.getName());
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
                	//System.out.println("Output Message...."+output.getMessage());
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
               
                //System.out.println("WSDL input type and output>>>>"+"...."+inputType+outputType);
                OutputTypes.add(outputType);
                opObject.setInputTypes(InputTypes);
                opObject.setOutputTypes(OutputTypes);
                // get port name here
                portName = getSOAPOperationPortName(Bindings, defs.getServices());
                //System.out.println("Port Name...<<<<"+portName);
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
			//System.out.println("SOAP Endpoint : "  + port.getAddress().getLocation());
			
		}
	}
	setServiceName(serviceName);
	setServiceEndPoint(serviceEndPoint);
	String WSDLBindingFile = mainFrame.getMainFrame().getMappingMainPanel().getWSDLBindingFilePath(); 
	mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("Name:"+serviceName+"\n\n"+"Endpoint:"+serviceEndPoint);
	if(WSDLBindingFile!=null && !WSDLBindingFile.equals(""))
	{
		mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().append("\n\n"+"WSDL Binding file:"+file.getPath());
	}
	
	mainFrame.getMainFrame().getMappingMainPanel().createOpenWSDLBindingFileButton();
	
	
  //PV Validate WSDL file here
    
    /// form the tree here PV...start
    
    	DefaultTargetTreeNode top = new DefaultTargetTreeNode("WSDL");
    	createNodes(top,operationsList);
        tree = new JTree(top);
        TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(mainFrame.getMainFrame().getMappingMainPanel().getGraphController());
		tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
		tree.setTransferHandler(new TreeTransferHandler(mainFrame.getMainFrame().getMappingMainPanel()));
		tree.setDropMode(DropMode.ON);
		tree.setDragEnabled(true);
		//GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
		//mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraph().setTransferHandler(gDropHandler);
		tree.setDragEnabled(true);
		int size = tree.getRowCount();
			for (int i = 0; i < size+100; i++)
			{
				if (i<tree.getRowCount())
					tree.expandRow(i);
			}
        mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setViewportView(tree);
        mainFrame.getMainFrame().getMappingMainPanel().setTargetTree(tree);
        if(!mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").isEnabled())
		{
        	 mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").setEnabled(true);
		}
    /// end
    
    }
    catch(Exception ex)
    { 
    	if(ex.toString().contains("ParseError"))
    	{
    		throw new Exception("Parser error!! Invalid document");
    	}
    	else
    	{
    		
    		throw ex;
    		
    	}
    }
	
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
    			//System.out.println("style value......>>>>>"+el.getAttribute("style"));
    			}
    		}
    		break;
 		   }
    }
    
    return style;
}

private String getSOAPOperationPortName(java.util.List<Binding> bindings, java.util.List<Service>  services) {
	// TODO Auto-generated method stub
	String Binding = "";
	String portName = "";
	//System.out.println("Bindings List size....."+bindings.size());
	Iterator<Binding> iter =  bindings.iterator();
	  while(iter.hasNext())
	  {
		  Binding = ((Binding)iter.next()).getName();
		  //System.out.println("Binding names....."+Binding);
		  portName = getWSDLOperationPortName(services,Binding );
	  }
	
    return portName;
}


private String getWSDLOperationPortName(java.util.List<Service>  services, String binding)
{
	String portName = "";
	//System.out.println("BINDING>>>> : "  + binding);
	for (Service svc : services)
	{
		serviceName = svc.getName();
		
		for (Port port: svc.getPorts())
		{
		
			if(binding.equals(port.getBinding().getName()))
			{
				portName = port.getName();
			}
			
		}
	}
	
	return portName;
}




private void createNodes(DefaultTargetTreeNode top,ArrayList<Operation> list) {
		
		Iterator<Operation> it = list.iterator();
	    
	    while(it.hasNext())
	    {
	    	Operation op = (Operation)it.next();
	    	String operationName = op.getName();
	    	DefaultTargetTreeNode childElement = null;
	    	ArrayList inputlist = op.getInputTypes();
	    	String inputString = "";
	    	
	    	for(int i=0 ;i<inputlist.size();i++)
	    	{
	    		inputString = inputString+inputlist.get(i);
	    		if(inputlist.size()>1)
				{
					if(i < inputlist.size()-1)
					{
						inputString = inputString +", ";
					}
				}
	    	}
	    	if(inputString!=null && !inputString.equals(""))
	    	{
	    		childElement = new DefaultTargetTreeNode(operationName+"("+inputString+")");
	    	}
	    	else
	    	{
	    		childElement = new DefaultTargetTreeNode(operationName+"()");
	    	}
	    	childElement.setOperationName(operationName);
	    	childElement.setInputType(op.getInputTypes());
	    	childElement.setOutputType(op.getOutputTypes().get(0));
	    	childElement.setServiceName(getServiceName());
	    	childElement.setEndPoint(getServiceEndPoint());
	    	childElement.setOperationStyle(op.getStyle());
	    	childElement.setImplementationType("SOAP");
	    	childElement.setPortName(op.getPortName());
	    	top.add(childElement);
	    }
	    
	}

//public static boolean ping(String url, int timeout) {
public static boolean ping(String url, int timeout) {
	url = "http://google.com";//rem later PV
    url = url.replaceFirst("https", "http"); // Otherwise an exception may be thrown on invalid SSL certificates.

    try {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        return (200 <= responseCode && responseCode <= 399);
    } catch (IOException exception) {
        return false;
    }
}


    /**
     * Return the associated UI component.
     *
     * @return the associated UI component.
     */
    protected Component getAssociatedUIComponent()
    {
        return mainFrame.getAssociatedUIComponent();
    }
}

/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.1  2008/12/09 19:04:17  linc
 * HISTORY      : First GUI release
 * HISTORY      :
 * HISTORY      : Revision 1.1  2008/12/03 20:46:14  linc
 * HISTORY      : UI update.
 * HISTORY      :
 */
