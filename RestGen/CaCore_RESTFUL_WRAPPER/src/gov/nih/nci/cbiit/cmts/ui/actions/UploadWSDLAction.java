/**
 * The content of this file is subject to the caAdapter Software License (the "License").  
 * A copy of the License is available at:
 * [caAdapter CVS home directory]\etc\license\caAdapter_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caadapter/indexContent
 * /docs/caAdapter_License
 */

package gov.nih.nci.cbiit.cmts.ui.actions;


import gov.nih.nci.cbiit.cmts.ui.common.ActionConstants;
import gov.nih.nci.cbiit.cmts.ui.common.DefaultSettings;
import gov.nih.nci.cbiit.cmts.ui.dnd.GraphDropTransferHandler;
import gov.nih.nci.cbiit.cmts.ui.dnd.TreeTransferHandler;
import gov.nih.nci.cbiit.cmts.ui.main.MainFrame;
import gov.nih.nci.cbiit.cmts.ui.main.MainFrameContainer;
import gov.nih.nci.cbiit.cmts.ui.tree.DefaultTargetTreeNode;
import gov.nih.nci.cbiit.cmts.ui.tree.TreeSelectionHandler;
//import gov.nih.nci.cbiit.cmts.ui.mapping.MappingMainPanel;
//import gov.nih.nci.cbiit.cmts.ui.message.MessagePanel;

import javax.swing.*;
import gov.nih.nci.cbiit.cmts.ui.tree.DefaultTargetTreeNode;

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
 * @author Chunqing Lin
 * @author LAST UPDATE $Author: wangeug $
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2009-11-23 18:32:47 $
 */
public class UploadWSDLAction extends AbstractContextAction
{
    private static final String COMMAND_NAME = ActionConstants.UPLOADWSDL;
    private static final Character COMMAND_MNEMONIC = new Character('E');
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Open source POJO";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".xsd";
	private JTree tree;
    //hotkey//private static final KeyStroke ACCELERATOR_KEY_STROKE = KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK, false);

    private MainFrameContainer mainFrame;

    /**
     * Defines an <code>Action</code> object with a default
     * description string and default icon.
     */
    public UploadWSDLAction(MainFrameContainer mainFrame)
    {
        this(COMMAND_NAME, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a default icon.
     */
    public UploadWSDLAction(String name, MainFrameContainer mainFrame)
    {
        this(name, (Icon) null, mainFrame);
    }

    /**
     * Defines an <code>Action</code> object with the specified
     * description string and a the specified icon.
     */
    public UploadWSDLAction(String name, Icon icon, MainFrameContainer mainFrame)
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
    	// open POJO here PV
    				File file = null;
    				
    	            file = DefaultSettings.getUserInputOfFileFromGUI(mainFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
    	                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
    	            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
    	            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
    	            {
    	                //JOptionPane.showMessageDialog(this, "This file is not a XML schema (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a schema file", JOptionPane.ERROR_MESSAGE);
    	                //return;
    	            }
    	        
    	            // Display WSDL details here once the WSDL file has been selected!!
    	            mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setBorder(BorderFactory.createTitledBorder("SOAP Webservice"));
    	            mainFrame.getMainFrame().getMappingMainPanel().getTargetLocationArea().setText("Name:SpecimenService"+"\n"+"Endpoint:"+"http://www.specimen.gov/SpecimenService");
    	            
    	            
    	            /// form the tree here PV...start
    	            
    	            	DefaultTargetTreeNode top = new DefaultTargetTreeNode("WSDL");
    	                createNodes(top);
    	                tree = new JTree(top);
    	                TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(mainFrame.getMainFrame().getMappingMainPanel().getGraphController());
    	        		tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
    	        		tree.setTransferHandler(new TreeTransferHandler(mainFrame.getMainFrame().getMappingMainPanel()));
    	        		tree.setDropMode(DropMode.ON);
    	        		tree.setDragEnabled(true);
    	        		//GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
    	        		//mainFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraph().setTransferHandler(gDropHandler);
    	    			tree.setDragEnabled(true);
    	                mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setViewportView(tree);
    	            
    	            /// end
    	            
    	        
    			return true;

    }
    
    private void createNodes(DefaultTargetTreeNode top) {
	    DefaultTargetTreeNode method1 = null;
	    DefaultTargetTreeNode method2 = null;
	    DefaultTargetTreeNode method3 = null;
	    DefaultTargetTreeNode method4 = null;
	    DefaultTargetTreeNode method5 = null;
	    
	    	    
	    method1 = new DefaultTargetTreeNode("CreateSpecimen(Specimen)");
	    method2 = new DefaultTargetTreeNode("UpdateSpecimen(Specimen)");
	    method3 = new DefaultTargetTreeNode("getSpecimenById(long)");
	    method4 = new DefaultTargetTreeNode("getSpecimen(Criteria)");
	    method5 = new DefaultTargetTreeNode("deleteSpecimen(long)");
	    	    
	    top.add(method1);
	    top.add(method2);
	    top.add(method3);
	    top.add(method4);
	    top.add(method5);
        
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
