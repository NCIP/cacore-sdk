/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.St;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class ChildWithAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Child With Association XML Data Test Case";
	}
	
//	/**
//	 * Uses Nested Search Criteria for search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testEntireObjectNestedSearch1() throws Exception
//	{
//		Payment searchObject = new Payment();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );
//
//		assertNotNull(results);
//		assertEquals(5,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			Payment result = (Payment)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getAmount());
//			
//			toXML(result);
//			
//			if (useGMETags){
//				validateClassElements(result,Class.forName(getClassName(result)).getSimpleName()+"GMEAlias");
//				validateAttribute(result,"idGMEAlias",result.getId());
//				validateAttribute(result,"amountGMEAlias",result.getAmount());
//			} else{
//				validateClassElements(result);
//				validateIso90210Element(result, "id", "extension", result.getId().getExtension());
//				validateIso90210Element(result, "amount", "value", result.getAmount().getValue());
//			}
//			
//			assertTrue(validateXMLData(result, searchObject.getClass()));
//
//			Payment result2 = (Payment)fromXML(result);
//			
//			assertNotNull(result2);
//			assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//			assertNotNull(result2.getAmount());
//		}
//	}
//
//	/**
//	 * Uses Nested Search Criteria for search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testEntireObjectNestedSearch2() throws Exception
//	{
//		Cash searchObject = new Cash();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash",searchObject );
//
//		assertNotNull(results);
//		assertEquals(2,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			Cash result = (Cash)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getAmount());
//			
//			toXML(result);
//			
//			if (useGMETags){
//				validateClassElements(result,"CashGMEAlias");
//				validateAttribute(result,"idGMEAlias",result.getId());
//				validateAttribute(result,"amountGMEAlias",result.getAmount());
//			} else{
//				validateClassElements(result);
//				validateIso90210Element(result, "id", "extension", result.getId().getExtension());
//				validateIso90210Element(result, "amount", "value", result.getAmount().getValue());
//			}
//			
//			assertTrue(validateXMLData(result, searchObject.getClass()));
//			
//			Cash result2 = (Cash)fromXML(result);
//			
//			assertNotNull(result2);
//			assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//			assertNotNull(result2.getAmount());
//		}
//	}
//
//	/**
//	 * Uses Nested Search Criteria for search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testEntireObjectNestedSearch3() throws Exception
//	{
//		Credit searchObject = new Credit();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );
//
//		assertNotNull(results);
//		assertEquals(2,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			Credit result = (Credit)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getAmount());
//			assertNotNull(result.getCardNumber());
//			
//			toXML(result);
//			
//			if (useGMETags){
//				validateClassElements(result,"CreditGMEAlias");
//				validateAttribute(result,"idGMEAlias",result.getId());
//				validateAttribute(result,"amountGMEAlias",result.getAmount());
//				validateAttribute(result,"cardNumberGMEAlias",result.getCardNumber());
//			} else{
//				validateClassElements(result);
//				validateIso90210Element(result, "id", "extension", result.getId().getExtension());
//				validateIso90210Element(result, "amount", "value", result.getAmount().getValue());
//				validateIso90210Element(result, "cardNumber", "value", result.getCardNumber().getValue());
//			}
//			
//			assertTrue(validateXMLData(result, searchObject.getClass()));
//
//			Credit result2 = (Credit)fromXML(result);
//			
//			assertNotNull(result2);
//			assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//			assertNotNull(result2.getAmount());
//			assertNotNull(result2.getCardNumber());
//		}
//	}
//	
//	/**
//	 * Uses Nested Search Criteria for search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testEntireObjectNestedSearch4() throws Exception
//	{
//		Bank searchObject = new Bank();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank",searchObject );
//
//		assertNotNull(results);
//		assertEquals(4,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			Bank result = (Bank)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getName());
//			
//			toXML(result);
//			
//			if (useGMETags){
//				validateClassElements(result,"BankGMEAlias");
//				validateAttribute(result,"idGMEAlias",result.getId());
//				validateAttribute(result,"nameGMEAlias",result.getName());
//			} else{
//				validateClassElements(result);
//				validateIso90210Element(result, "id", "extension", result.getId().getExtension());
//				validateIso90210Element(result, "name", "value", result.getName().getValue());
//			}
//			
//			assertTrue(validateXMLData(result, searchObject.getClass()));
//
//			Bank result2 = (Bank)fromXML(result);
//			
//			assertNotNull(result2);
//			assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//			assertNotNull(result2.getName());
//		}
//	}
//		
//
//	/**
//	 * Uses Nested Search Criteria for inheritance as association in search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testAssociationNestedSearch1() throws Exception
//	{
//		Payment searchObject = new Payment();
//		Int int1 = new Int();
//		int1.setValue(1);
//		searchObject.setAmount(int1);
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash",searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
//		
//		Cash result = (Cash)results.iterator().next();
//		toXML(result);
//		Cash result2 = (Cash)fromXML(result);
//
//		assertNotNull(result2);
//		assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//		assertEquals("1", result2.getId().getExtension());
//	}
//
//	/**
//	 * Uses Nested Search Criteria for inheritance as association in search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testAssociationNestedSearch2() throws Exception
//	{
//		Cash searchObject = new Cash();
//		Int int1 = new Int();
//		int1.setValue(2);
//		searchObject.setAmount(int1);
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
//		
//		Payment result = (Payment)results.iterator().next();
//		toXML(result);
//		Payment result2 = (Payment)fromXML(result);
//
//		assertNotNull(result2);
//		assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//		assertEquals("2", result2.getId().getExtension());
//	}
//
//	/**
//	 * Uses Nested Search Criteria for inheritance as association in search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testAssociationNestedSearch3() throws Exception
//	{
//		Payment searchObject = new Payment();
//		Int int1 = new Int();
//		int1.setValue(4);
//		searchObject.setAmount(int1);
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
//		
//		Credit result = (Credit)results.iterator().next();
//		toXML(result);
//		Credit result2 = (Credit)fromXML(result);
//
//		assertNotNull(result2);
//		assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//		assertEquals("4", result2.getId().getExtension());
//		
//	}
//
//	/**
//	 * Uses Nested Search Criteria for inheritance as association in search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws Exception
//	 */
//	public void testAssociationNestedSearch4() throws Exception
//	{
//		Credit searchObject = new Credit();
//		Int int1 = new Int();
//		int1.setValue(4);
//		searchObject.setAmount(int1);
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
//		
//		Payment result = (Payment)results.iterator().next();
//		toXML(result);
//		Payment result2 = (Payment)fromXML(result);
//
//		assertNotNull(result2);
//		assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
//		assertEquals("4", result2.getId().getExtension());		
//	}
//	

	public void testGetAssociation() throws Exception
	{
		Credit searchObject = new Credit();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );		
//		Collection results = getApplicationService().search(searchObject.getClass(),searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Bank bank;
		int counter = 0;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			counter++;
			Object proxyResult = i.next();
//			Credit result = (Credit)myUtil.convertFromProxy(proxyResult, true);
			Credit result = (Credit)proxyResult;			
			toXML(result);
//			toXML(result,counter);
			
			if (useGMETags){
				validateAssociation(result,"BankGMEAlias","bankAliasRolename", false);
			} else{
				validateAssociation(result,"Bank","issuingBank", false);
			}

			Credit result2 = (Credit)fromXML(result);
			bank = result2.getIssuingBank();
			
			assertNotNull(bank);
			assertNotNull(bank.getId().getExtension());
			assertNotNull(bank.getId().getRoot());
			assertNotNull(bank.getName());
		}
	}
}
