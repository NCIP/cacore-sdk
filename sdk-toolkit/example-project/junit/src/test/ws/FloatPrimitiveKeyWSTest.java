package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey;

import java.util.ArrayList;
import java.util.Collection;


public class FloatPrimitiveKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Float Primitive Key WS Test Case";
	}
	 
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		mappedKlasses.add(FloatPrimitiveKey.class);
		
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
//		FloatPrimitiveKey searchObject = new FloatPrimitiveKey();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey",searchObject );
//
//		assertNotNull(results);
//		assertEquals(3,results.size());
//		
//		for(Iterator i = results.iterator();i.hasNext();)
//		{
//			FloatPrimitiveKey result = (FloatPrimitiveKey)i.next();
//			assertNotNull(result);
//			assertNotNull(result.getId());
//			assertNotNull(result.getName());
//		}
		Class targetClass = FloatPrimitiveKey.class;
		FloatPrimitiveKey criteria = new FloatPrimitiveKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			FloatPrimitiveKey result = (FloatPrimitiveKey)obj;
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
//		FloatPrimitiveKey searchObject = new FloatPrimitiveKey();
//		searchObject.setId(new Float(1.1));
//		Collection results = getApplicationService().search(FloatPrimitiveKey.class,searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
		
		Class targetClass = FloatPrimitiveKey.class;
		FloatPrimitiveKey criteria = new FloatPrimitiveKey();
		criteria.setId(new Float(1.1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);				
	}
}
