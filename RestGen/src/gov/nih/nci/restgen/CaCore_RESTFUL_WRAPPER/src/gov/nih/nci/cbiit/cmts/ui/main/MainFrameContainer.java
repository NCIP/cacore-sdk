package gov.nih.nci.cbiit.cmts.ui.main;

//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: umkis
 * Date: Jun 9, 2011
 * Time: 1:10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainFrameContainer
{
    private MainFrame mainFrame;
   
    public MainFrameContainer(MainFrame mf)
    {
        setFrameContainer(mf);
    }
   
    public MainFrame getMainFrame()
    {
        return mainFrame;
    }
   
    public void setFrameContainer(MainFrame mf)
    {
        mainFrame = mf;
   
    }
   
    public Component getAssociatedUIComponent()
    {
        if (mainFrame != null) return mainFrame;
        
        return null;
    }
    public Container getAssociatedUIContainer()
    {
        if (mainFrame != null) return mainFrame;
                return null;
    }

    public void closeTab()
    {
        if (mainFrame != null) mainFrame.closeTab();
        
    }
    public void resetCenterPanel()
    {
        if (mainFrame != null) mainFrame.resetCenterPanel();
        
    }
    public java.util.List<Component> getAllTabs()
    {
        if (mainFrame != null) return mainFrame.getAllTabs();
        
        return null;
    }
    public JComponent hasComponentOfGivenClass(Class classValue, boolean bringToFront)
    {
        if (mainFrame != null) return mainFrame.hasComponentOfGivenClass(classValue, bringToFront);
        
        return null;
    }
    public void addNewTab(JPanel mappingPanel, String tabKind)
    {
        if (mainFrame != null) mainFrame.addNewTab(mappingPanel, tabKind);
        
    }

    public Frame getOwnerFrame()
    {
        if (mainFrame != null) return mainFrame;

                return null;
    }
    public JTabbedPane getTabbedPane()
    {
        if (mainFrame != null) return mainFrame.getTabbedPane();
        
        return null;
    }

    public void updateToolBar(JToolBar newToolBar)
    {
        if (mainFrame != null) mainFrame.updateToolBar(newToolBar);
        
    }
    public void updateToolBar(JToolBar newToolBar, JButton rightSideButton)
    {
        if (mainFrame != null) mainFrame.updateToolBar(newToolBar, rightSideButton);
        
    }

    public MainMenuBar getMainMenuBar()
    {
        if (mainFrame != null) return mainFrame.getMainMenuBar();
        
        return null;
    }
}
