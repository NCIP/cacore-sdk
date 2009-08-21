package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.HTMLEditorKit;

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
	private static final String HELP_HTML_FILE = "SDKWorkbench.html";
	private static final String HELP_RTF_FILE = "SDKWorkbench.rtf";
	private static final String HELP_RTF_FILE_URL = "/"+HELP_RTF_FILE;
	private static final String HELP_HTML_FILE_URL = "/SDKWorkbench_files/"+HELP_HTML_FILE;
	
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
    }
    
    /**
     * This method initializes this Viewer
     */
    private void initialize() {
     
        setContentPane(getMainPanel());
        setFrameIcon(LookAndFeel.getGenerateApplicationIcon());
        setTitle("Workbench Help");

    }
    
    /**
     * This method initializes the Project Settings jPanel
     */
    public JPanel getHelpPanel() {
        if (helpPanel == null) {

			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 3;
			gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.weightx = 1.0D; 

            JEditorPane helpEditorPane = new JEditorPane();
            helpEditorPane.setEditable(false);
            helpEditorPane.setEnabled(true);
            helpEditorPane.setEditorKit(new HTMLEditorKit());
            
//          URL helpURL = null;
//			try {
//				helpURL = new URL(HELP_URL);
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e){
//				e.printStackTrace();
//			}
			
            URL helpURL = HelpViewer.class.getResource(HELP_HTML_FILE_URL);
            
//            if (helpURL != null) {
//                try {
//
//				helpEditorPane.setPage(helpURL);
////                    helpEditorPane.setPage(HELP_URL);
//                } catch (IOException e) {
//                	// TODO ::
//                    System.err.println("Attempted to read a bad URL: " + HELP_URL);
//                    e.printStackTrace();
//                }
//            }
            
            helpEditorPane.setText("<html><i>Add Help content here.</i></html>");
            
//            } else {
//            	// TODO ::
//                System.err.println("Couldn't find file: "+HELP_RTF_FILE_URL);
//            }

            //Put the editor pane in a scroll pane.
            JScrollPane editorScrollPane = new JScrollPane(helpEditorPane);
            editorScrollPane.setVerticalScrollBarPolicy(
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            editorScrollPane.setSize(new Dimension(900, 500));
            editorScrollPane.setPreferredSize(new Dimension(900, 500));
            editorScrollPane.setMinimumSize(new Dimension(900, 500));
            
        	helpPanel = new JPanel();
        	helpPanel.setLayout(new GridBagLayout());
        	helpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workbench Help",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
        	helpPanel.add(editorScrollPane, gridBagConstraints10);

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
        	
            JLabel helpLabel = new JLabel();
            helpLabel.setText("To open the caCORE Workbench Tool site, click the 'Open' button below, or go to " + HELP_URL + ".");
            
            // TODO :: add helpLabel
        	
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
            openButton.setText("Learn More");
            openButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            openButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(HELP_URL);
                }
            });
        }

        return openButton;
    }

} 
