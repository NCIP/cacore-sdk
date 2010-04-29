package test.xml.data;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2MBidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional XML Data Test Case";
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
		Flight searchObject = new Flight();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Flight result = (Flight)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "destination", "value", result.getDestination().getValue());			
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Flight result2 = (Flight)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getDestination());
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
		Passanger searchObject = new Passanger();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Passanger result = (Passanger)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Passanger result2 = (Passanger)fromXML(result);
			
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
		Flight searchObject = new Flight();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Flight result = (Flight)i.next();
		toXML(result);
		Flight result2 = (Flight)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getDestination());
		
		Collection passangerCollection = result2.getPassangerCollection();
		assertNull(passangerCollection);
	}
	
	
	public void testAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Flight result = (Flight)i.next();
		toXML(result);
		Flight result2 = (Flight)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getDestination());
		
		Collection passangerCollection = result2.getPassangerCollection();
		assertNull(passangerCollection);
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
		Flight searchObject = new Flight();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Flight result = (Flight)i.next();
		toXML(result);
		Flight result2 = (Flight)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getDestination());
		
		Collection passangerCollection = result2.getPassangerCollection();
		assertNotNull(passangerCollection);
		
		Iterator j = passangerCollection.iterator();
		
		Passanger passanger = (Passanger)j.next();
		
		assertNotNull(passanger);
		assertNotNull(passanger.getId());
		assertNotNull(passanger.getName());
		assertEquals("1",passanger.getId().getExtension());
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
		Flight searchObject = new Flight();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Iterator i = results.iterator();
		
		Passanger result = (Passanger)i.next();
		toXML(result);
		Passanger result2 = (Passanger)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals("1",result2.getId().getExtension());
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
		Passanger searchObject = new Passanger();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Flight result = (Flight)i.next();
		toXML(result);
		Flight result2 = (Flight)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getDestination());
		assertEquals("1",result2.getId().getExtension());
	}	

	public void testGetAssociation() throws Exception
	{

		Passanger searchObject = new Passanger();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Flight flight;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Passanger result = (Passanger)i.next();
			toXML(result);
			Passanger result2 = (Passanger)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			
			validateAssociation(result,"Flight","flight");
			
			flight = result2.getFlight();
			assertNotNull(flight);
			assertNotNull(flight.getId());
			assertNotNull(flight.getDestination());
		}
	}		
	
}
