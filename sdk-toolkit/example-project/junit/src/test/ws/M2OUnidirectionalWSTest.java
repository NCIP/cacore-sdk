package test.ws;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant;

import java.util.ArrayList;
import java.util.Collection;


public class M2OUnidirectionalWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Chef.class);
		mappedKlasses.add(Restaurant.class);
		
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
		Class targetClass = Restaurant.class;
		Restaurant criteria = new Restaurant();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Restaurant result = (Restaurant)obj;
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
		Class targetClass = Chef.class;
		Chef criteria = new Chef();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Chef result = (Chef)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
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
		Class targetClass = Chef.class;
		Chef criteria = new Chef();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Chef result = (Chef)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] restaurantResults = getAssociationResults(result, "restaurant", 0);
		assertEquals(0,restaurantResults.length);			
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
		Class targetClass = Restaurant.class;
		Chef criteria = new Chef();
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
		Class targetClass = Chef.class;
		Chef criteria = new Chef();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Chef result = (Chef)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] restaurantResults = getAssociationResults(result, "restaurant", 0);
		Restaurant restaurant = (Restaurant)restaurantResults[0];
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertNotNull(restaurant.getName());
		assertEquals(new Integer(1),restaurant.getId());
		
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
		Class targetClass = Restaurant.class;
		Chef criteria = new Chef();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Restaurant result = (Restaurant)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertEquals(new Integer(1),result.getId());		
	}
	

	public void testGetAssociation() throws Exception
	{
		Class targetClass = Chef.class;
		Chef criteria = new Chef();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		Chef result = (Chef)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		result = null;
		for(int i=0;i<results.length;i++)
		{
			if(((Chef)results[i]).getId().equals(1))
				result = (Chef)results[i];
		}
		assertNotNull(result);
		Object[] restaurantResults = getAssociationResults(result, "restaurant", 0);
		assertNotNull(restaurantResults);
		assertEquals(1,restaurantResults.length);
		Restaurant restaurant = (Restaurant)restaurantResults[0];
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertNotNull(restaurant.getName());
		
	}		
}
