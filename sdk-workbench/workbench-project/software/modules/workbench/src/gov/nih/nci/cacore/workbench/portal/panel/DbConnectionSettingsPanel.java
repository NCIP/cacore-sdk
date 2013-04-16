/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.FileFilters;
import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.DeployPropertiesViewer;
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
	
	int SQL_FILE_DISPLAY_LENGTH = 42;
	
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
	private static final String DB_DROP_SCHEMA = "DB Drop Schema";
	private static final String DB_SQL_FILE = "DB SQL File";
	
    //Buttons
    private JButton testConnectionButton = null;
    private JButton dbSqlFilePathButton = null;
	
	public DbConnectionSettingsPanel(DeployPropertiesViewer parentContainer,TabbedPanePropertiesValidator mainPanelValidator){
		this.parentContainer = parentContainer;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	// DB Connection Panel Definitions
	private JPanel dbSettingsPanel = null;
	private JPanel dbJndiSettingsSubPanel = null;
	private JPanel dbConnectionSettingsSubPanel = null;
	private JPanel dbCreationSettingsSubPanel = null;
	private JPanel dbConnectionSettingsReviewPanel = null;
    
	//DB Connection Settings Panel Component Definitions
    private JComboBox  dbTypeComboBox = null;
    private JCheckBox  useJndiBasedConnectionCheckBox = null;
    private JTextField dbJndiNameField = null;
    private JTextField dbUrlField = null;
    private JTextField dbHostnameField = null;
    private JTextField dbPortField = null;
    private JTextField dbSchemaField = null;
    private JTextField dbUsernameField = null;
    private JTextField dbPasswordField = null;
    
    //DB Drop Schema Sub-Panel Component Definitions
    private JCheckBox 	dbDropSchemaCheckBox=null;
    private JTextField 	dbSqlFileField=null;

    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getDbTypeComboBox() {
        if (dbTypeComboBox == null) {
        	dbTypeComboBox = new JComboBox();
        	dbTypeComboBox.setToolTipText("Select the application database type");
        	
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
                    	parentContainer.syncDbCsmDbFields();
                    	parentContainer.syncDbClmDbFields();
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	dbTypeComboBox.addFocusListener(new FocusChangeHandler());
        }
        return dbTypeComboBox;
    }
    
    public String getDbType(){
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
        	useJndiBasedConnectionCheckBox.setToolTipText("Toggle to enable/disable using a JNDI-based Connection");
        	useJndiBasedConnectionCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	useJndiBasedConnectionCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_USE_JNDI_BASED_CONNECTION")));
        	useJndiBasedConnectionCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	useJndiBasedConnectionCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	useJndiBasedConnectionCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return useJndiBasedConnectionCheckBox;
    }  
    
    public boolean isUseJndiBasedConnection() {
    	return getUseJndiBasedConnectionCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Database JNDI Name Field
     * 
     * @return javax.swing.JTextField
     */
    public JTextField getDbJndiNameField() {
        if (dbJndiNameField == null) {
        	dbJndiNameField = new JTextField();
        	dbJndiNameField.setToolTipText("Enter the JNDI name used in the server JNDI configuration file to lookup and identify the database configuration properties.");
        	dbJndiNameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_JNDI_NAME"));
        	dbJndiNameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbJndiNameField.addFocusListener(new FocusChangeHandler());
        }
        return dbJndiNameField;
    }
    
    public String getDbJndiName() {
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
        	dbUrlField.setToolTipText("This is a read-only  property composed of the DB Hostname, Port, and Schema properties");
        	dbUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_CONNECTION_URL"));
        	dbUrlField.setEnabled(false);// use hostname, port, schema fields instead
        	dbUrlField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
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
        	dbHostnameField.setToolTipText("Enter the hostname (or sitename) that uniquely identifies the database instance on the network");
        	dbHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_SERVER"));
        	
        	dbHostnameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbHostnameField.addFocusListener(new FocusChangeHandler());
        }
        return dbHostnameField;
    }
    
    public String getDbHostname(){
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
        	dbPortField.setToolTipText("Enter the port number the database instance is listening to for data requests");
        	dbPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_SERVER_PORT"));
        	dbPortField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbPortField.addFocusListener(new FocusChangeHandler());
        }
        return dbPortField;
    }
    
    public String getDbPort() {
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
        	dbSchemaField.setToolTipText("Enter the database schema name");
        	dbSchemaField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_NAME"));
        	dbSchemaField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateDbFields();
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	dbSchemaField.addFocusListener(new FocusChangeHandler());
        }
        return dbSchemaField;
    }
    
    public String getDbSchema(){
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
        	dbUsernameField.setToolTipText("Enter the username used to authenticate database requests");
        	dbUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_USERNAME"));
        	dbUsernameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
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
        	dbPasswordField.setToolTipText("Enter the password used to authenticate database requests");
        	dbPasswordField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_PASSWORD"));
        	dbPasswordField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	parentContainer.syncDbCsmDbFields();
                	parentContainer.syncDbClmDbFields();
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
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getTestConnectionButton() {
        if (testConnectionButton == null) {
        	testConnectionButton = new JButton();
        	testConnectionButton.setToolTipText("Click to test a connection to the application database using the specified properties");
        	testConnectionButton.setText("Test Connection");
        	testConnectionButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	testConnectionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.testDbConnection(OptionsMapManager.getDbTypeOptionsMap().get(getDbTypeComboBox().getSelectedItem().toString()), 
                			getDbUrl(), getDbUsername(), getDbPassword());
                    mainPanelValidator.validateInput();
                }
            });
        }

        return testConnectionButton;
    }
    
    public void setTestConnectionButtonEnabled(boolean enabled){
    	getTestConnectionButton().setEnabled(enabled);
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getDbSqlFilePathButton() {
        if (dbSqlFilePathButton == null) {
        	dbSqlFilePathButton = new JButton();
        	dbSqlFilePathButton.setToolTipText("Click to select the SQL file to use when recreating the database objects such as schema, tables, and views.");
        	dbSqlFilePathButton.setText("Browse");
        	dbSqlFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	dbSqlFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getDbSqlFileField().getText();
                        String location = ResourceManager.promptFile(previous, FileFilters.SQL_FILTER);
                        if (location != null && location.length() > 0) {
                        	getDbSqlFileField().setText(location);
                        } else {
                        	getDbSqlFileField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return dbSqlFilePathButton;
    }
        
    public void enableDbSqlFileButton(boolean enable){
    	getDbSqlFilePathButton().setEnabled(enable);
    }
    
    /**
     * This method initializes the Use JNDI Based Connection Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getDbDropSchemaCheckBox() {
        if (dbDropSchemaCheckBox == null) {
        	dbDropSchemaCheckBox = new JCheckBox();
        	dbDropSchemaCheckBox.setToolTipText("If checked, all application database objects like tables, views, and sequences will be dropped at deployment time");
        	dbDropSchemaCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	dbDropSchemaCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("DB_DROP_SCHEMA")));
        	dbDropSchemaCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	dbDropSchemaCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	dbDropSchemaCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return dbDropSchemaCheckBox;
    }  
    
    public boolean isDbDropSchemaSelected() {
    	return getDbDropSchemaCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Database JNDI Name Field
     * 
     * @return javax.swing.JTextField
     */
    public JTextField getDbSqlFileField() {
        if (dbSqlFileField == null) {
        	dbSqlFileField = new JTextField();
        	dbSqlFileField.setToolTipText("Enter the absolute path to the SQL file to be executed at the deployment time. Alternatively, use the Browse button");
        	
        	String dbType = getDbType();
        	if ("oracle".equalsIgnoreCase(dbType)){
        		dbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("db.install.create.oracle.file.list.ui")); 
        	} else if ("mysql".equalsIgnoreCase(dbType)){
        		dbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("db.install.create.mysql.file.list.ui"));
        	} else if ("postgresql".equalsIgnoreCase(dbType)){
        		dbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("db.install.create.postgresql.file.list.ui"));
        	} else {
        		dbSqlFileField.setText("");
        	}
        	
        	dbSqlFileField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dbSqlFileField.addFocusListener(new FocusChangeHandler());
        	
        	dbSqlFileField.setEditable(true); // Allow changes directly or via the DB SQL File Button 
        }
        return dbSqlFileField;
    }
    
    public String getDbSqlFile() {
    	return getDbSqlFileField().getText();
    }
    
    public String getDbSqlFilePath(){
    	return getDbSqlFileField().getText().replace('\\', '/');
    }
    
    public void setDbSqlFilePath(String filePath){
    	getDbSqlFileField().setText(filePath);
    }
    
    public String getDbSqlFileName(){
    	String dbSqlFilePath = getDbSqlFileField().getText().replace('\\', '/');
    	
    	return dbSqlFilePath.substring(dbSqlFilePath.lastIndexOf('/')+1);
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
    	String dbType = getDbType();
    	
    	//update using hostname, port, and schema values
    	String dbConnectionUrlTemplate = OptionsMapManager.getDbUrlOptionsMap().get(getDbType());
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
			gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridwidth = 3;

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

		    dbSettingsPanel = new JPanel();
		    dbSettingsPanel.setLayout(new GridBagLayout());
		    dbSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Application Database Connection Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    dbSettingsPanel.add(dbTypeLabel, gridBagConstraints10);
		    dbSettingsPanel.add(getDbTypeComboBox(), gridBagConstraints11);
		    dbSettingsPanel.add(getDbJndiSettingsSubPanel(), gridBagConstraints20);
		    dbSettingsPanel.add(getDbConnectionSettingsSubPanel(), gridBagConstraints30);
		    dbSettingsPanel.add(getDbCreationSettingsSubPanel(), gridBagConstraints40);
			
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
			
		    //DB JNDI Settings Panel Label Definitions
		    JLabel useJndiBasedConnectionLabel = null;
		    JLabel dbJndiNameLabel = null;
			
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
			gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.weightx = 1.0D;  
			gridBagConstraints21.gridwidth = 2;
		    
		    useJndiBasedConnectionLabel = new JLabel();
			useJndiBasedConnectionLabel.setText("Use a JNDI-based Connection?");

		    dbJndiNameLabel = new JLabel();
		    dbJndiNameLabel.setText("JNDI Name:");

		    dbJndiSettingsSubPanel = new JPanel();
		    dbJndiSettingsSubPanel.setLayout(new GridBagLayout());
		    dbJndiSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JNDI Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    dbJndiSettingsSubPanel.add(useJndiBasedConnectionLabel, gridBagConstraints10);
		    dbJndiSettingsSubPanel.add(getUseJndiBasedConnectionCheckBox(), gridBagConstraints11);
		    
		    dbJndiSettingsSubPanel.add(dbJndiNameLabel, gridBagConstraints20);
		    dbJndiSettingsSubPanel.add(getDbJndiNameField(), gridBagConstraints21);
			
		    dbJndiSettingsSubPanel.validate();
		}
		
		return dbJndiSettingsSubPanel;
	}
	
	/**
	 * This method initializes dbConnectionJndiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDbCreationSettingsSubPanel() {
		if (dbCreationSettingsSubPanel == null) {
			
		    //DB Creation Settings Panel Label Definitions
		    JLabel dbDropSchemaLabel = null;
		    JLabel dbSqlFileLabel = null;
			
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
            gridBagConstraints21.weighty = 1.0D;
            gridBagConstraints21.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
            gridBagConstraints22.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints22.gridy = 2;
            gridBagConstraints22.gridx = 2;
            gridBagConstraints22.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints22.gridwidth = 1;
		    
			dbDropSchemaLabel = new JLabel();
			dbDropSchemaLabel.setText("Drop Database Schema?");

			dbSqlFileLabel = new JLabel();
			dbSqlFileLabel.setText("Database SQL File:");

			dbCreationSettingsSubPanel = new JPanel();
		    dbCreationSettingsSubPanel.setLayout(new GridBagLayout());
		    dbCreationSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Re-create Database Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    dbCreationSettingsSubPanel.add(dbDropSchemaLabel, gridBagConstraints10);
		    dbCreationSettingsSubPanel.add(getDbDropSchemaCheckBox(), gridBagConstraints11);
		    
		    dbCreationSettingsSubPanel.add(dbSqlFileLabel, gridBagConstraints20);
		    dbCreationSettingsSubPanel.add(getDbSqlFileField(), gridBagConstraints21);
		    dbCreationSettingsSubPanel.add(getDbSqlFilePathButton(), gridBagConstraints22);
			
		    dbCreationSettingsSubPanel.validate();
		}
		
		return dbCreationSettingsSubPanel;
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
		    JLabel dbHostnameLabel = null;
		    JLabel dbPortLabel = null;
		    JLabel dbSchemaLabel = null;
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
			//gridBagConstraints70.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints70.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints70.gridy = 7;
			gridBagConstraints70.gridx = 0;
			gridBagConstraints70.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints70.gridwidth = 3;
			//gridBagConstraints70.weighty = 1.0D;
			gridBagConstraints70.weightx = 1.0D;  

		    dbConnectionUrlLabel = new JLabel();
		    dbConnectionUrlLabel.setText("Connection URL:");
		    
		    dbHostnameLabel = new JLabel();
		    dbHostnameLabel.setText("Hostname:");
		    
		    dbPortLabel = new JLabel();
		    dbPortLabel.setText("Port:");
		    
		    dbSchemaLabel = new JLabel();
		    dbSchemaLabel.setText("Schema:");
		    
		    dbUsernameLabel = new JLabel();
		    dbUsernameLabel.setText("Username:");

		    dbPasswordLabel = new JLabel();
		    dbPasswordLabel.setText("Password:");

		    dbConnectionSettingsSubPanel = new JPanel();
		    dbConnectionSettingsSubPanel.setLayout(new GridBagLayout());
		    dbConnectionSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Database Connection Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    dbConnectionSettingsSubPanel.add(dbConnectionUrlLabel, gridBagConstraints10);
		    dbConnectionSettingsSubPanel.add(getDbUrlField(), gridBagConstraints11);
		    
		    dbConnectionSettingsSubPanel.add(dbHostnameLabel, gridBagConstraints20);
		    dbConnectionSettingsSubPanel.add(getDbHostnameField(), gridBagConstraints21);
		    dbConnectionSettingsSubPanel.add(dbPortLabel, gridBagConstraints30);
		    dbConnectionSettingsSubPanel.add(getDbPortField(), gridBagConstraints31);
		    dbConnectionSettingsSubPanel.add(dbSchemaLabel, gridBagConstraints40);
		    dbConnectionSettingsSubPanel.add(getDbSchemaField(), gridBagConstraints41);
		    
		    dbConnectionSettingsSubPanel.add(dbUsernameLabel, gridBagConstraints50);
		    dbConnectionSettingsSubPanel.add(getDbUsernameField(), gridBagConstraints51);
		    dbConnectionSettingsSubPanel.add(dbPasswordLabel, gridBagConstraints60);
		    dbConnectionSettingsSubPanel.add(getDbPasswordField(), gridBagConstraints61);
		    
		    dbConnectionSettingsSubPanel.add(getTestConnectionButton(), gridBagConstraints70);
			
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
		    JLabel dbDropSchemaLabel = null;
		    JLabel dbDropSchemaValueLabel = null;
		    JLabel dbSqlFileLabel = null;
		    JLabel dbSqlFileValueLabel = null;
        	
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
                
		    useJndiBasedConnectionLabel = new JLabel();
		    useJndiBasedConnectionLabel.setText("Use JNDI Based Connection?");
		    useJndiBasedConnectionValueLabel = new JLabel();
		    useJndiBasedConnectionValueLabel.setText(Utils.convertToYesNo(getUseJndiBasedConnectionCheckBox()));
		    
		    dbJndiUrlLabel = new JLabel();
		    dbJndiUrlLabel.setText("JNDI Name:");
		    dbJndiUrlValueLabel = new JLabel();
		    dbJndiUrlValueLabel.setText(getDbJndiNameField().getText());
		    
		    dbConnectionUrlLabel = new JLabel();
		    dbConnectionUrlLabel.setText("Connection URL:");
		    dbConnectionUrlValueLabel = new JLabel();
		    dbConnectionUrlValueLabel.setText(getDbUrlField().getText());
		    
		    dbUsernameLabel = new JLabel();
		    dbUsernameLabel.setText("Username:");
		    dbUsernameValueLabel = new JLabel();
		    dbUsernameValueLabel.setText(getDbUsernameField().getText());
		    
		    dbPasswordLabel = new JLabel();
		    dbPasswordLabel.setText("Password:");
		    dbPasswordValueLabel = new JLabel();
		    dbPasswordValueLabel.setText(getDbPasswordField().getText());
		    
		    dbTypeLabel = new JLabel();
		    dbTypeLabel.setText("Type:");
		    dbTypeValueLabel = new JLabel();
		    dbTypeValueLabel.setText(getDbTypeComboBox().getSelectedItem().toString());
		    
		    dbDropSchemaLabel = new JLabel();
		    dbDropSchemaLabel.setText("Drop Database Schema?");
		    dbDropSchemaValueLabel = new JLabel();
		    dbDropSchemaValueLabel.setText(Utils.convertToYesNo(getDbDropSchemaCheckBox()));
            
		    dbConnectionSettingsReviewPanel = new JPanel();
		    dbConnectionSettingsReviewPanel.setLayout(new GridBagLayout());
		    dbConnectionSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Database Connection Settings",
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
		    dbConnectionSettingsReviewPanel.add(dbDropSchemaLabel, gridBagConstraints70);
		    dbConnectionSettingsReviewPanel.add(dbDropSchemaValueLabel, gridBagConstraints71);
		    
		    if (ValidationUtils.isNotBlank(this.getDbSqlFileField().getText())){
			    dbSqlFileLabel = new JLabel();
			    dbSqlFileLabel.setText("Database SQL File:");
			    
			    dbSqlFileValueLabel = new JLabel();
			    String dbSqlFile = getDbSqlFileField().getText();
			    if (dbSqlFile != null && dbSqlFile.length() > SQL_FILE_DISPLAY_LENGTH)
			    	dbSqlFile = "..."+dbSqlFile.substring(dbSqlFile.length() - SQL_FILE_DISPLAY_LENGTH);
			    dbSqlFileValueLabel.setText(dbSqlFile);
		    	
			    dbConnectionSettingsReviewPanel.add(dbSqlFileLabel, gridBagConstraints80);
			    dbConnectionSettingsReviewPanel.add(dbSqlFileValueLabel, gridBagConstraints81);
		    }
            
		    dbConnectionSettingsReviewPanel.validate();
        //}
        return dbConnectionSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = validateDbConnectionInput();
    	
    	if (getUseJndiBasedConnectionCheckBox().isSelected()) {
    		if (ValidationUtils.isBlank(this.getDbJndiNameField().getText())) {
    			result.add(new SimpleValidationMessage(DB_JNDI_NAME + " must not be blank.", Severity.ERROR, DB_JNDI_NAME));
    		}
    	}
    	
    	if (getDbDropSchemaCheckBox().isSelected()){
    		if (ValidationUtils.isBlank(this.getDbSqlFileField().getText())) {
    			result.add(new SimpleValidationMessage(DB_SQL_FILE + " must not be blank when "+DB_DROP_SCHEMA+" is selected.", Severity.ERROR, DB_SQL_FILE));
    		}
    	}
    	
		if (ValidationUtils.isNotBlank(this.getDbSqlFileField().getText())) {
    		File file = new File(this.getDbSqlFileField().getText());
    		if(!file.exists()){
    			result.add(new SimpleValidationMessage(DB_SQL_FILE + " does not exist.  Please select or enter a valid absolute path to the file.", Severity.ERROR, DB_SQL_FILE));
    		}
    		
    		if (!this.getDbSqlFileField().getText().endsWith("sql")){
    			result.add(new SimpleValidationMessage(DB_SQL_FILE + " must refer to a SQL (*.sql) file.", Severity.ERROR, DB_SQL_FILE));
    		}
    	}
    	
    	return result;
    }
    
    public ValidationResult validateDbConnectionInput() {

    	ValidationResult result = new ValidationResult();
    	
       	//DB Connection Settings Validation
		if (ValidationUtils.isBlank(getDbHostnameField().getText())) {
			result.add(new SimpleValidationMessage(DB_SERVER + " must not be blank.", Severity.ERROR, DB_SERVER));
		}
		
		String dbPort = getDbPortField().getText();
		if (ValidationUtils.isBlank(dbPort)) {
			result.add(new SimpleValidationMessage(DB_SERVER_PORT + " must not be blank.", Severity.ERROR, DB_SERVER_PORT));
		}
		
		if (!ValidationUtils.isNumeric(dbPort)){
			result.add(new SimpleValidationMessage(DB_SERVER_PORT + " must be numeric.", Severity.ERROR, DB_SERVER_PORT));
		}
		
		if (ValidationUtils.isBlank(getDbSchemaField().getText())) {
			result.add(new SimpleValidationMessage(DB_NAME + " must not be blank.", Severity.ERROR, DB_NAME));
		}

		if (ValidationUtils.isBlank(this.getDbUsernameField().getText())) {
			result.add(new SimpleValidationMessage(DB_USERNAME + " must not be blank.", Severity.ERROR, DB_USERNAME));
		} 

		if (ValidationUtils.isBlank(this.getDbPasswordField().getText())) {
			result.add(new SimpleValidationMessage(DB_PASSWORD + " must not be blank.", Severity.ERROR, DB_PASSWORD));
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
        ValidationComponentUtils.setMessageKey(getDbDropSchemaCheckBox(), DB_DROP_SCHEMA);
        ValidationComponentUtils.setMandatory(getDbDropSchemaCheckBox(), true);
        ValidationComponentUtils.setMessageKey(getDbSqlFileField(), DB_SQL_FILE);
        ValidationComponentUtils.setMandatory(getDbSqlFileField(), true);
        
        updateDbFields();
        parentContainer.toggleTestConnectionButton();
        parentContainer.toggleDbJndiNameField();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	//DB Connection Settings
		propsMap.put("DB_TYPE", OptionsMapManager.getDbTypeOptionsMap().get(getDbTypeComboBox().getSelectedItem().toString()));
		propsMap.put("DB_USE_JNDI_BASED_CONNECTION", Boolean.valueOf(getUseJndiBasedConnectionCheckBox().isSelected()).toString() );
		propsMap.put("DB_JNDI_NAME", getDbJndiNameField().getText());
		propsMap.put("DB_CONNECTION_URL", getDbUrlField().getText());
		propsMap.put("DB_SERVER", getDbHostnameField().getText());
		propsMap.put("DB_SERVER_PORT", getDbPortField().getText());
		propsMap.put("DB_NAME", getDbSchemaField().getText());
		propsMap.put("DB_USERNAME", getDbUsernameField().getText());
		propsMap.put("DB_PASSWORD", getDbPasswordField().getText());
		
		propsMap.put("DB_DROP_SCHEMA", Boolean.valueOf(getDbDropSchemaCheckBox().isSelected()).toString() );
		
    	String dbType = getDbType();
    	if ("oracle".equalsIgnoreCase(dbType)){
    		propsMap.put("db.install.create.oracle.file.list", getDbSqlFileList()); 
    		propsMap.put("db.install.create.oracle.file.list.ui", getDbSqlFileField().getText().replace('\\', '/')); 
    	} else if ("mysql".equalsIgnoreCase(dbType)){
    		propsMap.put("db.install.create.mysql.file.list", getDbSqlFileList());
    		propsMap.put("db.install.create.mysql.file.list.ui", getDbSqlFileField().getText().replace('\\', '/'));
    	} else if ("postgresql".equalsIgnoreCase(dbType)){
    		propsMap.put("db.install.create.postgresql.file.list", getDbSqlFileList());
    		propsMap.put("db.install.create.postgresql.file.list.ui", getDbSqlFileField().getText().replace('\\', '/'));
    	}
    	
    	return propsMap;
    }
    
    private String getDbSqlFileList(){
    	StringBuffer sb = new StringBuffer();

    	String dbSqlFileName = getDbSqlFileName();
    	if (dbSqlFileName != null && dbSqlFileName.length() > 0) {
    		sb.append(dbSqlFileName);

    		if (parentContainer.isAppDbAndCsmSchemaSame() && parentContainer.isCsmEnabled()){
    			String csmDbSqlFileName = parentContainer.getCsmDbSqlFileName();
    			if (csmDbSqlFileName != null && csmDbSqlFileName.length() > 0) {
    				sb.append(",").append(csmDbSqlFileName);
    			}
    		}
    		
    		if (parentContainer.isAppDbAndClmSchemaSame() && parentContainer.isClmEnabled()){
    			String clmDbSqlFileName = parentContainer.getClmDbSqlFileName();
    			if (clmDbSqlFileName != null && clmDbSqlFileName.length() > 0) {
    				sb.append(",").append(clmDbSqlFileName);
    			}
    		}

    	}

    	return sb.toString();
    }
}
