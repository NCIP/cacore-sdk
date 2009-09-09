package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class ProjectSettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;
	
	private static final String PROJECT_TEMPLATE_DIR = "project-template";
	
	// Validation Message Constants
	private static final String SDK_INSTALL_DIR = "SDK installation directory";
	private static final String PROJECT_DIR = "Project generation directory";
	private static final String PROJECT_NAME = "Project name";
	private static final String NAMESPACE_PREFIX = "Project namespace prefix";
	private static final String WEBSERVICE_NAME = "Project Webservice name";
	
	public ProjectSettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	// Project Settings Panel
	private JPanel projectSettingsPanel = null;
	private JPanel projectSettingsReviewPanel = null;
    
	//Project Settings Panel Component Definitions
	private JTextField sdkInstallDirField = null;
	private JTextField projectDirField = null;
    private JTextField projectNameField = null;
	private JTextField namespacePrefixField = null;
    private JTextField webserviceNameField = null;
    private boolean hasUserModifiedWebserviceName=false;
    
    //Project Settings Buttons
    private JButton sdkInstallDirButton = null;
    private JButton projectDirButton = null;
    
    private JTextField getProjectNameField() {
        if (projectNameField == null) {
            projectNameField = new JTextField();
            projectNameField.setText(propsMgr.getDeployPropertyValue("PROJECT_NAME"));
            projectNameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    updateSuggestedWebserviceName();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                    updateSuggestedWebserviceName();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                    updateSuggestedWebserviceName();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput(); 
                }
            });
            projectNameField.addFocusListener(new FocusChangeHandler());
        }
        return projectNameField;
    }
    
    /**
     * This method initializes Project Namespace Prefix Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getNamespacePrefixField() {
        if (namespacePrefixField == null) {
            namespacePrefixField = new JTextField();
            namespacePrefixField.setText(propsMgr.getDeployPropertyValue("NAMESPACE_PREFIX"));
            namespacePrefixField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
            namespacePrefixField.addFocusListener(new FocusChangeHandler());
        }
        return namespacePrefixField;
    }


    /**
     * This method initializes webserviceNameField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getWebServiceNameField() {
        if (webserviceNameField == null) {
            webserviceNameField = new JTextField();
            webserviceNameField.setText(propsMgr.getDeployPropertyValue("WEBSERVICE_NAME"));

            webserviceNameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { 
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
            webserviceNameField.addFocusListener(new FocusChangeHandler());
        }
        return webserviceNameField;
    }


    private void updateSuggestedWebserviceName() {
    	if (!hasUserModifiedWebserviceName)
    		getWebServiceNameField().setText(getProjectNameField().getText()+"Service");
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
     * This method initializes jTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getSdkInstallDirField() {
        if (sdkInstallDirField == null) {
            sdkInstallDirField = new JTextField();

            sdkInstallDirField.setText(propsMgr.getDeployPropertyValue("SDK_INSTALL_DIR"));
            sdkInstallDirField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
            sdkInstallDirField.addFocusListener(new FocusChangeHandler());
        }
        return sdkInstallDirField;
    }
    
    public String getSdkInstallDirValue(){
    	return getSdkInstallDirField().getText();
    }
    
    public String getProjectTemplateDirValue(){
    	return getSdkInstallDirField().getText()+File.separator+PROJECT_TEMPLATE_DIR;
    }
    
    /**
     * This method initializes jTextField
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getProjectDirField() {
        if (projectDirField == null) {
        	projectDirField = new JTextField();
        	
        	projectDirField.setText(propsMgr.getDeployPropertyValue("PROJECT_DIR"));
        	projectDirField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }


                public void insertUpdate(DocumentEvent e) {
                	mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	projectDirField.addFocusListener(new FocusChangeHandler());
        }
        return projectDirField;
    }
    
    public void setProjectDir(String projectDir){
    	getProjectDirField().setText(projectDir);
    }
    
    public void setProjectDirValue(String projectDirFieldValue){
    	this.getProjectDirField().setText(projectDirFieldValue.replace('\\', '/'));
    }
    
    public String getProjectDir(){
    	return getProjectDirField().getText().replace('\\', '/');
    }

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getSdkInstallDirButton() {
        if (sdkInstallDirButton == null) {
            sdkInstallDirButton = new JButton();
            sdkInstallDirButton.setText("Browse");
            sdkInstallDirButton.setIcon(LookAndFeel.getBrowseIcon());
            sdkInstallDirButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getSdkInstallDirField().getText();
                        String location = ResourceManager.promptDir(previous);
                        if (location != null && location.length() > 0) {
                            getSdkInstallDirField().setText(location);
                        } else {
                            getSdkInstallDirField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return sdkInstallDirButton;
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
                        String previous = getProjectDirField().getText();
                        String location = ResourceManager.promptDir(previous);
                        if (location != null && location.length() > 0) {
                        	getProjectDirField().setText(location);
                        } else {
                        	getProjectDirField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return projectDirButton;
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSettingsPanel() {
        if (projectSettingsPanel == null) {
        	
            // Project Settings Panel Label Definitions
            JLabel sdkInstallDirLabel = null;
            JLabel projectDirLabel = null;
            JLabel projectNameLabel = null;
            JLabel namespacePrefixLabel = null;
            JLabel webserviceNameLabel = null;
        	
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
            gridBagConstraints11.weighty = 1.0D;
            gridBagConstraints11.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
            gridBagConstraints12.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints12.gridy = 1;
            gridBagConstraints12.gridx = 2;
            gridBagConstraints12.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints12.gridwidth = 1;
            
            GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints20.gridy = 2;
            gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints20.gridx = 0;            

            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints21.gridy = 2;
            gridBagConstraints21.gridx = 1;
            gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints21.weighty = 1.0D;
            gridBagConstraints21.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
            gridBagConstraints22.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints22.gridy = 2;
            gridBagConstraints22.gridx = 2;
            gridBagConstraints22.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints22.gridwidth = 1;
            
            GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
            gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints30.gridy = 3;
            gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints30.gridx = 0;
            
            GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
            gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints31.gridy = 3;
            gridBagConstraints31.weightx = 1.0;
            gridBagConstraints31.gridwidth = 2;
            gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints31.weighty = 1.0D;
            gridBagConstraints31.gridx = 1;
     
            GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
            gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints40.gridy = 4;
            gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints40.gridx = 0;
            
            GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints41.gridy = 4;
            gridBagConstraints41.weightx = 1.0;
            gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints41.gridwidth = 2;
            gridBagConstraints41.weighty = 1.0D;
            gridBagConstraints41.gridx = 1;
            
            GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
            gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints50.gridy = 5;
            gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints50.gridx = 0;
            
            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints51.gridy = 5;
            gridBagConstraints51.weightx = 1.0;
            gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints51.gridwidth = 2;
            gridBagConstraints51.weighty = 1.0D;
            gridBagConstraints51.gridx = 1;
                        
            sdkInstallDirLabel = new JLabel();
            sdkInstallDirLabel.setText("Enter the SDK install home directory:");
            sdkInstallDirLabel.setName("SDK Install Directory");
            
            projectDirLabel = new JLabel();
            projectDirLabel.setText("Enter the project directory:");
            projectDirLabel.setName("Project Directory");
            
            projectNameLabel = new JLabel();
            projectNameLabel.setText("Enter the Project Name:");
            
            namespacePrefixLabel = new JLabel();
            namespacePrefixLabel.setText("Enter the Project Namespace Prefix:");
            
            webserviceNameLabel = new JLabel();
            webserviceNameLabel.setText("Enter the Project Web Service Name:");
            
            projectSettingsPanel = new JPanel();
            projectSettingsPanel.setLayout(new GridBagLayout());
            projectSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Project Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            projectSettingsPanel.add(projectDirLabel, gridBagConstraints10);
            projectSettingsPanel.add(getProjectDirField(), gridBagConstraints11);
            projectSettingsPanel.add(getProjectDirButton(), gridBagConstraints12);
            projectSettingsPanel.add(sdkInstallDirLabel, gridBagConstraints20);
            projectSettingsPanel.add(getSdkInstallDirField(), gridBagConstraints21);
            projectSettingsPanel.add(getSdkInstallDirButton(), gridBagConstraints22);
            projectSettingsPanel.add(projectNameLabel, gridBagConstraints30);
            projectSettingsPanel.add(getProjectNameField(), gridBagConstraints31);
            projectSettingsPanel.add(namespacePrefixLabel, gridBagConstraints40);
            projectSettingsPanel.add(getNamespacePrefixField(), gridBagConstraints41);
            projectSettingsPanel.add(webserviceNameLabel, gridBagConstraints50);
            projectSettingsPanel.add(getWebServiceNameField(), gridBagConstraints51);
            
            projectSettingsPanel.validate();
        }
        return projectSettingsPanel;
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (projectSettingsReviewPanel == null) {
        	
            // Project Settings Panel Label Definitions
            JLabel sdkInstallDirLabel = null;
            JLabel sdkInstallDirValueLabel = null;
            JLabel projectDirLabel = null;
            JLabel projectDirValueLabel = null;
            JLabel projectNameLabel = null;
            JLabel projectNameValueLabel = null;
            JLabel namespacePrefixLabel = null;
            JLabel namespacePrefixValueLabel = null;
            JLabel webserviceNameLabel = null;
            JLabel webserviceNameValueLabel = null;
        	
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
            
            GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints20.gridy = 2;
            gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints20.gridx = 0;            

            GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
            gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints21.gridy = 2;
            gridBagConstraints21.gridx = 1;
            gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints21.weighty = 1.0D;
            gridBagConstraints21.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
            gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints30.gridy = 3;
            gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints30.gridx = 0;
            
            GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
            gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints31.gridy = 3;
            gridBagConstraints31.weightx = 1.0;
            gridBagConstraints31.gridwidth = 2;
            gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints31.weighty = 1.0D;
            gridBagConstraints31.gridx = 1;
     
            GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
            gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints40.gridy = 4;
            gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints40.gridx = 0;
            
            GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints41.gridy = 4;
            gridBagConstraints41.weightx = 1.0;
            gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints41.gridwidth = 2;
            gridBagConstraints41.weighty = 1.0D;
            gridBagConstraints41.gridx = 1;
            
            GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
            gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints50.gridy = 5;
            gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints50.gridx = 0;
            
            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints51.gridy = 5;
            gridBagConstraints51.weightx = 1.0;
            gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints51.gridwidth = 2;
            gridBagConstraints51.weighty = 1.0D;
            gridBagConstraints51.gridx = 1;
                        
            sdkInstallDirLabel = new JLabel();
            sdkInstallDirLabel.setText("SDK Install Home Directory:");
            sdkInstallDirValueLabel = new JLabel();
            sdkInstallDirValueLabel.setText(getSdkInstallDirField().getText());
            
            projectDirLabel = new JLabel();
            projectDirLabel.setText("Project Directory:");
            projectDirValueLabel = new JLabel();
            projectDirValueLabel.setText(getProjectDirField().getText());
            
            projectNameLabel = new JLabel();
            projectNameLabel.setText("Project Name:");
            projectNameValueLabel = new JLabel();
            projectNameValueLabel.setText(getProjectNameField().getText());
            
            namespacePrefixLabel = new JLabel();
            namespacePrefixLabel.setText("Project Namespace Prefix:");
            namespacePrefixValueLabel = new JLabel();
            namespacePrefixValueLabel.setText(getNamespacePrefixField().getText());
            
            webserviceNameLabel = new JLabel();
            webserviceNameLabel.setText("Project Web Service Name:");
            webserviceNameValueLabel = new JLabel();
            webserviceNameValueLabel.setText(getWebServiceNameField().getText());
            
            projectSettingsReviewPanel = new JPanel();
            projectSettingsReviewPanel.setLayout(new GridBagLayout());
            projectSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            projectSettingsReviewPanel.add(projectDirLabel, gridBagConstraints10);
            projectSettingsReviewPanel.add(projectDirValueLabel, gridBagConstraints11);
            projectSettingsReviewPanel.add(sdkInstallDirLabel, gridBagConstraints20);
            projectSettingsReviewPanel.add(sdkInstallDirValueLabel, gridBagConstraints21);
            projectSettingsReviewPanel.add(projectNameLabel, gridBagConstraints30);
            projectSettingsReviewPanel.add(projectNameValueLabel, gridBagConstraints31);
            projectSettingsReviewPanel.add(namespacePrefixLabel, gridBagConstraints40);
            projectSettingsReviewPanel.add(namespacePrefixValueLabel, gridBagConstraints41);
            projectSettingsReviewPanel.add(webserviceNameLabel, gridBagConstraints50);
            projectSettingsReviewPanel.add(webserviceNameValueLabel, gridBagConstraints51);
            
            projectSettingsReviewPanel.validate();
        //}
        return projectSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Panel Settings Validation
    	if (ValidationUtils.isBlank(this.getProjectDirField().getText())) {
    		result.add(new SimpleValidationMessage(PROJECT_DIR + " must not be blank.", Severity.ERROR, PROJECT_DIR));
    	} else {
    		File file = new File(this.getProjectDirField().getText());
    		if(file.exists()){
    			result.add(new SimpleValidationMessage(PROJECT_DIR + " already exists.  The output directory will be overwritten.", Severity.WARNING, PROJECT_DIR));
    		}
    	}
    	
    	if (ValidationUtils.isBlank(this.getSdkInstallDirField().getText())) {
    		result.add(new SimpleValidationMessage(SDK_INSTALL_DIR + " must not be blank.", Severity.ERROR, SDK_INSTALL_DIR));
    	} else {
    		File file = new File(this.getSdkInstallDirField().getText());
    		if(!file.exists()){
    			result.add(new SimpleValidationMessage(SDK_INSTALL_DIR + " does not exist.  Please select the Home directory of an existing SDK Installation.", Severity.ERROR, SDK_INSTALL_DIR));
    		}
    	}
    	
    	if (ValidationUtils.isNotBlank(this.getProjectDirField().getText()) 
    			&& ValidationUtils.isNotBlank(this.getSdkInstallDirField().getText()) &&
    			this.getProjectDirField().getText().equalsIgnoreCase(this.getSdkInstallDirField().getText())) {
    		result.add(new SimpleValidationMessage(PROJECT_DIR + " and " + SDK_INSTALL_DIR + " must be different.", Severity.ERROR, PROJECT_DIR));
    		result.add(new SimpleValidationMessage(PROJECT_DIR + " and " + SDK_INSTALL_DIR + " must be different.", Severity.ERROR, SDK_INSTALL_DIR));
    	} 

    	if (ValidationUtils.isBlank(this.getProjectNameField().getText())) {
    		result.add(new SimpleValidationMessage(PROJECT_NAME + " must not be blank.", Severity.ERROR, PROJECT_NAME));
    	}

    	if (ValidationUtils.isBlank(this.getNamespacePrefixField().getText())) {
    		result.add(new SimpleValidationMessage(NAMESPACE_PREFIX + " must not be blank.", Severity.ERROR, NAMESPACE_PREFIX));
    	}

    	if (ValidationUtils.isBlank(this.getWebServiceNameField().getText())) {
    		result.add(new SimpleValidationMessage(WEBSERVICE_NAME + " must not be blank.", Severity.ERROR, WEBSERVICE_NAME));
    	}
    	
    	return result;
    }
    
    public void initValidation() {
    	//Project
        ValidationComponentUtils.setMessageKey(getSdkInstallDirField(), SDK_INSTALL_DIR);
        ValidationComponentUtils.setMandatory(getSdkInstallDirField(), true);
        ValidationComponentUtils.setMessageKey(getProjectDirField(), PROJECT_DIR);
        ValidationComponentUtils.setMandatory(getProjectDirField(), true);
        ValidationComponentUtils.setMessageKey(getProjectNameField(), PROJECT_NAME);
        ValidationComponentUtils.setMandatory(getProjectNameField(), true);
        ValidationComponentUtils.setMessageKey(getNamespacePrefixField(), NAMESPACE_PREFIX);
        ValidationComponentUtils.setMandatory(getNamespacePrefixField(), true);
        ValidationComponentUtils.setMessageKey(getWebServiceNameField(), WEBSERVICE_NAME);
        ValidationComponentUtils.setMandatory(getWebServiceNameField(), true);
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	//propsMap.put("output.dir", (getProjectDirField().getText() + File.separator + "output").replace('\\', '/'));
    	propsMap.put("SDK_INSTALL_DIR", getSdkInstallDirField().getText().replace('\\', '/'));
    	propsMap.put("PROJECT_DIR", getProjectDirField().getText().replace('\\', '/'));
		
    	propsMap.put("PROJECT_NAME", getProjectNameField().getText());
    	propsMap.put("NAMESPACE_PREFIX", getNamespacePrefixField().getText());
    	propsMap.put("WEBSERVICE_NAME", getWebServiceNameField().getText());
    	
    	return propsMap;
    }
}
