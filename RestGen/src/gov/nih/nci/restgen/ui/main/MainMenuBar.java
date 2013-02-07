/**
 * The content of this file is subject to the caCore SDK Software License (the "License").
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.main;



import gov.nih.nci.restgen.ui.actions.AboutViewAction;
import gov.nih.nci.restgen.ui.actions.AbstractContextAction;
import gov.nih.nci.restgen.ui.actions.CloseAction;
import gov.nih.nci.restgen.ui.actions.DefaultExitAction;
import gov.nih.nci.restgen.ui.actions.DefaultSaveAction;
import gov.nih.nci.restgen.ui.actions.GenerateRESTfulResourceAction;
import gov.nih.nci.restgen.ui.actions.HelpViewAction;
import gov.nih.nci.restgen.ui.actions.NewPOJOFileAction;
import gov.nih.nci.restgen.ui.actions.OpenMappingAction;
import gov.nih.nci.restgen.ui.actions.OpenPOJOJarAction;
import gov.nih.nci.restgen.ui.actions.OptionsAction;
import gov.nih.nci.restgen.ui.actions.UploadEJBJarAction;
import gov.nih.nci.restgen.ui.actions.UploadWSDLAction;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.common.MenuConstants;
import gov.nih.nci.restgen.util.FileUtil;

import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;


/**
 * This class manages the definitions and instantiations of menu items. It will
 * coordinate ContextManager class to deal with context sensitive menu
 * switches.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 */
