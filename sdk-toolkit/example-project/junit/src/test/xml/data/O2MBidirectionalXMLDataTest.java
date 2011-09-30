package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive;

import java.util.Collection;
import java.util.Iterator;

import test.xml.data.SDKXMLDataTestBase;

public class O2MBidirectionalXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional XML Data Test Case";
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
		Computer searchObject = new Computer();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Computer result = (Computer)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"type",result.getType());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Computer result2 = (Computer)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getType());
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
		HardDrive searchObject = new HardDrive();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardDrive result = (HardDrive)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"size",result.getSize());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			HardDrive result2 = (HardDrive)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getSize());
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
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Computer result = (Computer)i.next();
		toXML(result);
		Computer result2 = (Computer)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getType());
		
		Collection hardDriveCollection = result2.getHardDriveCollection();
		assertNull(hardDriveCollection);
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
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Computer result = (Computer)i.next();
		toXML(result);
		Computer result2 = (Computer)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getType());
		
		validateAssociation(result,"HardDrive","hardDriveCollection", true, false);
		
		Collection hardDriveCollection = result2.getHardDriveCollection();
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
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Computer searchObject = new Computer();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		HardDrive hardDrive = (HardDrive)i.next();	
		toXML(hardDrive);
		HardDrive result2 = (HardDrive)fromXML(hardDrive);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getSize());
		assertEquals(new Integer(1),result2.getId());
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
		HardDrive searchObject = new HardDrive();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Computer computer = (Computer)i.next();
		toXML(computer);
		Computer result2 = (Computer)fromXML(computer);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getType());
		assertEquals(new Integer(1),result2.getId());
	}
	
	public void testGetAssociation() throws Exception
	{

		HardDrive searchObject = new HardDrive();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Computer computer;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardDrive result = (HardDrive)i.next();
			toXML(result);
			HardDrive result2 = (HardDrive)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getSize());
			
			validateAssociation(result,"Computer","computer", true, false);
			
			computer = result2.getComputer();
			assertNotNull(computer);
			assertNotNull(computer.getId());
			assertNotNull(computer.getType());
		}
	}	
}
