package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey;

import java.util.ArrayList;
import java.util.Collection;


public class CharacterPrimitiveKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Character Primitive Key Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(CharacterPrimitiveKey.class);
		mappedKlasses.add(Character.class);
		
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
	public void testEntireObjectNestedSearch() throws Exception
	{
//		CharacterPrimitiveKey searchObject = new CharacterPrimitiveKey();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey",searchObject );
//
//		assertNotNull(results);
//		assertEquals(4,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			CharacterPrimitiveKey result = (CharacterPrimitiveKey)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getName());
//		}
		
		Class targetClass = CharacterPrimitiveKey.class;
		CharacterPrimitiveKey criteria = new CharacterPrimitiveKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			CharacterPrimitiveKey result = (CharacterPrimitiveKey)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
		}		
	}

	/**
	 * Uses Class for search
	 * Searches by the primary key
	 * Verifies size of the result set
	 * 
	 * @throws Exception
	 */
	public void testPrimaryKeyNestedSearch() throws Exception
	{
//		CharacterPrimitiveKey searchObject = new CharacterPrimitiveKey();
//		searchObject.setId('6');
//		Collection results = getApplicationService().search(CharacterPrimitiveKey.class,searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
		
		Class targetClass = CharacterPrimitiveKey.class;
		CharacterPrimitiveKey criteria = new CharacterPrimitiveKey();
		criteria.setId('6');

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);			
	}
}
