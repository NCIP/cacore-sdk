/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class StringPrimitiveKeyTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "String Primitive Key Test Case";
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
		StringPrimitiveKey searchObject = new StringPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			StringPrimitiveKey result = (StringPrimitiveKey)i.next();
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
		StringPrimitiveKey searchObject = new StringPrimitiveKey();
		searchObject.setId("id1");
		Collection results = getApplicationService().search(StringPrimitiveKey.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}
}
