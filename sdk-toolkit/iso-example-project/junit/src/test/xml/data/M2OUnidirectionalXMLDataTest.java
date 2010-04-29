package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import test.xml.mapping.SDKXMLDataTestBase;

public class M2OUnidirectionalXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional XML Data Test Case";
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
		Restaurant searchObject = new Restaurant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Restaurant result = (Restaurant)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Restaurant result2 = (Restaurant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
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
		Chef searchObject = new Chef();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chef result = (Chef)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Chef result2 = (Chef)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
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
		Chef searchObject = new Chef();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		toXML(result);
		Chef result2 = (Chef)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
	
		assertNull(result2.getRestaurant());
	}
	
	public void testAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef where id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		toXML(result);
		Chef result2 = (Chef)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
	
		assertNull(result2.getRestaurant());
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
		Chef searchObject = new Chef();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		toXML(result);
		Chef result2 = (Chef)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		Restaurant restaurant = result2.getRestaurant();
		assertNotNull(restaurant);
		assertNotNull(restaurant.getId());
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
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Chef searchObject = new Chef();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Restaurant restaurant = (Restaurant)i.next();
		toXML(restaurant);
		Restaurant result2 = (Restaurant)fromXML(restaurant);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		assertEquals("1",result2.getId().getExtension());
	}

	public void testGetAssociation() throws Exception
	{

		Chef searchObject = new Chef();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		Restaurant restaurant;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chef result = (Chef)i.next();
			toXML(result);
			Chef result2 = (Chef)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			
			if (new Integer(result2.getId().getExtension()) != 4) { //Chef id=4 has no Restaurant associated with it
				restaurant = result2.getRestaurant();
				
				validateAssociation(result,"Restaurant","restaurant");
				
				assertNotNull(restaurant);
				assertNotNull(restaurant.getId());
				assertNotNull(restaurant.getName());
			}

		}
	}		
}
