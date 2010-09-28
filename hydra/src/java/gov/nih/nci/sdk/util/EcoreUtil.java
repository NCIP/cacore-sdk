package gov.nih.nci.sdk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
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
	 * A business domain package name does not contain "<tt>iso21090</tt>".
	 */
	private static final String FORBIDDEN_PACKAGE_NAME_STRING_iso21090 = "iso21090";
	
	/**
	 * A business domain package name does not contain "<tt>java</tt>".
	 */
	private static final String FORBIDDEN_PACKAGE_NAME_STRING_java = "java";
	
	/**
	 * A regular package name does not contain these strings.
	 */
	private static final List<String> SKIPPED_PACKAGE_NAME_STRINGS = Arrays.asList("LogicalModel", "LogicalView", "EA_Model");
	
	
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
		if (rootEPackage == null || targetName == null || "".equals(targetName)) return null;
		List<EModelElement> list = new ArrayList<EModelElement>();
		searchModelElementsByClassName(list, rootEPackage, targetName);
		return (list.size() == 1)?list.get(0):null;
	}

	/**
	 * Gets the EClass related to the <tt>targetName</tt>. 
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
	public static EClass getEClass(EPackage _rootEPackage, String _className)
	{
		return (EClass) EcoreUtil.getModelElementForName(_rootEPackage, _className);
	}

	/**
	 * Given a <tt>_fullyQualifiedClassName</tt> this returns the
	 * package this class belongs to. 
	 * 
	 * Example <tt>_fullyQualifiedClassName</tt>: 
	 * <pre>
	 * gov.nih.nci.sdkexample.Organization
	 * </pre>
	 * 
	 * @param rootEPackage  root EPackage to search
	 * @param targetName  target ModelElement name
	 * @return "gov.nih.nci.sdkexample"
	 */
	public static String determinePackageName(String _fullyQualifiedClassName)
	{
		int lastPeriodIndex = _fullyQualifiedClassName.lastIndexOf(".");
		return (lastPeriodIndex > 0) ? _fullyQualifiedClassName.substring(0, lastPeriodIndex) : "";
	}

	/**
	 * Given a <tt>_fullyQualifiedClassName</tt> this returns the
	 * class' short name. 
	 * 
	 * Example <tt>_fullyQualifiedClassName</tt>: 
	 * <pre>
	 * gov.nih.nci.sdkexample.Organization
	 * </pre>
	 * 
	 * @param rootEPackage  root EPackage to search
	 * @param targetName  target ModelElement name
	 * @return "Organization"
	 */
	public static String determineClassName(String _fullyQualifiedClassName)
	{
		int lastPeriodIndex = _fullyQualifiedClassName.lastIndexOf(".");
		return (lastPeriodIndex > 0) ? _fullyQualifiedClassName.substring(lastPeriodIndex + 1, _fullyQualifiedClassName.length()) : _fullyQualifiedClassName;
	}
	
	/**
	 * Finds all ModelElements in a Package.
	 * 
	 * @param _modelElementList  list of ModelElements
	 * @param _rootEPackage  root EPackage to search
	 */
	public static void findAllEClassModelElements(List<EClassifier> _modelElementList, EPackage _rootEPackage)
	{
		if (_rootEPackage == null)
		{
			throw new IllegalArgumentException("Input rootEPackage cannot be null.");
		}
		
		for (EObject eObject: _rootEPackage.eContents())
		{
			if (eObject instanceof EClassImpl) { _modelElementList.add((EClassifier) eObject); }
			else if (eObject instanceof EPackage) { findAllEClassModelElements(_modelElementList, (EPackage) eObject); }
		}
	}

	/**
	 * Determines EClassifier's fully qualified package name.
	 * 
	 * @param _eClass  EClassifier
	 */
	public static String determinePackageName(EClassifier _eClassifier)
	{
		StringBuffer packageName = new StringBuffer();
		EPackage ePackage = null;

		if (_eClassifier != null)
		{
			ePackage = _eClassifier.getEPackage();
			java.util.Stack<String> stack = new java.util.Stack<String>();

			while (ePackage != null)
			{
				stack.push(ePackage.getName());
				ePackage = (EPackage) ePackage.getESuperPackage();
			}

			while (stack.isEmpty() == false)
			{
				packageName.append((String)stack.pop()).append(".");
			}
		}
		
		return packageName.toString().replaceAll("\\.$", "");
	}

	/**
	 * Determines EClassifier's fully qualified name.
	 * 
	 * @param _eClassifier  EClassifier
	 */
	public static String determineFullyQualifiedName(EClassifier _eClassifier)
	{
		return determinePackageName(_eClassifier) + "." + _eClassifier.getName();
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
	
	/**
	 * Gets all packages and their associated class names as a map. The key 
	 * of the map is a name of a business domain package, while the value is 
	 * a sorted set of all class names within the package. 
	 * 
	 * @param rootPackage  the EPackage to parse
	 * @return map
	 */
	public static Map<String, SortedSet<String>> getAllDomainPackageClassNamesMap(EPackage rootPackage) {
		Map<String, SortedSet<String>> domainPackageClassesMap = new TreeMap<String, SortedSet<String>>();
		
		Map<String, SortedSet<String>> map = getAllPackageClassNamesMap(rootPackage);
		for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			SortedSet<String> value = entry.getValue();
			if (key.indexOf(FORBIDDEN_PACKAGE_NAME_STRING_iso21090) != -1 ||
					key.indexOf(FORBIDDEN_PACKAGE_NAME_STRING_java) != -1) continue;
			domainPackageClassesMap.put(key, value);
		}
		
		return domainPackageClassesMap;
	}
	
	/**
	 * Gets all packages and their associated class names as a map. The key 
	 * of the map is a name of a package, while the value is 
	 * a sorted set of all class names within the package. 
	 * 
	 * @param rootPackage  the EPackage to parse
	 * @return map
	 */
	public static Map<String, SortedSet<String>> getAllPackageClassNamesMap(EPackage rootPackage) {
		List<EClass> ecs = getAllEClasses(rootPackage);
		
		Map<String, SortedSet<String>> pkgClassesMap = new TreeMap<String, SortedSet<String>>();
		for (EClass ec : ecs) {
			String pkgName = getPackage(ec);
			if (pkgClassesMap.containsKey(pkgName)) {
				pkgClassesMap.get(pkgName).add(ec.getName());
			}
			else {
				SortedSet<String> classNames = new TreeSet<String>();
				classNames.add(ec.getName());
				pkgClassesMap.put(pkgName, classNames);
			}
		}
		
		return pkgClassesMap;
	}
	
	/**
	 * Gets all EClass instances within the <tt>rootPackage</tt>.
	 * 
	 * @param rootPackage  the EPackage to parse
	 * @return a list of EClass instances
	 */
	public static List<EClass> getAllEClasses(EPackage rootPackage) {
		List<EClass> ecs = new ArrayList<EClass>();
		_findAllEClasses(rootPackage, ecs);
		return ecs;
	}
	
	private static void _findAllEClasses(EPackage epkg, List<EClass> list) {
		Iterator<EObject> pkgIter = epkg.eContents().iterator();
		EObject eo = null;
		while (pkgIter.hasNext()) {
			eo = pkgIter.next();
			if (eo instanceof EClassImpl) {
				list.add((EClass)eo);
			} else if (eo instanceof EPackage) {
				_findAllEClasses((EPackage)eo, list);
			}
		}
	}
	
	/**
	 * Gets the package name of a EClass <tt>ec</tt>.
	 * 
	 * @param ec the EClass instance
	 * @return package name string
	 */
	public static String getPackage(EClass ec) {
		List<String> names = new ArrayList<String>();
		EPackage epkg = ec.getEPackage();
		while(epkg != null) {
			String name = epkg.getName();
			if (name == null || "".equals(name) || SKIPPED_PACKAGE_NAME_STRINGS.contains(name)) break;
			names.add(name);
			epkg = epkg.getESuperPackage();
		}
		
		if (names.size() == 0) return "";
		
		StringBuilder sb = new StringBuilder();
		int total = names.size();
		for (int i = total - 1; i > 0; i--) {
			sb.append(names.get(i)).append(".");
		}
		if (total > 0) sb.append(names.get(0));
		return sb.toString();
	}
}
