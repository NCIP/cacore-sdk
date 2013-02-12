/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.main;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * This class
 * 
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since CMTS v1.0
 * @version $Revision: 1.1 $
 * @date       $Date: 2013-01-11
 * 
 */
public class MainFrame extends JFrame
{
	//private JTabbedPane tabbedPane = new JTabbedPane();
	private CloseAndMaxTabbedPane tabbedPane = new CloseAndMaxTabbedPane(true);
	private JPanel toolBarPanel;

	private JPanel centerPanel ;

	private JPanel currentToolBarPanel;

	private Map<Class, JComponent> tabMap;

    private MainMenuBar frameMenu;

    public MainMenuBar getFrameMenu() {
		return frameMenu;
	}

	public void setFrameMenu(MainMenuBar frameMenu) {
		this.frameMenu = frameMenu;
	}

	private static MainFrameContainer instanceContainer = null;
    
    private static MappingMainPanel mp = null;

    private MainFrame(){
		super();
	}

	/**
	 * @return the instance
	 */
	//public static MainFrame getInstance() {
	//	if(instance == null) instance = new MainFrame();
	//	return instance;
	//}
    public static MainFrameContainer getInstanceContainer() {
		if(instanceContainer == null) instanceContainer = new MainFrameContainer(new MainFrame());
		return instanceContainer;
	}
    
    public static MappingMainPanel getMappingMainPanel() throws Exception {
		if(instanceContainer != null)
		{
			if(mp==null)
			{
				mp = new MappingMainPanel(instanceContainer);
			}
		}
		return mp;
	}


