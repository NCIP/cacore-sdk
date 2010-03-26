package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created June, 2008
 */
public class HelpViewer extends WorkbenchViewerBaseComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(HelpViewer.class);
	
	private static final String HELP_URL = ResourceManager.getWorkbenchHelpUrl();
	private static final String TOOLS_SITE_URL = ResourceManager.getWorkbenchToolsSiteUrl();
	private static final String SDK_GUIDE_URL = ResourceManager.getSdkGuideUrl();	
	private static final String WORKBENCH_GUIDE_URL = ResourceManager.getWorkbenchGuideUrl();
	
    // Buttons
    private JButton learnMoreButton = null;
    private JButton sdkGuideButton = null;    
    private JButton workbenchGuideButton = null;
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
    	// Always refresh help content from URL; this allows for dynamic update of help information
        // if (helpPanel == null) {

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
            
            log.debug("* * * javax.net.ssl.trustStore: " +System.getProperty("javax.net.ssl.trustStore"));

            
// The following code is left here for future reference:            
//            URL helpURL = null;
//            try {
//            	helpURL = new URL(HELP_URL);
//            } catch (MalformedURLException e) {
//            	e.printStackTrace();
//            } catch (Exception e){
//            	e.printStackTrace();
//            }		
//            URL helpURL = HelpViewer.class.getResource(HELP_HTML_FILE_URL);
//
//            if (helpURL != null) {
//            	try {
//            		helpEditorPane.setPage(helpURL);
//            		helpEditorPane.setPage(HELP_URL);
//            	} catch (IOException e) {
//            		// TODO ::
//            		System.err.println("Attempted to read a bad URL: " + HELP_URL);
//            		e.printStackTrace();
//            	}
//            }
            
            try {
            	log.debug("Attempting to read help file from  URL: " + HELP_URL);
            	helpEditorPane.setPage(new URL(HELP_URL));
//              InputStream is = HelpViewer.class.getResourceAsStream(HELP_URL);
//				helpEditorPane.setText(convertStreamToString(is));
			} catch (Exception e) {
				// Attempt to read packaged help file instead
				log.error("Error:  Unable to read help file from URL for the following reason:", e);
				log.debug("Attempting to read packaged help file instead");
				InputStream is = CreateGridServiceViewer.class.getResourceAsStream("/html/Help.html");
				try {
					helpEditorPane.setText(convertStreamToString(is));
				} catch (Exception e1) {
					helpEditorPane.setText("<html><i>Unable to display Workbench Help information at this time.</i></html>");
				}
			}

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

        //}
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
            helpLabel.setText("To open the caCORE Workbench Tools site, click the 'Learn More' button below, or go to " + TOOLS_SITE_URL + ".");
            
            // TODO :: add helpLabel
        	
            buttonPanel = new JPanel();
            buttonPanel.add(getLearnMoreButton(), null);
            buttonPanel.add(getSdkGuideButton(), null);
            buttonPanel.add(getWorkbenchGuideButton(), null);
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
    private JButton getLearnMoreButton() {
        if (learnMoreButton == null) {
            learnMoreButton = new JButton();
            learnMoreButton.setToolTipText("Click the 'Learn More' button to open the caCORE Workbench Tool site, or go to " + TOOLS_SITE_URL + ".");
            learnMoreButton.setText("Learn More");
            learnMoreButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            learnMoreButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(TOOLS_SITE_URL);
                }
            });
        }

        return learnMoreButton;
    }
    
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getSdkGuideButton() {
        if (sdkGuideButton == null) {
        	sdkGuideButton = new JButton();
        	sdkGuideButton.setToolTipText("Click the 'SDK Guide' button to open the caCORE SDK Programmer's Guide, or go to " + SDK_GUIDE_URL + ".");
        	sdkGuideButton.setText("SDK Guide");
        	sdkGuideButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	sdkGuideButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(SDK_GUIDE_URL);
                }
            });
        }

        return sdkGuideButton;
    }    
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getWorkbenchGuideButton() {
        if (workbenchGuideButton == null) {
        	workbenchGuideButton = new JButton();
        	workbenchGuideButton.setToolTipText("Click the 'Workbench Guide' button to open the caCORE Workbench User's Guide, or go to " + WORKBENCH_GUIDE_URL + ".");
        	workbenchGuideButton.setText("Workbench Guide");
        	workbenchGuideButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
        	workbenchGuideButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	Utils.openURL(WORKBENCH_GUIDE_URL);
                }
            });
        }

        return workbenchGuideButton;
    }    
    
    public String convertStreamToString(InputStream is) throws Exception {

    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();

    	String line = null;
    	try {
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    		
    		log.debug("* * * converted input stream[length=" + sb.length() + "]: " + sb.toString());
    	} catch (IOException e) {
    		log.error("Error reading 'Help' HTML file: ",e);
    		throw e;
    	} finally {
    		try {
    			is.close();
    		} catch (IOException e) {
    			log.error("Error closing input stream after reading 'Help' HTML file:: ",e);
    			throw e;
    		}
    	}

    	return sb.toString();
    }

} 
