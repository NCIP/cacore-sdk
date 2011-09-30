package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent;

import org.jdom.Document;

import test.xsd.SDKXSDTestBase;

public class MultipleChildXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Multiple Child XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.xsd";
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
		Class targetClass = Student.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "name", "String");	
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
		Class targetClass = GraduateStudent.class;

		validateSubclassElements(targetClass);
		validateSubclassAttributeElement(targetClass, "projectName","String");
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
		Class targetClass = UndergraduateStudent.class;	

		validateSubclassElements(targetClass);
	}
	
}
