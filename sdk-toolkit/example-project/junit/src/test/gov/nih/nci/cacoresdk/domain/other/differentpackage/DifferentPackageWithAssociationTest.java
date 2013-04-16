/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.other.differentpackage;

import gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class DifferentPackageWithAssociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Different Package With Association Test Case";
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
		Dessert searchObject = new Dessert();

		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Dessert result = (Dessert)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		Pie searchObject = new Pie();

		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getFilling());
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
		Utensil searchObject = new Utensil();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Utensil result = (Utensil)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
	public void testEntireObjectCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getFilling());
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

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getFilling());
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

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Utensil result = (Utensil)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
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
		Pie searchObject = new Pie();
		searchObject.setFilling("Non-existent Filling");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

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
		Dessert searchObject = new Dessert();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		association.setAttribute(new CQLAttribute("filling", CQLPredicate.EQUAL_TO,"Non-existent filling"));

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
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
		Pie searchObject = new Pie();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Dessert result = (Dessert)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3),result.getId());
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
		Dessert searchObject = new Dessert();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getFilling());
		assertEquals(new Integer(4), result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"3"));

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Dessert result = (Dessert)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"4"));

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(4), result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil");
		association.setTargetRoleName("utensilCollection");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Spoon"));

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		Dessert result = (Dessert)results.iterator().next();
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
		association.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil");
		association.setTargetRoleName("utensilCollection");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Spoon"));

		target.setName("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}

	public void testGetAssociation() throws ApplicationException
	{
		Pie searchObject = new Pie();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Utensil utensil;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getFilling());

			for(Iterator j = result.getUtensilCollection().iterator();j.hasNext();)
			{
				utensil = (Utensil)j.next();
				assertNotNull(utensil);
				assertNotNull(utensil.getId());
				assertNotNull(utensil.getName());
			}
		}
	}

	public void testIsDifferentPackage1() throws ApplicationException
	{
		assertFalse(Dessert.class.getPackage().getName().equals(Pie.class.getPackage().getName()));
	}

	public void testIsDifferentPackage2() throws ApplicationException
	{
		assertFalse(Dessert.class.getPackage().getName().equals(Utensil.class.getPackage().getName()));
	}

	public void testIsDifferentPackage3() throws ApplicationException
	{
		assertTrue(Pie.class.getPackage().getName().equals(Utensil.class.getPackage().getName()));
	}
}
