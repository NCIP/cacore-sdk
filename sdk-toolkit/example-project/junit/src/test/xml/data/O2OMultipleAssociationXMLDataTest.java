/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class O2OMultipleAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association Test Case";
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
		Parent searchObject = new Parent();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Parent result = (Parent)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Parent result2 = (Parent)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());			
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
		Child searchObject = new Child();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Child result = (Child)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Child result2 = (Child)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Child searchObject = new Child();
		searchObject.setId(new Integer(4));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Child result = (Child)i.next();
		toXML(result);
		Child result2 = (Child)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNull(result2.getFather());
		assertNotNull(result2.getMother());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Child searchObject = new Child();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Child result = (Child)i.next();
		toXML(result);
		Child result2 = (Child)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNotNull(result2.getFather());
		assertNull(result2.getMother());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch3() throws Exception
	{
		Child searchObject = new Child();
		searchObject.setId(new Integer(5));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Child result = (Child)i.next();
		toXML(result);
		Child result2 = (Child)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNull(result2.getFather());
		assertNull(result2.getMother());
	}
	
	public void testGetAssociation() throws Exception
	{

		Child searchObject = new Child();
		searchObject.setId(2);// A Child with both a Mother and Father
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Parent father;
		Parent mother;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Child result = (Child)i.next();
			toXML(result);
			Child result2 = (Child)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			
			validateAssociation(result,"Parent","father", true, false);
			
			father = result2.getFather();
			assertNotNull(father);
			assertNotNull(father.getId());
			assertNotNull(father.getName());
			
			validateAssociation(result,"Parent","mother", true, false);
			
			mother = result2.getMother();
			assertNotNull(mother);
			assertNotNull(mother.getId());
			assertNotNull(mother.getName());
		}
	}
}
