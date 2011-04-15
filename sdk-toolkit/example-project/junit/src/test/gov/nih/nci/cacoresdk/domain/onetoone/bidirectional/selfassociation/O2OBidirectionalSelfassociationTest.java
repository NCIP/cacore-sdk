package test.gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassocation;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2OBidirectionalSelfassociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional Self Association Test Case";
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
		MemberO2OBS searchObject = new MemberO2OBS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			if(result.getId()==2){
				assertNotNull(result.getMentor());
			}
			if(result.getId()==1){
				assertNotNull(result.getSelf());
			}
		}
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
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OBS result = (MemberO2OBS)i.next();
			assertNull(result.getMentor());
		}
		
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
		MemberO2OBS searchObject = new MemberO2OBS();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberO2OBS result = (MemberO2OBS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		MemberO2OBS mentor = result.getMentor();
		assertNotNull(mentor);
		
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertNotNull(mentor.getSelf());
		assertEquals(new Integer(1),mentor.getSelf().getId());
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
	public void testNoAssociationCQL() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			CQLQuery cqlQuery = new CQLQuery();
			CQLObject target = new CQLObject();
			
			CQLAssociation association = new CQLAssociation();
			association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"4"));
			
			target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
			target.setAssociation(association);
			cqlQuery.setTarget(target);
	
			Collection results = getApplicationService().query(cqlQuery);
			assertNotNull(results);
			for(Iterator i = results.iterator();i.hasNext();)
			{
				MemberO2OBS result = (MemberO2OBS)i.next();
				assertNull(result.getMentor());
			}

			
		}
		catch(ApplicationException e)
		{
			flag = true;
		}
		
		assertTrue(flag);
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"2"));
		//association.setSourceRoleName("self");
		association.setTargetRoleName("mentor");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberO2OBS mentor = (MemberO2OBS)i.next();
		assertNotNull(mentor);
		
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertNotNull(mentor.getSelf());
		assertEquals(new Integer(1),mentor.getId());
		assertEquals(new Integer(1),mentor.getSelf().getId());
	}	
	
	public void testOneAssociatedObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"2"));
		//association.setSourceRoleName("self");
		association.setTargetRoleName("self");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		MemberO2OBS self = (MemberO2OBS)i.next();
		assertNotNull(self);
		
		assertNotNull(self);
		assertNotNull(self.getId());
		assertNotNull(self.getName());
		assertEquals(new Integer(2),self.getId());
		assertEquals(new Integer(1),self.getMentor().getId());
		
	}	

		
}

