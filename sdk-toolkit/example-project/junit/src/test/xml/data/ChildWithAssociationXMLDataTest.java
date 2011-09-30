package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment;

import java.util.Collection;
import java.util.Iterator;

import test.xml.data.SDKXMLDataTestBase;

public class ChildWithAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Child With Association XML Data Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch1() throws Exception
	{
		Payment searchObject = new Payment();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Payment result = (Payment)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getAmount());
			
			toXML(result);
			
			if (useGMETags){
				validateClassElements(result,Class.forName(getClassName(result)).getSimpleName()+"GMEAlias");
				validateAttribute(result,"idGMEAlias",result.getId());
				validateAttribute(result,"amountGMEAlias",result.getAmount());
			} else{
				validateClassElements(result);
				validateAttribute(result,"id",result.getId());
				validateAttribute(result,"amount",result.getAmount());
			}
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Payment result2 = (Payment)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getAmount());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Cash searchObject = new Cash();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Cash result = (Cash)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getAmount());
			
			toXML(result);
			
			if (useGMETags){
				validateClassElements(result,"CashGMEAlias");
				validateAttribute(result,"idGMEAlias",result.getId());
				validateAttribute(result,"amountGMEAlias",result.getAmount());
			} else{
				validateClassElements(result);
				validateAttribute(result,"id",result.getId());
				validateAttribute(result,"amount",result.getAmount());
			}
			
			assertTrue(validateXMLData(result, searchObject.getClass()));
			
			Cash result2 = (Cash)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getAmount());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch3() throws Exception
	{
		Credit searchObject = new Credit();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Credit result = (Credit)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getAmount());
			assertNotNull(result.getCardNumber());
			
			toXML(result);
			
			if (useGMETags){
				validateClassElements(result,"CreditGMEAlias");
				validateAttribute(result,"idGMEAlias",result.getId());
				validateAttribute(result,"amountGMEAlias",result.getAmount());
				validateAttribute(result,"cardNumberGMEAlias",result.getCardNumber());
			} else{
				validateClassElements(result);
				validateAttribute(result,"id",result.getId());
				validateAttribute(result,"amount",result.getAmount());
				validateAttribute(result,"cardNumber",result.getCardNumber());
			}
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Credit result2 = (Credit)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getAmount());
			assertNotNull(result2.getCardNumber());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch4() throws Exception
	{
		Bank searchObject = new Bank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bank result = (Bank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			
			toXML(result);
			
			if (useGMETags){
				validateClassElements(result,"BankGMEAlias");
				validateAttribute(result,"idGMEAlias",result.getId());
				validateAttribute(result,"nameGMEAlias",result.getName());
			} else{
				validateClassElements(result);
				validateAttribute(result,"id",result.getId());
				validateAttribute(result,"name",result.getName());
			}
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Bank result2 = (Bank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}
		

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch1() throws Exception
	{
		Payment searchObject = new Payment();
		searchObject.setAmount(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Cash result = (Cash)results.iterator().next();
		toXML(result);
		Cash result2 = (Cash)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertEquals(new Integer(1), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch2() throws Exception
	{
		Cash searchObject = new Cash();
		searchObject.setAmount(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Payment result = (Payment)results.iterator().next();
		toXML(result);
		Payment result2 = (Payment)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertEquals(new Integer(2), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch3() throws Exception
	{
		Payment searchObject = new Payment();
		searchObject.setAmount(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Credit result = (Credit)results.iterator().next();
		toXML(result);
		Credit result2 = (Credit)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertEquals(new Integer(4), result2.getId());
		
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch4() throws Exception
	{
		Credit searchObject = new Credit();
		searchObject.setAmount(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Payment result = (Payment)results.iterator().next();
		toXML(result);
		Payment result2 = (Payment)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertEquals(new Integer(3), result2.getId());		
	}
	

	public void testGetAssociation() throws Exception
	{
		Credit searchObject = new Credit();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Bank bank;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Credit result = (Credit)i.next();
			toXML(result);
			
			if (useGMETags){
				validateAssociation(result,"BankGMEAlias","bankAliasRolename", true, false);
			} else{
				validateAssociation(result,"Bank","issuingBank", true, false);
			}

			Credit result2 = (Credit)fromXML(result);
			bank = result2.getIssuingBank();
			
			assertNotNull(bank);
			assertNotNull(bank.getId());
			assertNotNull(bank.getName());
		}
	}
}
