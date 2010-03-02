package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.FileFilters;
import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.DeployPropertiesViewer;
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

public final class RemoteSshSettingsPanel implements Panel, PanelValidator {
	
	
	//private static final Logger log = Logger.getLogger(OptionsMapManager.class);
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	private static final String SSH_SERVER_USERNAME = "SSH Server Username";
	private static final String SSH_SERVER_HOSTNAME = "SSH Server Hostname";
	private static final String SSH_PORT = "SSH Port";
	private static final String SSH_KEY_FILE = "SSH Key File";
	private static final String SSH_TEMP_DIR = "SSH Temporary Directory";

	public RemoteSshSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator) {
		this.parentContainer=parentContainer;
		this.mainPanelValidator = mainPanelValidator;
	}
	
	//Deploy Settings Panel
	private JPanel deploySettingsPanel = null;
	private JPanel deployRemoteSettingsSubPanel = null;
	private JPanel deploySettingsReviewPanel = null;
    
	//Deploy Settings Panel Component Definitions
//    private JComboBox  deployTypeComboBox = null;
    
//    private JComboBox  remoteDeployEnvComboBox = null;
    private JTextField sshServerUsernameField = null;
    private JTextField sshServerHostnameField = null;
    private JTextField sshPortField = null;
    private JTextField sshKeyFileField = null;
    private JTextField sshTempDirField = null;

    // Buttons
    private JButton sshKeyFilePathButton = null;
    
    // Cross-panel properties
    
    /**
     * This method initializes the SSH Server Username Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getSshServerUsernameField() {
    	if (sshServerUsernameField == null) {
    		sshServerUsernameField = new JTextField();
    		sshServerUsernameField.setToolTipText("Enter the authentication username when connecting via the SSH protocol");
    		sshServerUsernameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("ssh.server.username"));
    		sshServerUsernameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		
    		sshServerUsernameField.addFocusListener(new FocusChangeHandler());
    	}
    	return sshServerUsernameField;
    } 

   /**
    * This method initializes the SSH Server Username Field
    * 
    * @return javax.swing.JTextField
    */
    private JTextField getSshServerHostnameField() {
    	if (sshServerHostnameField == null) {
    		sshServerHostnameField = new JTextField();
    		sshServerHostnameField.setToolTipText("Enter the hostname (or sitename) that uniquely identifies the remote server instance on the network");
    		sshServerHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("ssh.server.hostname"));
    		sshServerHostnameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		
    		sshServerHostnameField.addFocusListener(new FocusChangeHandler());
    	}
    	return sshServerHostnameField;
    }
    
    /**
     * This method initializes the SSH Server Port Field
     * 
     * @return javax.swing.JTextField
     */
     private JTextField getSshPortField() {
     	if (sshPortField == null) {
     		sshPortField = new JTextField();
     		sshPortField.setToolTipText("Enter the SSH Port configured on the remote server");
     		sshPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("ssh.port"));
     		sshPortField.getDocument().addDocumentListener(new DocumentListener() {
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
     		
     		sshPortField.addFocusListener(new FocusChangeHandler());
     	}
     	return sshPortField;
     }
    
    
    /**
     * This method initializes the SSH Temporary Directory Field
     * 
     * @return javax.swing.JTextField
     */    
    private JTextField getSshTempDirField() {
    	if (sshTempDirField == null) {
    		sshTempDirField = new JTextField();
    		sshTempDirField.setToolTipText("Enter the absolute path to a temporary directory on the remote server that can be used while deploying the application server and generated application artifacts");
    		sshTempDirField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("ssh.dir.temp"));
    		
    		sshTempDirField.getDocument().addDocumentListener(new DocumentListener() {
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
    		
    		sshTempDirField.addFocusListener(new FocusChangeHandler());

    	}
    	return sshTempDirField;
    }
    
    public String getSshTempDirPath(){
    	return getSshTempDirField().getText().replace('\\', '/');
    }
    
    /**
     * This method initializes the SSH Key File Field
     * 
     * @return javax.swing.JTextField
     */    
    private JTextField getSshKeyFileField() {
    	if (sshKeyFileField == null) {
    		sshKeyFileField = new JTextField();
    		sshKeyFileField.setToolTipText("Enter the absolute path to the SSH protocol Key file, or select it using the 'Browse' button to the right of the property");
    		sshKeyFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("ssh.key.file"));
    		sshKeyFileField.getDocument().addDocumentListener(new DocumentListener() {
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
    		
    		sshKeyFileField.addFocusListener(new FocusChangeHandler());

    		sshKeyFileField.setEditable(false); // Only allow changes via the Key File Button
    		//sshKeyFileField.setEnabled(false);
    	}
    	return sshKeyFileField;
    }
    
    public String getSshKeyFilePath(){
    	return getSshKeyFileField().getText().replace('\\', '/');
    }
    
    public String getSshKeyFileName(){
    	String keyFilePath = getSshKeyFileField().getText().replace('\\', '/');
    	
    	return keyFilePath.substring(keyFilePath.lastIndexOf('/')+1);
    }
    
    /**
     * This method initializes SSH Key File Path jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getSshKeyFilePathButton() {
        if (sshKeyFilePathButton == null) {
        	sshKeyFilePathButton = new JButton();
        	sshKeyFilePathButton.setToolTipText("Click to select the SSH protocol Key file");
        	sshKeyFilePathButton.setText("Browse");
        	sshKeyFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	sshKeyFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getSshKeyFileField().getText();
                        String location = ResourceManager.promptFile(previous);
                        if (location != null && location.length() > 0) {
                        	getSshKeyFileField().setText(location);
                        } else {
                        	getSshKeyFileField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return sshKeyFilePathButton;
    }
    
    protected void toggleDeployTypeFields() {
      	
		if (parentContainer.isLocalDeployment()){
			getSshServerUsernameField().setEnabled(false);
		    getSshServerHostnameField().setEnabled(false);
		    getSshPortField().setEnabled(false);
		    getSshKeyFileField().setEnabled(false);
		    getSshTempDirField().setEnabled(false);
		} else if (parentContainer.isRemoteDeployment()){
			getSshServerUsernameField().setEnabled(true);
		    getSshServerHostnameField().setEnabled(true);
		    getSshPortField().setEnabled(true);
		    getSshKeyFileField().setEnabled(true);
		    getSshTempDirField().setEnabled(true);
		} else {
			getSshServerUsernameField().setEnabled(false);
		    getSshServerHostnameField().setEnabled(false);
		    getSshPortField().setEnabled(false);
		    getSshKeyFileField().setEnabled(false);
		    getSshTempDirField().setEnabled(false);
		}
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
	 * This method initializes deploySettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (deploySettingsPanel == null) {
			
		    //Security Settings Panel Label Definitions
		    JLabel deployTypeLabel = null;
	        
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
			gridBagConstraints20.weightx = 1.0D;  
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			//gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridwidth = 3;
			//gridBagConstraints20.weighty = 1.0D;
			gridBagConstraints30.weightx = 1.0D;  
			
			deployTypeLabel = new JLabel();
			deployTypeLabel.setText("Select Deployment Type:");

		    deploySettingsPanel = new JPanel();
		    deploySettingsPanel.setLayout(new GridBagLayout());
		    deploySettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Remote Deployment SSH Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    deploySettingsPanel.add(getRemoteSshSettingsSubPanel(), gridBagConstraints20);
			
		    deploySettingsPanel.validate();
		}
		return deploySettingsPanel;
    }
    
	/**
	 * This method initializes the deploy remote Settings Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRemoteSshSettingsSubPanel() {
		if (deployRemoteSettingsSubPanel == null) {
			
		    //Deploy Remote Settings Label Definitions
		    JLabel sshServerUsernameLabel = null;
		    JLabel sshServerHostnameLabel = null;
		    JLabel sshPortLabel = null;
		    JLabel sshKeyFileLabel = null;
		    JLabel sshTempDirLabel = null;
		
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
			gridBagConstraints21.gridwidth = 2;
			gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
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
			gridBagConstraints41.gridwidth = 2;
			gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints41.weighty = 1.0D;
			gridBagConstraints41.gridx = 1;

			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints50.gridy = 5;
			gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints50.gridx = 0;

            GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
            gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints51.gridx = 1;
            gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints51.gridy = 5;
            gridBagConstraints51.weighty = 1.0D;
            gridBagConstraints51.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
            gridBagConstraints52.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints52.gridy = 5;
            gridBagConstraints52.gridx = 2;
            gridBagConstraints52.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints52.gridwidth = 1;
            
			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints60.gridy = 6;
			gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints60.gridx = 0;

			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints61.gridy = 6;
			gridBagConstraints61.weightx = 1.0;
			gridBagConstraints61.gridwidth = 2;
			gridBagConstraints61.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints61.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints61.weighty = 1.0D;
			gridBagConstraints61.gridx = 1;       
			
		    sshServerUsernameLabel = new JLabel();
		    sshServerUsernameLabel.setText("SSH Server Username:");

		    sshServerHostnameLabel = new JLabel();
		    sshServerHostnameLabel.setText("SSH Server Hostname:");
		    
		    sshPortLabel = new JLabel();
		    sshPortLabel.setText("SSH Port:");
		    
		    sshKeyFileLabel = new JLabel();
		    sshKeyFileLabel.setText("SSH Key File:");
		    
		    sshTempDirLabel = new JLabel();
		    sshTempDirLabel.setText("SSH Temp Directory:");

			deployRemoteSettingsSubPanel = new JPanel();
			deployRemoteSettingsSubPanel.setLayout(new GridBagLayout());
			deployRemoteSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remote SSH Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
			deployRemoteSettingsSubPanel.add(sshServerUsernameLabel, gridBagConstraints20);
			deployRemoteSettingsSubPanel.add(getSshServerUsernameField(), gridBagConstraints21);
			deployRemoteSettingsSubPanel.add(sshServerHostnameLabel, gridBagConstraints30);
			deployRemoteSettingsSubPanel.add(getSshServerHostnameField(), gridBagConstraints31);
			deployRemoteSettingsSubPanel.add(sshPortLabel, gridBagConstraints40);
			deployRemoteSettingsSubPanel.add(getSshPortField(), gridBagConstraints41);
			deployRemoteSettingsSubPanel.add(sshKeyFileLabel, gridBagConstraints50);
			deployRemoteSettingsSubPanel.add(getSshKeyFileField(), gridBagConstraints51);
			deployRemoteSettingsSubPanel.add(getSshKeyFilePathButton(), gridBagConstraints52);
			deployRemoteSettingsSubPanel.add(sshTempDirLabel, gridBagConstraints60);
			deployRemoteSettingsSubPanel.add(getSshTempDirField(), gridBagConstraints61);
			
			deployRemoteSettingsSubPanel.validate();
		}
		return deployRemoteSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (deploySettingsReviewPanel == null) {
    	
		    //Remote SSH Settings Panel Label Definitions  	
	    	JLabel sshServerUsernameLabel = null;
		    JLabel sshServerUsernameValueLabel = null;
		    JLabel sshServerHostnameLabel = null;
		    JLabel sshServerHostnameValueLabel = null;
		    JLabel sshPortLabel = null;
		    JLabel sshPortValueLabel = null;
		    JLabel sshKeyFileLabel = null;
		    JLabel sshKeyFileValueLabel = null;
		    JLabel sshTempDirLabel = null;
		    JLabel sshTempDirValueLabel = null;
        	
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
		    
		    sshServerUsernameLabel = new JLabel();
		    sshServerUsernameLabel.setText("SSH Server Username:");
		    sshServerUsernameValueLabel = new JLabel();
		    sshServerUsernameValueLabel.setText(getSshServerUsernameField().getText());
		    
		    sshServerHostnameLabel = new JLabel();
		    sshServerHostnameLabel.setText("SSH Server Hostname:");
		    sshServerHostnameValueLabel = new JLabel();
		    sshServerHostnameValueLabel.setText(getSshTempDirField().getText());
		    
		    sshPortLabel = new JLabel();
		    sshPortLabel.setText("SSH Port:");
		    sshPortValueLabel = new JLabel();
		    sshPortValueLabel.setText(getSshPortField().getText());

		    sshKeyFileLabel = new JLabel();
		    sshKeyFileLabel.setText("SSH Key File:");
		    sshKeyFileValueLabel = new JLabel();
		    sshKeyFileValueLabel.setText(getSshKeyFileField().getText());
		    
		    sshTempDirLabel = new JLabel();
		    sshTempDirLabel.setText("SSH Temp Dir:");
		    sshTempDirValueLabel = new JLabel();
		    sshTempDirValueLabel.setText(getSshPortField().getText());
            
		    deploySettingsReviewPanel = new JPanel();
		    deploySettingsReviewPanel.setLayout(new GridBagLayout());
		    deploySettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remote SSH Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
	    	
		    if (parentContainer.isRemoteDeployment()){   
			    deploySettingsReviewPanel.add(sshServerUsernameLabel, gridBagConstraints30);
			    deploySettingsReviewPanel.add(sshServerUsernameValueLabel, gridBagConstraints31);
			    deploySettingsReviewPanel.add(sshServerHostnameLabel, gridBagConstraints40);
			    deploySettingsReviewPanel.add(sshServerHostnameValueLabel, gridBagConstraints41);
			    deploySettingsReviewPanel.add(sshPortLabel, gridBagConstraints50);
			    deploySettingsReviewPanel.add(sshPortValueLabel, gridBagConstraints51);
			    deploySettingsReviewPanel.add(sshKeyFileLabel, gridBagConstraints60);
			    deploySettingsReviewPanel.add(sshKeyFileValueLabel, gridBagConstraints61);
			    deploySettingsReviewPanel.add(sshKeyFileLabel, gridBagConstraints70);
			    deploySettingsReviewPanel.add(sshKeyFileValueLabel, gridBagConstraints71);
		    }
            
		    deploySettingsReviewPanel.validate();
        //}
        return deploySettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();

    	if (parentContainer.isRemoteDeployment()){
    		
    		if (ValidationUtils.isBlank(this.getSshServerUsernameField().getText())) {
    			result.add(new SimpleValidationMessage(SSH_SERVER_USERNAME + " must not be blank.", Severity.ERROR, SSH_SERVER_USERNAME));
    		}
    		
    		if (ValidationUtils.isBlank(this.getSshServerHostnameField().getText())) {
    			result.add(new SimpleValidationMessage(SSH_SERVER_HOSTNAME + " must not be blank.", Severity.ERROR, SSH_SERVER_HOSTNAME));
    		}
    		
    		String sshPort = getSshPortField().getText();
    		if (ValidationUtils.isBlank(this.getSshPortField().getText())) {
    			result.add(new SimpleValidationMessage(SSH_PORT + " must not be blank.", Severity.ERROR, SSH_PORT));
    		}
    		
    		if (!ValidationUtils.isNumeric(sshPort)){
    			result.add(new SimpleValidationMessage(SSH_PORT + " must be numeric.", Severity.ERROR, SSH_PORT));
    		}

    		if (ValidationUtils.isBlank(this.getSshKeyFileField().getText())) {
    			result.add(new SimpleValidationMessage(SSH_KEY_FILE + " must not be blank.", Severity.ERROR, SSH_KEY_FILE));
    		} else {
    			File file = new File(this.getSshKeyFileField().getText());
    			if(!file.exists()){
    				result.add(new SimpleValidationMessage(SSH_KEY_FILE + " does not exist.  Please select or enter a valid absolute path to the file.", Severity.ERROR, SSH_KEY_FILE));
    			}
    		}
    		
    		if (ValidationUtils.isBlank(this.getSshTempDirField().getText())) {
    			result.add(new SimpleValidationMessage(SSH_TEMP_DIR + " must not be blank.", Severity.ERROR, SSH_TEMP_DIR));
    		} else {
    			// Cannot validate if it is a valid directory as it is remote.
//    			File file = new File(this.getSshTempDirField().getText());
//    			if(!file.exists()){
//    				result.add(new SimpleValidationMessage(SSH_TEMP_DIR + " does not exist.  Please select or enter a valid absolute path to the directory.", Severity.ERROR, SSH_TEMP_DIR));
//    			}
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        // Deploy Type Validation
        ValidationComponentUtils.setMessageKey(getSshServerUsernameField(), SSH_SERVER_USERNAME);
        ValidationComponentUtils.setMandatory(getSshServerUsernameField(), true);
        ValidationComponentUtils.setMessageKey(getSshServerHostnameField(), SSH_SERVER_HOSTNAME);
        ValidationComponentUtils.setMandatory(getSshServerHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getSshPortField(), SSH_PORT);
        ValidationComponentUtils.setMandatory(getSshPortField(), true);
        ValidationComponentUtils.setMessageKey(getSshKeyFileField(), SSH_KEY_FILE);
        ValidationComponentUtils.setMandatory(getSshKeyFileField(), true);
        ValidationComponentUtils.setMessageKey(getSshTempDirField(), SSH_TEMP_DIR);
        ValidationComponentUtils.setMandatory(getSshTempDirField(), true);
        
        toggleDeployTypeFields(); 
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
		
		if (parentContainer.isRemoteDeployment()){
			// Remote Deploy properties
			propsMap.put("ssh.server.username", getSshServerUsernameField().getText());
			propsMap.put("ssh.server.hostname", getSshServerHostnameField().getText());
			propsMap.put("ssh.port", getSshPortField().getText());
			propsMap.put("ssh.key.file", getSshKeyFileField().getText().replace('\\', '/'));
			propsMap.put("ssh.dir.temp", getSshTempDirField().getText().replace('\\', '/'));
		}
    	
    	return propsMap;
    }
}
