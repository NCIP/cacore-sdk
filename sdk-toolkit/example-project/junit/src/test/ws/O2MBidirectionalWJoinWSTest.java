/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger;

import java.util.ArrayList;
import java.util.Collection;


public class O2MBidirectionalWJoinWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Flight.class);
		mappedKlasses.add(Passanger.class);
		
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
		Class targetClass = Flight.class;
		Flight criteria = new Flight();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Flight result = (Flight)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getDestination());	
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
		Class targetClass = Passanger.class;
		Passanger criteria = new Passanger();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			Passanger result = (Passanger)obj;
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
		Class targetClass = Flight.class;
		Flight criteria = new Flight();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Flight result = (Flight)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getDestination());	
		
		Object[] passangerCollection = getAssociationResults(result, "passangerCollection", 0);
		assertEquals(0,passangerCollection.length);
		
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
		Class targetClass = Passanger.class;
		Flight criteria = new Flight();
		criteria.setId(new Integer(3));

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
	public void testTwoAssociatedObjectNestedSearch1() throws Exception
	{
		Class targetClass = Flight.class;
		Flight criteria = new Flight();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Flight result = (Flight)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getDestination());
		
		Object[] passangerCollection = getAssociationResults(result, "passangerCollection", 0);
		Passanger passanger = (Passanger)passangerCollection[0];
		assertNotNull(passanger);
		assertNotNull(passanger.getId());
		assertNotNull(passanger.getName());
		assertEquals(new Integer(1),result.getId());
	
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
	public void testTwoAssociatedObjectNestedSearch2() throws Exception
	{
		Class targetClass = Passanger.class;
		Flight criteria = new Flight();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Passanger result = (Passanger)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertEquals(new Integer(1),result.getId());
	
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
	public void testOneAssociatedObjectNestedSearch3() throws Exception
	{
		Class targetClass = Flight.class;
		Passanger criteria = new Passanger();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Flight result = (Flight)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getDestination());
		assertEquals(new Integer(1),result.getId());
	}
	
	public void testGetAssociation() throws Exception
	{

		Class targetClass = Passanger.class;
		Passanger criteria = new Passanger();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Passanger result = (Passanger)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] flightResults = getAssociationResults(result, "flight", 0);
		Flight flight = (Flight)flightResults[0];
		assertNotNull(flight);
		assertNotNull(flight.getId());
		assertNotNull(flight.getDestination());
	}		
	
}
