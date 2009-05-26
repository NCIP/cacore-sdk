package test.ws;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person;

import java.util.ArrayList;
import java.util.Collection;


public class O2OUnidirectionalWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Address.class);
		mappedKlasses.add(Person.class);
		
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
		Class targetClass = Person.class;
		Person criteria = new Person();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Person result = (Person)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
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
		Class targetClass = Address.class;
		Address criteria = new Address();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Address result = (Address)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getZip());	
		}	
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = Person.class;
		Person criteria = new Person();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Person result = (Person)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] addressResults = getAssociationResults(result, "livesAt", 0);
		assertEquals(0,addressResults.length);
		
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Class targetClass = Address.class;
		Person criteria = new Person();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);		
	}	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch1() throws Exception
	{
		Class targetClass = Person.class;
		Person criteria = new Person();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);			

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Person result = (Person)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] addressResults = getAssociationResults(result, "livesAt", 0);
		assertEquals(1,addressResults.length);
		
		Address address = (Address)addressResults[0];
		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());	
		assertEquals(new Integer(1),address.getId());
		
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Class targetClass = Address.class;
		Person criteria = new Person();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);			

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Address address = (Address)results[0];
		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());
		assertEquals(new Integer(1),address.getId());
	
	}
	
	public void testGetAssociation() throws Exception
	{
		Class targetClass = Person.class;
		Person criteria = new Person();

		Object[] results = getQueryObjectResults(targetClass, criteria);			

		assertNotNull(results);
		assertEquals(5,results.length);
		
		Person result = (Person)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] addressResults = getAssociationResults(result, "livesAt", 0);
		assertEquals(1,addressResults.length);
		
		Address address = (Address)addressResults[0];
		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());	
		assertEquals(new Integer(1),address.getId());		
	}	
}
