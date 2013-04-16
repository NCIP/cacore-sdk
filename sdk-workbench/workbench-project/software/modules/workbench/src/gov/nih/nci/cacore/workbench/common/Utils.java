/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.common;

import gov.nih.nci.cacore.workbench.portal.panel.LogViewerPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.apache.axis.utils.XMLUtils;
import org.apache.log4j.Logger;
import org.globus.wsrf.encoding.ObjectDeserializer;

public class Utils {
	
	private static final Logger log = Logger.getLogger(Utils.class);
	
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
	
    public static String convertStreamToString(InputStream is) {
    	return convertStreamToString(is,false);
    }
    
    public static String convertStreamToString(InputStream is, boolean insertBreak) {

    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();

    	String line = null;
    	try {
    		if (insertBreak){
        		while ((line = reader.readLine()) != null) {
        			sb.append(line + "<br>");
        		}
    		} else{
        		while ((line = reader.readLine()) != null) {
        			sb.append(line + "\n");
        		}
    		}

    		
    		log.debug("* * * converted input stream[length=" + sb.length() + "]: " + sb.toString());
    	} catch (IOException e) {
    		log.error("Error reading file: ",e);
    		return "Error reading file: " + e.getMessage();
    	} finally {
    		try {
    			is.close();
    		} catch (IOException e) {
    			log.error("Error closing input stream after reading file: ",e);
    			return "Error reading file: " + e.getMessage();
    		}
    	}

    	return sb.toString();
    }

}
