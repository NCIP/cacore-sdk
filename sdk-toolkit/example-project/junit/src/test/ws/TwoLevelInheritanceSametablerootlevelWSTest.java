/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;

import java.util.ArrayList;
import java.util.Collection;


public class TwoLevelInheritanceSametablerootlevelWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Same Table Root Level WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(FinancialCalculator.class);
		mappedKlasses.add(Calculator.class);
		mappedKlasses.add(Goverment.class);
		mappedKlasses.add(GraphicCalculator.class);
		mappedKlasses.add(ScientificCalculator.class);
		
		return mappedKlasses;
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
		Class targetClass = Calculator.class;
		Calculator criteria = new Calculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Calculator result = (Calculator)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());	
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
		
		Class targetClass = FinancialCalculator.class;
		FinancialCalculator criteria = new FinancialCalculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for (Object obj : results){
			FinancialCalculator result = (FinancialCalculator)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());	
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
	
		Class targetClass = ScientificCalculator.class;
		ScientificCalculator criteria = new ScientificCalculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			ScientificCalculator result = (ScientificCalculator)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());	
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
		
		Class targetClass = GraphicCalculator.class;
		GraphicCalculator criteria = new GraphicCalculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for (Object obj : results){
			GraphicCalculator result = (GraphicCalculator)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getBrand());	
		}			
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociationNestedSearch() throws Exception
	{
		Class targetClass = FinancialCalculator.class;
		Calculator criteria = new Calculator();
		criteria.setId(2);//No such row

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);		
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch() throws Exception
	{
		Class targetClass = GraphicCalculator.class;	
//		ScientificCalculator criteria = new ScientificCalculator();
		GraphicCalculator criteria = new GraphicCalculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		GraphicCalculator result = (GraphicCalculator)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());			
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
		Class targetClass = ScientificCalculator.class;	
		GraphicCalculator criteria = new GraphicCalculator();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		ScientificCalculator result = (ScientificCalculator)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());	
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
		Class targetClass = GraphicCalculator.class;	
//		ScientificCalculator criteria = new ScientificCalculator();
		GraphicCalculator criteria = new GraphicCalculator();
		criteria.setBrand("HP");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		GraphicCalculator result = (GraphicCalculator)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getBrand());
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
		Class targetClass = ScientificCalculator.class;	

		GraphicCalculator criteria = new GraphicCalculator();
		criteria.setBrand("HP");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		ScientificCalculator result = (ScientificCalculator)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getBrand());	
	}
}
