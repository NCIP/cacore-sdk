package test.ws;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw;

import java.util.ArrayList;
import java.util.Collection;


public class O2OMultipleAssociationWJoinWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Bride.class);
		mappedKlasses.add(InLaw.class);
		
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
		Class targetClass = InLaw.class;
		InLaw criteria = new InLaw();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			InLaw result = (InLaw)obj;
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
		Class targetClass = Bride.class;
		Bride criteria = new Bride();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Bride result = (Bride)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
		}		
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = Bride.class;
		Bride criteria = new Bride();
		criteria.setId(new Integer(2));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Bride result = (Bride)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(1,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(0,motherResults.length);		
	}
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Class targetClass = Bride.class;
		Bride criteria = new Bride();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Bride result = (Bride)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(0,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(1,motherResults.length);			
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch3() throws Exception
	{
		Class targetClass = Bride.class;
		Bride criteria = new Bride();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Bride result = (Bride)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(0,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(0,motherResults.length);			
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
	public void xtestOneAssociatedObjectNestedSearch() throws Exception
	{
		Class targetClass = InLaw.class;
		Bride criteria = new Bride();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);			

		assertNotNull(results);
		assertEquals(0,results.length);
	}
	
	public void testGetAssociation() throws Exception
	{
		Class targetClass = Bride.class;
		Bride criteria = new Bride();
		criteria.setId(new Integer(1));// A Bride with both a Mother- and Father-in-Law

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Bride result = (Bride)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(1,fatherResults.length);
		InLaw fatherInLaw = (InLaw)fatherResults[0];
		assertNotNull(fatherInLaw);
		assertNotNull(fatherInLaw.getId());
		assertNotNull(fatherInLaw.getName());	
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(1,motherResults.length);		
		InLaw motherInLaw = (InLaw)motherResults[0];
		assertNotNull(motherInLaw);
		assertNotNull(motherInLaw.getId());
		assertNotNull(motherInLaw.getName());		
	}	
}
