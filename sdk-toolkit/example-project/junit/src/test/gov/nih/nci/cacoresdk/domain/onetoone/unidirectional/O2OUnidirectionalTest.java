package test.gov.nih.nci.cacoresdk.domain.onetoone.unidirectional;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person;
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

public class O2OUnidirectionalTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional Test Case";
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
		Person searchObject = new Person();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Person result = (Person)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
		Address searchObject = new Address();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Address result = (Address)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getZip());
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Person result = (Person)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());

		Address address = result.getLivesAt();
		assertNull(address);
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address",searchObject );

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
		Person searchObject = new Person();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();
		Person result = (Person)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());

		Address address = result.getLivesAt();
		assertNotNull(address);

		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());
		assertEquals(new Integer(1),address.getId());
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
		Person searchObject = new Person();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		Address address = (Address)i.next();
		assertNotNull(address);

		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());
		assertEquals(new Integer(1),address.getId());
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
	public void testNoAssociationCQL() throws ApplicationException
	{
		boolean flag = false;
		try
		{
			CQLQuery cqlQuery = new CQLQuery();
			CQLObject target = new CQLObject();

			CQLAssociation association = new CQLAssociation();
			association.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));

			target.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address");
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
	public void testOneAssociatedObjectCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setSourceRoleName("livesAt");

		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Iterator i = results.iterator();

		Address address = (Address)i.next();
		assertNotNull(address);

		assertNotNull(address);
		assertNotNull(address.getId());
		assertNotNull(address.getZip());
		assertEquals(new Integer(1),address.getId());
	}

	public void testGetAssociation() throws ApplicationException
	{

		Person searchObject = new Person();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());

		Address address;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Person result = (Person)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());

			if (result.getId() < 4){//Person id=1,2,3 have an associated Address; the others don't
				address = result.getLivesAt();
				assertNotNull(address);
				assertNotNull(address.getId());
				assertNotNull(address.getZip());
			}
		}
	}
}
