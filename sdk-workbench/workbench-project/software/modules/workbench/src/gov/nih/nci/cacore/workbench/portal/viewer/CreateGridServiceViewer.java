package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.portal.application.ExternalWorkflowApplicationComponent;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Workbench Create Grid Service (Introduce) Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created June, 2008
 */
public class CreateGridServiceViewer extends ExternalWorkflowApplicationComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(CreateGridServiceViewer.class);
	
	private static final String CREATE_GRID_SERVICE_URL = ResourceManager.getLaunchIntroduceUrl();
	
    // Buttons
    private JButton launchIntroduceButton = null;
    private JButton closeButton = null;
    
	// Constructor
    public CreateGridServiceViewer() {
    	super();
        initialize();
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        //setFrameIcon(LookAndFeel.getWorkbenchIcon());
        setTitle("Create Grid Service (Introduce) Process");
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
			
			InputStream is = CreateGridServiceViewer.class.getResourceAsStream("/html/CreateGridServiceDescription.html");

//        	
//        	JLabel descriptionLabel = new JLabel("Add 'Create Grid Service (Introduce)' process description here", JLabel.CENTER);
//            descriptionLabel.setFont(descriptionLabel.getFont().deriveFont(Font.ITALIC));
//            descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
            
            JButton[] buttons = {getLaunchIntroduceButton(),getCloseButton()};

        	contentPanel = new JPanel();
        	contentPanel.setLayout(new GridBagLayout());
        	contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	contentPanel.add(getMainSplitPanel(convertStreamToString(is),buttons), gridBagConstraints10);
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
    private JButton getLaunchIntroduceButton() {
        if (launchIntroduceButton == null) {
        	launchIntroduceButton = new JButton();
        	launchIntroduceButton.setText("Launch Introduce");
        	launchIntroduceButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	launchIntroduceButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(CREATE_GRID_SERVICE_URL);
                }
            });
        }

        return launchIntroduceButton;
    }

} 
