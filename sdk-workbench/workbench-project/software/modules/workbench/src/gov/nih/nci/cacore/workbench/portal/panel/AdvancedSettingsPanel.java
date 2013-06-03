/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.viewer.DeployPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

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

public final class AdvancedSettingsPanel implements Panel, PanelValidator {
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
    public static final String APP_BASE_PATH_LINUX = "Linux Application Base Path";
    public static final String APP_BASE_PATH_WINDOWS = "Windows Application Base Path";
    public static final String CACHE_PATH = "Cache Path";

	
	public AdvancedSettingsPanel(DeployPropertiesViewer parentContainer,TabbedPanePropertiesValidator mainPanelValidator){
		this.parentContainer = parentContainer;
		this.mainPanelValidator = mainPanelValidator;
	}
	
	// App Server Panel
	private JPanel advancedSettingsPanel = null;
	private JPanel advancedSettingsReviewPanel = null;
    
	//Advanced Settings Panel Label Panel Component Definitions
    JTextField  appBasePathLinuxField = null; 
    JTextField  appBasePathWindowsField = null; 
    JTextField  cachePathField = null; 
    
    /**
     * This method initializes the Cache Path Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getCachePathField() {
    	if (cachePathField == null) {
    		cachePathField = new JTextField();
    		cachePathField.setToolTipText("Enter the absolute directory path of the EHCache");
    		cachePathField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("CACHE_PATH"));
    		
    		cachePathField.getDocument().addDocumentListener(new DocumentListener() {
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
    		cachePathField.addFocusListener(new FocusChangeHandler());
    	}
    	return cachePathField;
    }
    
    /**
     * This method initializes the Cache Path Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getAppBasePathLinuxField() {
    	if (appBasePathLinuxField == null) {
    		appBasePathLinuxField = new JTextField();
    		appBasePathLinuxField.setToolTipText("Enter the base Linux path where the application server and generated application artifacts will be deployed. Only relevant if the deployment Operating System (OS) is UNIX-based");
    		appBasePathLinuxField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("application.base.path.linux"));
    		
    		appBasePathLinuxField.getDocument().addDocumentListener(new DocumentListener() {
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
    		appBasePathLinuxField.addFocusListener(new FocusChangeHandler());
    	}
    	return appBasePathLinuxField;
    }
    
    /**
     * This method initializes the Cache Path Field
     * 
     * @return javax.swing.JTextField
     */         
    private JTextField getAppBasePathWindowsField() {
    	if (appBasePathWindowsField == null) {
    		appBasePathWindowsField = new JTextField();
    		appBasePathWindowsField.setToolTipText("Enter the base Windows path where the application server and generated application artifacts will be deployed. Only relevant if the deployment Operating System (OS) is Windows-based.");
    		appBasePathWindowsField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("application.base.path.windows"));
    		
    		appBasePathWindowsField.getDocument().addDocumentListener(new DocumentListener() {
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
    		appBasePathWindowsField.addFocusListener(new FocusChangeHandler());
    	}
    	return appBasePathWindowsField;
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
		if (advancedSettingsPanel == null) {
			
		    //Advanced Settings Panel Label Panel Label Definitions
		    JLabel appBasePathLinuxLabel = null;
		    JLabel appBasePathWindowsLabel = null;
		    JLabel cachePathLabel = null;
		    
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

			appBasePathLinuxLabel = new JLabel();
			appBasePathLinuxLabel.setText("Linux Application Base Path:");
			
			appBasePathWindowsLabel = new JLabel();
			appBasePathWindowsLabel.setText("Windows Application Base Path:");
			
			cachePathLabel = new JLabel();
			cachePathLabel.setText("EHCache Path:");

			advancedSettingsPanel = new JPanel();
			advancedSettingsPanel.setLayout(new GridBagLayout());
			advancedSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Advanced Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
			advancedSettingsPanel.add(appBasePathLinuxLabel, gridBagConstraints10);
			advancedSettingsPanel.add(getAppBasePathLinuxField(), gridBagConstraints11);
			advancedSettingsPanel.add(appBasePathWindowsLabel, gridBagConstraints20);
			advancedSettingsPanel.add(getAppBasePathWindowsField(), gridBagConstraints21);
			advancedSettingsPanel.add(cachePathLabel, gridBagConstraints30);
			advancedSettingsPanel.add(getCachePathField(), gridBagConstraints31);
			
			advancedSettingsPanel.validate();

		}
		return advancedSettingsPanel;
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
       // if (advancedSettingsReviewPanel == null) {
        	
		    //Advanced Settings Panel Label Panel Label Definitions
		    JLabel appBasePathLinuxLabel = null;
		    JLabel appBasePathLinuxValueLabel = null;
		    JLabel appBasePathWindowsLabel = null;
		    JLabel appBasePathWindowsValueLabel = null;
		    JLabel cachePathLabel = null;
		    JLabel cachePathValueLabel = null;
		    
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
			
			appBasePathLinuxLabel = new JLabel();
			appBasePathLinuxLabel.setText("Application Base Path Linux:");
			appBasePathLinuxValueLabel = new JLabel();
			appBasePathLinuxValueLabel.setText(getAppBasePathLinuxField().getText());
			
			appBasePathWindowsLabel = new JLabel();
			appBasePathWindowsLabel.setText("Application Base Path Windows:");
			appBasePathWindowsValueLabel = new JLabel();
			appBasePathWindowsValueLabel.setText(getAppBasePathWindowsField().getText());
		    
		    cachePathLabel = new JLabel();
		    cachePathLabel.setText("Cache Path:");
		    cachePathValueLabel = new JLabel();
		    cachePathValueLabel.setText(getCachePathField().getText());
            
            advancedSettingsReviewPanel = new JPanel();
            advancedSettingsReviewPanel.setLayout(new GridBagLayout());
            advancedSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Advanced Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            advancedSettingsReviewPanel.add(appBasePathLinuxLabel, gridBagConstraints10);
            advancedSettingsReviewPanel.add(appBasePathLinuxValueLabel, gridBagConstraints11);
            advancedSettingsReviewPanel.add(appBasePathWindowsLabel, gridBagConstraints20);
            advancedSettingsReviewPanel.add(appBasePathWindowsValueLabel, gridBagConstraints21);
            advancedSettingsReviewPanel.add(cachePathLabel, gridBagConstraints30);
            advancedSettingsReviewPanel.add(cachePathValueLabel, gridBagConstraints31);
            
            advancedSettingsReviewPanel.validate();
        //}
        return advancedSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
		//Advanced Settings components
		if (ValidationUtils.isBlank(this.getAppBasePathLinuxField().getText())) {
			result.add(new SimpleValidationMessage(APP_BASE_PATH_LINUX + " must not be blank.", Severity.ERROR, APP_BASE_PATH_LINUX));
		}

		if (ValidationUtils.isBlank(this.getAppBasePathWindowsField().getText())) {
			result.add(new SimpleValidationMessage(APP_BASE_PATH_WINDOWS + " must not be blank.", Severity.ERROR, APP_BASE_PATH_WINDOWS));
		}
		
		if (ValidationUtils.isBlank(this.getCachePathField().getText())) {
			result.add(new SimpleValidationMessage(CACHE_PATH + " must not be blank.", Severity.ERROR, CACHE_PATH));
		}
    	
    	return result;
    }
    
    public void initValidation() {
        // Advanced
        ValidationComponentUtils.setMessageKey(getAppBasePathLinuxField(), APP_BASE_PATH_LINUX);
        ValidationComponentUtils.setMandatory(getAppBasePathLinuxField(), true);
        
        ValidationComponentUtils.setMessageKey(getAppBasePathWindowsField(), APP_BASE_PATH_WINDOWS);
        ValidationComponentUtils.setMandatory(getAppBasePathWindowsField(), true);
        
        ValidationComponentUtils.setMessageKey(getCachePathField(), CACHE_PATH);
        ValidationComponentUtils.setMandatory(getCachePathField(), true);
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// Advanced Settings properties	
		propsMap.put("application.base.path.linux", getAppBasePathLinuxField().getText());
		propsMap.put("application.base.path.windows", getAppBasePathWindowsField().getText());
		propsMap.put("CACHE_PATH", getCachePathField().getText());
    	
    	return propsMap;
    }
}
