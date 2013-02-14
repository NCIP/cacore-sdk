package gov.nih.nci.restgen.ui.actions;



import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.util.FileUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.applet.Applet;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JOptionPane;



public class AboutViewAction extends AbstractContextAction {
    private static final String COMMAND_NAME = "About RESTFul Wrapper";
    private static final Character COMMAND_MNEMONIC = new Character('A');
    private static final String LICENSE_INFORMATION_HTML = "LicenseInformation.html";
    private MainFrameContainer mainFrame;

    public AboutViewAction(MainFrameContainer container) {
        super(COMMAND_NAME);
        this.setMnemonic(COMMAND_MNEMONIC);
        mainFrame=container;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean doAction(ActionEvent e) throws Exception {
        // TODO Auto-generated method stub
         String aboutTextPath="/RESTFUL_WRAPPER_About.txt";

         String warningMsg=readMessageFromFile(aboutTextPath).toString();
        //String warningMsg  = "<html><head>DDD</head><body>FFF<br><a href=\"http://www.google.com\" target=\"_blank\">CCC</a></body></html>";
         String frmName="About RESTful Wrapper";
            //JOptionPane.showMessageDialog(mainApplet, warningMsg, frmName, JOptionPane.DEFAULT_OPTION);
//        Object c = JOptionPane.showInputDialog(mainApplet,
//                                     warningMsg,
//                                     frmName,
//                                     JOptionPane.INFORMATION_MESSAGE,
//                                     null,
//                                     new String[] {"S1", "S3"},
//                                     "S1");

        int c = JOptionPane.showOptionDialog(mainFrame.getAssociatedUIComponent(),
                                           warningMsg,
                                           frmName,
                                           JOptionPane.DEFAULT_OPTION,
                                           JOptionPane.PLAIN_MESSAGE,
                                           null,
                                           new String[] {"Show License Information", "Close"},
                                           "Close");
        //System.out.println("Selected : " + c);
        if (c == 0)
        {
            
        }
        return false;
    }
    private StringBuffer readMessageFromFile(String filePath)
    {
        StringBuffer licenseBf=new StringBuffer();
        try {
            InputStream input=this.getClass().getResourceAsStream(filePath);
            InputStreamReader reader=new InputStreamReader(input);
            BufferedReader bfReader=new BufferedReader(reader);
            String lineSt;
            lineSt = bfReader.readLine();
            while (lineSt!=null)
            {
                licenseBf.append(lineSt+"\n");
                lineSt = bfReader.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }
        return licenseBf;
    }
    @Override
    protected Component getAssociatedUIComponent() {
        // TODO Auto-generated method stub
        return null;
    }

}
