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

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
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
                  
            JarFile jar = new JarFile(file);
            Enumeration<?> en = jar.entries();
            ArrayList<String> classList = new ArrayList<String>();
            boolean containsClassFile = false;
            boolean isValidPOJOClass = false;
              		while (en.hasMoreElements()) {
              			JarEntry entry = (JarEntry) en.nextElement();
              			isValidPOJOClass = false;
              			if (entry.getName().endsWith(".class")) {
              				InputStream input = jar.getInputStream(entry);
              				isValidPOJOClass = validatePOJOClass(input,entry.getName());
              				if(!isValidPOJOClass)
              				{
              					JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This class in the Jar file : " + entry.getName()+ ", Not a POJO class file",", Not a POJO class file", JOptionPane.ERROR_MESSAGE);
              					continue;
              				}
              				classList.add(entry.getName().replace(".class", ""));
              				containsClassFile = true;

              			}

              		}
             if(!containsClassFile || classList.isEmpty())
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

	
	  // validate and parse the POJO class here
    public boolean validatePOJOClass(InputStream is,String classFile)throws Exception
    {
    boolean validatePOJOMethods = false;	
    
     ClassParser cp = new ClassParser(is,classFile);
     JavaClass javaClass = cp.parse();
     for(Field field : javaClass.getFields()){
  	   validatePOJOMethods = false;
  	   
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
  	  
     }
     
     return validatePOJOMethods;
     
     // validate and parse the POJO class here
	
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
