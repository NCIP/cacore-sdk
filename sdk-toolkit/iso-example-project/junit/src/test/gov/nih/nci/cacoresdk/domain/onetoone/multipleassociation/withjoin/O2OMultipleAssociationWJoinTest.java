/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw;
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

public class O2OMultipleAssociationWJoinTest extends SDKISOTestBase
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
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
		Ii ii=new Ii();
		ii.setExtension("2");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
			Ii ii=new Ii();
			ii.setExtension("2");
			searchObject.setId(ii);
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

	/**
	 * Uses CQL Criteria for search
	 * Verifies that exception is generated
	 *
	 * @throws ApplicationException
	 */
	public void testRoleNameAssociationCQL() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			CQLQuery cqlQuery = new CQLQuery();
			CQLObject target = new CQLObject();

			CQLAssociation association = new CQLAssociation();
			association.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));

			target.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");
			target.setAssociation(association);
			cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);
			assertNotNull(results);

		}
		catch(ApplicationException e)
		{
			flag = true;
		}

		assertTrue(flag);
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
	public void testZeroAssociatedObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"4"));
		association.setSourceRoleName("mother");

		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	public void testZeroAssociatedObjectHQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"4"));
		association.setSourceRoleName("mother");

		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(0,results.size());
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setSourceRoleName("father");

		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		InLaw parent = (InLaw)i.next();
		assertNotNull(parent);

		assertNotNull(parent);
		assertNotNull(parent.getId());
		assertEquals(parent.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(parent.getName());
		assertEquals("1",parent.getId().getExtension());
	}

	public void testOneAssociatedObjectHQL1() throws ApplicationException {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"select bride.father from gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride bride "
						+ "where bride.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");

		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		InLaw parent = (InLaw) i.next();
		assertNotNull(parent);

		assertNotNull(parent);
		assertNotNull(parent.getId());
		assertEquals(parent.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(parent.getName());
		assertEquals("1", parent.getId().getExtension());
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setSourceRoleName("mother");

		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		InLaw parent = (InLaw)i.next();
		assertNotNull(parent);

		assertNotNull(parent);
		assertNotNull(parent.getId());
		assertEquals(parent.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(parent.getName());
		assertEquals("2",parent.getId().getExtension());
	}

	public void testOneAssociatedObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select bride.mother from gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride bride "
						+ "where bride.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw");

		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		InLaw parent = (InLaw) i.next();
		assertNotNull(parent);

		assertNotNull(parent);
		assertNotNull(parent.getId());
		assertEquals(parent.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(parent.getName());
		assertEquals("2", parent.getId().getExtension());
	}

	public void testGetAssociation() throws ApplicationException
	{

		Bride searchObject = new Bride();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);// A Bride with both a Mother- and Father-in-Law
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
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());

			fatherInLaw = result.getFather();
			assertNotNull(fatherInLaw);
			assertNotNull(fatherInLaw.getId());
			assertEquals(fatherInLaw.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(fatherInLaw.getName());

			motherInLaw = result.getMother();
			assertNotNull(motherInLaw);
			assertNotNull(motherInLaw.getId());
			assertEquals(motherInLaw.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(motherInLaw.getName());
		}
	}
}
