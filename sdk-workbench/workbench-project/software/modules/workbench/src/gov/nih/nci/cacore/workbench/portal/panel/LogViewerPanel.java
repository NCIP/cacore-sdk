package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.CacoreWorkbenchConstants;
import gov.nih.nci.cacore.workbench.common.FileFilters;
import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.CreateGridServiceViewer;
import gov.nih.nci.cacore.workbench.portal.viewer.DeployPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class LogViewerPanel implements Panel, PanelValidator {
	
	private static final Logger log = Logger.getLogger(LogViewerPanel.class);
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private String projectDirPath = null;
	
	// Validation Message Constants
	private static final String LOG_FILE = CacoreWorkbenchConstants.LOG_FILE_VALIDATION_KEY;	

	public LogViewerPanel(TabbedPanePropertiesValidator mainPanelValidator,
			String projectDirPath) {
		this.mainPanelValidator = mainPanelValidator;
		this.projectDirPath = projectDirPath;
	}
	
	//Log Viewer Panel
	private JPanel logViewerPanel = null;
    
	//Log Viewer Panel Component Definitions
	private JEditorPane logEditorPane = null;	
	private JTextField logFileField = null;
    
    //Buttons
    private JButton logFileButton = null;
    private JButton viewLogFileButton = null;    
    
    /**
     * This method initializes jTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getLogFileField() {
        if (logFileField == null) {
        	logFileField = new JTextField();
        	
        	logFileField.setToolTipText("Select the log file to be viewed");
        	logFileField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	toggleViewLogFileButton();
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	toggleViewLogFileButton();
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	toggleViewLogFileButton();
                    mainPanelValidator.validateInput();
                }
            });
        	logFileField.addFocusListener(new FocusChangeHandler());
        }
        
        //Have the user use the "Browse" button to select the Log file to be viewed
        logFileField.setEnabled(false);
        logFileField.setEditable(false);
        
        return logFileField;
    }
    
    
    private final class FocusChangeHandler implements FocusListener {
        public void focusGained(FocusEvent e) {
            update();
        }

        public void focusLost(FocusEvent e) {
            update();
        }

        private void update() {
        	mainPanelValidator.validateInput();
        }
    }    
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLogFileButton() {
        if (logFileButton == null) {
        	logFileButton = new JButton();
        	logFileButton.setText("Browse");
        	logFileButton.setToolTipText("Click to select the log file to be viewed");
        	logFileButton.setIcon(LookAndFeel.getBrowseIcon());
        	logFileButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = ResourceManager.getLogsDir(projectDirPath).getAbsolutePath();
                        String location = ResourceManager.promptFile(previous, FileFilters.LOG_FILTER);;
                        if (location != null && location.length() > 0) {
                        	getLogFileField().setText(location);
                        } else {
                        	getLogFileField().setText(""); // Leave blank
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }
            });
        }
        return logFileButton;
    }    
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getViewLogFileButton() {
        if (viewLogFileButton == null) {
            viewLogFileButton = new JButton();
            viewLogFileButton.setText("View Log File");
            viewLogFileButton.setToolTipText("Click to view the specified log file.");
            viewLogFileButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            viewLogFileButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	loadLogFile();
                    mainPanelValidator.validateInput();
                }
            });
        }

        return viewLogFileButton;
    }
    
    public void toggleViewLogFileButton() {
    	
    	if (ValidationUtils.isBlank(this.getLogFileField().getText()) ||
    			!(new File(this.getLogFileField().getText()).exists()) ) {
    		this.getViewLogFileButton().setEnabled(false);
    		return;
    	}
    	
    	this.getViewLogFileButton().setEnabled(true);

    }
    
	/**
	 * This method initializes logViewerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (logViewerPanel == null) {
			
		    //Security Settings Panel Label Definitions
			JLabel logFileLabel = null;
			
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D; 

            logEditorPane = new JEditorPane();
            logEditorPane.setEditable(false);
            logEditorPane.setEnabled(true);
            logEditorPane.setEditorKit(new HTMLEditorKit());
            
            //Put the editor pane in a scroll pane.
            JScrollPane editorScrollPane = new JScrollPane(logEditorPane);
            editorScrollPane.setVerticalScrollBarPolicy(
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            editorScrollPane.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);            
            editorScrollPane.setSize(new Dimension(900, 450));
            editorScrollPane.setPreferredSize(new Dimension(900, 450));
            editorScrollPane.setMinimumSize(new Dimension(900, 450));
		    
            GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints20.gridy = 2;
            gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints20.gridx = 0;
            
            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints21.gridx = 1;
            gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints21.gridy = 2;
//            gridBagConstraints11.weighty = 1.0D;
            gridBagConstraints21.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
            gridBagConstraints22.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints22.gridy = 2;
            gridBagConstraints22.gridx = 2;
            gridBagConstraints22.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints22.gridwidth = 1;
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			//gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridwidth = 3;
			//gridBagConstraints40.weighty = 1.0D;
			gridBagConstraints30.weightx = 1.0D;
			
            logFileLabel = new JLabel();
            logFileLabel.setText("Log File:");
            logFileLabel.setName("Log File");

		    logViewerPanel = new JPanel();
		    logViewerPanel.setLayout(new GridBagLayout());
		    logViewerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "View Workbench Log File",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    logViewerPanel.add(editorScrollPane, gridBagConstraints10);
		    logViewerPanel.add(logFileLabel, gridBagConstraints20);
		    logViewerPanel.add(getLogFileField(), gridBagConstraints21);
		    logViewerPanel.add(getLogFileButton(), gridBagConstraints22);
		    logViewerPanel.add(getViewLogFileButton(), gridBagConstraints30);
			
		    logViewerPanel.validate();
		}
		return logViewerPanel;
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        return null;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Log File Validation
    	if (ValidationUtils.isBlank(this.getLogFileField().getText())) {
    		result.add(new SimpleValidationMessage(LOG_FILE + " must not be blank.", Severity.ERROR, LOG_FILE));
    	} else {
    		File file = new File(this.getLogFileField().getText());
    		if(!file.exists()){
    			result.add(new SimpleValidationMessage(LOG_FILE + " does not exist.  Please select an existing log file", Severity.WARNING, LOG_FILE));
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        // Deploy Type Validation
        ValidationComponentUtils.setMessageKey(getLogFileField(), LOG_FILE);
        ValidationComponentUtils.setMandatory(getLogFileField(), true);
 
        toggleViewLogFileButton();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	return propsMap;
    }
    
    private void loadLogFile(){
    	
    	String logFilePath = getLogFileField().getText();
    	
		log.debug("Attempting to load log file: " + logFilePath);
		try {
			FileInputStream fstream = new FileInputStream(logFilePath);
			BufferedInputStream in = new BufferedInputStream(fstream);			
			logEditorPane.setText(Utils.convertStreamToString(in,true));
		} catch (Exception e) {
			log.error("Error encountered while trying to load log file "+logFilePath+":",e);
			logEditorPane.setText("<html><i>Unable to display Workbench Log information at this time.</i></html>");
		}
    }
}
