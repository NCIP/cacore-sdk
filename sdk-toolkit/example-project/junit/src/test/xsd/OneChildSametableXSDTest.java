package test.xsd;


import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency;

import org.jdom.Document;

import test.xsd.SDKXSDTestBase;

public class OneChildSametableXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "One Child Same Table XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.xsd";
		doc = getDocument( schemaFileName);
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
		Class targetClass = Currency.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "country", "String");	
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
		Class targetClass = Note.class;	

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "value", "Integer");			
	}
	
}
