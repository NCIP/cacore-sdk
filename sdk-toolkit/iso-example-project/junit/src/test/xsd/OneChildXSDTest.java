/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class OneChildXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "One Child XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.onechild.xsd";
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
		Class targetClass = Mammal.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "hairColor", "st");	
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
		Class targetClass = Human.class;	

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "diet", "st");			
	}
	
}
