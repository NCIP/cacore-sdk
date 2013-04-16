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
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public final class ModelSettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;

	// Model Panel Validation Message Constants
	private static final String MODEL_FILE_PATH = "Model file";
	private static final String LOGICAL_MODEL_PACKAGE_NAME = "Logical Model package name";
	private static final String DATA_MODEL_PACKAGE_NAME = "Data Model package name";
	private static final String INCLUDE_PACKAGE_REGEX = "Include package Regular Expression";
	
	// Model Settings Panel
	private JPanel modelSettingsPanel = null;
	private JPanel modelSettingsReviewPanel = null;
    
    //Model Settings Panel Component Definitions
    private JTextField modelFileField = null;
    private JComboBox  modelFileTypeComboBox = null;
    private JTextField logicalModelField = null;
    private JTextField dataModelField = null;
    private JTextField includePackageField = null;
    private JTextField excludePackageField = null;
    private JTextField excludeNameField = null;
    private JTextField excludeNamespaceField = null;
    
    // Buttons
    private JButton modelFilePathButton = null;
	
	public ModelSettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getModelFilePathButton() {
        if (modelFilePathButton == null) {
        	modelFilePathButton = new JButton();
        	modelFilePathButton.setText("Browse");
        	modelFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	modelFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getModelFileField().getText();
                        String location = ResourceManager.promptFile(previous, FileFilters.XMI_UML_FILTER);
                        if (location != null && location.length() > 0) {
                        	getModelFileField().setText(location);
                        } else {
                        	getModelFileField().setText(previous);
                        }
                        mainPanelValidator.setDirty(true);
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return modelFilePathButton;
    }
    
    /**
     * This method initializes Model File Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getModelFileField() {
        if (modelFileField == null) {
        	modelFileField = new JTextField();
        	modelFileField.setToolTipText("Enter the absolute path of the file containing the object/data model");
        	modelFileField.setText(propsMgr.getDeployPropertyValue("MODEL_FILE_PATH"));

        	modelFileField.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    toggleModelFileType();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void removeUpdate(DocumentEvent e) {
                    toggleModelFileType();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }

                public void insertUpdate(DocumentEvent e) {
                    toggleModelFileType();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });
        	modelFileField.addFocusListener(new FocusChangeHandler());
        	
        	modelFileField.setEditable(false); // Only allow changes via the Model File Button
        	modelFileField.setEnabled(false);
        }
        return modelFileField;
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
    
    public void toggleModelFileType() {
    	if (getModelFileField().getText().endsWith(".xmi")){
    		getModelFileTypeComboBox().setSelectedItem("Enterprise Architect");
    	} else if (getModelFileField().getText().endsWith(".uml")){
    		getModelFileTypeComboBox().setSelectedItem("ArgoUML");
    	} else {
    		getModelFileTypeComboBox().setSelectedItem("");
    	}
    }

    /**
     * This method initializes Model File Type Combo Box
     * 
     * @return javax.swing.JComboBox
     */
    private JComboBox getModelFileTypeComboBox() {
        if (modelFileTypeComboBox == null) {
        	modelFileTypeComboBox = new JComboBox();
        	modelFileTypeComboBox.setToolTipText("Specify whether the object/data model file was created using Enterprise Architect (EA) or ArgoUML (ARGO)");
        	
            Map<String,String> modelFileTypeOptionsMap = OptionsMapManager.getModelFileTypeOptionsMap();
        	if (modelFileTypeOptionsMap != null){
            	Iterator<String> iter = modelFileTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		modelFileTypeComboBox.addItem((String)iter.next());
            	}

            	String modelFileTypeValue = propsMgr.getDeployPropertyValue("MODEL_FILE_TYPE");
            	String modelFileTypeKey = OptionsMapManager.getValueToKeyMap().get(modelFileTypeValue);
            	
            	if (modelFileTypeKey != null && modelFileTypeKey.length()>0)
            		modelFileTypeComboBox.setSelectedItem(modelFileTypeKey);
        	}
 
        	modelFileTypeComboBox.addFocusListener(new FocusChangeHandler());
        	
        	modelFileTypeComboBox.setEditable(false);
        	modelFileTypeComboBox.setEnabled(false);
        }
        return modelFileTypeComboBox;
    }
    
    /**
     * This method initializes Logical Model Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getLogicalModelField() {
        if (logicalModelField == null) {
        	logicalModelField = new JTextField();
        	logicalModelField.setToolTipText("Enter the name of the base package within the object/data model that contains the logical model data");
        	logicalModelField.setText(propsMgr.getDeployPropertyValue("LOGICAL_MODEL"));
        	logicalModelField.getDocument().addDocumentListener(new DocumentListener() {
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
        	logicalModelField.addFocusListener(new FocusChangeHandler());
        }
        return logicalModelField;
    }
    
    /**
     * This method initializes Data Model Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getDataModelField() {
        if (dataModelField == null) {
        	dataModelField = new JTextField();
        	dataModelField.setToolTipText("Enter the name of the base package within the object/data model that contains the data model data");
        	dataModelField.setText(propsMgr.getDeployPropertyValue("DATA_MODEL"));
        	dataModelField.getDocument().addDocumentListener(new DocumentListener() {
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
        	dataModelField.addFocusListener(new FocusChangeHandler());
        }
        return dataModelField;
    }
    
    /**
     * This method initializes the Include Package Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getIncludePackageField() {
        if (includePackageField == null) {
        	includePackageField = new JTextField();
        	includePackageField.setToolTipText("Enter the regular expression (java.util.regex) pattern(s) of any fully qualified package names within the object/data model that should be processed by the code generator.");
        	includePackageField.setText(propsMgr.getDeployPropertyValue("INCLUDE_PACKAGE"));
        	includePackageField.getDocument().addDocumentListener(new DocumentListener() {
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
        	includePackageField.addFocusListener(new FocusChangeHandler());
        }
        return includePackageField;
    }
    
    /**
     * This method initializes the Exclude Package Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getExcludePackageField() {
        if (excludePackageField == null) {
        	excludePackageField = new JTextField();
        	excludePackageField.setToolTipText("Enter the regular expression (java.util.regex) pattern(s) of the fully qualified package name(s) within the object/data model that should be ignored by the code generator.");
        	excludePackageField.setText(propsMgr.getDeployPropertyValue("EXCLUDE_PACKAGE"));
        	excludePackageField.getDocument().addDocumentListener(new DocumentListener() {
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
        	excludePackageField.addFocusListener(new FocusChangeHandler());
        }
        return excludePackageField;
    }
    
    /**
     * This method initializes the Exclude Name Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getExcludeNameField() {
        if (excludeNameField == null) {
        	excludeNameField = new JTextField();
        	excludeNameField.setToolTipText("Enter the regular expression (java.util.regex) pattern(s) of the fully qualified class name(s) within the object/data model that should be ignored by the code generator.");
        	excludeNameField.setText(propsMgr.getDeployPropertyValue("EXCLUDE_NAME"));
        	excludeNameField.getDocument().addDocumentListener(new DocumentListener() {
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
        	excludeNameField.addFocusListener(new FocusChangeHandler());
        }
        return excludeNameField;
    }

    /**
     * This method initializes the Exclude Namespace Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getExcludeNamespaceField() {
        if (excludeNamespaceField == null) {
        	excludeNamespaceField = new JTextField();
        	excludeNamespaceField.setToolTipText("Enter the regular expression (java.util.regex) namespace pattern of the fully qualified package name(s) within the object/data model that should be ignored by the code generator.");
        	excludeNamespaceField.setText(propsMgr.getDeployPropertyValue("EXCLUDE_NAMESPACE"));
        	excludeNamespaceField.getDocument().addDocumentListener(new DocumentListener() {
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
        	excludeNamespaceField.addFocusListener(new FocusChangeHandler());
        }
        return excludeNamespaceField;
    }
    
	/**
	 * This method initializes modelSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (modelSettingsPanel == null) {

		    //Model Settings Panel Label Definitions
		    JLabel modelFileLabel = null;
		    JLabel modelFileTypeLabel = null;
		    JLabel logicalModelLabel = null;
		    JLabel dataModelLabel = null;
		    JLabel includePackageLabel = null;
		    JLabel excludePackageLabel = null;
		    JLabel excludeNameLabel = null;
		    JLabel excludeNamespaceLabel = null;
			
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
            gridBagConstraints21.gridwidth = 1;
            gridBagConstraints21.weightx = 0.3D;
            
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
            gridBagConstraints51.gridwidth = 2;
            gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints51.weightx = 1.0D;
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

            modelFileLabel = new JLabel();
            modelFileLabel.setText("Enter the Model file path:");
            
            modelFileTypeLabel = new JLabel();
            modelFileTypeLabel.setText("Enter the Model file type:");
            
            logicalModelLabel = new JLabel();
            logicalModelLabel.setText("Enter the 'Logical Model' package name:");
            
            dataModelLabel = new JLabel();
            dataModelLabel.setText("Enter the 'Data Model' package name:");
            
            includePackageLabel = new JLabel();
            includePackageLabel.setText("Enter the 'Include Package' regex:");
            
            excludePackageLabel = new JLabel();
            excludePackageLabel.setText("Enter the 'Exclude Package' regex:");
            
            excludeNameLabel = new JLabel();
            excludeNameLabel.setText("Enter the 'Exclude Class Name' regex:");
            
            excludeNamespaceLabel = new JLabel();
            excludeNamespaceLabel.setText("Enter the 'Exclude Namespace' regex:");
            
            modelSettingsPanel = new JPanel();
            modelSettingsPanel.setLayout(new GridBagLayout());
            modelSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Model Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            modelSettingsPanel.add(modelFileLabel, gridBagConstraints10);
            modelSettingsPanel.add(getModelFileField(), gridBagConstraints11);
            modelSettingsPanel.add(getModelFilePathButton(), gridBagConstraints12);
            modelSettingsPanel.add(modelFileTypeLabel, gridBagConstraints20);
            modelSettingsPanel.add(getModelFileTypeComboBox(), gridBagConstraints21);
            modelSettingsPanel.add(logicalModelLabel, gridBagConstraints30);
            modelSettingsPanel.add(getLogicalModelField(), gridBagConstraints31);
            modelSettingsPanel.add(dataModelLabel, gridBagConstraints40);
            modelSettingsPanel.add(getDataModelField(), gridBagConstraints41);
            modelSettingsPanel.add(includePackageLabel, gridBagConstraints50);
            modelSettingsPanel.add(getIncludePackageField(), gridBagConstraints51);
            modelSettingsPanel.add(excludePackageLabel, gridBagConstraints60);
            modelSettingsPanel.add(getExcludePackageField(), gridBagConstraints61);
            modelSettingsPanel.add(excludeNameLabel, gridBagConstraints70);
            modelSettingsPanel.add(getExcludeNameField(), gridBagConstraints71);
            modelSettingsPanel.add(excludeNamespaceLabel, gridBagConstraints80);
            modelSettingsPanel.add(getExcludeNamespaceField(), gridBagConstraints81);             
            
            modelSettingsPanel.validate();
		}
		return modelSettingsPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (modelSettingsReviewPanel == null) {
        	
		    //Model Settings Panel Label Definitions
		    JLabel modelFileLabel = null;
		    JLabel modelFileValueLabel = null;
		    JLabel modelFileTypeLabel = null;
		    JLabel modelFileTypeValueLabel = null;
		    JLabel logicalModelLabel = null;
		    JLabel logicalModelValueLabel = null;
		    JLabel dataModelLabel = null;
		    JLabel dataModelValueLabel = null;
		    JLabel includePackageLabel = null;
		    JLabel includePackageValueLabel = null;
		    JLabel excludePackageLabel = null;
		    JLabel excludePackageValueLabel = null;
		    JLabel excludeNameLabel = null;
		    JLabel excludeNameValueLabel = null;
		    JLabel excludeNamespaceLabel = null;
		    JLabel excludeNamespaceValueLabel = null;
        	
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
                        
            modelFileLabel = new JLabel();
            modelFileLabel.setText("Model File Path:");
            modelFileValueLabel = new JLabel();
            modelFileValueLabel.setText(getModelFileField().getText());
            
            modelFileTypeLabel = new JLabel();
            modelFileTypeLabel.setText("Model File Type:");
            modelFileTypeValueLabel = new JLabel();
            modelFileTypeValueLabel.setText(OptionsMapManager.getModelFileTypeOptionsMap().get(getModelFileTypeComboBox().getSelectedItem().toString()));
            
            logicalModelLabel = new JLabel();
            logicalModelLabel.setText("'Logical Model' Package Name:");
            logicalModelValueLabel = new JLabel();
            logicalModelValueLabel.setText(getLogicalModelField().getText());
            
            dataModelLabel = new JLabel();
            dataModelLabel.setText("'Data Model' Package Name:");
            dataModelValueLabel = new JLabel();
            dataModelValueLabel.setText(getDataModelField().getText());
            
            includePackageLabel = new JLabel();
            includePackageLabel.setText("'Include Package' regex:");
            includePackageValueLabel = new JLabel();
            includePackageValueLabel.setText(getIncludePackageField().getText());
            
            excludePackageLabel = new JLabel();
            excludePackageLabel.setText("'Exclude Package' regex:");
            excludePackageValueLabel = new JLabel();
            excludePackageValueLabel.setText(getExcludePackageField().getText());
            
            excludeNameLabel = new JLabel();
            excludeNameLabel.setText("'Exclude Class Name' regex:");
            excludeNameValueLabel = new JLabel();
            excludeNameValueLabel.setText(getExcludeNameField().getText());
            
            excludeNamespaceLabel = new JLabel();
            excludeNamespaceLabel.setText("'Exclude Namespace' regex:");
            excludeNamespaceValueLabel = new JLabel();
            excludeNamespaceValueLabel.setText(getExcludeNamespaceField().getText());
            
            modelSettingsReviewPanel = new JPanel();
            modelSettingsReviewPanel.setLayout(new GridBagLayout());
            modelSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Model Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            modelSettingsReviewPanel.add(modelFileLabel, gridBagConstraints10);
            modelSettingsReviewPanel.add(modelFileValueLabel, gridBagConstraints11);
            modelSettingsReviewPanel.add(modelFileTypeLabel, gridBagConstraints20);
            modelSettingsReviewPanel.add(modelFileTypeValueLabel, gridBagConstraints21);
            modelSettingsReviewPanel.add(logicalModelLabel, gridBagConstraints30);
            modelSettingsReviewPanel.add(logicalModelValueLabel, gridBagConstraints31);
            modelSettingsReviewPanel.add(dataModelLabel, gridBagConstraints40);
            modelSettingsReviewPanel.add(dataModelValueLabel, gridBagConstraints41);
            modelSettingsReviewPanel.add(includePackageLabel, gridBagConstraints50);
            modelSettingsReviewPanel.add(includePackageValueLabel, gridBagConstraints51);
            modelSettingsReviewPanel.add(excludePackageLabel, gridBagConstraints60);
            modelSettingsReviewPanel.add(excludePackageValueLabel, gridBagConstraints61);
            modelSettingsReviewPanel.add(excludeNameLabel, gridBagConstraints70);
            modelSettingsReviewPanel.add(excludeNameValueLabel, gridBagConstraints71);
            modelSettingsReviewPanel.add(excludeNamespaceLabel, gridBagConstraints80);
            modelSettingsReviewPanel.add(excludeNamespaceValueLabel, gridBagConstraints81);             
            
            modelSettingsReviewPanel.validate();
        //}
        return modelSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Model Settings Validation
    	if (ValidationUtils.isBlank(this.getModelFileField().getText())) {
    		result.add(new SimpleValidationMessage(MODEL_FILE_PATH + " path must not be blank.", Severity.ERROR, MODEL_FILE_PATH));
    	} else {
    		File file = new File(this.getModelFileField().getText());
    		if(!file.exists()){
    			result.add(new SimpleValidationMessage(MODEL_FILE_PATH + " does not exist.  Please select or enter a valid absolute path to the model file.", Severity.ERROR, MODEL_FILE_PATH));
    		}
    		
    		if (!this.getModelFileField().getText().endsWith("xmi") && !this.getModelFileField().getText().endsWith("uml")){
    			result.add(new SimpleValidationMessage(MODEL_FILE_PATH + " must refer to an XMI (*.xmi) or Argo UML (*.uml) file.", Severity.ERROR, MODEL_FILE_PATH));
    		}
    	}

    	if (ValidationUtils.isBlank(this.getLogicalModelField().getText())) {
    		result.add(new SimpleValidationMessage(LOGICAL_MODEL_PACKAGE_NAME + " must not be blank.", Severity.ERROR, LOGICAL_MODEL_PACKAGE_NAME));
    	}

    	if (ValidationUtils.isBlank(this.getDataModelField().getText())) {
    		result.add(new SimpleValidationMessage(DATA_MODEL_PACKAGE_NAME + " must not be blank.", Severity.ERROR, DATA_MODEL_PACKAGE_NAME));
    	}

    	if (ValidationUtils.isBlank(this.getIncludePackageField().getText())) {
    		result.add(new SimpleValidationMessage(INCLUDE_PACKAGE_REGEX + " must not be blank.", Severity.ERROR, INCLUDE_PACKAGE_REGEX));
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        ValidationComponentUtils.setMessageKey(getModelFileField(), MODEL_FILE_PATH);
        ValidationComponentUtils.setMandatory(getModelFileField(), true);
        ValidationComponentUtils.setMessageKey(getLogicalModelField(), LOGICAL_MODEL_PACKAGE_NAME);
        ValidationComponentUtils.setMandatory(getLogicalModelField(), true);
        ValidationComponentUtils.setMessageKey(getDataModelField(), DATA_MODEL_PACKAGE_NAME);
        ValidationComponentUtils.setMandatory(getDataModelField(), true);
        ValidationComponentUtils.setMessageKey(getIncludePackageField(), INCLUDE_PACKAGE_REGEX);
        ValidationComponentUtils.setMandatory(getIncludePackageField(), true);
        
    	toggleModelFileType();
    }
    
    public String getModelFilePath(){
    	return getModelFileField().getText().replace('\\', '/');
    }
    
    public String getModelFileName(){
    	String modelFilePath = getModelFileField().getText().replace('\\', '/');
    	
    	return modelFilePath.substring(modelFilePath.lastIndexOf('/')+1);
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// NOTE: this property is currently being overriden to point to the project generation 
		// "models" sub-directory, as we are now copying the model file there
		propsMap.put("MODEL_FILE_PATH", getModelFileField().getText().replace('\\', '/'));
		propsMap.put("MODEL_FILE_TYPE", OptionsMapManager.getModelFileTypeOptionsMap().get(getModelFileTypeComboBox().getSelectedItem().toString()));
		propsMap.put("LOGICAL_MODEL", getLogicalModelField().getText());
		propsMap.put("DATA_MODEL", getDataModelField().getText());
		propsMap.put("INCLUDE_PACKAGE", getIncludePackageField().getText());
		propsMap.put("EXCLUDE_PACKAGE", getExcludePackageField().getText());
		propsMap.put("EXCLUDE_NAME", getExcludeNameField().getText());
		propsMap.put("EXCLUDE_NAMESPACE", getExcludeNamespaceField().getText());
    	
    	return propsMap;
    }
}
