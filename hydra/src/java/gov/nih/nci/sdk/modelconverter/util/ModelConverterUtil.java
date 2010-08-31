package gov.nih.nci.sdk.modelconverter.util;

import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter;

import org.eclipse.emf.ecore.EPackage;

/**
 * Helper methods for the converter.
 * 
 * @author John Chen
 *
 */
public class ModelConverterUtil {
	
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
