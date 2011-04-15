package test.gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2OUnidirectionalSelfassociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional Self Association Test Case";
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
		MemberO2OUS searchObject = new MemberO2OUS();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			MemberO2OUS result = (MemberO2OUS)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
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
		MemberO2OUS searchObject = new MemberO2OUS();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
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
		MemberO2OUS searchObject = new MemberO2OUS();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		MemberO2OUS result = (MemberO2OUS)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		MemberO2OUS mentor = result.getMentor();
		assertNotNull(mentor);
		
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(1),mentor.getId());
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
	public void testAssociationCQL() throws ApplicationException
	{
		boolean flag = true;
		try
		{
			CQLQuery cqlQuery = new CQLQuery();
			CQLObject target = new CQLObject();
			
			CQLAssociation association = new CQLAssociation();
			association.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
			
			target.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.selfassociation.MemberO2OUS");
			target.setAssociation(association);
			cqlQuery.setTarget(target);
	
			Collection results = getApplicationService().query(cqlQuery);
			assertNotNull(results);
			
		}
		catch(ApplicationException e)
		{
			flag = false;
		}
		
		assertTrue(flag);
	}

	
}

