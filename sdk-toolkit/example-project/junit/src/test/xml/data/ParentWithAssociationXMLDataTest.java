/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor;

import test.xml.mapping.SDKXMLDataTestBase;

public class ParentWithAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Parent With Association XML Data Test Case";
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
		Professor searchObject = new Professor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor",searchObject );

		assertNotNull(results);
		assertEquals(15,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Professor result = (Professor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Professor result2 = (Professor)fromXML(result);
			
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
		TenuredProfessor searchObject = new TenuredProfessor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			TenuredProfessor result = (TenuredProfessor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			validateAttribute(result,"tenuredYear",result.getTenuredYear());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			TenuredProfessor result2 = (TenuredProfessor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			assertNotNull(result2.getTenuredYear());

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
	public void testEntireObjectNestedSearch3() throws Exception
	{
		AssociateProfessor searchObject = new AssociateProfessor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			AssociateProfessor result = (AssociateProfessor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			validateAttribute(result,"yearsServed",result.getYearsServed());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			AssociateProfessor result2 = (AssociateProfessor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			assertNotNull(result2.getYearsServed());
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
	public void testEntireObjectNestedSearch4() throws Exception
	{
		Assistant searchObject = new Assistant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant",searchObject );

		assertNotNull(results);
		assertEquals(9,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Assistant result = (Assistant)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Assistant result2 = (Assistant)fromXML(result);
			
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
	public void testEntireObjectNestedSearch5() throws Exception
	{
		AssistantProfessor searchObject = new AssistantProfessor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			AssistantProfessor result = (AssistantProfessor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			validateAttribute(result,"joiningYear",result.getJoiningYear());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			AssistantProfessor result2 = (AssistantProfessor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			assertNotNull(result2.getJoiningYear());
		}
	}	
		
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch1() throws Exception
	{
		Professor searchObject = new Professor();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		TenuredProfessor result = (TenuredProfessor)results.iterator().next();
		toXML(result);
		TenuredProfessor result2 = (TenuredProfessor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch2() throws Exception
	{
		TenuredProfessor searchObject = new TenuredProfessor();
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Professor result = (Professor)results.iterator().next();
		toXML(result);
		Professor result2 = (Professor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(2), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch3() throws Exception
	{
		Professor searchObject = new Professor();
		searchObject.setId(new Integer(7));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AssociateProfessor result = (AssociateProfessor)results.iterator().next();
		toXML(result);
		AssociateProfessor result2 = (AssociateProfessor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(7), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch4() throws Exception
	{
		AssociateProfessor searchObject = new AssociateProfessor();
		searchObject.setId(new Integer(7));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Professor result = (Professor)results.iterator().next();
		toXML(result);
		Professor result2 = (Professor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(7), result2.getId());
	}
	
}
