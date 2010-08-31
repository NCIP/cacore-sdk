package gov.nih.nci.sdk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Help methods for working with an Ecore model.
 * 
 * @author John Chen
 *
 */
public class EcoreUtil {
	
	/**
	 * Returns the root EPackage instance. 
	 * 
	 * @param ecoreFile Ecore file name
	 * @return root EPackage of the Ecore model
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
	
	/**
	 * Gets the ModelElement related to the <tt>targetName</tt>. 
	 * 
	 * Example <tt>targetName</tt>: 
	 * <pre>
	 * gov.nih.nci.sdkexample.Organization
	 * gov.nih.nci.sdkexample.Person
	 * </pre>
	 * 
	 * @param rootEPackage  root EPackage to search
	 * @param targetName  target ModelElement name
	 * @return the ModelElement
	 */
	public static EModelElement getModelElementForName(EPackage rootEPackage, String targetName) {
		List<EModelElement> list = new ArrayList<EModelElement>();
		searchModelElementsByClassName(list, rootEPackage, targetName);
		return (list.size() == 1)?list.get(0):null;
	}
	
	/**
	 * Finds all ModelElements related to the <tt>targetName</tt>.
	 * 
	 * Example <tt>targetName</tt>: 
	 * <pre>
	 * gov.nih.nci.sdkexample.Organization
	 * gov.nih.nci.sdkexample.Person
	 * </pre>
	 * 
	 * @param results  list of ModelElements
	 * @param rootEPackage  root EPackage to search
	 * @param targetName  target ModelElement name
	 */
	public static void searchModelElementsByClassName(List<EModelElement> results, EPackage rootEPackage, String targetName) {
		if (targetName == null) 
			throw new IllegalArgumentException("Input targetName cannot be null.");
		
		if (rootEPackage == null) 
			throw new IllegalArgumentException("Input rootEPackage cannot be null.");
		
		int firstDotIndex = targetName.indexOf('.');
		if (firstDotIndex != -1) {
			String firstPart = targetName.substring(0, firstDotIndex);
			String remainingName = targetName.substring(firstDotIndex + 1);
			
			Iterator<EObject> pkgIter = rootEPackage.eContents().iterator();
			EObject eo = null;
			while (pkgIter.hasNext()) {
				eo = pkgIter.next();
				
				if (eo instanceof EClassImpl) {
					EClass tmp = (EClassImpl) eo;
					if (targetName.equals(tmp.getName())) {
						results.add(tmp);
					}
				} else if (eo instanceof EPackage) {
					EPackage tmp = (EPackage) eo;
					if (tmp.getName().equals(firstPart)) {
						searchModelElementsByClassName(results, tmp, remainingName);
					}
					else {
						searchModelElementsByClassName(results, tmp, targetName);
					}
				}
			}
		}
		else {
			Iterator<EObject> pkgIter = rootEPackage.eContents().iterator();
			EObject eo = null;
			while (pkgIter.hasNext()) {
				eo = pkgIter.next();
				
				if (eo instanceof EClassImpl) {
					EClass tmp = (EClassImpl) eo;
					if (targetName.equals(tmp.getName())) {
						results.add(tmp);
					}
				} else if (eo instanceof EPackage) {
					searchModelElementsByClassName(results, (EPackage) eo, targetName);
				}
			}
		}
	}
}
