package test.gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2MBidirectionalSelfassociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional Self association Test Case";
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
		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
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
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch2() throws ApplicationException
	{
		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberO2MBS result = (MemberO2MBS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		System.out.println("getFriend: "+result.getFriend());
		System.out.println("getFriendCollection: "+result.getFriendCollection());
		
		Collection friendCollection = result.getFriendCollection();
//		MemberO2MBS member = (MemberO2MBS)mentorCollection.iterator().next();
//		System.out.println("mentorCollection: "+member.getId());
//		System.out.println("mentorCollection: "+member.getName());
//		MemberO2MBS self = member.getSelf();
//		System.out.println("mentorCollection: self "+self.getId());
//		System.out.println("mentorCollection: self "+self.getName());
		
		assertEquals(0,friendCollection.size());
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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberO2MBS friend = (MemberO2MBS)i.next();		
		assertNotNull(friend);
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
		MemberO2MBS searchObject = new MemberO2MBS();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberO2MBS self= (MemberO2MBS)i.next();		
		assertNotNull(self);
		assertNotNull(self);
		assertNotNull(self.getId());
		assertNotNull(self.getName());
		
		Collection friendCollection = self.getFriendCollection();
		assertEquals(2,friendCollection.size());
		
		
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"2"));
		association.setTargetRoleName("friendCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS friend = (MemberO2MBS)i.next();
			assertNotNull(friend);
			assertNotNull(friend.getId());
			assertEquals(true,friend.getId().intValue()==1);
		}
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"3"));
		association.setTargetRoleName("friend");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(0,results.size());
	}	
	
	public void testGetAssociation() throws ApplicationException
	{

		MemberO2MBS searchObject = new MemberO2MBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		MemberO2MBS self;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2MBS result = (MemberO2MBS)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			
			if(result.getId()==1)
			{
				Collection friendCollection = result.getFriendCollection();
				for(Iterator j = friendCollection.iterator();j.hasNext();)
				{
					MemberO2MBS mentor = (MemberO2MBS)j.next();
					self = mentor.getFriend();
					assertNotNull(self);
					assertNotNull(self.getId());
					assertNotNull(self.getName());
				}
			}
			if(result.getId()==2)
			{
				self = result.getFriend();
				assertNotNull(self);
				assertNotNull(self.getId());
				assertNotNull(self.getName());
			}
			if(result.getId()==3)
			{
				self = result.getFriend();
				assertNotNull(self);
				assertNotNull(self.getId());
				assertNotNull(self.getName());
			}
		}
	}	
}
