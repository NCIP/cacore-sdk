/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

import gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey;
import gov.nih.nci.system.query.hibernate.HQLCriteria;


public class StringKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "String Key XML Data Test Case";
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
		StringKey searchObject = new StringKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			StringKey result = (StringKey)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			StringKey result2 = (StringKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}
	
	public void testEntireObjectNestedSearchHQL() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.primarykey.StringKey");

		StringKey searchObject = new StringKey();

		assertNotNull(results);
		assertEquals(5, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			StringKey result = (StringKey) i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result, "id", result.getId());
			validateAttribute(result, "name", result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			StringKey result2 = (StringKey) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}
}
