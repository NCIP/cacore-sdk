package gov.nih.nci.sdk.modelconverter.util;

import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Help methods for the converter.
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
	
	/**
	 * Returns the root EPackage instance. 
	 * 
	 * @param ecoreFile ecore file name
	 * @return root EPackage
	 */
	public static EPackage readRootEPackageFromEcoreFile(String ecoreFile) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());
		Resource resource = resourceSet.getResource(
				URI.createFileURI(ecoreFile), true);

		if (resource == null || resource.getContents().size() == 0) {
			throw new IllegalArgumentException(
					"There is no ecore model found in " + ecoreFile
							+ ". Please verify if it is a valid ecore file.");
		}

		return (EPackage) resource.getContents().get(0);
	}
}
