/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "title", "value", result.getTitle().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Album result2 = (Album)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "title", "value", result.getTitle().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Song result2 = (Song)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
		Ii ii = new Ii();
		ii.setExtension("12");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Song result = (Song)i.next();
		toXML(result);
		Song result2 = (Song)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getTitle());
		
		assertNull(result2.getAlbum());
	}
	
	
	public void testAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song where id='12'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Song result = (Song)i.next();
		toXML(result);
		Song result2 = (Song)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Song result = (Song)i.next();
		toXML(result);
		Song result2 = (Song)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getTitle());
		
		Album Album = result2.getAlbum();
		assertNotNull(Album);
		assertNotNull(Album.getId());
		assertNotNull(Album.getTitle());
		assertEquals("1",Album.getId().getExtension());
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
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Album result = (Album)i.next();
		toXML(result);
		Album result2 = (Album)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getTitle());
		assertEquals("1",result2.getId().getExtension());
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
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getTitle());

			if (new Integer(result2.getId().getExtension()) != 12) { //Song id=12 has no Album associated with it
				
				validateAssociation(result,"Album","album", false);
				
				album = result2.getAlbum();
				assertNotNull(album);
				assertNotNull(album.getId());
				assertNotNull(album.getTitle());
			}

		}
	}	
}
