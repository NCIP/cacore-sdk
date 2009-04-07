package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization;

import java.util.ArrayList;
import java.util.Collection;


public class MultipleChildSametableWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Multiple Child Same Table WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(PvtOrganization.class);
		mappedKlasses.add(Organization.class);
		mappedKlasses.add(GovtOrganization.class);
		
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
		Class targetClass = Organization.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);

		for(Object obj : results)
		{
			Organization result = (Organization)obj;
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
		Class targetClass = GovtOrganization.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		for(Object obj : results)
		{
			GovtOrganization result = (GovtOrganization)obj;
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
	public void testEntireObjectNestedSearch3() throws Exception
	{
		Class targetClass = PvtOrganization.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		for(Object obj : results)
		{
			PvtOrganization result = (PvtOrganization)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			assertNotNull(result.getCeo());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociationNestedSearch() throws Exception
	{
//		Class targetClass = GovtOrganization.class;
		Class targetClass = Organization.class;
		Organization criteria = new Organization();
		criteria.setName("Invalid Org Name");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);
	}

	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch1() throws Exception
	{
		Class targetClass = Organization.class;
		GovtOrganization criteria = new GovtOrganization();
		criteria.setName("Public Org Name");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Organization result = (Organization)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch2() throws Exception
	{
//		Class targetClass = PvtOrganization.class;
		Class targetClass = Organization.class;
		Organization criteria = new Organization();
		criteria.setName("Private Org Name");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		PvtOrganization result = (PvtOrganization)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch3() throws Exception
	{
		Class targetClass = Organization.class;
		PvtOrganization criteria = new PvtOrganization();
		criteria.setName("Private Org Name");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Organization result = (Organization)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());		
	}
}
