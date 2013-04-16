/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2OBidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional With Join XML Data Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch1() throws Exception
	{
		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"shape",result.getShape());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Pendant result2 = (Pendant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getShape());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"metal",result.getMetal());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Chain result2 = (Chain)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getMetal());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getShape());
		
		Chain chain = result2.getChain();
		assertNotNull(chain);
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch1() throws Exception
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getShape());
		
		Chain chain = result2.getChain();
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
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Chain result = (Chain)i.next();
		toXML(result);
		Chain result2 = (Chain)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getMetal());
		assertEquals(new Integer(1),result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch3() throws Exception
	{
		Chain searchObject = new Chain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Pendant result = (Pendant)i.next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getShape());
		assertEquals(new Integer(1),result2.getId());		
	}	

	public void testGetMethods1() throws Exception
	{
		Pendant searchObject = new Pendant();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pendant result = (Pendant)results.iterator().next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		assertEquals(new Integer(1),result2.getChain().getId());

		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		toXML(result);
		result2 = (Pendant)fromXML(result);
		assertEquals(new Integer(2),result2.getChain().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Pendant)results.iterator().next();
		toXML(result);
		result2 = (Pendant)fromXML(result);
		assertNotNull(result2.getChain());
		
	}


	public void testGetMethods2() throws Exception
	{
		Chain searchObject = new Chain();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Chain result = (Chain)results.iterator().next();
		toXML(result);
		Chain result2 = (Chain)fromXML(result);
		assertEquals(new Integer(1),result2.getPendant().getId());

		searchObject.setId(new Integer(2));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		toXML(result);
		result2 = (Chain)fromXML(result);
		assertEquals(new Integer(2),result2.getPendant().getId());

		searchObject.setId(new Integer(3));
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Chain)results.iterator().next();
		toXML(result);
		result2 = (Chain)fromXML(result);
		assertNotNull(result2.getPendant());
		
	}
	
	public void testGetAssociation1() throws Exception
	{

		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Chain chain;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			toXML(result);
			Pendant result2 = (Pendant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getShape());
			
			if (result2.getId() != 3){ // Pendant id = 3 does not have an associated Chain	
				
				validateAssociation(result,"Chain","chain", true, false);
				
				chain = result2.getChain();
				assertNotNull(chain);
				assertNotNull(chain.getId());
				assertNotNull(chain.getMetal());
			}
		}
	}
	
	public void testGetAssociation2() throws Exception
	{

		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		Pendant pendant;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			toXML(result);
			Chain result2 = (Chain)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getMetal());
			
			if (result2.getId() != 3){ // Chain id = 3 does not have an associated Pendant
				
				validateAssociation(result,"Pendant","pendant", true, false);
				
				pendant = result2.getPendant();
				assertNotNull(pendant);
				assertNotNull(pendant.getId());
				assertNotNull(pendant.getShape());
			}
		}
	}		
}
