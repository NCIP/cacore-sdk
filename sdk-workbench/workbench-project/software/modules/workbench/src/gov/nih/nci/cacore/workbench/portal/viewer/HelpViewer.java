package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	
	private static final String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
		"seamonkey", "galeon", "kazehakase", "mozilla", "netscape" };

	/*
	 * Primary Panel definitions
	 */
    private JPanel mainPanel = null;
    
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
        setTitle("Workbench Help Viewer");
        
        openURL(HELP_URL);

    }
    
    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getMainPanel() {
        if (mainPanel == null) {
            
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.CENTER;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridwidth = 3;
			gridBagConstraints10.weightx = 1.0D;

            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Workbench User Manual",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));
            
            JLabel helpLabel = new JLabel();
            helpLabel.setText("If the default browser does not automatically open to the workbench user manual, go to " + HELP_URL + " for more information.");
            mainPanel.add(helpLabel, gridBagConstraints10);
        }
        return mainPanel;
    }
    
    /**
     * This method opens the default browser to the Workbench Help URL
     * Based upon "Bare Bones Browser Launch" - See http://www.centerkey.com/java/browser/
     */
	private void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Mac OS")) {
				Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
				Method openURL = fileMgr.getDeclaredMethod("openURL",
						new Class[] {String.class});
				openURL.invoke(null, new Object[] {url});
			}
			else if (osName.startsWith("Windows"))
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			else { //assume Unix or Linux
				boolean found = false;
				for (String browser : browsers)
					if (!found) {
						found = Runtime.getRuntime().exec(
								new String[] {"which", browser}).waitFor() == 0;
						if (found)
							Runtime.getRuntime().exec(new String[] {browser, url});
					}
				if (!found)
					throw new Exception(Arrays.toString(browsers));
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error attempting to launch web browser\n" + e.toString());
		}
	}

} 
