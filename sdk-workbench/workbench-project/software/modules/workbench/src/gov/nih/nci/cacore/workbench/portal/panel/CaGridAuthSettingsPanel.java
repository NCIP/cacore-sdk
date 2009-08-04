package gov.nih.nci.cacore.workbench.portal.panel;

import gov.nih.nci.cacore.workbench.common.FileFilters;
import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.OptionsMapManager;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
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

public final class CaGridAuthSettingsPanel implements Panel, PanelValidator {

	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Validation Message Constants
	private static final String TARGET_GRID = "Target Grid";
	private static final String GRID_SECURE_CERT_FILE = "Grid Security Certificate File";
	private static final String GRID_SECURE_KEY_FILE = "Grid Security Key File";

	public CaGridAuthSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator,
			boolean isSecurityEnabled,
			boolean isCaGridLoginModuleEnabled) {
		this.parentContainer=parentContainer;
		this.mainPanelValidator = mainPanelValidator;
		this.isSecurityEnabled = isSecurityEnabled;
		this.isCaGridLoginModuleEnabled = isCaGridLoginModuleEnabled;
	}

	// caGrid Auth Settings Panel
	private JPanel caGridAuthSettingsPanel = null;
	private JPanel caGridAuthLoginModuleSettingsSubPanel = null;
	private JPanel caGridAuthSettingsReviewPanel = null;
    
	//caGRID Authentication Settings Panel Component Definitions
    private JComboBox  caGridTargetGridComboBox = null;
    private JTextField gridSecureCertFileField = null;
    private JTextField gridSecureKeyFileField = null;

    // Buttons
    private JButton gridSecureCertFilePathButton = null;
    private JButton gridSecureKeyFilePathButton = null;
    
    // Cross-panel properties
    boolean isSecurityEnabled;
    boolean isCaGridLoginModuleEnabled;
    
    /**
     * This method initializes the caGrid Security Certificate File Field
     * 
     * @return javax.swing.JTextField
     */    
    private JTextField getGridSecureCertFileField() {
    	if (gridSecureCertFileField == null) {
    		gridSecureCertFileField = new JTextField();
    		gridSecureCertFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("grid.secure.cert.file"));
    		
    		gridSecureCertFileField.getDocument().addDocumentListener(new DocumentListener() {
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
    		gridSecureCertFileField.addFocusListener(new FocusChangeHandler());
    		
    		gridSecureCertFileField.setEditable(false); // Only allow changes via the Cert File Button
    		//gridSecureCertFileField.setEnabled(false);
    	}
    	return gridSecureCertFileField;
    }
    
    public String getGridSecureCertFilePath(){
    	return getGridSecureCertFileField().getText().replace('\\', '/');
    }
    
    public String getGridSecureCertFileName(){
    	String certFilePath = getGridSecureCertFileField().getText().replace('\\', '/');
    	
    	return certFilePath.substring(certFilePath.lastIndexOf('/')+1);
    }
    
    public void setGridSecureCertFilePath(String filePath){
    	getGridSecureCertFileField().setText(filePath);
    }
    
    /**
     * This method initializes the caGrid Security Certificate File Field
     * 
     * @return javax.swing.JTextField
     */    
    private JTextField getGridSecureKeyFileField() {
    	if (gridSecureKeyFileField == null) {
    		gridSecureKeyFileField = new JTextField();
    		gridSecureKeyFileField.setText(parentContainer.getPropertiesManager().getDeployPropertyValue("grid.secure.key.file"));
    		gridSecureKeyFileField.getDocument().addDocumentListener(new DocumentListener() {
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
    		gridSecureKeyFileField.addFocusListener(new FocusChangeHandler());
    		
    		gridSecureKeyFileField.setEditable(false); // Only allow changes via the Key File Button
    		//gridSecureKeyFileField.setEnabled(false);
    	}
    	return gridSecureKeyFileField;
    }
    
    public String getGridSecureKeyFilePath(){
    	return getGridSecureKeyFileField().getText().replace('\\', '/');
    }
    
    public String getGridSecureKeyFileName(){
    	String keyFilePath = getGridSecureKeyFileField().getText().replace('\\', '/');
    	
    	return keyFilePath.substring(keyFilePath.lastIndexOf('/')+1);
    }
    
    public void setGridSecureKeyFilePath(String filePath){
    	getGridSecureKeyFileField().setText(filePath);
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getGridSecureKeyFilePathButton() {
        if (gridSecureKeyFilePathButton == null) {
        	gridSecureKeyFilePathButton = new JButton();
        	gridSecureKeyFilePathButton.setText("Browse");
        	gridSecureKeyFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	gridSecureKeyFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getGridSecureKeyFileField().getText();
                        String location = ResourceManager.promptFile(previous, FileFilters.PEM_FILTER);
                        if (location != null && location.length() > 0) {
                        	getGridSecureKeyFileField().setText(location);
                        } else {
                        	getGridSecureKeyFileField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return gridSecureKeyFilePathButton;
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getGridSecureCertFilePathButton() {
        if (gridSecureCertFilePathButton == null) {
        	gridSecureCertFilePathButton = new JButton();
        	gridSecureCertFilePathButton.setText("Browse");
        	gridSecureCertFilePathButton.setIcon(LookAndFeel.getBrowseIcon());
        	gridSecureCertFilePathButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        String previous = getGridSecureCertFileField().getText();
                        String location = ResourceManager.promptFile(previous, FileFilters.PEM_FILTER);
                        if (location != null && location.length() > 0) {
                        	getGridSecureCertFileField().setText(location);
                        } else {
                        	getGridSecureCertFileField().setText(previous);
                        }
                        mainPanelValidator.validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        return gridSecureCertFilePathButton;
    }
        
    /**
     * This method initializes the caGrid Target Grid Combo Box
     * 
     * @return javax.swing.JTextField
     */         
    private JComboBox getCaGridTargetGridComboBox() {
    	if (caGridTargetGridComboBox == null) {
    		caGridTargetGridComboBox = new JComboBox(); 
    		
    		Map<String,String> caGridTargetGridOptionsMap = OptionsMapManager.getCaGridTargetGridOptionsMap();
        	if (caGridTargetGridOptionsMap != null){
            	Iterator<String> iter = caGridTargetGridOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		caGridTargetGridComboBox.addItem((String)iter.next());
            	}
            	
            	String targetValue = parentContainer.getPropertiesManager().getDeployPropertyValue("TARGET_GRID");
            	String targetKey = OptionsMapManager.getValueToKeyMap().get(targetValue);
            	
            	if (targetKey != null && targetKey.length()>0)
            		caGridTargetGridComboBox.setSelectedItem(targetKey);
        	}
    		
    		caGridTargetGridComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });

    		
    	}
    	return caGridTargetGridComboBox;
    }
    
    public String getCaGridTargetGrid(){
    	return OptionsMapManager.getCaGridTargetGridOptionsMap().get(getCaGridTargetGridComboBox().getSelectedItem().toString());
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
	 * This method initializes caGridAuthSettingsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
    public JPanel getSettingsPanel() {
		if (caGridAuthSettingsPanel == null) {
	        
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 2;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			//gridBagConstraints20.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D;  

		    caGridAuthSettingsPanel = new JPanel();
		    caGridAuthSettingsPanel.setLayout(new GridBagLayout());
		    caGridAuthSettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define caGrid Authentication Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    caGridAuthSettingsPanel.add(getCaGridAuthLoginModuleSettingsSubPanel(), gridBagConstraints10);
			
		    caGridAuthSettingsPanel.validate();
		}
		return caGridAuthSettingsPanel;
    }
    
	/**
	 * This method initializes caGrid Authenthication Settings Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCaGridAuthLoginModuleSettingsSubPanel() {
		if (caGridAuthLoginModuleSettingsSubPanel == null) {
			
		    //caGRID Authentication Settings Panel Label Definitions
		    JLabel caGridEnvironmentLabel = null;
		    JLabel gridSecureCertFileLabel = null;
		    JLabel gridSecureKeyFileLabel = null;
		
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
            gridBagConstraints31.weightx = 1.0;          
            
            GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
            gridBagConstraints32.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints32.gridy = 3;
            gridBagConstraints32.gridx = 2;
            gridBagConstraints32.insets = new java.awt.Insets(2, 2, 2, 2);
            gridBagConstraints32.gridwidth = 1;
			
			caGridEnvironmentLabel = new JLabel();
			caGridEnvironmentLabel.setText("Select caGrid Environment:");

			gridSecureCertFileLabel = new JLabel();
			gridSecureCertFileLabel.setText("Select caGrid Security Certificate File:");
		    
			gridSecureKeyFileLabel = new JLabel();
			gridSecureKeyFileLabel.setText("Select caGrid Security Key File:");

			caGridAuthLoginModuleSettingsSubPanel = new JPanel();
			caGridAuthLoginModuleSettingsSubPanel.setLayout(new GridBagLayout());
			caGridAuthLoginModuleSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "caGrid Authentication Login Module Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
			caGridAuthLoginModuleSettingsSubPanel.add(caGridEnvironmentLabel, gridBagConstraints10);
			caGridAuthLoginModuleSettingsSubPanel.add(getCaGridTargetGridComboBox(), gridBagConstraints11);
			caGridAuthLoginModuleSettingsSubPanel.add(gridSecureCertFileLabel, gridBagConstraints20);
			caGridAuthLoginModuleSettingsSubPanel.add(getGridSecureCertFileField(), gridBagConstraints21);
			caGridAuthLoginModuleSettingsSubPanel.add(getGridSecureCertFilePathButton(), gridBagConstraints22);
			caGridAuthLoginModuleSettingsSubPanel.add(gridSecureKeyFileLabel, gridBagConstraints30);
			caGridAuthLoginModuleSettingsSubPanel.add(getGridSecureKeyFileField(), gridBagConstraints31);
			caGridAuthLoginModuleSettingsSubPanel.add(getGridSecureKeyFilePathButton(), gridBagConstraints32);
			
			caGridAuthLoginModuleSettingsSubPanel.validate();
		}
		return caGridAuthLoginModuleSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (caGridAuthSettingsReviewPanel == null) {
        	
		    //caGRID Authentication Settings Panel Label Definitions
		    JLabel caGridEnvironmentLabel = null;
		    JLabel caGridEnvironmentValueLabel = null;
		    JLabel gridSecureCertFileLabel = null;
		    JLabel gridSecureCertFileValueLabel = null;
		    JLabel gridSecureKeyFileLabel = null;
		    JLabel gridSecureKeyFileValueLabel = null;
        	
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
		    
		    caGridEnvironmentLabel = new JLabel();
		    caGridEnvironmentLabel.setText("caGrid Environment:");
		    caGridEnvironmentValueLabel = new JLabel();
		    caGridEnvironmentValueLabel.setText(getCaGridTargetGridComboBox().getSelectedItem().toString());
		    
		    gridSecureCertFileLabel = new JLabel();
		    gridSecureCertFileLabel.setText("Certificate File:");
		    gridSecureCertFileValueLabel = new JLabel();
		    gridSecureCertFileValueLabel.setText(getGridSecureCertFileField().getText());
		    
		    gridSecureKeyFileLabel = new JLabel();
		    gridSecureKeyFileLabel.setText("Key File:");
		    gridSecureKeyFileValueLabel = new JLabel();
		    gridSecureKeyFileValueLabel.setText(getGridSecureKeyFileField().getText());
            
		    caGridAuthSettingsReviewPanel = new JPanel();
		    caGridAuthSettingsReviewPanel.setLayout(new GridBagLayout());
		    caGridAuthSettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "caGrid Authentication Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    caGridAuthSettingsReviewPanel.add(caGridEnvironmentLabel, gridBagConstraints10);
		    caGridAuthSettingsReviewPanel.add(caGridEnvironmentValueLabel, gridBagConstraints11);
		    caGridAuthSettingsReviewPanel.add(gridSecureCertFileLabel, gridBagConstraints20);
		    caGridAuthSettingsReviewPanel.add(gridSecureCertFileValueLabel, gridBagConstraints21);
		    caGridAuthSettingsReviewPanel.add(gridSecureKeyFileLabel, gridBagConstraints30);
		    caGridAuthSettingsReviewPanel.add(gridSecureKeyFileValueLabel, gridBagConstraints31);
            
		    caGridAuthSettingsReviewPanel.validate();
        //}
        return caGridAuthSettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
        //Security setting Validation
    	if (isSecurityEnabled && isCaGridLoginModuleEnabled){
    		
    		//caGrid Authentication Setting Validation		
    		if ( getCaGridTargetGridComboBox().getSelectedItem().toString()=="") {
				result.add(new SimpleValidationMessage(TARGET_GRID + " must be selected.", Severity.ERROR, TARGET_GRID));
			} 
			
			if (!ValidationUtils.isNotBlank(this.getGridSecureCertFileField().getText())) {
				result.add(new SimpleValidationMessage(GRID_SECURE_CERT_FILE + " must not be blank.", Severity.ERROR, GRID_SECURE_CERT_FILE));
			} else {
        		File file = new File(this.getGridSecureCertFileField().getText());
        		if(!file.exists()){
        			result.add(new SimpleValidationMessage(GRID_SECURE_CERT_FILE + " does not exist.  Please select or enter a valid absolute path to the file.", Severity.ERROR, GRID_SECURE_CERT_FILE));
        		}
        		
        		if (!this.getGridSecureCertFileField().getText().endsWith("pem")){
        			result.add(new SimpleValidationMessage(GRID_SECURE_CERT_FILE + " must refer to a Certificate (*.pem) file.", Severity.ERROR, GRID_SECURE_CERT_FILE));
        		}
        	}

			if (!ValidationUtils.isNotBlank(this.getGridSecureKeyFileField().getText())) {
				result.add(new SimpleValidationMessage(GRID_SECURE_KEY_FILE + " must not be blank.", Severity.ERROR, GRID_SECURE_KEY_FILE));
			} else {
        		File file = new File(this.getGridSecureKeyFileField().getText());
        		if(!file.exists()){
        			result.add(new SimpleValidationMessage(GRID_SECURE_KEY_FILE + " does not exist.  Please select or enter a valid absolute path to the file.", Severity.ERROR, GRID_SECURE_KEY_FILE));
        		}
        		
        		if (!this.getGridSecureKeyFileField().getText().endsWith("pem")){
        			result.add(new SimpleValidationMessage(GRID_SECURE_KEY_FILE + " must refer to a Certificate (*.pem) file.", Severity.ERROR, GRID_SECURE_KEY_FILE));
        		}
        	}
			
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        // caGrid Authenthication
        ValidationComponentUtils.setMessageKey(getCaGridTargetGridComboBox(), TARGET_GRID);
        ValidationComponentUtils.setMandatory(getCaGridTargetGridComboBox(), true);
        ValidationComponentUtils.setMessageKey(getGridSecureCertFileField(), GRID_SECURE_CERT_FILE);
        ValidationComponentUtils.setMandatory(getGridSecureCertFileField(), true);
        ValidationComponentUtils.setMessageKey(getGridSecureKeyFileField(), GRID_SECURE_KEY_FILE);
        ValidationComponentUtils.setMandatory(getGridSecureKeyFileField(), true);
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
		// caGrid Authentication properties
		propsMap.put("TARGET_GRID", OptionsMapManager.getCaGridTargetGridOptionsMap().get(getCaGridTargetGridComboBox().getSelectedItem().toString()));
		propsMap.put("grid.secure.cert.file", getGridSecureCertFileField().getText().replace('\\', '/'));
		propsMap.put("grid.secure.key.file", getGridSecureKeyFileField().getText().replace('\\', '/'));
    	
    	return propsMap;
    }
}
