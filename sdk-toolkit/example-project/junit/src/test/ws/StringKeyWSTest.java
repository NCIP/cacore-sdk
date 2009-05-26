package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey;

import java.util.ArrayList;
import java.util.Collection;


public class StringKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "String Key WS Test Case";
	}
	 
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		mappedKlasses.add(StringKey.class);
		
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

		Class targetClass = StringKey.class;
		StringKey criteria = new StringKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			StringKey result = (StringKey)obj;
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
		Class targetClass = StringKey.class;
		StringKey criteria = new StringKey();
		criteria.setId("ID1");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);			
	}
}
