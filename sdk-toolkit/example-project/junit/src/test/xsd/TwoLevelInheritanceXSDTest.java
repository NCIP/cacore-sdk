package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;

import org.jdom.Document;

import test.xsd.SDKXSDTestBase;

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

		validateAttributeElement(targetClass, "id", "Integer");
		validateAttributeElement(targetClass, "height", "Integer");
		validateAttributeElement(targetClass, "width", "Integer");	
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
		validateSubclassAttributeElement(targetClass, "brand", "String");
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
		validateSubclassAttributeElement(targetClass, "refreshRate", "Integer");
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
		validateSubclassAttributeElement(targetClass, "dpiSupported", "Integer");

	}	
	
}
