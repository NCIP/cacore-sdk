/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.jgraph.action;


import gov.nih.nci.restgen.ui.actions.AbstractContextAction;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultMappableTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultPort;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Currently play as place holder to define the general look and feel of a "Delete Node..." action.
 * Descendant class will provide concrete implementation of the action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class EditResourcePathAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.EDITNODEPATH;
	protected static final Character COMMAND_MNEMONIC = new Character('E');
	//public static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("fileSaveAs.gif"));
    public static final ImageIcon IMAGE_ICON = null;
	protected transient MainFrameContainer mainFrame = null;
	private DefaultMutableTreeNode treeNode = null;
	private JTree tree = null;
	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public EditResourcePathAction(MainFrameContainer mainFrame, JTree tree, DefaultMutableTreeNode treeNode)
	{
		this(COMMAND_NAME,null);
		this.mainFrame = mainFrame;
		this.treeNode = treeNode;
		this.tree = tree;
		
	}



	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public EditResourcePathAction(String name, Icon icon)
	{
		super(name, icon);
		
		setAdditionalAttributes();
	}

	/**
	 * provide descendant class to override.
	 */
	protected void setAdditionalAttributes()
	{
		setMnemonic(COMMAND_MNEMONIC);

	}

		
	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e) throws Exception
	{
		
		System.out.println("Edit path clicked.......");
       if (treeNode==null)
       {
    	   System.out.println("Tree node null returning.......");
			return false;
       }
       	
       
 

       char[] specialChars = {'!','@',']','#','$','%','^','&','*'}; 
       
       String inputString = JOptionPane.showInputDialog(null, "Please enter the path for Resource : ", 
				"Resource Path", 1);
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
		         
		    	   JOptionPane.showMessageDialog(mainFrame.getMainFrame().getMappingMainPanel(), "Please enter a valid path...", "Invalid Path Entry!!!", JOptionPane.ERROR_MESSAGE);
		       }
		       try
		       {
		       
		    	   if(mainFrame.getMainFrame().getMappingMainPanel().getResourcePathValues()==null)
		    	   {
		    		   Hashtable<String, String> values = new Hashtable();
		    		   values.put(((DefaultSourceTreeNode)treeNode).getUserObject().toString(), inputString);
		    		   mainFrame.getMainFrame().getMappingMainPanel().setResourcePathValues(values);
		    		   System.out.println("inside if");
		    	   }
		    	   else
		    	   {
		    		   Hashtable<String, String> values = mainFrame.getMainFrame().getMappingMainPanel().getResourcePathValues();
		    		   values.put(((DefaultSourceTreeNode)treeNode).getUserObject().toString(), inputString);
		    		   mainFrame.getMainFrame().getMappingMainPanel().setResourcePathValues(values);
		    		   System.out.println("inside else");
		    	   }
		    	   System.out.println("path66666"+((DefaultSourceTreeNode)treeNode).getUserObject().toString());
		       
		       }
		       catch(Exception ex)
		       {
		    	   System.out.println(ex.toString());
		       }
		}
       
       return true;
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
