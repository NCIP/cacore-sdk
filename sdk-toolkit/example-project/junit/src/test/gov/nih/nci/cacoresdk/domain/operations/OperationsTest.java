package test.gov.nih.nci.cacoresdk.domain.operations;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address;
import gov.nih.nci.cacoresdk.domain.operations.*;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class OperationsTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Operations Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies custom operations on the object
	 * 
	 * @throws ApplicationException
	 */
	public void testSpecimen() throws ApplicationException
	{
		Specimen searchObject = new Specimen();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.operations.Specimen",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Specimen result = (Specimen)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getActivityStatus());
			assertNotNull(result.getLabel());
			Date date = new Date();
			
			result.preProcess();
			Date createdDate = result.getCreatedOn();
			assertNotNull(createdDate);
			assertEquals(date, createdDate);
			result.postProcess();
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies custom operations on the object
	 * 
	 * @throws ApplicationException
	 */
	public void testSite() throws ApplicationException
	{
		Site searchObject = new Site();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.operations.Site",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Site result = (Site)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getActivityStatus());
			assertNotNull(result.getType());
			
			try
			{
				result.validateStatus("test");
			}
			catch(Exception e)
			{
				throw new ApplicationException(e);
			}
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies custom operations on the object
	 * 
	 * @throws ApplicationException
	 */
	public void testChildClass() throws ApplicationException
	{
		ChildTest searchObject = new ChildTest();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.operations.ChildTest",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ChildTest result = (ChildTest)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());

			result.testOperation1();
			result.testOperation2(new Address());
			result.testOperation3();
			Address address = result.testOperation4("test");
			assertNotNull(address);
			result.testOperation5(true, false);
		}
	}
	
}
