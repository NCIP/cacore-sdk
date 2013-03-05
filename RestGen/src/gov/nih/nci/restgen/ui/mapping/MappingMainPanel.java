/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */
package gov.nih.nci.restgen.ui.mapping;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;


import gov.nih.nci.restgen.mapping.model.Implementation;
import gov.nih.nci.restgen.mapping.model.Mapping;
import gov.nih.nci.restgen.mapping.model.Method;
import gov.nih.nci.restgen.mapping.model.Resource;
import gov.nih.nci.restgen.ui.actions.NewPOJOFileAction;
import gov.nih.nci.restgen.ui.actions.OpenPOJOJarAction;
import gov.nih.nci.restgen.ui.actions.UploadEJBJarAction;
import gov.nih.nci.restgen.ui.actions.UploadWSDLAction;
import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.dnd.GraphDropTransferHandler;
import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.jgraph.MiddlePanelJGraphController;
import gov.nih.nci.restgen.ui.jgraph.MiddlePanelMarqueeHandler;
import gov.nih.nci.restgen.ui.jgraph.MiddlePanelJGraphController.MethodType;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.DefaultTargetTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeMouseAdapter;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;




/**
 * This class
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.16 $
 * @date       $Date: 2013-01-11
 *
 */
public class MappingMainPanel extends JPanel implements ActionListener
{

	private static final String Cmps_V3_MESSAGE_FILE_DEFAULT_EXTENSION = ".map";
	private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Upload Binding file";
	private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_TARGET_FILE = "Select JNDI File";
	private static final String ENTER_JNDI_NAME = "Enter JNDI name";
	private static final String SELECT_CSV_TIP = "select POJO";
	private static final String SELECT_HMD_TIP = "select WSDL";
	private static final String SELECT_SOURCE = "Upload Binding File";
	private static final String UPLOAD_JNDI = "Select JNDI File";
	private static final String SELECT_TARGET = "Open WSDL...";
	private static String ejbType = null;
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".xml";
	private static String optionsPath = "";
	private static String wsdlURL = "";
	private static String enterJNDIName = null;
	private static final String TARGET_TREE_FILE_DEFAULT_EXTENTION = ".properties";
	private static MainFrameContainer mainFrame = null;
    private File mapFile = null;
    private static JButton enterJNDIButton = null;
    private static JRadioButton remoteButton = null;
    private static JRadioButton localButton = null;
    private static ButtonGroup group = null;
    private static JButton openSourceButton = null;
    private static JButton openSourceWSDLButton = null;
    private static File mappingSourceFile = null;
	private static File mappingTargetFile = null;
	private static String sourceFileType = null;
	private static String targetFileType = null;
	private static JPanel targetLocationPanel = null;
	private static String WSDLBindingFilePath = null;
	private static String JNDIPropertiesFilePath = null;
	private static JPanel targetButtonPanel = null;
	private static JPanel targetRadioButtonPanel = null;
	private static Hashtable resourcePathValues = null;
	private static JSplitPane leftRightSplitPane = null;
	private static JTextArea textArea = new JTextArea();
	public static Hashtable getResourcePathValues() {
		return resourcePathValues;
	}


	public static String getWsdlURL() {
		return wsdlURL;
	}


	public static void setWsdlURL(String wsdlURL) {
		MappingMainPanel.wsdlURL = wsdlURL;
	}


	public static void setResourcePathValues(Hashtable resourcePathValues) {
		MappingMainPanel.resourcePathValues = resourcePathValues;
	}

	public static ArrayList<String> POJOClassList = new ArrayList<String>();
	private static JTree sTree = null;
	private static JTree tTree = null;
	public static JTree getSourceTree() {
		return sTree;
	}


	public static JButton getEnterJNDIButton() {
		return enterJNDIButton;
	}


	public static String getEjbType() {
		return ejbType;
	}


	public static void setEjbType(String ejbType) {
		MappingMainPanel.ejbType = ejbType;
	}



	public static void setEnterJNDIButton(JButton enterJNDIButton) {
		MappingMainPanel.enterJNDIButton = enterJNDIButton;
	}


	public static void setSourceTree(JTree sTree) {
		MappingMainPanel.sTree = sTree;
	}


	public static JTree getTargetTree() {
		return tTree;
	}


	public static JPanel getTargetRadioButtonPanel() {
		return targetRadioButtonPanel;
	}


	public static void setTargetRadioButtonPanel(JPanel targetRadioButtonPanel) {
		MappingMainPanel.targetRadioButtonPanel = targetRadioButtonPanel;
	}


	public static JButton getOpenSourceButton() {
		return openSourceButton;
	}


	public static JTextArea getTextArea() {
		return textArea;
	}


	public static void setTextArea(JTextArea textArea) {
		MappingMainPanel.textArea = textArea;
	}


	public static void setOpenSourceButton(JButton openSourceButton) {
		MappingMainPanel.openSourceButton = openSourceButton;
	}


	public static String getOptionsPath() {
		return optionsPath;
	}


	public JPanel getTargetButtonPanel() {
		return targetButtonPanel;
	}


	public static String getEnterJNDIName() {
		return enterJNDIName;
	}


	public static void setEnterJNDIName(String enterJNDIName) {
		MappingMainPanel.enterJNDIName = enterJNDIName;
	}


	public void setTargetButtonPanel(JPanel targetButtonPanel) {
		this.targetButtonPanel = targetButtonPanel;
	}


	public File getMappingTargetFile() {
		return mappingTargetFile;
	}


	public void setMappingTargetFile(File mappingTargetFile) {
		this.mappingTargetFile = mappingTargetFile;
	}


	public File getMappingSourceFile() {
		return mappingSourceFile;
	}


	public void setMappingSourceFile(File mappingSourceFile) {
		this.mappingSourceFile = mappingSourceFile;
	}


	public static void setOptionsPath(String optionsPath) {
		MappingMainPanel.optionsPath = optionsPath;
	}


	public static String getJNDIPropertiesFilePath() {
		return JNDIPropertiesFilePath;
	}


	public static void setJNDIPropertiesFilePath(String jNDIPropertiesFile) {
		JNDIPropertiesFilePath = jNDIPropertiesFile;
	}


