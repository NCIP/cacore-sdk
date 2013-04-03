package test.gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class TwoLevelInheritanceSametablerootlevelTest extends SDKISOTestBase
{
	
	protected String CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "CalculatorRoot LocalConstant";
	protected String FINANCIAL_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "FinancialCalculatorRoot LocalConstant";
	protected String GRAPHIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "GraphicCalculatorRoot LocalConstant";
	protected String SCIENTIFIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "ScientificCalculatorRoot LocalConstant";
	
	public static String getTestCaseName()
	{
		return "Two Level Inheritance Same Table Root Level Test Case";
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
		Calculator searchObject = new Calculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Calculator result = (Calculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
	
	private void assertCalculatorRoot(Calculator calculator) {
		if (calculator instanceof GraphicCalculator) {
			assertEquals(calculator.getId().getRoot(),
					GRAPHIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE);

		} else if (calculator instanceof FinancialCalculator) {
			assertEquals(calculator.getId().getRoot(),
					FINANCIAL_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE);

		} else if (calculator instanceof ScientificCalculator) {
			assertEquals(calculator.getId().getRoot(),
					SCIENTIFIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE);

		} else {
			assertEquals(calculator.getId().getRoot(),
					CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE);
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
		FinancialCalculator searchObject = new FinancialCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FinancialCalculator result = (FinancialCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
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
		ScientificCalculator searchObject = new ScientificCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
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
	public void testEntireObjectNestedSearch4() throws ApplicationException
	{
		GraphicCalculator searchObject = new GraphicCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
	


	public void testEntireObjectHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			GraphicCalculator result = (GraphicCalculator) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
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
		Calculator searchObject = new Calculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FinancialCalculator result = (FinancialCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch() throws ApplicationException
	{
		ScientificCalculator searchObject = new ScientificCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
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
		GraphicCalculator searchObject = new GraphicCalculator();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch3() throws ApplicationException
	{
		ScientificCalculator searchObject = new ScientificCalculator();
		St st=new St();
		st.setValue("HP");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraphicCalculator result = (GraphicCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch4() throws ApplicationException
	{
		GraphicCalculator searchObject = new GraphicCalculator();
		St st=new St();
		st.setValue("HP");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
	
	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator where brand='HP'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator");

		assertNotNull(results);
		assertEquals(1, results.size());
		for (Iterator i = results.iterator(); i.hasNext();) {
			GraphicCalculator result = (GraphicCalculator) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertCalculatorRoot(result);
			assertNotNull(result.getBrand());
		}
	}
}
