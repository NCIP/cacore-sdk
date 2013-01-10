/**
 * The content of this file is subject to the caAdapter Software License (the "License").  
 * A copy of the License is available at:
 * [caAdapter CVS home directory]\etc\license\caAdapter_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent
 * /docs/caAdapter_License
 */

package gov.nih.nci.cbiit.cmts.ui.actions;


import gov.nih.nci.cbiit.cmts.core.ComponentType;
import gov.nih.nci.cbiit.cmts.ui.common.ActionConstants;
import gov.nih.nci.cbiit.cmts.ui.common.DefaultSettings;
import gov.nih.nci.cbiit.cmts.ui.dnd.TreeTransferHandler;
import gov.nih.nci.cbiit.cmts.ui.main.MainFrameContainer;
import gov.nih.nci.cbiit.cmts.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.cbiit.cmts.ui.tree.TreeSelectionHandler;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;

//PV commented import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This class defines the new Map panel action.
 *
 * @author Chunqing Lin
 * @author LAST UPDATE $Author: wangeug $
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2009-11-23 18:32:47 $
 */
public class NewPOJOFileAction extends AbstractContextAction
		{
	private static final String COMMAND_NAME = ActionConstants.NEW_POJO_FILE;
	private static final Character COMMAND_MNEMONIC = new Character('M');
	private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Open source POJO";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".class";
	
	//hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_M, Event.CTRL_MASK, false);

	private MainFrameContainer mainFrame;
	private JTree tree;
	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public NewPOJOFileAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME, mainFrame);
		//mainContextManager = cm;
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
		//hotkey//setAcceleratorKey(ACCELERATOR_KEY_STROKE);
		setActionCommandType(DESKTOP_ACTION_TYPE);
		//do not know how to set the icon location name, or just do not matter.
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
        
        
        
            file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file is not a XML schema (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a schema file", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // validate and parse the POJO class here
           
           /*ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
           ClassLoader newCL = new URLClassLoader(new URL[]{file.toURI().toURL()}, oldCL);
           Thread.currentThread().setContextClassLoader(newCL);
           System.out.println("Successfully replaced ClassLoader");
           //Class fooClass = newCL.loadClass("gov.nih.nci.cbiit.cmts.ui.main"+file.getName());
           String className = file.getName();*/
           //Class fooClass = newCL.loadClass(className);
           InputStream is = new FileInputStream(file);
           ClassParser cp = new ClassParser(is,file.getName());
           JavaClass javaClass = cp.parse(); 
           for(Method method : javaClass.getMethods()){
        
        	   	System.out.println("Method names....."+method.getName());
        		   
           }
           // validate and parse the POJO class here
           
            /// form the tree here PV...start
            
                DefaultSourceTreeNode top = new DefaultSourceTreeNode(file.getName());
                createNodes(top);
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
    			for (int i = 0; i < size+10; i++)
    			{
    				if (i<tree.getRowCount())
    					tree.expandRow(i);
    			}
                mainFrame.getMainFrame().getMappingMainPanel().getSourceScrollPane().setViewportView(tree);
                mainFrame.getMainFrame().getMappingMainPanel().setSourceTree(tree);
                
            /// end
            
        
		return true;
	}

	private void createNodes(DefaultMutableTreeNode top) {
		
	    DefaultSourceTreeNode Createclass1 = null;
	    DefaultSourceTreeNode Updateclass1 = null;
	    DefaultSourceTreeNode Readclass1 = null;
	    DefaultSourceTreeNode Deleteclass1 = null;
	    
	    Createclass1 = new DefaultSourceTreeNode("Create");
	    top.add(Createclass1);
	    
	    Updateclass1 = new DefaultSourceTreeNode("Update");
	    top.add(Updateclass1);
	    
	    Readclass1 = new DefaultSourceTreeNode("Read");
	    top.add(Readclass1);
	    
	    Deleteclass1 = new DefaultSourceTreeNode("Delete");
	    top.add(Deleteclass1);
        
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
