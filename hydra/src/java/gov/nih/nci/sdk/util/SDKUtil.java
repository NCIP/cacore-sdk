package gov.nih.nci.sdk.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;

/**
 * Helper methods for the SDK.
 * 
 * @author John Chen
 *
 */
public class SDKUtil {
	public static final String SDK_TAGS_FILE = "gov/nih/nci/sdk/util/sdk_tags";
	
	private static Collection<EAnnotation> sdkTags = new ArrayList<EAnnotation>();
	
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
	 * Returns all tag names on an EModelElement.
	 * 
	 * @param modelElement a EModelElement
	 * @return a set of annotation names
	 */
	public static Set<String> getAllTagNames(EModelElement modelElement) {
		Set<String> set = new HashSet<String>();
		EList<EAnnotation> anns = modelElement.getEAnnotations();
		Iterator<EAnnotation> itAnn = anns.iterator();
		while(itAnn.hasNext()) {
			set.add(itAnn.next().getSource());
		}
		return set;
	}
	
	/**
	 * Returns all SDK tag names on an EModelElement.
	 * 
	 * @param modelElement a EModelElement
	 * @return a set of annotation names
	 */
	public static Set<String> getAllSDKTagNames(EModelElement modelElement) {
		Set<String> set = new HashSet<String>();
		EList<EAnnotation> anns = modelElement.getEAnnotations();
		Iterator<EAnnotation> itAnn = anns.iterator();
		while(itAnn.hasNext()) {
			String tagName = itAnn.next().getSource();
			if (isSDKTag(tagName)) set.add(tagName);
		}
		return set;
	}
	
	/**
	 * Returns tag value of a tag of an EModelElement.
	 * 
	 * @param modelElement a EModelElement
	 * @param key tag name
	 * @return a value corresponding to the key
	 */
	public static String getTagValue(EModelElement modelElement, String key) {
		if (modelElement == null) return null;
		
		EAnnotation ann = modelElement.getEAnnotation(key);
		if (ann == null) return null;
		
		EMap<String, String> eMap = ann.getDetails();
		return (eMap != null)?eMap.get(key):null;
	}
	
	/**
	 * Returns a collection of EAnnotation instances for SDK.
	 * 
	 * @return
	 */
	public static Collection<EAnnotation> getSDKTags() {
		return sdkTags;
	}
	
	/**
	 * Sets a collection of EAnnotation instances for SDK.
	 * 
	 * @param annotations
	 */
	public static void setSDKTags(Collection<EAnnotation> annotations) {
		sdkTags = annotations;
	}
}
