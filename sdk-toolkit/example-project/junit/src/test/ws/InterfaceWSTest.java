/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.interfaze.Dog;

import java.util.ArrayList;
import java.util.Collection;


public class InterfaceWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Interface WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Dog.class);
		
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
	public void testEntireObjectNestedSearch1() throws Exception
	{
		Class targetClass = Dog.class;
		Dog criteria = new Dog();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Dog result = (Dog)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBreed());
			assertNotNull(result.getGender());
		}		
	}
}
