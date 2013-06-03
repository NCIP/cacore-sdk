/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey;

import java.util.Collection;
import java.util.Iterator;


public class DoubleKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Double Key XML Data Test Case";
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
		DoubleKey searchObject = new DoubleKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.DoubleKey",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DoubleKey result = (DoubleKey)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			DoubleKey result2 = (DoubleKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}

}
