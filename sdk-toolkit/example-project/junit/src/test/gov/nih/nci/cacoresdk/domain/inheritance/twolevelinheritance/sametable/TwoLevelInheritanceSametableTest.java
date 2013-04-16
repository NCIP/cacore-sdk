/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLGroup;
import gov.nih.nci.system.query.cql.CQLLogicalOperator;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class TwoLevelInheritanceSametableTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Same Table Test Case";
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
		Goverment searchObject = new Goverment();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Goverment result = (Goverment)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
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
		CommunistGovt searchObject = new CommunistGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CommunistGovt result = (CommunistGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
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
		DemocraticGovt searchObject = new DemocraticGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
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
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			ParliamantaryGovt result = (ParliamantaryGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
			assertNotNull(result.getPrimeMinister());
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
	public void testEntireObjectNestedSearch5() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
			assertNotNull(result.getPresident());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Goverment result = (Goverment)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CommunistGovt result = (CommunistGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			ParliamantaryGovt result = (ParliamantaryGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
	public void testEntireObjectCQL5() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getPresident());
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
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		searchObject.setId(1);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

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
		Goverment searchObject = new Goverment();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CommunistGovt result = (CommunistGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		association.setAttribute(new CQLAttribute("primeMinister", CQLPredicate.EQUAL_TO,"George Bush"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
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
		ParliamantaryGovt searchObject = new ParliamantaryGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		DemocraticGovt searchObject = new DemocraticGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
	public void testAssociationNestedSearch4() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
		}
	}

	public void testAssociationNestedSearch5() throws ApplicationException
	{
		PresidentialGovt searchObject = new PresidentialGovt();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		association.setAttribute(new CQLAttribute("primeMinister", CQLPredicate.EQUAL_TO,"Tony Blair"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			ParliamantaryGovt result = (ParliamantaryGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		association.setAttribute(new CQLAttribute("country", CQLPredicate.EQUAL_TO,"USA"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DemocraticGovt result = (DemocraticGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			PresidentialGovt result = (PresidentialGovt)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
	public void testAssociationCQL5() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association1 = new CQLAssociation();
		association1.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt");
		association1.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		CQLAssociation association2 = new CQLAssociation();
		association2.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt");
		association2.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));


		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.OR);
		group.addAssociation(association1);
		group.addAssociation(association2);

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt");
		target.setGroup(group);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Goverment result = (Goverment)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCountry());
		}
	}
}
