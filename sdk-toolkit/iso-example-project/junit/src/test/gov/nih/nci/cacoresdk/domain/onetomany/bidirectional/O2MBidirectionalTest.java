package test.gov.nih.nci.cacoresdk.domain.onetomany.bidirectional;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class O2MBidirectionalTest extends SDKISOTestBase
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
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
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
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
		assertEquals("1",hardDrive.getId().getExtension());
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		HardDrive hardDrive = (HardDrive)i.next();		
		assertNotNull(hardDrive);
		assertNotNull(hardDrive.getId());
		assertNotNull(hardDrive.getSize());
		assertEquals("1",hardDrive.getId().getExtension());
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Computer computer = (Computer)i.next();
		assertNotNull(computer);
		assertNotNull(computer.getId());
		assertNotNull(computer.getType());
		assertEquals("1",computer.getId().getExtension());
	}	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"2"));
		association.setTargetRoleName("computer");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardDrive hardDrive = (HardDrive)i.next();
			assertNotNull(hardDrive);
			assertNotNull(hardDrive.getId());
			assertNotNull(hardDrive.getSize());
			assertEquals(true,new Integer(hardDrive.getId().getExtension())>1);
		}
	}	
	
	public void testOneAssociatedObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive harddrive "
						+ "where harddrive.computer.id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive");
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			HardDrive hardDrive = (HardDrive) i.next();
			assertNotNull(hardDrive);
			assertNotNull(hardDrive.getId());
			assertNotNull(hardDrive.getSize());
			assertEquals(true,
					new Integer(hardDrive.getId().getExtension()) > 1);
		}
	}

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setTargetRoleName("hardDriveCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Computer computer = (Computer)i.next();
		assertNotNull(computer);
		
		assertNotNull(computer);
		assertNotNull(computer.getId());
		assertNotNull(computer.getType());
		assertEquals("1",computer.getId().getExtension());
	}	
	
	public void testOneAssociatedObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer computer "
						+ " inner join fetch computer.hardDriveCollection as harddrive where harddrive.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer");

		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		Computer computer = (Computer) i.next();
		assertNotNull(computer);

		assertNotNull(computer);
		assertNotNull(computer.getId());
		assertNotNull(computer.getType());
		assertEquals("1", computer.getId().getExtension());
	}

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"3"));
		association.setTargetRoleName("computer");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(0,results.size());
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
