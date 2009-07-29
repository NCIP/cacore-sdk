package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.portal.panel.SdkInstallSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacore.workbench.portal.validation.SdkInstallPropertiesValidator;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;
import gov.nih.nci.cagrid.common.portal.validation.IconFeedbackPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * SDK Code Generation Properties Manager Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created November 6, 2008
 */
public class SdkInstallPropertiesViewer extends WorkbenchViewerBaseComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(SdkInstallPropertiesViewer.class);
	
	private boolean isDirty=false;
	
	// Validation 
	private SdkInstallPropertiesValidator propsValidator = null;
	private List<PanelValidator> panelValidators = null;
    private ValidationResultModel validationModel = new DefaultValidationResultModel();

    // Buttons
    private JButton installButton = null;
    private JButton closeButton = null;

	/*
	 * Primary Panel definitions
	 */
	private JTabbedPane mainTabbedPane = null;
    private JPanel mainPanel = null;
    private JPanel buttonPanel = null;

	// Tab panel definitions
	private SdkInstallSettingsPanel sdkInstallSettingsPanel = null;
    
	// Constructor
    public SdkInstallPropertiesViewer() {
        super();
        initialize();
    }

    /**
     * This method initializes this Viewer
     */
    private void initialize() {  
        
        //Initialize main tabbed panel validator
        propsValidator = new SdkInstallPropertiesValidator(this);
        
        //Initialize Panels
        sdkInstallSettingsPanel=new SdkInstallSettingsPanel(propsMgr, propsValidator);
        
        panelValidators = new ArrayList<PanelValidator>();
        panelValidators.add((PanelValidator)sdkInstallSettingsPanel);
        
        this.setContentPane(getMainPanel());
        this.setFrameIcon(LookAndFeel.getGenerateApplicationIcon());
        this.setTitle("Install SDK Properties");

        initValidation();
        
        setDirty(false);
        validateInput();
    }


    private void initValidation() {
    	propsValidator.initValidation();
    }
    
    private void validateInput() {
    	propsValidator.validateInput();
    }

    public void updateComponentTreeSeverity() {
        ValidationComponentUtils.updateComponentTreeMandatoryAndBlankBackground(this);
        ValidationComponentUtils.updateComponentTreeSeverityBackground(this, this.getValidationModel().getResult());
    }
    
    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getMainPanel() {
        if (mainPanel == null) {
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.fill = GridBagConstraints.BOTH;
            gridBagConstraints10.weighty = 1.0;
            gridBagConstraints10.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
            gridBagConstraints20.insets = new java.awt.Insets(5, 5, 5, 5);
            gridBagConstraints20.gridx = 0;
            gridBagConstraints20.gridy = 2;
            gridBagConstraints20.anchor = java.awt.GridBagConstraints.CENTER;
            gridBagConstraints20.gridheight = 1;
            
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.add(getMainTabbedPane(), gridBagConstraints10);
            mainPanel.add(getButtonPanel(), gridBagConstraints20);
        }
        return mainPanel;
    }


    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getInstallButton(), null);
            buttonPanel.add(getCloseButton(), null);
        }
        return buttonPanel;
    }

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getInstallButton() {
        if (installButton == null) {
            installButton = new JButton();
            installButton.setText("Install");
            installButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            installButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    installSdk(sdkInstallSettingsPanel.getSdkInstallDirValue());
                }
            });
        }

        return installButton;
    }
    
    public void toggleInstallButton() {
     	if (this.validationModel.hasErrors()) {
    		installButton.setEnabled(false);
    	} else {
    		installButton.setEnabled(true);
    	}
    }

    /**
     * This method initializes closeButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new JButton();
            closeButton.setIcon(PortalLookAndFeel.getCloseIcon());
            closeButton.setText("Cancel");
            closeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return closeButton;
    }

	/**
	 * This method initializes mainTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
    public JTabbedPane getMainTabbedPane() {
		if (mainTabbedPane == null) {
			mainTabbedPane = new JTabbedPane();
			mainTabbedPane.addTab("Install SDK", null, new IconFeedbackPanel(this.validationModel, sdkInstallSettingsPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addMouseListener(new java.awt.event.MouseListener() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mouseReleased(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mousePressed(java.awt.event.MouseEvent e) {
                	validateInput();
                }
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    try {
                        validateInput();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
		}
		return mainTabbedPane;
	}
	
	public List<PanelValidator> getPanelValidators() {
		return panelValidators;
	}

	public ValidationResultModel getValidationModel() {
		return validationModel;
	}

	private Map<String,String> getPropsMap(){
		Map<String,String> propsMap=new TreeMap<String,String>();

		// SDK Install properties
		propsMap.putAll(sdkInstallSettingsPanel.getPropsMap());

		return propsMap;
	}
	
	public void setDirty(boolean isDirty){
		this.isDirty = isDirty;
	}
} 
