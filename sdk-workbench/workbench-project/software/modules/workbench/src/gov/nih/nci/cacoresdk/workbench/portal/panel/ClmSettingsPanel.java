package gov.nih.nci.cacoresdk.workbench.portal.panel;

import gov.nih.nci.cacoresdk.workbench.common.LookAndFeel;
import gov.nih.nci.cacoresdk.workbench.common.OptionsMapManager;
import gov.nih.nci.cacoresdk.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacoresdk.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacoresdk.workbench.portal.viewer.DeployPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class ClmSettingsPanel implements Panel, PanelValidator {
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	private static final String CLM_DB_TYPE = "CLM DB Type";
	private static final String CLM_DB_CONNECTION_URL = "CLM DB Connection URL";
	private static final String CLM_DB_SERVER = "CLM DB Hostname";
	private static final String CLM_DB_SERVER_PORT = "CLM DB Port";
	private static final String CLM_DB_NAME = "CLM DB Schema";
	private static final String CLM_DB_USERNAME = "CLM DB Username";
	private static final String CLM_DB_PASSWORD = "CLM DB Password";

    private boolean isWritableApiEnabled;
	
	public ClmSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator,
			boolean isEnableWritableApiExtensionSelected) {
		this.parentContainer = parentContainer;
		this.mainPanelValidator = mainPanelValidator;
		this.isWritableApiEnabled = isEnableWritableApiExtensionSelected;
	}
	
	// Common Logging (CLM) 
	private JPanel clmSettingsPanel = null;
	private JPanel clmSettingsSubPanel = null;
	
	private JPanel clmSettingsReviewPanel = null;
    
    //CLM (Logging) Settings Panel Component Definitions
    private JCheckBox  enableCommonLoggingModuleCheckBox = null;
    private JComboBox  clmDbTypeComboBox = null;
    private JTextField clmDbUrlField = null;
    private JTextField clmDbHostnameField = null;
    private JTextField clmDbPortField = null;
    private JTextField clmDbSchemaField = null;
    private JTextField clmDbUsernameField = null;
    private JTextField clmDbPasswordField = null;
    
    //Buttons
    private JButton testConnectionButton = null;
 
    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getClmDbTypeComboBox() {
        if (clmDbTypeComboBox == null) {
        	clmDbTypeComboBox = new JComboBox();
        	
        	Map<String,String> clmDbTypeOptionsMap = OptionsMapManager.getClmDbTypeOptionsMap();
        	if (clmDbTypeOptionsMap!=null){
            	Iterator<String> iter = clmDbTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		clmDbTypeComboBox.addItem((String)iter.next());
            	}
            	

            	String clmDbTypeValue = parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_TYPE");
            	String clmDbTypeKey = OptionsMapManager.getValueToKeyMap().get(clmDbTypeValue);
            	
            	if (clmDbTypeKey != null && clmDbTypeKey.length()>0)
            		clmDbTypeComboBox.setSelectedItem(clmDbTypeKey);
        	}
        	  		
        	clmDbTypeComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	clmDbTypeComboBox.addFocusListener(new FocusChangeHandler());
        }
        return clmDbTypeComboBox;
    }    
    
    /**
     * This method initializes the Enable Common Logging Module CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableCommonLoggingModuleCheckBox() {
        if (enableCommonLoggingModuleCheckBox == null) {
        	enableCommonLoggingModuleCheckBox = new JCheckBox();
        	enableCommonLoggingModuleCheckBox.setToolTipText("Enable Logging?");
        	enableCommonLoggingModuleCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableCommonLoggingModuleCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("ENABLE_COMMON_LOGGING_MODULE")));
        	enableCommonLoggingModuleCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableCommonLoggingModuleCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					parentContainer.toggleWritableApiFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableCommonLoggingModuleCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableCommonLoggingModuleCheckBox;
    }
    
    public boolean isEnableCommonLoggingModuleSelected(){
    	return getEnableCommonLoggingModuleCheckBox().isSelected();
    }

    /**
     * This method initializes the CLM Database Connection URL Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbUrlField() {
        if (clmDbUrlField == null) {
        	clmDbUrlField = new JTextField();
        	clmDbUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_CONNECTION_URL"));
        	clmDbUrlField.setEnabled(false);// use hostname, port, schema fields instead
        	clmDbUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmDbUrlField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbUrlField;
    }
    
    public String getClmDbUrl(){
    	return getClmDbUrlField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Hostname Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbHostnameField() {
        if (clmDbHostnameField == null) {
        	clmDbHostnameField = new JTextField();
        	clmDbHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_SERVER"));
        	clmDbHostnameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbHostnameField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbHostnameField;
    }
    
    public String getClmDbConnectionUrlHostname(){
    	return getClmDbHostnameField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Port Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbPortField() {
        if (clmDbPortField == null) {
        	clmDbPortField = new JTextField();
        	clmDbPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_SERVER_PORT"));
        	clmDbPortField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbPortField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbPortField;
    }
    
    public String getClmDbConnectionUrlPort() {
    	return getClmDbPortField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Schema Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbSchemaField() {
        if (clmDbSchemaField == null) {
        	clmDbSchemaField = new JTextField();
        	clmDbSchemaField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_NAME"));
        	clmDbSchemaField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbSchemaField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbSchemaField;
    }
    
    public String getClmDbConnectionUrlSchema(){
    	return getClmDbSchemaField().getText();
    }
    
    private void updateClmDbFields(){
    	String dbType = (String)getClmDbTypeComboBox().getSelectedItem();
    	
    	//update using hostname, port, and schema values
    	String clmDbConnectionUrlTemplate = OptionsMapManager.getDbUrlOptionsMap().get(dbType);
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@HOSTNAME@",clmDbHostnameField.getText());
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@PORT@",clmDbPortField.getText());
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@SCHEMA@",clmDbSchemaField.getText());
    	
    	clmDbUrlField.setText(clmDbConnectionUrlTemplate);
    }    
    
    /**
     * This method initializes the CLM Database Username Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbUsernameField() {
        if (clmDbUsernameField == null) {
        	clmDbUsernameField = new JTextField();
        	clmDbUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_USERNAME"));
        	clmDbUsernameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmDbUsernameField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbUsernameField;
    }
    
    public String getClmDbUsername(){
    	return getClmDbUsernameField().getText();
    }
    
    public void setClmDbUsernameEnabled(boolean isEnabled){
    	getClmDbUsernameField().setEnabled(isEnabled);
    }
    
    /**
     * This method initializes the CLM Database Password Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbPasswordField() {
        if (clmDbPasswordField == null) {
        	clmDbPasswordField = new JTextField();
        	clmDbPasswordField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_PASSWORD"));
        	clmDbPasswordField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmDbPasswordField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbPasswordField;
    }
    
    public String getClmDbPassword() {
    	return getClmDbPasswordField().getText();
    }
    
    public void setClmDbPasswordEnabled(boolean isEnabled){
    	getClmDbPasswordField().setEnabled(isEnabled);
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getTestConnectionButton() {
        if (testConnectionButton == null) {
        	testConnectionButton = new JButton();
        	testConnectionButton.setText("Test Connection");
        	testConnectionButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	testConnectionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.testDbConnection(OptionsMapManager.getDbTypeOptionsMap().get(getClmDbTypeComboBox().getSelectedItem().toString()), 
                			getClmDbUrl(), getClmDbUsername(), getClmDbPassword());
                    mainPanelValidator.validateInput();
                }
            });
        }

        return testConnectionButton;
    }
    
    public void setTestConnectionButtonEnabled(boolean enabled){
    	getTestConnectionButton().setEnabled(enabled);
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
	 * This method initializes advancedSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (clmSettingsPanel == null) {
			
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			//gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D;  
		    
		    clmSettingsPanel = new JPanel();
			clmSettingsPanel.setLayout(new GridBagLayout());
			clmSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Logging Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

			clmSettingsPanel.add(getLoggingSettingsSubPanel(), gridBagConstraints10);

			clmSettingsPanel.validate();
		}
		return clmSettingsPanel;
    }
    
	/**
	 * This method initializes clmSettingsSubPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLoggingSettingsSubPanel() {
		if (clmSettingsSubPanel == null) {
			
		    // CLM (Logging) Settings Panel Label Definitions
		    JLabel clmDbTypeLabel = null;
		    JLabel clmDbConnectionUrlLabel = null;
		    JLabel clmDbConnectionUrlHostnameLabel = null;
		    JLabel clmDbConnectionUrlPortLabel = null;
		    JLabel clmDbConnectionUrlSchemaLabel = null;
		    JLabel clmDbUsernameLabel = null;
		    JLabel clmDbPasswordLabel = null;

			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridx = 0;

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.gridwidth = 2;
			gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints11.gridx = 1;

			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridx = 0;

			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints21.gridwidth = 2;
			gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.gridx = 1;

			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridx = 0;

			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.gridy = 3;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints31.gridwidth = 2;
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

			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints60.gridy = 6;
			gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints60.gridx = 0;

			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints61.gridy = 6;
			gridBagConstraints61.weightx = 1.0;
			gridBagConstraints61.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints61.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints61.gridwidth = 2;
			gridBagConstraints61.weighty = 1.0D;
			gridBagConstraints61.gridx = 1;
			
			GridBagConstraints gridBagConstraints70 = new GridBagConstraints();
			gridBagConstraints70.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints70.gridy = 7;
			gridBagConstraints70.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints70.gridx = 0;

			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints71.gridy = 7;
			gridBagConstraints71.weightx = 1.0;
			gridBagConstraints71.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints71.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints71.gridwidth = 2;
			gridBagConstraints71.weighty = 1.0D;
			gridBagConstraints71.gridx = 1;		
			
			GridBagConstraints gridBagConstraints80 = new GridBagConstraints();
			//gridBagConstraints80.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints80.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints80.gridy = 8;
			gridBagConstraints80.gridx = 0;
			gridBagConstraints80.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints80.gridwidth = 3;
			//gridBagConstraints80.weighty = 1.0D;
			gridBagConstraints80.weightx = 1.0D;  
			
			clmDbTypeLabel = new JLabel();
			clmDbTypeLabel.setText("Select Database Type:");

		    clmDbConnectionUrlLabel = new JLabel();
		    clmDbConnectionUrlLabel.setText("Connection URL:");
		    
		    clmDbConnectionUrlHostnameLabel = new JLabel();
		    clmDbConnectionUrlHostnameLabel.setText("Hostname:");
		    
		    clmDbConnectionUrlPortLabel = new JLabel();
		    clmDbConnectionUrlPortLabel.setText("Port:");
		    
		    clmDbConnectionUrlSchemaLabel = new JLabel();
		    clmDbConnectionUrlSchemaLabel.setText("Schema:");

		    clmDbUsernameLabel = new JLabel();
		    clmDbUsernameLabel.setText("Username:");

		    clmDbPasswordLabel = new JLabel();
		    clmDbPasswordLabel.setText("Password:");
		    
		    clmSettingsSubPanel = new JPanel();
		    clmSettingsSubPanel.setLayout(new GridBagLayout());
		    clmSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logging Database Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    clmSettingsSubPanel.add(clmDbTypeLabel, gridBagConstraints10);
		    clmSettingsSubPanel.add(getClmDbTypeComboBox(), gridBagConstraints11);
		    clmSettingsSubPanel.add(clmDbConnectionUrlLabel, gridBagConstraints20);
		    clmSettingsSubPanel.add(getClmDbUrlField(), gridBagConstraints21);
		    clmSettingsSubPanel.add(clmDbConnectionUrlHostnameLabel, gridBagConstraints30);
		    clmSettingsSubPanel.add(getClmDbHostnameField(), gridBagConstraints31);
		    clmSettingsSubPanel.add(clmDbConnectionUrlPortLabel, gridBagConstraints40);
		    clmSettingsSubPanel.add(getClmDbPortField(), gridBagConstraints41);
		    clmSettingsSubPanel.add(clmDbConnectionUrlSchemaLabel, gridBagConstraints50);
		    clmSettingsSubPanel.add(getClmDbSchemaField(), gridBagConstraints51);
		    clmSettingsSubPanel.add(clmDbUsernameLabel, gridBagConstraints60);
		    clmSettingsSubPanel.add(getClmDbUsernameField(), gridBagConstraints61);   
		    clmSettingsSubPanel.add(clmDbPasswordLabel, gridBagConstraints70);
		    clmSettingsSubPanel.add(getClmDbPasswordField(), gridBagConstraints71);
		   
		    clmSettingsSubPanel.add(getTestConnectionButton(), gridBagConstraints80);

		    clmSettingsSubPanel.validate();
		}
		return clmSettingsSubPanel;
	}
    
    /**
     * This method initializes the Common Logging Module Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (clmSettingsReviewPanel == null) {
        	
		    // CLM (Logging) Settings Panel Label Definitions
        	JLabel clmDbTypeLabel = null;
        	JLabel clmDbTypeValueLabel = null;
		    JLabel clmDbConnectionUrlLabel = null;
		    JLabel clmDbConnectionUrlValueLabel = null;
		    JLabel clmDbUsernameLabel = null;
		    JLabel clmDbUsernameValueLabel = null;
		    JLabel clmDbPasswordLabel = null;
		    JLabel clmDbPasswordValueLabel = null;
        	
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
            gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints31.gridy = 3;
            gridBagConstraints31.gridx = 1;
            gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints31.weighty = 1.0D;
            gridBagConstraints31.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
            gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints40.gridy = 4;
            gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints40.gridx = 0;            

            GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
            gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints41.gridy = 4;
            gridBagConstraints41.gridx = 1;
            gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints41.weighty = 1.0D;
            gridBagConstraints41.weightx = 1.0;
        	
        	clmDbTypeLabel = new JLabel();
        	clmDbTypeLabel.setText("Type:");
        	clmDbTypeValueLabel = new JLabel();
        	clmDbTypeValueLabel.setText(OptionsMapManager.getClmDbTypeOptionsMap().get(getClmDbTypeComboBox().getSelectedItem().toString()));
		    
		    clmDbConnectionUrlLabel = new JLabel();
		    clmDbConnectionUrlLabel.setText("Connection URL:");
		    clmDbConnectionUrlValueLabel = new JLabel();
		    clmDbConnectionUrlValueLabel.setText(getClmDbUrlField().getText());
		    
		    clmDbUsernameLabel = new JLabel();
		    clmDbUsernameLabel.setText("Username:");
		    clmDbUsernameValueLabel = new JLabel();
		    clmDbUsernameValueLabel.setText(getClmDbUsernameField().getText());
		    
		    clmDbPasswordLabel = new JLabel();
		    clmDbPasswordLabel.setText("Password:");
		    clmDbPasswordValueLabel = new JLabel();
		    clmDbPasswordValueLabel.setText(getClmDbPasswordField().getText());
            
		    clmSettingsReviewPanel = new JPanel();
		    clmSettingsReviewPanel.setLayout(new GridBagLayout());
		    clmSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Common Logging Module Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
	    
		    clmSettingsReviewPanel.add(clmDbTypeLabel, gridBagConstraints10);
		    clmSettingsReviewPanel.add(clmDbTypeValueLabel, gridBagConstraints11);
		    clmSettingsReviewPanel.add(clmDbConnectionUrlLabel, gridBagConstraints20);
		    clmSettingsReviewPanel.add(clmDbConnectionUrlValueLabel, gridBagConstraints21);
		    clmSettingsReviewPanel.add(clmDbUsernameLabel, gridBagConstraints30);
		    clmSettingsReviewPanel.add(clmDbUsernameValueLabel, gridBagConstraints31);
		    clmSettingsReviewPanel.add(clmDbPasswordLabel, gridBagConstraints40);
		    clmSettingsReviewPanel.add(clmDbPasswordValueLabel, gridBagConstraints41);
            
		    clmSettingsReviewPanel.validate();
        //}
        return clmSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Writable API Setting Validation
    	if (isWritableApiEnabled){
    		
    		//CLM Setting Validation
    		if (getEnableCommonLoggingModuleCheckBox().isSelected()){

    			String clmDbConnectionUrlField = this.getClmDbUrlField().getText();
    			if (!ValidationUtils.isNotBlank(clmDbConnectionUrlField)) {
    				result.add(new SimpleValidationMessage(CLM_DB_CONNECTION_URL + " must not be blank.", Severity.ERROR, CLM_DB_CONNECTION_URL));
    			}
        		
        		if (clmDbConnectionUrlField.indexOf('<') > 1 || clmDbConnectionUrlField.indexOf('@') > 1) {
        			result.add(new SimpleValidationMessage(CLM_DB_CONNECTION_URL + " information is incomplete.  Make sure hostname, port and schema information is correct.", Severity.ERROR, CLM_DB_CONNECTION_URL));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getClmDbHostnameField().getText())) {
        			result.add(new SimpleValidationMessage(CLM_DB_SERVER + " must not be blank.", Severity.ERROR, CLM_DB_SERVER));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getClmDbPortField().getText())) {
        			result.add(new SimpleValidationMessage(CLM_DB_SERVER_PORT + " must not be blank.", Severity.ERROR, CLM_DB_SERVER_PORT));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getClmDbSchemaField().getText())) {
        			result.add(new SimpleValidationMessage(CLM_DB_NAME + " must not be blank.", Severity.ERROR, CLM_DB_NAME));
        		}

    			if (!ValidationUtils.isNotBlank(this.getClmDbUsernameField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_USERNAME + " must not be blank.", Severity.ERROR, CLM_DB_USERNAME));
    			}

    			if (!ValidationUtils.isNotBlank(this.getClmDbPasswordField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_PASSWORD + " must not be blank.", Severity.ERROR, CLM_DB_PASSWORD));
    			}
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
		// Common Logging Module DB Connection
        ValidationComponentUtils.setMessageKey(getClmDbTypeComboBox(), CLM_DB_TYPE);
        ValidationComponentUtils.setMandatory(getClmDbTypeComboBox(), true);
        ValidationComponentUtils.setMessageKey(getClmDbUrlField(), CLM_DB_CONNECTION_URL);
        ValidationComponentUtils.setMandatory(getClmDbUrlField(), true);
        ValidationComponentUtils.setMessageKey(getClmDbHostnameField(), CLM_DB_SERVER);
        ValidationComponentUtils.setMandatory(getClmDbHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getClmDbPortField(), CLM_DB_SERVER_PORT);
        ValidationComponentUtils.setMandatory(getClmDbPortField(), true);
        ValidationComponentUtils.setMessageKey(getClmDbSchemaField(), CLM_DB_NAME);
        ValidationComponentUtils.setMandatory(getClmDbSchemaField(), true);
        ValidationComponentUtils.setMessageKey(getClmDbUsernameField(), CLM_DB_USERNAME);
        ValidationComponentUtils.setMandatory(getClmDbUsernameField(), true);
        ValidationComponentUtils.setMessageKey(getClmDbPasswordField(), CLM_DB_PASSWORD);
        ValidationComponentUtils.setMandatory(getClmDbPasswordField(), true);
        
        updateClmDbFields();
        parentContainer.toggleClmTestConnectionButton();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    		
		propsMap.put("CLM_DB_TYPE", OptionsMapManager.getClmDbTypeOptionsMap().get(getClmDbTypeComboBox().getSelectedItem().toString()));
		propsMap.put("CLM_DB_CONNECTION_URL", getClmDbUrlField().getText());
		propsMap.put("CLM_DB_SERVER", getClmDbHostnameField().getText());
		propsMap.put("CLM_DB_SERVER_PORT", getClmDbPortField().getText());
		propsMap.put("CLM_DB_NAME", getClmDbSchemaField().getText());
		propsMap.put("CLM_DB_USERNAME", getClmDbUsernameField().getText());
		propsMap.put("CLM_DB_PASSWORD", getClmDbPasswordField().getText());
    	
    	return propsMap;
    }
}
