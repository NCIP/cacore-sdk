/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS;


import org.jdom.Document;

import test.xsd.SDKXSDTestBase;

public class O2OBidirectionalSelfassociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "One to One Bidirectional Self Association XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.xsd";
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
		Class targetClass = MemberO2OBS.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "name", "String");	
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
		Class targetClass = MemberO2OBS.class;	

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "name", "String");		
	}
	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements1() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		Class associatedClass = MemberO2OBS.class;

		validateClassAssociationElements(targetClass, associatedClass, "mentor","0","1", false);
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
		Class targetClass = MemberO2OBS.class;
		Class associatedClass = MemberO2OBS.class;

		validateClassAssociationElements(targetClass, associatedClass, "me","0","1", false);
	}	
}
