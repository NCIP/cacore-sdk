package test.gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKTestBase;

public class O2OBidirectionalWJoinTest extends SDKTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional With Join Test Case";
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
		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
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
		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getMetal());
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
	
	public void testAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getShape());
		
		Chain chain = result.getChain();
		assertNotNull(chain);
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociatedObjectsNestedSearch2() throws ApplicationException
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
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
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getShape());
		
		Chain chain = result.getChain();
		assertNotNull(chain);
		assertNotNull(chain.getId());
		assertNotNull(chain.getMetal());
		assertEquals(new Integer(1),chain.getId());
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
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Chain chain = (Chain)i.next();
		assertNotNull(chain);
		
		assertNotNull(chain);
		assertNotNull(chain.getId());
		assertNotNull(chain.getMetal());
		assertEquals(new Integer(1),chain.getId());
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
		Chain searchObject = new Chain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Pendant pendant = (Pendant)i.next();
		assertNotNull(pendant);
		
		assertNotNull(pendant);
		assertNotNull(pendant.getId());
		assertNotNull(pendant.getShape());
		assertEquals(new Integer(1),pendant.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setTargetRoleName("pendant");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Chain Chain = (Chain)i.next();
		assertNotNull(Chain);
		
		assertNotNull(Chain);
		assertNotNull(Chain.getId());
		assertNotNull(Chain.getMetal());
		assertEquals(new Integer(1),Chain.getId());
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setTargetRoleName("chain");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Pendant Pendant = (Pendant)i.next();
		assertNotNull(Pendant);
		
		assertNotNull(Pendant);
		assertNotNull(Pendant.getId());
		assertNotNull(Pendant.getShape());
		assertEquals(new Integer(1),Pendant.getId());
	}	
	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 1
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociatedObjectCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"3"));
		association.setTargetRoleName("pendant");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
	}
	
	public void testGetMethods1() throws ApplicationException
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pendant result = (Pendant)results.iterator().next();
		assertEquals(new Integer(1),result.getChain().getId());


		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		assertEquals(new Integer(2),result.getChain().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		assertNotNull(result.getChain());
		
	}


	public void testGetMethods2() throws ApplicationException
	{
		Chain searchObject = new Chain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Chain result = (Chain)results.iterator().next();
		assertEquals(new Integer(1),result.getPendant().getId());


		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		assertEquals(new Integer(2),result.getPendant().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		assertNotNull(result.getPendant());
		
	}
	
	public void testGetAssociation1() throws ApplicationException
	{

		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Chain chain;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getShape());
			
			if (result.getId() != 3){ // Pendant id = 3 does not have an associated Chain			
				chain = result.getChain();
				assertNotNull(chain);
				assertNotNull(chain.getId());
				assertNotNull(chain.getMetal());
			}
		}
	}
	
	public void testGetAssociation2() throws ApplicationException
	{

		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Pendant pendant;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getMetal());
			
			if (result.getId() != 3){ // Chain id = 3 does not have an associated Pendant
				pendant = result.getPendant();
				assertNotNull(pendant);
				assertNotNull(pendant.getId());
				assertNotNull(pendant.getShape());
			}
		}
	}		
}
