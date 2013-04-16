/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.sametable.Organization;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class TwoLevelInheritanceSametablerootlevelXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Same Table Root Level XML Data Test Case";
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
		Calculator searchObject = new Calculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Calculator result = (Calculator)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"brand",result.getBrand());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Calculator result2 = (Calculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
		FinancialCalculator searchObject = new FinancialCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FinancialCalculator result = (FinancialCalculator)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"brand",result.getBrand());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			FinancialCalculator result2 = (FinancialCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
		ScientificCalculator searchObject = new ScientificCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"brand",result.getBrand());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			ScientificCalculator result2 = (ScientificCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
		GraphicCalculator searchObject = new GraphicCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"brand",result.getBrand());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			GraphicCalculator result2 = (GraphicCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
		ScientificCalculator searchObject = new ScientificCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			toXML(result);
			GraphicCalculator result2 = (GraphicCalculator)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
	public void testAssociationNestedSearch2() throws Exception
	{
		GraphicCalculator searchObject = new GraphicCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			toXML(result);
			ScientificCalculator result2 = (ScientificCalculator)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
	public void testAssociationNestedSearch3() throws Exception
	{
		ScientificCalculator searchObject = new ScientificCalculator();
		searchObject.setBrand("HP");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			toXML(result);
			GraphicCalculator result2 = (GraphicCalculator)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
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
	public void testAssociationNestedSearch4() throws Exception
	{
		GraphicCalculator searchObject = new GraphicCalculator();
		searchObject.setBrand("HP");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			toXML(result);
			ScientificCalculator result2 = (ScientificCalculator)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getBrand());
		}
	}
}
