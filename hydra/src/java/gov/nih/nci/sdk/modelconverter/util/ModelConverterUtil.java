/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.modelconverter.util;

import gov.nih.nci.sdk.modelconverter.xmi2ecore.SDKNamespaceContext;
import gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
	
	/**
	 * Adds annotation to a model element.
	 * 
	 * @param modelElement The model element to add tag on
	 * @param tagName  name of the tag
	 * @param tagValue  value of the tag
	 * @return A newly added EAnnotation instance
	 */
	public static EAnnotation addAnnotationToModelElement(EModelElement modelElement, String tagName, String tagValue) {
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(tagName);
		ann.setEModelElement(modelElement);
		ann.getDetails().put(tagName, tagValue);
		return ann;
	}
	
	/**
	 * Gets tags for package level element.
	 * 
	 * @param xmiFilePath  The xmi file path
	 * @param ePackage  The package level element
	 * @return A newly added EAnnotation instance
	 */
	public static Map<String, String> getPackageTags(String xmiFilePath, EPackage ePackage) {
		if (ePackage == null) return new HashMap<String, String>();
		String packageName = ePackage.getName();
		String expression = "//xmi:XMI/xmi:Extension/elements/element[@name='" 
					+ packageName + "']/tags/tag";
		return getTags(xmiFilePath, expression);
	}
	
	/**
	 * Gets tags for class level element.
	 * 
	 * @param xmiFilePath  The xmi file path
	 * @param eClass  The class level element
	 * @return A newly added EAnnotation instance
	 */
	public static Map<String, String> getClassTags(String xmiFilePath, EClass eClass) {
		if (eClass == null) return new HashMap<String, String>();
		String className = eClass.getName();
		String expression = "//xmi:XMI/xmi:Extension/elements/element[@name='" 
					+ className + "']/tags/tag";
		return getTags(xmiFilePath, expression);
	}
	
	/**
	 * Gets tags for attribute level element.
	 * 
	 * @param xmiFilePath  The xmi file path
	 * @param attr  The attribute level element
	 * @return A newly added EAnnotation instance
	 */
	public static Map<String, String> getAttributeTags(String xmiFilePath, EAttribute attr) {
		if (attr == null) return new HashMap<String, String>();
		String attrName = attr.getName();
		String parentName = attr.getEContainingClass().getName();
		String expression = "//xmi:XMI/xmi:Extension/elements/element[@name='" 
					+ parentName + "']/attributes/attribute[@name='" 
					+ attrName + "']/tags/tag";
		return getTags(xmiFilePath, expression);
	}
	
	/**
	 * Gets tags for operation level element.
	 * 
	 * @param xmiFilePath  The xmi file path
	 * @param oper  The operation level element
	 * @return A newly added EAnnotation instance
	 */
	public static Map<String, String> getOperationTags(String xmiFilePath, EOperation oper) {
		if (oper == null) return new HashMap<String, String>();
		String operName = oper.getName();
		String parentName = oper.getEContainingClass().getName();
		String expression = "//xmi:XMI/xmi:Extension/elements/element[@name='" 
					+ parentName + "']/operations/operation[@name='" 
					+ operName + "']/tags/tag";
		return getTags(xmiFilePath, expression);
	}
	
	/**
	 * Gets tags for reference level element.
	 * 
	 * @param xmiFilePath  The xmi file path
	 * @param ref  The reference level element
	 * @return A newly added EAnnotation instance
	 */
	public static Map<String, String> getReferenceTags(String xmiFilePath, EReference ref) {
		if (ref == null) return new HashMap<String, String>();
		String refName = ref.getName();
		String parentName = ref.getEContainingClass().getName();
		String expression = "//xmi:XMI/xmi:Extension/elements/element[@name='" 
					+ parentName + "']/attributes/attribute[@name='" 
					+ refName + "']/tags/tag";
		return getTags(xmiFilePath, expression);
	}
	
	private static Map<String, String> getTags(String xmiFilePath, String expression) {
		Map<String, String> tagsMap = new HashMap<String, String>();
		XPathFactory xfactory = XPathFactory.newInstance();
		XPath xpath = xfactory.newXPath();
		xpath.setNamespaceContext(new SDKNamespaceContext());
		
		try {
			File xmlDocument = new File(xmiFilePath);
			InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));
			
			Object results = xpath.evaluate(expression, inputSource, XPathConstants.NODESET);
			if (results != null) {
				NodeList nodes = (NodeList) results;
				for (int i = 0; i < nodes.getLength(); i++) {
					Node item = nodes.item(i);
					if (item == null) continue;
					
					NamedNodeMap nnm = item.getAttributes();
					if (nnm == null) continue;
					
					String tagName = "";
					String tagValue = "";
					boolean foundName = false;
					int attrs = nnm.getLength();
					for (int j = 0; j < attrs; j++) {
						Node attr = nnm.item(j);
						if ("name".equals(attr.getNodeName())) {
							tagName = attr.getNodeValue();
							foundName = true;
						}
						else if ("value".equals(attr.getNodeName())) {
							tagValue = attr.getNodeValue();
						}
					}
					
					if (foundName) {
						tagsMap.put(tagName, tagValue);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("Error parsing file " + xmiFilePath + ": " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return tagsMap;
	}
}
