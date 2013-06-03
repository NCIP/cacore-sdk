/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.selfassociation.MemberO2MBS;


import java.util.ArrayList;
import java.util.Collection;


public class O2MBidirectionalSelfassociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional Self association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(MemberO2MBS.class);
				
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
		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			MemberO2MBS result = (MemberO2MBS)obj;
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
		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			MemberO2MBS result = (MemberO2MBS)obj;
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
		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2MBS result = (MemberO2MBS)results[0];
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

		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2MBS result = (MemberO2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] mentorCollection = getAssociationResults(result, "mentorCollection", 0);
		assertEquals(2,mentorCollection.length);
		
		MemberO2MBS mentor0 = (MemberO2MBS)mentorCollection[0];
		assertNotNull(mentor0);
		assertNotNull(mentor0.getId());
		assertNotNull(mentor0.getName());
		assertEquals(true,mentor0.getId()>1 && mentor0.getId()<4);
		
		MemberO2MBS mentor1 = (MemberO2MBS)mentorCollection[1];
		assertNotNull(mentor1);
		assertNotNull(mentor1.getId());
		assertNotNull(mentor1.getName());
		assertEquals(true,mentor0.getId()>1 && mentor0.getId()<4);	
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

		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2MBS result = (MemberO2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1),result.getId());		
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
	
		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2MBS result = (MemberO2MBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1),result.getId());		
	}	

	public void testGetAssociation() throws Exception
	{
		Class targetClass = MemberO2MBS.class;
		MemberO2MBS criteria = new MemberO2MBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		MemberO2MBS mentor = (MemberO2MBS)results[0];
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());	
		
		Object[] selfResults = getAssociationResults(mentor, "self", 0);
		assertEquals(1,selfResults.length);
		
		MemberO2MBS self = (MemberO2MBS)selfResults[0];
		assertNotNull(self);
		assertNotNull(self.getId());
		assertNotNull(self.getName());	
	}	
}
