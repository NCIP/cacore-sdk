package test.gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class M2MBidirectionalSelfassociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Many to Many Bidirectional Selfassociation Test Case";
	}
	
	public void printEntireTestData() throws ApplicationException
	{
		String spacer = "";
		printEntireTestData2(spacer);
	}
	public final void printObject(String spacer, final MemberM2MBS m2mObject) throws ApplicationException
	{		
		System.out.println(spacer+" :: ID/Name= "+m2mObject.getId()+"/ "+m2mObject.getName());
	}
	
	private void printEntireTestData2(String spacer) throws ApplicationException
	{
	
		MemberM2MBS searchObject = new MemberM2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS result = (MemberM2MBS)i.next();
			printObject(spacer,result);
			Collection mentorCollection = result.getMentorCollection();
			
			for(Iterator j = mentorCollection.iterator();j.hasNext();)
			{
				MemberM2MBS mentor = (MemberM2MBS)j.next();
				spacer = spacer+"     ";
				printObject(spacer,mentor);
				if(mentor.getMemberCollection()!=null){
					MemberM2MBS member = (MemberM2MBS)j.next();
					printObject(spacer,member);
				}
				if(mentor.getMentorCollection()!=null){
					MemberM2MBS mentor2 = (MemberM2MBS)j.next();
					printObject(spacer,mentor2);
				}
			}
			
		}
		assertTrue(true);
	}

	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch1() throws ApplicationException
	{
	//	printEntireTestData();
		MemberM2MBS searchObject = new MemberM2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
//		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS result = (MemberM2MBS)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			System.out.println(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch2() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS result = (MemberM2MBS)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(7));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberM2MBS result = (MemberM2MBS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Collection mentorCollection = result.getMentorCollection();
		assertEquals(0,mentorCollection.size());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(8));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberM2MBS result = (MemberM2MBS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Collection mentorCollection = result.getMentorCollection();
		assertEquals(0,mentorCollection.size());
	}		

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch1() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberM2MBS result = (MemberM2MBS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Collection mentorCollection = result.getMentorCollection();
		Iterator j = mentorCollection.iterator();
		
		MemberM2MBS mentor = (MemberM2MBS)j.next();
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(1),mentor.getId());
		
		Collection memberCollection = mentor.getMemberCollection();
		assertEquals(1,memberCollection.size());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch2() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberM2MBS mentor = (MemberM2MBS)i.next();
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(1),mentor.getId());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch3() throws ApplicationException
	{
		MemberM2MBS searchObject = new MemberM2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberM2MBS member = (MemberM2MBS)i.next();
		assertNotNull(member);
		assertNotNull(member.getId());
		assertNotNull(member.getName());
		assertEquals(new Integer(1),member.getId());
	}	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"4"));
		association.setTargetRoleName("memberCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberM2MBS mentor = (MemberM2MBS)i.next();
			assertNotNull(mentor);
			assertNotNull(mentor.getId());
			assertNotNull(mentor.getName());
			assertEquals(true,mentor.getId().intValue()>1);
		}
	}	

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"4"));
		association.setTargetRoleName("mentorCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberM2MBS member = (MemberM2MBS)i.next();
		assertNotNull(member);
		
		assertNotNull(member);
		assertNotNull(member.getId());
		assertNotNull(member.getName());
		assertEquals(new Integer(4),member.getId());
	}	
	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"7"));
		association.setTargetRoleName("memberCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(0,results.size());
	}	
	
}
