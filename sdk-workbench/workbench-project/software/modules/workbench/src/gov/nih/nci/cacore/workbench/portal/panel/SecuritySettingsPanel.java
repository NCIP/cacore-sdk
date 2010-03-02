package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.CodegenPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
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

public final class SecuritySettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;
	CodegenPropertiesViewer parentContainer = null;
	
	// Security Panel Validation
	private static final String INSTANCE_LEVEL_SECURITY = "Instance level Security";
	private static final String CSM_PROJECT_NAME = "CSM Project Name";
	
	// CaGrid Auth Panel Validation
	private static final String CAGRID_LOGIN_MODULE_NAME = "caGrid Login Module Name";
	private static final String SDK_GRID_LOGIN_SERVICE_NAME = "SDK Grid Login Service Name";
	
	// Security
	private JPanel securitySettingsPanel = null;
	private JPanel securitySettingsSubPanel = null;
	private JPanel caGridLoginSettingsSubPanel = null;
	private JPanel securitySettingsReviewPanel = null;
    
	// Security Settings Panel Component Definitions
    private JCheckBox  enableSecurityCheckBox = null;
    private JCheckBox  enableInstanceLevelSecurityCheckBox = null;
    private JCheckBox  enableAttributeLevelSecurityCheckBox = null;
    private JTextField csmProjectNameField = null;
    private JCheckBox  cacheProtectionElementsCheckBox = null;
    
    private JCheckBox  enableCaGridLoginModuleCheckBox = null;
    private JTextField caGridLoginModuleNameField = null;
    private JTextField sdkGridLoginSvcNameField = null;
	
	public SecuritySettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	public void setParentContainer(CodegenPropertiesViewer parentContainer){
		this.parentContainer=parentContainer;
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
     * This method initializes the Enable Common Logging Module CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableSecurityCheckBox() {
        if (enableSecurityCheckBox == null) {
        	enableSecurityCheckBox = new JCheckBox();
        	enableSecurityCheckBox.setToolTipText("Toggle to enable/disable Security within the generated system. Applies to all of the SDK interfaces.");
        	enableSecurityCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableSecurityCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_SECURITY")));
        	enableSecurityCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableSecurityCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleSecurityFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableSecurityCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableSecurityCheckBox;
    }
    
    public boolean isSecurityEnabled(){
    	return getEnableSecurityCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Enable Instance Level Security CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableInstanceLevelSecurityCheckBox() {
        if (enableInstanceLevelSecurityCheckBox == null) {
        	enableInstanceLevelSecurityCheckBox = new JCheckBox();
        	enableInstanceLevelSecurityCheckBox.setToolTipText("Toggle to enable/disable CSM Instance Level Security. Only relevant if Security is enabled.");
        	enableInstanceLevelSecurityCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableInstanceLevelSecurityCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_INSTANCE_LEVEL_SECURITY")));
        	enableInstanceLevelSecurityCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableInstanceLevelSecurityCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleSecurityFields();
					
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
					
					if (parentContainer != null && (parentContainer instanceof CodegenPropertiesViewer))
					parentContainer.confirmCsmTablesPresent();
				}
        	});

        	enableInstanceLevelSecurityCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableInstanceLevelSecurityCheckBox;
    }
    
    public boolean isInstanceLevelSecurityEnabled(){
    	return getEnableInstanceLevelSecurityCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Enable Attribute Level Security CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableAttributeLevelSecurityCheckBox() {
        if (enableAttributeLevelSecurityCheckBox == null) {
        	enableAttributeLevelSecurityCheckBox = new JCheckBox();
        	enableAttributeLevelSecurityCheckBox.setToolTipText("Toggle to enable/disable CSM Attribute Level Security. Only relevant if Security is enabled.");
        	enableAttributeLevelSecurityCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableAttributeLevelSecurityCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_ATTRIBUTE_LEVEL_SECURITY")));
        	enableAttributeLevelSecurityCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableAttributeLevelSecurityCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleSecurityFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableAttributeLevelSecurityCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableAttributeLevelSecurityCheckBox;
    }
    
    /**
     * This method initializes the CSM Project Name Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCsmProjectNameField() {
        if (csmProjectNameField == null) {
        	csmProjectNameField = new JTextField();
        	csmProjectNameField.setToolTipText("Enter the CSM project name.  Will be used as a prefix when creating the CSM security configuration file name.");
        	csmProjectNameField.setText(propsMgr.getDeployPropertyValue("CSM_PROJECT_NAME"));
        	csmProjectNameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	csmProjectNameField.addFocusListener(new FocusChangeHandler());
        }
        return csmProjectNameField;
    } 
    
    /**
     * This method initializes the Cache Protection Elements CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getCacheProtectionElementsCheckBox() {
        if (cacheProtectionElementsCheckBox == null) {
        	cacheProtectionElementsCheckBox = new JCheckBox();
        	cacheProtectionElementsCheckBox.setToolTipText("Toggle to enable/disable caching of CSM Protection Elements.");
        	cacheProtectionElementsCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	cacheProtectionElementsCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("CACHE_PROTECTION_ELEMENTS")));
        	cacheProtectionElementsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	cacheProtectionElementsCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleSecurityFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	cacheProtectionElementsCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return cacheProtectionElementsCheckBox;
    }
    
    /**
     * This method initializes the Validate Logical Model Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableCaGridLoginModuleCheckBox() {
        if (enableCaGridLoginModuleCheckBox == null) {
        	enableCaGridLoginModuleCheckBox = new JCheckBox();
        	enableCaGridLoginModuleCheckBox.setToolTipText("Toggle to enable/disable the caGrid Login Module. Only relevant if Security is enabled.");
        	enableCaGridLoginModuleCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableCaGridLoginModuleCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_GRID_LOGIN_MODULE")));
        	enableCaGridLoginModuleCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableCaGridLoginModuleCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                	toggleCaGridLoginFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableCaGridLoginModuleCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableCaGridLoginModuleCheckBox;
    }
    
    public boolean isCaGridLoginModuleEnabled(){
    	return getEnableCaGridLoginModuleCheckBox().isSelected();
    }
    
    /**
     * This method initializes the caGrid Login Module Name Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getCaGridLoginModuleNameField() {
    	if (caGridLoginModuleNameField == null) {
    		caGridLoginModuleNameField = new JTextField();
    		caGridLoginModuleNameField.setToolTipText("Enter the caGrid Login Module name. Only relevant if Security and the caGrid Login Module are both enabled."); 
    		caGridLoginModuleNameField.setText(propsMgr.getDeployPropertyValue("CAGRID_LOGIN_MODULE_NAME"));
    		caGridLoginModuleNameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		caGridLoginModuleNameField.addFocusListener(new FocusChangeHandler());
    	}
    	return caGridLoginModuleNameField;
    }
    
    /**
     * This method initializes the SDK Grid Login Service Name Field
     * 
     * @return javax.swing.JTextField
     */    
    private JTextField getSdkGridLoginSvcNameField() {
    	if (sdkGridLoginSvcNameField == null) {
    		sdkGridLoginSvcNameField = new JTextField();
    		sdkGridLoginSvcNameField.setToolTipText("Enter the SDK GRID Login Service name. Only relevant if Security and the caGrid Login Module are both enabled.");
    		sdkGridLoginSvcNameField.setText(propsMgr.getDeployPropertyValue("SDK_GRID_LOGIN_SERVICE_NAME"));
    		sdkGridLoginSvcNameField.getDocument().addDocumentListener(new DocumentListener() {
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
    		sdkGridLoginSvcNameField.addFocusListener(new FocusChangeHandler());
    	}
    	return sdkGridLoginSvcNameField;
    }
    
    protected void toggleSecurityFields() {
		if (getEnableSecurityCheckBox().isSelected()){
		    getEnableInstanceLevelSecurityCheckBox().setEnabled(true);
		    getEnableAttributeLevelSecurityCheckBox().setEnabled(true);
		    getCsmProjectNameField().setEnabled(true);
		    getCacheProtectionElementsCheckBox().setEnabled(true);
		    getEnableCaGridLoginModuleCheckBox().setEnabled(true);
		} else{
		    getEnableInstanceLevelSecurityCheckBox().setEnabled(false);
		    getEnableAttributeLevelSecurityCheckBox().setEnabled(false);
		    getCsmProjectNameField().setEnabled(false);
		    getCacheProtectionElementsCheckBox().setEnabled(false);
		    getEnableCaGridLoginModuleCheckBox().setEnabled(false);
		}
		
		toggleCaGridLoginFields();
    }
    
    protected void toggleCaGridLoginFields() {
    	
    	if (getEnableSecurityCheckBox().isSelected()){
    		if (getEnableCaGridLoginModuleCheckBox().isSelected()){
    			sdkGridLoginSvcNameField.setEnabled(true);
    			caGridLoginModuleNameField.setEnabled(true);
    			return;
    		} 
    	}

    	sdkGridLoginSvcNameField.setEnabled(false);
    	caGridLoginModuleNameField.setEnabled(false);
    }
    
	/**
	 * This method initializes securitySettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (securitySettingsPanel == null) {
			
		    //Security Settings Panel Label Definitions
		    JLabel enableSecurityLabel = null;
		    
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
	    
		    enableSecurityLabel = new JLabel();
		    enableSecurityLabel.setText("Enable Security Extension?");

			securitySettingsPanel = new JPanel();
			securitySettingsPanel.setLayout(new GridBagLayout());
			securitySettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Security Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

			securitySettingsPanel.add(enableSecurityLabel, gridBagConstraints10);
			securitySettingsPanel.add(getEnableSecurityCheckBox(), gridBagConstraints11);
			securitySettingsPanel.add(getSecuritySettingsSubPanel(), gridBagConstraints20);

			securitySettingsPanel.validate();
		}
		return securitySettingsPanel;
	}
    
	/**
	 * This method initializes securitySettingsSubPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSecuritySettingsSubPanel() {
		if (securitySettingsSubPanel == null) {
			
		    //Security Settings Panel Label Definitions
		    JLabel enableInstanceLevelSecurityLabel = null;
		    JLabel enableAttributeLevelSecurityLabel = null;
		    JLabel csmProjectNameLabel = null;
		    JLabel cacheProtectionElementsLabel = null;
		    
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
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints21.gridwidth = 2;
			//gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.weightx = 1.0D;  

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
			//gridBagConstraints31.weighty = 1.0D;
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
			//gridBagConstraints41.weighty = 1.0D;
			gridBagConstraints41.gridx = 1;
			
			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints50.gridy = 5;
			gridBagConstraints50.insets = new java.awt.Insets(20, 2, 2, 2);
			gridBagConstraints50.gridx = 0;

			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints51.gridx = 1;
			gridBagConstraints51.insets = new java.awt.Insets(20, 2, 2, 2);
			gridBagConstraints51.gridy = 5;
			gridBagConstraints51.weighty = 1.0D;
			gridBagConstraints51.weightx = 1.0D;  
			gridBagConstraints51.gridwidth = 2;

			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints60.gridy = 6;
			gridBagConstraints60.gridx = 0;
			gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints60.gridwidth = 3;
			//gridBagConstraints60.weighty = 1.0D; //Non-standard 1.0 setting
			gridBagConstraints60.weightx = 1.0D;  
		    
		    enableInstanceLevelSecurityLabel = new JLabel();
		    enableInstanceLevelSecurityLabel.setText("Enable Instance Level Security?");
		    
		    enableAttributeLevelSecurityLabel = new JLabel();
		    enableAttributeLevelSecurityLabel.setText("Enable Attribute Level Security?");

		    csmProjectNameLabel = new JLabel();
		    csmProjectNameLabel.setText("Enter CSM Project Name:");

		    cacheProtectionElementsLabel = new JLabel();
		    cacheProtectionElementsLabel.setText("Cache Protection Elements?");

		    securitySettingsSubPanel = new JPanel();
		    securitySettingsSubPanel.setLayout(new GridBagLayout());
		    securitySettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Security Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    securitySettingsSubPanel.add(enableInstanceLevelSecurityLabel, gridBagConstraints10);
		    securitySettingsSubPanel.add(getEnableInstanceLevelSecurityCheckBox(), gridBagConstraints11);
		    securitySettingsSubPanel.add(enableAttributeLevelSecurityLabel, gridBagConstraints20);
		    securitySettingsSubPanel.add(getEnableAttributeLevelSecurityCheckBox(), gridBagConstraints21);
		    securitySettingsSubPanel.add(csmProjectNameLabel, gridBagConstraints30);
		    securitySettingsSubPanel.add(getCsmProjectNameField(), gridBagConstraints31);
		    securitySettingsSubPanel.add(cacheProtectionElementsLabel, gridBagConstraints40);
		    securitySettingsSubPanel.add(getCacheProtectionElementsCheckBox(), gridBagConstraints41);

		    securitySettingsSubPanel.add(getCaGridLoginSettingsSubPanel(), gridBagConstraints60);

		    securitySettingsSubPanel.validate();
		}
		return securitySettingsSubPanel;
	}
	
	/**
	 * This method initializes the caDSR Code Generation settings sub-panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCaGridLoginSettingsSubPanel() {
		if (caGridLoginSettingsSubPanel == null) {
			
		    JLabel enableCaGridLoginModuleLabel = null;
		    JLabel caGridLoginModuleNameLabel = null;
		    JLabel sdkGridLoginSvcNameLabel = null;

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
			gridBagConstraints31.weighty = 1.0D;
			gridBagConstraints31.weightx = 1.0D;  
			gridBagConstraints31.gridwidth = 2;
		    
		    //caGrid Auth
			enableCaGridLoginModuleLabel = new JLabel();
			enableCaGridLoginModuleLabel.setText("Enable caGrid Login Module?");
		    
			caGridLoginModuleNameLabel = new JLabel();
			caGridLoginModuleNameLabel.setText("caGrid Login Module Name:");
			
			sdkGridLoginSvcNameLabel = new JLabel();
			sdkGridLoginSvcNameLabel.setText("SDK Grid Login Service Name:");

		    caGridLoginSettingsSubPanel = new JPanel();
		    caGridLoginSettingsSubPanel.setLayout(new GridBagLayout());
		    caGridLoginSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "caGRID Login Module Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    
		    //caGrid Auth
		    caGridLoginSettingsSubPanel.add(enableCaGridLoginModuleLabel, gridBagConstraints10);
		    caGridLoginSettingsSubPanel.add(getEnableCaGridLoginModuleCheckBox(), gridBagConstraints11);
		    caGridLoginSettingsSubPanel.add(sdkGridLoginSvcNameLabel, gridBagConstraints20);
		    caGridLoginSettingsSubPanel.add(getSdkGridLoginSvcNameField(), gridBagConstraints21);
		    caGridLoginSettingsSubPanel.add(caGridLoginModuleNameLabel, gridBagConstraints30);
		    caGridLoginSettingsSubPanel.add(getCaGridLoginModuleNameField(), gridBagConstraints31);
			
		    caGridLoginSettingsSubPanel.validate();
		}
		
		return caGridLoginSettingsSubPanel;
	}
	
	
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (securitySettingsReviewPanel == null) {
        	
        	JLabel enableSecurityLabel = null;
        	JLabel enableSecurityValueLabel = null;
		    JLabel enableInstanceLevelSecurityLabel = null;
		    JLabel enableInstanceLevelSecurityValueLabel = null;
		    JLabel enableAttributeLevelSecurityLabel = null;
		    JLabel enableAttributeLevelSecurityValueLabel = null;
		    JLabel csmProjectNameLabel = null;
		    JLabel csmProjectNameValueLabel = null;
		    JLabel cacheProtectionElementsLabel = null;
		    JLabel cacheProtectionElementsValueLabel = null;
		    JLabel enableCaGridLoginModuleLabel = null;
		    JLabel enableCaGridLoginModuleValueLabel = null;
		    JLabel caGridLoginModuleNameLabel = null;
		    JLabel caGridLoginModuleNameValueLabel = null;
		    JLabel sdkGridLoginSvcNameLabel = null;
		    JLabel sdkGridLoginSvcNameValueLabel = null;
        	
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
            gridBagConstraints80.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints80.gridy = 8;
            gridBagConstraints80.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints80.gridx = 0;
            
            GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
            gridBagConstraints81.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints81.gridy = 8;
            gridBagConstraints81.weightx = 1.0;
            gridBagConstraints81.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints81.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints81.gridwidth = 2;
            gridBagConstraints81.weighty = 1.0D;
            gridBagConstraints81.gridx = 1;
            
        	enableSecurityLabel = new JLabel();
        	enableSecurityLabel.setText("Enable Security?");
        	enableSecurityValueLabel = new JLabel();
        	enableSecurityValueLabel.setText(Utils.convertToYesNo(getEnableSecurityCheckBox()));
		               
		    enableInstanceLevelSecurityLabel = new JLabel();
		    enableInstanceLevelSecurityLabel.setText("Enable Instance Level Security?");
		    enableInstanceLevelSecurityValueLabel = new JLabel();
		    enableInstanceLevelSecurityValueLabel.setText(Utils.convertToYesNo(getEnableInstanceLevelSecurityCheckBox()));
            
		    enableAttributeLevelSecurityLabel = new JLabel();
		    enableAttributeLevelSecurityLabel.setText("Enable Attribute Level Security?");
		    enableAttributeLevelSecurityValueLabel = new JLabel();
		    enableAttributeLevelSecurityValueLabel.setText(Utils.convertToYesNo(getEnableAttributeLevelSecurityCheckBox()));
            
		    csmProjectNameLabel = new JLabel();
		    csmProjectNameLabel.setText("CSM Project Name");
		    csmProjectNameValueLabel = new JLabel();
		    csmProjectNameValueLabel.setText(getCsmProjectNameField().getText());
            
		    cacheProtectionElementsLabel = new JLabel();
		    cacheProtectionElementsLabel.setText("Cache Protection Elements?");
		    cacheProtectionElementsValueLabel = new JLabel();
		    cacheProtectionElementsValueLabel.setText(Utils.convertToYesNo(getCacheProtectionElementsCheckBox()));
		    
		    enableCaGridLoginModuleLabel = new JLabel();
		    enableCaGridLoginModuleLabel.setText("Enable caGrid Login Module?");
		    enableCaGridLoginModuleValueLabel = new JLabel();
		    enableCaGridLoginModuleValueLabel.setText(Utils.convertToYesNo(getEnableCaGridLoginModuleCheckBox()));
           
		    sdkGridLoginSvcNameLabel = new JLabel();
		    sdkGridLoginSvcNameLabel.setText("SDK Grid Login Service Name:");
		    sdkGridLoginSvcNameValueLabel = new JLabel();
		    sdkGridLoginSvcNameValueLabel.setText(getSdkGridLoginSvcNameField().getText());
		    
		    caGridLoginModuleNameLabel = new JLabel();
		    caGridLoginModuleNameLabel.setText("caGrid Login Module Name:");
		    caGridLoginModuleNameValueLabel = new JLabel();
		    caGridLoginModuleNameValueLabel.setText(getCaGridLoginModuleNameField().getText());
            
		    securitySettingsReviewPanel = new JPanel();
		    securitySettingsReviewPanel.setLayout(new GridBagLayout());
		    securitySettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Security Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    securitySettingsReviewPanel.add(enableSecurityLabel, gridBagConstraints10);
		    securitySettingsReviewPanel.add(enableSecurityValueLabel, gridBagConstraints11);
		    
		    if (getEnableSecurityCheckBox().isSelected()){
		    	securitySettingsReviewPanel.add(enableInstanceLevelSecurityLabel, gridBagConstraints20);
		    	securitySettingsReviewPanel.add(enableInstanceLevelSecurityValueLabel, gridBagConstraints21);
		    	securitySettingsReviewPanel.add(enableAttributeLevelSecurityLabel, gridBagConstraints30);
		    	securitySettingsReviewPanel.add(enableAttributeLevelSecurityValueLabel, gridBagConstraints31);
		    	securitySettingsReviewPanel.add(csmProjectNameLabel, gridBagConstraints40);
		    	securitySettingsReviewPanel.add(csmProjectNameValueLabel, gridBagConstraints41); 
		    	securitySettingsReviewPanel.add(cacheProtectionElementsLabel, gridBagConstraints50);
		    	securitySettingsReviewPanel.add(cacheProtectionElementsValueLabel, gridBagConstraints51);

		    	securitySettingsReviewPanel.add(enableCaGridLoginModuleLabel, gridBagConstraints60);
		    	securitySettingsReviewPanel.add(enableCaGridLoginModuleValueLabel, gridBagConstraints61);
		    	
		    	if (getEnableCaGridLoginModuleCheckBox().isSelected()){
			    	securitySettingsReviewPanel.add(caGridLoginModuleNameLabel, gridBagConstraints70);
			    	securitySettingsReviewPanel.add(caGridLoginModuleNameValueLabel, gridBagConstraints71);
			    	securitySettingsReviewPanel.add(sdkGridLoginSvcNameLabel, gridBagConstraints80);
			    	securitySettingsReviewPanel.add(sdkGridLoginSvcNameValueLabel, gridBagConstraints81);
		    	}
		    }
            
            securitySettingsReviewPanel.validate();
        //}
        return securitySettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();

    	//Security setting Validation
    	if (getEnableSecurityCheckBox().isSelected()){

    		if (ValidationUtils.isBlank(this.getCsmProjectNameField().getText())) {
    			result.add(new SimpleValidationMessage(CSM_PROJECT_NAME + " must not be blank.", Severity.ERROR, CSM_PROJECT_NAME));
    		}

    		//caGrid Auth Setting Validation
    		if (getEnableCaGridLoginModuleCheckBox().isSelected()){
   
    			if (ValidationUtils.isBlank(this.getCaGridLoginModuleNameField().getText())) {
    				result.add(new SimpleValidationMessage(CAGRID_LOGIN_MODULE_NAME + " must not be blank.", Severity.ERROR, CAGRID_LOGIN_MODULE_NAME));
    			}

    			if (ValidationUtils.isBlank(this.getSdkGridLoginSvcNameField().getText())) {
    				result.add(new SimpleValidationMessage(SDK_GRID_LOGIN_SERVICE_NAME + " must not be blank.", Severity.ERROR, SDK_GRID_LOGIN_SERVICE_NAME));
    			}
    		}
    	}

    	return result;
    }
    
    public void initValidation() {
       
        //Security
        ValidationComponentUtils.setMessageKey(getEnableInstanceLevelSecurityCheckBox(), INSTANCE_LEVEL_SECURITY);
        ValidationComponentUtils.setMandatory(getEnableInstanceLevelSecurityCheckBox(), true);
        ValidationComponentUtils.setMessageKey(getCsmProjectNameField(), CSM_PROJECT_NAME);
        ValidationComponentUtils.setMandatory(getCsmProjectNameField(), true);
        
        //caGrid Auth
        ValidationComponentUtils.setMessageKey(getCaGridLoginModuleNameField(), CAGRID_LOGIN_MODULE_NAME);
        ValidationComponentUtils.setMandatory(getCaGridLoginModuleNameField(), true);
        ValidationComponentUtils.setMessageKey(getSdkGridLoginSvcNameField(), SDK_GRID_LOGIN_SERVICE_NAME);
        ValidationComponentUtils.setMandatory(getSdkGridLoginSvcNameField(), true);
        
        toggleSecurityFields();
        toggleCaGridLoginFields();
        
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// Security properties
		propsMap.put("ENABLE_SECURITY", Boolean.valueOf(enableSecurityCheckBox.isSelected()).toString() );
		propsMap.put("ENABLE_INSTANCE_LEVEL_SECURITY", Boolean.valueOf(enableInstanceLevelSecurityCheckBox.isSelected()).toString() );
		propsMap.put("ENABLE_ATTRIBUTE_LEVEL_SECURITY", Boolean.valueOf(enableAttributeLevelSecurityCheckBox.isSelected()).toString() );
		propsMap.put("CSM_PROJECT_NAME", getCsmProjectNameField().getText());
		propsMap.put("CACHE_PROTECTION_ELEMENTS", Boolean.valueOf(cacheProtectionElementsCheckBox.isSelected()).toString() );
		
		// caGrid Authentication properties
		propsMap.put("ENABLE_GRID_LOGIN_MODULE", Boolean.valueOf(getEnableCaGridLoginModuleCheckBox().isSelected()).toString() );
		propsMap.put("ENABLE_CSM_LOGIN_MODULE", Boolean.valueOf(!getEnableCaGridLoginModuleCheckBox().isSelected()).toString() );
		propsMap.put("CAGRID_LOGIN_MODULE_NAME", getCaGridLoginModuleNameField().getText());
		propsMap.put("SDK_GRID_LOGIN_SERVICE_NAME", getSdkGridLoginSvcNameField().getText());
    	
    	return propsMap;
    }
}
