/**
 * The content of this file is subject to the caCore Software License (the "License").  
 * A copy of the License is available at:
 * [caCore CVS home directory]\etc\license\caCore_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore/indexContent
 * /docs/caCore_License
 */

package gov.nih.nci.restgen.ui.actions;

import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeMouseAdapter;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;
import groovy.io.FileType;

//

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.generic.Type;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class defines the new Map panel action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class NewPOJOFileAction extends AbstractContextAction
		{
	private static final String COMMAND_NAME = ActionConstants.NEW_POJO_FILE;
	private static final Character COMMAND_MNEMONIC = new Character('N');
	private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Open source POJO";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".class";
	private static final HashSet<String> WRAPPER_TYPES = getWrapperTypes();
	private MainFrameContainer mainFrame;
	private JTree tree;
	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public NewPOJOFileAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME, mainFrame);
		
	}

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a default icon.
	 */
	public NewPOJOFileAction(String name, MainFrameContainer mainFrame)
	{
		this(name, null, mainFrame);
	}

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public NewPOJOFileAction(String name, Icon icon, MainFrameContainer mainFrame)
	{
		super(name, icon);
		this.mainFrame = mainFrame;
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DESKTOP_ACTION_TYPE);

	}

	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e) throws Exception
	{
	
		// open POJO here PV
			File file = null;
            file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(),
                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
			
            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file is not a POJO class file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO class file", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // validate and parse the POJO class here
          mainFrame.getMainFrame().getMappingMainPanel().setMappingSourceFile(file);
          mainFrame.getMainFrame().getMappingMainPanel().setSourceFileType("POJOCLASS");
          if(mainFrame.getMainFrame().getMappingMainPanel().getTargetTree()!=null)
  		  {
          	mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraphController().handleDeleteAll();
          	
  		  }	
          createSourceTree(file);        
		return true;
	}

	public void createSourceTree(File file) throws Exception
	{
		
        InputStream is = new FileInputStream(file);
        ClassParser cp = new ClassParser(is,file.getName());
        JavaClass javaClass = cp.parse();
        ArrayList<String> classList = new ArrayList<String>();
        for(Field field : javaClass.getFields()){
        	System.out.println("Java field types...."+field.getType());
     	   boolean validatePOJOMethods = false;
     	    if(!isWrapperType(field.getType().toString()))
     	   {
     		   JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file Contains non-primitive java types (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") field class... : " + field.getType().toString(), "Not a POJO class file", JOptionPane.ERROR_MESSAGE);
     		   //return false;
     		   break;
     	   }
     	   if(field.getName().contains("serialVersionUID"))
     	   continue;
     		   
     	   for(Method method : javaClass.getMethods()){
     	   	   System.out.println("Field names:"+field.getName()+method.getName()+"\n");
     		   String fieldCompare = "get"+field.getName();
     		   if(fieldCompare.equalsIgnoreCase(method.getName()))
     		   {
     			   System.out.println("Inside if loop..."+"\n");
     			   validatePOJOMethods = true;
     			   break;
     		   }
     		   
     	   }
     	      
     	   if(!validatePOJOMethods)
     	   {
     		   JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file is not a POJO class (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO class file", JOptionPane.ERROR_MESSAGE);
                return;
     	   }
        }
        
        // validate and parse the POJO class here
        
         /// form the tree here PV...start
        		classList.add(file.getName());
        		mainFrame.getMainFrame().getMappingMainPanel().setPOJOClassList(classList);
             DefaultSourceTreeNode top = new DefaultSourceTreeNode(file.getName());
             top.setResourceLocation(file.getPath());
             top.setResourcePathLocation("");
             createNodes(top,file.getName());
             tree = new JTree(top);
             TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(mainFrame.getMainFrame().getMappingMainPanel().getGraphController());
             tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
             tree.addMouseListener(new TreeMouseAdapter(mainFrame,tree));
     		tree.setTransferHandler(new TreeTransferHandler(mainFrame.getMainFrame().getMappingMainPanel()));
     		tree.setDropMode(DropMode.ON);
     		tree.setDragEnabled(true);
     		
     		//GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
     		//mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraph().setTransferHandler(gDropHandler);
 			tree.setDragEnabled(true);
 			int size = tree.getRowCount();
 			for (int i = 0; i < size+10; i++)
 			{
 				if (i<tree.getRowCount())
 					tree.expandRow(i);
 			}
             mainFrame.getMainFrame().getMappingMainPanel().getSourceScrollPane().setViewportView(tree);
             mainFrame.getMainFrame().getMappingMainPanel().setSourceTree(tree);
            if(!mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").isEnabled())
   			{
            	 mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").setEnabled(true);
   			}
             
         /// end
         
		
	}
	
	
	
	private void createNodes(DefaultSourceTreeNode top, String resourceName) {
		
	    DefaultSourceTreeNode CreateClass = null;
	    DefaultSourceTreeNode Updateclass = null;
	    DefaultSourceTreeNode ReadClass = null;
	    DefaultSourceTreeNode DeleteClass = null;
	    
	    CreateClass = new DefaultSourceTreeNode("Create");
	    CreateClass.setResourceLocation(top.getResourceLocation());
	    CreateClass.setResourceName(resourceName);
	    CreateClass.setResourcePathLocation(top.getResourcePathLocation());
	    top.add(CreateClass);
	    
	    Updateclass = new DefaultSourceTreeNode("Update");
	    Updateclass.setResourceLocation(top.getResourceLocation());
	    Updateclass.setResourceName(resourceName);
	    Updateclass.setResourcePathLocation(top.getResourcePathLocation());
	    top.add(Updateclass);
	    
	    ReadClass = new DefaultSourceTreeNode("Read");
	    ReadClass.setResourceLocation(top.getResourceLocation());
	    ReadClass.setResourceName(resourceName);
	    ReadClass.setResourcePathLocation(top.getResourcePathLocation());
	    top.add(ReadClass);
	    
	    DeleteClass = new DefaultSourceTreeNode("Delete");
	    DeleteClass.setResourceLocation(top.getResourceLocation());
	    DeleteClass.setResourceName(resourceName);
	    DeleteClass.setResourcePathLocation(top.getResourcePathLocation());
	    top.add(DeleteClass);
        top.setResourceName(resourceName);
	    
	    	    
	    
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

	
	public static boolean isWrapperType(String clazz)
    {
		if(clazz.contains("java.")||clazz.contains("javax."))
		{
			return true;
		}
        return WRAPPER_TYPES.contains(clazz);
    }

    private static HashSet<String> getWrapperTypes()
    {
        HashSet<String> ret = new HashSet<String>();
        ret.add("boolean");
        ret.add("character");
        ret.add("byte");
        ret.add("short");
        ret.add("integer");
        ret.add("long");
        ret.add("float");
        ret.add("double");
        ret.add("void");
        return ret;
    }

}

/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.1  2013-01-11 19:04:17  linc
 * HISTORY      : First GUI release
 * HISTORY      :
 * HISTORY      : Revision 1.1  2013-01-11 20:46:14  linc
 * HISTORY      : UI update.
 * HISTORY      :
 */
