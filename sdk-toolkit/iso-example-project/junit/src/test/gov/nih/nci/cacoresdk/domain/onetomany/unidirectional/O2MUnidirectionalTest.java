package test.gov.nih.nci.cacoresdk.domain.onetomany.unidirectional;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class O2MUnidirectionalTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "One to Many Unidirectional Test Case";
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
		KeyChain searchObject = new KeyChain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			KeyChain result = (KeyChain)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertEquals(result.getName().getNullFlavor(),"MSK");
		}
	}

	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain");
		assertNotNull(results);
		assertEquals(5, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			KeyChain result = (KeyChain) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertEquals(result.getName().getNullFlavor(),"MSK");
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
		LatchKey searchObject = new LatchKey();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			LatchKey result = (LatchKey)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getType());
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
		KeyChain searchObject = new KeyChain();
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		KeyChain result = (KeyChain)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		assertEquals(result.getName().getNullFlavor(),"MSK");
		
		Collection keyCollection = result.getKeyCollection();
		assertEquals(0, keyCollection.size());
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
		KeyChain searchObject = new KeyChain();
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey",searchObject );

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
		KeyChain searchObject = new KeyChain();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		KeyChain result = (KeyChain)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		assertEquals(result.getName().getNullFlavor(),"MSK");
		
		Collection keyCollection = result.getKeyCollection();
		assertEquals(true, keyCollection.size()>0);
		
		Iterator j = keyCollection.iterator();
		LatchKey key = (LatchKey)j.next();
		assertNotNull(key);
		assertNotNull(key.getId());
		assertEquals(key.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(key.getType());
		assertEquals("1",key.getId().getExtension());
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
		KeyChain searchObject = new KeyChain();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		LatchKey key = (LatchKey)i.next();
		assertNotNull(key);
		assertNotNull(key.getId());
		assertEquals(key.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(key.getType());
		assertEquals("1",key.getId().getExtension());
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
			association.setName("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain");
			association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
			
			target.setName("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey");
			target.setAssociation(association);
			cqlQuery.setTarget(target);
	
			Collection results = getApplicationService().query(cqlQuery);
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
		association.setName("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain");
		association.setAttribute(new CQLAttribute("id",CQLPredicate.EQUAL_TO,"1"));
		association.setSourceRoleName("keyCollection");
		
		target.setName("gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey");
		target.setAssociation(association);
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		LatchKey key = (LatchKey)i.next();
		assertNotNull(key);
		assertNotNull(key.getId());
		assertEquals(key.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(key.getType());
		assertEquals("1",key.getId().getExtension());
	}	
}
