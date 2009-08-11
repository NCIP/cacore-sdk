package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.application.ExternalWorkflowApplicationComponent;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Workbench Create UML Model Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created June, 2008
 */
public class CreateUmlModelViewer extends ExternalWorkflowApplicationComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(CreateUmlModelViewer.class);
	
	private static final String ARGOUML_WEB_START_URL = "http://argouml-downloads.tigris.org/jws/argouml-0.24.jnlp";
	private static final String EA_DOWNLOAD_URL = "http://www.sparxsystems.com.au/products/ea/index.html";
	
    // Buttons
    private JButton downloadArgoButton = null;
    private JButton downloadEAButton = null;
    private JButton closeButton = null;
    
	// Constructor
    public CreateUmlModelViewer() {
    	super();
        initialize();
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        //setFrameIcon(LookAndFeel.getWorkbenchIcon());
        setTitle("Create UML Model");
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
        	
        	JLabel descriptionLabel = new JLabel("Add 'Create UML Model' process description here", JLabel.CENTER);
            descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(Font.ITALIC));
            descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            
            JButton[] buttons = {getDownloadArgoButton(),getDownloadEAButton(),getCloseButton()};

        	contentPanel = new JPanel();
        	contentPanel.setLayout(new GridBagLayout());
        	contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create UML Model Process",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	contentPanel.add(getMainSplitPanel(descriptionLabel,buttons), gridBagConstraints10);
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
    private JButton getDownloadArgoButton() {
        if (downloadArgoButton == null) {
        	downloadArgoButton = new JButton();
        	downloadArgoButton.setText("Open ArgoUML");
        	downloadArgoButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	downloadArgoButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(ARGOUML_WEB_START_URL);
                }
            });
        }

        return downloadArgoButton;
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getDownloadEAButton() {
        if (downloadEAButton == null) {
            downloadEAButton = new JButton();
            downloadEAButton.setText("Download EA");
            downloadEAButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            downloadEAButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(EA_DOWNLOAD_URL);
                }
            });
        }

        return downloadEAButton;
    }

} 
