package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.TabbedPanePropertiesValidator;
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

public final class CodegenSettingsPanel implements Panel, PanelValidator {
	
	WorkbenchPropertiesManager propsMgr = null;
	TabbedPanePropertiesValidator mainPanelValidator = null;
	
	// Code Generation Panel Validation Message Constants
	private static final String CADSR_CONNECTION_URL = "caDSR Connection URL";
    
	// Code Generation Settings Panel
	private JPanel codeGenSettingsPanel = null;
	private JPanel codeGenSettingsReviewPanel = null;
	
	//private JPanel codeGenSettingsPanel = null;
	private JPanel ormCodeGenSettingsSubPanel = null;
	private JPanel xsdCodeGenSettingsSubPanel = null;
	private JPanel caDsrCodeGenSettingsSubPanel = null;
	
    
	//Code Generation Settings Panel Component Definitions
    private JCheckBox validateLogicalModelCheckBox = null;
    private JCheckBox validateModelMappingCheckBox = null;
    private JCheckBox validateGmeTagsCheckBox = null;
    private JCheckBox generateHibernateMappingCheckBox = null;
    private JCheckBox generateBeansCheckBox = null;
    private JCheckBox generateCastorMappingCheckBox = null;
    private JCheckBox generateXsdCheckBox = null;
    private JCheckBox generateXsdWithGmeTagsCheckBox = null;
    private JCheckBox generateXsdWithPermissibleValuesCheckBox = null;
    private JCheckBox generateWsddCheckBox = null;
    private JCheckBox generateHibernateValidatorCheckBox = null;
    private JTextField caDsrConnectionUrlField = null;
    
	public CodegenSettingsPanel(WorkbenchPropertiesManager propsMgr,TabbedPanePropertiesValidator mainPanelValidator){
		this.propsMgr=propsMgr;
		this.mainPanelValidator=mainPanelValidator;
	}
	
    public void toggleXsdFields() {
		if (getGenerateXsdCheckBox().isSelected()){
			validateGmeTagsCheckBox.setEnabled(true);
			generateCastorMappingCheckBox.setEnabled(true);
			generateXsdWithGmeTagsCheckBox.setEnabled(true);
			generateXsdWithPermissibleValuesCheckBox.setEnabled(true);
		} else{
			validateGmeTagsCheckBox.setEnabled(false);
			generateCastorMappingCheckBox.setEnabled(false);
			generateXsdWithGmeTagsCheckBox.setEnabled(false);
			generateXsdWithPermissibleValuesCheckBox.setEnabled(false);
		}
    }
    
	
    public void toggleCaDsrField() {
		if (getGenerateXsdWithPermissibleValuesCheckBox().isSelected() || getGenerateHibernateValidatorCheckBox().isSelected()){
			getCaDsrConnectionUrlField().setEnabled(true);
			return;
		} 
		
		getCaDsrConnectionUrlField().setEnabled(false);
    }    
    
