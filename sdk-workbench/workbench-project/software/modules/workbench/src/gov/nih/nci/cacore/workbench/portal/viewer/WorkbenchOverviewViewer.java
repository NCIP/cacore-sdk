/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

/**
 * Workbench Overview Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created June, 2008
 */
public class WorkbenchOverviewViewer extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(WorkbenchOverviewViewer.class);

	/*
	 * Primary Panel definitions
	 */
    private JPanel mainPanel = null;
	private JPanel contentPanel = null;
	
	private Color defaultBackgroundColor = Color.WHITE;
    
	// Constructor
    public WorkbenchOverviewViewer() {
    	super();
        initialize();
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
    	
		setSize(600, 700);
		setMaximizable(true);
		setIconifiable(false);
		setClosable(true);
		setResizable(false);
     
        setContentPane(getMainPanel());
        setFrameIcon(LookAndFeel.getWorkbenchIcon());
        setTitle("Workbench Overview");
    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getContentPanel() {
        if (contentPanel == null) {
        	
            JEditorPane overviewTextPane = getTextPane(getOverviewText());
            JEditorPane supportedWorkflowTextPane = getTextPane(getSupportedWorkflowText());
            JEditorPane implementationPlanTextPane = getTextPane(getImplementationPlanText());
            
            URL imageURL = WorkbenchOverviewViewer.class.getResource("/images/caCORE_Workbench_App_Dev_Environment.jpg");
            log.debug("image URL: "+imageURL.getFile());
            
        	ImageIcon workbenchEnvIcon = Utils.createImageIcon("/images/caCORE_Workbench_App_Dev_Environment.jpg","Workbench Application Development Process");
//        	JLabel textLabel = new JLabel("Image and Text", icon, JLabel.CENTER);
        	JLabel workbenchEnvImage = new JLabel(workbenchEnvIcon);
            
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			//gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			//gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D; 
			gridBagConstraints10.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.gridx = 0;
			//gridBagConstraints20.insets = new java.awt.Insets(5, 5, 5, 5);
			//gridBagConstraints20.weighty = 1.0D;
			gridBagConstraints20.weightx = 1.0D; 
			gridBagConstraints20.gridwidth = 2;
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.gridx = 0;
			//gridBagConstraints30.insets = new java.awt.Insets(5, 5, 5, 5);
			gridBagConstraints30.weighty = 1.0D;
			gridBagConstraints30.weightx = 1.0D; 
			gridBagConstraints30.gridwidth = 1;
			
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints31.gridy = 3;
			gridBagConstraints31.gridx = 1;
			//gridBagConstraints31.insets = new java.awt.Insets(5, 5, 5, 5);
			gridBagConstraints31.weighty = 1.0D;
			gridBagConstraints31.weightx = 1.0D; 
			gridBagConstraints31.gridwidth = 1;
			
        	contentPanel = new JPanel();
        	contentPanel.setLayout(new GridBagLayout());
        	contentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	contentPanel.add(overviewTextPane, gridBagConstraints10);
        	contentPanel.add(workbenchEnvImage, gridBagConstraints20);
        	contentPanel.add(supportedWorkflowTextPane, gridBagConstraints30);
        	contentPanel.add(implementationPlanTextPane, gridBagConstraints31);
        	contentPanel.setBackground(defaultBackgroundColor);
//        	
//			//Put the editor pane in a scroll pane.
//        	contentPanel = new JScrollPane(overviewContentPanel);
//			contentPanel.setVerticalScrollBarPolicy(
//			                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//			editorScrollPane.setPreferredSize(new Dimension(250, 145));
//			editorScrollPane.setMinimumSize(new Dimension(10, 10));

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
        }
        return mainPanel;
    }
    
    private String getOverviewText() {
    	return "<html>"
    	+ "The caCORE Workbench is a simple GUI which facilitates "
    	+ "the creation of caBIG silver or gold compliant systems.  It "
    	+ "acts as an integrated platform and process guide, enabling "
    	+ "the user to more readily create a Data or Analytical service "
    	+ "on the Grid."
    	+ "<p>"
    	+ "The following diagram illustrates the role of the Workbench within the caBIG development process:"
    	+ "</html>";
    }
    
    
    private String getSupportedWorkflowText() {

    	return "<html>"
    	+ "Supported caBIG processes in this Workbench version include:<br>"
    	+ "<UL>"
    	+ "<LI>Create UML Model (Link Only)"
    	+ "<LI>Semantic Integration (Link Only)"
    	+ "<LI>Model Mapping (Link Only)"
    	+ "<LI>Create Application (Integrated)"
    	+ "<LI>Deploy Application (Integrated)"
    	+ "<LI>Create Grid Service (Link Only)"
    	+ "</UL>"
    	+ "</html>";
    }
    
    private String getImplementationPlanText() {

    	return "<html>"
    	+ "Phase II:<br>"
    	+ "<UL>"
    	+ "<LI>Integrate existing tools and their workflows"
    	+ "<LI>Maintain boundaries between individual tools"
    	+ "</UL>"
    	+ "Phase III:<br>"
    	+ "<UL>"
    	+ "<LI>Harmonize the workflows between the individual tools"
    	+ "<LI>Eliminate boundaries between tools to provide a unified view"
    	+ "</UL>"
    	+ "</html>";
    }
    
    private JEditorPane getTextPane(String text){
    	JEditorPane textPane = new JEditorPane();
    	textPane.setEditable(false);
    	textPane.setEnabled(true);
    	textPane.setMargin(new Insets(10, 10, 10, 10));

    	HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
    	//htmlEditorKit.setStyleSheet();
    	textPane.setEditorKit(htmlEditorKit);
    	textPane.setBackground(defaultBackgroundColor);
    	textPane.setText(text);
    	
    	return textPane;
    }

} 
