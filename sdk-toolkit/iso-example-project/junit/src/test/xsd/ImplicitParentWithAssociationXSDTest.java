/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class ImplicitParentWithAssociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Implicit Parent With Association XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.implicit.xsd";
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
		Class targetClass = Fish.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "genera", "st.nt");
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
		Class targetClass = AngelFish.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "finSize","int");

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
		Class targetClass = DiscusFish.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "primaryColor", "st");	
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
		Class targetClass = Tank.class;

		validateClassElements(targetClass);	
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
		Class targetClass = FishTank.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "id", "ii");
		validateSubclassAttributeElement(targetClass, "shape", "st");
		validateSubclassAttributeElement(targetClass, "numGallons", "int");
	}	
	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement6() throws Exception
	{
		Class targetClass = FreshwaterFishTank.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "filterModel", "st");	
	}	
	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement7() throws Exception
	{
		Class targetClass = SaltwaterFishTank.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "proteinSkimmerModel", "st");	
	}	
	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement8() throws Exception
	{
		Class targetClass = TankAccessory.class;

		validateClassElements(targetClass);
		validateAttributeElement(targetClass, "id", "ii");
		validateAttributeElement(targetClass, "name", "st");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testClassElement9() throws Exception
	{
		Class targetClass = Substrate.class;

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
	public void testAssociationElements2() throws Exception
	{
		Class targetClass = Fish.class;
		Class associatedClass = Tank.class;

		validateClassAssociationElements(targetClass, associatedClass, "tank","1","1");
	}

	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements4() throws Exception
	{
		Class targetClass = TankAccessory.class;
		Class associatedClass = Tank.class;

		validateClassAssociationElements(targetClass, associatedClass, "tankCollection","0","1");
	}
	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements5() throws Exception
	{
		Class targetClass = SaltwaterFishTank.class;
		Class associatedClass = Substrate.class;

		validateSubclassAssociationElements(targetClass, associatedClass, "substrateCollection","1","1");
	}	
}
