package gov.nih.nci.cacore.workbench.common;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.apache.axis.utils.XMLUtils;
import org.globus.wsrf.encoding.ObjectDeserializer;

public class Utils {
	
	private static final String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
		"seamonkey", "galeon", "kazehakase", "mozilla", "netscape" };

	public static Object deserializeDocument(String fileName, Class objectType)
	throws Exception {
		InputStream inputStream = null;

		inputStream = Utils.class.getResourceAsStream(fileName);
		org.w3c.dom.Document doc = XMLUtils.newDocument(inputStream);
		Object obj = ObjectDeserializer.toObject(doc.getDocumentElement(),
				objectType);
		inputStream.close();
		return obj;
	}
	
    public static String convertToYesNo(JCheckBox checkBox){
    	if(checkBox.isSelected())
    		return "Yes";

    	return "No";
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = Utils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * This method opens the default browser to the Workbench Help URL
     * Based upon "Bare Bones Browser Launch" - See http://www.centerkey.com/java/browser/
     */
	public static void openURL(String url) {
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
					"Error attempting to launch workbench help in web browser:\n" + e.toString());
		}
	}

}
