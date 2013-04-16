/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive;

import java.util.ArrayList;
import java.util.Collection;


public class O2MBidirectionalWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Bidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Computer.class);
		mappedKlasses.add(HardDrive.class);
		
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
		Class targetClass = Computer.class;
		Computer criteria = new Computer();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Computer result = (Computer)obj;
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
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Class targetClass = HardDrive.class;
		HardDrive criteria = new HardDrive();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			HardDrive result = (HardDrive)obj;
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
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = Computer.class;
		Computer criteria = new Computer();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Computer result = (Computer)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getType());	
		
		Object[] hardDriveCollection = getAssociationResults(result, "hardDriveCollection", 0);
		assertEquals(0,hardDriveCollection.length);
	
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
		Class targetClass = HardDrive.class;
		Computer criteria = new Computer();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);		
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

		Class targetClass = Computer.class;
		Computer criteria = new Computer();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Computer result = (Computer)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getType());	
		
		Object[] hardDriveCollection = getAssociationResults(result, "hardDriveCollection", 0);
		assertEquals(1,hardDriveCollection.length);
		
		HardDrive hardDrive = (HardDrive)hardDriveCollection[0];
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

		Class targetClass = HardDrive.class;
		Computer criteria = new Computer();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		HardDrive result = (HardDrive)results[0];
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
	
		Class targetClass = Computer.class;
		HardDrive criteria = new HardDrive();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Computer result = (Computer)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1),result.getId());		
	}	

	public void testGetAssociation() throws Exception
	{
		Class targetClass = HardDrive.class;
		HardDrive criteria = new HardDrive();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		HardDrive hardDrive = (HardDrive)results[0];
		assertNotNull(hardDrive);
		assertNotNull(hardDrive.getId());
		assertNotNull(hardDrive.getSize());	
		
		Object[] computerResults = getAssociationResults(hardDrive, "computer", 0);
		assertEquals(1,computerResults.length);
		
		Computer computer = (Computer)computerResults[0];
		assertNotNull(computer);
		assertNotNull(computer.getId());
		assertNotNull(computer.getType());	
	}	
}
