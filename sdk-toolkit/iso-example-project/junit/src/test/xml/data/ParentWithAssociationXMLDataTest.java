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


import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;


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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Professor result2 = (Professor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			validateIso90210Element(result, "tenuredYear", "value", result.getTenuredYear().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			TenuredProfessor result2 = (TenuredProfessor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			validateIso90210Element(result, "yearsServed", "value", result.getYearsServed().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			AssociateProfessor result2 = (AssociateProfessor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Assistant result2 = (Assistant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());			
		}
	}
}
