package gov.nih.nci.sdk.modelconverter.util;

import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter;

import java.util.ResourceBundle;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;

/**
 * Helper methods for the converter.
 * 
 * @author John Chen
 *
 */
public class ModelConverterUtil {
	public static final String SDK_TAGS_FILE = "gov/nih/nci/sdk/modelconverter/util/sdk_tags";
	private static Set<String> allTagNames = null;
	
	/**
	 * Returns a set of SDK tag names.
	 * 
	 * @return a set of SDK tag names
	 */
	static Set<String> getAllSDKTagNames() {
		ResourceBundle bundle = ResourceBundle.getBundle(SDK_TAGS_FILE);
		Set<String> keys = bundle.keySet();
		return keys;
	}
	
	/**
	 * Checks if it is a SDK defined tag name.
	 * 
	 * @return true of it is a SDK defined tag name
	 */
	public static boolean isSDKTag(String name) {
		if (name == null) 
			throw new IllegalArgumentException("Input name cannot be null.");
		
		if (allTagNames == null) {
			allTagNames = getAllSDKTagNames();
		}
		
		return allTagNames.contains(name);
	}
	
	/**
	 * Returns the root EPackage instance. 
	 * 
	 * @param xmiFile xmi file name
	 * @return root EPackage
	 */
	public static EPackage getEPackageFromXMIFile(String xmiFile) {
		EPackage rootEPackage = null;
		XMI2EcoreModelConverter test = new XMI2EcoreModelConverter();
		try {
			rootEPackage = test.convert(xmiFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Failed to read test model xmi file: " + ex.getMessage());
		}
		return rootEPackage;
	}
}
