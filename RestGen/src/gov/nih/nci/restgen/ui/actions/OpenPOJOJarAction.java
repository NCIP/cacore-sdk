/**
 * The content of this file is subject to the caAdapter Software License (the "License").  
 * A copy of the License is available at:
 * [caAdapter CVS home directory]\etc\license\caAdapter_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent
 * /docs/caAdapter_License
 */

package gov.nih.nci.restgen.ui.actions;

import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeMouseAdapter;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;

/**
 * This class defines the default close action.
 * Descendant classes may implement additional functions.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class OpenPOJOJarAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.OPENPOJO;
	protected static final Character COMMAND_MNEMONIC = new Character('O');
	//hotkey//protected static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.CTRL_MASK, false);
    public static final String TOOL_TIP_DESCRIPTION = "Open POJO Jar";
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Open source POJO Jar";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".jar";

    private boolean forceClose = false;

    protected MainFrameContainer ownerFrame = null;
    
    private JTree tree;

	public OpenPOJOJarAction(MainFrameContainer owner)
	{
		this(COMMAND_NAME, owner);
	}

	public OpenPOJOJarAction(String name, MainFrameContainer owner)
	{
		this(name, null, owner);
	}

	public OpenPOJOJarAction(String name, Icon icon, MainFrameContainer owner)
	{
		super(name, icon);
		ownerFrame = owner;
		setAdditionalAttributes();
	}

	protected void setAdditionalAttributes()
	{//override super class's one to plug in its own attributes.
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
        setShortDescription(TOOL_TIP_DESCRIPTION);
    }

	protected void setFrame(Component newFrame)
	{
		if (newFrame instanceof MainFrame)
		{
			ownerFrame = new MainFrameContainer((MainFrame)newFrame);
		}
        
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
        
        
        
            file = DefaultSettings.getUserInputOfFileFromGUI(ownerFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This file is not a POJO Jar file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO Jar file", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // validate and parse the POJO jar classes here
     
         /* InputStream is = new FileInputStream(file);
           ClassParser cp = new ClassParser(is,file.getName());
           JavaClass javaClass = cp.parse();
           for(Method method : javaClass.getMethods()){
        	   boolean validatePOJOMethods = false;
        	   for(Field field : javaClass.getFields()){
        		 
        		   String fieldCompare = "get"+field.getName();
        		   if(fieldCompare.equalsIgnoreCase(method.getName()))
        		   {
        			   validatePOJOMethods = true;
        			   break;
        		   }
        		   
        	   }
        	      
        	   if(!validatePOJOMethods)
        	   {
        		   JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file is not a POJO class (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO class file", JOptionPane.ERROR_MESSAGE);
                   return false;
        	   }
           }
           */
           // validate and parse the POJO jar classes here
           
            JarFile jar = new JarFile(file);
            Enumeration<?> en = jar.entries();
            ArrayList<String> classList = new ArrayList<String>();
            boolean containsClassFile = false;  
              		while (en.hasMoreElements()) {
              			JarEntry entry = (JarEntry) en.nextElement();
              			if (entry.getName().endsWith(".class")) {
              				classList.add(entry.getName().replace(".class", ""));
              				containsClassFile = true;

              			}

              		}
             if(!containsClassFile)
             {
            	 JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This file is not a POJO Jar File: " + file.getName(), "Does not contain POJO classes", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
            
            /// form the tree here PV...start
            
                DefaultSourceTreeNode top = new DefaultSourceTreeNode(file.getName());
                createNodes(top,classList);
                tree = new JTree(top);
                TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(ownerFrame.getMainFrame().getMappingMainPanel().getGraphController());
                tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
                tree.addMouseListener(new TreeMouseAdapter(ownerFrame,tree));
        		tree.setTransferHandler(new TreeTransferHandler(ownerFrame.getMainFrame().getMappingMainPanel()));
        		tree.setDropMode(DropMode.ON);
        		tree.setDragEnabled(true);
    			tree.setDragEnabled(true);
    			int size = tree.getRowCount();
    			for (int i = 0; i < size+10; i++)
    			{
    				if (i<tree.getRowCount())
    					tree.expandRow(i);
    			}
    			ownerFrame.getMainFrame().getMappingMainPanel().getSourceScrollPane().setViewportView(tree);
    			ownerFrame.getMainFrame().getMappingMainPanel().setSourceTree(tree);
                
            /// end
            
        
		return true;
	}

	private void createNodes(DefaultSourceTreeNode top,ArrayList<String> list) {
		
	    DefaultSourceTreeNode Createclass = null;
	    DefaultSourceTreeNode Updateclass = null;
	    DefaultSourceTreeNode Readclass = null;
	    DefaultSourceTreeNode Deleteclass = null;
	    Iterator<String> it = list.iterator();
	    while(it.hasNext())
	    {
	    	
	    	DefaultSourceTreeNode element = new DefaultSourceTreeNode((String)it.next());
	    	Createclass = new DefaultSourceTreeNode("Create");
	    	element.add(Createclass);
	    
	    	Updateclass = new DefaultSourceTreeNode("Update");
	    	element.add(Updateclass);
	    
	    	Readclass = new DefaultSourceTreeNode("Read");
	    	element.add(Readclass);
	    
	    	Deleteclass = new DefaultSourceTreeNode("Delete");
	    	element.add(Deleteclass);
	    	
	    	top.add(element);
	    }
	}

	/**
	 * Return the associated UI component.
	 *
	 * @return the associated UI component.
	 */
	protected Component getAssociatedUIComponent()
	{
		return ownerFrame.getAssociatedUIComponent();
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
