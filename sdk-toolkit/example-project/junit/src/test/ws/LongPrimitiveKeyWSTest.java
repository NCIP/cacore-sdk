package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.LongPrimitiveKey;

import java.util.ArrayList;
import java.util.Collection;


public class LongPrimitiveKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Long Primitive Key WS Test Case";
	}
	 
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		mappedKlasses.add(LongPrimitiveKey.class);
		
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

		Class targetClass = LongPrimitiveKey.class;
		LongPrimitiveKey criteria = new LongPrimitiveKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for (Object obj : results){
			LongPrimitiveKey result = (LongPrimitiveKey)obj;
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
		Class targetClass = LongPrimitiveKey.class;
		LongPrimitiveKey criteria = new LongPrimitiveKey();
		criteria.setId(new Long("9876"));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);			
	}
}
