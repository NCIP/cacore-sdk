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
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;

import com.predic8.wsdl.*;
//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.message.MessagePanel;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Type;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;



/**
 * This class defines the exit action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class UploadEJBJarAction extends AbstractContextAction
{
    private static final String COMMAND_NAME = ActionConstants.UPLOADEJBJAR;
    private static final Character COMMAND_MNEMONIC = new Character('U');
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Upload EJB JAR file";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".jar";
	private JTree tree;
    //hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK, false);

    private MainFrameContainer mainFrame;
    private ArrayList<String> EJBOperationsList = new ArrayList<String>();
    private ArrayList<String> EJBNameList = new ArrayList<String>();

    /**
     * Defines an <code>Action</code> object with a default
     * description string and default icon.
     */
    public UploadEJBJarAction(MainFrameContainer mainFrame)
    {
        this(COMMAND_NAME, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a default icon.
     */
    public UploadEJBJarAction(String name, MainFrameContainer mainFrame)
    {
        this(name, (Icon) null, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a the specified icon.
     */
    public UploadEJBJarAction(String name, Icon icon, MainFrameContainer mainFrame)
    {
        super(name, icon);
        this.mainFrame = mainFrame;
        setMnemonic(COMMAND_MNEMONIC);
        //hotkey//setAcceleratorKey(ACCELERATOR_KEY_STROKE);
        setActionCommandType(DESKTOP_ACTION_TYPE);
        //do not know how to set the icon location name, or just do not matter.
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
    	// open EJB Jar here PV
    				File file = null;
    				String serviceEndPoint = "";
    				String serviceName = "";
    	            file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
    	                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
    	            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
    	            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
    	            {
    	                JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "This file is not a EJB Jar file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a WSDL file", JOptionPane.ERROR_MESSAGE);
    	                return false;
    	            }
    	        
    	            // Display EJB Jar details here once the EJB Jar file has been selected!!
    	            mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder("EJB"));
    	           //PV Validate EJB Jar file here
    	            JarFile jarFile = new JarFile(file);
    	            boolean ejbjarxml = false;
    	            Enumeration jarEntries = jarFile.entries();
    	            JarEntry jarEntry = null;
    	           
    	            while (jarEntries.hasMoreElements())
    	            {
    	              jarEntry = (JarEntry)jarEntries.nextElement();
    	              if(jarEntry.getName().contains("ejb-jar.xml"))
    	              {
    	            	  ejbjarxml = true;
    	            	  break;
    	              }

    	           }
    	           // JarEntry jarEntry = jarFile.getJarEntry("META-INF\\ejb-jar.xml");
    	            if (ejbjarxml) {
    	            	InputStream is = jarFile.getInputStream(jarEntry);
    	            	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	            	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	            	Document doc = dBuilder.parse(is);
    	            	doc.getDocumentElement().normalize();
    	            //do something with the doc
    	            	populateEntriesFromDoc(doc);
    	            }
    	            else
    	            {
    	            	
    	            	JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "ejb-jar.xml not found in the jar!!", file.getName(), JOptionPane.ERROR_MESSAGE);
    	                return false;
    	            	
    	            }
    	            if(EJBNameList.size()>0)
    	            {
    	            	String EJBDisplayName=(String)EJBNameList.get(0);
    	            	for(int i=1;i<EJBNameList.size();i++)
    	            	{
    	            		EJBDisplayName = EJBDisplayName+", "+(String)EJBNameList.get(i);
    	            	}
    	            	mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("Name:"+EJBDisplayName);
    	            }
    	          //PV Validate EJB Jar file here
    	            
    	            /// form the tree here PV...start
    	            
    	            	DefaultTargetTreeNode top = new DefaultTargetTreeNode("EJB");
    	            	if(EJBOperationsList.size()>0)
    	            	{
    	            		createNodes(top,EJBOperationsList,file);
    	            	}
    	                tree = new JTree(top);
    	                TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(mainFrame.getMainFrame().getMappingMainPanel().getGraphController());
    	        		tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
    	        		tree.setTransferHandler(new TreeTransferHandler(mainFrame.getMainFrame().getMappingMainPanel()));
    	        		tree.setDropMode(DropMode.ON);
    	        		tree.setDragEnabled(true);
    	        		//GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
    	        		//mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraph().setTransferHandler(gDropHandler);
    	    			tree.setDragEnabled(true);
    	                mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setViewportView(tree);
    	                mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Save").setEnabled(true);
    	            /// end
    	            
    	            
    			return true;

    }
    
 
    	            
private void populateEntriesFromDoc(Document doc)
{
	
    NodeList listOfSessionBeans = doc.getElementsByTagName("session");
    
    for(int s=0; s<listOfSessionBeans.getLength() ; s++){
    	
    	Node nNode = listOfSessionBeans.item(s);
    		
 		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

    		Element firstSessionElement = (Element)nNode;
    		NodeList ejbNameNodeList = firstSessionElement.getElementsByTagName("ejb-name");
    		if(ejbNameNodeList != null && ejbNameNodeList.getLength() > 0) {
    			for(int i=0; i<ejbNameNodeList.getLength() ; i++){
    			Element el = (Element)ejbNameNodeList.item(i);
    			String textVal = el.getFirstChild().getNodeValue();
    			EJBNameList.add(textVal);
    			System.out.println("ejb-name>>>>>"+textVal);
    			}
    		}
    		
    		NodeList ejbRemoteInterfaceList = firstSessionElement.getElementsByTagName("remote");
    		if(ejbRemoteInterfaceList != null && ejbRemoteInterfaceList.getLength() > 0) {
    			for(int j=0; j<ejbNameNodeList.getLength() ; j++){
    			Element el = (Element)ejbRemoteInterfaceList.item(j);
    			String textVal = el.getFirstChild().getNodeValue();
    			EJBOperationsList.add(textVal);
    			System.out.println("remote interface>>>>:"+textVal);
    			}
    		}
    		
    		
 	 }
 		   
    }
    
}
     	            
    	            
private void createNodes(DefaultTargetTreeNode top,ArrayList<String> list, File file) throws IOException {
		
	    Iterator<String> it = list.iterator();
	    JarFile jarFile = new JarFile(file);
	    while(it.hasNext())
	    {
	    	String remoteInterfaceClass = (String)it.next();
	    	System.out.println("remote interface class name begin>>>>:"+remoteInterfaceClass);
	    	String [] classNameSplit = remoteInterfaceClass.trim().split("\\.");
	    	if(classNameSplit!=null && classNameSplit.length > 0)
	    	{
	    		String splitRemoteInterfaceClass  = classNameSplit[classNameSplit.length-1];
	    		System.out.println("remote interface class name>>>>:"+splitRemoteInterfaceClass);
	    		remoteInterfaceClass = splitRemoteInterfaceClass;
	    	}
	    	
	    	DefaultTargetTreeNode childElement = new DefaultTargetTreeNode(remoteInterfaceClass);
	    	//add children for the remote interface....
	    	boolean ejbclassFound = false;
            Enumeration jarEntries = jarFile.entries();
            JarEntry jarEntry = null;
           
            while (jarEntries.hasMoreElements())
            {
              jarEntry = (JarEntry)jarEntries.nextElement();
              if(jarEntry.getName().contains(remoteInterfaceClass))
              {
            	  ejbclassFound = true;
            	  break;
              }

           }
	    	if(ejbclassFound)
	    	{
	    		///////////
	    		String argumentTypes = "";
	    		InputStream input = jarFile.getInputStream(jarEntry);
	    		ClassParser cp = new ClassParser(input,file.getName());
	    		JavaClass javaClass = cp.parse();
	        	for(Method method : javaClass.getMethods())
	        	{
	    		    //get input type and outtypes here
	        		Type [] args = method.getArgumentTypes();
	        		Type returnType = method.getReturnType();
	        		
	        		if(args!=null && args.length>0){
	        			for (int i=0;i<args.length;i++)
	        			{
	        				argumentTypes = args[i].toString()+":";
	        			}
	        		}
	        		DefaultTargetTreeNode element = new DefaultTargetTreeNode(method.getName());
	        		element.setInputType(argumentTypes);
	        		element.setOutputType(returnType.toString());
	    	    	childElement.add(element);
	    	    	System.out.println("Input Type and Return Type>>>>>"+argumentTypes+"  "+returnType.toString());
	    	    	    	  
	    		}
	    		
	    		
	    		///////////
	    	}
	    	top.add(childElement);
	    	
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
