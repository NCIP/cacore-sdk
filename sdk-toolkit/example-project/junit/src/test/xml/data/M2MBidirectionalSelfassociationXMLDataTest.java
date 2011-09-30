package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS;


import test.xml.data.SDKXMLDataTestBase;

public class M2MBidirectionalSelfassociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Many to Many Bidirectional Selfassociation XML Data Test Case";
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
		MemberM2MBS searchObject = new MemberM2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS result = (MemberM2MBS)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
			
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
		MemberM2MBS searchObject = new MemberM2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS result = (MemberM2MBS)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
			
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
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(7));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberM2MBS result = (MemberM2MBS)i.next();
		toXML(result);
		MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		Collection mentorCollection = result2.getMentorCollection();
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
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberM2MBS result = (MemberM2MBS)i.next();
		toXML(result);
		MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		Collection mentorCollection = result2.getMentorCollection();
		Iterator j = mentorCollection.iterator();
		
		MemberM2MBS mentor = (MemberM2MBS)j.next();
		toXML(mentor);
		MemberM2MBS mentor2 = (MemberM2MBS)fromXML(mentor);
		
		assertNotNull(mentor2);
		assertNotNull(mentor2.getId());
		assertNotNull(mentor2.getName());
		assertEquals(new Integer(1),mentor2.getId());
		
		Collection memberCollection = mentor2.getMemberCollection();
		//2nd level associations, including Collections, get nullified via the Castor Collection Handler
//		assertEquals(1,memberCollection.size());
		assertNull(memberCollection);
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
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberM2MBS result = (MemberM2MBS)i.next();
		toXML(result);
		MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
		
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
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberM2MBS result = (MemberM2MBS)i.next();
		toXML(result);
		MemberM2MBS result2 = (MemberM2MBS)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1),result2.getId());
	}	
}
