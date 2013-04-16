/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;
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

public class TwoLevelInheritanceTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Test Case";
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
		Display searchObject = new Display();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Display result = (Display)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getHeight());
			assertNotNull(result.getWidth());
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
		Monitor searchObject = new Monitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		CRTMonitor searchObject = new CRTMonitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
			assertNotNull(result.getRefreshRate());
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
		LCDMonitor searchObject = new LCDMonitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
			assertNotNull(result.getDpiSupported());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Display result = (Display)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getWidth());
			assertNotNull(result.getHeight());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(4,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
			assertNotNull(result.getDpiSupported());
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
		CRTMonitor searchObject = new CRTMonitor();
		searchObject.setBrand("B");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

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
		Monitor searchObject = new Monitor();
		searchObject.setBrand("A");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");
		association.setAttribute(new CQLAttribute("brand", CQLPredicate.EQUAL_TO,"B"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
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
		CRTMonitor searchObject = new CRTMonitor();
		searchObject.setBrand("A");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		Monitor searchObject = new Monitor();
		searchObject.setBrand("B");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		LCDMonitor searchObject = new LCDMonitor();
		searchObject.setBrand("C");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");
		association.setAttribute(new CQLAttribute("brand", CQLPredicate.EQUAL_TO,"A"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		association.setAttribute(new CQLAttribute("width", CQLPredicate.EQUAL_TO,"1"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");
		association.setAttribute(new CQLAttribute("height", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());
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
		association1.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");
		association1.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));

		CQLAssociation association2 = new CQLAssociation();
		association2.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");
		association2.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"3"));


		CQLGroup group = new CQLGroup();
		group.setLogicOperator(CQLLogicalOperator.OR);
		group.addAssociation(association1);
		group.addAssociation(association2);

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor");
		target.setGroup(group);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Display result = (Display)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getWidth());
			assertNotNull(result.getHeight());
		}
	}
}
