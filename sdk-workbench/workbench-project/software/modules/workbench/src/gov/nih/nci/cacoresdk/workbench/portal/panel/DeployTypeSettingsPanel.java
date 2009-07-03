package gov.nih.nci.cacoresdk.workbench.portal.panel;

import gov.nih.nci.cacoresdk.workbench.common.LookAndFeel;
import gov.nih.nci.cacoresdk.workbench.common.OptionsMapManager;
import gov.nih.nci.cacoresdk.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacoresdk.workbench.portal.validation.TabbedPanePropertiesValidator;
import gov.nih.nci.cacoresdk.workbench.portal.viewer.DeployPropertiesViewer;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.message.SimpleValidationMessage;
import com.jgoodies.validation.view.ValidationComponentUtils;

public final class DeployTypeSettingsPanel implements Panel, PanelValidator {
	
	
	private static final Logger log = Logger.getLogger(OptionsMapManager.class);
	
	private TabbedPanePropertiesValidator mainPanelValidator = null;
	private DeployPropertiesViewer parentContainer = null;
	
	// Constants
	private static final String LOCAL = "local";
	private static final String REMOTE = "remote";
	
	// Validation Message Constants
	private static final String DEPLOY_TYPE = "Deployment Type";
	
	private static final String REMOTE_DEPLOY_ENV = "Remote Deployment Environment";
	
    private JButton loadPropertiesButton = null;

	public DeployTypeSettingsPanel(DeployPropertiesViewer parentContainer,
			TabbedPanePropertiesValidator mainPanelValidator) {
		this.parentContainer=parentContainer;
		this.mainPanelValidator = mainPanelValidator;
	}
	
	//Deploy Settings Panel
	private JPanel deploySettingsPanel = null;
	private JPanel deployRemoteSettingsSubPanel = null;
	private JPanel deploySettingsReviewPanel = null;
    
	//Deploy Settings Panel Component Definitions
    private JComboBox  deployTypeComboBox = null;
    
    private JComboBox  remoteDeployEnvComboBox = null;
    
    // Cross-panel properties
        
