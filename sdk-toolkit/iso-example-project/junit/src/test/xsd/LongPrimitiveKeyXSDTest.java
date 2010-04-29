package test.xsd;

import gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class LongPrimitiveKeyXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "LongPrimitiveKey XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.other.primarykey.xsd";
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
		Class targetClass = LongPrimitiveKey.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Long");
		validateAttributeElement(targetClass, "name", "st");	

	}	
}
