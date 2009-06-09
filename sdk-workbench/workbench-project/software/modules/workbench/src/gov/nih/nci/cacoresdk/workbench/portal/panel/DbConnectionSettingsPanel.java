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

public final class DbConnectionSettingsPanel implements Panel, PanelValidator {
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	private static final String DB_TYPE = "DB Type";
	private static final String DB_JNDI_NAME = "DB JNDI Name";
	private static final String DB_SERVER = "DB Hostname";
	private static final String DB_SERVER_PORT = "DB Port";
	private static final String DB_NAME = "DB Schema";
	private static final String DB_USERNAME = "DB Username";
	private static final String DB_PASSWORD = "DB Password";
	
	public DbConnectionSettingsPanel(DeployPropertiesViewer parentContainer,TabbedPanePropertiesValidator mainPanelValidator){
		this.parentContainer = parentContainer;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	// DB Connection Panel Definitions
	private JPanel dbSettingsPanel = null;
	private JPanel dbJndiSettingsSubPanel = null;
	private JPanel dbConnectionSettingsSubPanel = null;
	private JPanel dbConnectionSettingsReviewPanel = null;
    
	//DB Connection Settings Panel Component Definitions
    private JComboBox dbTypeComboBox = null;
    private JCheckBox  useJndiBasedConnectionCheckBox = null;
    private JTextField dbJndiNameField = null;
    private JTextField dbUrlField = null;
    private JTextField dbHostnameField = null;
    private JTextField dbPortField = null;
    private JTextField dbSchemaField = null;
    private JTextField dbUsernameField = null;
    private JTextField dbPasswordField = null;

    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getDbTypeComboBox() {
        if (dbTypeComboBox == null) {
        	dbTypeComboBox = new JComboBox();
        	
        	Map<String,String> dbTypeOptionsMap = OptionsMapManager.getDbTypeOptionsMap();
        	if (dbTypeOptionsMap!=null){
            	Iterator<String> iter = dbTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		dbTypeComboBox.addItem((String)iter.next());
            	}
            	
            	String dbTypeValue = parentContainer.getPropertiesManager().getDeployPropertyValue("DB_TYPE");
            	String dbTypeKey = OptionsMapManager.getValueToKeyMap().get(dbTypeValue);
            	
            	if (dbTypeKey != null && dbTypeKey.length()>0)
            		dbTypeComboBox.setSelectedItem(dbTypeKey);
        	}
        	  		
        	dbTypeComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                    	updateDbFields();
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	dbTypeComboBox.addFocusListener(new FocusChangeHandler());
        }
        return dbTypeComboBox;
    }
    