	public static MainFrameContainer getMainFrame() {
		return mainFrame;
	}


	public static void setMainFrame(MainFrameContainer mainFrame) {
		mainFrame = mainFrame;
	}


	public static void setTargetTree(JTree tTree) {
		MappingMainPanel.tTree = tTree;
	}

	public static JSplitPane getLeftRightSplitPane() {
		return leftRightSplitPane;
	}


	public static void setLeftRightSplitPane(JSplitPane leftRightSplitPane) {
		MappingMainPanel.leftRightSplitPane = leftRightSplitPane;
	}

	private MappingTreeScrollPane sourceScrollPane = new MappingTreeScrollPane(MappingTreeScrollPane.DRAW_NODE_TO_RIGHT);
	private MappingTreeScrollPane targetScrollPane = new MappingTreeScrollPane(MappingTreeScrollPane.DRAW_NODE_TO_LEFT);
	private static MiddlePanelJGraphController graphController =null;
	
	public MiddlePanelJGraphController getGraphController() {
		return graphController;
	}
	
	
	public static JPanel getTargetLocationPanel() {
		return targetLocationPanel;
	}


	public static String getWSDLBindingFilePath() {
		return WSDLBindingFilePath;
	}


	public static void setWSDLBindingFilePath(String wSDLBindingFilePath) {
		WSDLBindingFilePath = wSDLBindingFilePath;
	}


	public static void setTargetLocationPanel(JPanel targetLocationPanel) {
		MappingMainPanel.targetLocationPanel = targetLocationPanel;
	}


	public static String getTargetFileType() {
		return targetFileType;
	}


	public static void setTargetFileType(String targetFileType) {
		MappingMainPanel.targetFileType = targetFileType;
	}


	public MappingTreeScrollPane getTargetScrollPane() {
		if(targetScrollPane==null)
		{
			targetScrollPane = new MappingTreeScrollPane(MappingTreeScrollPane.DRAW_NODE_TO_RIGHT);
		}
		return targetScrollPane;
	}

	public void setTargetScrollPane(MappingTreeScrollPane targetScrollPane) {
		this.targetScrollPane = targetScrollPane;
	}


	private MappingMiddlePanel middlePanel = null;
	private JTextField sourceLocationArea = new JTextField();
	private JTextArea targetLocationArea = new JTextArea();
	
    //private boolean hasBeenChanged = false;

    public MappingMainPanel(MainFrameContainer mainFrame) throws Exception
	{
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new BorderLayout());
        this.mainFrame = mainFrame;
        middlePanel = new MappingMiddlePanel(this);
        graphController = new MiddlePanelJGraphController(this);
		MiddlePanelMarqueeHandler marquee=(MiddlePanelMarqueeHandler)middlePanel.getGraph().getMarqueeHandler();
		marquee.setController(graphController);
		GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
		middlePanel.getGraph().setTransferHandler(gDropHandler);
		this.add(getCenterPanel(false), BorderLayout.CENTER);
		
    }

	
    

	private JComponent getCenterPanel(boolean functionPaneRequired)
	{//construct the top level layout of mapping panel
		/**
		 * GUI Layout:
		 * JSplitPane - Horizontal:   --> leftRightSplitPane
		 *      left: JSplitPane - Horizontal: --> topCenterPanel, centerSplitPane
		 *				left: source panel; --> sourceButtonPanel
		 *				right: JSplitPane - Horizontal: --> rightSplitPane
		 *							left: middle panel for graph; -->middleContainerPanel
		 *							right: target panel; -->targetButtonPanel
		 * 		right: JSplitPane - Vertical:  -->topBottomSplitPane
		 * 				top: functional pane; -->functionPane
		 *				bottom: properties panel; -->propertiesPane
		 */

		leftRightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		DefaultSettings.setDefaultFeatureForJSplitPane(leftRightSplitPane);
        int locDiv = (int) (mainFrame.getAssociatedUIContainer().getWidth() * 0.85);
        leftRightSplitPane.setDividerLocation(locDiv); //.setDividerLocation(0.85);
        //System.out.println("First locDiv : " + locDiv);
        leftRightSplitPane.setLeftComponent(getTopLevelLeftPanel());
        //leftRightSplitPane.setRightComponent(getTopLevelRightPanel());
        // PV commented below 12-11
		return leftRightSplitPane;
	}

	
	/**
	 * This constructs function and properties panels.
	 *
	 * @return the top level right pane.
	 */
			
	
