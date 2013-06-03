/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class CharacterKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Character Key XML Data Test Case";
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
		CharacterKey searchObject = new CharacterKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterKey",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CharacterKey result = (CharacterKey)i.next();
			try{
				toXML(result);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			validateClassElements(result);
//			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			CharacterKey result2 = (CharacterKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId()); 
			assertNotNull(result2.getName());
		}
	}
}
