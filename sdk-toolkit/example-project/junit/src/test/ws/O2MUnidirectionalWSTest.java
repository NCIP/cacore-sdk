/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain;

import java.util.ArrayList;
import java.util.Collection;


public class O2MUnidirectionalWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Unidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(LatchKey.class);
		mappedKlasses.add(KeyChain.class);
		
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
	
		Class targetClass = KeyChain.class;
		KeyChain criteria = new KeyChain();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			KeyChain result = (KeyChain)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
		}	
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Class targetClass = LatchKey.class;
		LatchKey criteria = new LatchKey();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			LatchKey result = (LatchKey)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getType());	
		}	
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = KeyChain.class;
		KeyChain criteria = new KeyChain();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		KeyChain result = (KeyChain)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] keyCollection = getAssociationResults(result, "keyCollection", 0);
		assertEquals(0,keyCollection.length);	
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Class targetClass = LatchKey.class;
		KeyChain criteria = new KeyChain();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);			
	}	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch1() throws Exception
	{
		Class targetClass = KeyChain.class;
		KeyChain criteria = new KeyChain();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		KeyChain result = (KeyChain)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] keyCollection = getAssociationResults(result, "keyCollection", 0);
		assertEquals(1,keyCollection.length);
		
		LatchKey key = (LatchKey)keyCollection[0];
		assertNotNull(key);
		assertNotNull(key.getId());
		assertNotNull(key.getType());
		assertEquals(new Integer(1),key.getId());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Class targetClass = LatchKey.class;
		KeyChain criteria = new KeyChain();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		LatchKey key = (LatchKey)results[0];
		assertNotNull(key);
		assertNotNull(key.getId());
		assertEquals(new Integer(1),key.getId());		
	}
}
