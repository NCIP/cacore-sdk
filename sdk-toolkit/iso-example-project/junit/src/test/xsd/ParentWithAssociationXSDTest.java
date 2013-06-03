/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class ParentWithAssociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Parent With Association XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.xsd";
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
		Class targetClass = Professor.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "name", "st");	
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Subclass are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement2() throws Exception
	{
		Class targetClass = AssociateProfessor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "yearsServed","int");

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
		Class targetClass = TenuredProfessor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "tenuredYear","int");
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
		Class targetClass = AssistantProfessor.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "joiningYear","int");
	}
		
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement5() throws Exception
	{
		Class targetClass = Assistant.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "name", "st");	
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements() throws Exception
	{
		Class targetClass = Professor.class;
		Class associatedClass = Assistant.class;

		validateClassAssociationElements(targetClass, associatedClass, "assistantCollection","0","1");
	}	
}
