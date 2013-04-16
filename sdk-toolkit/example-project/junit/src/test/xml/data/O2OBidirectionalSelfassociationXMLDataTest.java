/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;


import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS;


import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2OBidirectionalSelfassociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional Self Association XML Data Test Case";
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
		MemberO2OBS searchObject = new MemberO2OBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberO2OBS result2 = (MemberO2OBS)fromXML(result);

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
		MemberO2OBS searchObject = new MemberO2OBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberO2OBS result2 = (MemberO2OBS)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
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
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		MemberO2OBS result = (MemberO2OBS)i.next();
		toXML(result);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		MemberO2OBS mentor = result2.getMentor();
		assertNull(mentor);
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
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		MemberO2OBS result = (MemberO2OBS)i.next();
		toXML(result);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		MemberO2OBS mentor = result2.getMentor();
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(2),mentor.getId());
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
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		MemberO2OBS mentor = (MemberO2OBS)i.next();
		toXML(mentor);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(mentor);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1),result2.getId());
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
	public void testOneAssociatedObjectNestedSearch3() throws Exception
	{
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		MemberO2OBS mentor = (MemberO2OBS)i.next();
		toXML(mentor);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(mentor);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1),result2.getId());
	}

	public void testGetMethods1() throws Exception
	{
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		MemberO2OBS result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(result);
		assertEquals(new Integer(2),result2.getMentor().getId());

		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		result2 = (MemberO2OBS)fromXML(result);
		assertEquals(new Integer(1),result2.getMentor().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		result2 = (MemberO2OBS)fromXML(result);
		assertNull(result2.getMentor());

	}


	public void testGetMethods2() throws Exception
	{
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		MemberO2OBS result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		MemberO2OBS result2 = (MemberO2OBS)fromXML(result);
		assertEquals(new Integer(2),result2.getMe().getId());

		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		result2 = (MemberO2OBS)fromXML(result);
		assertEquals(new Integer(1),result2.getMe().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		result = (MemberO2OBS)results.iterator().next();
		toXML(result);
		result2 = (MemberO2OBS)fromXML(result);
		assertNull(result2.getMe());

	}

	public void testGetAssociation1() throws Exception
	{

		MemberO2OBS searchObject = new MemberO2OBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		MemberO2OBS mentor;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			toXML(result);
			MemberO2OBS result2 = (MemberO2OBS)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());

			if (result2.getId() < 3){ // MemberO2OBS id = 3 does not have an associated Mentor
				validateAssociation(result,"MemberO2OBS","mentor", true, false);
				mentor = result2.getMentor();
				assertNotNull(mentor);
				assertNotNull(mentor.getId());
				assertNotNull(mentor.getName());
			}
		}
	}

	public void testGetAssociation2() throws Exception
	{

		MemberO2OBS searchObject = new MemberO2OBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		MemberO2OBS mentor;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			toXML(result);
			MemberO2OBS result2 = (MemberO2OBS)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());

			if (result2.getId() < 3){ // MemberO2OBS id = 3,4, and 5 don't have an associated MemberO2OBS
				validateAssociation(result,"MemberO2OBS","mentor", true, false);
				mentor = result2.getMentor();
				assertNotNull(mentor);
				assertNotNull(mentor.getId());
				assertNotNull(mentor.getName());
			}
		}
	}

}
