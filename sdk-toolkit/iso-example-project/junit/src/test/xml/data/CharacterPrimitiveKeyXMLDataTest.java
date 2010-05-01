package test.xml.data;

import gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class CharacterPrimitiveKeyXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Character Primitive Key XML Data Test Case";
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
		CharacterPrimitiveKey searchObject = new CharacterPrimitiveKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.primarykey.CharacterPrimitiveKey",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CharacterPrimitiveKey result = (CharacterPrimitiveKey)i.next();
			toXML(result);
			
			validateClassElements(result);
//			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			CharacterPrimitiveKey result2 = (CharacterPrimitiveKey)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());			
			assertNotNull(result2.getName());
		}
	}
}
