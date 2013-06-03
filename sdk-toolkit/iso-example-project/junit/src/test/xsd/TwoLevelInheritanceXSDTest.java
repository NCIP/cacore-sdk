/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class TwoLevelInheritanceXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Two Level Inheritance XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.xsd";
		doc = getDocument(schemaFileName);
	}

	public Document getDoc() {
		return doc;
	}
	
	/**
	 * Uses xpath to query XSD
	 * Verifies that common XSD elements are present 
	 * 
	 * @throws Exception
	 */
	public void testCommonSchemaElements() throws Exception
	{
		validateCommonSchemaElements();
	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement1() throws Exception
	{
		Class targetClass = Display.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "height", "int");
		validateAttributeElement(targetClass, "width", "int");	
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement2() throws Exception
	{
		Class targetClass = Monitor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "brand", "st");
	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement3() throws Exception
	{
		Class targetClass = CRTMonitor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "refreshRate", "int");
	}
		
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement4() throws Exception
	{
		Class targetClass = LCDMonitor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "dpiSupported", "int");

	}	
	
}
