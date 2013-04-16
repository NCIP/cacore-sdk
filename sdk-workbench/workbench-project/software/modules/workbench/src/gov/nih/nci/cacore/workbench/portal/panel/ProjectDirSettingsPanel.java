/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.CodegenPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class ProjectDirSettingsPanel implements Panel, PanelValidator {
	
	
	private static final Logger log = Logger.getLogger(OptionsMapManager.class);
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private CodegenPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	private static final String PROJECT_GEN_DIR = "Project Generation Directory";	

	public ProjectDirSettingsPanel(CodegenPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator) {
		this.parentContainer=parentContainer;
		this.mainPanelValidator = mainPanelValidator;
	}
	
	//Deploy Settings Panel
	private JPanel projectDirSettingsPanel = null;
	private JPanel projectDirSettingsReviewPanel = null;
    
	//Deploy Settings Panel Component Definitions
	private JTextField projectGenDirField = null;
    
    //Buttons
    private JButton projectDirButton = null;
    private JButton loadPropertiesButton = null;    
    
    /**
     * This method initializes jTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getProjectGenDirField() {
        if (projectGenDirField == null) {
        	projectGenDirField = new JTextField();
        	
        	//projectGenDirField.setText(propsMgr.getDeployPropertyValue("PROJECT_GEN_DIR"));
        	projectGenDirField.setToolTipText("Enter the directory where the project will be generated");
        	projectGenDirField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	toggleLoadPropertiesButton();
//                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	toggleLoadPropertiesButton();
//                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }


                public void insertUpdate(DocumentEvent e) {
                	toggleLoadPropertiesButton();
//                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	projectGenDirField.addFocusListener(new FocusChangeHandler());
        }
        return projectGenDirField;
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
    
    public void setProjectDir(String projectDir){
    	getProjectGenDirField().setText(projectDir);
    }
    
    public void setProjectDirValue(String projectDirFieldValue){
    	this.getProjectGenDirField().setText(projectDirFieldValue.replace('\\', '/'));
    }
    
    public String getProjectDir(){
    	return getProjectGenDirField().getText().replace('\\', '/');
    }    

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getProjectDirButton() {
        if (projectDirButton == null) {
        	projectDirButton = new JButton();
        	projectDirButton.setText("Browse");
        	projectDirButton.setIcon(LookAndFeel.getBrowseIcon());
        	projectDirButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getProjectGenDirField().getText();
                        String location = ResourceManager.promptDir(previous);
                        if (location != null && location.length() > 0) {
                        	getProjectGenDirField().setText(location);
                        } else {
                        	getProjectGenDirField().setText(previous);
                        }
                        validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }
            });
        }
        return projectDirButton;
    }    
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLoadPropertiesButton() {
        if (loadPropertiesButton == null) {
            loadPropertiesButton = new JButton();
            loadPropertiesButton.setText("Load/Create Configuration");
            loadPropertiesButton.setToolTipText("Click to load the specified Codegen configuration properties to the Workbench.");
            loadPropertiesButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            loadPropertiesButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.setProjectDir(getProjectDir());
                	parentContainer.loadCodegenProperties();
                    mainPanelValidator.validateInput();
                }
            });
        }

        return loadPropertiesButton;
    }
    
    public void toggleLoadPropertiesButton() {
    	
    	if (ValidationUtils.isBlank(this.getProjectGenDirField().getText()) ||
    			!(new File(this.getProjectGenDirField().getText()).exists()) ) {
    		this.getLoadPropertiesButton().setEnabled(false);
    		return;
    	}
    	
    	this.getLoadPropertiesButton().setEnabled(true);

    }
    
	/**
	 * This method initializes projectDirSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (projectDirSettingsPanel == null) {
			
		    //Security Settings Panel Label Definitions
			JLabel projectDirLabel = null;
		    
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints10.gridy = 1;
            gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints10.gridx = 0;
            
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints11.gridx = 1;
            gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints11.gridy = 1;
//            gridBagConstraints11.weighty = 1.0D;
            gridBagConstraints11.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints12.gridy = 1;
            gridBagConstraints12.gridx = 2;
            gridBagConstraints12.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints12.gridwidth = 1;
			
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			//gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints20.gridy = 5;
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridwidth = 3;
			//gridBagConstraints20.weighty = 1.0D;
			gridBagConstraints20.weightx = 1.0D;
			
            projectDirLabel = new JLabel();
            projectDirLabel.setText("Project Generation Directory:");
            projectDirLabel.setName("Project Directory");

		    projectDirSettingsPanel = new JPanel();
		    projectDirSettingsPanel.setLayout(new GridBagLayout());
		    projectDirSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define the Project Generation Directory",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    projectDirSettingsPanel.add(projectDirLabel, gridBagConstraints10);
		    projectDirSettingsPanel.add(getProjectGenDirField(), gridBagConstraints11);
		    projectDirSettingsPanel.add(getProjectDirButton(), gridBagConstraints12);
		    projectDirSettingsPanel.add(getLoadPropertiesButton(), gridBagConstraints20);
			
		    projectDirSettingsPanel.validate();
		}
		return projectDirSettingsPanel;
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (projectDirSettingsReviewPanel == null) {
    	
		    //Deploy Settings Panel Label Definitions
	    	JLabel deployTypeLabel = null;
	    	JLabel deployTypeValueLabel = null; 	
        	
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints10.gridy = 1;
            gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints10.gridx = 0;
            
            GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
            gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints11.gridy = 1;
            gridBagConstraints11.gridx = 1;
            gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints11.weighty = 1.0D;
            gridBagConstraints11.weightx = 1.0;
            
		    deployTypeLabel = new JLabel();
		    deployTypeLabel.setText("Project Directory:");
		    deployTypeValueLabel = new JLabel();
		    deployTypeValueLabel.setText(getProjectDir());

		    projectDirSettingsReviewPanel = new JPanel();
		    projectDirSettingsReviewPanel.setLayout(new GridBagLayout());
		    projectDirSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project Directory Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    projectDirSettingsReviewPanel.add(deployTypeLabel, gridBagConstraints10);
		    projectDirSettingsReviewPanel.add(deployTypeValueLabel, gridBagConstraints11);
            
		    projectDirSettingsReviewPanel.validate();
        //}
        return projectDirSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Project Directory Setting Validation
    	if (ValidationUtils.isBlank(this.getProjectGenDirField().getText())) {
    		result.add(new SimpleValidationMessage(PROJECT_GEN_DIR + " must not be blank", Severity.ERROR, PROJECT_GEN_DIR));
    	} else {
    		File file = new File(this.getProjectGenDirField().getText());
    		if(file.exists()){
    			result.add(new SimpleValidationMessage(PROJECT_GEN_DIR + " already exists.  The output directory will be overwritten.", Severity.WARNING, PROJECT_GEN_DIR));
    		} else {
    			result.add(new SimpleValidationMessage(PROJECT_GEN_DIR + " does not exist.  Please select the Home directory of an existing directory.", Severity.ERROR, PROJECT_GEN_DIR));
    		}
    	}
   	
    	return result;
    }
    
    public void initValidation() {
        // Deploy Type Validation
        ValidationComponentUtils.setMessageKey(getProjectGenDirField(), PROJECT_GEN_DIR);
        ValidationComponentUtils.setMandatory(getProjectGenDirField(), true);
        
        toggleLoadPropertiesButton();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	//Project Dir property gets saved on the Project Settings Panel, so we don't need to save here
    	
    	return propsMap;
    }
}
