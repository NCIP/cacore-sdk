package test.xml.mapping;

import gov.nih.nci.cacoresdk.domain.other.levelassociation.Card;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Deck;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Hand;
import gov.nih.nci.cacoresdk.domain.other.levelassociation.Suit;

import org.jdom.Document;


public class LevelAssociationXMLMappingTest extends SDKXMLMappingTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Level Association XML Mapping Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String xmlMappingFileName = "xml-mapping.xml";
		doc = getDocument(xmlMappingFileName);
	}

	public Document getDoc() {
		return doc;
	}
	
	/**
	 * Uses xpath to query XMLMapping
	 * Verifies that common XMLMapping elements are present 
	 * 
	 * @throws Exception
	 */
	public void testCommonSchemaElements() throws Exception
	{
		validateCommonSchemaElements();
	}
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testClassElement1() throws Exception
	{
		Class targetClass = Hand.class;

		validateClassElements(targetClass,"id");

		validateFieldElement(targetClass, "id", "Integer");
	}	

	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testClassElement2() throws Exception
	{
		Class targetClass = Card.class;	

		validateFieldElement(targetClass, "id", "Integer");
		validateFieldElement(targetClass, "image", "String");
		validateFieldElement(targetClass, "Name", "String");
	}
	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testClassElement3() throws Exception
	{
		Class targetClass = Suit.class;	

		validateFieldElement(targetClass, "id", "Integer");
		validateFieldElement(targetClass, "name", "String");
	}	
	
	/**
	 * Verifies that the 'element' and 'complexType' elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testClassElement4() throws Exception
	{
		Class targetClass = Deck.class;	

		validateFieldElement(targetClass, "id", "Integer");
		validateFieldElement(targetClass, "name", "String");
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements1() throws Exception
	{
		Class targetClass = Hand.class;
		Class associatedClass = Card.class;

		validateClassAssociationElements(targetClass, associatedClass, "cardCollection",true);
	}	
	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements2() throws Exception
	{
		Class targetClass = Card.class;
		Class associatedClass = Suit.class;

		validateClassAssociationElements(targetClass, associatedClass, "suit",false);
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements3() throws Exception
	{
		Class targetClass = Suit.class;
		Class associatedClass = Card.class;

		validateClassAssociationElements(targetClass, associatedClass, "cardCollection",true);
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements4() throws Exception
	{
		Class targetClass = Suit.class;
		Class associatedClass = Deck.class;

		validateClassAssociationElements(targetClass, associatedClass, "deck",false);
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XMLMapping
	 * Verifies that the Class attributes are present in the XMLMapping
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements5() throws Exception
	{
		Class targetClass = Deck.class;
		Class associatedClass = Suit.class;

		validateClassAssociationElements(targetClass, associatedClass, "suitCollection",true);
	}		
}
