package test.gov.nih.nci.cacoresdk.domain.manytoone.unidirectional;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class M2OUnidirectionalTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch1() throws ApplicationException
	{
		Restaurant searchObject = new Restaurant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Restaurant result = (Restaurant)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch2() throws ApplicationException
	{
		Chef searchObject = new Chef();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chef result = (Chef)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		Chef searchObject = new Chef();
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		
		assertNull(result.getRestaurant());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws ApplicationException
	{
		Chef searchObject = new Chef();
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch1() throws ApplicationException
	{
		Chef searchObject = new Chef();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		
		Restaurant restaurant = result.getRestaurant();
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(restaurant.getName());
		assertEquals("1",restaurant.getId().getExtension());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch2() throws ApplicationException
	{
		Chef searchObject = new Chef();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Restaurant restaurant = (Restaurant)i.next();
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertEquals(restaurant.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(restaurant.getName());
		assertEquals("1",restaurant.getId().getExtension());
	}
	

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testNoAssociationCQL() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			CQLQuery cqlQuery = new CQLQuery();
			CQLObject target = new CQLObject();
			
			CQLAssociation association = new CQLAssociation();
			association.setName("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
			
			target.setName("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant");
			target.setAssociation(association);
			cqlQuery.setTarget(target);
	
			Collection results = getApplicationService().query(cqlQuery);
			assertNotNull(results);
			
		}
		catch(ApplicationException e)
		{
			flag = true;
		}
		
		assertTrue(flag);
	}

	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setSourceRoleName("restaurant");
		
		target.setName("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Restaurant restaurant = (Restaurant)i.next();
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertEquals(restaurant.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(restaurant.getName());
		assertEquals("1",restaurant.getId().getExtension());
	}	
	
	public void testOneAssociatedObjectHQL() throws ApplicationException {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"select chef.restaurant from gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef chef"
						+ " where chef.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant");
		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();
		Restaurant restaurant = (Restaurant) i.next();
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
		assertEquals(restaurant.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(restaurant.getName());
		assertEquals("1", restaurant.getId().getExtension());
	}
	
	public void testGetAssociation() throws ApplicationException
	{

		Chef searchObject = new Chef();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		Restaurant restaurant;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chef result = (Chef)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			
			if (new Integer(result.getId().getExtension()) != 4) { //Chef id=4 has no Restaurant associated with it
				restaurant = result.getRestaurant();
				assertNotNull(restaurant);
				assertNotNull(restaurant.getId());
				assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
				assertNotNull(restaurant.getName());
			}

		}
	}		
}
