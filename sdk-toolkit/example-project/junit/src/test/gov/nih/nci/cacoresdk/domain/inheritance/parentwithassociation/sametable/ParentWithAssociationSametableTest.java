/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTopType;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import test.gov.nih.nci.cacoresdk.SDKTestBase;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class ParentWithAssociationSametableTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Parent With Association Same Table Test Case";
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
		Luggage searchObject = new Luggage();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Luggage result = (Luggage)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
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
		HardTop searchObject = new HardTop();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardTop result = (HardTop)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getKeyCode());
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
	public void testEntireObjectNestedSearch3() throws ApplicationException
	{
		SoftTop searchObject = new SoftTop();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			SoftTop result = (SoftTop)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getExpandable());
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
	public void testEntireObjectNestedSearch4() throws ApplicationException
	{
		Wheel searchObject = new Wheel();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Wheel result = (Wheel)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getRadius());
		}
	}

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardTop result = (HardTop)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getKeyCode());
		}
	}

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Luggage result = (Luggage)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
		}
	}

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL3() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			SoftTop result = (SoftTop)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getExpandable());
		}
	}


	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Wheel result = (Wheel)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getRadius());
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 *
	 * @throws ApplicationException
	 */
	public void testZeroAssociationNestedSearch() throws ApplicationException
	{
		HardTop searchObject = new HardTop();
		searchObject.setCapacity(99);//No such row exists
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch() throws ApplicationException
	{
		HardTop searchObject = new HardTop();

		Wheel wheel = new Wheel();
		wheel.setRadius(1);

		searchObject.setWheel(wheel);
		searchObject.setCapacity(75);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardTop result = (HardTop)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getKeyCode());
			assertNotNull(result.getCapacity());
			assertEquals(result.getKeyCode(),new Integer(627));
		}
	}


	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		Luggage searchObject = new Luggage();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		HardTop result = (HardTop)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1), result.getId());
	}

	/**
	 * Uses CQL Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testZeroAssociationCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop");
		association.setAttribute(new CQLAttribute("capacity", CQLPredicate.EQUAL_TO,"99"));//no such row exists

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(0,results.size());

	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		HardTop searchObject = new HardTop();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Luggage result = (Luggage)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(2),result.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch3() throws ApplicationException
	{
		Luggage searchObject = new Luggage();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		SoftTop result = (SoftTop)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch4() throws ApplicationException
	{
		SoftTop searchObject = new SoftTop();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Luggage result = (Luggage)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch5() throws ApplicationException
	{
		HardTopType searchObject = new HardTopType();

		Wheel wheel = new Wheel();
		wheel.setRadius(1);

		searchObject.setWheel(wheel);
		searchObject.setCapacity(100);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTopType",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			HardTopType result = (HardTopType)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(new Integer(5), result.getId());
			assertEquals(new Integer(100), result.getCapacity());
			assertEquals(new Integer(890), result.getKeyCode());
		}
	}

	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Luggage result = (Luggage)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1), result.getId());
	}


	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		HardTop result = (HardTop)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(2), result.getId());
	}

	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationCQL3() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel");
		association.setAttribute(new CQLAttribute("radius", CQLPredicate.EQUAL_TO,"1"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		Luggage result = (Luggage)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1), result.getId());
	}


	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 *
	 * @throws ApplicationException
	 */
	public void testAssociationCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel");
		association.setAttribute(new CQLAttribute("radius", CQLPredicate.EQUAL_TO,"5"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		SoftTop result = (SoftTop)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(4), result.getId());
	}


	public void testGetAssociation() throws ApplicationException
	{
		Luggage searchObject = new Luggage();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		Wheel wheel;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Luggage result = (Luggage)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());

			wheel = result.getWheel();
			assertNotNull(wheel);
			assertNotNull(wheel.getId());
			assertNotNull(wheel.getRadius());
		}
	}
}
