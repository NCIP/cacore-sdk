/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

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

public final class SdkInstallSettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;
	
	// Validation Message Constants
	private static final String SDK_INSTALL_DIR = "SDK installation directory";
	
	public SdkInstallSettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
	
	//SDK Install Settings Panel
	private JPanel sdkInstallSettingsPanel = null;
    
	//SDK Install Settings Panel Component Definitions
	private JTextField sdkInstallDirField = null;
    
    //SDK Install Panel Buttons
    private JButton sdkInstallDirButton = null;
    
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

            //sdkInstallDirField.setText(propsMgr.getDeployPropertyValue("SDK_INSTALL_DIR"));
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
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSettingsPanel() {
        if (sdkInstallSettingsPanel == null) {
        	
            // Project Settings Panel Label Definitions
            JLabel sdkInstallDirLabel = null;
        	
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
            sdkInstallDirLabel.setText("Select SDK Installation Directory:");
            sdkInstallDirLabel.setName("SDK Installation Directory");
            
            sdkInstallSettingsPanel = new JPanel();
            sdkInstallSettingsPanel.setLayout(new GridBagLayout());
            sdkInstallSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define SDK Install Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            sdkInstallSettingsPanel.add(sdkInstallDirLabel, gridBagConstraints20);
            sdkInstallSettingsPanel.add(getSdkInstallDirField(), gridBagConstraints21);
            sdkInstallSettingsPanel.add(getSdkInstallDirButton(), gridBagConstraints22);
            
            sdkInstallSettingsPanel.validate();
        }
        return sdkInstallSettingsPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Sdk Install Panel Settings Validation
   	
    	if (ValidationUtils.isBlank(this.getSdkInstallDirField().getText())) {
    		result.add(new SimpleValidationMessage(SDK_INSTALL_DIR + " must not be blank.", Severity.ERROR, SDK_INSTALL_DIR));
    	} else {
    		File file = new File(this.getSdkInstallDirField().getText());
    		if(!file.exists()){
    			result.add(new SimpleValidationMessage(SDK_INSTALL_DIR + " does not exist.  Please select the Home directory of an existing SDK Installation.", Severity.ERROR, SDK_INSTALL_DIR));
    		}
    	}
    	
    	return result;
    }
    
    public JPanel getSummaryPanel() {
    	return null;
    }
    
    public void initValidation() {
    	//SDK Install
        ValidationComponentUtils.setMessageKey(getSdkInstallDirField(), SDK_INSTALL_DIR);
        ValidationComponentUtils.setMandatory(getSdkInstallDirField(), true);
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	//propsMap.put("output.dir", (getProjectDirField().getText() + File.separator + "output").replace('\\', '/'));
    	propsMap.put("SDK_INSTALL_DIR", getSdkInstallDirField().getText().replace('\\', '/'));
    	
    	return propsMap;
    }
}
