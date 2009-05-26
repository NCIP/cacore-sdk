package test.ws;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Album;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.withjoin.Song;

import java.util.ArrayList;
import java.util.Collection;


public class M2OUnidirectionalWJoinWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional With Join WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Album.class);
		mappedKlasses.add(Song.class);
		
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

		Class targetClass = Album.class;
		Album criteria = new Album();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			Album result = (Album)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getTitle());	
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
		Class targetClass = Song.class;
		Song criteria = new Song();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(12,results.length);
		
		for (Object obj : results){
			Song result = (Song)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getTitle());	
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
		Class targetClass = Song.class;
		Song criteria = new Song();
		criteria.setId(new Integer(12));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		Song song = (Song)results[0];
		assertNotNull(song);
		assertNotNull(song.getId());
		assertNotNull(song.getTitle());
		
		Object[] albumResults = getAssociationResults(song, "album", 0);
		assertEquals(0,albumResults.length);		
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

		Class targetClass = Album.class;
		Song criteria = new Song();
		criteria.setId(new Integer(12));

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
		Class targetClass = Song.class;
		Song criteria = new Song();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		Song song = (Song)results[0];
		assertNotNull(song);
		assertNotNull(song.getId());
		assertNotNull(song.getTitle());
		
		Object[] albumResults = getAssociationResults(song, "album", 0);
		Album album = (Album)albumResults[0];
		assertNotNull(album);
		assertNotNull(album.getId());
		assertNotNull(album.getTitle());
		assertEquals(new Integer(1),album.getId());
		
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
	
		Class targetClass = Album.class;
		Song criteria = new Song();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		Album album = (Album)results[0];
		assertNotNull(album);
		assertNotNull(album.getId());
		assertNotNull(album.getTitle());
		assertEquals(new Integer(1),album.getId());
	
	}
	
	public void testGetAssociation() throws Exception
	{

		Class targetClass = Song.class;
		Song criteria = new Song();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(12,results.length);

		Song result = (Song)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getTitle());

		Object[] albumResults = getAssociationResults(result, "album", 0);
		Album album = (Album)albumResults[0];
		assertNotNull(album);
		assertNotNull(album.getId());
		assertNotNull(album.getTitle());		
			
	}	
}
