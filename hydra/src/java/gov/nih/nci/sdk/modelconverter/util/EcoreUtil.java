package gov.nih.nci.sdk.modelconverter.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * This works.
 * 
 */
public class EcoreUtil {
	
	/**
	 * Returns the root EPackage instance. 
	 * 
	 * @param fileName ecore file name
	 * @return root EPackage
	 */
	public static EPackage getRootEPackage(String fileName) {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("ecore", new EcoreResourceFactoryImpl());
		Resource resource = resourceSet.getResource(
				URI.createFileURI(fileName), true);

		if (resource == null || resource.getContents().size() == 0) {
			throw new IllegalArgumentException(
					"There is no ecore model found in " + fileName
							+ ". Please verify if it is a valid ecore file.");
		}

		return (EPackage) resource.getContents().get(0);
	}
}
