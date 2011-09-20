package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class TwoLevelInheritanceSametablerootlevelXMLDataTest extends SDKXMLDataTestBase
{
	protected String CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "CalculatorRoot LocalConstant";
	protected String FINANCIAL_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "FinancialCalculatorRoot LocalConstant";
	protected String GRAPHIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "GraphicCalculatorRoot LocalConstant";
	protected String SCIENTIFIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE = "ScientificCalculatorRoot LocalConstant";

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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Calculator result2 = (Calculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			
			validateLocalConstant(result2,true,true,true,true);
			assertNotNull(result2.getBrand());
		}
	}
	
	private void validateLocalConstant(Calculator result2,boolean isCalculator,boolean isFinancialCalc,boolean isScientificCalc,boolean graphicCalc) {
		
		if(isCalculator && "gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator".equals(result2.getClass().getName())){
			assertEquals(CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE,result2.getId().getRoot());
		}else if(isFinancialCalc && "gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator".equals(result2.getClass().getName())){
			assertEquals(FINANCIAL_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE,result2.getId().getRoot());
		}else if(isScientificCalc && "gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator".equals(result2.getClass().getName())){
			assertEquals(SCIENTIFIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE,result2.getId().getRoot());
		}else if(graphicCalc && "gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator".equals(result2.getClass().getName())){
			assertEquals(GRAPHIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE,result2.getId().getRoot());
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			FinancialCalculator result2 = (FinancialCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			validateLocalConstant(result2,true,true,false,false);
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			ScientificCalculator result2 = (ScientificCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			validateLocalConstant(result2,false,false,true,true);
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			GraphicCalculator result2 = (GraphicCalculator)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			validateLocalConstant(result2,false,false,false,true);
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
			assertNotNull(result2.getId().getExtension()); 
			validateLocalConstant(result2,true,true,true,true);
			assertNotNull(result2.getBrand());
		}
	}
}
