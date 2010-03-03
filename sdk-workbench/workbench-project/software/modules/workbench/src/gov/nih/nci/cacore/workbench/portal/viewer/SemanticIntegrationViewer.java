package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.application.ExternalWorkflowApplicationComponent;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.InputStream;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Workbench Semantic Integration Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created June, 2008
 */
public class SemanticIntegrationViewer extends ExternalWorkflowApplicationComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(SemanticIntegrationViewer.class);
	
	private static final String LAUNCH_SIW_URL = ResourceManager.getLaunchSiwUrl();
	private static final String LAUNCH_CDE_BROWSER_URL = ResourceManager.getLaunchCdeBrowserUrl();
	private static final String LAUNCH_UML_MODEL_BROWSER_URL = ResourceManager.getLaunchUmlModelBrowserUrl();
	private static final String LAUNCH_CURATION_TOOL_URL = ResourceManager.getLaunchCdeCurationToolBrowserUrl();
	
    // Buttons
    private JButton launchSIWButton = null;
    private JButton launchCDEBrowserButton = null;
    private JButton launchUMLModelBrowserButton = null;
    private JButton launchCurationToolButton = null;
    private JButton closeButton = null;
    
	// Constructor
    public SemanticIntegrationViewer() {
    	super();
        initialize();
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        //setFrameIcon(LookAndFeel.getWorkbenchIcon());
        setTitle("Semantic Integration Process");
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    protected JPanel getContentPanel() {
        if (contentPanel == null) {

			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D;
			
			InputStream is = SemanticIntegrationViewer.class.getResourceAsStream("/html/SemanticIntegrationDescription.html");
        	
//        	JLabel descriptionLabel = new JLabel("Add 'Semantic Integration' process description here.  ", JLabel.CENTER);
//          descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(Font.ITALIC));
//          descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            
            JButton[] buttons = {getLaunchSIWButton(),getLaunchCDEBrowserButton(),getLaunchUMLModelBrowserButton(),
            		getLaunchCurationToolButton(),getCloseButton()};

        	contentPanel = new JPanel();
        	contentPanel.setLayout(new GridBagLayout());
        	contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	contentPanel.add(getMainSplitPanel(Utils.convertStreamToString(is),buttons), gridBagConstraints10);
        	contentPanel.validate();
        	
        }
        return contentPanel;
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
            closeButton.setText("Close");
            closeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return closeButton;
    }
    

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLaunchSIWButton() {
        if (launchSIWButton == null) {
        	launchSIWButton = new JButton();
        	launchSIWButton.setText("Launch SIW");
        	launchSIWButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	launchSIWButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(LAUNCH_SIW_URL);
                }
            });
        }

        return launchSIWButton;
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLaunchCDEBrowserButton() {
        if (launchCDEBrowserButton == null) {
            launchCDEBrowserButton = new JButton();
            launchCDEBrowserButton.setText("CDE Browser");
            launchCDEBrowserButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            launchCDEBrowserButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(LAUNCH_CDE_BROWSER_URL);
                }
            });
        }
        log.debug("launchCDEBrowserButton size: ("+launchCDEBrowserButton.getPreferredSize()+")");

        return launchCDEBrowserButton;
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLaunchUMLModelBrowserButton() {
        if (launchUMLModelBrowserButton == null) {
        	launchUMLModelBrowserButton = new JButton();
        	launchUMLModelBrowserButton.setText("UML Model Browser");
        	launchUMLModelBrowserButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	launchUMLModelBrowserButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(LAUNCH_UML_MODEL_BROWSER_URL);
                }
            });
        }
        log.debug("launchUMLModelBrowserButton size: ("+launchUMLModelBrowserButton.getPreferredSize()+")");

        return launchUMLModelBrowserButton;
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getLaunchCurationToolButton() {
        if (launchCurationToolButton == null) {
        	launchCurationToolButton = new JButton();
        	launchCurationToolButton.setText("CDE Curation Tool");
        	launchCurationToolButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	launchCurationToolButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(LAUNCH_CURATION_TOOL_URL);
                }
            });
        }
        log.debug("launchCurationToolButton size: ("+launchCurationToolButton.getPreferredSize()+")");

        return launchCurationToolButton;
    }

} 
