package test.gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2OMultipleAssociationWJoinTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association Test Case";
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
		InLaw searchObject = new InLaw();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			InLaw result = (InLaw)i.next();
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
		Bride searchObject = new Bride();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bride result = (Bride)i.next();
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
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		Bride searchObject = new Bride();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		assertNotNull(result.getFather());
		assertNull(result.getMother());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws ApplicationException
	{
		Bride searchObject = new Bride();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		assertNull(result.getFather());
		assertNotNull(result.getMother());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch3() throws ApplicationException
	{
		Bride searchObject = new Bride();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		
		assertNull(result.getFather());
		assertNull(result.getMother());
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
	public void xtestOneAssociatedObjectNestedSearch() throws ApplicationException
	{
		boolean flag = true;

		try
		{
			Bride searchObject = new Bride();
			searchObject.setId(new Integer(2));
			Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw",searchObject );

			assertNotNull(results);
			assertEquals(1,results.size());
		} 
		catch(ApplicationException e)
		{
			flag = false;
		}
		assertTrue(flag);		

	}

	public void testGetAssociation() throws ApplicationException
	{

		Bride searchObject = new Bride();
		searchObject.setId(1);// A Bride with both a Mother- and Father-in-Law
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		InLaw fatherInLaw;
		InLaw motherInLaw;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bride result = (Bride)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
			
			fatherInLaw = result.getFather();
			assertNotNull(fatherInLaw);
			assertNotNull(fatherInLaw.getId());
			assertNotNull(fatherInLaw.getName());
			
			motherInLaw = result.getMother();
			assertNotNull(motherInLaw);
			assertNotNull(motherInLaw.getId());
			assertNotNull(motherInLaw.getName());
		}
	}	
}
