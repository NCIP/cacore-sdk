package gov.nih.nci.cacoresdk.workbench.portal.viewer;

import gov.nih.nci.cacoresdk.workbench.portal.panel.SdkInstallSettingsPanel;
import gov.nih.nci.cacoresdk.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cacoresdk.workbench.portal.validation.SdkInstallPropertiesValidator;

import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;


/**
 * Workbench Help Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A> Based upon "Bare Bones Browser Launch" - See http://www.centerkey.com/java/browser/
 * @created June, 2008
 */

public class HelpViewer extends WorkbenchViewerBaseComponent {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(HelpViewer.class);
	
    
	private static final String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
		"seamonkey", "galeon", "kazehakase", "mozilla", "netscape" };
	
	// Validation 
	private SdkInstallPropertiesValidator propsValidator = null;
	private List<PanelValidator> panelValidators = null;
    private ValidationResultModel validationModel = new DefaultValidationResultModel();

    // Buttons
    private JButton installButton = null;
    private JButton closeButton = null;

	/*
	 * Primary Panel definitions
	 */
	private JTabbedPane mainTabbedPane = null;
    private JPanel mainPanel = null;
    private JPanel buttonPanel = null;

	// Tab panel definitions
	private SdkInstallSettingsPanel sdkInstallSettingsPanel = null;
    
	// Constructor
    public HelpViewer() {
        super();

        openURL("https://cabig.nci.nih.gov/tools/caCORE_SDK");
        
        setVisible(false);
        dispose();
        
        try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			log.error("Error trying to close HelpViewer: ", e);
		}
    }

	public void openURL(String url) {
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
