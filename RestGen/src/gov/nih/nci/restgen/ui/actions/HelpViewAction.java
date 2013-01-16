package gov.nih.nci.restgen.ui.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.net.URLConnection;
import java.io.File;
import javax.swing.*;


import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.util.FileUtil;

public class HelpViewAction extends AbstractContextAction {
    private static final String COMMAND_NAME = ActionConstants.HELP;
    private static final Character COMMAND_MNEMONIC = new Character('H');
    private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false);
//	private static final ImageIcon IMAGE_ICON = new ImageIcon(DefaultSettings.getImage("Help16.gif"));
//	private static final String TOOL_TIP_DESCRIPTION = ActionConstants.HELP;

    //private JApplet mainApplet;
    private MainFrameContainer mainFrame;
    public HelpViewAction(MainFrameContainer container) {
        super(COMMAND_NAME);
        this.setMnemonic(COMMAND_MNEMONIC);
         mainFrame=container;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean doAction(ActionEvent e) throws Exception {
        // TODO Auto-generated method stub
                
        String location = ActionConstants.CMTS_HELP_MENU_CONTENT_URL;

        try
        {
            URL ur = new URL(location);
            URLConnection ct = ur.openConnection();
            ct.getInputStream();
        }
        catch(Exception ee)
        {
            location = null;
        }

      
        return true;
    }

    @Override
    protected Component getAssociatedUIComponent() {
        // TODO Auto-generated method stub
        return mainFrame.getAssociatedUIComponent();
    }

}
