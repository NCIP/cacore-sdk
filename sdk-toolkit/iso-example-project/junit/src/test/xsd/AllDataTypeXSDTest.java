/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.other.datatype.AllDataType;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;


public class AllDataTypeXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "AllDataType XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.other.datatype.xsd";
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
		Class targetClass = AllDataType.class;

		validateClassElements(targetClass);

		validateAttributeElement(targetClass, "id", "integer");
		validateAttributeElement(targetClass, "booleanPrimitiveValue", "boolean");	
		validateAttributeElement(targetClass, "booleanValue", "boolean");
		validateAttributeElement(targetClass, "characterPrimitiveValue", "string");
		validateAttributeElement(targetClass, "characterValue", "string");
		validateAttributeElement(targetClass, "clobValue", "string");
		validateAttributeElement(targetClass, "datePrimitiveValue", "Datetime");
		validateAttributeElement(targetClass, "dateValue", "Datetime");
		validateAttributeElement(targetClass, "doublePrimitiveValue", "Double");
		validateAttributeElement(targetClass, "doubleValue", "Double");
		validateAttributeElement(targetClass, "floatPrimitiveValue", "Float");
		validateAttributeElement(targetClass, "floatValue", "Float");
		validateAttributeElement(targetClass, "intValue", "integer");
		validateAttributeElement(targetClass, "intPrimitiveValue", "integer");
		validateAttributeElement(targetClass, "longValue", "Long");
		validateAttributeElement(targetClass, "longPrimitiveValue", "Long");
		validateAttributeElement(targetClass, "stringPrimitiveValue", "string");
		validateAttributeElement(targetClass, "stringValue", "string");		

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
		Class targetClass = AllDataType.class;

		String xpath = "/xs:schema/xs:complexType[@name='" + targetClass.getSimpleName() + "']" 
		+ "/xs:sequence/xs:element[@name='stringCollection']"
		+ "/xs:complexType/xs:sequence/xs:element[@name='string']";

		List<Element> elts = queryXSD(doc, xpath);
		assertEquals(1, elts.size());
		
		Element klassElt = elts.get(0);
		assertEquals(4, klassElt.getAttributes().size()); // name, type, minOccurs, maxOccurs
		assertEquals(klassElt.getAttributeValue("name"),"string");
		assertEquals(klassElt.getAttributeValue("type"),"xs:string");
		assertEquals(klassElt.getAttributeValue("minOccurs"),"0");
		assertEquals(klassElt.getAttributeValue("maxOccurs"),"unbounded");
	}	
}
