package gov.nih.nci.sdk.modelconverter;

import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter;

import org.eclipse.emf.ecore.EPackage;

/**
 * ModelConverterFactory class.
 * 
 * @author John Chen
 *
 */
public class ModelConverterFactory {
	
	/**
	 * Converts UML model inside the xmi file to Ecore model.
	 * 
	 * @param xmiFilePath
	 *            full file path to the xmi file.
	 * @return a root instance of EPackage inside the xmi file or <tt>null</tt>
	 *         if there is none.
	 * @throws Exception
	 */
	public static EPackage convertXMI2Ecore(String xmiFilePath) throws Exception {
		return (new XMI2EcoreModelConverter()).convert(xmiFilePath);
	}
}
