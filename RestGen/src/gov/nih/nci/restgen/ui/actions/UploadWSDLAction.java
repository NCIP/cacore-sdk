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

import com.predic8.schema.Element;
import com.predic8.wsdl.*;


import javax.swing.*;
import javax.xml.namespace.QName;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
	private String serviceEndPoint = "";
    //hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK, false);

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

    public String getServiceEndPoint() {
		return serviceEndPoint;
	}

	public void setServiceEndPoint(String serviceEndPoint) {
		this.serviceEndPoint = serviceEndPoint;
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
    				File file = null;
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
    	            mainFrame.getMainFrame().getMappingMainPanel().setMappingTargetFile(file);
    	            mainFrame.getMainFrame().getMappingMainPanel().setTargetFileType("WSDL");
    	            createTargetTree(file);
    			return true;

    }

public void createTargetTree(File file) throws Exception
{
	
    try
    {
    // Display WSDL details here once the WSDL file has been selected!!
    mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder("SOAP Webservice"));
   //PV Validate WSDL file here
    WSDLParser parser = new WSDLParser();
    Definitions defs = parser.parse(file.getPath());
    ArrayList<String> operationsList = new ArrayList<String>();
    ArrayList<String> InputTypes = new ArrayList<String>();
    ArrayList<String> OutputTypes = new ArrayList<String>();
    
  
    for (PortType pt : defs.getPortTypes())
    {       
    	System.out.println(pt.getName());
    	for (Operation op : pt.getOperations()) {
    			System.out.println(" -" + op.getName());
    			String inputType = "";
    			String outputType = "";
    			operationsList.add(op.getName());
    			Input input = op.getInput();
    			
                if (input != null && input.getMessage() != null) {
                    Collection<Part> parts = CastUtils.cast(input.getMessage().getParts());
                    for (Part part : parts) {
                        if (part.getElement() != null || !"".equals(part.getElement())) {
                        	Element type = defs.getElement(part.getElement());
                        	inputType +=type.getType();
                        	
                        }
                    }
                }
                Output output = op.getOutput();
                if (output != null && output.getMessage() != null) {
                    Collection<Part> parts = CastUtils.cast(output.getMessage().getParts());
                    for (Part part : parts) {
                        if (part.getElement() != null || !"".equals(part.getElement())) {
                        	Element type = defs.getElement(part.getElement());
                        	outputType += type.getType(); 
                        }
                    }
                }
                System.out.println("WSDL input type and output>>>>"+"...."+inputType+outputType);
                OutputTypes.add(outputType);
                InputTypes.add(inputType);
    		}  
    }
    java.util.List<Service>  services = defs.getServices();
	for (Service svc : services)
	{
		serviceName = svc.getName();
		for (Port port: svc.getPorts())
		{
			serviceEndPoint= port.getAddress().getLocation();
			if(!ping(serviceEndPoint,REQUESTTIMEOUT))
			{
				throw new Exception("Request timed out WSDL end point invalid");
			}
			System.out.println("SOAP Endpoint : "  + port.getAddress().getLocation());
		}
	}
	setServiceName(serviceName);
	setServiceEndPoint(serviceEndPoint);
	mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("Name:"+serviceName+"\n"+"Endpoint:"+serviceEndPoint);
  //PV Validate WSDL file here
    
    /// form the tree here PV...start
    
    	DefaultTargetTreeNode top = new DefaultTargetTreeNode("WSDL");
    	createNodes(top,operationsList,InputTypes,OutputTypes);
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
        mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Save").setEnabled(true);
        mainFrame.getMainFrame().getMappingMainPanel().setTargetTree(tree);
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
    
    
private void createNodes(DefaultTargetTreeNode top,ArrayList<String> list, ArrayList<String> InputType, ArrayList<String> OutputType ) {
		
	    Iterator<String> it = list.iterator();
	    Iterator<String> inputList = InputType.iterator();
	    Iterator<String> outputList = OutputType.iterator();
	    while(it.hasNext())
	    {
	    	String operationName = (String)it.next();
	    	DefaultTargetTreeNode childElement = new DefaultTargetTreeNode(operationName);
	    	childElement.setOperationName(operationName);
	    	childElement.setInputType((String)inputList.next());
	    	childElement.setOutputType((String)outputList.next());
	    	childElement.setServiceName(getServiceName());
	    	childElement.setEndPoint(getServiceEndPoint());
	    	childElement.setImplementationType("SOAP");
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