@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar
{

    //ContextManager contextManager = null;
    MainFrameContainer mainFrame = null;
    private Map<String, AbstractContextAction> actionMap;
    private Map<String, JMenuItem> menuItemMap;
    private Map<String, JMenu> menuMap;

    private JMenuItem exitMenuItem = null;

    public MainMenuBar(MainFrameContainer mf)//ContextManager contextManager) {
    {//this.contextManager = contextManager;
        this.mainFrame = mf;//contextManager.getMainFrame();
        initialize();
    }

    private void initialize()
    {
        actionMap = Collections.synchronizedMap(new HashMap<String, AbstractContextAction>());
        menuItemMap = Collections.synchronizedMap(new HashMap<String, JMenuItem>());
        menuMap = Collections.synchronizedMap(new HashMap<String, JMenu>());

        add(constructFileMenu());
        
        // add the main menu Items here PV........start
        JMenu uploadMenu=new JMenu("Upload");
        add(uploadMenu);
        
        JMenu generateMenu=new JMenu("Generate");
        add(generateMenu);

        JMenuItem uploadWSDLItem=new JMenuItem(new UploadWSDLAction(mainFrame));
        uploadMenu.add(uploadWSDLItem);

        JMenuItem uploadEJBJarItem=new JMenuItem(new UploadEJBJarAction(mainFrame));
        uploadMenu.add(uploadEJBJarItem);
        
        JMenuItem generateRestfulItem=new JMenuItem(new GenerateRESTfulResourceAction(mainFrame));
        generateMenu.add(generateRestfulItem);

        // add the main menu Items here PV........end
        JMenu helpMenu=new JMenu("Help");
        
        JMenuItem aboutItem=new JMenuItem(new AboutViewAction(mainFrame));
        helpMenu.add(aboutItem);
        
        JMenuItem helpItem=new JMenuItem(new HelpViewAction(mainFrame));
        helpMenu.add(helpItem);
        add(helpMenu);
        
        
    }


    /* (non-Javadoc)
      * @see gov.nih.nci.caCore SDK.ui.main.AbstractMenuBar#enableAction(java.lang.String, boolean)
      */
    public void enableAction(String actionConstant, boolean value)
    {
        Action action = getDefinedAction(actionConstant);// (Action)actionMap.get(actionConstant);
        if (action != null)
        {
            action.setEnabled(value);
        } else
        {
            String msg = "Action could not be found for '" + actionConstant + "'.";
            System.err.println(msg);
            //Log.logWarning(this.getClass(), msg);
        }
    }

    /* (non-Javadoc)
      * @see gov.nih.nci.caCore SDK.ui.main.AbstractMenuBar#getDefinedAction(java.lang.String)
      */
    public Action getDefinedAction(String actionConstant)
    {
        return (Action) actionMap.get(actionConstant);
    }

    public JMenuItem getDefinedMenuItem(String actionConstant)
    {
        return (JMenuItem) menuItemMap.get(actionConstant);
    }

    public JMenu getDefinedMenu(String actionConstant)
    {
        return (JMenu) menuMap.get(actionConstant);
    }

    private JMenu constructFileMenu()
    {
        
        // link them together
        JMenu fileMenu = new JMenu(MenuConstants.FILE_MENU_NAME);
        fileMenu.setMnemonic('F');
        // open POJO menu item
        NewPOJOFileAction newPOJOFileAction = new NewPOJOFileAction(mainFrame);
        JMenuItem newCmpsMapItem = new JMenuItem(newPOJOFileAction);
        fileMenu.add(newCmpsMapItem);
        ImageIcon newImageIcon = new ImageIcon(DefaultSettings.getImage("ico_new.bmp"));
        newCmpsMapItem.setIcon(newImageIcon);
        fileMenu.addSeparator();
        
       // open POJO Jar menu item
        OpenPOJOJarAction newOpenPOJOJarAction = new OpenPOJOJarAction(mainFrame);
        JMenuItem openPOJOJar = new JMenuItem(newOpenPOJOJarAction);
        fileMenu.add(openPOJOJar);
        
        // open mapping menu item
        OpenMappingAction newOpenMappingAction = new OpenMappingAction(mainFrame);
        JMenuItem openMapping = new JMenuItem(newOpenMappingAction);
        fileMenu.add(openMapping);
        
        //	save menu item
        DefaultSaveAction defaultSaveAction = new DefaultSaveAction(mainFrame);
        JMenuItem saveMenuItem = new JMenuItem(defaultSaveAction);
        actionMap.put(ActionConstants.SAVE, defaultSaveAction);
        menuItemMap.put(ActionConstants.SAVE, saveMenuItem);
        fileMenu.add(saveMenuItem);
        saveMenuItem.getAction().setEnabled(false);
       	// options menu item
        OptionsAction optionsAction = new OptionsAction(mainFrame);
        JMenuItem optionsMenuItem = new JMenuItem(optionsAction);
        actionMap.put(ActionConstants.OPTIONS, optionsAction);
        menuItemMap.put(ActionConstants.OPTIONS, optionsMenuItem);
        fileMenu.add(optionsMenuItem);
        fileMenu.addSeparator();
        
        // close action item
        
        CloseAction closeAction = new CloseAction(mainFrame);
        JMenuItem closeMenuItem = new JMenuItem(closeAction);
        actionMap.put(ActionConstants.CLOSE, closeAction);
        menuItemMap.put(ActionConstants.CLOSE, closeMenuItem);
        //fileMenu.add(closeMenuItem);
        //fileMenu.addSeparator();
        closeMenuItem.setEnabled(false);
        
        DefaultExitAction exitAction = new DefaultExitAction(mainFrame);//.getAssociatedUIComponent());
        exitMenuItem = new JMenuItem(exitAction);
        actionMap.put(ActionConstants.EXIT, exitAction);
        menuItemMap.put(ActionConstants.EXIT, exitMenuItem);

        
        // exit menu item
        if (exitMenuItem != null)
        {
            fileMenu.addSeparator();
            fileMenu.add(exitMenuItem);
        }
        menuMap.put(MenuConstants.FILE_MENU_NAME, fileMenu);
       
        //fileMenu.addActionListener(
        fileMenu.addMenuListener(
           new MenuListener()
           {
               public void menuCanceled(MenuEvent e) {}
               public void menuDeselected(MenuEvent e) {}
               public void menuSelected(MenuEvent e)
               {
                   URL u = FileUtil.getCodeBase();
                       if (u != null) {} //System.out.println("C - Already exist - Applet documentBase=" + u.toString());
                       
                 }
           }
        );
        
        return fileMenu;
    }



    private JMenu constructOpenMenu()
    {
        // construct actions and menu items.
        /*OpenMapFileAction openMapAction = new OpenMapFileAction(mainFrame);
        JMenuItem openMapFileItem = new JMenuItem(openMapAction);
        */
    	// replace with open later PV
    	
    	DefaultSaveAction openMapAction = new DefaultSaveAction(mainFrame);
        JMenuItem openMapFileItem = new JMenuItem(openMapAction);
        //
        
        actionMap.put(ActionConstants.OPEN_MAP_FILE, openMapAction);
        menuItemMap.put(ActionConstants.OPEN_MAP_FILE, openMapFileItem);

        // link them together
        JMenu openMenu = new JMenu("      " + MenuConstants.OPEN_MENU_NAME);
        openMenu.setMnemonic('O');
        openMenu.add(openMapFileItem);

        return openMenu;
    }

    
    /* (non-Javadoc)
      * @see gov.nih.nci.caCore SDK.ui.main.AbstractMenuBar#resetMenus(boolean)
      */
    public void resetMenus(boolean hasActiveDocument)
    {// provide structure for
        // more menus to be
        // reset
        resetFileMenu(hasActiveDocument);
//		resetReportMenu(hasActiveDocument);
    }

    private void resetFileMenu(boolean hasActiveDocument)
    {
        resetNewSubMenu(hasActiveDocument);
        resetOpenSubMenu(hasActiveDocument);
        JMenuItem saveMenuItem = menuItemMap.get(ActionConstants.SAVE);
        JMenuItem saveAsMenuItem = menuItemMap.get(ActionConstants.OPTIONS);
        JMenuItem closeMenuItem = menuItemMap.get(ActionConstants.OPENPOJO);
		JMenuItem closeAllMenuItem = menuItemMap.get(ActionConstants.OPEN_MAPPING);
		
        if (hasActiveDocument)
        {
            saveMenuItem.getAction().setEnabled(true);
            saveAsMenuItem.getAction().setEnabled(true);
            closeMenuItem.getAction().setEnabled(true);
            closeAllMenuItem.getAction().setEnabled(true);
        }
        else
        {
            boolean cTag = false;
            try
            {
                cTag = ((mainFrame.getAllTabs() != null)&&(mainFrame.getAllTabs().size() > 0));
            }
            catch(ArrayIndexOutOfBoundsException ae)
            {
                cTag = false;
            }

            if (cTag)
            {
                saveMenuItem.getAction().setEnabled(true);
                saveAsMenuItem.getAction().setEnabled(true);
                closeMenuItem.getAction().setEnabled(true);
                closeAllMenuItem.getAction().setEnabled(true);
            }
            else
            {
                saveMenuItem.getAction().setEnabled(false);
                saveAsMenuItem.getAction().setEnabled(false);
                closeMenuItem.getAction().setEnabled(false);
                closeAllMenuItem.getAction().setEnabled(false);
            }
        }

        
    }

    private void resetNewSubMenu(boolean hasActiveDocument)
    {
        if (!hasActiveDocument)
        {
            //			menuItemMap.get(ActionConstants.NEW_MAP_FILE).getAction().setEnabled(true);
            resetMenuItem(ActionConstants.NEW_MAP_FILE, true);
            //			newMapFileItem.getAction().setEnabled(true);
            //			newCSVSpecificationItem.getAction().setEnabled(true);
            //			menuItemMap.get(ActionConstants.NEW_CSV_SPEC).getAction().setEnabled(true);
        }
    }

    private void resetMenuItem(String itemName, boolean newValue)
    {
        JMenuItem menuItem = menuItemMap.get(itemName);
        if (menuItem != null)
        {
            Action a = menuItem.getAction();
            if (a != null)
                a.setEnabled(newValue);
        }
    }

    private void resetOpenSubMenu(boolean hasActiveDocument)
    {
        if (!hasActiveDocument)
        {
            resetMenuItem(ActionConstants.OPEN_MAP_FILE, true);
        }
    }

//	private void resetReportMenu(boolean hasActiveDocument)
//	{
//		if (!hasActiveDocument)
//		{
//			resetMenuItem(ActionConstants.GENERATE_REPORT, false);
//		}
//	}

    public void enableCloseAllAction(boolean newValue)
    {
        Action closeAllAction = actionMap.get(ActionConstants.OPEN_MAPPING);
        if (closeAllAction != null)
        {
            closeAllAction.setEnabled(newValue);
            JMenuItem closeAllMenuItem = menuItemMap.get(ActionConstants.OPEN_MAPPING);
            closeAllMenuItem.setAction(null);
            closeAllMenuItem.setAction(closeAllAction);
            // closeAllMenuItem.invalidate();
        }
    }
    public JMenuItem getExitMenuItem()
    {
        return exitMenuItem;
    }
    private boolean isEnableExitMenu()
    {
        if (mainFrame.getMainFrame() != null)
        {
            //System.out.println("Run on the MainFrame");
            return true;
        }
       
        return true;
    }
}
/**
 * HISTORY : $Log: not supported by cvs2svn $
 * HISTORY : Revision 1.2  2008/12/09 19:04:17  linc
 * HISTORY : First GUI release
 * HISTORY :
 * HISTORY : Revision 1.1  2008/12/03 20:46:14  linc
 * HISTORY : UI update.
 * HISTORY :
 */
