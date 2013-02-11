/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.actions;


import gov.nih.nci.restgen.codegen.GeneratorContext;
import gov.nih.nci.restgen.codegen.GeneratorException;
import gov.nih.nci.restgen.codegen.RESTfulWrapperGenerator;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.message.MessagePanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
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
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Select mapping file!!";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".xml";
	private static File mappingFile = null;

    private MainFrameContainer mainFrame;
    private static JPanel logStats = null;
    private static JTextPane textPane = null;
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
     * @throws Exception 
     */
    protected boolean doAction(ActionEvent e) throws Exception
    {
    	File file = getMappingFile(); 
        /*file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
                SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
        if ((file == null)||(!file.exists())||(!file.isFile())) return true;
        if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
        {
            JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "This file is not a mapping file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO Jar file", JOptionPane.ERROR_MESSAGE);
            return false;
        }*/
    	
        if(file!=null)
        {
        	Mapping m = MappingMainPanel.loadMapping(file);
        	GeneratorContext genContext = new GeneratorContext(m);
        	/*
        	if((mainFrame.getMainFrame().getMappingMainPanel().getOptionsPath())!=null)
        	{
        		genContext.setOutputPath(mainFrame.getMainFrame().getMappingMainPanel().getOptionsPath());
        	}
        	else
        	{
        		// display a error dialog
        		JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "Please set output path", "Output path not set", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}
        	*/
        	RESTfulWrapperGenerator restfulWrapper = new RESTfulWrapperGenerator(genContext);
        	String outputPath = genContext.getMapping().getOptions().getOutputPath();
        	///////
        	logStats = new JPanel();
        	textPane = new JTextPane();
        	JScrollPane scrollPane = new JScrollPane( logStats );
        	logStats.setBorder(BorderFactory.createRaisedBevelBorder());
        	logStats.setLayout(new BorderLayout());
        	logStats.setSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH / 3), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));
        	scrollPane.setViewportView(textPane);
        	logStats.add( textPane );
        	mainFrame.getMainFrame().addNewTabForLog(logStats, "");
        	///////
        	if(outputPath != null && !outputPath.equals(""))
        	{
        		restfulWrapper.runProcess();
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(mainFrame.getMainFrame(), "Please set output path", "Output path not set", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}

        	
        	restfulWrapper.generate();
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

	public static JTextPane getTextPane() {
		return textPane;
	}

	public static void setTextPane(JTextPane textPane) {
		GenerateRESTfulResourceAction.textPane = textPane;
	}

	public static JPanel getLogStats() {
		return logStats;
	}

	public static void setLogStats(JPanel logStats) {
		GenerateRESTfulResourceAction.logStats = logStats;
	}

	public static File getMappingFile() {
		return mappingFile;
	}

	public static void setMappingFile(File mappingFile) {
		GenerateRESTfulResourceAction.mappingFile = mappingFile;
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