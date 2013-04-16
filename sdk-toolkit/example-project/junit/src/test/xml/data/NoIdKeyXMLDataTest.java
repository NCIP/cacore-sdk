/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey;

import test.xml.mapping.SDKXMLDataTestBase;

public class NoIdKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "No Id Key XML Data Test Case";
	}
	 
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch() throws Exception
	{
		NoIdKey searchObject = new NoIdKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			NoIdKey result = (NoIdKey)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"mykey",result.getMykey());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			NoIdKey result2 = (NoIdKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getMykey());
			assertNotNull(result2.getName());
		}
	}
}
