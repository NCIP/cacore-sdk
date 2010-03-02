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

import org.apache.log4j.Logger;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class ClmSettingsPanel implements Panel, PanelValidator {

	private static final Logger log = Logger.getLogger(ClmSettingsPanel.class);	
	
	int SQL_FILE_DISPLAY_LENGTH = 42;
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	private static final String CLM_USE_DB_CONNECTION_SETTINGS = "Use DB Connection Settings";	
	private static final String CLM_DB_TYPE = "CLM DB Type";
	private static final String CLM_DB_CONNECTION_URL = "CLM DB Connection URL";
	private static final String CLM_DB_SERVER = "CLM DB Hostname";
	private static final String CLM_DB_SERVER_PORT = "CLM DB Port";
	private static final String CLM_DB_NAME = "CLM DB Schema";
	private static final String CLM_DB_USERNAME = "CLM DB Username";
	private static final String CLM_DB_PASSWORD = "CLM DB Password";
	private static final String CLM_DB_DROP_SCHEMA = "CLM DB Drop Schema";
	private static final String CLM_DB_SQL_FILE = "CLM DB SQL File";
	
	public ClmSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator) {
		this.parentContainer = parentContainer;
		this.mainPanelValidator = mainPanelValidator;
	}
	
	// Common Logging (CLM) 
	private JPanel clmSettingsPanel = null;
	private JPanel clmSettingsSubPanel = null;
	private JPanel clmDbCreationSettingsSubPanel = null;
	private JPanel clmSettingsReviewPanel = null;
    
    //CLM (Logging) Settings Panel Component Definitions
    private JCheckBox  clmUseDbConnectionSettingsCheckBox = null;
    private JComboBox  clmDbTypeComboBox = null;
    private JTextField clmDbConnectionUrlField = null;
    private JTextField clmDbHostnameField = null;
    private JTextField clmDbPortField = null;
    private JTextField clmDbSchemaField = null;
    private JTextField clmDbUsernameField = null;
    private JTextField clmDbPasswordField = null;
    
    //DB Re-create DB Sub-Panel Component Definitions
    private JCheckBox  clmDbDropSchemaCheckBox=null;
    private JTextField clmDbSqlFileField=null;
    
    //Buttons
    private JButton testConnectionButton = null;
    private JButton clmDbSqlFilePathButton = null;
    
    /**
     * This method initializes the CLM 'Use DB Connection Settings?' Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getClmUseDbConnectionSettingsCheckBox() {
        if (clmUseDbConnectionSettingsCheckBox == null) {
        	clmUseDbConnectionSettingsCheckBox = new JCheckBox();
        	clmUseDbConnectionSettingsCheckBox.setToolTipText("Toggle to enable/disable using the same database options settings as specified on the Application Database tab");
        	clmUseDbConnectionSettingsCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	clmUseDbConnectionSettingsCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_USE_DB_CONNECTION_SETTINGS")));
        	clmUseDbConnectionSettingsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	clmUseDbConnectionSettingsCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					parentContainer.syncDbClmDbFields();
					toggleClmDbConnectionFields();
					toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	clmUseDbConnectionSettingsCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return clmUseDbConnectionSettingsCheckBox;
    } 
    
    public boolean isClmUseDBConnectionSettings(){
    	return getClmUseDbConnectionSettingsCheckBox().isSelected();
    }
    
    public void setClmUseDbConnectionSettings(boolean isSelected){
    	getClmUseDbConnectionSettingsCheckBox().setSelected(isSelected);
    }
    
    public void setClmUseDbConnectionSettingsEnabled(boolean isEnabled){
    	getClmUseDbConnectionSettingsCheckBox().setEnabled(isEnabled);
    }
 
    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getClmDbTypeComboBox() {
        if (clmDbTypeComboBox == null) {
        	clmDbTypeComboBox = new JComboBox();
        	clmDbTypeComboBox.setToolTipText("The Database Type is automatically synchronized with the Database Type property selected on the App DB tab");
        	
        	Map<String,String> clmDbTypeOptionsMap = OptionsMapManager.getDbTypeOptionsMap();
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
                    	updateClmDbFields();
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	clmDbTypeComboBox.addFocusListener(new FocusChangeHandler());
        	
        	clmDbTypeComboBox.setEnabled(false); // Value is driven by App Db Type
        }
        return clmDbTypeComboBox;
    }    
    
    public void setClmDatabaseType(String selectedItemValue){
    	getClmDbTypeComboBox().setSelectedItem(selectedItemValue);
    }

    /**
     * This method initializes the CLM Database Connection URL Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbConnectionUrlField() {
        if (clmDbConnectionUrlField == null) {
        	clmDbConnectionUrlField = new JTextField();
        	clmDbConnectionUrlField.setToolTipText("This is a read-only compound property composed of the DB Hostname, Port, and Schema properties");
        	clmDbConnectionUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_CONNECTION_URL"));
        	clmDbConnectionUrlField.setEnabled(false);// use hostname, port, schema fields instead
        	clmDbConnectionUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmDbConnectionUrlField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbConnectionUrlField;
    }
    
    public String getClmDbConnectionUrl(){
    	return getClmDbConnectionUrlField().getText();
    }
    
    public void setClmDbConnectionUrl(String clmDbConnectionUrl){
    	getClmDbConnectionUrlField().setText(clmDbConnectionUrl);
    }
    
    /**
     * This method initializes the Database Connection URL Hostname Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbHostnameField() {
        if (clmDbHostnameField == null) {
        	clmDbHostnameField = new JTextField();
        	clmDbHostnameField.setToolTipText("Enter the hostname (or sitename) that uniquely identifies the database instance on the network");
        	clmDbHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_SERVER"));
        	clmDbHostnameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbHostnameField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbHostnameField;
    }
    
    public String getClmDbHostname(){
    	return getClmDbHostnameField().getText();
    }
    
    public void setClmDbHostname(String clmDbHostName){
    	getClmDbHostnameField().setText(clmDbHostName);
    }
    
    /**
     * This method initializes the Database Connection URL Port Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbPortField() {
        if (clmDbPortField == null) {
        	clmDbPortField = new JTextField();
        	clmDbPortField.setToolTipText("Enter the port number the database instance is listening to for data requests");
        	clmDbPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_SERVER_PORT"));
        	clmDbPortField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbPortField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbPortField;
    }
    
    public String getClmDbPort() {
    	return getClmDbPortField().getText();
    }
    
    public void setClmDbPort(String clmDbPort){
    	getClmDbPortField().setText(clmDbPort);
    }
    
    /**
     * This method initializes the Database Connection URL Schema Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbSchemaField() {
        if (clmDbSchemaField == null) {
        	clmDbSchemaField = new JTextField();
        	clmDbSchemaField.setToolTipText("Enter the database schema name");
        	clmDbSchemaField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_NAME"));
        	clmDbSchemaField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	updateClmDbFields();
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	clmDbSchemaField.addFocusListener(new FocusChangeHandler());
        }
        return clmDbSchemaField;
    }
    
    public String getClmDbSchema(){
    	return getClmDbSchemaField().getText();
    }
    
    public void setClmDbSchema(String setClmDbSchema){
    	getClmDbSchemaField().setText(setClmDbSchema);
    }
    
    private void updateClmDbFields(){
    	String dbType = (String)getClmDbTypeComboBox().getSelectedItem();
    	
    	//update using hostname, port, and schema values
    	String clmDbConnectionUrlTemplate = OptionsMapManager.getDbUrlOptionsMap().get(dbType);
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@HOSTNAME@",clmDbHostnameField.getText());
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@PORT@",clmDbPortField.getText());
    	clmDbConnectionUrlTemplate = clmDbConnectionUrlTemplate.replace("@SCHEMA@",clmDbSchemaField.getText());
    	
    	clmDbConnectionUrlField.setText(clmDbConnectionUrlTemplate);
    }    
    
    /**
     * This method initializes the CLM Database Username Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmDbUsernameField() {
        if (clmDbUsernameField == null) {
        	clmDbUsernameField = new JTextField();
        	clmDbUsernameField.setToolTipText("Enter the username used to authenticate database requests");
        	clmDbUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_USERNAME"));
        	clmDbUsernameField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                	toggleRecreateClmDbFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                	toggleRecreateClmDbFields();
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
   
    public void setClmDbUsername(String clmDbUsername){
    	getClmDbUsernameField().setText(clmDbUsername);
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
        	clmDbPasswordField.setToolTipText("Enter the password used to authenticate database requests");
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
    
    public void setClmDbPassword(String clmDbPassword){
    	getClmDbPasswordField().setText(clmDbPassword);
    }
    
    public void setClmDbPasswordEnabled(boolean isEnabled){
    	getClmDbPasswordField().setEnabled(isEnabled);
    }
    
    public void toggleRecreateClmDbFields() {
		if (ValidationUtils.isNotBlank(getClmDbSchema()) && (parentContainer.isAppDbAndClmSchemaSame() || parentContainer.isCsmDbAndClmSchemaSame() )) {
			clmDbDropSchemaCheckBox.setSelected(false);
			clmDbDropSchemaCheckBox.setEnabled(false);
		} else {
			clmDbDropSchemaCheckBox.setEnabled(true);
		}
    }
    
    
    public void toggleClmDbConnectionFields() {
    	log.debug("* * * isClmUseDBConnectionSettings: "+isClmUseDBConnectionSettings());
    	if (isClmUseDBConnectionSettings()){
    		//clmDbTypeComboBox.setEnabled(false);  //always disabled - set to match App Db Type
			//clmDbConnectionUrlField.setEnabled(false); //always disabled
			clmDbHostnameField.setEnabled(false);
			clmDbPortField.setEnabled(false);
			clmDbSchemaField.setEnabled(false);
			clmDbUsernameField.setEnabled(false);
			clmDbPasswordField.setEnabled(false);
    	} else {
    		//clmDbTypeComboBox.setEnabled(true); //always disabled - set to match App Db Type
			//clmDbConnectionUrlField.setEnabled(true); //always disabled
			clmDbHostnameField.setEnabled(true);
			clmDbPortField.setEnabled(true);
			clmDbSchemaField.setEnabled(true);
			clmDbUsernameField.setEnabled(true);
			clmDbPasswordField.setEnabled(true);
    	}
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getTestConnectionButton() {
        if (testConnectionButton == null) {
        	testConnectionButton = new JButton();
        	testConnectionButton.setToolTipText("Click to test a connection to the CLM database using the specified properties");
        	testConnectionButton.setText("Test Connection");
        	testConnectionButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	testConnectionButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.testDbConnection(OptionsMapManager.getDbTypeOptionsMap().get(getClmDbTypeComboBox().getSelectedItem().toString()), 
                			getClmDbConnectionUrl(), getClmDbUsername(), getClmDbPassword());
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
    private JButton getClmDbSqlFilePathButton() {
        if (clmDbSqlFilePathButton == null) {
        	clmDbSqlFilePathButton = new JButton();
        	clmDbSqlFilePathButton.setToolTipText("Click to select the SQL file to use when recreating the CLM database objects such as schema, tables, and views.");
        	clmDbSqlFilePathButton.setText("Browse");
        	clmDbSqlFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	clmDbSqlFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getClmDbSqlFileField().getText();
                        String location = ResourceManager.promptFile(previous, FileFilters.SQL_FILTER);
                        if (location != null && location.length() > 0) {
                        	getClmDbSqlFileField().setText(location);
                        } else {
                        	getClmDbSqlFileField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return clmDbSqlFilePathButton;
    }
        
    public void enableClmDbSqlFileButton(boolean enable){
    	getClmDbSqlFilePathButton().setEnabled(enable);
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
     * This method initializes the Use JNDI Based Connection Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getClmDbDropSchemaCheckBox() {
        if (clmDbDropSchemaCheckBox == null) {
        	clmDbDropSchemaCheckBox = new JCheckBox();
        	clmDbDropSchemaCheckBox.setToolTipText("If checked, all CLM database objects like tables, views, and sequences will be dropped at deployment time");
        	clmDbDropSchemaCheckBox.setToolTipText("Drop all of the tables from the CLM Database Schema?");
        	clmDbDropSchemaCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	clmDbDropSchemaCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("CLM_DB_DROP_SCHEMA")));
        	clmDbDropSchemaCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	clmDbDropSchemaCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	clmDbDropSchemaCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return clmDbDropSchemaCheckBox;
    }  
    
    public boolean isDbDropSchemaSelected() {
    	return getClmDbDropSchemaCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Database JNDI Name Field
     * 
     * @return javax.swing.JTextField
     */
    public JTextField getClmDbSqlFileField() {
        if (clmDbSqlFileField == null) {
        	clmDbSqlFileField = new JTextField();
        	clmDbSqlFileField.setToolTipText("Enter the absolute path to the CLM SQL file to be executed at the deployment time. Alternatively, use the Browse button");
        	
        	String dbType = getClmDbType();
        	if ("oracle".equalsIgnoreCase(dbType)){
        		clmDbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("clm.db.install.create.oracle.file.list.ui")); 
        	} else if ("mysql".equalsIgnoreCase(dbType)){
        		clmDbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("clm.db.install.create.mysql.file.list.ui"));
        	} else if ("postgresql".equalsIgnoreCase(dbType)){
        		clmDbSqlFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("clm.db.install.create.postgresql.file.list.ui"));        		
        	} else {
        		clmDbSqlFileField.setText("");
        	}
        	
        	clmDbSqlFileField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmDbSqlFileField.addFocusListener(new FocusChangeHandler());
        	
        	clmDbSqlFileField.setEditable(true); // Allow changes directly or via the DB SQL File Button 
        }
        return clmDbSqlFileField;
    }
   
    
    public String getDbSqlFile() {
    	return getClmDbSqlFileField().getText();
    }
    
    public String getDbSqlFilePath(){
    	return getClmDbSqlFileField().getText().replace('\\', '/');
    }
    
    public void setDbSqlFilePath(String filePath){
    	getClmDbSqlFileField().setText(filePath);
    }
    
    public String getClmDbSqlFileName(){
    	String dbSqlFilePath = getClmDbSqlFileField().getText().replace('\\', '/');
    	
    	return dbSqlFilePath.substring(dbSqlFilePath.lastIndexOf('/')+1);
    }
    
    public String getClmDbType(){
    	return getClmDbTypeComboBox().getSelectedItem().toString();
    }
    
	/**
	 * This method initializes advancedSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (clmSettingsPanel == null) {
			
			JLabel clmUseDbConnectionSettingsLabel = null;
			
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
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridwidth = 3;
			//gridBagConstraints20.weighty = 1.0D;
			//gridBagConstraints20.weightx = 1.0D; 
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridwidth = 3;
			//gridBagConstraints30.weighty = 1.0D;
			//gridBagConstraints30.weightx = 1.0D;  
			
		    clmUseDbConnectionSettingsLabel = new JLabel();
		    clmUseDbConnectionSettingsLabel.setText("Use DB connection Settings?");
		    
		    clmSettingsPanel = new JPanel();
			clmSettingsPanel.setLayout(new GridBagLayout());
			clmSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Logging Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

			clmSettingsPanel.add(clmUseDbConnectionSettingsLabel, gridBagConstraints10);
			clmSettingsPanel.add(getClmUseDbConnectionSettingsCheckBox(), gridBagConstraints11);
		    
			clmSettingsPanel.add(getLoggingSettingsSubPanel(), gridBagConstraints20);
			clmSettingsPanel.add(getClmDbCreationSettingsSubPanel(), gridBagConstraints30);

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
		    clmSettingsSubPanel.add(getClmDbConnectionUrlField(), gridBagConstraints21);
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
	 * This method initializes dbConnectionJndiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getClmDbCreationSettingsSubPanel() {
		if (clmDbCreationSettingsSubPanel == null) {
			
		    //DB Creation Settings Panel Label Definitions
		    JLabel clmDbDropSchemaLabel = null;
		    JLabel clmDbSqlFileLabel = null;
			
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
		    
			clmDbDropSchemaLabel = new JLabel();
			clmDbDropSchemaLabel.setText("Drop CLM Database Schema?");

			clmDbSqlFileLabel = new JLabel();
			clmDbSqlFileLabel.setText("Database SQL File:");

			clmDbCreationSettingsSubPanel = new JPanel();
		    clmDbCreationSettingsSubPanel.setLayout(new GridBagLayout());
		    clmDbCreationSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Re-create CLM Database Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    clmDbCreationSettingsSubPanel.add(clmDbDropSchemaLabel, gridBagConstraints10);
		    clmDbCreationSettingsSubPanel.add(getClmDbDropSchemaCheckBox(), gridBagConstraints11);
		    
		    clmDbCreationSettingsSubPanel.add(clmDbSqlFileLabel, gridBagConstraints20);
		    clmDbCreationSettingsSubPanel.add(getClmDbSqlFileField(), gridBagConstraints21);
		    clmDbCreationSettingsSubPanel.add(getClmDbSqlFilePathButton(), gridBagConstraints22);
			
		    clmDbCreationSettingsSubPanel.validate();
		}
		
		return clmDbCreationSettingsSubPanel;
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
		    
		    JLabel clmDbDropSchemaLabel = null;
		    JLabel clmDbDropSchemaValueLabel = null;
		    JLabel clmDbSqlFileLabel = null;
		    JLabel clmDbSqlFileValueLabel = null;
        	
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
        	
        	clmDbTypeLabel = new JLabel();
        	clmDbTypeLabel.setText("Type:");
        	clmDbTypeValueLabel = new JLabel();
        	clmDbTypeValueLabel.setText(getClmDbType());
		    
		    clmDbConnectionUrlLabel = new JLabel();
		    clmDbConnectionUrlLabel.setText("Connection URL:");
		    clmDbConnectionUrlValueLabel = new JLabel();
		    clmDbConnectionUrlValueLabel.setText(getClmDbConnectionUrlField().getText());
		    
		    clmDbUsernameLabel = new JLabel();
		    clmDbUsernameLabel.setText("Username:");
		    clmDbUsernameValueLabel = new JLabel();
		    clmDbUsernameValueLabel.setText(getClmDbUsernameField().getText());
		    
		    clmDbPasswordLabel = new JLabel();
		    clmDbPasswordLabel.setText("Password:");
		    clmDbPasswordValueLabel = new JLabel();
		    clmDbPasswordValueLabel.setText(getClmDbPasswordField().getText());
		    
		    clmDbDropSchemaLabel = new JLabel();
		    clmDbDropSchemaLabel.setText("Drop Database Schema?");
		    clmDbDropSchemaValueLabel = new JLabel();
		    clmDbDropSchemaValueLabel.setText(Utils.convertToYesNo(getClmDbDropSchemaCheckBox()));
            
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
		    clmSettingsReviewPanel.add(clmDbDropSchemaLabel, gridBagConstraints50);
		    clmSettingsReviewPanel.add(clmDbDropSchemaValueLabel, gridBagConstraints51);
		    
		    if (ValidationUtils.isNotBlank(this.getClmDbSqlFileField().getText())){

			    clmDbSqlFileLabel = new JLabel();
			    clmDbSqlFileLabel.setText("Database SQL File:");
			    
			    clmDbSqlFileValueLabel = new JLabel();
			    String clmDbSqlFile = getClmDbSqlFileField().getText();
			    if (clmDbSqlFile != null && clmDbSqlFile.length() > SQL_FILE_DISPLAY_LENGTH)
			    	clmDbSqlFile = "..."+clmDbSqlFile.substring(clmDbSqlFile.length() - SQL_FILE_DISPLAY_LENGTH);
			    clmDbSqlFileValueLabel.setText(clmDbSqlFile);
			    
		    	clmSettingsReviewPanel.add(clmDbSqlFileLabel, gridBagConstraints60);
		    	clmSettingsReviewPanel.add(clmDbSqlFileValueLabel, gridBagConstraints61);
		    }
            
		    clmSettingsReviewPanel.validate();
        //}
        return clmSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();

    	//CLM Settings Validation
    	if (parentContainer.isClmEnabled()){

    		String clmDbConnectionUrlField = this.getClmDbConnectionUrlField().getText();
    		if (ValidationUtils.isBlank(clmDbConnectionUrlField)) {
    			result.add(new SimpleValidationMessage(CLM_DB_CONNECTION_URL + " must not be blank.", Severity.ERROR, CLM_DB_CONNECTION_URL));
    		}

    		if (ValidationUtils.isBlank(getClmDbHostnameField().getText())) {
    			result.add(new SimpleValidationMessage(CLM_DB_SERVER + " must not be blank.", Severity.ERROR, CLM_DB_SERVER));
    		}

    		String clmDbPort = getClmDbPortField().getText();
    		if (ValidationUtils.isBlank(clmDbPort)) {
    			result.add(new SimpleValidationMessage(CLM_DB_SERVER_PORT + " must not be blank.", Severity.ERROR, CLM_DB_SERVER_PORT));
    		}

    		if (!ValidationUtils.isNumeric(clmDbPort)){
    			result.add(new SimpleValidationMessage(CLM_DB_SERVER_PORT + " must be numeric.", Severity.ERROR, CLM_DB_SERVER_PORT));
    		}

    		if (ValidationUtils.isBlank(getClmDbSchemaField().getText())) {
    			result.add(new SimpleValidationMessage(CLM_DB_NAME + " must not be blank.", Severity.ERROR, CLM_DB_NAME));
    		}

    		if (ValidationUtils.isBlank(this.getClmDbUsernameField().getText())) {
    			result.add(new SimpleValidationMessage(CLM_DB_USERNAME + " must not be blank.", Severity.ERROR, CLM_DB_USERNAME));
    		}

    		if (ValidationUtils.isBlank(this.getClmDbPasswordField().getText())) {
    			result.add(new SimpleValidationMessage(CLM_DB_PASSWORD + " must not be blank.", Severity.ERROR, CLM_DB_PASSWORD));
    		}

    		if (getClmDbDropSchemaCheckBox().isSelected() && !parentContainer.isAppDbAndClmSchemaSame()){
    			if (ValidationUtils.isBlank(this.getClmDbSqlFileField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " must not be blank when "+CLM_DB_DROP_SCHEMA+" is selected.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}
    		}

    		if (getClmDbDropSchemaCheckBox().isSelected() && !parentContainer.isCsmDbAndClmSchemaSame()){
    			if (ValidationUtils.isBlank(this.getClmDbSqlFileField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " must not be blank when "+CLM_DB_DROP_SCHEMA+" is selected.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}
    		}    		

    		String clmDbSchema = getClmDbSchemaField().getText();
    		if (ValidationUtils.isNotBlank(clmDbSchema) && parentContainer.isAppDbAndClmSchemaSame() && parentContainer.isAppDbDropSchemaSelected() ) {
    			//TODO :: investigate if there is a way to visually signal that a CheckBox has a validation error.
    			//        Currently, creating a validation error for the CheckBox has no effect.  As a result,
    			//        using toggleReCreateClmDBFields() instead to enforce rule
//  			if (getClmDbDropSchemaCheckBox().isSelected()){
//  			log.debug("* * * Validation error: App DB and CLM Schema are the same, and CLM Drop Schema CheckBox is selected.");
//  			result.add(new SimpleValidationMessage(CLM_DB_DROP_SCHEMA + " must not selected when both the App DB and CLM schema are the same.", Severity.ERROR, CLM_DB_DROP_SCHEMA));
//  			}

    			if (ValidationUtils.isBlank(this.getClmDbSqlFileField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " must not be blank when the application DB is being dropped, and both the App DB and CLM DB schema are the same.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}
    		}
    		
    		if (ValidationUtils.isNotBlank(clmDbSchema) && parentContainer.isCsmDbAndClmSchemaSame() && parentContainer.isCsmDbDropSchemaSelected() ) {
    			//TODO :: investigate if there is a way to visually signal that a CheckBox has a validation error.
    			//        Currently, creating a validation error for the CheckBox has no effect.  As a result,
    			//        using toggleReCreateClmDBFields() instead to enforce rule
//  			if (getClmDbDropSchemaCheckBox().isSelected()){
//  			log.debug("* * * Validation error: App DB and CLM Schema are the same, and CLM Drop Schema CheckBox is selected.");
//  			result.add(new SimpleValidationMessage(CLM_DB_DROP_SCHEMA + " must not selected when both the App DB and CLM schema are the same.", Severity.ERROR, CLM_DB_DROP_SCHEMA));
//  			}

    			if (ValidationUtils.isBlank(this.getClmDbSqlFileField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " must not be blank when the CSM DB is being dropped, and both the CSM DB and CLM DB schema are the same.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}
    		}    		

    		if (ValidationUtils.isNotBlank(this.getClmDbSqlFileField().getText())) {
    			File file = new File(this.getClmDbSqlFileField().getText());
    			if(!file.exists()){
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " does not exist.  Please select or enter a valid absolute path to the file.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}

    			if (!this.getClmDbSqlFileField().getText().endsWith("sql")){
    				result.add(new SimpleValidationMessage(CLM_DB_SQL_FILE + " must refer to a SQL (*.sql) file.", Severity.ERROR, CLM_DB_SQL_FILE));
    			}
    		}
    	}

    	return result;
    }
    
    public void initValidation() {
		// Common Logging Module DB Connection
        ValidationComponentUtils.setMessageKey(getClmDbTypeComboBox(), CLM_DB_TYPE);
        ValidationComponentUtils.setMandatory(getClmDbTypeComboBox(), true);
        ValidationComponentUtils.setMessageKey(getClmDbConnectionUrlField(), CLM_DB_CONNECTION_URL);
        ValidationComponentUtils.setMandatory(getClmDbConnectionUrlField(), true);
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
        
        ValidationComponentUtils.setMessageKey(getClmDbDropSchemaCheckBox(), CLM_DB_DROP_SCHEMA);
        ValidationComponentUtils.setMandatory(getClmDbDropSchemaCheckBox(), true);
        ValidationComponentUtils.setMessageKey(getClmDbSqlFileField(), CLM_DB_SQL_FILE);
        ValidationComponentUtils.setMandatory(getClmDbSqlFileField(), true);
        
        updateClmDbFields();
        toggleClmDbConnectionFields();
        toggleRecreateClmDbFields();
        parentContainer.toggleClmTestConnectionButton();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    		
		propsMap.put("CLM_USE_DB_CONNECTION_SETTINGS", Boolean.valueOf(clmUseDbConnectionSettingsCheckBox.isSelected()).toString() );
		propsMap.put("CLM_DB_TYPE", OptionsMapManager.getDbTypeOptionsMap().get(getClmDbTypeComboBox().getSelectedItem().toString()));
		propsMap.put("CLM_DB_CONNECTION_URL", getClmDbConnectionUrlField().getText());
		propsMap.put("CLM_DB_SERVER", getClmDbHostnameField().getText());
		propsMap.put("CLM_DB_SERVER_PORT", getClmDbPortField().getText());
		propsMap.put("CLM_DB_NAME", getClmDbSchemaField().getText());
		propsMap.put("CLM_DB_USERNAME", getClmDbUsernameField().getText());
		propsMap.put("CLM_DB_PASSWORD", getClmDbPasswordField().getText());
		
		propsMap.put("CLM_DB_DROP_SCHEMA", Boolean.valueOf(getClmDbDropSchemaCheckBox().isSelected()).toString() );
		
    	String dbType = getClmDbType();  	
    	if ("oracle".equalsIgnoreCase(dbType)){
    		propsMap.put("clm.db.install.create.oracle.file.list", getClmDbSqlFileName());
    		propsMap.put("clm.db.install.create.oracle.file.list.ui", getClmDbSqlFileField().getText().replace('\\', '/'));  
    	} else if ("mysql".equalsIgnoreCase(dbType)){
    		propsMap.put("clm.db.install.create.mysql.file.list", getClmDbSqlFileName());
    		propsMap.put("clm.db.install.create.mysql.file.list.ui", getClmDbSqlFileField().getText().replace('\\', '/'));
    	} else if ("postgresql".equalsIgnoreCase(dbType)){
    		propsMap.put("clm.db.install.create.postgresql.file.list", getClmDbSqlFileName());
    		propsMap.put("clm.db.install.create.postgresql.file.list.ui", getClmDbSqlFileField().getText().replace('\\', '/'));
    	}
    	
    	return propsMap;
    }
}
