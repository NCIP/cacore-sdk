/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class O2MBidirectionalWJoinTest extends SDKISOTestBase
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
		Flight searchObject = new Flight();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Flight result = (Flight)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getDestination());
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
		Passanger searchObject = new Passanger();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Passanger result = (Passanger)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
		Flight searchObject = new Flight();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Flight result = (Flight)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getDestination());

		Collection passangerCollection = result.getPassangerCollection();
		assertEquals(0,passangerCollection.size());
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
		Flight searchObject = new Flight();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

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
	public void testTwoAssociatedObjectNestedSearch1() throws ApplicationException
	{
		Flight searchObject = new Flight();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Flight result = (Flight)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getDestination());

		Collection PassangerCollection = result.getPassangerCollection();
		Iterator j = PassangerCollection.iterator();

		Passanger passanger = (Passanger)j.next();
		assertNotNull(passanger);

		assertNotNull(passanger.getId());
		assertEquals(passanger.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(passanger.getName());
		assertEquals("1",passanger.getId().getExtension());
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
	public void testTwoAssociatedObjectNestedSearch2() throws ApplicationException
	{
		Flight searchObject = new Flight();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		Iterator i = results.iterator();

		Passanger passanger = (Passanger)i.next();
		assertNotNull(passanger);

		assertNotNull(passanger);
		assertNotNull(passanger.getId());
		assertEquals(passanger.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(passanger.getName());
		assertEquals("1",passanger.getId().getExtension());
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
		Passanger searchObject = new Passanger();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		Flight flight = (Flight)i.next();
		assertNotNull(flight);
		assertNotNull(flight.getId());
		assertEquals(flight.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(flight.getDestination());
		assertEquals("1",flight.getId().getExtension());	}
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setTargetRoleName("flight");

		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Passanger passanger = (Passanger)i.next();
			assertNotNull(passanger);
			assertNotNull(passanger.getId());
			assertEquals(passanger.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(passanger.getName());
			assertEquals(true,new Integer(passanger.getId().getExtension())>0);
		}
	}

	public void testOneAssociatedObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger passanger "
						+ " where passanger.flight='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger");
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Passanger passanger = (Passanger) i.next();
			assertNotNull(passanger);
			assertNotNull(passanger.getId());
			assertEquals(passanger.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(passanger.getName());
			assertEquals(true,new Integer(passanger.getId().getExtension()) > 0);
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setTargetRoleName("passangerCollection");

		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		Flight Flight = (Flight)i.next();
		assertNotNull(Flight);

		assertNotNull(Flight);
		assertNotNull(Flight.getId());
		assertNotNull(Flight.getDestination());
		assertEquals("1",Flight.getId().getExtension());
	}

	public void testOneAssociatedObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight flight "
						+ "inner join fetch flight.passangerCollection passanger where passanger.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight");
		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		Flight Flight = (Flight) i.next();
		assertNotNull(Flight);

		assertNotNull(Flight);
		assertNotNull(Flight.getId());
		assertNotNull(Flight.getDestination());
		assertEquals("1", Flight.getId().getExtension());
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"3"));
		association.setTargetRoleName("flight");

		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	public void testGetAssociation() throws ApplicationException
	{

		Passanger searchObject = new Passanger();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		Flight flight;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Passanger result = (Passanger)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());

			flight = result.getFlight();
			assertNotNull(flight);
			assertNotNull(flight.getId());
			assertNotNull(flight.getDestination());
		}
	}

}