    /* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#launch()
	 */
	private void launch()
	{

		try {
			tabMap = new HashMap<Class, JComponent>();
            instanceContainer = new MainFrameContainer(this);
            frameMenu=new MainMenuBar(instanceContainer);
			this.setTitle("caCore RESTFUL WRAPPER Mapping and GenerationService");
			
			Container contentPane = this.getContentPane();
			contentPane.setLayout(new BorderLayout());
			//set the icon.
			Image icon = DefaultSettings.getNCICBImage();
			this.setIconImage(icon);
			// set the menu bar.
			setJMenuBar(frameMenu);
			//set size before constructing each of those panels since some of them
			//may depend on the size to align components.
			this.setSize(DefaultSettings.FRAME_DEFAULT_WIDTH, DefaultSettings.FRAME_DEFAULT_HEIGHT);
			//contentPane.add(constructNorthPanel(), BorderLayout.NORTH);
			centerPanel = (JPanel) constructCenterPanel();
			contentPane.add(centerPanel, BorderLayout.CENTER);
			//--------------------------------------
			JPanel statusBar = new JPanel();
			contentPane.add(statusBar, BorderLayout.SOUTH);
			tabbedPane.setOpaque(false);
			enableEvents(AWTEvent.WINDOW_EVENT_MASK);
			this.setVisible(true);
			DefaultSettings.centerWindow(this);
			this.setFocusable(true);
			this.setFocusableWindowState(true);

			// added PV start
			
			instanceContainer.addNewTab(getMappingMainPanel(), ".xml");
			// added PV end
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Component constructCenterPanel() {
		JPanel rtnPanel=new JPanel();
		//ImageIcon ii1 = new ImageIcon(DefaultSettings.getImage("default_scr.gif"));
		JLabel baseScreenJLabel = new JLabel("");
		//ii1.setImageObserver(baseScreenJLabel);
		rtnPanel.add(baseScreenJLabel);
		rtnPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		rtnPanel.setOpaque(false);
		return rtnPanel;
	}

	

	public void updateToolBar(JToolBar newToolBar) {
		updateToolBar(newToolBar, null);
	}

	public void updateToolBar(JToolBar newToolBar, JButton rightSideButton) {
		//remove first in case it already contains
		JToolBar rightSideToolbar = new JToolBar();
		JPanel rightSidePanel = new JPanel(new BorderLayout());
		if (rightSideButton != null)
		{
			rightSideToolbar.add(rightSideButton);
			rightSidePanel.add(rightSideToolbar, BorderLayout.CENTER);
            if (tabbedPane.getComponentCount() > 0) rightSideButton.setEnabled(true);
        }
		toolBarPanel.remove(currentToolBarPanel);
		currentToolBarPanel.removeAll();
		currentToolBarPanel.add(newToolBar, BorderLayout.CENTER);
		currentToolBarPanel.add(rightSidePanel, BorderLayout.EAST);
		toolBarPanel.add(currentToolBarPanel, BorderLayout.SOUTH);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#addNewTab(javax.swing.JPanel)
	 */
    public void addNewTab(JPanel panel, String tabKind)
    {
        if (tabbedPane.getTabCount() == 0) {
            centerPanel.removeAll();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(tabbedPane, BorderLayout.CENTER);
        }
        String title = ActionConstants.FILE_NAME_UNTITLED_TAG + (tabbedPane.getTabCount()+1);
        
        if (tabKind!=null)
        title =title + tabKind;
        tabbedPane.addTab(title, panel);
        tabbedPane.setSelectedComponent(panel);
        System.out.println("Panel Class: '" + (panel==null?"null":panel.getClass().getName()) + "'.");
        tabMap.put(panel.getClass(), panel);
    }

	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#addNewTabForLog(javax.swing.JPanel)
	 */
    public void addNewTabForLog(JPanel panel, String tabKind)
    {
        if (tabbedPane.getTabCount() == 0) {
            centerPanel.removeAll();
            centerPanel.setLayout(new BorderLayout());
            centerPanel.add(tabbedPane, BorderLayout.CENTER);
        }
        String title = "Generate Resource Log";
        
        if (tabKind!=null)
        title =title + tabKind;
        tabbedPane.addTab(title, panel);
        tabbedPane.setSelectedComponent(panel);
        System.out.println("Panel Class: '" + (panel==null?"null":panel.getClass().getName()) + "'.");
        tabMap.put(panel.getClass(), panel);
    }
    
    
    
    
    /* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#closeTab()
	 */
	public void closeTab() {
		
		if (tabbedPane.getTabCount() > 0) {//reset if not tab at all.
			centerPanel.removeAll();
			//ImageIcon imgIcon = new ImageIcon(DefaultSettings.getImage("default_scr.gif"));
			JLabel baseScreenJLabel = new JLabel("");
			//imgIcon.setImageObserver(baseScreenJLabel);
			centerPanel.add(baseScreenJLabel);
			centerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		}

	}
	/**
	 * Only accessible by the same package so as to
	 * avoid abuse of using the tabbedPane directly.
	 * @return the tab pane.
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#hasComponentOfGivenClass(java.lang.Class, boolean)
	 */
	public JComponent hasComponentOfGivenClass(Class classValue, boolean bringToFront) {
		JComponent component = tabMap.get(classValue);
		if (component != null) {
			if (bringToFront) {
				try {
					tabbedPane.setSelectedComponent(component);
				} catch (Throwable e) {
					e.printStackTrace();
					//		    Log.logInfo(this, "What kind of Component is this: '" + component.getClass().getName() + "'.");
					//		    Log.logException(this, e);
				}
			}
			return component;
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#getAllTabs()
	 */
	public java.util.List<Component> getAllTabs() {
		java.util.List<Component> resultList = new java.util.ArrayList<Component>();
		int count = tabbedPane.getComponentCount();
        if (count > 0)
        {
            for (int i = 0; i < count; i++) {
                Component comp = tabbedPane.getComponentAt(i);
                resultList.add(comp);
            }
        }
        return resultList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#setCurrentPanelTitle(java.lang.String)
	 */
	public boolean setCurrentPanelTitle(String newTitle) {
		int seleIndex = tabbedPane.getSelectedIndex();
		if (seleIndex != -1) {
			tabbedPane.setTitleAt(seleIndex, newTitle);
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args)
	{

        try
		{
			try
			{
                String os = System.getProperty("os.name");
             
                if (os.toLowerCase().startsWith("windows"))
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                else
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
			catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (InstantiationException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IllegalAccessException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (UnsupportedLookAndFeelException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainFrame.getInstanceContainer().getMainFrame().launch();
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			//	        Log.logException(new Object(), t);
		}
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#resetCenterPanel()
	 */
	public void resetCenterPanel() // inserted by umkis on 01/18/2006, defaect# 252
	{
		centerPanel.updateUI();
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#processWindowEvent(java.awt.event.WindowEvent)
	 */
	public void processWindowEvent(WindowEvent e) {
		//		Log.logInfo(this, "processWindowEvent() invoked with '" + e + "'.");
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.getMenuBar();
			//		MainMenuBar frameMenuBar=(MainMenuBar)ContextManager.getContextManager().getMenu();
			//		CloseAllAction closeAllAction=(CloseAllAction)frameMenuBar.getDefinedAction(ActionConstants.CLOSE_ALL);//.closeAllAction;
			//	    CloseAllAction closeAllAction =ContextManager.getContextManager().getMenu().closeAllAction;
			//	    if (closeAllAction != null && closeAllAction.isEnabled()) {
			//		closeAllAction.actionPerformed(null);
			//		if (closeAllAction.isSuccessfullyPerformed()) {
			//		    super.processWindowEvent(e);
			//		} else {//back to normal process.
			//		    return;
			//		}
			//	    } else {
			//		super.processWindowEvent(e);
			//	    }
			exit();
		} else {
			super.processWindowEvent(e);
		}
	}
    public MainMenuBar getMainMenuBar()
    {
        return frameMenu;
    }

    /* (non-Javadoc)
	 * @see gov.nih.nci.caCore SDK.ui.main.AbstractMainFrame#exit()
	 */
	public void exit() {
		//    	ContextManager contextManager = ContextManager.getContextManager();
		//		if (contextManager.isItOKtoShutdown()) {
		this.exit(0);
		//		}
	}

	protected void exit(int errorLevel) {
		this.setVisible(false);
		this.dispose();
		//	Log.logInfo(this, "\r\n\r\nShutting down logging with exit code = " + errorLevel + "\r\n\r\n" + "===============================================================\r\n" + "===============================================================\r\n");
		System.exit(errorLevel);
	}
}
/**
 * HISTORY: $Log: not supported by cvs2svn $
 * HISTORY: Revision 1.2  2008/12/09 19:04:17  linc
 * HISTORY: First GUI release
 * HISTORY:
 * HISTORY: Revision 1.1  2008/12/03 20:46:14  linc
 * HISTORY: UI update.
 * HISTORY:
 */
