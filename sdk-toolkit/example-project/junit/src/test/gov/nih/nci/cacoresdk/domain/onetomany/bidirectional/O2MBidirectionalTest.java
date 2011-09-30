package test.gov.nih.nci.cacoresdk.domain.onetomany.bidirectional;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2MBidirectionalTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional Test Case";
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
		Computer searchObject = new Computer();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Computer result = (Computer)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getType());
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
		HardDrive searchObject = new HardDrive();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardDrive result = (HardDrive)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getSize());
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
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Computer result = (Computer)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getType());
		
		Collection hardDriveCollection = result.getHardDriveCollection();
		assertEquals(0,hardDriveCollection.size());
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
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
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
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Computer result = (Computer)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getType());
		
		Collection hardDriveCollection = result.getHardDriveCollection();
		Iterator j = hardDriveCollection.iterator();
		
		HardDrive hardDrive = (HardDrive)j.next();
		assertNotNull(hardDrive);
		assertNotNull(hardDrive.getId());
		assertNotNull(hardDrive.getSize());
		assertEquals(new Integer(1),hardDrive.getId());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch2() throws ApplicationException
	{
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		HardDrive hardDrive = (HardDrive)i.next();		
		assertNotNull(hardDrive);
		assertNotNull(hardDrive.getId());
		assertNotNull(hardDrive.getSize());
		assertEquals(new Integer(1),hardDrive.getId());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch3() throws ApplicationException
	{
		HardDrive searchObject = new HardDrive();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Computer computer = (Computer)i.next();
		assertNotNull(computer);
		assertNotNull(computer.getId());
		assertNotNull(computer.getType());
		assertEquals(new Integer(1),computer.getId());
	}	

	public void testGetAssociation() throws ApplicationException
	{

		HardDrive searchObject = new HardDrive();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Computer computer;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardDrive result = (HardDrive)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getSize());
			
			computer = result.getComputer();
			assertNotNull(computer);
			assertNotNull(computer.getId());
			assertNotNull(computer.getType());
		}
	}	
}