    /**
     * This method initializes the Deploy Type Combo Box
     * 
     * @return javax.swing.JTextField
     */         
    private JComboBox getDeployTypeComboBox() {
    	if (deployTypeComboBox == null) {
    		deployTypeComboBox = new JComboBox(); 
    		
    		Map<String,String> deployTypeOptionsMap = OptionsMapManager.getDeployTypeOptionsMap();
        	if (deployTypeOptionsMap != null){
            	Iterator<String> iter = deployTypeOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		deployTypeComboBox.addItem((String)iter.next());
            	}
            	
            	deployTypeComboBox.setSelectedItem("");
        	}
    		
    		deployTypeComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	toggleDeployTypeFields();
                	toggleLoadPropertiesButton();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });

    	}
    	return deployTypeComboBox;
    }
    
    public String getDeployType() {
    	return getDeployTypeComboBox().getSelectedItem().toString();
    }
    
    /**
     * This method initializes the Deploy Type Combo Box
     * 
     * @return javax.swing.JTextField
     */         
    private JComboBox getRemoteDeployEnvComboBox() {
    	if (remoteDeployEnvComboBox == null) {
    		remoteDeployEnvComboBox = new JComboBox(); 
    		
    		Map<String,String> remoteDeployEnvOptionsMap = OptionsMapManager.getRemoteDeployEnvOptionsMap();
        	if (remoteDeployEnvOptionsMap != null){
            	Iterator<String> iter = remoteDeployEnvOptionsMap.keySet().iterator();
            	
            	while (iter.hasNext()){
            		remoteDeployEnvComboBox.addItem((String)iter.next());
            	}
            	
        		remoteDeployEnvComboBox.setSelectedItem("");
        	}
    		
        	remoteDeployEnvComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	toggleLoadPropertiesButton();
                    mainPanelValidator.setDirty(true);
                    mainPanelValidator.validateInput();
                }
            });

    	}
    	return remoteDeployEnvComboBox;
    }
    
    public String getRemoteDeployEnv() {
    	return getRemoteDeployEnvComboBox().getSelectedItem().toString();
    }
    
    public String getRemoteDeployEnvPrefix(){
    	return OptionsMapManager.getRemoteDeployEnvOptionsMap().get(getRemoteDeployEnvComboBox().getSelectedItem().toString());
	}
    
    public boolean isLocalDeployment(){
    	return (getDeployTypeComboBox().getSelectedItem().toString()).equalsIgnoreCase(LOCAL);
    }
    
    public boolean isRemoteDeployment(){
    	return (getDeployTypeComboBox().getSelectedItem().toString()).equalsIgnoreCase(REMOTE);
    }
    
    protected void toggleDeployTypeFields() {
       	String deployType = getDeployTypeComboBox().getSelectedItem().toString();
       	log.debug("* * * deployType: " + deployType);
       	
		if (deployType.equalsIgnoreCase(LOCAL)){
			getRemoteDeployEnvComboBox().setEnabled(false);
		} else if (deployType.equalsIgnoreCase(REMOTE)){
			getRemoteDeployEnvComboBox().setEnabled(true);
		} else {
			getRemoteDeployEnvComboBox().setEnabled(false);
		}
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLoadPropertiesButton() {
        if (loadPropertiesButton == null) {
            loadPropertiesButton = new JButton();
            loadPropertiesButton.setText("Load/Create Configuration");
            loadPropertiesButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            loadPropertiesButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	parentContainer.loadDeployProperties();
                    mainPanelValidator.validateInput();
                }
            });
        }

        return loadPropertiesButton;
    }
    
    public void toggleLoadPropertiesButton() {
    	String deployType = getDeployTypeComboBox().getSelectedItem().toString();
    	
    	if (deployType.equalsIgnoreCase(LOCAL)){
    		this.getLoadPropertiesButton().setEnabled(true);
    		return;
        }

    	if (deployType.equalsIgnoreCase(REMOTE)){
        	String remoteDeployEnv = getRemoteDeployEnvComboBox().getSelectedItem().toString();
        	if (!remoteDeployEnv.equalsIgnoreCase("")){
        		this.getLoadPropertiesButton().setEnabled(true);
        		return;
        	}
        	
        	this.getLoadPropertiesButton().setEnabled(false);
        	return;
        }
    	
    	this.getLoadPropertiesButton().setEnabled(false);

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
		    deploySettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Define Local/Remote Deployment Properties",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    deploySettingsPanel.add(deployTypeLabel, gridBagConstraints10);
		    deploySettingsPanel.add(getDeployTypeComboBox(), gridBagConstraints11);
		    deploySettingsPanel.add(getDeployRemoteSettingsSubPanel(), gridBagConstraints20);
		    deploySettingsPanel.add(getLoadPropertiesButton(), gridBagConstraints30);
			
		    deploySettingsPanel.validate();
		}
		return deploySettingsPanel;
    }
    
	/**
	 * This method initializes the deploy remote Settings Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDeployRemoteSettingsSubPanel() {
		if (deployRemoteSettingsSubPanel == null) {
			
		    //Deploy Remote Settings Label Definitions
		    JLabel remoteDeployEnvLabel = null;
		
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
            
            remoteDeployEnvLabel = new JLabel();
            remoteDeployEnvLabel.setText("Remote Environment:");
			
			deployRemoteSettingsSubPanel = new JPanel();
			deployRemoteSettingsSubPanel.setLayout(new GridBagLayout());
			deployRemoteSettingsSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Remote Deployment Options",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
			deployRemoteSettingsSubPanel.add(remoteDeployEnvLabel, gridBagConstraints10);
			deployRemoteSettingsSubPanel.add(getRemoteDeployEnvComboBox(), gridBagConstraints11);
			
			deployRemoteSettingsSubPanel.validate();
		}
		return deployRemoteSettingsSubPanel;
	}
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getSummaryPanel() {
        //if (deploySettingsReviewPanel == null) {
    	
		    //Deploy Settings Panel Label Definitions
	    	JLabel deployTypeLabel = null;
	    	JLabel deployTypeValueLabel = null;
	    	JLabel remoteDeployEnvLabel = null;
	    	JLabel remoteDeployEnvValueLabel = null;    	
        	
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
            
	    	String deployType = getDeployTypeComboBox().getSelectedItem().toString();
            
		    deployTypeLabel = new JLabel();
		    deployTypeLabel.setText("Deployment Type:");
		    deployTypeValueLabel = new JLabel();
		    deployTypeValueLabel.setText(deployType);
		    
		    remoteDeployEnvLabel = new JLabel();
		    remoteDeployEnvLabel.setText("Remote Deploy Env:");
		    remoteDeployEnvValueLabel = new JLabel();
		    remoteDeployEnvValueLabel.setText(getRemoteDeployEnvComboBox().getSelectedItem().toString());
            
		    deploySettingsReviewPanel = new JPanel();
		    deploySettingsReviewPanel.setLayout(new GridBagLayout());
		    deploySettingsReviewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deploy Properties",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
		    
		    deploySettingsReviewPanel.add(deployTypeLabel, gridBagConstraints10);
		    deploySettingsReviewPanel.add(deployTypeValueLabel, gridBagConstraints11);
		    
		    if (deployType.equalsIgnoreCase(REMOTE)){
			    deploySettingsReviewPanel.add(remoteDeployEnvLabel, gridBagConstraints20);
			    deploySettingsReviewPanel.add(remoteDeployEnvValueLabel, gridBagConstraints21);		    
		    }
            
		    deploySettingsReviewPanel.validate();
        //}
        return deploySettingsReviewPanel;
    }
    
    public ValidationResult validateInput() {

    	ValidationResult result = new ValidationResult();
    	
    	//Deploy Type Setting Validation	
       	String deployType = getDeployTypeComboBox().getSelectedItem().toString();
  
    	if ( deployType== null || deployType=="") {
    		result.add(new SimpleValidationMessage(DEPLOY_TYPE + " must be selected.", Severity.ERROR, DEPLOY_TYPE));
    	}

    	if (deployType.equalsIgnoreCase(REMOTE)){
    		
           	String remoteDeployEnv = getRemoteDeployEnvComboBox().getSelectedItem().toString();
            
        	if ( remoteDeployEnv== null || remoteDeployEnv=="") {
        		result.add(new SimpleValidationMessage(REMOTE_DEPLOY_ENV + " must be selected.", Severity.ERROR, REMOTE_DEPLOY_ENV));
        	}
    	}
    	
    	return result;
    }
    
    public void initValidation() {
        // Deploy Type Validation
        ValidationComponentUtils.setMessageKey(getDeployTypeComboBox(), DEPLOY_TYPE);
        ValidationComponentUtils.setMandatory(getDeployTypeComboBox(), true);
        ValidationComponentUtils.setMessageKey(getRemoteDeployEnvComboBox(), REMOTE_DEPLOY_ENV);
        ValidationComponentUtils.setMandatory(getRemoteDeployEnvComboBox(), true); 
        
        toggleDeployTypeFields(); 
        toggleLoadPropertiesButton();
    }
    
    public Map<String,String> getPropsMap(){
    	Map<String,String> propsMap = new HashMap<String,String>();
    	
    	String deployType = OptionsMapManager.getDeployTypeOptionsMap().get(getDeployTypeComboBox().getSelectedItem().toString());
		
//    	// Deploy properties
//		propsMap.put("DEPLOY_TYPE", deployType);
		
		if (deployType.equalsIgnoreCase(REMOTE)){
			// Remote Deploy properties
			propsMap.put("REMOTE_DEPLOY_ENV", OptionsMapManager.getRemoteDeployEnvOptionsMap().get(getRemoteDeployEnvComboBox().getSelectedItem().toString()));
		}
    	
    	return propsMap;
    }
}
