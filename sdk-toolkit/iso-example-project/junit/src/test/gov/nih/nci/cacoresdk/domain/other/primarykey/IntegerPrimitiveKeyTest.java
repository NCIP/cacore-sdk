package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class IntegerPrimitiveKeyTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Integer Primitive Key Test Case";
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
		IntegerPrimitiveKey searchObject = new IntegerPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.IntegerPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			IntegerPrimitiveKey result = (IntegerPrimitiveKey)i.next();
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
		IntegerPrimitiveKey searchObject = new IntegerPrimitiveKey();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search(IntegerPrimitiveKey.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}
}
