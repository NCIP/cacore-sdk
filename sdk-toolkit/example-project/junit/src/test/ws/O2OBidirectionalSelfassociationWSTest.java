package test.ws;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.selfassociation.MemberO2OBS;


import java.util.ArrayList;
import java.util.Collection;


public class O2OBidirectionalSelfassociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional Selfassociation WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(MemberO2OBS.class);
		
		
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
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			MemberO2OBS result = (MemberO2OBS)obj;
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
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			MemberO2OBS result = (MemberO2OBS)obj;
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
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] mentorResults = getAssociationResults(result, "mentor", 0);
		assertEquals(0,mentorResults.length);
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
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(2));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] mentorResults = getAssociationResults(result, "mentor", 0);
		assertEquals(1,mentorResults.length);
		
		MemberO2OBS mentor = (MemberO2OBS)mentorResults[0];
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
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS mentor = (MemberO2OBS)results[0];
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
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS self = (MemberO2OBS)results[0];
		assertNotNull(self);
		assertNotNull(self.getId());
		assertNotNull(self.getName());
		assertEquals(new Integer(1),self.getId());			
	}	
	
	
	public void testGetMethods1() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		Object[] mentorResults = getAssociationResults(result, "mentor", 0);
		assertEquals(new Integer(2),((MemberO2OBS)mentorResults[0]).getId());
		
		criteria.setId(new Integer(2));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (MemberO2OBS)results[0];
		mentorResults = getAssociationResults(result, "mentor", 0);
		assertEquals(new Integer(1),((MemberO2OBS)mentorResults[0]).getId());
		
		criteria.setId(new Integer(3));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (MemberO2OBS)results[0];
		mentorResults = getAssociationResults(result, "mentor", 0);
		assertEquals(0,mentorResults.length);		
		
	}

	public void testGetMethods2() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		Object[] selfResults = getAssociationResults(result, "self", 0);
		assertEquals(new Integer(1),((MemberO2OBS)selfResults[0]).getId());
		
		criteria.setId(new Integer(2));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (MemberO2OBS)results[0];
		selfResults = getAssociationResults(result, "self", 0);
		assertEquals(new Integer(2),((MemberO2OBS)selfResults[0]).getId());
		
		criteria.setId(new Integer(3));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (MemberO2OBS)results[0];
		selfResults = getAssociationResults(result, "mentor", 0);
		assertEquals(0,selfResults.length);		
		
	}
	
	public void testGetAssociation1() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		Object[] mentorResults = getAssociationResults(result, "mentor", 0);
		MemberO2OBS mentor = (MemberO2OBS)mentorResults[0];
		assertNotNull(mentor);
		assertNotNull(mentor.getId());
		assertNotNull(mentor.getName());
		assertEquals(new Integer(2),mentor.getId());		
	}
	
	public void testGetAssociation2() throws Exception
	{
		Class targetClass = MemberO2OBS.class;
		MemberO2OBS criteria = new MemberO2OBS();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		MemberO2OBS result = (MemberO2OBS)results[0];
		Object[] selfResults = getAssociationResults(result, "self", 0);
		MemberO2OBS self = (MemberO2OBS)selfResults[0];
		assertNotNull(self);
		assertNotNull(self.getId());
		assertNotNull(self.getName());		
		assertEquals(new Integer(1),self.getId());		
	}		
}
