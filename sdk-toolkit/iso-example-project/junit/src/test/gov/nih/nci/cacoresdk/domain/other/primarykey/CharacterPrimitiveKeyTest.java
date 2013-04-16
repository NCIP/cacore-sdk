/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.primarykey;

import gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class CharacterPrimitiveKeyTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Character Primitive Key Test Case";
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
		CharacterPrimitiveKey searchObject = new CharacterPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CharacterPrimitiveKey result = (CharacterPrimitiveKey)i.next();
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
		CharacterPrimitiveKey searchObject = new CharacterPrimitiveKey();
		searchObject.setId('6');
		Collection results = getApplicationService().search(CharacterPrimitiveKey.class,searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
	}	
}
