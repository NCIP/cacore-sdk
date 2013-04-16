/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.DeployPropertiesViewer;
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

import org.apache.log4j.Logger;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.util.ValidationUtils;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class AppServerSettingsPanel implements Panel, PanelValidator {
	
	private static final Logger log = Logger.getLogger(AppServerSettingsPanel.class);
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	public static final String SERVER_HTTP_URL = "External Server HTTP URL";
    public static final String SERVER_TYPE = "Server Type";
    
    // Tomcat Validation Message Constants
    public static final String TOMCAT = "tomcat";
    public static final String TOMCAT_HOSTNAME = "Tomcat Hostname";
    public static final String TOMCAT_PORT_AJP = "Tomcat AJP Port";
    public static final String TOMCAT_PORT_HTTP = "Tomcat HTTP Port";
    public static final String TOMCAT_PORT_SHUTDOWN = "Tomcat Shutdown Port";
    public static final String TOMCAT_PORT_SSL = "Tomcat SSL Port";
    
    // JBoss Validation Message Constants
    public static final String JBOSS = "jboss";

    public static final String JBOSS_PORT_NAME="JBoss Port Name";
    public static final String JBOSS_SERVER_AJP_PORT="JBoss Server AJP Port";
    public static final String JBOSS_SERVER_HOSTNAME="JBoss Server Hostname";
    public static final String JBOSS_SERVER_JNDI_PORT="JBoss Server JNDI Port";
    public static final String JBOSS_SERVER_PORT="Jboss Server Port";
    public static final String JBOSS_SERVER_SSL_PORT="Jboss Server SSL Port";
	
	public AppServerSettingsPanel(DeployPropertiesViewer parentContainer,TabbedPanePropertiesValidator mainPanelValidator){
		this.parentContainer = parentContainer;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	// App Server Tab Panels
	private JPanel appServerSettingsPanel = null;
	private JPanel tomcatServerSettingsSubPanel = null;
	private JPanel jbossServerSettingsSubPanel = null;
	private JPanel appServerSettingsReviewPanel = null;
	private JPanel configureServerSubPanel = null;
    
	//Application Server Settings Panel Component Definitions
    private JTextField externalServerHttpUrlField = null;
    private JComboBox  serverTypeComboBox = null;
    
    // Tomcat component Definitions
    private JTextField tomcatHostnameField = null;
    private JTextField tomcatPortAjpField=null;
    private JTextField tomcatPortHttpField=null;
    private JTextField tomcatPortShutdownField=null;
    private JTextField tomcatPortSslField=null;
    
    // JBoss
    private JComboBox  jbossPortNameComboBox = null;
    private JTextField jbossServerAjpPortField = null;
    private JTextField jbossServerHostnameField = null;
    private JTextField jbossServerJndiPortField = null;
    private JTextField jbossServerPortField = null;
    private JTextField jbossServerSslPortField = null;
    
    //Configure Server Sub-Panel Component Definitions
    private JCheckBox installServerCheckBox=null;
    
    /**
     * This method initializes the tomcat Server Hostname Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getExternalServerHttpUrlField() {
    	if (externalServerHttpUrlField == null) {
    		externalServerHttpUrlField = new JTextField();
   		
    		externalServerHttpUrlField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("SERVER_HTTP_URL"));
    		externalServerHttpUrlField.setToolTipText("Enter the complete external server HTTP URL.  The format is \"http://<<SERVER_HOST_NAME>>:<<SERVER_HTTP_PORT>>/<<PROJECT_NAME>>");
    		
    		externalServerHttpUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
    		externalServerHttpUrlField.addFocusListener(new FocusChangeHandler());
    	}
    	return externalServerHttpUrlField;
    }
    
    
    /**
     * This method initializes the Server Type Combo Box
     * 
     * @return javax.swing.JTextField
     */         
    private JComboBox getServerTypeComboBox() {
    	if (serverTypeComboBox == null) {
    		serverTypeComboBox = new JComboBox();
    		serverTypeComboBox.setToolTipText("Specify whether deploying to a JBoss or Tomcat Application Server instance.");
    		
    		Map<String,String> serverTypeOptionsMap = OptionsMapManager.getServerTypeOptionsMap();
        	if (serverTypeOptionsMap!=null){
            	Iterator<String> iter = serverTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		serverTypeComboBox.addItem((String)iter.next());
            	}

            	String serverTypeValue = parentContainer.getPropertiesManager().getDeployPropertyValue("SERVER_TYPE");
            	String serverTypeKey = OptionsMapManager.getValueToKeyMap().get(serverTypeValue);
            	
            	if (serverTypeKey != null && serverTypeKey.length()>0)
            		serverTypeComboBox.setSelectedItem(serverTypeKey);
        	}
        	
        	serverTypeComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	toggleServerFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });

    		serverTypeComboBox.addFocusListener(new FocusChangeHandler());
    	}
    	return serverTypeComboBox; 
    }
    
    public String getServerType() {
    	return getServerTypeComboBox().getSelectedItem().toString();
    }
    
    protected void toggleServerFields() {
    	String serverType = getServerType();

    	log.debug("Toggling Server Fields:  serverType: " + serverType);
		if (serverType.equalsIgnoreCase(TOMCAT)){
		    
		    getTomcatSettingsSubPanel().setVisible(true);
		    getJBossSettingsSubPanel().setVisible(false);
		    
		    getTomcatHostnameField().setEnabled(true);
		    getTomcatPortAjpField().setEnabled(true);
		    getTomcatPortHttpField().setEnabled(true);
		    getTomcatPortShutdownField().setEnabled(true);
		    getTomcatPortSslField().setEnabled(true);
		    
		    getJbossPortNameComboBox().setEnabled(false);
		    getJbossServerAjpPortField().setEnabled(false);
		    getJbossServerHostnameField().setEnabled(false);
		    getJbossServerJndiPortField().setEnabled(false);
		    getJbossServerPortField().setEnabled(false);
		    getJbossServerSslPortField().setEnabled(false);
		    
		} else if (serverType.equalsIgnoreCase(JBOSS)){
		    
		    getTomcatSettingsSubPanel().setVisible(false);
		    getJBossSettingsSubPanel().setVisible(true);
		    
		    getTomcatHostnameField().setEnabled(false);
		    getTomcatPortAjpField().setEnabled(false);
		    getTomcatPortHttpField().setEnabled(false);
		    getTomcatPortShutdownField().setEnabled(false);
		    getTomcatPortSslField().setEnabled(false);
		    
		    getJbossPortNameComboBox().setEnabled(true);
		    getJbossServerAjpPortField().setEnabled(true);
		    getJbossServerHostnameField().setEnabled(true);
		    getJbossServerJndiPortField().setEnabled(true);
		    getJbossServerPortField().setEnabled(true);
		    getJbossServerSslPortField().setEnabled(true);

		} else {
		    
		    getTomcatSettingsSubPanel().setVisible(false);
		    getJBossSettingsSubPanel().setVisible(false);
		    
		    getTomcatHostnameField().setEnabled(false);
		    getTomcatPortAjpField().setEnabled(false);
		    getTomcatPortHttpField().setEnabled(false);
		    getTomcatPortShutdownField().setEnabled(false);
		    getTomcatPortSslField().setEnabled(false);
		    
		    getJbossPortNameComboBox().setEnabled(false);
		    getJbossServerAjpPortField().setEnabled(false);
		    getJbossServerHostnameField().setEnabled(false);
		    getJbossServerJndiPortField().setEnabled(false);
		    getJbossServerPortField().setEnabled(false);
		    getJbossServerSslPortField().setEnabled(false);

		}
		
		getSettingsPanel().validate();// in order to 'repaint' the panel
		
    }

    /**
     * This method initializes the tomcat Server Hostname Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getTomcatHostnameField() {
    	if (tomcatHostnameField == null) {
    		tomcatHostnameField = new JTextField();
    		tomcatHostnameField.setToolTipText("Enter the hostname (or site name) that uniquely identifies the Tomcat instance on the network.");
   		
    		tomcatHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("tomcat.hostname"));
    		
    		tomcatHostnameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		tomcatHostnameField.addFocusListener(new FocusChangeHandler());
    	}
    	return tomcatHostnameField;
    }

    /**
     * This method initializes the Server AJP Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getTomcatPortAjpField() {
    	if (tomcatPortAjpField == null) {
    		tomcatPortAjpField = new JTextField();
    		tomcatPortAjpField.setToolTipText("Enter the Tomcat Apache JServ Protocol (AJP) Port Number.");
    		
    		tomcatPortAjpField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("tomcat.port.ajp"));
    		
    		tomcatPortAjpField.getDocument().addDocumentListener(new DocumentListener() {
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
    		tomcatPortAjpField.addFocusListener(new FocusChangeHandler());
    	}
    	return tomcatPortAjpField;
    }
    
    /**
     * This method initializes the Tomcat Server HTTP Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getTomcatPortHttpField() {
    	if (tomcatPortHttpField == null) {
    		tomcatPortHttpField = new JTextField();
    		tomcatPortHttpField.setToolTipText("Enter the port number Tomcat will listen to for HTTP requests");
    		tomcatPortHttpField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("tomcat.port.http"));
    		
    		tomcatPortHttpField.getDocument().addDocumentListener(new DocumentListener() {
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
    		tomcatPortHttpField.addFocusListener(new FocusChangeHandler());
    	}
    	return tomcatPortHttpField;
    }
    
    /**
     * This method initializes the Tomcat Server Shutdown Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getTomcatPortShutdownField() {
    	if (tomcatPortShutdownField == null) {
    		tomcatPortShutdownField = new JTextField();
    		tomcatPortShutdownField.setToolTipText("Enter the port number Tomcat will listen to for application server shutdown requests");
    		
    		tomcatPortShutdownField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("tomcat.port.shutdown"));
    		
    		tomcatPortShutdownField.getDocument().addDocumentListener(new DocumentListener() {
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
    		tomcatPortShutdownField.addFocusListener(new FocusChangeHandler());
    	}
    	return tomcatPortShutdownField;
    }

    /**
     * This method initializes the Tomcat Server Shutdown Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getTomcatPortSslField() {
    	if (tomcatPortSslField == null) {
    		tomcatPortSslField = new JTextField();
   		    tomcatPortSslField.setToolTipText("Enter the port number the Tomcat server will listen to for SSL requests");
    		tomcatPortSslField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("tomcat.port.ssl"));
    		
    		tomcatPortSslField.getDocument().addDocumentListener(new DocumentListener() {
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
    		tomcatPortSslField.addFocusListener(new FocusChangeHandler());
    	}
    	return tomcatPortSslField;
    }
    
    /**
     * This method initializes the Jboss Port Name Combo Box
     * 
     * @return javax.swing.JTextField
     */         
    private JComboBox getJbossPortNameComboBox() {
    	if (jbossPortNameComboBox == null) {
    		jbossPortNameComboBox = new JComboBox();
    		jbossPortNameComboBox.setToolTipText("Select the JBoss Port Name corresponding to the server port that JBoss will use to manage its services");
    		
    		Map<String,String> jbossPortNameOptionsMap = OptionsMapManager.getJbossPortNameOptionsMap();
        	if (jbossPortNameOptionsMap!=null){
            	Iterator<String> iter = jbossPortNameOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		jbossPortNameComboBox.addItem((String)iter.next());
            	}

            	String jbossPortNameValue = parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.ports.name");
            	String jbossPortNameKey = OptionsMapManager.getValueToKeyMap().get(jbossPortNameValue);
            	
            	if (jbossPortNameKey != null && jbossPortNameKey.length()>0)
            		jbossPortNameComboBox.setSelectedItem(jbossPortNameKey);
        	}
        	
        	jbossPortNameComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	toggleServerFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });

        	jbossPortNameComboBox.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossPortNameComboBox; 
    }
    
    /**
     * This method initializes the Jboss Server AJP Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getJbossServerAjpPortField() {
    	if (jbossServerAjpPortField == null) {
    		jbossServerAjpPortField = new JTextField();
    		jbossServerAjpPortField.setToolTipText("Enter the JBoss Apache JServ Protocol (AJP) Port Number.");
    		jbossServerAjpPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.ajp.port"));
    		
    		jbossServerAjpPortField.getDocument().addDocumentListener(new DocumentListener() {
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
    		jbossServerAjpPortField.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossServerAjpPortField;
    }
    
   /**
     * This method initializes the Jboss Server Hostname Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getJbossServerHostnameField() {
    	if (jbossServerHostnameField == null) {
    		jbossServerHostnameField = new JTextField();
    		jbossServerHostnameField.setToolTipText("Enter the hostname (or site name) that uniquely identifies the JBoss instance on the network.");
   		
    		jbossServerHostnameField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.hostname"));
    		
    		jbossServerHostnameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		jbossServerHostnameField.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossServerHostnameField;
    }
    
    /**
     * This method initializes the Jboss Server JNDI Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getJbossServerJndiPortField() {
    	if (jbossServerJndiPortField == null) {
    		jbossServerJndiPortField = new JTextField();
    		jbossServerJndiPortField.setToolTipText("Enter the port number the JBoss server will listen to for JNDI requests");
    		jbossServerJndiPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.jndi.port"));
    		
    		jbossServerJndiPortField.getDocument().addDocumentListener(new DocumentListener() {
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
    		jbossServerJndiPortField.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossServerJndiPortField;
    }
    
    /**
     * This method initializes the Jboss Server Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getJbossServerPortField() {
    	if (jbossServerPortField == null) {
    		jbossServerPortField = new JTextField();
    		jbossServerPortField.setToolTipText("Enter the port number JBoss will listen to for HTTP requests");
    		jbossServerPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.port"));
    		
    		jbossServerPortField.getDocument().addDocumentListener(new DocumentListener() {
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
    		jbossServerPortField.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossServerPortField;
    }
       
    /**
     * This method initializes the Jboss Server SSL Port Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getJbossServerSslPortField() {
    	if (jbossServerSslPortField == null) {
    		jbossServerSslPortField = new JTextField();
    		jbossServerSslPortField.setToolTipText("Enter the port number the JBoss server will listen to for SSL requests");
    		jbossServerSslPortField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("jboss.server.ssl.port"));
    		
    		jbossServerSslPortField.getDocument().addDocumentListener(new DocumentListener() {
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
    		jbossServerSslPortField.addFocusListener(new FocusChangeHandler());
    	}
    	return jbossServerSslPortField;
    }
    
    /**
     * This method initializes the Use JNDI Based Connection Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getInstallServerCheckBox() {
        if (installServerCheckBox == null) {
        	installServerCheckBox = new JCheckBox();
        	installServerCheckBox.setToolTipText("Toggle to enable/disable the installation of a new instance of the Application Server");
        	installServerCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	installServerCheckBox.setSelected(Boolean.parseBoolean(parentContainer.getPropertiesManager().getDeployPropertyValue("INSTALL_CONTAINER")));
        	installServerCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	installServerCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	installServerCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return installServerCheckBox;
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
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSettingsPanel() {
		if (appServerSettingsPanel == null) {
			
		    //Application Server Settings Panel Label Definitions
		    JLabel externalServerHostnameLabel = null;
		    JLabel serverTypeLabel = null;
		    
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridwidth = 1;

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints11.gridy = 1;
			//gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints11.weightx = 1.0D;  
			gridBagConstraints11.gridwidth = 1;
		    
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridwidth = 1;

			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints21.gridy = 2;
			//gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints21.weightx = 1.0D;  
			gridBagConstraints21.gridwidth = 1;

			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.insets = new java.awt.Insets(1, 1, 1, 1);
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.gridwidth = 2;
			gridBagConstraints30.weightx = 1.0D;
			
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.insets = new java.awt.Insets(1, 1, 1, 1);
			gridBagConstraints40.gridx = 0;
			gridBagConstraints40.gridwidth = 2;
			gridBagConstraints40.weightx = 1.0D;
			
			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints50.gridy = 5;
			gridBagConstraints50.insets = new java.awt.Insets(1, 1, 1, 1);
			gridBagConstraints50.gridx = 0;
			gridBagConstraints50.gridwidth = 2;
			gridBagConstraints50.weightx = 1.0D;
			
			externalServerHostnameLabel = new JLabel();
			externalServerHostnameLabel.setText("External Server Hostname:");
	
			serverTypeLabel = new JLabel();
			serverTypeLabel.setText("Select Server Type:");

			appServerSettingsPanel = new JPanel();
			appServerSettingsPanel.setLayout(new GridBagLayout());
			appServerSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Application Server Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
			appServerSettingsPanel.add(externalServerHostnameLabel, gridBagConstraints10);
			appServerSettingsPanel.add(getExternalServerHttpUrlField(), gridBagConstraints11);
			appServerSettingsPanel.add(serverTypeLabel, gridBagConstraints20);
			appServerSettingsPanel.add(getServerTypeComboBox(), gridBagConstraints21);
			appServerSettingsPanel.add(getTomcatSettingsSubPanel(), gridBagConstraints30);
			appServerSettingsPanel.add(getJBossSettingsSubPanel(), gridBagConstraints40);
			appServerSettingsPanel.add(getConfigureServerSubPanel(), gridBagConstraints50);
			
			appServerSettingsPanel.validate();
		}
		return appServerSettingsPanel;
    }
    
	/**
	 * This method initializes dbConnectionJndiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTomcatSettingsSubPanel() {
		if (tomcatServerSettingsSubPanel == null) {
			
		    //Tomcat Settings SubPanel Label
			JLabel tomcatHostnameLabel = null;
			JLabel tomcatPortAjpLabel=null;
			JLabel tomcatPortHttpLabel=null;
			JLabel tomcatPortShutdownLabel=null;
			JLabel tomcatPortSslLabel=null;
			
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
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridx = 0;

			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints41.gridx = 1;
			gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints41.gridy = 4;
			//gridBagConstraints41.weighty = 1.0D;
			gridBagConstraints41.weightx = 1.0D;  
			gridBagConstraints41.gridwidth = 2;
			
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
			//gridBagConstraints51.weighty = 1.0D;
			gridBagConstraints51.weightx = 1.0D;  
			gridBagConstraints51.gridwidth = 2;
			
			tomcatHostnameLabel = new JLabel();
			tomcatHostnameLabel.setText("Hostname:");
			
			tomcatPortAjpLabel = new JLabel();
			tomcatPortAjpLabel.setText("AJP Port:");
			
			tomcatPortHttpLabel = new JLabel();
			tomcatPortHttpLabel.setText("HTTP Port:");
			
			tomcatPortShutdownLabel = new JLabel();
			tomcatPortShutdownLabel.setText("Shutdown Port:");
			
			tomcatPortSslLabel = new JLabel();
			tomcatPortSslLabel.setText("SSL Port:");

		    tomcatServerSettingsSubPanel = new JPanel();
		    tomcatServerSettingsSubPanel.setLayout(new GridBagLayout());
		    tomcatServerSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tomcat Server Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
			
		    tomcatServerSettingsSubPanel.add(tomcatHostnameLabel, gridBagConstraints10);
		    tomcatServerSettingsSubPanel.add(getTomcatHostnameField(), gridBagConstraints11);
		    tomcatServerSettingsSubPanel.add(tomcatPortAjpLabel, gridBagConstraints20);
		    tomcatServerSettingsSubPanel.add(getTomcatPortAjpField(), gridBagConstraints21);
		    tomcatServerSettingsSubPanel.add(tomcatPortHttpLabel, gridBagConstraints30);
		    tomcatServerSettingsSubPanel.add(getTomcatPortHttpField(), gridBagConstraints31);
		    tomcatServerSettingsSubPanel.add(tomcatPortShutdownLabel, gridBagConstraints40);
		    tomcatServerSettingsSubPanel.add(getTomcatPortShutdownField(), gridBagConstraints41);
		    tomcatServerSettingsSubPanel.add(tomcatPortSslLabel, gridBagConstraints50);
		    tomcatServerSettingsSubPanel.add(getTomcatPortSslField(), gridBagConstraints51);
			
		    tomcatServerSettingsSubPanel.validate();
		}
		return tomcatServerSettingsSubPanel;
	}
	
	/**
	 * This method initializes the first of two JBoss Server Settings panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJBossSettingsSubPanel() {
		if (jbossServerSettingsSubPanel == null) {
			
		    //JBoss Settings SubPanel Labels
			JLabel jbossPortNameLabel = null;
		    JLabel jbossServerAjpPortLabel = null;
		    JLabel jbossServerHostnameLabel = null;
		    JLabel jbossServerJndiPortLabel = null;
		    JLabel jbossServerPortLabel = null;
		    JLabel jbossServerSslPortLabel = null;
			
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
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridx = 0;

			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints41.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints41.gridx = 1;
			gridBagConstraints41.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints41.gridy = 4;
			//gridBagConstraints41.weighty = 1.0D;
			gridBagConstraints41.weightx = 1.0D;  
			gridBagConstraints41.gridwidth = 2;
			
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
			//gridBagConstraints51.weighty = 1.0D;
			gridBagConstraints51.weightx = 1.0D;  
			gridBagConstraints51.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints60.gridy = 6;
			gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints60.gridx = 0;

			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints61.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints61.gridx = 1;
			gridBagConstraints61.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints61.gridy = 6;
			//gridBagConstraints61.weighty = 1.0D;
			gridBagConstraints61.weightx = 1.0D;  
			gridBagConstraints61.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints70 = new GridBagConstraints();
			gridBagConstraints70.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints70.gridy = 7;
			gridBagConstraints70.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints70.gridx = 0;

			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints71.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints71.gridx = 1;
			gridBagConstraints71.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints71.gridy = 7;
			//gridBagConstraints71.weighty = 1.0D;
			gridBagConstraints71.weightx = 1.0D;  
			gridBagConstraints71.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints80 = new GridBagConstraints();
			gridBagConstraints80.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints80.gridy = 8;
			gridBagConstraints80.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints80.gridx = 0;

			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints81.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints81.gridx = 1;
			gridBagConstraints81.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints81.gridy = 8;
			//gridBagConstraints81.weighty = 1.0D;
			gridBagConstraints81.weightx = 1.0D;  
			gridBagConstraints81.gridwidth = 2;		
			
			GridBagConstraints gridBagConstraints90 = new GridBagConstraints();
			gridBagConstraints90.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints90.gridy = 9;
			gridBagConstraints90.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints90.gridx = 0;

			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
			gridBagConstraints91.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints91.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints91.gridx = 1;
			gridBagConstraints91.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints91.gridy = 9;
			//gridBagConstraints91.weighty = 1.0D;
			gridBagConstraints91.weightx = 1.0D;  
			gridBagConstraints91.gridwidth = 2;	
			
			GridBagConstraints gridBagConstraints100 = new GridBagConstraints();
			gridBagConstraints100.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints100.gridy = 10;
			gridBagConstraints100.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints100.gridx = 0;

			GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
			gridBagConstraints101.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints101.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints101.gridx = 1;
			gridBagConstraints101.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints101.gridy = 10;
			//gridBagConstraints101.weighty = 1.0D;
			gridBagConstraints101.weightx = 1.0D;  
			gridBagConstraints101.gridwidth = 2;	
			
			GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
			gridBagConstraints110.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints110.gridy = 11;
			gridBagConstraints110.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints110.gridx = 0;

			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints111.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints111.gridx = 1;
			gridBagConstraints111.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints111.gridy = 11;
			//gridBagConstraints111.weighty = 1.0D;
			gridBagConstraints111.weightx = 1.0D;  
			gridBagConstraints111.gridwidth = 2;	
			
			GridBagConstraints gridBagConstraints120 = new GridBagConstraints();
			gridBagConstraints120.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints120.gridy = 12;
			gridBagConstraints120.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints120.gridx = 0;

			GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
			gridBagConstraints121.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints121.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints121.gridx = 1;
			gridBagConstraints121.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints121.gridy = 12;
			//gridBagConstraints121.weighty = 1.0D;
			gridBagConstraints121.weightx = 1.0D;  
			gridBagConstraints121.gridwidth = 2;		
		
			jbossPortNameLabel = new JLabel();
			jbossPortNameLabel.setText("Port Name:");
			jbossServerAjpPortLabel = new JLabel();
			jbossServerAjpPortLabel.setText("Server AJP Port:");
			jbossServerHostnameLabel = new JLabel();
			jbossServerHostnameLabel.setText("Server Hostname:");
			jbossServerJndiPortLabel = new JLabel();
			jbossServerJndiPortLabel.setText("Server JNDI Port:");
			jbossServerPortLabel = new JLabel();
			jbossServerPortLabel.setText("Server Port:");
			jbossServerSslPortLabel = new JLabel();
			jbossServerSslPortLabel.setText("Server SSL Port:");

			jbossServerSettingsSubPanel = new JPanel();
			jbossServerSettingsSubPanel.setLayout(new GridBagLayout());
			jbossServerSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "JBoss Server Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
			
			jbossServerSettingsSubPanel.add(jbossPortNameLabel, gridBagConstraints20);
			jbossServerSettingsSubPanel.add(getJbossPortNameComboBox(), gridBagConstraints21);
			jbossServerSettingsSubPanel.add(jbossServerHostnameLabel, gridBagConstraints30);
			jbossServerSettingsSubPanel.add(getJbossServerHostnameField(), gridBagConstraints31);
			jbossServerSettingsSubPanel.add(jbossServerAjpPortLabel, gridBagConstraints40);
			jbossServerSettingsSubPanel.add(getJbossServerAjpPortField(), gridBagConstraints41);
			jbossServerSettingsSubPanel.add(jbossServerJndiPortLabel, gridBagConstraints50);
			jbossServerSettingsSubPanel.add(getJbossServerJndiPortField(), gridBagConstraints51);
			jbossServerSettingsSubPanel.add(jbossServerPortLabel, gridBagConstraints60);
			jbossServerSettingsSubPanel.add(getJbossServerPortField(), gridBagConstraints61);
			jbossServerSettingsSubPanel.add(jbossServerSslPortLabel, gridBagConstraints70);
			jbossServerSettingsSubPanel.add(getJbossServerSslPortField(), gridBagConstraints71);
			
			jbossServerSettingsSubPanel.validate();
		}
		return jbossServerSettingsSubPanel;
	}
	
	/**
	 * This method initializes dbConnectionJndiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getConfigureServerSubPanel() {
		if (configureServerSubPanel == null) {
			
		    //DB Creation Settings Panel Label Definitions
		    JLabel installServerLabel = null;
			
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
			
			installServerLabel = new JLabel();
			installServerLabel.setText("Install Server?");

			configureServerSubPanel = new JPanel();
		    configureServerSubPanel.setLayout(new GridBagLayout());
		    configureServerSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configure Server Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    configureServerSubPanel.add(installServerLabel, gridBagConstraints10);
		    configureServerSubPanel.add(getInstallServerCheckBox(), gridBagConstraints11);
			
		    configureServerSubPanel.validate();
		}
		
		return configureServerSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (appServerSettingsReviewPanel == null) {
        	
		    // Tomcat Server Labels
			JLabel tomcatHostnameLabel = null;
			JLabel tomcatHostnameValueLabel = null;
			JLabel tomcatPortAjpLabel=null;
			JLabel tomcatPortAjpValueLabel=null;
			JLabel tomcatPortHttpLabel=null;
			JLabel tomcatPortHttpValueLabel=null;
			JLabel tomcatPortShutdownLabel=null;
			JLabel tomcatPortShutdownValueLabel=null;
			JLabel tomcatPortSslLabel=null;
			JLabel tomcatPortSslValueLabel=null;
			
			// JBoss Server Labels
		    JLabel jbossPortNameLabel = null;
		    JLabel jbossPortNameValueLabel = null;
		    JLabel jbossServerAjpPortLabel = null;
		    JLabel jbossServerAjpPortValueLabel = null;
		    JLabel jbossServerHostnameLabel = null;
		    JLabel jbossServerHostnameValueLabel = null;
		    JLabel jbossServerJndiPortLabel = null;
		    JLabel jbossServerJndiPortValueLabel = null;
		    JLabel jbossServerPortLabel = null;
		    JLabel jbossServerPortValueLabel = null;
		    JLabel jbossServerSslPortLabel = null;
		    JLabel jbossServerSslPortValueLabel = null;
        	
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
		    
        	String serverType = getServerType();
        	if (serverType.equalsIgnoreCase(TOMCAT)){
        		
        		tomcatHostnameLabel = new JLabel();
        		tomcatHostnameLabel.setText("Hostname:");
        		tomcatHostnameValueLabel = new JLabel();
        		tomcatHostnameValueLabel.setText(getTomcatHostnameField().getText());
        		
        		tomcatPortAjpLabel = new JLabel();
        		tomcatPortAjpLabel.setText("AJP Port:");
        		tomcatPortAjpValueLabel = new JLabel();
        		tomcatPortAjpValueLabel.setText(getTomcatPortAjpField().getText());
        		
        		tomcatPortHttpLabel = new JLabel();
        		tomcatPortHttpLabel.setText("HTTP Port:");
        		tomcatPortHttpValueLabel = new JLabel();
        		tomcatPortHttpValueLabel.setText(getTomcatPortHttpField().getText());
        		
        		tomcatPortShutdownLabel = new JLabel();
        		tomcatPortShutdownLabel.setText("Shutdown Port:");
        		tomcatPortShutdownValueLabel = new JLabel();
        		tomcatPortShutdownValueLabel.setText(getTomcatPortShutdownField().getText());
        		
        		tomcatPortSslLabel = new JLabel();
        		tomcatPortSslLabel.setText("SSL Port:");
        		tomcatPortSslValueLabel = new JLabel();
        		tomcatPortSslValueLabel.setText(getTomcatPortSslField().getText());
        		
        	} else if (serverType.equalsIgnoreCase(JBOSS)){
    		    
    		    jbossPortNameLabel = new JLabel();
    		    jbossPortNameLabel.setText("Port Name:");
    		    jbossPortNameValueLabel = new JLabel();
    		    jbossPortNameValueLabel.setText(getJbossPortNameComboBox().getSelectedItem().toString());
    		    
    		    jbossServerAjpPortLabel = new JLabel();
    		    jbossServerAjpPortLabel.setText("Server AJP Port:");
    		    jbossServerAjpPortValueLabel = new JLabel();
    		    jbossServerAjpPortValueLabel.setText(getJbossServerAjpPortField().getText());
    		    
    		    jbossServerHostnameLabel= new JLabel();
    		    jbossServerHostnameLabel.setText("Server Hostname:");
    		    jbossServerHostnameValueLabel = new JLabel();
    		    jbossServerHostnameValueLabel.setText(getJbossServerHostnameField().getText());
    		    
    		    jbossServerJndiPortLabel = new JLabel();
    		    jbossServerJndiPortLabel.setText("Server JNDI Port:");
    		    jbossServerJndiPortValueLabel = new JLabel();
    		    jbossServerJndiPortValueLabel.setText(getJbossServerJndiPortField().getText());
    		    
    		    jbossServerPortLabel = new JLabel();
    		    jbossServerPortLabel.setText("Server Port:");
    		    jbossServerPortValueLabel = new JLabel();
    		    jbossServerPortValueLabel.setText(getJbossServerPortField().getText());
    		    
    		    jbossServerSslPortLabel = new JLabel();
    		    jbossServerSslPortLabel.setText("Server SSL Port:");
    		    jbossServerSslPortValueLabel = new JLabel();
    		    jbossServerSslPortValueLabel.setText(getJbossServerSslPortField().getText());    		    
        	}
            
            appServerSettingsReviewPanel = new JPanel();
            appServerSettingsReviewPanel.setLayout(new GridBagLayout());
            appServerSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, serverType+" Application Server Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	if (serverType.equalsIgnoreCase(TOMCAT)){
                appServerSettingsReviewPanel.add(tomcatHostnameLabel, gridBagConstraints20);
                appServerSettingsReviewPanel.add(tomcatHostnameValueLabel, gridBagConstraints21);
                appServerSettingsReviewPanel.add(tomcatPortAjpLabel, gridBagConstraints30);
                appServerSettingsReviewPanel.add(tomcatPortAjpValueLabel, gridBagConstraints31);
                appServerSettingsReviewPanel.add(tomcatPortHttpLabel, gridBagConstraints40);
                appServerSettingsReviewPanel.add(tomcatPortHttpValueLabel, gridBagConstraints41);
                appServerSettingsReviewPanel.add(tomcatPortShutdownLabel, gridBagConstraints50);
                appServerSettingsReviewPanel.add(tomcatPortShutdownValueLabel, gridBagConstraints51);
                appServerSettingsReviewPanel.add(tomcatPortSslLabel, gridBagConstraints60);
                appServerSettingsReviewPanel.add(tomcatPortSslValueLabel, gridBagConstraints61);
        	} else if (serverType.equalsIgnoreCase(JBOSS)){
                appServerSettingsReviewPanel.add(jbossPortNameLabel, gridBagConstraints20);
                appServerSettingsReviewPanel.add(jbossPortNameValueLabel, gridBagConstraints21);  
                appServerSettingsReviewPanel.add(jbossServerHostnameLabel, gridBagConstraints30);
                appServerSettingsReviewPanel.add(jbossServerHostnameValueLabel, gridBagConstraints31);
                appServerSettingsReviewPanel.add(jbossServerAjpPortLabel, gridBagConstraints40);
                appServerSettingsReviewPanel.add(jbossServerAjpPortValueLabel, gridBagConstraints41);
                appServerSettingsReviewPanel.add(jbossServerJndiPortLabel, gridBagConstraints50);
                appServerSettingsReviewPanel.add(jbossServerJndiPortValueLabel, gridBagConstraints51);
                appServerSettingsReviewPanel.add(jbossServerPortLabel, gridBagConstraints60);
                appServerSettingsReviewPanel.add(jbossServerPortValueLabel, gridBagConstraints61);
                appServerSettingsReviewPanel.add(jbossServerSslPortLabel, gridBagConstraints70);
                appServerSettingsReviewPanel.add(jbossServerSslPortValueLabel, gridBagConstraints71);
        	}
            
            appServerSettingsReviewPanel.validate();
        //}
        return appServerSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	// Turns out that the External Server HTTP URL is not a required field
//		if (ValidationUtils.isBlank(getExternalServerHttpUrlField().getText())) {
//			result.add(new SimpleValidationMessage(SERVER_HTTP_URL + " must not be blank.", Severity.ERROR, SERVER_HTTP_URL));
//		} 
    	
    	String serverType = getServerType();
		if (ValidationUtils.isBlank(serverType)) {
			result.add(new SimpleValidationMessage(SERVER_TYPE + " must not be blank.", Severity.ERROR, SERVER_TYPE));
		} 
		
        //Tomcat Server Components
    	if (serverType.equalsIgnoreCase(TOMCAT)){
    	    
    		if (ValidationUtils.isBlank(this.getTomcatHostnameField().getText())) {
    			result.add(new SimpleValidationMessage(TOMCAT_HOSTNAME + " must not be blank.", Severity.ERROR, TOMCAT_HOSTNAME));
    		} 
    		
    		String tomcatAjpPort = this.getTomcatPortAjpField().getText();
    		if (ValidationUtils.isBlank(tomcatAjpPort)) {
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_AJP + " must not be blank.", Severity.ERROR, TOMCAT_PORT_AJP));
    		} 
    		
    		if (!ValidationUtils.isNumeric(tomcatAjpPort)){
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_AJP + " must be numeric.", Severity.ERROR, TOMCAT_PORT_AJP));
    		}
    		
    		String tomcatHttpPort = this.getTomcatPortHttpField().getText();
    		if (ValidationUtils.isBlank(tomcatHttpPort)) {
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_HTTP + " must not be blank.", Severity.ERROR, TOMCAT_PORT_HTTP));
    		}
    		
    		if (!ValidationUtils.isNumeric(tomcatHttpPort)){
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_HTTP + " must be numeric.", Severity.ERROR, TOMCAT_PORT_HTTP));
    		}
    		
    		String tomcatShutdownPort = this.getTomcatPortShutdownField().getText();
    		if (ValidationUtils.isBlank(tomcatShutdownPort)) {
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_SHUTDOWN + " must not be blank.", Severity.ERROR, TOMCAT_PORT_SHUTDOWN));
    		} 
    		
    		if (!ValidationUtils.isNumeric(tomcatShutdownPort)){
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_SHUTDOWN + " must be numeric.", Severity.ERROR, TOMCAT_PORT_SHUTDOWN));
    		}
    		
    		String tomcatSslPort = this.getTomcatPortSslField().getText();
    		if (ValidationUtils.isBlank(tomcatSslPort)) {
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_SSL + " must not be blank.", Severity.ERROR, TOMCAT_PORT_SSL));
    		}
    		
    		if (!ValidationUtils.isNumeric(tomcatSslPort)){
    			result.add(new SimpleValidationMessage(TOMCAT_PORT_SSL + " must be numeric.", Severity.ERROR, TOMCAT_PORT_SSL));
    		}
    		
    	} else if (serverType.equalsIgnoreCase(JBOSS)){

    		if (ValidationUtils.isBlank(this.getJbossPortNameComboBox().getSelectedItem().toString())) {
    			result.add(new SimpleValidationMessage(JBOSS_PORT_NAME + " must not be blank.", Severity.ERROR, JBOSS_PORT_NAME));
    		} 
    		
    		if (ValidationUtils.isBlank(this.getJbossServerHostnameField().getText())) {
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_HOSTNAME + " must not be blank.", Severity.ERROR, JBOSS_SERVER_HOSTNAME));
    		} 
    		
    		String JbossAjpPort = this.getJbossServerAjpPortField().getText();
    		if (ValidationUtils.isBlank(JbossAjpPort)) {
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_AJP_PORT + " must not be blank.", Severity.ERROR, JBOSS_SERVER_AJP_PORT));
    		} 
    		
    		if (!ValidationUtils.isNumeric(JbossAjpPort)){
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_AJP_PORT + " must be numeric.", Severity.ERROR, JBOSS_SERVER_AJP_PORT));
    		}
    		
    		String jbossJndiPort = this.getJbossServerJndiPortField().getText();
    		if (ValidationUtils.isBlank(jbossJndiPort)) {
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_JNDI_PORT + " must not be blank.", Severity.ERROR, JBOSS_SERVER_JNDI_PORT));
    		} 
    		
    		if (!ValidationUtils.isNumeric(jbossJndiPort)){
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_JNDI_PORT + " must be numeric.", Severity.ERROR, JBOSS_SERVER_JNDI_PORT));
    		}
    		
    		String jbossPort = this.getJbossServerPortField().getText();
    		if (ValidationUtils.isBlank(jbossPort)) {
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_PORT + " must not be blank.", Severity.ERROR, JBOSS_SERVER_PORT));
    		} 
    		
    		if (!ValidationUtils.isNumeric(jbossPort)){
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_PORT + " must be numeric.", Severity.ERROR, JBOSS_SERVER_PORT));
    		}

    		String jbossSslPort = this.getJbossServerSslPortField().getText();
    		if (ValidationUtils.isBlank(jbossSslPort)) {
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_SSL_PORT + " must not be blank.", Severity.ERROR, JBOSS_SERVER_SSL_PORT));
    		}
    		
    		if (!ValidationUtils.isNumeric(jbossSslPort)){
    			result.add(new SimpleValidationMessage(JBOSS_SERVER_SSL_PORT + " must be numeric.", Severity.ERROR, JBOSS_SERVER_SSL_PORT));
    		}
    	}
    	
    	return result;
    }
    
    public void initValidation() {

    	//App Server
    	ValidationComponentUtils.setMessageKey(getExternalServerHttpUrlField(), SERVER_HTTP_URL);
        ValidationComponentUtils.setMandatory(getExternalServerHttpUrlField(), true);
    	ValidationComponentUtils.setMessageKey(getServerTypeComboBox(), SERVER_TYPE);
        ValidationComponentUtils.setMandatory(getServerTypeComboBox(), true);
    	
        //Tomcat Server
        ValidationComponentUtils.setMessageKey(getTomcatHostnameField(), TOMCAT_HOSTNAME);
        ValidationComponentUtils.setMandatory(getTomcatHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getTomcatPortAjpField(), TOMCAT_PORT_AJP);
        ValidationComponentUtils.setMandatory(getTomcatPortAjpField(), true);  
        ValidationComponentUtils.setMessageKey(getTomcatPortHttpField(), TOMCAT_PORT_HTTP);
        ValidationComponentUtils.setMandatory(getTomcatPortHttpField(), true);
        ValidationComponentUtils.setMessageKey(getTomcatPortShutdownField(), TOMCAT_PORT_SHUTDOWN);
        ValidationComponentUtils.setMandatory(getTomcatPortShutdownField(), true);
        ValidationComponentUtils.setMessageKey(getTomcatPortSslField(), TOMCAT_PORT_SSL);
        ValidationComponentUtils.setMandatory(getTomcatPortSslField(), true);
        
        //JBoss Server
        ValidationComponentUtils.setMessageKey(getJbossPortNameComboBox(), JBOSS_PORT_NAME);
        ValidationComponentUtils.setMandatory(getJbossPortNameComboBox(), true);       
        ValidationComponentUtils.setMessageKey(getJbossServerAjpPortField(), JBOSS_SERVER_AJP_PORT);
        ValidationComponentUtils.setMandatory(getJbossServerAjpPortField(), true);
        ValidationComponentUtils.setMessageKey(getJbossServerHostnameField(), JBOSS_SERVER_HOSTNAME);
        ValidationComponentUtils.setMandatory(getJbossServerHostnameField(), true);
        ValidationComponentUtils.setMessageKey(getJbossServerJndiPortField(), JBOSS_SERVER_JNDI_PORT);
        ValidationComponentUtils.setMandatory(getJbossServerJndiPortField(), true);
        ValidationComponentUtils.setMessageKey(getJbossServerPortField(), JBOSS_SERVER_PORT);
        ValidationComponentUtils.setMandatory(getJbossServerPortField(), true);
        ValidationComponentUtils.setMessageKey(getJbossServerSslPortField(), JBOSS_SERVER_SSL_PORT);
        ValidationComponentUtils.setMandatory(getJbossServerSslPortField(), true);
        
    	toggleServerFields();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	String serverType = getServerType();
    	
		// Application Server Properties
    	
    	if (ValidationUtils.isNotBlank(getExternalServerHttpUrlField().getText())) {
    		propsMap.put("SERVER_HTTP_URL", getExternalServerHttpUrlField().getText());
    	} 
    	
		propsMap.put("SERVER_TYPE", OptionsMapManager.getServerTypeOptionsMap().get(serverType));
		
		if (serverType.equalsIgnoreCase(TOMCAT)){
			// Tomcat
			propsMap.put("tomcat.hostname", getTomcatHostnameField().getText());
			propsMap.put("tomcat.port.ajp", getTomcatPortAjpField().getText());
			propsMap.put("tomcat.port.http", getTomcatPortHttpField().getText());
			propsMap.put("tomcat.port.shutdown", getTomcatPortShutdownField().getText());
			propsMap.put("tomcat.port.ssl", getTomcatPortSslField().getText());

		} else if (serverType.equalsIgnoreCase(JBOSS)){
			// JBoss
			propsMap.put("jboss.server.ports.name", OptionsMapManager.getJbossPortNameOptionsMap().get(getJbossPortNameComboBox().getSelectedItem().toString()));
			propsMap.put("jboss.server.ajp.port", getJbossServerAjpPortField().getText());
			propsMap.put("jboss.server.hostname", getJbossServerHostnameField().getText());
			propsMap.put("jboss.server.jndi.port", getJbossServerJndiPortField().getText());
			propsMap.put("jboss.server.port", getJbossServerPortField().getText());
			propsMap.put("jboss.server.ssl.port", getJbossServerSslPortField().getText());
		}
		
		propsMap.put("INSTALL_CONTAINER", Boolean.valueOf(getInstallServerCheckBox().isSelected()).toString() );
		
    	return propsMap;
    }
    
}
