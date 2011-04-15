package test.xsd;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS;


import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class O2MBidirectionalSelfassociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional Selfassociation XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.xsd";
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
		Class targetClass = MemberO2MBS.class;

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
		Class targetClass = MemberO2MBS.class;	

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
		Class targetClass = MemberO2MBS.class;
		Class associatedClass = MemberO2MBS.class;

		validateClassAssociationElements(targetClass, associatedClass, "friend","1","1");
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
		Class targetClass = MemberO2MBS.class;
		Class associatedClass = MemberO2MBS.class;

		validateClassAssociationElements(targetClass, associatedClass, "friendCollection","1","unbounded");
	}	
}
