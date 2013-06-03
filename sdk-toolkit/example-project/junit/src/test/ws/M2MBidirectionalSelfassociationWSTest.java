/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.selfassociation.MemberM2MBS;

import java.util.ArrayList;
import java.util.Collection;


public class M2MBidirectionalSelfassociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Many to Many Bidirectional Selfassociation WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(MemberM2MBS.class);
		
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
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(10,results.length);
		
		for (Object obj : results){
			MemberM2MBS result = (MemberM2MBS)obj;
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
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(10,results.length);
		
		for (Object obj : results){
			MemberM2MBS result = (MemberM2MBS)obj;
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
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();
		criteria.setId(new Integer(7));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		MemberM2MBS result = (MemberM2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());

		Object[] mentorCollection = getAssociationResults(result, "mentorCollection", 0);
		assertEquals(0,mentorCollection.length);	
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
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();
		criteria.setId(new Integer(8));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		MemberM2MBS result = (MemberM2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());

		Object[] mentorCollection = getAssociationResults(result, "mentorCollection", 0);
		assertEquals(0,mentorCollection.length);		
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
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		

		MemberM2MBS result = (MemberM2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		Object[] mentorCollection = getAssociationResults(result, "mentorCollection", 0);

		MemberM2MBS mentor = (MemberM2MBS)mentorCollection[0];
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(1),mentor.getId());
		
		Object[] memberCollection = getAssociationResults(mentor, "memberCollection", 0);
		assertEquals(1,memberCollection.length);
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
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberM2MBS mentor = (MemberM2MBS)results[0];
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
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch3() throws Exception
	{
		Class targetClass = MemberM2MBS.class;
		MemberM2MBS criteria = new MemberM2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberM2MBS member = (MemberM2MBS)results[0];
		assertNotNull(member);
		assertNotNull(member.getId());
		assertNotNull(member.getName());
		assertEquals(new Integer(1),member.getId());		
	}	
}
