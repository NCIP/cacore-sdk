package test.xsd;

import java.util.List;

import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author;
import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book;

import org.jdom.Document;
import org.jdom.Element;


public class M2MUnidirectionalXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Many to Many Unidirectional XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.xsd";
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
		Class targetClass = Author.class;

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
	public void testClassElement2() throws Exception
	{
		Class targetClass = Book.class;	

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
	public void testAssociationElements1() throws Exception
	{
		Class targetClass = Book.class;
		Class associatedClass = Author.class;

		validateClassAssociationElements(targetClass, associatedClass, "authorCollection","0","1");
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
		Class targetClass = Author.class;
		Class associatedClass = Book.class;

		//No Association from Author to Book
		String xpath = "/xs:schema/xs:complexType[@name='" + targetClass.getSimpleName() + "']" 
		+ "/xs:sequence/xs:element"
		+ "/xs:complexType/xs:sequence/xs:element[@ref='" + associatedClass.getSimpleName() + "']";

		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(0, elts.size());
	}	
}
