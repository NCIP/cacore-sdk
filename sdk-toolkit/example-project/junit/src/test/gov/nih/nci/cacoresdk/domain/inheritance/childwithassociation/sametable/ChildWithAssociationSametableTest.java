package test.gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import test.gov.nih.nci.cacoresdk.SDKTestBase;
import gov.nih.nci.system.dao.orm.translator.CQL2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class ChildWithAssociationSametableTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Child With Association Same Table Test Case";
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
		Shoes searchObject = new Shoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Shoes result = (Shoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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
		SportsShoes searchObject = new SportsShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			SportsShoes result = (SportsShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
			assertNotNull(result.getSportsType());
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
		DesignerShoes searchObject = new DesignerShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DesignerShoes result = (DesignerShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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
	public void testGetAssociation() throws ApplicationException
	{
		DesignerShoes searchObject = new DesignerShoes();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());

		Designer designer;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DesignerShoes result = (DesignerShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());

			designer = result.getDesigner();

			assertNotNull(designer);
			assertNotNull(designer.getId());
			assertNotNull(designer.getName());
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
		Designer searchObject = new Designer();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Designer result = (Designer)i.next();
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			SportsShoes result = (SportsShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Shoes result = (Shoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(2,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			DesignerShoes result = (DesignerShoes)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer");
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(3,results.size());

		for(Iterator i = results.iterator();i.hasNext();)
		{
			Designer result = (Designer)i.next();
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
		SportsShoes searchObject = new SportsShoes();
		searchObject.setColor("Purple");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		association.setAttribute(new CQLAttribute("color", CQLPredicate.EQUAL_TO,"6"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
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
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		Shoes searchObject = new Shoes();
		searchObject.setColor("Red");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		SportsShoes result = (SportsShoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(2), result.getId());
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
		SportsShoes searchObject = new SportsShoes();
		searchObject.setColor("Red");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Shoes result = (Shoes)results.iterator().next();
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
		Shoes searchObject = new Shoes();
		searchObject.setColor("Black");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		DesignerShoes result = (DesignerShoes)results.iterator().next();
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
		DesignerShoes searchObject = new DesignerShoes();
		searchObject.setColor("Black");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Shoes result = (Shoes)results.iterator().next();
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
	public void testAssociationCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Shoes result = (Shoes)results.iterator().next();
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
	public void testAssociationCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		SportsShoes result = (SportsShoes)results.iterator().next();
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Prada"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		Shoes result = (Shoes)results.iterator().next();
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Sergio Rossi"));

		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		CQL2HQL converter = new CQL2HQL(getClassCache());
		HQLCriteria hqlCriteria = converter.translate(cqlQuery, false, false);

		Collection results = getApplicationService().query(hqlCriteria);

		assertNotNull(results);
		assertEquals(1,results.size());

		DesignerShoes result = (DesignerShoes)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}
}
