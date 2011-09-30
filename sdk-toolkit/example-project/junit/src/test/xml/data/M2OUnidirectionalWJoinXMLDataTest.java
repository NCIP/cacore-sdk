package test.xml.data;

import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song;

import java.util.Collection;
import java.util.Iterator;

import test.xml.data.SDKXMLDataTestBase;

public class M2OUnidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional With Join XML Data Test Case";
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
		Album searchObject = new Album();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Album result = (Album)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"title",result.getTitle());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Album result2 = (Album)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getTitle());
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
		Song searchObject = new Song();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(12,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Song result = (Song)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"title",result.getTitle());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Song result2 = (Song)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getTitle());
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
		Song searchObject = new Song();
		searchObject.setId(new Integer(12));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Song result = (Song)i.next();
		toXML(result);
		Song result2 = (Song)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getTitle());
		
		assertNull(result2.getAlbum());
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
		Song searchObject = new Song();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Song result = (Song)i.next();
		toXML(result);
		Song result2 = (Song)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getTitle());
		
		Album Album = result2.getAlbum();
		assertNotNull(Album);
		assertNotNull(Album.getId());
		assertNotNull(Album.getTitle());
		assertEquals(new Integer(1),Album.getId());
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
		Song searchObject = new Song();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Album result = (Album)i.next();
		toXML(result);
		Album result2 = (Album)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getTitle());
		assertEquals(new Integer(1),result2.getId());
	}
	
	public void testGetAssociation() throws Exception
	{

		Song searchObject = new Song();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(12,results.size());
		
		Album album;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Song result = (Song)i.next();
			toXML(result);
			
			Song result2 = (Song)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getTitle());

			if (result2.getId() != 12) { //Song id=12 has no Album associated with it
				
				validateAssociation(result,"Album","album", true, false);
				
				album = result2.getAlbum();
				assertNotNull(album);
				assertNotNull(album.getId());
				assertNotNull(album.getTitle());
			}

		}
	}	
}
