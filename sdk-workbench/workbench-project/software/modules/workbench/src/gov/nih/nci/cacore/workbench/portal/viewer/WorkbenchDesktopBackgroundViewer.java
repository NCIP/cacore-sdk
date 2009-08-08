package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.application.WorkbenchApplicationComponent;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Workbench CSM Launcher Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A> Based upon "Bare Bones Browser Launch" - See http://www.centerkey.com/java/browser/
 * @created June, 2008
 */
public class WorkbenchDesktopBackgroundViewer extends WorkbenchApplicationComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(HelpViewer.class);
	
	private static final String WORKFLOW_URL = ResourceManager.getWorkbenchHelpUrl();
	
    // Buttons
    private JButton openButton = null;
    private JButton closeButton = null;

	/*
	 * Primary Panel definitions
	 */
    private JPanel mainPanel = null;
    private JPanel buttonPanel = null;
	private JPanel contentPanel = null;
    
	// Constructor
    public WorkbenchDesktopBackgroundViewer() {
    	super();
        initialize();
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        setFrameIcon(LookAndFeel.getWorkbenchIcon());
//        setTitle("CSM Workflow Launcher");
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getContentPanel() {
        if (contentPanel == null) {
        	
        	ImageIcon icon = Utils.createImageIcon("/images/workbenchSplashScreen.gif","Workflow");
//        	JLabel textLabel = new JLabel("Image and Text", icon, JLabel.CENTER);
        	JLabel textLabel = new JLabel(icon);
            
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			//gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints10.gridy = 3;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			//gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D; 
            
//            textLabel.setText("To launch the CSM Workflow, click the 'Open' button below.");

        	contentPanel = new JPanel();
        	contentPanel.setLayout(new GridBagLayout());
        	contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workflow Description",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	contentPanel.add(textLabel, gridBagConstraints10);

        }
        return contentPanel;
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
            mainPanel.add(getContentPanel(), gridBagConstraints10);
//            mainPanel.add(getButtonPanel(), gridBagConstraints20);
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
            buttonPanel.add(getOpenButton(), null);
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
    private JButton getOpenButton() {
        if (openButton == null) {
            openButton = new JButton();
            openButton.setText("Open");
            openButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            openButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(WORKFLOW_URL);
                }
            });
        }

        return openButton;
    }

} 
