package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Cash;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Payment;

import java.util.ArrayList;
import java.util.Collection;


public class ChildWithAssociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Child With Association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Bank.class);
		mappedKlasses.add(Cash.class);
		mappedKlasses.add(Credit.class);
		mappedKlasses.add(Payment.class);
		
		return mappedKlasses;
		
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
		Class targetClass = Payment.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);

		for(Object obj : results)
		{
			Payment result = (Payment)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getAmount());
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
			Class targetClass = Cash.class;
			Object criteria = targetClass.newInstance();

			Object[] results = getQueryObjectResults(targetClass, criteria);
	
			assertNotNull(results);
			assertEquals(2,results.length);
			
			for(Object obj : results)
			{
				Cash result = (Cash)obj;
				assertNotNull(result);
				assertNotNull(result.getId());
				assertNotNull(result.getAmount());
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
	
		Class targetClass = Credit.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);
			
		assertNotNull(results);
		assertEquals(2,results.length);
		
		for(Object obj:results)
		{
			Credit result = (Credit)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getAmount());
			assertNotNull(result.getCardNumber());
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
		Class targetClass = Bank.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for(Object obj:results)
		{
			Bank result = (Bank)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociationNestedSearch() throws Exception
	{
		Class targetClass = Payment.class;
		Cash criteria = new Cash();
		criteria.setAmount(new Integer(6));

		Object[] results = getQueryObjectResults(targetClass, criteria);
		
		assertNotNull(results);
		assertEquals(0,results.length);
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
		Class targetClass = Cash.class;
		Payment criteria = new Payment();
		criteria.setAmount(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Cash result = (Cash)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1), result.getId());
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

		Class targetClass = Payment.class;
		Cash criteria = new Cash();
		criteria.setAmount(new Integer(2));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Payment result = (Payment)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(2), result.getId());
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
		//Class targetClass = Credit.class;
		Class targetClass = Payment.class;
		Payment criteria = new Payment();
		criteria.setAmount(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Credit result = (Credit)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(4), result.getId());
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

		Class targetClass = Payment.class;
		Object criteria = Credit.class.newInstance();
		((Credit)criteria).setAmount(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Payment result = (Payment)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}
	
	public void testGetAssociation() throws Exception
	{
		Class targetClass = Credit.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Bank bank;
		for(Object obj : results)
		{
			Credit credit = (Credit)obj;
			assertNotNull(credit);
			assertNotNull(credit.getId());
			assertNotNull(credit.getAmount());
			
			Object[] associatedResults = getAssociationResults(credit, "issuingBank", 0);
			for (Object obj2 : associatedResults){
				bank = (Bank)obj2;
				assertNotNull(bank);
				assertNotNull(bank.getId());
				assertNotNull(bank.getName());
			}
		}
	}
}
