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
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;
import gov.nih.nci.restgen.util.GeneralUtilities;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * This class defines a default implementation of Save action.
 * The primary focus of this class is to provide consistent look and feel definition
 * to SaveObjectToDbMapAction across the system.
 * Please see SaveObjectToDbMapAction defined in ui.map.actions for some reference.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class DefaultSaveAction extends OptionsAction
{
	public static final String COMMAND_NAME = ActionConstants.SAVE;
	public static final Character COMMAND_MNEMONIC = new Character('S');
	//hotkey//public static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK, false);
	//public static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("fileSave.gif"));
    public static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("ico_save.bmp"));
		
    public static final String TOOL_TIP_DESCRIPTION = "Save";

	private static final String LOGID = "$RCSfile: DefaultSaveAction.java,v $";
	public static String RCSID = "$Header: /share/content/gforge/caCore SDK/cmts/src/gov/nih/nci/cbiit/cmts/ui/actions/DefaultSaveAction.java,v 1.2 2009-11-23 18:32:47 wangeug Exp $";

	/**
	 * Defines an <code>Action</code> object with a default
	 * description string and default icon.
	 */
	public DefaultSaveAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME, mainFrame);
	}

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a default icon.
	 */
	public DefaultSaveAction(String name, MainFrameContainer mainFrame)
	{
		this(name, IMAGE_ICON, mainFrame);
	}

	/**
	 * Defines an <code>Action</code> object with the specified
	 * description string and a the specified icon.
	 */
	public DefaultSaveAction(String name, Icon icon, MainFrameContainer mainFrame)
	{
		super(name, icon, mainFrame);
	}

	protected void setAdditionalAttributes()
	{//override super class's one to plug in its own attributes.
		setMnemonic(COMMAND_MNEMONIC);
		//hotkey//setAcceleratorKey(ACCELERATOR_KEY_STROKE);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
		setShortDescription(TOOL_TIP_DESCRIPTION);
	}
	
	
	/**
	 * Invoked when an action occurs.
	 */
	protected boolean doAction(ActionEvent e) throws Exception
	{
			
		MappingMainPanel mappingMain=mainFrame.getMainFrame().getMappingMainPanel();
		/*if(mappingMain.getSourceTree()==null || mappingMain.getTargetTree()==null)
		{
					String msg = "Enter both source and target schema file before saving the map specification.";
					JOptionPane.showMessageDialog(mappingMain, msg, "No mapping data for Saving", JOptionPane.ERROR_MESSAGE);
					setSuccessfullyPerformed(false);
					return false;
		}*/
			
        String extension = ".xml";
        
        File file = DefaultSettings.getUserInputOfFileFromGUI(mappingMain, extension, "Save As...", true, true);

        if (file != null)
			setSuccessfullyPerformed(processSaveFile(file,mappingMain));
		
		return isSuccessfullyPerformed();
	}

	@SuppressWarnings("unchecked")
	protected boolean processSaveFile(File file, MappingMainPanel mappingMain) throws Exception
	{
	
		try
		{
			mappingMain.persistFile(file);
			// save this file object globally as an object in GenerateRESTfulResourceAction 
    		mainFrame.getTabbedPane().setTitleAt(0,file.getName());
    		GenerateRESTfulResourceAction.setMappingFile(file);
    		if(!mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("RESTful Resource").isEnabled())
   			{
            	 mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("RESTful Resource").setEnabled(true);
   			}
			return true;
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
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
 * HISTORY      :
 */
