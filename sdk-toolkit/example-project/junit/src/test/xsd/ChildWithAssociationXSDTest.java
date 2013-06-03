/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xsd;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash;

import org.jdom.Document;

import test.xml.mapping.SDKXSDTestBase;

public class ChildWithAssociationXSDTest extends SDKXSDTestBase
{
	
	private Document doc = null;
	
	public static String getTestCaseName()
	{
		return "Child With Association XSD Test Case";
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		String schemaFileName = "gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.xsd";
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
		Class targetClass = Payment.class;
		
		if (useGMETags){
			validateClassElements(targetClass,"PaymentGMEAlias");
			validateAttributeElement(targetClass,"PaymentGMEAlias", "idGMEAlias", "Integer");
			validateAttributeElement(targetClass,"PaymentGMEAlias", "amountGMEAlias", "Integer");
			
		} else {
			validateClassElements(targetClass);
			validateAttributeElement(targetClass, "id", "Integer");
			validateAttributeElement(targetClass, "amount", "Integer");
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
			validateSubclassElements(targetClass,"CashGMEAlias","PaymentGMEAlias");
		} else {
			validateSubclassElements(targetClass);
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
			validateSubclassElements(targetClass,"CreditGMEAlias","PaymentGMEAlias");
			validateSubclassAttributeElement(targetClass,"CreditGMEAlias","PaymentGMEAlias", "cardNumberGMEAlias","String");
			
		} else {
			validateSubclassElements(targetClass);
			validateSubclassAttributeElement(targetClass, "cardNumber","String");
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
		
		if (useGMETags){
			validateClassElements(targetClass,"BankGMEAlias");
			validateAttributeElement(targetClass, "BankGMEAlias","idGMEAlias", "Integer");
			validateAttributeElement(targetClass, "BankGMEAlias","nameGMEAlias", "String");
		} else {
			validateClassElements(targetClass);
			validateAttributeElement(targetClass, "id", "Integer");
			validateAttributeElement(targetClass, "name", "String");
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
			validateSubclassAssociationElements(targetClass,"CreditGMEAlias", associatedClass,"BankGMEAlias","PaymentGMEAlias","bankAliasRolename","0","1", false);
		} else {
			validateSubclassAssociationElements(targetClass, associatedClass, "issuingBank","0","1", false);
		}

	}	
}
