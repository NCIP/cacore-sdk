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
//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.message.MessagePanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * This class defines the closeAll action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class OpenMappingAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.OPEN_MAPPING;
	protected static final Character COMMAND_MNEMONIC = new Character('A');
    public static final ImageIcon IMAGE_ICON = null;
//	protected static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.CTRL_MASK, false);

	private static final String LOGID = "$RCSfile: CloseAllAction.java,v $";
	public static String RCSID = "$Header: /share/content/gforge/caCore SDK/cmts/src/gov/nih/nci/cbiit/cmts/ui/actions/CloseAllAction.java,v 1.2 2009-11-23 18:32:47 wangeug Exp $";

	protected MainFrameContainer mainFrame = null;

	public OpenMappingAction(MainFrameContainer mainFrame)
	{
		this(COMMAND_NAME, mainFrame);
	}

	public OpenMappingAction(String name, MainFrameContainer mainFrame)
	{
		this(name, IMAGE_ICON , mainFrame);
	}

	public OpenMappingAction(String name, Icon icon, MainFrameContainer mainFrame)
	{
		super(name, icon);
		this.mainFrame = mainFrame;
		setAdditionalAttributes();
	}

	protected void setAdditionalAttributes()
	{//override super class's one to plug in its own attributes.
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
	}


	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e)
	{
//		Log.logInfo(this, "CloseAllAction is called.");
		java.util.List<Component> componentList = mainFrame.getAllTabs();
		int count = componentList.size();//tabbedPane.getComponentCount();
		ArrayList<AbstractContextAction> actionList = new ArrayList<AbstractContextAction>();
        //boolean closedAllSuccessfully = true;

        String ll = "";
        int cnt = 0;
        for (int i = 0; i < count; i++)
		{//retrieve the list of close actions,
			//shall call individual after the loop, since the close action will remove the referred tab
			//which will cause the component count decreased.
			Component comp = componentList.get(i);
		}
        String message = null;
        String titleT = "Unsaved documents found";
        if (cnt == 1)
        {
            String m2 = "Are you sure to close all tabs?";
            titleT = "Unsaved document found";
            if (count == 1) m2 = "Are you sure to close this tab?";

            message = "One document '" + ll.trim() + "' is not saved yet. " + m2;
        }
        else if (cnt > 1)
        {
            message = "Following "+cnt+" documents are not saved yet. Are you sure to close all tabs?\n" + ll;
        }

        if (message != null)
        {
            int n = JOptionPane.showConfirmDialog(mainFrame.getAssociatedUIComponent(), message, titleT, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (n != JOptionPane.YES_OPTION) return isSuccessfullyPerformed();
        }

        try
		{
			
			int size = actionList.size();
			for (int i = 0; i < size; i++)
			{
				AbstractContextAction action = actionList.get(i);
                if (action instanceof OpenPOJOJarAction)
                {
                    OpenPOJOJarAction closeA = (OpenPOJOJarAction) action;
                    //closeA.setForceClose(true);
                }
                action.actionPerformed(e);
				if (!action.isSuccessfullyPerformed())
				{//stop at the first failed execution of close action.
					this.setSuccessfullyPerformed(false);
					break;
				}
				else
				{
					this.setSuccessfullyPerformed(true);
				}
			}
			File file = null;
	        while(true)
	        {
	            file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getAssociatedUIComponent(), //getUIWorkingDirectoryPath(),
				    	DefaultSettings.MAP_FILE_DEFAULT_EXTENTION, ActionConstants.OPEN_MAP_FILE, false, false);
	            if (file == null)
	            {
	                JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "Opening File is Canceled.",
	                        "Cancel Open File", JOptionPane.INFORMATION_MESSAGE);
	                return isSuccessfullyPerformed();
	            }

	            if (file.getName().toLowerCase().endsWith(".xml")) break;
	            else
	            {
	                int ans = JOptionPane.showConfirmDialog(mainFrame.getAssociatedUIComponent(), "The file type of your selection is not '.map'. : " + file.getName() + "\nDo you want to continue, anyway?",
	                                              "Not Mapping File", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	                if (ans == JOptionPane.YES_OPTION) break;
	                else
	                {
	                    JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "Opening File is Canceled.",
	                        "Cancel Open File", JOptionPane.INFORMATION_MESSAGE);
	                    return isSuccessfullyPerformed();
	                }
	            }
	        }
			
			// call the main panel open mapping method here PV>>>>>>>>>
			mainFrame.getMainFrame().getMappingMainPanel().processOpenMapFile(file);
			mainFrame.getTabbedPane().setTitleAt(0,file.getName());
			if(!mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("RESTful Resource").isEnabled())
   			{
            	 mainFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("RESTful Resource").setEnabled(true);
   			}
			GenerateRESTfulResourceAction.setMappingFile(file);
			
		}
		catch (Throwable t)
		{
			setSuccessfullyPerformed(false);
			//Log.logException(this, t);
//			System.err.println("Exception: " + t);
			t.printStackTrace();
		}
		finally
		{//roll back the mode.
			if (isSuccessfullyPerformed())
            {
                if (mainFrame.getAllTabs().size() == 0)
                {
                	// ContextManager.getContextManager().setInClosingAllOrShutdownMode(false, isSuccessfullyPerformed());
                }
                   // 
            }
            int size = actionList.size();
            for (int i = 0; i < size; i++)
            {
                AbstractContextAction action = actionList.get(i);
                if (action instanceof OpenPOJOJarAction)
                {
                    OpenPOJOJarAction closeA = (OpenPOJOJarAction) action;
                   // closeA.setForceClose(false);
                }
            }
        }
		return isSuccessfullyPerformed();
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
