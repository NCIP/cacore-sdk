package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;
import gov.nih.nci.cagrid.common.portal.validation.IconFeedbackPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;


/**
 * Workbench Help Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A> Based upon "Bare Bones Browser Launch" - See http://www.centerkey.com/java/browser/
 * @created June, 2008
 */
public class HelpViewer extends WorkbenchViewerBaseComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(HelpViewer.class);
	
	private static final String HELP_URL = ResourceManager.getWorkbenchHelpUrl();
	
    // Buttons
    private JButton openButton = null;
    private JButton closeButton = null;

	/*
	 * Primary Panel definitions
	 */
    private JPanel mainPanel = null;
    private JPanel buttonPanel = null;
	private JPanel helpPanel = null;
    
	// Constructor
    public HelpViewer() {
    	super();
        initialize();
        
        setVisible(false);
        dispose();
        
        try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			log.error("Error trying to close HelpViewer: ", e);
		}
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        setFrameIcon(LookAndFeel.getGenerateApplicationIcon());
        setTitle("Workbench Help");

//        URL workbenchManualUrl = HelpViewer.class.getResource("/SDKWorkbench.pdf");
//        log.debug("* * * Workbench User Manual URL: " + workbenchManualUrl);
//        log.debug("* * * Workbench User Manual URL file: " + workbenchManualUrl.getFile());
//        
//        openURL(workbenchManualUrl.getFile());
//        
//        if (workbenchManualUrl != null && workbenchManualUrl.getFile().length() >0){
//        	openURL(workbenchManualUrl.getFile());
//        }
        
//        openURL(HELP_URL);

    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getHelpPanel() {
        if (helpPanel == null) {
        	
            JLabel helpLabel = new JLabel();
            
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			//gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints10.gridy = 3;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			//gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D; 
            
            helpLabel.setText("To open the caCORE Workbench user manual in your default browser, click the 'Open' button below, or go to " + HELP_URL + ".");

        	helpPanel = new JPanel();
        	helpPanel.setLayout(new GridBagLayout());
        	helpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workbench Help",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	helpPanel.add(helpLabel, gridBagConstraints10);

        }
        return helpPanel;
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
            mainPanel.add(getHelpPanel(), gridBagConstraints10);
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
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getInstallButton() {
        if (openButton == null) {
            openButton = new JButton();
            openButton.setText("Open");
            openButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            openButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	openURL(HELP_URL);
                }
            });
        }

        return openButton;
    }

} 
