package test.gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class O2OBidirectionalWJoinTest extends SDKISOTestBase
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
	public void testZeroAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		Pendant searchObject = new Pendant();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getShape());
		
		Chain chain = result.getChain();
		assertNull(chain);
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
		Pendant searchObject = new Pendant();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

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
		Pendant searchObject = new Pendant();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
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
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
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
		assertEquals("1",Chain.getId().getExtension());
	}	
	
	public void testOneAssociatedObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select pendant.chain from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant pendant "
						+ "where pendant.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");
		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		Chain Chain = (Chain) i.next();
		assertNotNull(Chain);

		assertNotNull(Chain);
		assertNotNull(Chain.getId());
		assertNotNull(Chain.getMetal());
		assertEquals("1",Chain.getId().getExtension());
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
		assertEquals("1",Pendant.getId().getExtension());	
	}	
	
	public void testOneAssociatedObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select chain.pendant from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain chain "
						+ "where chain.id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant");
		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		Pendant Pendant = (Pendant) i.next();
		assertNotNull(Pendant);

		assertNotNull(Pendant);
		assertNotNull(Pendant.getId());
		assertNotNull(Pendant.getShape());
		assertEquals("1",Pendant.getId().getExtension());	
	}	

	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectCQL() throws ApplicationException
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
		assertEquals(0,results.size());
	}
	
	public void testZeroAssociatedObjectHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select pendant.chain from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant pendant "
						+ "where pendant.id='100'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain");

		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	public void testGetMethods1() throws ApplicationException
	{
		Pendant searchObject = new Pendant();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pendant result = (Pendant)results.iterator().next();
		assertEquals(new Integer(1),result.getChain().getId());

		Ii ii2=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		assertEquals(new Integer(2),result.getChain().getId());
		
		Ii ii3=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		searchObject.setId(ii3);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		assertNull(result.getChain());
		
	}


	public void testGetMethods2() throws ApplicationException
	{
		Chain searchObject = new Chain();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Chain result = (Chain)results.iterator().next();
		assertEquals(new Integer(1),result.getPendant().getId());

		Ii ii2=new Ii();
		ii.setExtension("2");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		assertEquals(new Integer(2),result.getPendant().getId());

		Ii ii3=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii3);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		assertNull(result.getPendant());
		
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
			
			if (new Integer(result.getId().getExtension()) != 3){ // Pendant id = 3 does not have an associated Chain			
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
			
			if (new Integer(result.getId().getExtension()) != 3){ // Chain id = 3 does not have an associated Pendant
				pendant = result.getPendant();
				assertNotNull(pendant);
				assertNotNull(pendant.getId());
				assertNotNull(pendant.getShape());
			}
		}
	}		
}
