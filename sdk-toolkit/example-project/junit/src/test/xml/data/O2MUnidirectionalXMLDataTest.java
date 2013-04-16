/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2MUnidirectionalXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Unidirectional XML Data Test Case";
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
		KeyChain searchObject = new KeyChain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			KeyChain result = (KeyChain)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			KeyChain result2 = (KeyChain)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
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
		LatchKey searchObject = new LatchKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LatchKey result = (LatchKey)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"type",result.getType());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			LatchKey result2 = (LatchKey)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getType());
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
		KeyChain searchObject = new KeyChain();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		KeyChain result = (KeyChain)i.next();
		toXML(result);
		KeyChain result2 = (KeyChain)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		Collection keyCollection = result2.getKeyCollection();
		assertNull(keyCollection);
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
		KeyChain searchObject = new KeyChain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		KeyChain result = (KeyChain)i.next();
		toXML(result);
		KeyChain result2 = (KeyChain)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		validateAssociation(result,"LatchKey","keyCollection", true, false);

		Collection keyCollection = result2.getKeyCollection();
		assertEquals(true, keyCollection.size()>0);

		Iterator j = keyCollection.iterator();
		LatchKey key = (LatchKey)j.next();
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
		KeyChain searchObject = new KeyChain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		LatchKey result = (LatchKey)i.next();
		toXML(result);
		LatchKey result2 = (LatchKey)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getType());

		assertEquals(new Integer(1),result2.getId());
	}
}
