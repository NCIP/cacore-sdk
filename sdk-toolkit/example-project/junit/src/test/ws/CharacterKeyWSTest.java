package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey;

import java.util.ArrayList;
import java.util.Collection;


public class CharacterKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Character Key WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(CharacterKey.class);
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
	public void xtestEntireObjectNestedSearch() throws Exception
	{
//		CharacterKey searchObject = new CharacterKey();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey",searchObject );
//
//		assertNotNull(results);
//		assertEquals(4,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			CharacterKey result = (CharacterKey)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getName());
//		}
		
		Class targetClass = CharacterKey.class;
		CharacterKey criteria = new CharacterKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			CharacterKey result = (CharacterKey)obj;
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
	public void xtestPrimaryKeyNestedSearch() throws Exception
	{
//		CharacterKey searchObject = new CharacterKey();
//		searchObject.setId('9');
//		Collection results = getApplicationService().search(CharacterKey.class,searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
		
		Class targetClass = CharacterKey.class;
		CharacterKey criteria = new CharacterKey();
		criteria.setId('9');

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);		
		
	}
	
	public void testFixCharacterDataTypeSupportForWS(){		
	}	
}
