package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2OUnidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional With Join Test Case";
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
		Bag searchObject = new Bag();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(11,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bag result = (Bag)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"style",result.getStyle());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Bag result2 = (Bag)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getStyle());
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
		Handle searchObject = new Handle();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle",searchObject );

		assertNotNull(results);
		assertEquals(12,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Handle result = (Handle)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"color",result.getColor());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Handle result2 = (Handle)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getColor());
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
		Bag searchObject = new Bag();
		searchObject.setId(new Integer(11));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bag result = (Bag)i.next();
		toXML(result);
		Bag result2 = (Bag)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getStyle());
		
		Handle handle = result2.getHandle();
		assertNull(handle);
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
		Bag searchObject = new Bag();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bag result = (Bag)i.next();
		toXML(result);
		Bag result2 = (Bag)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getStyle());
		
		Handle handle = result2.getHandle();
		assertNotNull(handle);
		
		assertNotNull(handle);
		assertNotNull(handle.getId());
		assertNotNull(handle.getColor());
		assertEquals(new Integer(1),handle.getId());
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
		Bag searchObject = new Bag();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Handle handle = (Handle)i.next();
		toXML(handle);
		Handle result2 = (Handle)fromXML(handle);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getColor());

		assertEquals(new Integer(1),result2.getId());
	}
	
	public void testGetAssociation() throws Exception
	{

		Bag searchObject = new Bag();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(11,results.size());
		
		Handle handle;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bag result = (Bag)i.next();
			toXML(result);
			Bag result2 = (Bag)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getStyle());
			
			if (result2.getId() < 11){
				handle = result2.getHandle();
				validateAssociation(result,"Handle","handle", true, false);
				assertNotNull(handle);
				assertNotNull(handle.getId());
				assertNotNull(handle.getColor());
			}
		}
	}	
}
