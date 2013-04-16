/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.other.primarykey.DoublePrimitiveKey;

import java.util.ArrayList;
import java.util.Collection;


public class DoublePrimitiveKeyWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Double Primitive Key WS Test Case";
	}
	 
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		mappedKlasses.add(DoublePrimitiveKey.class);
		
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

		Class targetClass = DoublePrimitiveKey.class;
		DoublePrimitiveKey criteria = new DoublePrimitiveKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			DoublePrimitiveKey result = (DoublePrimitiveKey)obj;
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
		Class targetClass = DoublePrimitiveKey.class;
		DoublePrimitiveKey criteria = new DoublePrimitiveKey();
		criteria.setId(1.10);

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);			
	}
	
}
