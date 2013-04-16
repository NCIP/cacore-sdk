/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2OUnidirectionalSelfassociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional XML Data Test Case";
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
		Person searchObject = new Person();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Person result = (Person)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			Person result2 = (Person)fromXML(result);

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
		Address searchObject = new Address();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Address result = (Address)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"zip",result.getZip());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			Address result2 = (Address)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getZip());
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Person result = (Person)i.next();
		toXML(result);
		Person result2 = (Person)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		Address address = result2.getLivesAt();
		assertNull(address);
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Person result = (Person)i.next();
		toXML(result);
		Person result2 = (Person)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		Address address = result2.getLivesAt();
		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());
		assertEquals(new Integer(1),address.getId());
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		Address result = (Address)i.next();
		toXML(result);
		Address result2 = (Address)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getZip());
		assertEquals(new Integer(1),result2.getId());
	}

	public void testGetAssociation() throws Exception
	{

		Person searchObject = new Person();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		Address address;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Person result = (Person)i.next();
			toXML(result);
			Person result2 = (Person)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());

			if (result2.getId() < 4){//Person id=1,2,3 have an associated Address; the others don't

				validateAssociation(result,"Address","livesAt", true, false);

				address = result2.getLivesAt();
				assertNotNull(address);
				assertNotNull(address.getId());
				assertNotNull(address.getZip());
			}
		}
	}
}