    public String getDatabaseType(){
    	return getDbTypeComboBox().getSelectedItem().toString();
    }
    
    
    /**
     * This method initializes the Use JNDI Based Connection Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getUseJndiBasedConnectionCheckBox() {
        if (useJndiBasedConnectionCheckBox == null) {
        	useJndiBasedConnectionCheckBox = new JCheckBox();
        	useJndiBasedConnectionCheckBox.setToolTipText("Use a JNDI-based Connection?");
        	useJndiBasedConnectionCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	useJndiBasedConnectionCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_USE_JNDI_BASED_CONNECTION")));
        	useJndiBasedConnectionCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	useJndiBasedConnectionCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	useJndiBasedConnectionCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return useJndiBasedConnectionCheckBox;
    }  
    
    public boolean getUseJndiBasedConnection() {
    	return getUseJndiBasedConnectionCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Database JNDI Name Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbJndiNameField() {
        if (dbJndiNameField == null) {
        	dbJndiNameField = new JTextField();
        	dbJndiNameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_JNDI_NAME"));
        	dbJndiNameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dbJndiNameField.addFocusListener(new FocusChangeHandler());
        }
        return dbJndiNameField;
    }
    
    public String getDbJndiUrl() {
    	return getDbJndiNameField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbUrlField() {
        if (dbUrlField == null) {
        	dbUrlField = new JTextField();
        	dbUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_CONNECTION_URL"));
        	dbUrlField.setEnabled(false);// use hostname, port, schema fields instead
        	dbUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dbUrlField.addFocusListener(new FocusChangeHandler());
        }
        return dbUrlField;
    }
    
    public String getDbUrl(){
    	return getDbUrlField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Hostname Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbHostnameField() {
        if (dbHostnameField == null) {
        	dbHostnameField = new JTextField();
        	dbHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_SERVER"));
        	dbHostnameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbHostnameField.addFocusListener(new FocusChangeHandler());
        }
        return dbHostnameField;
    }
    
    public String getDbConnectionUrlHostname(){
    	return getDbHostnameField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Port Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbPortField() {
        if (dbPortField == null) {
        	dbPortField = new JTextField();
        	dbPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_SERVER_PORT"));
        	dbPortField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbPortField.addFocusListener(new FocusChangeHandler());
        }
        return dbPortField;
    }
    
    public String getDbConnectionUrlPort() {
    	return getDbPortField().getText();
    }
    
    /**
     * This method initializes the Database Connection URL Schema Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbSchemaField() {
        if (dbSchemaField == null) {
        	dbSchemaField = new JTextField();
        	dbSchemaField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_NAME"));
        	dbSchemaField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbSchemaField.addFocusListener(new FocusChangeHandler());
        }
        return dbSchemaField;
    }
    
    public String getDbConnectionUrlSchema(){
    	return getDbSchemaField().getText();
    }
    
    /**
     * This method initializes the Database Username Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbUsernameField() {
        if (dbUsernameField == null) {
        	dbUsernameField = new JTextField();
        	dbUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_USERNAME"));
        	dbUsernameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dbUsernameField.addFocusListener(new FocusChangeHandler());
        }
        return dbUsernameField;
    }
    
    public String getDbUsername(){
    	return getDbUsernameField().getText();
    }
    
    /**
     * This method initializes the Database Password Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDbPasswordField() {
        if (dbPasswordField == null) {
        	dbPasswordField = new JTextField();
        	dbPasswordField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_PASSWORD"));
        	dbPasswordField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dbPasswordField.addFocusListener(new FocusChangeHandler());
        }
        return dbPasswordField;
    }
    
    public String getDbPassword(){
    	return getDbPasswordField().getText();
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
    
    private void updateDbFields(){
    	String dbType = (String)dbTypeComboBox.getSelectedItem();
    	
    	//update using hostname, port, and schema values
    	String dbConnectionUrlTemplate = OptionsMapManager.getDbUrlOptionsMap().get(dbType);
    	dbConnectionUrlTemplate = dbConnectionUrlTemplate.replace("@HOSTNAME@",dbHostnameField.getText());
    	dbConnectionUrlTemplate = dbConnectionUrlTemplate.replace("@PORT@",dbPortField.getText());
    	dbConnectionUrlTemplate = dbConnectionUrlTemplate.replace("@SCHEMA@",dbSchemaField.getText());
    	
    	dbUrlField.setText(dbConnectionUrlTemplate);
    }
    
	/**
	 * This method initializes dbSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (dbSettingsPanel == null) {
			
		    //DB Connection Settings Panel Label Definitions
		    JLabel useJndiBasedConnectionLabel = null;
		    JLabel dbTypeLabel = null;
			
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
			gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.gridwidth = 3;

			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridx = 0;
			gridBagConstraints40.gridwidth = 3;

		    dbTypeLabel = new JLabel();
		    dbTypeLabel.setText("Select Database Type:");
		    
		    useJndiBasedConnectionLabel = new JLabel();
			useJndiBasedConnectionLabel.setText("Use a JNDI-based Connection?");

		    dbSettingsPanel = new JPanel();
		    dbSettingsPanel.setLayout(new GridBagLayout());
		    dbSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Database Connection Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    dbSettingsPanel.add(dbTypeLabel, gridBagConstraints10);
		    dbSettingsPanel.add(getDbTypeComboBox(), gridBagConstraints11);
		    dbSettingsPanel.add(useJndiBasedConnectionLabel, gridBagConstraints20);
		    dbSettingsPanel.add(getUseJndiBasedConnectionCheckBox(), gridBagConstraints21);
		    dbSettingsPanel.add(getDbJndiSettingsSubPanel(), gridBagConstraints30);
		    dbSettingsPanel.add(getDbConnectionSettingsSubPanel(), gridBagConstraints40);
			
		    dbSettingsPanel.validate();
		}
		return dbSettingsPanel;
	}
	
	/**
	 * This method initializes dbConnectionJndiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbJndiSettingsSubPanel() {
		if (dbJndiSettingsSubPanel == null) {
			
		    //DB Connection Settings Panel Label Definitions
		    JLabel dbJndiUrlLabel = null;
			
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

		    dbJndiUrlLabel = new JLabel();
		    dbJndiUrlLabel.setText("DB JNDI Name:");

		    dbJndiSettingsSubPanel = new JPanel();
		    dbJndiSettingsSubPanel.setLayout(new GridBagLayout());
		    dbJndiSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JNDI Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
			
		    dbJndiSettingsSubPanel.add(dbJndiUrlLabel, gridBagConstraints10);
		    dbJndiSettingsSubPanel.add(getDbJndiNameField(), gridBagConstraints11);

			
		    dbJndiSettingsSubPanel.validate();
		}
		return dbJndiSettingsSubPanel;
	}
	

	/**
	 * This method initializes dbSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbConnectionSettingsSubPanel() {
		if (dbConnectionSettingsSubPanel == null) {
			
		    //DB Connection Settings Panel Label Definitions
		    JLabel dbConnectionUrlLabel = null;
		    JLabel dbConnectionUrlHostnameLabel = null;
		    JLabel dbConnectionUrlPortLabel = null;
		    JLabel dbConnectionUrlSchemaLabel = null;
		    JLabel dbUsernameLabel = null;
		    JLabel dbPasswordLabel = null;
			
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

		    dbConnectionUrlLabel = new JLabel();
		    dbConnectionUrlLabel.setText("DB Connection URL:");
		    
		    dbConnectionUrlHostnameLabel = new JLabel();
		    dbConnectionUrlHostnameLabel.setText("DB Hostname:");
		    
		    dbConnectionUrlPortLabel = new JLabel();
		    dbConnectionUrlPortLabel.setText("DB Port:");
		    
		    dbConnectionUrlSchemaLabel = new JLabel();
		    dbConnectionUrlSchemaLabel.setText("DB Schema:");
		    
		    dbUsernameLabel = new JLabel();
		    dbUsernameLabel.setText("DB Username:");

		    dbPasswordLabel = new JLabel();
		    dbPasswordLabel.setText("DB Password:");

		    dbConnectionSettingsSubPanel = new JPanel();
		    dbConnectionSettingsSubPanel.setLayout(new GridBagLayout());
		    dbConnectionSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Database Connection Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    dbConnectionSettingsSubPanel.add(dbConnectionUrlLabel, gridBagConstraints10);
		    dbConnectionSettingsSubPanel.add(getDbUrlField(), gridBagConstraints11);
		    
		    dbConnectionSettingsSubPanel.add(dbConnectionUrlHostnameLabel, gridBagConstraints20);
		    dbConnectionSettingsSubPanel.add(getDbHostnameField(), gridBagConstraints21);
		    dbConnectionSettingsSubPanel.add(dbConnectionUrlPortLabel, gridBagConstraints30);
		    dbConnectionSettingsSubPanel.add(getDbPortField(), gridBagConstraints31);
		    dbConnectionSettingsSubPanel.add(dbConnectionUrlSchemaLabel, gridBagConstraints40);
		    dbConnectionSettingsSubPanel.add(getDbSchemaField(), gridBagConstraints41);
		    
		    dbConnectionSettingsSubPanel.add(dbUsernameLabel, gridBagConstraints50);
		    dbConnectionSettingsSubPanel.add(getDbUsernameField(), gridBagConstraints51);
		    dbConnectionSettingsSubPanel.add(dbPasswordLabel, gridBagConstraints60);
		    dbConnectionSettingsSubPanel.add(getDbPasswordField(), gridBagConstraints61);
			
		    dbConnectionSettingsSubPanel.validate();
		}
		return dbConnectionSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (dbConnectionSettingsReviewPanel == null) {
        	
		    //DB Connection Settings Panel Label Definitions
		    JLabel useJndiBasedConnectionLabel = null;
		    JLabel useJndiBasedConnectionValueLabel = null;
		    JLabel dbJndiUrlLabel = null;
		    JLabel dbJndiUrlValueLabel = null;
		    JLabel dbConnectionUrlLabel = null;
		    JLabel dbConnectionUrlValueLabel = null;
		    JLabel dbUsernameLabel = null;
		    JLabel dbUsernameValueLabel = null;
		    JLabel dbPasswordLabel = null;
		    JLabel dbPasswordValueLabel = null;
		    JLabel dbTypeLabel = null;
		    JLabel dbTypeValueLabel = null;
        	
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
                
		    useJndiBasedConnectionLabel = new JLabel();
		    useJndiBasedConnectionLabel.setText("Use JNDI Based Connection?");
		    useJndiBasedConnectionValueLabel = new JLabel();
		    useJndiBasedConnectionValueLabel.setText(Boolean.valueOf(getUseJndiBasedConnectionCheckBox().isSelected()).toString());
		    
		    dbJndiUrlLabel = new JLabel();
		    dbJndiUrlLabel.setText("Db JNDI URL:");
		    dbJndiUrlValueLabel = new JLabel();
		    dbJndiUrlValueLabel.setText(getDbJndiNameField().getText());
		    
		    dbConnectionUrlLabel = new JLabel();
		    dbConnectionUrlLabel.setText("DB Connection URL:");
		    dbConnectionUrlValueLabel = new JLabel();
		    dbConnectionUrlValueLabel.setText(getDbUrlField().getText());
		    
		    dbUsernameLabel = new JLabel();
		    dbUsernameLabel.setText("DB Username:");
		    dbUsernameValueLabel = new JLabel();
		    dbUsernameValueLabel.setText(getDbUsernameField().getText());
		    
		    dbPasswordLabel = new JLabel();
		    dbPasswordLabel.setText("DB Password:");
		    dbPasswordValueLabel = new JLabel();
		    dbPasswordValueLabel.setText(getDbPasswordField().getText());
		    
		    dbTypeLabel = new JLabel();
		    dbTypeLabel.setText("DB Type:");
		    dbTypeValueLabel = new JLabel();
		    dbTypeValueLabel.setText(getDbTypeComboBox().getSelectedItem().toString());
            
		    dbConnectionSettingsReviewPanel = new JPanel();
		    dbConnectionSettingsReviewPanel.setLayout(new GridBagLayout());
		    dbConnectionSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DB Connection Settings",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    dbConnectionSettingsReviewPanel.add(dbTypeLabel, gridBagConstraints10);
		    dbConnectionSettingsReviewPanel.add(dbTypeValueLabel, gridBagConstraints11);
		    dbConnectionSettingsReviewPanel.add(useJndiBasedConnectionLabel, gridBagConstraints20);
		    dbConnectionSettingsReviewPanel.add(useJndiBasedConnectionValueLabel, gridBagConstraints21);
		    dbConnectionSettingsReviewPanel.add(dbJndiUrlLabel, gridBagConstraints30);
		    dbConnectionSettingsReviewPanel.add(dbJndiUrlValueLabel, gridBagConstraints31);
		    dbConnectionSettingsReviewPanel.add(dbConnectionUrlLabel, gridBagConstraints40);
		    dbConnectionSettingsReviewPanel.add(dbConnectionUrlValueLabel, gridBagConstraints41);
		    dbConnectionSettingsReviewPanel.add(dbUsernameLabel, gridBagConstraints50);
		    dbConnectionSettingsReviewPanel.add(dbUsernameValueLabel, gridBagConstraints51);
		    dbConnectionSettingsReviewPanel.add(dbPasswordLabel, gridBagConstraints60);
		    dbConnectionSettingsReviewPanel.add(dbPasswordValueLabel, gridBagConstraints61);
            
		    dbConnectionSettingsReviewPanel.validate();
        //}
        return dbConnectionSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
       	//DB Connection Settings Validation
    	if (!getUseJndiBasedConnectionCheckBox().isSelected()){
    		
    		if (!ValidationUtils.isNotBlank(getDbHostnameField().getText())) {
    			result.add(new SimpleValidationMessage(DB_SERVER + " must not be blank.", Severity.ERROR, DB_SERVER));
    		}
    		
    		if (!ValidationUtils.isNotBlank(getDbPortField().getText())) {
    			result.add(new SimpleValidationMessage(DB_SERVER_PORT + " must not be blank.", Severity.ERROR, DB_SERVER_PORT));
    		}
    		
    		if (!ValidationUtils.isNotBlank(getDbSchemaField().getText())) {
    			result.add(new SimpleValidationMessage(DB_NAME + " must not be blank.", Severity.ERROR, DB_NAME));
    		}

    		if (!ValidationUtils.isNotBlank(this.getDbUsernameField().getText())) {
    			result.add(new SimpleValidationMessage(DB_USERNAME + " must not be blank.", Severity.ERROR, DB_USERNAME));
    		} 

    		if (!ValidationUtils.isNotBlank(this.getDbPasswordField().getText())) {
    			result.add(new SimpleValidationMessage(DB_PASSWORD + " must not be blank.", Severity.ERROR, DB_PASSWORD));
    		} 
    	} else {
    		if (!ValidationUtils.isNotBlank(this.getDbJndiNameField().getText())) {
    			result.add(new SimpleValidationMessage(DB_JNDI_NAME + " must not be blank.", Severity.ERROR, DB_JNDI_NAME));
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        //DB Connection
        ValidationComponentUtils.setMessageKey(getDbTypeComboBox(), DB_TYPE);
        ValidationComponentUtils.setMandatory(getDbTypeComboBox(), true);
        ValidationComponentUtils.setMessageKey(getDbJndiNameField(), DB_JNDI_NAME);
        ValidationComponentUtils.setMandatory(getDbJndiNameField(), true);
        ValidationComponentUtils.setMessageKey(getDbHostnameField(), DB_SERVER);
        ValidationComponentUtils.setMandatory(getDbHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getDbPortField(), DB_SERVER_PORT);
        ValidationComponentUtils.setMandatory(getDbPortField(), true);
        ValidationComponentUtils.setMessageKey(getDbSchemaField(), DB_NAME);
        ValidationComponentUtils.setMandatory(getDbSchemaField(), true);
        ValidationComponentUtils.setMessageKey(getDbUsernameField(), DB_USERNAME);
        ValidationComponentUtils.setMandatory(getDbUsernameField(), true);
        ValidationComponentUtils.setMessageKey(getDbPasswordField(), DB_PASSWORD);
        ValidationComponentUtils.setMandatory(getDbPasswordField(), true);
        
        updateDbFields();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	//DB Connection Settings
		propsMap.put("DB_TYPE", OptionsMapManager.getDbTypeOptionsMap().get(getDbTypeComboBox().getSelectedItem().toString()));
		propsMap.put("DB_USE_JNDI_BASED_CONNECTION", Boolean.valueOf(useJndiBasedConnectionCheckBox.isSelected()).toString() );
		propsMap.put("DB_JNDI_NAME", getDbJndiNameField().getText());
		propsMap.put("DB_CONNECTION_URL", getDbUrlField().getText());
		propsMap.put("DB_SERVER", getDbHostnameField().getText());
		propsMap.put("DB_SERVER_PORT", getDbPortField().getText());
		propsMap.put("DB_NAME", getDbSchemaField().getText());
		propsMap.put("DB_USERNAME", getDbUsernameField().getText());
		propsMap.put("DB_PASSWORD", getDbPasswordField().getText());
    	
    	return propsMap;
    }
}
