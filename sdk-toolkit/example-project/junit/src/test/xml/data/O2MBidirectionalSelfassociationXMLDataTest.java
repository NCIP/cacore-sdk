package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS;


import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2MBidirectionalSelfassociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional Self association XML Data Test Case";
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
		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberO2MBS result2 = (MemberO2MBS)fromXML(result);

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
		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
			toXML(result);

			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberO2MBS result2 = (MemberO2MBS)fromXML(result);

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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		MemberO2MBS result = (MemberO2MBS)i.next();
		toXML(result);
		MemberO2MBS result2 = (MemberO2MBS)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		Collection mentorCollection = result2.getFriendCollection();
		assertNull(mentorCollection);
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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		MemberO2MBS result = (MemberO2MBS)i.next();
		toXML(result);
		MemberO2MBS result2 = (MemberO2MBS)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());

		validateAssociation(result,"MemberO2MBS","friend", false, false);
/*
		Collection mentorCollection = result2.getFriendCollection();
		Iterator j = mentorCollection.iterator();

		MemberO2MBS mentor = (MemberO2MBS)j.next();

		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());

		assertEquals(new Integer(3),mentor.getId());
*/
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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		MemberO2MBS mentor = (MemberO2MBS)i.next();
		toXML(mentor);
		MemberO2MBS result2 = (MemberO2MBS)fromXML(mentor);

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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		MemberO2MBS self = (MemberO2MBS)i.next();
		toXML(self);
		MemberO2MBS result2 = (MemberO2MBS)fromXML(self);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1),result2.getId());
	}

	public void testGetAssociation() throws Exception
	{

		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		MemberO2MBS self;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
			toXML(result);
			MemberO2MBS result2 = (MemberO2MBS)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());


			if(result2.getId()==3 || result2.getId()==2){
				validateAssociation(result,"MemberO2MBS","friend", false, false);

				self = result2.getFriend();
				assertNotNull(self);
				assertNotNull(self.getId());
				assertNotNull(self.getName());
			}

		}
	}
}