//	/**
//	 * return the close action inherited with this client.
//	 * @return the close action inherited with this client.
//	 */
//	public Action getDefaultCloseAction()
//	{//by doing this way, the menu and the panel will use the same close action.
//		Map actionMap = getMenuItems(MenuConstants.FILE_MENU_NAME);
//		Action closeAction = (Action) actionMap.get(ActionConstants.CLOSE);
//		return closeAction;
//	}

	
	public MappingMiddlePanel getMiddlePanel() {
		return middlePanel;
	}

	/**
	 * Return the top root container (frame or dialog or window) this panel is associated with.
	 * @return the top root container (frame or dialog or window) this panel is associated with.
	 */
	public Container getRootContainer()
	{
		return DefaultSettings.findRootContainer(this);
	}

	
	public JPanel getTopLevelLeftPanel()
	{
		JPanel topCenterPanel = new JPanel(new BorderLayout());
		topCenterPanel.setBorder(BorderFactory.createEtchedBorder());
		JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		DefaultSettings.setDefaultFeatureForJSplitPane(centerSplitPane);

        int frameWidth = mainFrame.getAssociatedUIContainer().getWidth();

        //construct source panel
		JPanel sourceButtonPanel = new JPanel(new BorderLayout());
		sourceButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		JPanel sourceLocationPanel = new JPanel(new BorderLayout(2, 0));
		sourceLocationPanel.setBorder(BorderFactory.createEmptyBorder());
		//		sourceTreeCollapseAllAction = new TreeCollapseAllAction(sTree);
		//		sourceTreeExpandAllAction = new TreeExpandAllAction(sTree);

		JToolBar sourceTreeToolBar = new JToolBar("Source Tree Tool Bar");
		sourceTreeToolBar.setFloatable(false);
		//		sourceTreeToolBar.add(sourceTreeExpandAllAction);
		//		sourceTreeToolBar.add(sourceTreeCollapseAllAction);
		sourceLocationPanel.add(sourceTreeToolBar, BorderLayout.WEST);

		sourceLocationArea.setEditable(false);
		//sourceLocationArea.setPreferredSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH / 10), 24));
        sourceLocationArea.setPreferredSize(new Dimension((frameWidth / 10), 24));
	    sourceLocationPanel.add(sourceLocationArea, BorderLayout.CENTER);

		//JButton openSourceButton = new JButton(SELECT_SOURCE);
		//sourceLocationPanel.add(openSourceButton, BorderLayout.EAST);
		//openSourceButton.setMnemonic('S');
		//openSourceButton.setToolTipText(SELECT_CSV_TIP);
		//openSourceButton.addActionListener(this);
		//sourceButtonPanel.add(sourceLocationPanel, BorderLayout.NORTH);
		//sourceScrollPane.setSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH / 4), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));
        sourceScrollPane.setSize(new Dimension((int)((frameWidth*0.85) / 4), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));
		sourceButtonPanel.add(sourceScrollPane, BorderLayout.CENTER);

		//construct target panel
		JPanel targetButtonPanel = new JPanel(new BorderLayout());
		targetButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		targetLocationPanel = new JPanel(new BorderLayout(2, 0));
		targetLocationPanel.setBorder(BorderFactory.createEmptyBorder());
		//		targetTreeCollapseAllAction = new TreeCollapseAllAction(tTree);
		//		targetTreeExpandAllAction = new TreeExpandAllAction(tTree);
		JToolBar targetTreeToolBar = new JToolBar("Target Tree Tool Bar");
		targetTreeToolBar.setFloatable(false);
		//		targetTreeToolBar.add(targetTreeExpandAllAction);
		//		targetTreeToolBar.add(targetTreeCollapseAllAction);
		//targetLocationPanel.add(targetTreeToolBar, BorderLayout.WEST);
		targetLocationArea.setBackground(new Color(212,208,200));
		targetLocationArea.setLineWrap(true);
		targetLocationArea.setEditable(false);
		//targetLocationArea.setPreferredSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH / 10), 24));
        targetLocationArea.setPreferredSize(new Dimension((frameWidth / 4+70), 10));
		targetLocationPanel.add(targetLocationArea, BorderLayout.WEST);
		
		/*JButton openTargetButton = new JButton(SELECT_TARGET);
		targetLocationPanel.add(openTargetButton, BorderLayout.EAST);
				
		openTargetButton.setMnemonic('T');
		openTargetButton.setToolTipText(SELECT_HMD_TIP);
		openTargetButton.addActionListener(this);
		*/
		targetButtonPanel.add(targetLocationPanel, BorderLayout.NORTH);
		targetButtonPanel.add(targetScrollPane, BorderLayout.CENTER);
		
				
		//targetButtonPanel.setPreferredSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH / 5), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));
        //targetButtonPanel.setPreferredSize(new Dimension((int)((frameWidth*0.85) / 5), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));

		//construct middle panel
		JPanel middleContainerPanel = new JPanel(new BorderLayout());
		JLabel placeHolderLabel = new JLabel();
		//placeHolderLabel.setPreferredSize(new Dimension((int) (DefaultSettings.FRAME_DEFAULT_WIDTH / 3.5), 24));
        placeHolderLabel.setPreferredSize(new Dimension((int)((frameWidth*0.85) / 3.5), 24));
		middleContainerPanel.add(placeHolderLabel, BorderLayout.NORTH);
		middleContainerPanel.add(middlePanel, BorderLayout.CENTER);

		JSplitPane rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		DefaultSettings.setDefaultFeatureForJSplitPane(rightSplitPane);
		//rightSplitPane.setDividerLocation(0.5);
        rightSplitPane.setDividerLocation(50);
        rightSplitPane.setLeftComponent(middleContainerPanel);
		rightSplitPane.setRightComponent(targetButtonPanel);
        rightSplitPane.setDividerLocation((int)((frameWidth*0.85)/3));

        centerSplitPane.setLeftComponent(sourceButtonPanel);
		centerSplitPane.setRightComponent(rightSplitPane);
        centerSplitPane.setDividerLocation(((int)((frameWidth*0.85)/3)) - (centerSplitPane.getDividerSize()*2));

        topCenterPanel.add(centerSplitPane, BorderLayout.CENTER);
		//topCenterPanel.setPreferredSize(new Dimension((int) (DefaultSettings.FRAME_DEFAULT_WIDTH * 0.8), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));
        topCenterPanel.setPreferredSize(new Dimension((int) (frameWidth * 0.85), (int) (DefaultSettings.FRAME_DEFAULT_HEIGHT / 1.5)));

		return topCenterPanel;


	}

	
	public void createOpenWSDLBindingFileButton()
	{
		if(targetButtonPanel==null)
		{
			targetButtonPanel = new JPanel(new BorderLayout(2, 0));
			targetButtonPanel.setBorder(BorderFactory.createEtchedBorder());
			targetButtonPanel.setLayout(new GridLayout(5, 5));
		}
		if(targetRadioButtonPanel==null)
		{
			targetRadioButtonPanel = new JPanel(new BorderLayout(2, 0));
			targetRadioButtonPanel.setBorder(BorderFactory.createEtchedBorder());
			targetRadioButtonPanel.setLayout(new GridLayout(5, 5));
			targetRadioButtonPanel.setPreferredSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH/ 10), 90));
		}
		if(openSourceWSDLButton==null)
		{
			openSourceWSDLButton = new JButton(SELECT_SOURCE);
			openSourceWSDLButton.setMnemonic('S');
			openSourceWSDLButton.setBounds(0, 0, 20, 20);
			openSourceWSDLButton.setToolTipText(SELECT_CSV_TIP);
			openSourceWSDLButton.addActionListener(this);
		}
		targetButtonPanel.add(Box.createHorizontalStrut(10));
		targetButtonPanel.add(openSourceWSDLButton, BorderLayout.SOUTH);
		targetLocationPanel.add(targetButtonPanel, BorderLayout.EAST);
		targetLocationPanel.add(targetRadioButtonPanel, BorderLayout.SOUTH);
		
	}
	
	public void createEJBJNDIButtons()
	{
		if(targetButtonPanel==null)
		{
			targetButtonPanel = new JPanel(new BorderLayout(2, 0));
			targetButtonPanel.setBorder(BorderFactory.createEtchedBorder());
			targetButtonPanel.setLayout(new GridLayout(5, 5));
		}
		if(targetRadioButtonPanel==null)
		{
			targetRadioButtonPanel = new JPanel(new BorderLayout(2, 0));
			targetRadioButtonPanel.setBorder(BorderFactory.createEtchedBorder());
			targetRadioButtonPanel.setLayout(new GridLayout(5, 5));
		}
		targetRadioButtonPanel.setPreferredSize(new Dimension((DefaultSettings.FRAME_DEFAULT_WIDTH/ 10), 90));
	    // add upload JNDI button here....
		if(openSourceButton==null)
		{
			openSourceButton = new JButton("Select JNDI File");
			openSourceButton.setMnemonic('U');
			openSourceButton.setBounds(0, 0, 20, 20);
			openSourceButton.setToolTipText(SELECT_CSV_TIP);
			openSourceButton.addActionListener(this);
		}		
		// Enter JNDI name here....
		if(enterJNDIButton==null)
		{
				enterJNDIButton = new JButton("Enter JNDI name");
				enterJNDIButton.setMnemonic('U');
				enterJNDIButton.setBounds(0, 0, 20, 20);
				enterJNDIButton.setToolTipText(SELECT_CSV_TIP);
				enterJNDIButton.addActionListener(this);
		}		
		// add radio button group here
		if(remoteButton==null)
		{
			remoteButton = new JRadioButton("Remote");
			remoteButton.setMnemonic('R');
			remoteButton.setActionCommand("Remote");
			remoteButton.setSelected(false);
		}
		if(localButton==null)
		{
			localButton = new JRadioButton("Local");
			localButton.setMnemonic('L');
			localButton.setActionCommand("Local");
			localButton.setSelected(false);
			
		}
		if(group==null)
		{	    //Group the radio buttons.
		    group = new ButtonGroup();
		    group.add(remoteButton);
		    group.add(localButton);
		    //Register a listener for the radio buttons.
		    remoteButton.addActionListener(this);
		    localButton.addActionListener(this);
	    }
		targetRadioButtonPanel.setBorder(BorderFactory.createTitledBorder("Client Type"));
		targetRadioButtonPanel.add(remoteButton, BorderLayout.NORTH);
		targetRadioButtonPanel.add(Box.createHorizontalStrut(10));
		targetRadioButtonPanel.add(localButton, BorderLayout.SOUTH);
		targetButtonPanel.add(Box.createHorizontalStrut(10));
		targetButtonPanel.add(openSourceButton, BorderLayout.SOUTH);
		targetButtonPanel.add(Box.createHorizontalStrut(10));
		targetButtonPanel.add(enterJNDIButton, BorderLayout.SOUTH);
		targetLocationPanel.add(targetRadioButtonPanel, BorderLayout.SOUTH);
		targetLocationPanel.add(targetButtonPanel, BorderLayout.EAST);
		// set the EJB type default to "REMOTE"
				//setEjbType("EJB_REMOTE");
		//
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		//System.out.println("INDSIDEEE>>>> action click.....");
		try
		{
            if (SELECT_SOURCE.equals(command))
			{
                processingButtonOpen(SELECT_SOURCE);
            }
			else if (UPLOAD_JNDI.equals(command))
			{
                processingButtonOpen(UPLOAD_JNDI);
            }
            
			else if(command.equals("Local"))
			{
				//getEnterJNDIButton().setEnabled(false);
				getOpenSourceButton().setEnabled(false);
				String toReplaceJNDIText = "\n\n"+"JNDI properties file:"+getJNDIPropertiesFilePath();
				getTargetLocationArea().setText(getTargetLocationArea().getText().replace(toReplaceJNDIText, ""));
				setJNDIPropertiesFilePath("");
				setEjbType("EJB_LOCAL");
				
				
			}
			else if(command.equals("Remote"))
			{
				getEnterJNDIButton().setEnabled(true);
				getOpenSourceButton().setEnabled(true);
				setEjbType("EJB_REMOTE");
			}
            
			else if(command.equals(ENTER_JNDI_NAME))
			{
				String jndiName = null;
				
				if(getEnterJNDIName()!=null && !getEnterJNDIName().equals(""))
				{
					jndiName = JOptionPane.showInputDialog(null, "Please enter the JNDI name: ",getEnterJNDIName().trim());
				}
				
				else
				{
					jndiName = JOptionPane.showInputDialog(null, "Please enter the JNDI name: ","");
				}
				if(jndiName!=null)
				{
					
					String toReplace = "\n\n"+"JNDI Name:"+getEnterJNDIName();
					String currString = "\n\n"+"JNDI Name:"+jndiName;
					if(getTargetLocationArea().getText().contains("JNDI Name:"))
					{
						String newOne = getTargetLocationArea().getText().replace(toReplace, currString);
						//System.out.println("inside//...."+newOne);
						getTargetLocationArea().setText(getTargetLocationArea().getText().replace(toReplace, currString));
						
					}
					else
					{
						getTargetLocationArea().append("\n\n"+"JNDI Name:"+jndiName);
					}
					setEnterJNDIName(jndiName);
				}
			}
			
		}
		catch (Exception e1)
		{
			//e1.printStackTrace();
			DefaultSettings.reportThrowableToLogAndUI(this, e1, "", this, false, false);
		}
	}
	
	
	private void processingButtonOpen(String type) throws Exception
    {
        //this.sourceButtonPanel.repaint();
        File file = null;
        
        
        if (type.equals(SELECT_SOURCE))
        {
            file = DefaultSettings.getUserInputOfFileFromGUI(this, //FileUtil.getUIWorkingDirectoryPath(),
                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
            if ((file == null)||(!file.exists())||(!file.isFile())) return;
            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(this, "This file is not a binding XML file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a binding file", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(file!=null)
            {
            		
					String toReplace = "\n\n"+"WSDL Binding file:"+getWSDLBindingFilePath();
					String currString = "\n\n"+"WSDL Binding file:"+file.getPath();
					if(getTargetLocationArea().getText().contains("WSDL Binding file:"))
					{
						String newOne = getTargetLocationArea().getText().replace(toReplace, currString);
						getTargetLocationArea().setText(getTargetLocationArea().getText().replace(toReplace, currString));
						
					}
					else
					{
						getTargetLocationArea().append("\n\n"+"WSDL Binding file:"+file.getPath());
					}
					setWSDLBindingFilePath(file.getPath());
				
            }
        
        }
        else
        {
            file = DefaultSettings.getUserInputOfFileFromGUI(this,
                        TARGET_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_TARGET_FILE, false, false);
            if ((file == null)||(!file.exists())||(!file.isFile())) return;
            if (!file.getName().toLowerCase().endsWith(TARGET_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(this, "This file is not a JNDI prop file (" + TARGET_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a binding file", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if(file!=null)
            {
            	
            	String toReplace = "\n\n"+"JNDI properties file:"+getJNDIPropertiesFilePath();
				String currString = "\n\n"+"JNDI properties file:"+file.getPath();
				if(getTargetLocationArea().getText().contains("JNDI properties file:"))
				{
					String newOne = getTargetLocationArea().getText().replace(toReplace, currString);
					getTargetLocationArea().setText(getTargetLocationArea().getText().replace(toReplace, currString));
					
				}
				else
				{
					getTargetLocationArea().append("\n\n"+"JNDI properties file:"+file.getPath());
				}
				setJNDIPropertiesFilePath(file.getPath());
            	
            }
        }
            
    }
	

	public MappingTreeScrollPane getSourceScrollPane() {
		if(sourceScrollPane==null)
		{
			sourceScrollPane = new MappingTreeScrollPane(MappingTreeScrollPane.DRAW_NODE_TO_RIGHT);
		}
		return sourceScrollPane;
	}


	public static ArrayList<String> getPOJOClassList() {
		return POJOClassList;
	}


	public static String getSourceFileType() {
		return sourceFileType;
	}


	public static void setSourceFileType(String sourceFileType) {
		MappingMainPanel.sourceFileType = sourceFileType;
	}


	public static void setPOJOClassList(ArrayList<String> pOJOClassList) {
		POJOClassList = pOJOClassList;
	}


	public void setSourceScrollPane(MappingTreeScrollPane sourceScrollPane) {
		this.sourceScrollPane = sourceScrollPane;
	}


	public JTextArea getTargetLocationArea() {
		return targetLocationArea;
	}


	public void setTargetLocationArea(JTextArea targetLocationArea) {
		this.targetLocationArea = targetLocationArea;
	}

	
	public void persistFile(File persistentFile)
	{
		
		MiddlePanelJGraphController mappingManager = getGraphController();//.getMiddlePanel().getGraphController();
		gov.nih.nci.restgen.mapping.model.Mapping mappingData = mappingManager.retrieveMappingData(true,persistentFile.getName());
		String errorString = checkForValidMappingData(mappingData);

		if(errorString!=null && !errorString.trim().equals(""))
		{
			JTextArea textArea = new JTextArea(errorString);
			JScrollPane scrollPane = new JScrollPane(textArea);  
			textArea.setLineWrap(true);  
			textArea.setWrapStyleWord(true); 
			scrollPane.setPreferredSize( new Dimension( 300, 200 ) );
			JOptionPane.showMessageDialog(this, scrollPane, "Data input errors",  
    	                                       JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		try {
			saveMapping(persistentFile, mappingData);
			JOptionPane.showMessageDialog(getParent(), "Mapping data has been saved successfully.", "Save Complete", JOptionPane.INFORMATION_MESSAGE);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            JOptionPane.showMessageDialog(getParent(), e.getMessage(), "JAXBException", JOptionPane.ERROR_MESSAGE);
        } 
		/*catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
            JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Save Failure", JOptionPane.ERROR_MESSAGE);
        }*/
		//clear the change flag.
		getGraphController().setGraphChanged(false);
        //hasBeenChanged = false;
        //setChanged(false);
    }

	private String checkForValidMappingData(Mapping mappingData) {
		// TODO Auto-generated method stub
		String errorString = null;
		String targetFileType = null;
		String WSDLBindingFilePath = null;
		String ejbType = null;
		String enterJNDIName = null;
		String JNDIPropertiesFilePath = null;
		Implementation imp = null;
		String outputPath = mappingData.getOptions().getOutputPath();
		targetFileType = mappingData.getOptions().getWrapperType();
		WSDLBindingFilePath = mappingData.getOptions().getWsdlBindingFile();
		List<Resource> rsc =  mappingData.getResources();
		if(outputPath.equals(null) || outputPath.equals(""))
		{
			if(errorString!=null)
			{
				errorString = errorString + "Please Set the Options output path\n";
			}
			else{
				errorString = "Please Set the Options output path\n";
			}
	
		}
		
		if(rsc!=null && rsc.size()>0)
		{
			Iterator<Resource> rscit = rsc.iterator();
			while(rscit.hasNext())
			{
				Resource rscVar =  (Resource) rscit.next();
				if(rscVar.getPath()==null || rscVar.getPath().equals("") )
				{
					if(errorString!=null)
					{
						errorString = errorString + "Please Set the path for resource:"+rscVar.getName()+"\n";
					}
					else{
						errorString = "Please set the path for resource:"+rscVar.getName()+"\n";
					}
				}
			}
		
		}
		if(rsc!=null && rsc.size()>0)
		{
			Iterator<Resource> rscit = rsc.iterator();
			while(rscit.hasNext())
			{
				Resource rscVar =  (Resource) rscit.next();
				List<Method> mtd =  rscVar.getMethods();
				if(mtd!=null && mtd.size()>0)
				{
					Iterator<Method> mtdit =mtd.iterator();
					while(mtdit.hasNext())
					{
						imp = ((Method)mtdit.next()).getImplementation();
						if(imp!=null)
						{
							break;
						}
					}
				}
				if(imp!=null)
				{
					break;
				}

			}
			if (imp != null) {
				if(targetFileType.equals("EJB"))
				{
					ejbType = imp.getClientType();
					enterJNDIName = imp.getJndiName();
					JNDIPropertiesFilePath = imp.getJndiProperties();
					if(ejbType==null || ejbType.equals(""))
					{
						if(errorString!=null)
						{
							errorString = errorString + "Please select EJB Type\n";
						}
						else{
							errorString = "Please select EJB Type\n";
						}
						
					}
					else if(ejbType.equals("EJB_REMOTE"))
					{
						if(enterJNDIName==null || enterJNDIName.equals(""))
						{
							if(errorString!=null)
							{
								errorString = errorString + "Please select EJB JNDI Name Type\n";
							}
							else{
								errorString = "Please select EJB JNDI Name Type\n";
							}
						}
						if(JNDIPropertiesFilePath==null || JNDIPropertiesFilePath.equals(""))
						{
							if(errorString!=null)
							{
								errorString = errorString + "Please select EJB JNDIProperties \n";
							}
							else{
								errorString = "Please select EJB JNDIProperties \n";
							}

						}
					}
					else if (ejbType.equals("EJB_LOCAL"))
					{
						if(enterJNDIName==null || enterJNDIName.equals(""))
						{
							if(errorString!=null)
							{
								errorString = errorString + "Please select EJB JNDI Name Type\n";
							}
							else{
								errorString = "Please select EJB JNDI Name Type\n";
							}
						}
					}

				}

			}
		}
		return errorString;
	}


	public static void saveMapping(File f, gov.nih.nci.restgen.mapping.model.Mapping m) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance( "gov.nih.nci.restgen.mapping.model" );
        Marshaller u = jc.createMarshaller();
        u.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
        u.marshal(new JAXBElement<gov.nih.nci.restgen.mapping.model.Mapping>(new QName("mapping"),gov.nih.nci.restgen.mapping.model.Mapping.class, m), f);

    }
	
// Added PV start
	
	/**
	 * Called by actionPerformed() and overridable by descendant classes.
	 *
	 * @param file
	 * @throws Exception changed from protected to pulic by sean
	 */
    public void processOpenMapFile(File file) throws Exception
	{
        processOpenMapFile(file, null);
    }
    public void processOpenMapFile(File file, gov.nih.nci.restgen.mapping.model.Mapping newMapping) throws Exception
	{

		long stTime=System.currentTimeMillis();
		// parse the file.
		gov.nih.nci.restgen.mapping.model.Mapping mapping = null;

        if (newMapping == null)
        {
            mapping = loadMapping(file);
        }
        else
        {
   
            mapping = newMapping;
        }
	    	
			List<gov.nih.nci.restgen.mapping.model.Component> l = mapping.getComponents();
			String sourceFilePath = null;
			String targetFilePath = null;
			String targetFileType = null;
			String WSDLBindingFilePath = null;
			String ejbType = null;
			String enterJNDIName = null;
			String JNDIPropertiesFilePath = null;
			Implementation imp = null;
			targetFileType = mapping.getOptions().getWrapperType();
			WSDLBindingFilePath = mapping.getOptions().getWsdlBindingFile();
			List<Resource> rsc =  mapping.getResources();
			// validate the source and target files here and return
			String optionPath = mapping.getOptions().getOutputPath();
			String wsdlLocation = mapping.getOptions().getWsdlLocation();
			
			if(optionPath!=null && !optionPath.equals(""))
			{
				setOptionsPath(optionPath);
			}
			for(gov.nih.nci.restgen.mapping.model.Component c:l){
				if(c.getType().equals("source"))
				{
					sourceFilePath = c.getLocation();
					
				}
				else if(c.getType().equals("target"))
				{
					targetFilePath = c.getLocation();
				}
			}
			
			// added
						if(targetFileType.equals("SOAP_SERVICE"))
						{
							if(wsdlLocation!=null && !wsdlLocation.equals(""))
							{
							File fileWSDL = new File("WSDLFile.wsdl");

							URLConnection  uCon = null;
							URL wsdlUrl = new URL(wsdlLocation);
							uCon = wsdlUrl.openConnection();
							InputStream is = uCon.getInputStream();
							OutputStream os = new FileOutputStream(fileWSDL);
							int c;
							while ((c = is.read()) != -1) {
							os.write(c);
							}
							is.close();
							os.close();
							}
							else
							{
								JOptionPane.showMessageDialog(this, "Target file is not present at the path...", "Target file not found!!!", JOptionPane.ERROR_MESSAGE);
								throw new Exception("Target file not found");
							}
						}
						
		   //
			
		if(sourceFilePath==null || sourceFilePath.equals("") || !new File(sourceFilePath).exists())
		{

			JOptionPane.showMessageDialog(this, "Source file is not present at the path...", "Source file not found!!!", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Source file not found");
    		

		}
		else if(targetFilePath==null || targetFilePath.equals("") || !new File(targetFilePath).exists()) 
		{
			JOptionPane.showMessageDialog(this, "Target file is not present at the path...", "Target file not found!!!", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Target file not found");

		}
		// populate resource paths here
		
		if(rsc!=null && rsc.size()>0)
		{
			Iterator<Resource> rscit = rsc.iterator();
			while(rscit.hasNext())
			{
				Resource rscVar =  (Resource) rscit.next();
				if(rscVar.getPath()!=null && !rscVar.getPath().equals("") )
				{
						//
					
			    		   Hashtable<String, String> values = new Hashtable();
			    		   values.put(rscVar.getName(), rscVar.getPath());
			    		   setResourcePathValues(values);
			    		//
				}
			}
		
		}
		
		// end
		
			if(rsc!=null && rsc.size()>0)
			{
				Iterator<Resource> rscit = rsc.iterator();
		    	while(rscit.hasNext())
		    	{
		    		Resource rscVar =  (Resource) rscit.next();
		    		List<Method> mtd =  rscVar.getMethods();
		    		if(mtd!=null && mtd.size()>0)
					{
		    		Iterator<Method> mtdit =mtd.iterator();
		    		while(mtdit.hasNext())
			    	{
		    			imp = ((Method)mtdit.next()).getImplementation();
		    			if(imp!=null)
		    			{
		    				break;
		    			}
		    		}
					}
		    		if(imp!=null)
	    			{
	    				break;
	    			}
		    		
		    	}
			if (imp != null) {
				if(targetFileType.equals("EJB"))
				{
					ejbType = imp.getClientType();
					if (ejbType != null) {
					setEjbType(ejbType);
					}
					enterJNDIName = imp.getJndiName();
					if (enterJNDIName != null) {
					
						setEnterJNDIName(enterJNDIName);
						
					}	
				
					JNDIPropertiesFilePath = imp.getJndiProperties();
					if (JNDIPropertiesFilePath != null) {
							setJNDIPropertiesFilePath(JNDIPropertiesFilePath);
						
					}
				}
				else
				{
					if(WSDLBindingFilePath!=null)
					{
						setWSDLBindingFilePath(WSDLBindingFilePath);
							
					}
				}
			}
			}
			if(targetFileType!=null)
			{
				setTargetFileType(targetFileType);
			}
			
		//System.out.println("source file path......target file path"+sourceFilePath+targetFilePath);	
		setMappingSourceFile(new File(sourceFilePath));
			
			if(sourceFilePath.contains(".class"))
			{
				NewPOJOFileAction newpojo = new NewPOJOFileAction(mainFrame);
				newpojo.createSourceTree(new File(sourceFilePath));
			}
			else 
			{
				setMappingSourceFile(new File(sourceFilePath));
				OpenPOJOJarAction newpojo = new OpenPOJOJarAction(mainFrame);
				newpojo.createSourceTree(new File(sourceFilePath));
			}
			setMappingTargetFile(new File(targetFilePath));
			// clear the target panel
			if(getTargetTree()!=null)
    		{
            	getMiddlePanel().getGraphController().handleDeleteAll();
    			getTargetScrollPane().setViewportView(null);
    			setTargetTree(null);
    			getTargetLocationArea().setBorder(BorderFactory.createTitledBorder(""));
    			getTargetLocationArea().setText("");
    			getTargetButtonPanel().removeAll();
    			getTargetRadioButtonPanel().setBorder(BorderFactory.createTitledBorder(""));
    			getTargetRadioButtonPanel().removeAll();
    			getTargetButtonPanel().updateUI();
    			//mainFrame.getMainFrame().getMappingMainPanel().getTargetScrollPane().setBackground(new Color(212,208,200));
    			
    		}
			
			if(targetFilePath.contains(".jar"))
			{
				UploadEJBJarAction newejb = new UploadEJBJarAction(mainFrame);
				newejb.createTargetTree(new File(targetFilePath));
				if(getEjbType()!=null)
				{
					if(getEjbType().equals("EJB_LOCAL"))
					{
						if(localButton!=null)
						{
							localButton.setSelected(true);
						}
					}
					else
					{
						if(remoteButton!=null)
						{
							remoteButton.setSelected(true);
						}
					}
				}
			}
			else
			{
				setMappingTargetFile(new File(targetFilePath));
				UploadWSDLAction newwsdl = new UploadWSDLAction(mainFrame);
				newwsdl.createTargetTree(new File(targetFilePath));
			}
		// commented rem later PV
		getGraphController().setMappingData(mapping, true);
        // PV
        getMiddlePanel().renderInJGraph();
		
        getMiddlePanel().setMappingNamesforLinkInGraph(mapping);
        
    }
    
    
    public static gov.nih.nci.restgen.mapping.model.Mapping loadMapping(File f) throws JAXBException
    {
    	gov.nih.nci.restgen.mapping.model.Mapping mapLoaded = null;
        String mappingParentPath = null;

        //System.out.println("MappingFactory.loadMapping()...mappingFile:"+f.getAbsolutePath());
            mappingParentPath=f.getAbsoluteFile().getParentFile().getAbsolutePath();
            //System.out.println("MappingFactory.loadMapping()..mapping Parent:"+mappingParentPath);
            JAXBContext jc=null;
            jc = JAXBContext.newInstance( "gov.nih.nci.restgen.mapping.model" );
            Unmarshaller u = jc.createUnmarshaller();
            JAXBElement<gov.nih.nci.restgen.mapping.model.Mapping> jaxbElmt = null;
            try
            {
                jaxbElmt = u.unmarshal(new StreamSource(f), gov.nih.nci.restgen.mapping.model.Mapping.class);
            }
            catch(UnmarshalException ee1)
            {
                throw new JAXBException(ActionConstants.MESSAGE_NOT_A_MAPPING_FILE  + " (MappingFactory.loadMapping()) : " + f.getName());
            }
            catch(Exception ee2)
            {
                throw new JAXBException("Mapping file opening error : " + f.getAbsolutePath() + "\n" + ee2.getMessage());
            }
            mapLoaded=jaxbElmt.getValue();
       
        return mapLoaded;
        
    }
	
    
    public void createSourceTree()
    {
    	   /// form the tree here PV...start
        
        DefaultSourceTreeNode top = new DefaultSourceTreeNode("POJO Jar");
        createNodesSource(top);
        sTree = new JTree(top);
        TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(this.getGraphController());
        sTree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
        sTree.setTransferHandler(new TreeTransferHandler(this));
        sTree.setDropMode(DropMode.ON);
        sTree.addMouseListener(new TreeMouseAdapter(mainFrame,sTree));
        sTree.setDragEnabled(true);
	    sTree.setDragEnabled(true);
        int size = sTree.getRowCount();
		for (int i = 0; i < size+5; i++)
		{
			if (i<sTree.getRowCount())
				sTree.expandRow(i);
		}
        getSourceScrollPane().setViewportView(sTree);
        
        
    /// end

    	
    }
    
    public void createTargetTree()
    {
    	// Display WSDL details here once the WSDL file has been selected!!
        getTargetLocationArea().setBorder(BorderFactory.createTitledBorder("SOAP Webservice"));
        getTargetLocationArea().setText("Name:SpecimenService"+"\n"+"Endpoint:"+"http://www.specimen.gov/SpecimenService");
        
        /// form the tree here PV...start
        
        	DefaultTargetTreeNode top = new DefaultTargetTreeNode("WSDL");
            createNodesTarget(top);
            tTree = new JTree(top);
            TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(this.getGraphController());
            tTree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
            tTree.setTransferHandler(new TreeTransferHandler(this));
            tTree.setDropMode(DropMode.ON);
            tTree.setDragEnabled(true);
    		//GraphDropTransferHandler gDropHandler=new GraphDropTransferHandler();
    		//""getMiddlePanel().getGraph().setTransferHandler(gDropHandler);
            tTree.setDragEnabled(true);
            getTargetScrollPane().setViewportView(tTree);
        
        /// end
    }
    
    private void createNodesSource(DefaultMutableTreeNode top) {
		DefaultSourceTreeNode class1 = null;
	    DefaultSourceTreeNode class2 = null;
	    DefaultSourceTreeNode class3 = null;
	    
	    DefaultSourceTreeNode Createclass1 = null;
	    DefaultSourceTreeNode Updateclass1 = null;
	    DefaultSourceTreeNode Readclass1 = null;
	    DefaultSourceTreeNode Deleteclass1 = null;
	    
	    DefaultSourceTreeNode Createclass2 = null;
	    DefaultSourceTreeNode Updateclass2 = null;
	    DefaultSourceTreeNode Readclass2 = null;
	    DefaultSourceTreeNode Deleteclass2 = null;
	    
	    DefaultSourceTreeNode Createclass3 = null;
	    DefaultSourceTreeNode Updateclass3 = null;
	    DefaultSourceTreeNode Readclass3 = null;
	    DefaultSourceTreeNode Deleteclass3 = null;
	    
	    class1 = new DefaultSourceTreeNode("Site");
	    
	    class2 = new DefaultSourceTreeNode("Specimen");
	    	    
	    class3 = new DefaultSourceTreeNode("SpecimenCollectionGroup");
	    	    
	    
	    Createclass1 = new DefaultSourceTreeNode("Create");
	    class1.add(Createclass1);
	    
	    Updateclass1 = new DefaultSourceTreeNode("Update");
	    class1.add(Updateclass1);
	    
	    Readclass1 = new DefaultSourceTreeNode("Read");
	    class1.add(Readclass1);
	    
	    Deleteclass1 = new DefaultSourceTreeNode("Delete");
	    class1.add(Deleteclass1);

	    
	    Createclass2 = new DefaultSourceTreeNode("Create");
	    class2.add(Createclass2);
	    
	    Updateclass2 = new DefaultSourceTreeNode("Update");
	    class2.add(Updateclass2);
	    
	    Readclass2 = new DefaultSourceTreeNode("Read");
	    class2.add(Readclass2);
	    
	    Deleteclass2 = new DefaultSourceTreeNode("Delete");
	    class2.add(Deleteclass2);

	    
	    Createclass3 = new DefaultSourceTreeNode("Create");
	    class3.add(Createclass3);
	    
	    Updateclass3 = new DefaultSourceTreeNode("Update");
	    class3.add(Updateclass3);
	    
	    Readclass3 = new DefaultSourceTreeNode("Read");
	    class3.add(Readclass3);
	    
	    Deleteclass3 = new DefaultSourceTreeNode("Delete");
	    class3.add(Deleteclass3);

	    	    
	    top.add(class1);
	    top.add(class2);
	    top.add(class3);
        
	}
    
    
    private void createNodesTarget(DefaultTargetTreeNode top) {
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


    
}

/**
 * HISTORY: $Log: not supported by cvs2svn $
 * HISTORY: Revision 1.15  2009/11/23 18:31:44  wangeug
 * HISTORY: create new package: ui.main
 * HISTORY:
 * HISTORY: Revision 1.14  2009/11/03 18:32:54  wangeug
 * HISTORY: clean codes: keep MiddlePanelJGraphController only with MiddleMappingPanel
 * HISTORY:
 * HISTORY: Revision 1.13  2009/10/30 14:45:30  wangeug
 * HISTORY: simplify code: only respond to link highter
 * HISTORY:
 * HISTORY: Revision 1.12  2009/10/28 16:47:06  wangeug
 * HISTORY: clean codes
 * HISTORY:
 * HISTORY: Revision 1.11  2009/10/27 19:26:16  wangeug
 * HISTORY: clean codes
 * HISTORY:
 * HISTORY: Revision 1.10  2009/10/27 18:25:08  wangeug
 * HISTORY: hook property panel with tree nodes
 * HISTORY:
 * HISTORY: Revision 1.9  2009/10/16 17:36:34  wangeug
 * HISTORY: use cell renderer
 * HISTORY:
 * HISTORY: Revision 1.8  2009/10/15 18:35:33  wangeug
 * HISTORY: clean codes
 * HISTORY:
 * HISTORY: Revision 1.7  2008/12/29 22:18:18  linc
 * HISTORY: function UI added.
 * HISTORY:
 * HISTORY: Revision 1.6  2008/12/09 19:04:17  linc
 * HISTORY: First GUI release
 * HISTORY:
 * HISTORY: Revision 1.5  2008/12/04 21:34:20  linc
 * HISTORY: Drap and Drop support with new Swing.
 * HISTORY:
 * HISTORY: Revision 1.4  2008/12/03 20:46:14  linc
 * HISTORY: UI update.
 * HISTORY:
 * HISTORY: Revision 1.3  2008/11/04 15:58:57  linc
 * HISTORY: updated.
 * HISTORY:
 * HISTORY: Revision 1.2  2008/10/30 16:02:14  linc
 * HISTORY: updated.
 * HISTORY:
 * HISTORY: Revision 1.1  2008/10/27 20:06:30  linc
 * HISTORY: GUI first add.
 * HISTORY:
 */

