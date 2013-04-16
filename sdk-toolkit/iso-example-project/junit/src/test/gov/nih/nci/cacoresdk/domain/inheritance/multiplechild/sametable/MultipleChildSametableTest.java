/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization;
import gov.nih.nci.iso21090.St;
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

public class MultipleChildSametableTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Multiple Child Same Table Test Case";
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
		Organization searchObject = new Organization();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
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
		GovtOrganization searchObject = new GovtOrganization();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			GovtOrganization result = (GovtOrganization)i.next();
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
	public void testEntireObjectNestedSearch3() throws ApplicationException
	{
		PvtOrganization searchObject = new PvtOrganization();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PvtOrganization result = (PvtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertNotNull(result.getCeo());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			GovtOrganization result = (GovtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");

		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Organization result = (Organization) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PvtOrganization result = (PvtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertNotNull(result.getCeo());
		}
	}

	public void testEntireObjectHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			PvtOrganization result = (PvtOrganization) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertNotNull(result.getCeo());
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 *
	 * @throws ApplicationException
	 */
	public void testZeroAssociationNestedSearch() throws ApplicationException {
		Organization searchObject = new Organization();
		St st = new St();
		st.setValue("Private Org Name");
		searchObject.setName(st);// No such GovtOrganization row exists
		Collection results = getApplicationService()
				.search(
						"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization",
						searchObject);

		assertNotNull(results);
		assertEquals(0, results.size());

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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Invalid Org Name"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(0,results.size());

	}

	public void testZeroAssociationHQL() throws ApplicationException
	{
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization where name='Invalid Org Name'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
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
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		GovtOrganization searchObject = new GovtOrganization();
		St st=new St();
		st.setValue("Public Org Name");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		Organization searchObject = new Organization();
		St st=new St();
		st.setValue("Private Org Name");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PvtOrganization result = (PvtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
	public void testAssociationNestedSearch3() throws ApplicationException
	{
		PvtOrganization searchObject = new PvtOrganization();
		St st=new St();
		st.setValue("Private Org Name");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Public Org Name"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization where name='Public Org Name'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Organization result = (Organization) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
	public void testAssociationCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Public Org Name"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			GovtOrganization result = (GovtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization where name='Public Org Name'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.GovtOrganization");

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			GovtOrganization result = (GovtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
	public void testAssociationCQL3() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Private Org Name"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testAssociationHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization where name='Private Org Name'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Organization result = (Organization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
	public void testAssociationCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Private Org Name"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PvtOrganization result = (PvtOrganization)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testAssociationHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization where name='Private Org Name'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.PvtOrganization");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			PvtOrganization result = (PvtOrganization) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
}
