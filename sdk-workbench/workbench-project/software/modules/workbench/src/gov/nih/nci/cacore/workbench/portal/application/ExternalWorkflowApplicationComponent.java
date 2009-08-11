package gov.nih.nci.cacore.workbench.portal.application;

import gov.nih.nci.cacore.workbench.portal.viewer.SemanticIntegrationViewer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.apache.log4j.Logger;

public abstract class ExternalWorkflowApplicationComponent extends JInternalFrame {
	
	private static final Logger log = Logger.getLogger(ExternalWorkflowApplicationComponent.class);
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Primary Panel definitions
	 */
    private JPanel mainPanel = null;
	protected JPanel contentPanel = null;
    private JPanel buttonPanel = null;
    
	private JSplitPane mainSplitPane = null;
	
	public ExternalWorkflowApplicationComponent() {
		//setSize(600, 700);
		setMaximizable(true);
		setIconifiable(false);
		setClosable(true);
		setResizable(true);
	}
	
    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    protected JPanel getButtonPanel(JButton[] buttons) {
        if (buttonPanel == null) {
            
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            
            int gridy = 0; 
            for (JButton button:buttons){
            	log.debug("* * * adding button: "+button.getText()+" for gridy: "+gridy);
            	Dimension minimumSize = new Dimension(151,31);
            	button.setMinimumSize(minimumSize);
            	button.setPreferredSize(minimumSize);
            	buttonPanel.add(button, getButtonContraints(gridy++));
            }

        }
        return buttonPanel;
    }
	
    
    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private GridBagConstraints getButtonContraints(int gridy) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        
        return gridBagConstraints;
    }
    
    protected JSplitPane getMainSplitPanel(JLabel descriptionLabel,JButton[] buttons){
    	
    	if (mainSplitPane == null){

            JScrollPane descriptionScrollPane = new JScrollPane(descriptionLabel);
            
            JScrollPane buttonPanelScrollPane = new JScrollPane(getButtonPanel(buttons));

        	//Create a split pane with the two scroll panes in it.
            mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
        	                           descriptionScrollPane, buttonPanelScrollPane);
            mainSplitPane.setOneTouchExpandable(false);
            mainSplitPane.setOneTouchExpandable(false);
            mainSplitPane.setDividerLocation(650);
            mainSplitPane.setDividerSize(5);
    	}
    	return mainSplitPane;
    }
    
    protected abstract JPanel getContentPanel();
    
    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    protected JPanel getMainPanel() {
        if (mainPanel == null) {
            
            GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
            gridBagConstraints10.fill = GridBagConstraints.BOTH;
            gridBagConstraints10.weighty = 1.0;
            gridBagConstraints10.weightx = 1.0;

            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.add(getContentPanel(), gridBagConstraints10);

        }
        return mainPanel;
    }

}
