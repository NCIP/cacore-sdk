/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.actions;


import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.message.MessagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.MalformedURLException;



/**
 * This class defines the exit action.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class GenerateRESTfulResourceAction extends AbstractContextAction
{
    private static final String COMMAND_NAME = ActionConstants.GENERATERESTFUL;
    private static final Character COMMAND_MNEMONIC = new Character('E');
    //hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK, false);

    private MainFrameContainer mainFrame;

    /**
     * Defines an <code>Action</code> object with a default
     * description string and default icon.
     */
    public GenerateRESTfulResourceAction(MainFrameContainer mainFrame)
    {
        this(COMMAND_NAME, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a default icon.
     */
    public GenerateRESTfulResourceAction(String name, MainFrameContainer mainFrame)
    {
        this(name, (Icon) null, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a the specified icon.
     */
    public GenerateRESTfulResourceAction(String name, Icon icon, MainFrameContainer mainFrame)
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
    protected boolean doAction(ActionEvent e)
    {
        OpenMappingAction closeAllAction = new OpenMappingAction(mainFrame);

        
        /*if (!closeAllAction.doAction(e))
        {
            if (mainFrame.getAllTabs().size() > 0) return isSuccessfullyPerformed();
        }*/

        /*
        int indexUnchanged = -1;
        String unsavedTabTitle = null;
        JTabbedPane tab = null;
        try
		{
            if (mainFrame != null)
			{
                java.util.List<Component> tabComps = mainFrame.getAllTabs();
                tab = mainFrame.getTabbedPane();
                //System.out.println("CCCC tab.getTabCount()=" + tab.getTabCount());
                for(int i=0;i<tab.getTabCount();i++)
                //for(Component comp:tabComps)
                {
                    indexUnchanged = -1;
                    //JTabbedPane tab = mainFrame.getTabbedPane();
                    //tab.getSelectedIndex()
                    //int i = tab.get
                    Component comp = tab.getComponentAt(i);
                    //boolean excutableClose = true;
                    //System.out.println("CCCC vv component compName:"+comp.getName()+", tabTitle:" + tab.getTitleAt(i) + ", className:" + comp.getClass().getCanonicalName()  );
                    if (comp instanceof MappingMainPanel)
                    {
                        MappingMainPanel mappingPanel = (MappingMainPanel) comp;
                        //System.out.println("CCCC vv MappingMainPanel");
                        if (mappingPanel.isChanged())
                        {
                            indexUnchanged = i;
                            unsavedTabTitle = tab.getTitleAt(i);
                            break;
                        }

                    }
                    else if (comp instanceof MessagePanel)
                    {
                        MessagePanel panel = (MessagePanel) comp;

                        String dispMesg = panel.getDisplayedMessage();

                        if ((dispMesg != null)&&(!dispMesg.trim().equals("")))
                        {
                            if (!panel.hasBeenSaved())
                            {
                                indexUnchanged = i;
                                unsavedTabTitle = tab.getTitleAt(i);
                                break;
                            }
                        }
                    }
                }
                //ownerFrame.closeTab();
				//ownerFrame.resetCenterPanel();  // inserted by umkis on 01/18/2006, defaect# 252
            }
			else
			{
				System.err.println("Main Frame is null. Ignore!");
			}

		}
		catch (Exception e1)
		{
            e1.printStackTrace();
		}

        if (indexUnchanged >= 0)
        {
            tab.setSelectedIndex(indexUnchanged);
            JOptionPane.showMessageDialog(mainFrame.getAssociatedUIComponent(), "The content of '" + unsavedTabTitle + "' tab is not saved yet. Close or save this first.", "Unsaved Content", JOptionPane.WARNING_MESSAGE);
            return isSuccessfullyPerformed();
        }
        */
        
        if (mainFrame.getMainFrame() != null)
        {
            WindowEvent we = new WindowEvent(mainFrame.getMainFrame(), WindowEvent.WINDOW_CLOSING);
            mainFrame.getMainFrame().processWindowEvent(we);
            System.out.println("Exit caCore SDK_cmts from Running on the MainFrame");
            setSuccessfullyPerformed(true);
            return true;
        }
      
        JOptionPane.showMessageDialog(getAssociatedUIComponent(), "This web browser cannot be closed by CMTS. \nUse the 'Exit' menu of this Web Browser.", "Exit Failure from Web Browser", JOptionPane.WARNING_MESSAGE);

        System.out.println("*****Exit Failure!!!");
        setSuccessfullyPerformed(false);
        return false;

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