    /**
     * 
     *     /**
     * This method initializes the caDSR Connection URL Field
     * 
     * @return javax.swing.JTextField
     */
    private JTextField getCaDsrConnectionUrlField() {
        if (caDsrConnectionUrlField == null) {
        	caDsrConnectionUrlField = new JTextField();
        	caDsrConnectionUrlField.setText(propsMgr.getDeployPropertyValue("CADSR_CONNECTION_URL"));
        	caDsrConnectionUrlField.getDocument().addDocumentListener(new DocumentListener() {
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
        	caDsrConnectionUrlField.addFocusListener(new FocusChangeHandler());
        }
        return caDsrConnectionUrlField;
    }
    
    /*
     * This method initializes the Validate Logical Model Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getValidateLogicalModelCheckBox() {
        if (validateLogicalModelCheckBox == null) {
        	validateLogicalModelCheckBox = new JCheckBox();
        	validateLogicalModelCheckBox.setToolTipText("Validate the Logical Model prior to generating the application?");
        	validateLogicalModelCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	validateLogicalModelCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("VALIDATE_LOGICAL_MODEL")));
        	validateLogicalModelCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	validateLogicalModelCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	validateLogicalModelCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return validateLogicalModelCheckBox;
    }
    
    /**
     * This method initializes the Validate Model Mapping Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getValidateModelMappingCheckBox() {
        if (validateModelMappingCheckBox == null) {
        	validateModelMappingCheckBox = new JCheckBox();
        	validateModelMappingCheckBox.setToolTipText("Validate the Model Mapping prior to generating the application?");
        	validateModelMappingCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	validateModelMappingCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("VALIDATE_MODEL_MAPPING")));
        	validateModelMappingCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	validateModelMappingCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	validateModelMappingCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return validateModelMappingCheckBox;
    }
    
    /**
     * This method initializes the Validate Model Mapping Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getValidateGmeTagsCheckBox() {
        if (validateGmeTagsCheckBox == null) {
        	validateGmeTagsCheckBox = new JCheckBox();
        	validateGmeTagsCheckBox.setToolTipText("Validate GME tags in the logical model prior to generating the application?");
        	validateGmeTagsCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	validateGmeTagsCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("VALIDATE_GME_TAGS")));
        	validateGmeTagsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	validateGmeTagsCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	validateGmeTagsCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return validateGmeTagsCheckBox;
    } 
    
    /**
     * This method initializes the Generate Beans Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateBeansCheckBox() {
        if (generateBeansCheckBox == null) {
        	generateBeansCheckBox = new JCheckBox();
        	generateBeansCheckBox.setToolTipText("Generate domain Java Beans?");
        	generateBeansCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateBeansCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_BEANS")));
        	generateBeansCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateBeansCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateBeansCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateBeansCheckBox;
    } 
    
    /**
     * This method initializes the Generate Hibernate Mapping File Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateHibernateMappingCheckBox() {
        if (generateHibernateMappingCheckBox == null) {
        	generateHibernateMappingCheckBox = new JCheckBox();
        	generateHibernateMappingCheckBox.setToolTipText("Generate Hibernate mapping files?");
        	generateHibernateMappingCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateHibernateMappingCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_HIBERNATE_MAPPING")));
        	generateHibernateMappingCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateHibernateMappingCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateHibernateMappingCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateHibernateMappingCheckBox;
    } 
    
    /**
     * This method initializes the Generate Castor Mapping File Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateCastorMappingCheckBox() {
        if (generateCastorMappingCheckBox == null) {
        	generateCastorMappingCheckBox = new JCheckBox();
        	generateCastorMappingCheckBox.setToolTipText("Generate Castor Mapping files?");
        	generateCastorMappingCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateCastorMappingCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_CASTOR_MAPPING")));
        	generateCastorMappingCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateCastorMappingCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateCastorMappingCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateCastorMappingCheckBox;
    }
    
    /**
     * This method initializes the Generate XSD Schema Files Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateXsdCheckBox() {
        if (generateXsdCheckBox == null) {
        	generateXsdCheckBox = new JCheckBox();
        	generateXsdCheckBox.setToolTipText("Generate XSD files?");
        	generateXsdCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateXsdCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_XSD")));
        	generateXsdCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateXsdCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleXsdFields();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateXsdCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateXsdCheckBox;
    }
    
    /**
     * This method initializes the Generate XSD Files Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateXsdWithGmeTagsCheckBox() {
        if (generateXsdWithGmeTagsCheckBox == null) {
        	generateXsdWithGmeTagsCheckBox = new JCheckBox();
        	generateXsdWithGmeTagsCheckBox.setToolTipText("Generate XSD files with GME Tags?");
        	generateXsdWithGmeTagsCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateXsdWithGmeTagsCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_XSD_WITH_GME_TAGS")));
        	generateXsdWithGmeTagsCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateXsdWithGmeTagsCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateXsdWithGmeTagsCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateXsdWithGmeTagsCheckBox;
    }
    
    /**
     * This method initializes the Generate XSD with Permissible Values Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateXsdWithPermissibleValuesCheckBox() {
        if (generateXsdWithPermissibleValuesCheckBox == null) {
        	generateXsdWithPermissibleValuesCheckBox = new JCheckBox();
        	generateXsdWithPermissibleValuesCheckBox.setToolTipText("Generate XSD files using Permissible Values?");
        	generateXsdWithPermissibleValuesCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateXsdWithPermissibleValuesCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_XSD_WITH_PERMISSIBLE_VALUES")));
        	generateXsdWithPermissibleValuesCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateXsdWithPermissibleValuesCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleCaDsrField();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateXsdWithPermissibleValuesCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateXsdWithPermissibleValuesCheckBox;
    }
    
    /**
     * This method initializes the Generate XSD with Permissible Values Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateWsddCheckBox() {
        if (generateWsddCheckBox == null) {
        	generateWsddCheckBox = new JCheckBox();
        	generateWsddCheckBox.setToolTipText("Generate Web Service Deployment Descriptor (Apache Axis library) files?");
        	generateWsddCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateWsddCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_WSDD")));
        	generateWsddCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateWsddCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateWsddCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateWsddCheckBox;
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
     * This method initializes the Generate XSD with Permissible Values Check Box
     * 
     * @return javax.swing.JCheckBox
     */
    private JCheckBox getGenerateHibernateValidatorCheckBox() {
        if (generateHibernateValidatorCheckBox == null) {
        	generateHibernateValidatorCheckBox = new JCheckBox();
        	generateHibernateValidatorCheckBox.setToolTipText("Generate Hibernate Validator?");
        	generateHibernateValidatorCheckBox.setHorizontalAlignment(SwingConstants.LEADING);
        	generateHibernateValidatorCheckBox.setSelected(Boolean.parseBoolean(propsMgr.getDeployPropertyValue("GENERATE_HIBERNATE_VALIDATOR")));
        	generateHibernateValidatorCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
			
        	generateHibernateValidatorCheckBox.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					toggleCaDsrField();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
				}
        	});

        	generateHibernateValidatorCheckBox.addFocusListener(new FocusChangeHandler());
        }
        return generateHibernateValidatorCheckBox;
    }

   
    
	/**
	 * This method initializes codeGenSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getSettingsPanel() {
		if (codeGenSettingsPanel == null) {

		    //Code Generation Settings Panel Label Definitions
		    JLabel validateLogicalModelLabel = null;
		    JLabel generateBeansLabel = null;
		    JLabel generateWsddLabel = null;
			
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
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.weightx = 1.0;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridwidth = 3;
			//gridBagConstraints40.weighty = 1.0D;
			gridBagConstraints40.gridx = 0;

			GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints50.gridy = 5;
			gridBagConstraints50.weightx = 1.0;
			gridBagConstraints50.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints50.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints50.gridwidth = 3;
			//gridBagConstraints50.weighty = 1.0D;
			gridBagConstraints50.gridx = 0;
			

			GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
			gridBagConstraints60.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints60.gridy = 6;
			gridBagConstraints60.weightx = 1.0;
			gridBagConstraints60.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints60.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints60.gridwidth = 3;
			//gridBagConstraints60.weighty = 1.0D;
			gridBagConstraints60.gridx = 0;

		    validateLogicalModelLabel = new JLabel();
		    validateLogicalModelLabel.setText("Validate Logical Model?");
		    
		    generateBeansLabel = new JLabel();
		    generateBeansLabel.setText("Generate domain Java Beans?");
		    
		    generateWsddLabel = new JLabel();
		    generateWsddLabel.setText("Generate WSDD?");
            
            codeGenSettingsPanel = new JPanel();
            codeGenSettingsPanel.setLayout(new GridBagLayout());
            codeGenSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Code Generation Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
		    codeGenSettingsPanel.add(validateLogicalModelLabel, gridBagConstraints10);
		    codeGenSettingsPanel.add(getValidateLogicalModelCheckBox(), gridBagConstraints11);
			codeGenSettingsPanel.add(generateBeansLabel, gridBagConstraints20);
			codeGenSettingsPanel.add(getGenerateBeansCheckBox(), gridBagConstraints21);
			codeGenSettingsPanel.add(generateWsddLabel, gridBagConstraints30);
			codeGenSettingsPanel.add(getGenerateWsddCheckBox(), gridBagConstraints31);
			codeGenSettingsPanel.add(getOrmCodeGenSettingsPanel(), gridBagConstraints40);
			codeGenSettingsPanel.add(getXsdCodeGenSettingsSubPanel(), gridBagConstraints50);
			codeGenSettingsPanel.add(getCaDsrCodeGenSettingsSubPanel(), gridBagConstraints60);
            
            codeGenSettingsPanel.validate();
		}
		return codeGenSettingsPanel;
	}
	
	/**
	 * This method initializes the Code Generation settings panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOrmCodeGenSettingsPanel() {
		if (ormCodeGenSettingsSubPanel == null) {
			
		    JLabel validateModelMappingLabel = null;
		    JLabel generateHibernateMappingLabel = null;
		    JLabel generateHibernateValidatorLabel = null;

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

		    validateModelMappingLabel = new JLabel();
		    validateModelMappingLabel.setText("Validate Model Mapping?");

		    generateHibernateMappingLabel = new JLabel();
		    generateHibernateMappingLabel.setText("Generate Hibernate Mapping Files?");

		    generateHibernateValidatorLabel = new JLabel();
		    generateHibernateValidatorLabel.setText("Generate Hibernate Validator?");

		    ormCodeGenSettingsSubPanel = new JPanel();
		    ormCodeGenSettingsSubPanel.setLayout(new GridBagLayout());
		    ormCodeGenSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Object Relational Mapping Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    ormCodeGenSettingsSubPanel.add(validateModelMappingLabel, gridBagConstraints10);
			ormCodeGenSettingsSubPanel.add(getValidateModelMappingCheckBox(), gridBagConstraints11);
			ormCodeGenSettingsSubPanel.add(generateHibernateMappingLabel, gridBagConstraints20);
			ormCodeGenSettingsSubPanel.add(getGenerateHibernateMappingCheckBox(), gridBagConstraints21);
			ormCodeGenSettingsSubPanel.add(generateHibernateValidatorLabel, gridBagConstraints30);
			ormCodeGenSettingsSubPanel.add(getGenerateHibernateValidatorCheckBox(), gridBagConstraints31);
			
		    ormCodeGenSettingsSubPanel.validate();
		}
		return ormCodeGenSettingsSubPanel;
	}
	
	/**
	 * This method initializes the XSD Code Generation settings sub-panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getXsdCodeGenSettingsSubPanel() {
		if (xsdCodeGenSettingsSubPanel == null) {
			
		    JLabel validateGmeTagsLabel = null;
		    JLabel generateCastorMappingLabel = null;
		    JLabel generateXsdLabel = null;
		    JLabel generateXsdWithGmeTagsLabel = null;
		    JLabel generateXsdWithPermissibleValuesLabel = null;

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
			gridBagConstraints51.gridy = 5;
			gridBagConstraints51.weightx = 1.0;
			gridBagConstraints51.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints51.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints51.gridwidth = 2;
			gridBagConstraints51.weighty = 1.0D;
			gridBagConstraints51.gridx = 1;
			
		    generateXsdLabel = new JLabel();
		    generateXsdLabel.setText("Generate XSD's?");
		    
		    validateGmeTagsLabel = new JLabel();
		    validateGmeTagsLabel.setText("Validate GME Tags?");
		    
		    generateCastorMappingLabel = new JLabel();
		    generateCastorMappingLabel.setText("Generate Castor Mapping files?");

		    generateXsdWithGmeTagsLabel = new JLabel();
		    generateXsdWithGmeTagsLabel.setText("Generate XSD's with GME tags?");
		    
		    generateXsdWithPermissibleValuesLabel = new JLabel();
		    generateXsdWithPermissibleValuesLabel.setText("Generate XSD's with Permissible Values?");

		    xsdCodeGenSettingsSubPanel = new JPanel();
		    xsdCodeGenSettingsSubPanel.setLayout(new GridBagLayout());
		    xsdCodeGenSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "XSD Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    xsdCodeGenSettingsSubPanel.add(generateXsdLabel, gridBagConstraints10);
		    xsdCodeGenSettingsSubPanel.add(getGenerateXsdCheckBox(), gridBagConstraints11);
		    xsdCodeGenSettingsSubPanel.add(generateCastorMappingLabel, gridBagConstraints20);
		    xsdCodeGenSettingsSubPanel.add(getGenerateCastorMappingCheckBox(), gridBagConstraints21);
		    xsdCodeGenSettingsSubPanel.add(generateXsdWithGmeTagsLabel, gridBagConstraints30);
		    xsdCodeGenSettingsSubPanel.add(getGenerateXsdWithGmeTagsCheckBox(), gridBagConstraints31); 
		    xsdCodeGenSettingsSubPanel.add(validateGmeTagsLabel, gridBagConstraints40);
		    xsdCodeGenSettingsSubPanel.add(getValidateGmeTagsCheckBox(), gridBagConstraints41);
		    xsdCodeGenSettingsSubPanel.add(generateXsdWithPermissibleValuesLabel, gridBagConstraints50);
		    xsdCodeGenSettingsSubPanel.add(getGenerateXsdWithPermissibleValuesCheckBox(), gridBagConstraints51);
			
		    xsdCodeGenSettingsSubPanel.validate();
		}
		return xsdCodeGenSettingsSubPanel;
	}
	
	/**
	 * This method initializes the caDSR Code Generation settings sub-panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCaDsrCodeGenSettingsSubPanel() {
		if (caDsrCodeGenSettingsSubPanel == null) {
			
		    JLabel caDsrConnectionUrlLabel = null;

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

		    caDsrConnectionUrlLabel = new JLabel();
		    caDsrConnectionUrlLabel.setText("caDSR Connection URL:");

		    caDsrCodeGenSettingsSubPanel = new JPanel();
		    caDsrCodeGenSettingsSubPanel.setLayout(new GridBagLayout());
		    caDsrCodeGenSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "caDSR Connection Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    caDsrCodeGenSettingsSubPanel.add(caDsrConnectionUrlLabel, gridBagConstraints10);
		    caDsrCodeGenSettingsSubPanel.add(getCaDsrConnectionUrlField(), gridBagConstraints11);
			
		    caDsrCodeGenSettingsSubPanel.validate();
		}
		return caDsrCodeGenSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (codeGenSettingsReviewPanel == null) {
        	
		    //Code Generation Settings Panel Label Definitions
		    JLabel validateLogicalModelLabel = null;
		    JLabel validateLogicalModelValueLabel = null;
		    JLabel validateModelMappingLabel = null;
		    JLabel validateModelMappingValueLabel = null;
		    JLabel validateGmeTagsLabel = null;
		    JLabel validateGmeTagsValueLabel = null;
		    JLabel generateHibernateMappingLabel = null;
		    JLabel generateHibernateMappingValueLabel = null;
		    JLabel generateBeansLabel = null;
		    JLabel generateBeansValueLabel = null;
		    JLabel generateCastorMappingLabel = null;
		    JLabel generateCastorMappingValueLabel = null;
		    JLabel generateXsdLabel = null;
		    JLabel generateXsdValueLabel = null;
		    JLabel generateXsdWithGmeTagsLabel = null;
		    JLabel generateXsdWithGmeTagsValueLabel = null;
		    JLabel generateXsdWithPermissibleValuesLabel = null;
		    JLabel generateXsdWithPermissibleValuesValueLabel = null;
		    JLabel generateWsddLabel = null;
		    JLabel generateWsddValueLabel = null;
		    JLabel generateHibernateValidatorLabel = null;
		    JLabel generateHibernateValidatorValueLabel = null;
		    JLabel caDsrConnectionUrlLabel = null;
		    JLabel caDsrConnectionUrlValueLabel = null;
        	
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
            
            GridBagConstraints gridBagConstraints90 = new GridBagConstraints();
            gridBagConstraints90.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints90.gridy = 9;
            gridBagConstraints90.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints90.gridx = 0;
            
            GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
            gridBagConstraints91.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints91.gridy = 9;
            gridBagConstraints91.weightx = 1.0;
            gridBagConstraints91.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints91.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints91.gridwidth = 2;
            gridBagConstraints91.weighty = 1.0D;
            gridBagConstraints91.gridx = 1;
            
            GridBagConstraints gridBagConstraints100 = new GridBagConstraints();
            gridBagConstraints100.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints100.gridy = 10;
            gridBagConstraints100.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints100.gridx = 0;
            
            GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
            gridBagConstraints101.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints101.gridy = 10;
            gridBagConstraints101.weightx = 1.0;
            gridBagConstraints101.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints101.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints101.gridwidth = 2;
            gridBagConstraints101.weighty = 1.0D;
            gridBagConstraints101.gridx = 1;
            
            GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
            gridBagConstraints110.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints110.gridy = 11;
            gridBagConstraints110.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints110.gridx = 0;
            
            GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
            gridBagConstraints111.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints111.gridy = 11;
            gridBagConstraints111.weightx = 1.0;
            gridBagConstraints111.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints111.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints111.gridwidth = 2;
            gridBagConstraints111.weighty = 1.0D;
            gridBagConstraints111.gridx = 1;
            
            GridBagConstraints gridBagConstraints120 = new GridBagConstraints();
            gridBagConstraints110.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints110.gridy = 12;
            gridBagConstraints110.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints110.gridx = 0;
            
            GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
            gridBagConstraints111.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints111.gridy = 12;
            gridBagConstraints111.weightx = 1.0;
            gridBagConstraints111.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints111.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints111.gridwidth = 2;
            gridBagConstraints111.weighty = 1.0D;
            gridBagConstraints111.gridx = 1;
                        
                        
		    validateLogicalModelLabel = new JLabel();
		    validateLogicalModelLabel.setText("Validate Logical Model?");
		    validateLogicalModelValueLabel = new JLabel();
		    validateLogicalModelValueLabel.setText(Boolean.valueOf(getValidateLogicalModelCheckBox().isSelected()).toString());
            
		    validateModelMappingLabel = new JLabel();
		    validateModelMappingLabel.setText("Validate Model Mapping?");
            validateModelMappingValueLabel = new JLabel();
            validateModelMappingValueLabel.setText(Boolean.valueOf(getValidateModelMappingCheckBox().isSelected()).toString());
            
            validateGmeTagsLabel = new JLabel();
            validateGmeTagsLabel.setText("Validate GME Tags?");
            validateGmeTagsValueLabel = new JLabel();
            validateGmeTagsValueLabel.setText(Boolean.valueOf(getValidateGmeTagsCheckBox().isSelected()).toString());
            
            generateHibernateMappingLabel = new JLabel();
            generateHibernateMappingLabel.setText("Generate Hibernate Mapping?");
            generateHibernateMappingValueLabel = new JLabel();
            generateHibernateMappingValueLabel.setText(Boolean.valueOf(getGenerateHibernateMappingCheckBox().isSelected()).toString());
            
            generateBeansLabel = new JLabel();
            generateBeansLabel.setText("Generate Beans?");
            generateBeansValueLabel = new JLabel();
            generateBeansValueLabel.setText(Boolean.valueOf(getGenerateBeansCheckBox().isSelected()).toString());
            
            generateCastorMappingLabel = new JLabel();
            generateCastorMappingLabel.setText("Generate Castor Mapping?");
            generateCastorMappingValueLabel = new JLabel();
            generateCastorMappingValueLabel.setText(Boolean.valueOf(getGenerateCastorMappingCheckBox().isSelected()).toString());
            
            generateXsdLabel = new JLabel();
            generateXsdLabel.setText("Generate XSD's?");
            generateXsdValueLabel = new JLabel();
            generateXsdValueLabel.setText(Boolean.valueOf(getGenerateXsdCheckBox().isSelected()).toString());
            
            generateXsdWithGmeTagsLabel = new JLabel();
            generateXsdWithGmeTagsLabel.setText("Generate XSD's w/ GME Tags?");
            generateXsdWithGmeTagsValueLabel = new JLabel();
            generateXsdWithGmeTagsValueLabel.setText(Boolean.valueOf(getGenerateXsdWithGmeTagsCheckBox().isSelected()).toString());
            
            generateXsdWithPermissibleValuesLabel = new JLabel();
            generateXsdWithPermissibleValuesLabel.setText("Generate XSD's w/ Permissible Values?");
            generateXsdWithPermissibleValuesValueLabel = new JLabel();
            generateXsdWithPermissibleValuesValueLabel.setText(Boolean.valueOf(getGenerateXsdWithPermissibleValuesCheckBox().isSelected()).toString());
            
		    generateWsddLabel = new JLabel();
		    generateWsddLabel.setText("Generate WSDD?");
		    generateWsddValueLabel = new JLabel();
		    generateWsddValueLabel.setText(Boolean.valueOf(getGenerateWsddCheckBox().isSelected()).toString());
            
		    generateHibernateValidatorLabel = new JLabel();
		    generateHibernateValidatorLabel.setText("Generate Hibernate Validator?");
		    generateHibernateValidatorValueLabel = new JLabel();
		    generateHibernateValidatorValueLabel.setText(Boolean.valueOf(getGenerateHibernateValidatorCheckBox().isSelected()).toString());
		    
		    caDsrConnectionUrlLabel = new JLabel();
		    caDsrConnectionUrlLabel.setText("caDSR Connection URL:");
		    caDsrConnectionUrlValueLabel = new JLabel();
		    caDsrConnectionUrlValueLabel.setText(getCaDsrConnectionUrlField().getText());
            
            codeGenSettingsReviewPanel = new JPanel();
            codeGenSettingsReviewPanel.setLayout(new GridBagLayout());
            codeGenSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Code Generation Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            codeGenSettingsReviewPanel.add(validateLogicalModelLabel, gridBagConstraints10);
            codeGenSettingsReviewPanel.add(validateLogicalModelValueLabel, gridBagConstraints11);
            codeGenSettingsReviewPanel.add(generateBeansLabel, gridBagConstraints20);
            codeGenSettingsReviewPanel.add(generateBeansValueLabel, gridBagConstraints21);
            codeGenSettingsReviewPanel.add(generateWsddLabel, gridBagConstraints30);
            codeGenSettingsReviewPanel.add(generateWsddValueLabel, gridBagConstraints31);
    
            codeGenSettingsReviewPanel.add(validateModelMappingLabel, gridBagConstraints40);
            codeGenSettingsReviewPanel.add(validateModelMappingValueLabel, gridBagConstraints41);
            codeGenSettingsReviewPanel.add(generateHibernateMappingLabel, gridBagConstraints50);
            codeGenSettingsReviewPanel.add(generateHibernateMappingValueLabel, gridBagConstraints51);
            codeGenSettingsReviewPanel.add(generateHibernateValidatorLabel, gridBagConstraints60);
            codeGenSettingsReviewPanel.add(generateHibernateValidatorValueLabel, gridBagConstraints61);
            
            codeGenSettingsReviewPanel.add(generateXsdLabel, gridBagConstraints70);
            codeGenSettingsReviewPanel.add(generateXsdValueLabel, gridBagConstraints71);
            
            if (getGenerateXsdCheckBox().isSelected()){
            	codeGenSettingsReviewPanel.add(generateCastorMappingLabel, gridBagConstraints80);
            	codeGenSettingsReviewPanel.add(generateCastorMappingValueLabel, gridBagConstraints81);
            	codeGenSettingsReviewPanel.add(generateXsdWithGmeTagsLabel, gridBagConstraints90);
            	codeGenSettingsReviewPanel.add(generateXsdWithGmeTagsValueLabel, gridBagConstraints91); 
            	codeGenSettingsReviewPanel.add(validateGmeTagsLabel, gridBagConstraints100);
            	codeGenSettingsReviewPanel.add(validateGmeTagsValueLabel, gridBagConstraints101);
            	codeGenSettingsReviewPanel.add(generateXsdWithPermissibleValuesLabel, gridBagConstraints110);
            	codeGenSettingsReviewPanel.add(generateXsdWithPermissibleValuesValueLabel, gridBagConstraints111);
            }
            
            if (getGenerateXsdWithPermissibleValuesCheckBox().isSelected() || getGenerateHibernateValidatorCheckBox().isSelected()){
            	codeGenSettingsReviewPanel.add(caDsrConnectionUrlLabel, gridBagConstraints120);
            	codeGenSettingsReviewPanel.add(caDsrConnectionUrlValueLabel, gridBagConstraints121);
            }
            
            codeGenSettingsReviewPanel.validate();
        //}
        return codeGenSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
        if (getGenerateXsdWithPermissibleValuesCheckBox().isSelected() || getGenerateHibernateValidatorCheckBox().isSelected()){
    		if (!ValidationUtils.isNotBlank(this.getCaDsrConnectionUrlField().getText())) {
    			result.add(new SimpleValidationMessage(CADSR_CONNECTION_URL + " must not be blank.", Severity.ERROR, CADSR_CONNECTION_URL));
    		} 
        }
    	
    	return result;
    }
    
    public void initValidation() {
    	
        ValidationComponentUtils.setMessageKey(getCaDsrConnectionUrlField(), CADSR_CONNECTION_URL);
        ValidationComponentUtils.setMandatory(getCaDsrConnectionUrlField(), true);
    	
    	toggleXsdFields();
    	toggleCaDsrField();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();

		// Code Generation properties
		propsMap.put("VALIDATE_LOGICAL_MODEL", Boolean.valueOf(validateLogicalModelCheckBox.isSelected()).toString() );
		propsMap.put("VALIDATE_MODEL_MAPPING", Boolean.valueOf(validateModelMappingCheckBox.isSelected()).toString() );
		propsMap.put("VALIDATE_GME_TAGS", Boolean.valueOf(validateGmeTagsCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_HIBERNATE_MAPPING", Boolean.valueOf(generateHibernateMappingCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_BEANS", Boolean.valueOf(generateBeansCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_CASTOR_MAPPING", Boolean.valueOf(generateCastorMappingCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_XSD", Boolean.valueOf(generateXsdCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_XSD_WITH_GME_TAGS", Boolean.valueOf(generateXsdWithGmeTagsCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_XSD_WITH_PERMISSIBLE_VALUES", Boolean.valueOf(generateXsdWithPermissibleValuesCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_WSDD", Boolean.valueOf(generateWsddCheckBox.isSelected()).toString() );
		propsMap.put("GENERATE_HIBERNATE_VALIDATOR", Boolean.valueOf(generateHibernateValidatorCheckBox.isSelected()).toString() );
		propsMap.put("CADSR_CONNECTION_URL", getCaDsrConnectionUrlField().getText());
    	
    	return propsMap;
    }
}
