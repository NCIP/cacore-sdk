package test.gov.nih.nci.cacoresdk.domain.inheritance.implicit;

import gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class ImplicitParentWithAssociationTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "Implicit Parent With Association Test Case";
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
		AngelFish searchObject = new AngelFish();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			AngelFish result = (AngelFish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
			assertNotNull(result.getFinSize());
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
		DiscusFish searchObject = new DiscusFish();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DiscusFish result = (DiscusFish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
			assertNotNull(result.getPrimaryColor());
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
		Fish searchObject = new Fish();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Fish result = (Fish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
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
	public void xtestEntireObjectNestedSearch4() throws ApplicationException
	{
		FishTank searchObject = new FishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FishTank result = (FishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
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
		FreshwaterFishTank searchObject = new FreshwaterFishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FreshwaterFishTank result = (FreshwaterFishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getFilterModel());
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
	public void testEntireObjectNestedSearch6() throws ApplicationException
	{
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			SaltwaterFishTank result = (SaltwaterFishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getProteinSkimmerModel());
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
	public void testEntireObjectNestedSearch7() throws ApplicationException
	{
		Substrate searchObject = new Substrate();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Substrate result = (Substrate)i.next();
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
	public void xtestEntireObjectNestedSearch8() throws ApplicationException
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Tank result = (Tank)i.next();
			assertNotNull(result);
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
	public void testEntireObjectNestedSearch9() throws ApplicationException
	{
		TankAccessory searchObject = new TankAccessory();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			TankAccessory result = (TankAccessory)i.next();
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
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			AngelFish result = (AngelFish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
			assertNotNull(result.getFinSize());
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
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DiscusFish result = (DiscusFish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
			assertNotNull(result.getPrimaryColor());
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
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Fish result = (Fish)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
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
	public void xtestEntireObjectCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FishTank result = (FishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
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
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FreshwaterFishTank result = (FreshwaterFishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getFilterModel());
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
	public void testEntireObjectCQL6() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			SaltwaterFishTank result = (SaltwaterFishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getProteinSkimmerModel());
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
	public void testEntireObjectCQL7() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Substrate result = (Substrate)i.next();
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
	public void xtestEntireObjectCQL8() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Tank result = (Tank)i.next();
			assertNotNull(result);
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
	public void testEntireObjectCQL9() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			TankAccessory result = (TankAccessory)i.next();
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
		FishTank searchObject = new FishTank();
		Ii ii = new Ii();
		ii.setExtension("5");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"5"));// no such row
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

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
		Fish searchObject = new Fish();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AngelFish result = (AngelFish)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getGenera());
		assertNotNull(result.getFinSize());
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
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		AngelFish searchObject = new AngelFish();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
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
	public void xtestAssociationNestedSearch3() throws ApplicationException
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationNestedSearch4() throws ApplicationException
	{
		FishTank searchObject = new FishTank();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Tank result = (Tank)results.iterator().next();
		assertNotNull(result);
	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationNestedSearch5() throws ApplicationException
	{
		FishTank searchObject = new FishTank();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FreshwaterFishTank result = (FreshwaterFishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getFilterModel());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
		assertEquals(new Integer(1),result.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationNestedSearch6() throws ApplicationException
	{
		FreshwaterFishTank searchObject = new FreshwaterFishTank();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
		assertEquals(new Integer(1), result.getId());
	}
	

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationNestedSearch7() throws ApplicationException
	{
		Fish searchObject = new Fish();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Tank result = (Tank)results.iterator().next();
		assertNotNull(result);
	}	
	

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationNestedSearch9() throws ApplicationException
	{
		TankAccessory searchObject = new TankAccessory();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		
		
		boolean flag = false;
		try {
			getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );
		} catch (Exception e) { //outer join fetches not currently supported by hibernate for implicit polymorphic queries
			flag = true;
		}

		assertTrue(flag);

	}	
	
//	/**
//	 * Uses Nested Search Criteria for inheritance as association in search
//	 * Verifies that the results are returned 
//	 * Verifies size of the result set
//	 * Verifies that none of the attribute is null
//	 * 
//	 * @throws ApplicationException
//	 */
//	public void testAssociationNestedSearch10() throws ApplicationException
//	{
//		Tank searchObject = new Tank();
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory",searchObject );
//
//		assertNotNull(results);
//		assertEquals(3,results.size());
//		
//		TankAccessory result = (TankAccessory)results.iterator().next();
//		assertNotNull(result);
//		assertNotNull(result.getId());
//		assertNotNull(result.getName());
//		assertEquals(new Integer(1), result.getId());
//	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch11() throws ApplicationException
	{
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Substrate result = (Substrate)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
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
	public void testAssociationCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"3"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AngelFish result = (AngelFish)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getFinSize());
		assertNotNull(result.getGenera());
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
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"3"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getGenera());
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
	public void xtestAssociationCQL3() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
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
	public void xtestAssociationCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Tank result = (Tank)results.iterator().next();
		assertNotNull(result);
	}
	
	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void xtestAssociationCQL5() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FreshwaterFishTank result = (FreshwaterFishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
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
	public void xtestAssociationCQL6() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
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
	public void xtestAssociationCQL8() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		association.setTargetRoleName("tank");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"4"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getGenera());
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
	public void testAssociationCQL10() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank");
		association.setTargetRoleName("tankCollection");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"4"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		boolean flag = false;
		try {
			getApplicationService().query(cqlQuery);
		} catch (Exception e){
			flag = true;
		}

		assertTrue(flag);
	}	
	

	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationCQL11() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate");
		association.setTargetRoleName("substrateCollection");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"3"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		SaltwaterFishTank result = (SaltwaterFishTank)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getProteinSkimmerModel());
		assertNotNull(result.getShape());
		assertEquals(new Integer(4), result.getId());
	}	
	
	public void xtestGetAssociation1() throws ApplicationException
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		TankAccessory tankAccessory;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Tank result = (Tank)i.next();
			assertNotNull(result);
		}
	}	

	public void xtestGetAssociation2() throws ApplicationException
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());

		Fish fish;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Tank result = (Tank)i.next();
			assertNotNull(result);
		}
	}


	public void testGetAssociation3() throws ApplicationException
	{
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Substrate substrate;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			SaltwaterFishTank result = (SaltwaterFishTank)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());

			for(Iterator j = result.getSubstrateCollection().iterator();j.hasNext();)
			{
				substrate = (Substrate)j.next();
				assertNotNull(substrate);
				assertNotNull(substrate.getId());
				assertNotNull(substrate.getName());
			}
		}		
	}	
	
	public void testGetAssociation4() throws ApplicationException
	{
		Fish searchObject = new Fish();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		Tank tank;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Fish fish = (Fish)i.next();
			assertNotNull(fish);
			assertNotNull(fish.getId());
			assertNotNull(fish.getGenera());

			tank = fish.getTank();
			assertNotNull(tank);
		}		
	}
	
	public void testGetAssociation5() throws ApplicationException
	{
		TankAccessory searchObject = new TankAccessory();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());

		boolean flag = false;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			TankAccessory tankAccessory = (TankAccessory)i.next();
			assertNotNull(tankAccessory);
			assertNotNull(tankAccessory.getId());
			assertNotNull(tankAccessory.getName());
			
			flag = false;
			
			try {
				tankAccessory.getTankCollection();
			} catch (Exception e) {
				flag = true; //Outer-join fetching is not currently supported by Hibernate
			}
			
			assertTrue(flag);
		}		
	}		
}
