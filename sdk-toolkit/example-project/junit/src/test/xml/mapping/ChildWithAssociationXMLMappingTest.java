/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.mapping;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment;

import org.jdom.Document;


public class ChildWithAssociationXMLMappingTest extends SDKXMLMappingTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Child With Association XML Mapping Test Case";
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
		Class targetClass = Payment.class;
	
		if (useGMETags){
			validateClassElements(targetClass,"PaymentGMEAlias","id");
			validateFieldElement(targetClass, "id","idGMEAlias","Integer");
			validateFieldElement(targetClass, "amount","amountGMEAlias", "Integer");
		} else{
			validateClassElements(targetClass, "id");
			validateFieldElement(targetClass, "id", "Integer");
			validateFieldElement(targetClass, "amount", "Integer");
		}
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
		Class targetClass = Cash.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"CashGMEAlias","id");
		} else{
			validateSubclassElements(targetClass,"id");
		}

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
		Class targetClass = Credit.class;

		if (useGMETags){
			validateSubclassElements(targetClass,"CreditGMEAlias","id");
			validateFieldElement(targetClass, "cardNumber","cardNumberGMEAlias","String");
		} else{
			validateSubclassElements(targetClass,"id");
			validateFieldElement(targetClass, "cardNumber","String");
		}
		
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
		Class targetClass = Bank.class;

;
		
		if (useGMETags){
			validateClassElements(targetClass,"BankGMEAlias","id");
			validateFieldElement(targetClass, "id","idGMEAlias","Integer");
			validateFieldElement(targetClass, "name","nameGMEAlias","String");
		} else{
			validateClassElements(targetClass,"id");
			validateFieldElement(targetClass, "id", "Integer");
			validateFieldElement(targetClass, "name", "String");
		}
	}	
	
	/**
	 * Verifies that association elements 
	 * corresponding to the Class are present in the XSD
	 * Verifies that the Class attributes are present in the XSD
	 * 
	 * @throws Exception
	 */
	public void testAssociationElements() throws Exception
	{
		Class targetClass = Credit.class;
		Class associatedClass = Bank.class;
		
		if (useGMETags){
			validateClassAssociationElements(targetClass,"CreditGMEAlias", associatedClass,"BankGMEAlias","issuingBank","bankAliasRolename", false);
		} else{
			validateClassAssociationElements(targetClass, targetClass.getSimpleName(), associatedClass, "issuingBank", "issuingBank", null, false);
		}
	}	
}
