package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class FloatPrimitiveKeyTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Float Primitive Key Test Case";
	}
	 
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch() throws ApplicationException
	{
		FloatPrimitiveKey searchObject = new FloatPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.FloatPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FloatPrimitiveKey result = (FloatPrimitiveKey)i.next();
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
	 * @throws ApplicationException
	 */
	public void testPrimaryKeyNestedSearch() throws ApplicationException
	{
		FloatPrimitiveKey searchObject = new FloatPrimitiveKey();
		searchObject.setId(new Float(1.1));
		Collection results = getApplicationService().search(FloatPrimitiveKey.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}
}
