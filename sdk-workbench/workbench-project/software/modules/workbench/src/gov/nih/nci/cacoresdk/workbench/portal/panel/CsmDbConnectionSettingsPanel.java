package gov.nih.nci.cacoresdk.workbench.portal.panel;

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

public final class CsmDbConnectionSettingsPanel implements Panel, PanelValidator {
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	private static final String CSM_USE_DB_CONNECTION_SETTINGS = "Use DB Connection Settings";
	private static final String CSM_JNDI_NAME = "JNDI Name";
	private static final String CSM_DB_CONNECTION_URL = "DB Connection URL";
	private static final String CSM_DB_SERVER = "DB Hostname";
	private static final String CSM_DB_SERVER_PORT = "DB Port";
	private static final String CSM_DB_NAME = "DB Schema";
	private static final String CSM_DB_USERNAME = "DB Username";
	private static final String CSM_DB_PASSWORD = "DB Password";
    
    // Cross-panel properties
    boolean isEnableSecuritySelected;
	
	public CsmDbConnectionSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator,
			boolean isEnableSecuritySelected) {
		this.parentContainer=parentContainer;
		this.mainPanelValidator = mainPanelValidator;
		this.isEnableSecuritySelected = isEnableSecuritySelected;
	}
	
	// App Server Panel
	private JPanel csmDbConnectionSettingsPanel = null;
	private JPanel csmDbConnectionJndiSettingsSubPanel = null;
	private JPanel csmDbConnectionSettingsSubPanel = null;
	
	private JPanel csmDbConnectionSettingsReviewPanel = null;
    
	//CSM DB Connection Settings Panel Component Definitions
    private JCheckBox  csmUseDbConnectionSettingsCheckBox = null;
    private JComboBox  csmDbTypeComboBox = null;
    private JCheckBox  csmUseJndiBasedConnectionCheckBox = null;
    private JTextField csmDbJndiUrlField = null;
    private JTextField csmDbConnectionUrlField = null;
    private JTextField csmDbHostnameField = null;
    private JTextField csmDbPortField = null;
    private JTextField csmDbSchemaField = null;
    private JTextField csmDbUsernameField = null;
    private JTextField csmDbPasswordField = null;
    
    /**
     * This method initializes the CSM 'Use DB Connection Settings?' Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getCsmUseDbConnectionSettingsCheckBox() {
        if (csmUseDbConnectionSettingsCheckBox == null) {
        	csmUseDbConnectionSettingsCheckBox = new JCheckBox();
        	csmUseDbConnectionSettingsCheckBox.setToolTipText("Use DB Connection Settings?");
        	csmUseDbConnectionSettingsCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	csmUseDbConnectionSettingsCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_USE_DB_CONNECTION_SETTINGS")));
        	csmUseDbConnectionSettingsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	csmUseDbConnectionSettingsCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					parentContainer.syncDbCsmDbFields();
					toggleCsmDbConnectionFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	csmUseDbConnectionSettingsCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return csmUseDbConnectionSettingsCheckBox;
    } 
    
    public boolean isCsmUseDBConnectionSettings(){
    	return getCsmUseDbConnectionSettingsCheckBox().isSelected();
    }
    
    public void setCsmUseDbConnectionSettings(boolean isSelected){
    	getCsmUseDbConnectionSettingsCheckBox().setSelected(isSelected);
    }
    
    public void setCsmUseDbConnectionSettingsEnabled(boolean isEnabled){
    	getCsmUseDbConnectionSettingsCheckBox().setEnabled(isEnabled);
    }
    
    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getCsmDbTypeComboBox() {
        if (csmDbTypeComboBox == null) {
        	csmDbTypeComboBox = new JComboBox();
        	
        	Map<String,String> dbTypeOptionsMap = OptionsMapManager.getDbTypeOptionsMap();
        	if (dbTypeOptionsMap!=null){
            	Iterator<String> iter = dbTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		csmDbTypeComboBox.addItem((String)iter.next());
            	}
            	
            	String dbTypeValue = parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_TYPE");
            	String dbTypeKey = OptionsMapManager.getValueToKeyMap().get(dbTypeValue);
            	
            	if (dbTypeKey != null && dbTypeKey.length()>0)
            		csmDbTypeComboBox.setSelectedItem(dbTypeKey);
        	}
        	  		
        	csmDbTypeComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                    	updateCsmDbFields();
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	csmDbTypeComboBox.addFocusListener(new FocusChangeHandler());
        }
        return csmDbTypeComboBox;
    }
    
    public void setCsmDatabaseType(String selectedItemValue){
    	getCsmDbTypeComboBox().setSelectedItem(selectedItemValue);
    }

    /**
     * This method initializes the CSM Use JNDI Based Connection Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getCsmUseJndiBasedConnectionCheckBox() {
        if (csmUseJndiBasedConnectionCheckBox == null) {
        	csmUseJndiBasedConnectionCheckBox = new JCheckBox();
        	csmUseJndiBasedConnectionCheckBox.setToolTipText("Use a JNDI-based CSM Connection?");
        	csmUseJndiBasedConnectionCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	csmUseJndiBasedConnectionCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_USE_JNDI_BASED_CONNECTION")));
        	csmUseJndiBasedConnectionCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	csmUseJndiBasedConnectionCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleCsmDbConnectionFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	csmUseJndiBasedConnectionCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return csmUseJndiBasedConnectionCheckBox;
    }  
    
    public boolean isCsmUseJndiBasedConnection(){
    	return getCsmUseJndiBasedConnectionCheckBox().isSelected();
    }
    
    public void setCsmUseJndiBasedConnection(boolean isSelected){
    	getCsmUseJndiBasedConnectionCheckBox().setSelected(isSelected);
    }
    
    /**
     * This method initializes the CSM Database JNDI URL Field
     * 
     * @return javax.swing.JTextField
     */
    public JTextField getCsmDbJndiNameField() {
        if (csmDbJndiUrlField == null) {
        	csmDbJndiUrlField = new JTextField();
        	csmDbJndiUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_JNDI_NAME"));
        	csmDbJndiUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	csmDbJndiUrlField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbJndiUrlField;
    }
    
    public void setCsmDbJndiUrl(String csmDbJndiURL){
    	getCsmDbJndiNameField().setText(csmDbJndiURL);
    }
    
    /**
     * This method initializes the CSM Database Connection URL Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbConnectionUrlField() {
        if (csmDbConnectionUrlField == null) {
        	csmDbConnectionUrlField = new JTextField();
        	csmDbConnectionUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_CONNECTION_URL"));
        	csmDbConnectionUrlField.setEnabled(false);
        	csmDbConnectionUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	csmDbConnectionUrlField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbConnectionUrlField;
    }
    
    public void setCsmDbConnectionUrl(String csmDbConnectionUrl){
    	getCsmDbConnectionUrlField().setText(csmDbConnectionUrl);
    }
    
    /**
     * This method initializes the CSM Database Connection URL Hostname Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbHostnameField() {
        if (csmDbHostnameField == null) {
        	csmDbHostnameField = new JTextField();
        	csmDbHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_SERVER"));
     
        	csmDbHostnameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	csmDbHostnameField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbHostnameField;
    }
    
    public void setCsmDbConnectionUrlHostname(String csmDbConnectionUrlHostName){
    	getCsmDbHostnameField().setText(csmDbConnectionUrlHostName);
    }
    
    /**
     * This method initializes the CSM Database Connection URL Port Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbPortField() {
        if (csmDbPortField == null) {
        	csmDbPortField = new JTextField();
        	csmDbPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_SERVER_PORT"));

        	csmDbPortField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	csmDbPortField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbPortField;
    }
    
    public void setCsmDbConnectionUrlPort(String csmDbConnectionUrlPort){
    	getCsmDbPortField().setText(csmDbConnectionUrlPort);
    }
    
    
    /**
     * This method initializes the CSM Database Connection URL Port Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbSchemaField() {
        if (csmDbSchemaField == null) {
        	csmDbSchemaField = new JTextField();
        	csmDbSchemaField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_NAME"));

        	csmDbSchemaField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	csmDbSchemaField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbSchemaField;
    }
    
    public void setCsmDbConnectionUrlSchema(String setCsmDbConnectionUrlSchema){
    	getCsmDbSchemaField().setText(setCsmDbConnectionUrlSchema);
    }
    
    /**
     * This method initializes the CSM Database Username Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbUsernameField() {
        if (csmDbUsernameField == null) {
        	csmDbUsernameField = new JTextField();
        	csmDbUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_USERNAME"));
        	csmDbUsernameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	csmDbUsernameField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbUsernameField;
    }
    
    public void setCsmDbUsername(String csmDbUsername){
    	getCsmDbUsernameField().setText(csmDbUsername);
    }
    
    /**
     * This method initializes the CSM Database Password Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmDbPasswordField() {
        if (csmDbPasswordField == null) {
        	csmDbPasswordField = new JTextField();
        	csmDbPasswordField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CSM_DB_PASSWORD"));
        	csmDbPasswordField.getDocument().addDocumentListener(new DocumentListener() {
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
        	csmDbPasswordField.addFocusListener(new FocusChangeHandler());
        }
        return csmDbPasswordField;
    }
    
    public void setCsmDbPassword(String csmDbPassword){
    	getCsmDbPasswordField().setText(csmDbPassword);
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
    
    private void updateCsmDbFields(){
    	String csmDbType = (String)csmDbTypeComboBox.getSelectedItem();
    	
    	//update using hostname, port, and schema values
    	String csmDbConnectionUrlTemplate = OptionsMapManager.getDbUrlOptionsMap().get(csmDbType);
    	csmDbConnectionUrlTemplate = csmDbConnectionUrlTemplate.replace("@HOSTNAME@",csmDbHostnameField.getText());
    	csmDbConnectionUrlTemplate = csmDbConnectionUrlTemplate.replace("@PORT@",csmDbPortField.getText());
    	csmDbConnectionUrlTemplate = csmDbConnectionUrlTemplate.replace("@SCHEMA@",csmDbSchemaField.getText());
    	
    	csmDbConnectionUrlField.setText(csmDbConnectionUrlTemplate);

    }
    
    public void toggleCsmDbConnectionFields() {
    	if (getCsmUseDbConnectionSettingsCheckBox().isSelected()){
    		csmDbTypeComboBox.setEnabled(false);
    		csmUseJndiBasedConnectionCheckBox.setEnabled(false);
			csmDbJndiUrlField.setEnabled(false);

			//csmDbConnectionUrlField.setEnabled(false);
			csmDbHostnameField.setEnabled(false);
			csmDbPortField.setEnabled(false);
			csmDbSchemaField.setEnabled(false);
			csmDbUsernameField.setEnabled(false);
			csmDbPasswordField.setEnabled(false);
    	} else {
    		csmDbTypeComboBox.setEnabled(true);
    		csmUseJndiBasedConnectionCheckBox.setEnabled(true);
    		
			csmDbJndiUrlField.setEnabled(true);
			//csmDbConnectionUrlField.setEnabled(true);
			csmDbHostnameField.setEnabled(true);
			csmDbPortField.setEnabled(true);
			csmDbSchemaField.setEnabled(true);
			csmDbUsernameField.setEnabled(true);
			csmDbPasswordField.setEnabled(true);
    	}
    }
    
	/**
	 * This method initializes dbConnectionSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (csmDbConnectionSettingsPanel == null) {
			
		    //CSM DB Connection Settings Panel Label Definitions
		    JLabel csmUseDbConnectionSettingsLabel = null;
		    JLabel csmDatabaseTypeLabel = null;
		    JLabel csmUseJndiBasedConnectionLabel = null;
			
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
			//gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints11.weightx = 1.0D;  
			gridBagConstraints11.gridwidth = 2;
			
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
			//gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.weightx = 1.0D;  
			gridBagConstraints21.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridx = 0;

			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints31.gridx = 1;
			gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints31.gridy = 3;
			//gridBagConstraints31.weighty = 1.0D;
			gridBagConstraints31.weightx = 1.0D;  
			gridBagConstraints31.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridx = 0;
			gridBagConstraints40.gridwidth = 3;

			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints50.gridy = 5;
			gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints50.gridx = 0;
			gridBagConstraints50.gridwidth = 3;
		    
		    csmUseDbConnectionSettingsLabel = new JLabel();
		    csmUseDbConnectionSettingsLabel.setText("Use DB connection Settings?");
		    
		    csmDatabaseTypeLabel = new JLabel();
		    csmDatabaseTypeLabel.setText("Select Database Type:");
		    
		    csmUseJndiBasedConnectionLabel = new JLabel();
			csmUseJndiBasedConnectionLabel.setText("Use a JNDI-based Connection?");

		    csmDbConnectionSettingsPanel = new JPanel();
		    csmDbConnectionSettingsPanel.setLayout(new GridBagLayout());
		    csmDbConnectionSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Common Security Module (CSM) Database Connection Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    csmDbConnectionSettingsPanel.add(csmUseDbConnectionSettingsLabel, gridBagConstraints10);
		    csmDbConnectionSettingsPanel.add(getCsmUseDbConnectionSettingsCheckBox(), gridBagConstraints11);
		    csmDbConnectionSettingsPanel.add(csmDatabaseTypeLabel, gridBagConstraints20);
		    csmDbConnectionSettingsPanel.add(getCsmDbTypeComboBox(), gridBagConstraints21);
		    csmDbConnectionSettingsPanel.add(csmUseJndiBasedConnectionLabel, gridBagConstraints30);
		    csmDbConnectionSettingsPanel.add(getCsmUseJndiBasedConnectionCheckBox(), gridBagConstraints31);
		    csmDbConnectionSettingsPanel.add(getCsmDbConnectionJndiSettingsSubPanel(), gridBagConstraints40);
		    csmDbConnectionSettingsPanel.add(getCsmDbConnectionSettingsSubPanel(), gridBagConstraints50);
			
		    csmDbConnectionSettingsPanel.validate();
		}
		return csmDbConnectionSettingsPanel;
	}
	
	/**
	 * This method initializes dbConnectionSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCsmDbConnectionJndiSettingsSubPanel() {
		if (csmDbConnectionJndiSettingsSubPanel == null) {
			
		    //CSM DB Connection Settings Panel Label Definitions
		    JLabel csmDbJndiUrlLabel = null;
			
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
			gridBagConstraints11.weightx = 1.0D;  
			gridBagConstraints11.gridwidth = 2;

		    csmDbJndiUrlLabel = new JLabel();
		    csmDbJndiUrlLabel.setText("JNDI Name:");

		    csmDbConnectionJndiSettingsSubPanel = new JPanel();
		    csmDbConnectionJndiSettingsSubPanel.setLayout(new GridBagLayout());
		    csmDbConnectionJndiSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CSM JNDI Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
			
		    csmDbConnectionJndiSettingsSubPanel.add(csmDbJndiUrlLabel, gridBagConstraints10);
		    csmDbConnectionJndiSettingsSubPanel.add(getCsmDbJndiNameField(), gridBagConstraints11);
			
		    csmDbConnectionJndiSettingsSubPanel.validate();
		}
		return csmDbConnectionJndiSettingsSubPanel;
	}
	
	/**
	 * This method initializes dbConnectionSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCsmDbConnectionSettingsSubPanel() {
		if (csmDbConnectionSettingsSubPanel == null) {
			
		    //CSM DB Connection Settings Panel Label Definitions
		    JLabel csmDbConnectionUrlLabel = null;
		    JLabel csmDbConnectionUrlHostnameLabel = null;
		    JLabel csmDbConnectionUrlPortLabel = null;
		    JLabel csmDbConnectionUrlSchemaLabel = null;
		    JLabel csmDbUsernameLabel = null;
		    JLabel csmDbPasswordLabel = null;
			
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridx = 0;

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints11.gridwidth = 2;
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
			gridBagConstraints51.gridwidth = 2;
			gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
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

		    csmDbConnectionUrlLabel = new JLabel();
		    csmDbConnectionUrlLabel.setText("Connection URL:");
		    
		    csmDbConnectionUrlHostnameLabel = new JLabel();
		    csmDbConnectionUrlHostnameLabel.setText("Hostname:");
		    
		    csmDbConnectionUrlPortLabel = new JLabel();
		    csmDbConnectionUrlPortLabel.setText("Port:");
		    
		    csmDbConnectionUrlSchemaLabel = new JLabel();
		    csmDbConnectionUrlSchemaLabel.setText("Schema:");
		    
		    csmDbUsernameLabel = new JLabel();
		    csmDbUsernameLabel.setText("Username:");

		    csmDbPasswordLabel = new JLabel();
		    csmDbPasswordLabel.setText("Password:");

		    csmDbConnectionSettingsSubPanel = new JPanel();
		    csmDbConnectionSettingsSubPanel.setLayout(new GridBagLayout());
		    csmDbConnectionSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CSM Database Connection Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    csmDbConnectionSettingsSubPanel.add(csmDbConnectionUrlLabel, gridBagConstraints10);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbConnectionUrlField(), gridBagConstraints11);
		    csmDbConnectionSettingsSubPanel.add(csmDbConnectionUrlHostnameLabel, gridBagConstraints20);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbHostnameField(), gridBagConstraints21);
		    csmDbConnectionSettingsSubPanel.add(csmDbConnectionUrlPortLabel, gridBagConstraints30);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbPortField(), gridBagConstraints31);
		    csmDbConnectionSettingsSubPanel.add(csmDbConnectionUrlSchemaLabel, gridBagConstraints40);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbSchemaField(), gridBagConstraints41);
		    csmDbConnectionSettingsSubPanel.add(csmDbUsernameLabel, gridBagConstraints50);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbUsernameField(), gridBagConstraints51);
		    csmDbConnectionSettingsSubPanel.add(csmDbPasswordLabel, gridBagConstraints60);
		    csmDbConnectionSettingsSubPanel.add(getCsmDbPasswordField(), gridBagConstraints61);
			
		    csmDbConnectionSettingsSubPanel.validate();
		}
		return csmDbConnectionSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (csmDbConnectionSettingsReviewPanel == null) {
        	
		    //CSM DB Connection Settings Panel Label Definitions
		    JLabel csmDatabaseTypeLabel = null;
		    JLabel csmDatabaseTypeValueLabel = null;
		    JLabel csmUseJndiBasedConnectionLabel = null;
		    JLabel csmUseJndiBasedConnectionValueLabel = null;
		    JLabel csmDbJndiUrlLabel = null;
		    JLabel csmDbJndiUrlValueLabel = null;
		    JLabel csmDbConnectionUrlLabel = null;
		    JLabel csmDbConnectionUrlValueLabel = null;
		    JLabel csmDbUsernameLabel = null;
		    JLabel csmDbUsernameValueLabel = null;
		    JLabel csmDbPasswordLabel = null;
		    JLabel csmDbPasswordValueLabel = null;
        	
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
            
            GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
            gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints50.gridy = 5;
            gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints50.gridx = 0;            

            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints51.gridy = 5;
            gridBagConstraints51.gridx = 1;
            gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints51.weighty = 1.0D;
            gridBagConstraints51.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
            gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints60.gridy = 6;
            gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints60.gridx = 0;            

            GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
            gridBagConstraints61.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints61.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints61.gridy = 6;
            gridBagConstraints61.gridx = 1;
            gridBagConstraints61.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints61.weighty = 1.0D;
            gridBagConstraints61.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints70 = new GridBagConstraints();
            gridBagConstraints70.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints70.gridy = 7;
            gridBagConstraints70.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints70.gridx = 0;            

            GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
            gridBagConstraints71.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints71.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints71.gridy = 7;
            gridBagConstraints71.gridx = 1;
            gridBagConstraints71.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints71.weighty = 1.0D;
            gridBagConstraints71.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints80 = new GridBagConstraints();
            gridBagConstraints80.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints80.gridy = 8;
            gridBagConstraints80.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints80.gridx = 0;            

            GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
            gridBagConstraints81.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints81.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints81.gridy = 8;
            gridBagConstraints81.gridx = 1;
            gridBagConstraints81.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints81.weighty = 1.0D;
            gridBagConstraints81.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints90 = new GridBagConstraints();
            gridBagConstraints90.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints90.gridy = 9;
            gridBagConstraints90.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints90.gridx = 0;            

            GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
            gridBagConstraints91.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints91.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints91.gridy = 9;
            gridBagConstraints91.gridx = 1;
            gridBagConstraints91.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints91.weighty = 1.0D;
            gridBagConstraints91.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints100 = new GridBagConstraints();
            gridBagConstraints100.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints100.gridy = 10;
            gridBagConstraints100.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints100.gridx = 0;            

            GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
            gridBagConstraints101.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints101.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints101.gridy = 10;
            gridBagConstraints101.gridx = 1;
            gridBagConstraints101.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints101.weighty = 1.0D;
            gridBagConstraints101.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
            gridBagConstraints110.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints110.gridy = 11;
            gridBagConstraints110.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints110.gridx = 0;            

            GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
            gridBagConstraints111.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints111.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints111.gridy = 11;
            gridBagConstraints111.gridx = 1;
            gridBagConstraints111.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints111.weighty = 1.0D;
            gridBagConstraints111.weightx = 1.0;
             
		    csmDatabaseTypeLabel = new JLabel();
		    csmDatabaseTypeLabel.setText("Type:");
		    csmDatabaseTypeValueLabel = new JLabel();
		    csmDatabaseTypeValueLabel.setText(OptionsMapManager.getDbTypeOptionsMap().get(getCsmDbTypeComboBox().getSelectedItem().toString()));
		    
		    csmUseJndiBasedConnectionLabel = new JLabel();
		    csmUseJndiBasedConnectionLabel.setText("Use CSM JNDI Based Connection?");
		    csmUseJndiBasedConnectionValueLabel = new JLabel();
		    csmUseJndiBasedConnectionValueLabel.setText(Boolean.valueOf(getCsmUseJndiBasedConnectionCheckBox().isSelected()).toString());
		    
		    csmDbJndiUrlLabel = new JLabel();
		    csmDbJndiUrlLabel.setText("JNDI URL:");
		    csmDbJndiUrlValueLabel = new JLabel();
		    csmDbJndiUrlValueLabel.setText(getCsmDbJndiNameField().getText());
		    
		    csmDbConnectionUrlLabel = new JLabel();
		    csmDbConnectionUrlLabel.setText("Connection URL:");
		    csmDbConnectionUrlValueLabel = new JLabel();
		    csmDbConnectionUrlValueLabel.setText(getCsmDbConnectionUrlField().getText());
		    
		    csmDbUsernameLabel = new JLabel();
		    csmDbUsernameLabel.setText("Username:");
		    csmDbUsernameValueLabel = new JLabel();
		    csmDbUsernameValueLabel.setText(getCsmDbUsernameField().getText());
		    
		    csmDbPasswordLabel = new JLabel();
		    csmDbPasswordLabel.setText("Password:");
		    csmDbPasswordValueLabel = new JLabel();
		    csmDbPasswordValueLabel.setText(getCsmDbPasswordField().getText());
		    
		    csmDbConnectionSettingsReviewPanel = new JPanel();
		    csmDbConnectionSettingsReviewPanel.setLayout(new GridBagLayout());
		    csmDbConnectionSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CSM Database Connection Settings",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    csmDbConnectionSettingsReviewPanel.add(csmDatabaseTypeLabel, gridBagConstraints10);
		    csmDbConnectionSettingsReviewPanel.add(csmDatabaseTypeValueLabel, gridBagConstraints11);
		    csmDbConnectionSettingsReviewPanel.add(csmUseJndiBasedConnectionLabel, gridBagConstraints20);
		    csmDbConnectionSettingsReviewPanel.add(csmUseJndiBasedConnectionValueLabel, gridBagConstraints21);
		    csmDbConnectionSettingsReviewPanel.add(csmDbJndiUrlLabel, gridBagConstraints30);
		    csmDbConnectionSettingsReviewPanel.add(csmDbJndiUrlValueLabel, gridBagConstraints31);
		    csmDbConnectionSettingsReviewPanel.add(csmDbConnectionUrlLabel, gridBagConstraints40);
		    csmDbConnectionSettingsReviewPanel.add(csmDbConnectionUrlValueLabel, gridBagConstraints41);
		    csmDbConnectionSettingsReviewPanel.add(csmDbUsernameLabel, gridBagConstraints50);
		    csmDbConnectionSettingsReviewPanel.add(csmDbUsernameValueLabel, gridBagConstraints51);
		    csmDbConnectionSettingsReviewPanel.add(csmDbPasswordLabel, gridBagConstraints60);
		    csmDbConnectionSettingsReviewPanel.add(csmDbPasswordValueLabel, gridBagConstraints61);
            
		    csmDbConnectionSettingsReviewPanel.validate();
        //}
        return csmDbConnectionSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
        //Security setting Validation
    	if (isEnableSecuritySelected){
    		
    		//CSM DB Connection Setting Validation
    		if (!getCsmUseJndiBasedConnectionCheckBox().isSelected() && !getCsmUseDbConnectionSettingsCheckBox().isSelected()){

    			String csmDbConnectionUrlField = this.getCsmDbConnectionUrlField().getText();
    			if (!ValidationUtils.isNotBlank(csmDbConnectionUrlField)) {
    				result.add(new SimpleValidationMessage(CSM_DB_CONNECTION_URL + " must not be blank.", Severity.ERROR, CSM_DB_CONNECTION_URL));
    			}
    			
        		if (getCsmDbConnectionUrlField().getText().indexOf('<') > 1 || csmDbConnectionUrlField.indexOf('@') > 1) {
        			result.add(new SimpleValidationMessage(CSM_DB_CONNECTION_URL + " information is incomplete.  Make sure hostname, port and schema information is correct.", Severity.ERROR, CSM_DB_CONNECTION_URL));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getCsmDbHostnameField().getText())) {
        			result.add(new SimpleValidationMessage(CSM_DB_SERVER + " must not be blank.", Severity.ERROR, CSM_DB_SERVER));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getCsmDbPortField().getText())) {
        			result.add(new SimpleValidationMessage(CSM_DB_SERVER_PORT + " must not be blank.", Severity.ERROR, CSM_DB_SERVER_PORT));
        		}
        		
        		if (!ValidationUtils.isNotBlank(getCsmDbSchemaField().getText())) {
        			result.add(new SimpleValidationMessage(CSM_DB_NAME + " must not be blank.", Severity.ERROR, CSM_DB_NAME));
        		}

    			if (!ValidationUtils.isNotBlank(this.getCsmDbUsernameField().getText())) {
    				result.add(new SimpleValidationMessage(CSM_DB_USERNAME + " must not be blank.", Severity.ERROR, CSM_DB_USERNAME));
    			} 

    			if (!ValidationUtils.isNotBlank(this.getCsmDbPasswordField().getText())) {
    				result.add(new SimpleValidationMessage(CSM_DB_PASSWORD + " must not be blank.", Severity.ERROR, CSM_DB_PASSWORD));
    			} 
    		} else {
    			if (!ValidationUtils.isNotBlank(this.getCsmDbJndiNameField().getText())) {
    				result.add(new SimpleValidationMessage(CSM_JNDI_NAME + " must not be blank.", Severity.ERROR, CSM_JNDI_NAME));
    			}
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        //CSM DB
        ValidationComponentUtils.setMessageKey(getCsmUseDbConnectionSettingsCheckBox(), CSM_USE_DB_CONNECTION_SETTINGS);
        ValidationComponentUtils.setMandatory(getCsmUseDbConnectionSettingsCheckBox(), true);
        ValidationComponentUtils.setMessageKey(getCsmDbJndiNameField(), CSM_JNDI_NAME);
        ValidationComponentUtils.setMandatory(getCsmDbJndiNameField(), true);
        ValidationComponentUtils.setMessageKey(getCsmDbConnectionUrlField(), CSM_DB_CONNECTION_URL);
        ValidationComponentUtils.setMandatory(getCsmDbConnectionUrlField(), true);

        ValidationComponentUtils.setMessageKey(getCsmDbHostnameField(), CSM_DB_SERVER);
        ValidationComponentUtils.setMandatory(getCsmDbHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getCsmDbPortField(), CSM_DB_SERVER_PORT);
        ValidationComponentUtils.setMandatory(getCsmDbPortField(), true);
        ValidationComponentUtils.setMessageKey(getCsmDbSchemaField(), CSM_DB_NAME);
        ValidationComponentUtils.setMandatory(getCsmDbSchemaField(), true);
        
        ValidationComponentUtils.setMessageKey(getCsmDbUsernameField(), CSM_DB_USERNAME);
        ValidationComponentUtils.setMandatory(getCsmDbUsernameField(), true);
        ValidationComponentUtils.setMessageKey(getCsmDbPasswordField(), CSM_DB_PASSWORD);
        ValidationComponentUtils.setMandatory(getCsmDbPasswordField(), true);
        
        toggleCsmDbConnectionFields();
        parentContainer.toggleCsmDbJndiNameField();
        updateCsmDbFields();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// CSM DB Connection properties
		propsMap.put("CSM_USE_DB_CONNECTION_SETTINGS", Boolean.valueOf(csmUseDbConnectionSettingsCheckBox.isSelected()).toString() );
		propsMap.put("CSM_DB_TYPE", OptionsMapManager.getDbTypeOptionsMap().get(getCsmDbTypeComboBox().getSelectedItem().toString()));
		propsMap.put("CSM_USE_JNDI_BASED_CONNECTION", Boolean.valueOf(csmUseJndiBasedConnectionCheckBox.isSelected()).toString() );
		propsMap.put("CSM_JNDI_NAME", getCsmDbJndiNameField().getText());
		propsMap.put("CSM_DB_CONNECTION_URL", getCsmDbConnectionUrlField().getText());
		propsMap.put("CSM_DB_SERVER", getCsmDbHostnameField().getText());
		propsMap.put("CSM_DB_SERVER_PORT", getCsmDbPortField().getText());
		propsMap.put("CSM_DB_NAME", getCsmDbSchemaField().getText());
		propsMap.put("CSM_DB_USERNAME", getCsmDbUsernameField().getText());
		propsMap.put("CSM_DB_PASSWORD", getCsmDbPasswordField().getText());
    	
    	return propsMap;
    }
}
