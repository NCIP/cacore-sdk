/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class TwoLevelInheritanceSametablerootlevelXMLData2Test extends SDKXMLDataTestBase
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
			assertNotNull(result2.getId().getExtension());
			validateLocalConstant(result2,false,false,true,true);
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
		
		St  st = new St();
		st.setValue("HP");
		searchObject.setBrand(st);
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
			validateLocalConstant(result2,false,false,false,true);
			assertEquals(GRAPHIC_CALCULATOR_ROOT_LOCAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getBrand());
		}
	}
	
	public void testAssociationNestedSearchHQL3() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator where brand='HP'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			GraphicCalculator result = (GraphicCalculator) i.next();
			toXML(result);
			GraphicCalculator result2 = (GraphicCalculator) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			validateLocalConstant(result2,false,true,false,false);
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
		St  st = new St();
		st.setValue("HP");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			ScientificCalculator result = (ScientificCalculator)i.next();
			toXML(result);
			ScientificCalculator result2 = (ScientificCalculator)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			validateLocalConstant(result2,false,false,true,true);
			assertNotNull(result2.getBrand());
		}
	}
	
	public void testAssociationNestedSearchHQL4() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator where brand='HP'");
		Collection results = search(
				hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			ScientificCalculator result = (ScientificCalculator) i.next();
			toXML(result);
			ScientificCalculator result2 = (ScientificCalculator) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			validateLocalConstant(result2,false,false,true,true);
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
		
}
