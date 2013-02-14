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
import gov.nih.nci.restgen.ui.main.MainFrameContainer;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Currently play as place holder to define the general look and feel of a "Options..." action.
 * Descendant class will provide concrete implementation of the action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class OptionsAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.OPTIONS;
	protected static final Character COMMAND_MNEMONIC = new Character('a');
	//public static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("fileSaveAs.gif"));
    public static final ImageIcon IMAGE_ICON = null;
	protected transient MainFrameContainer mainFrame = null;
	protected transient File defaultFile = null;

	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public OptionsAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME,null, mainFrame);
	}



	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public OptionsAction(String name, Icon icon, MainFrameContainer mainFrame)
	{
		super(name, icon);
		this.mainFrame = mainFrame;
		setAdditionalAttributes();
	}

	/**
	 * provide descendant class to override.
	 */
	protected void setAdditionalAttributes()
	{
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
	}

	public File getDefaultFile()
	{
		return defaultFile;
	}

	public void setDefaultFile(String fullFileName)
	{
		setDefaultFile(new File(fullFileName));
	}

	public void setDefaultFile(File file)
	{
		this.defaultFile = file;
	}


	
	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	@SuppressWarnings("static-access")
	protected boolean doAction(ActionEvent e) throws Exception
	{
		/*File file = null;
        file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(),
                "", "Output folder", false, false);*/
		char[] specialChars = {'!','@',']','#','$','%','^','&','*'}; 
	    String prevVal = mainFrame.getMainFrame().getMappingMainPanel().getOptionsPath();  
		String inputString = null;
		if(prevVal!=null)
		{
			inputString = JOptionPane.showInputDialog(null, "Please enter the path for output : ",
					prevVal.trim());
		}
		else
		{
			inputString = JOptionPane.showInputDialog(null, "Please enter the path for output : ", 
					"");
		}

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
			       else
			       {
			    	   mainFrame.getMainFrame().getMappingMainPanel().setOptionsPath(inputString);
			        	System.out.println("Selected directory path ....<<<....>>"+inputString);
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
