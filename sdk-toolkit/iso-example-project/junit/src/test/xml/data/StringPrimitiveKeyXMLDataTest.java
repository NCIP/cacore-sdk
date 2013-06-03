/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class StringPrimitiveKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "String Primitive Key XML Data Test Case";
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
		StringPrimitiveKey searchObject = new StringPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			StringPrimitiveKey result = (StringPrimitiveKey)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			StringPrimitiveKey result2 = (StringPrimitiveKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}
	
	public void testEntireObjectNestedSearchHQL() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.primarykey.StringPrimitiveKey");

		StringPrimitiveKey searchObject = new StringPrimitiveKey();

		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			StringPrimitiveKey result = (StringPrimitiveKey) i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result, "id", result.getId());
			validateAttribute(result, "name", result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			StringPrimitiveKey result2 = (StringPrimitiveKey) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}
}
