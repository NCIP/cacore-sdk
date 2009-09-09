package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
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

public final class WritableApiSettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;
	
	// Writable API Panel Validation Message Constants
	private static final String DATABASE_TYPE = "Database Type";
	private static final String CLM_PROJECT_NAME = "CLM Project Name";
	private static final String IDENTITY_GENERATOR_TAG = "Identity Generator Tag";

	// Writable API
	private JPanel writableApiSettingsPanel = null;
	private JPanel writableApiSettingsReviewPanel = null;
	private JPanel writableApiSettingsSubPanel = null;
	private JPanel clmSettingsSubPanel = null;
    
	// Writable API Settings Panel Component Definitions
    private JCheckBox  enableWritableApiExtensionCheckBox = null;
    private JComboBox  databaseTypeComboBox = null;
    private JTextField clmProjectNameField = null;
    private JTextField identityGeneratorTagField = null;
    private JCheckBox  enableCommonLoggingModuleCheckBox = null;
	
	public WritableApiSettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
    
    /**
     * This method initializes the Enable Writable API Extension CheckBox
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getEnableWritableApiExtensionCheckBox() {
        if (enableWritableApiExtensionCheckBox == null) {
        	enableWritableApiExtensionCheckBox = new JCheckBox();
        	enableWritableApiExtensionCheckBox.setToolTipText("Enable Writable API Extension?");
        	enableWritableApiExtensionCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	enableWritableApiExtensionCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_WRITABLE_API_EXTENSION")));
        	enableWritableApiExtensionCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableWritableApiExtensionCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleWritableApiFields();
					mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableWritableApiExtensionCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableWritableApiExtensionCheckBox;
    }
    
    public boolean isWritableApiExtensionEnabled(){
    	return getEnableWritableApiExtensionCheckBox().isSelected();
    }
    
    /**
     * This method initializes the Writable API Database Type Field
     * 
     * @return javax.swing.JTextField
     */
    private JComboBox getDatabaseTypeComboBox() {
        if (databaseTypeComboBox == null) {
        	databaseTypeComboBox = new JComboBox();
        	
        	Map<String,String> dbTypeOptionsMap = OptionsMapManager.getDbTypeOptionsMap();
        	if (dbTypeOptionsMap!=null){
            	Iterator<String> iter = dbTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		databaseTypeComboBox.addItem((String)iter.next());
            	}
            	
            	String databaseTypeValue = propsMgr.getDeployPropertyValue("DATABASE_TYPE");
            	String databaseTypeKey = OptionsMapManager.getValueToKeyMap().get(databaseTypeValue);
            	
            	if (databaseTypeKey != null && databaseTypeKey.length()>0)
            		databaseTypeComboBox.setSelectedItem(databaseTypeKey);
        	}
        	  		
        	databaseTypeComboBox.addActionListener(new ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                    	mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    }
                });
        	
        	databaseTypeComboBox.addFocusListener(new FocusChangeHandler());
        }
        return databaseTypeComboBox;
    }    
    
    /**
     * This method initializes the CLM Project Name Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getClmProjectNameField() {
        if (clmProjectNameField == null) {
        	clmProjectNameField = new JTextField();
        	clmProjectNameField.setText(propsMgr.getDeployPropertyValue("CLM_PROJECT_NAME"));
        	clmProjectNameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	clmProjectNameField.addFocusListener(new FocusChangeHandler());
        }
        return clmProjectNameField;
    }

    /**
     * This method initializes the Writable API Identity Generator Tag Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getIdentityGeneratorTagField() {
        if (identityGeneratorTagField == null) {
        	identityGeneratorTagField = new JTextField();
        	identityGeneratorTagField.setText(propsMgr.getDeployPropertyValue("IDENTITY_GENERATOR_TAG"));
        	identityGeneratorTagField.getDocument().addDocumentListener(new DocumentListener() {
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
        	identityGeneratorTagField.addFocusListener(new FocusChangeHandler());
        }
        return identityGeneratorTagField;
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
        	enableCommonLoggingModuleCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("ENABLE_COMMON_LOGGING_MODULE")));
        	enableCommonLoggingModuleCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	enableCommonLoggingModuleCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleWritableApiFields();
					mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	enableCommonLoggingModuleCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return enableCommonLoggingModuleCheckBox;
    }
    
    
    public boolean isCommonLoggingModuleEnabled(){
    	return getEnableCommonLoggingModuleCheckBox().isSelected();
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
    
    public void toggleWritableApiFields() {
		if (getEnableWritableApiExtensionCheckBox().isSelected()){
			getDatabaseTypeComboBox().setEnabled(true);
			
			getIdentityGeneratorTagField().setEnabled(true);
			getEnableCommonLoggingModuleCheckBox().setEnabled(true);
			
			if (getEnableCommonLoggingModuleCheckBox().isSelected()){
				getClmProjectNameField().setEnabled(true);
			} else{
				getClmProjectNameField().setEnabled(false);
			}
		} else{
			getDatabaseTypeComboBox().setEnabled(false);
			getClmProjectNameField().setEnabled(false);
			getIdentityGeneratorTagField().setEnabled(false);
			getEnableCommonLoggingModuleCheckBox().setEnabled(false);
		}
    }
    
	/**
	 * This method initializes writableApiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (writableApiSettingsPanel == null) {
			
		    //Writable API Settings Panel Label Definitions
		    JLabel enableWritableApiExtensionLabel = null;

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
			//gridBagConstraints11.weighty = 0.5D; //Non-standard 1.0 setting
			gridBagConstraints11.weightx = 1.0D;  
			gridBagConstraints11.gridwidth = 2;

			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridwidth = 3;
			//gridBagConstraints20.weighty = 1.0D; //Non-standard 1.0 setting
			gridBagConstraints20.weightx = 1.0D;  

		    enableWritableApiExtensionLabel = new JLabel();
		    enableWritableApiExtensionLabel.setText("Enable Writable API Extension?");
		    
			writableApiSettingsPanel = new JPanel();
			writableApiSettingsPanel.setLayout(new GridBagLayout());
			writableApiSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Writable API Module Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

			writableApiSettingsPanel.add(enableWritableApiExtensionLabel, gridBagConstraints10);
			writableApiSettingsPanel.add(getEnableWritableApiExtensionCheckBox(), gridBagConstraints11);
			writableApiSettingsPanel.add(getWritableApiSettingsSubPanel(), gridBagConstraints20);

			writableApiSettingsPanel.validate();
		}
		return writableApiSettingsPanel;
	}
    
	/**
	 * This method initializes writableApiSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getWritableApiSettingsSubPanel() {
		if (writableApiSettingsSubPanel == null) {
			
		    //Writable API Settings Panel Label Definitions
			JLabel databaseTypeLabel = null;
		    JLabel identityGeneratorTagLabel = null;
		    JLabel enableCommonLoggingModuleLabel = null;

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
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints21.gridwidth = 2;
			gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.weightx = 1.0D;  
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.insets = new java.awt.Insets(20, 2, 2, 2);
			gridBagConstraints30.gridx = 0;

			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints31.gridx = 1;
			gridBagConstraints31.insets = new java.awt.Insets(20, 2, 2, 2);
			gridBagConstraints31.gridy = 3;
			gridBagConstraints31.weighty = 1.0D;  // so that the Writable API options sub panel has priority
			gridBagConstraints31.weightx = 1.0D;  
			gridBagConstraints31.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.gridx = 0;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridwidth = 3;
			//gridBagConstraints40.weighty = 1.0D; //Non-standard 1.0 setting
			gridBagConstraints40.weightx = 1.0D;  
			
		    databaseTypeLabel = new JLabel();
		    databaseTypeLabel.setText("Select Database Type:");

		    identityGeneratorTagLabel = new JLabel();
		    identityGeneratorTagLabel.setText("Enter Hibernate Identity Generator Tag:");

		    enableCommonLoggingModuleLabel = new JLabel();
		    enableCommonLoggingModuleLabel.setText("Enable Common Logging Module (CLM)?");
		    
			writableApiSettingsSubPanel = new JPanel();
			writableApiSettingsSubPanel.setLayout(new GridBagLayout());
			writableApiSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Writable API Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

			writableApiSettingsSubPanel.add(databaseTypeLabel, gridBagConstraints10);
			writableApiSettingsSubPanel.add(getDatabaseTypeComboBox(), gridBagConstraints11);
			writableApiSettingsSubPanel.add(identityGeneratorTagLabel, gridBagConstraints20);
			writableApiSettingsSubPanel.add(getIdentityGeneratorTagField(), gridBagConstraints21);
			writableApiSettingsSubPanel.add(enableCommonLoggingModuleLabel, gridBagConstraints30);
			writableApiSettingsSubPanel.add(getEnableCommonLoggingModuleCheckBox(), gridBagConstraints31);
			writableApiSettingsSubPanel.add(getClmSettingsSubPanel(), gridBagConstraints40);

			writableApiSettingsSubPanel.validate();
		}
		return writableApiSettingsSubPanel;
	}
	
	/**
	 * This method initializes the caDSR Code Generation settings sub-panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getClmSettingsSubPanel() {
		if (clmSettingsSubPanel == null) {
			
		    JLabel clmProjectNameLabel = null;

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
		    
		    clmProjectNameLabel = new JLabel();
		    clmProjectNameLabel.setText("CLM Project Name:");

		    clmSettingsSubPanel = new JPanel();
		    clmSettingsSubPanel.setLayout(new GridBagLayout());
		    clmSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Common Logging Module Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    clmSettingsSubPanel.add(clmProjectNameLabel, gridBagConstraints10);
		    clmSettingsSubPanel.add(getClmProjectNameField(), gridBagConstraints11);
			
		    clmSettingsSubPanel.validate();
		}
		return clmSettingsSubPanel;
	}
	
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (writableApiSettingsReviewPanel == null) {
        	
		    JLabel enableWritableApiExtensionLabel = null;
		    JLabel enableWritableApiExtensionValueLabel = null;
		    JLabel clmProjectNameLabel = null;
		    JLabel clmProjectNameValueLabel = null;
		    JLabel identityGeneratorTagLabel = null;
		    JLabel identityGeneratorTagValueLabel = null;
		    JLabel enableCommonLoggingModuleLabel = null;
		    JLabel enableCommonLoggingModuleValueLabel = null;
		    JLabel databaseTypeLabel = null;
		    JLabel databaseTypeValueLabel = null;
        	
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
		    
		    enableWritableApiExtensionLabel = new JLabel();
		    enableWritableApiExtensionLabel.setText("Enable Writable API?");
		    enableWritableApiExtensionValueLabel = new JLabel();
		    enableWritableApiExtensionValueLabel.setText(Utils.convertToYesNo(getEnableWritableApiExtensionCheckBox()));
            
		    databaseTypeLabel = new JLabel();
		    databaseTypeLabel.setText("Database Type:");
		    databaseTypeValueLabel = new JLabel();
		    databaseTypeValueLabel.setText(OptionsMapManager.getDbTypeOptionsMap().get(getDatabaseTypeComboBox().getSelectedItem().toString()));
		    
		    clmProjectNameLabel = new JLabel();
		    clmProjectNameLabel.setText("CLM Project Name:");
		    clmProjectNameValueLabel = new JLabel();
		    clmProjectNameValueLabel.setText(getClmProjectNameField().getText());
		    
		    identityGeneratorTagLabel = new JLabel();
		    identityGeneratorTagLabel.setText("Identity Generator Tag:");
		    identityGeneratorTagValueLabel = new JLabel();
		    identityGeneratorTagValueLabel.setText(getIdentityGeneratorTagField().getText());
            
		    enableCommonLoggingModuleLabel = new JLabel();
		    enableCommonLoggingModuleLabel.setText("Enable Common Logging Module?");
		    enableCommonLoggingModuleValueLabel = new JLabel();
		    enableCommonLoggingModuleValueLabel.setText(Utils.convertToYesNo(getEnableCommonLoggingModuleCheckBox()));
            
            writableApiSettingsReviewPanel = new JPanel();
            writableApiSettingsReviewPanel.setLayout(new GridBagLayout());
            writableApiSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Writable API Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
            writableApiSettingsReviewPanel.add(enableWritableApiExtensionLabel, gridBagConstraints10);
            writableApiSettingsReviewPanel.add(enableWritableApiExtensionValueLabel, gridBagConstraints11);
            
            if (getEnableWritableApiExtensionCheckBox().isSelected()){
            	writableApiSettingsReviewPanel.add(clmProjectNameLabel, gridBagConstraints20);
            	writableApiSettingsReviewPanel.add(clmProjectNameValueLabel, gridBagConstraints21);
            	writableApiSettingsReviewPanel.add(identityGeneratorTagLabel, gridBagConstraints30);
            	writableApiSettingsReviewPanel.add(identityGeneratorTagValueLabel, gridBagConstraints31);
            	writableApiSettingsReviewPanel.add(enableCommonLoggingModuleLabel, gridBagConstraints40);
            	writableApiSettingsReviewPanel.add(enableCommonLoggingModuleValueLabel, gridBagConstraints41);  
            	writableApiSettingsReviewPanel.add(databaseTypeLabel, gridBagConstraints50);
            	writableApiSettingsReviewPanel.add(databaseTypeValueLabel, gridBagConstraints51);
            }
            
            writableApiSettingsReviewPanel.validate();
        //}
        return writableApiSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Writable API Settings Validation
    	if (getEnableWritableApiExtensionCheckBox().isSelected()){

    		if (ValidationUtils.isBlank(this.getIdentityGeneratorTagField().getText())) {
    			result.add(new SimpleValidationMessage(IDENTITY_GENERATOR_TAG + " must not be blank.", Severity.ERROR, IDENTITY_GENERATOR_TAG));
    		} 
    		
        	if (getEnableCommonLoggingModuleCheckBox().isSelected()){
    			if (ValidationUtils.isBlank(this.getClmProjectNameField().getText())) {
    				result.add(new SimpleValidationMessage(CLM_PROJECT_NAME + " must not be blank.", Severity.ERROR, CLM_PROJECT_NAME));
    			}
        	}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        
        //Writable API
        ValidationComponentUtils.setMessageKey(getDatabaseTypeComboBox(), DATABASE_TYPE);
        ValidationComponentUtils.setMandatory(getDatabaseTypeComboBox(), true);
        ValidationComponentUtils.setMessageKey(getClmProjectNameField(), CLM_PROJECT_NAME);
        ValidationComponentUtils.setMandatory(getClmProjectNameField(), true);
        ValidationComponentUtils.setMessageKey(getIdentityGeneratorTagField(), IDENTITY_GENERATOR_TAG);
        ValidationComponentUtils.setMandatory(getIdentityGeneratorTagField(), true);   
        
        toggleWritableApiFields();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// Writable API properties
		propsMap.put("ENABLE_WRITABLE_API_EXTENSION", Boolean.valueOf(getEnableWritableApiExtensionCheckBox().isSelected()).toString() );
		propsMap.put("DATABASE_TYPE", OptionsMapManager.getDbTypeOptionsMap().get(getDatabaseTypeComboBox().getSelectedItem().toString()));
		propsMap.put("CLM_PROJECT_NAME", getClmProjectNameField().getText());
		propsMap.put("IDENTITY_GENERATOR_TAG", getIdentityGeneratorTagField().getText());
		propsMap.put("ENABLE_COMMON_LOGGING_MODULE", Boolean.valueOf(getEnableCommonLoggingModuleCheckBox().isSelected()).toString() );
    	
    	return propsMap;
    }
}
