package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.Calculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.FinancialCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.GraphicCalculator;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametablerootlevel.ScientificCalculator;

public class TwoLevelInheritanceSametablerootlevelWritableApiTest extends SDKWritableApiTestBase {
	private static Logger log = Logger.getLogger(TwoLevelInheritanceSametablerootlevelWritableApiTest.class);
	public static String getTestCaseName() {
		return "Two Level Inheritance Same Table Root Level Writable Api Test Case";
	}
	
	public void testSaveObjectWithTwoLevelInheritanceSameTable(){
		log.debug("\n-----------testSaveObjectWithTwoLevelInheritanceSameTable()---------\n");
		Calculator calculator=new Calculator();
		calculator.setBrand("calbrand");
		FinancialCalculator financialCalculator=new FinancialCalculator();
		financialCalculator.setBrand("financialbrand");
		ScientificCalculator scientificCalculator=new ScientificCalculator();
		scientificCalculator.setBrand("scientificBrand");
		GraphicCalculator graphicCalculator=new GraphicCalculator();
		graphicCalculator.setBrand("graphicBrand");
		
		save(calculator);
		save(financialCalculator);
		save(scientificCalculator);
		save(graphicCalculator);
		
		Calculator resultCalculator=(Calculator)getObject(Calculator.class, calculator.getId());
		FinancialCalculator resultGraphicalCalculator=(FinancialCalculator)getObject(FinancialCalculator.class, financialCalculator.getId());
		ScientificCalculator resuScientificCalculator=(ScientificCalculator)getObject(ScientificCalculator.class, scientificCalculator.getId());
		GraphicCalculator resultFinancialCalculator=(GraphicCalculator)getObject(GraphicCalculator.class, graphicCalculator.getId());
		
		Assert.assertEquals(calculator.getBrand(), resultCalculator.getBrand());
		Assert.assertEquals(financialCalculator.getBrand(), resultGraphicalCalculator.getBrand());
		Assert.assertEquals(scientificCalculator.getBrand(), resuScientificCalculator.getBrand());
		Assert.assertEquals(graphicCalculator.getBrand(), resultFinancialCalculator.getBrand());
	}
	
	public void testUpdateObjectWithTwoLevelInheritanceSameTable(){
		log.debug("\n-----------testUpdateObjectWithTwoLevelInheritanceSameTable()---------\n");
		Calculator calculator=new Calculator();
		calculator.setBrand("calbrand");
		FinancialCalculator financialCalculator=new FinancialCalculator();
		financialCalculator.setBrand("financialbrand");
		ScientificCalculator scientificCalculator=new ScientificCalculator();
		scientificCalculator.setBrand("scientificBrand");
		GraphicCalculator graphicCalculator=new GraphicCalculator();
		graphicCalculator.setBrand("graphicBrand");
		
		save(calculator);
		save(financialCalculator);
		save(scientificCalculator);
		save(graphicCalculator);
		
		Calculator updateCalculator=(Calculator)getObject(Calculator.class, calculator.getId());
		updateCalculator.setBrand("calbrand");
		FinancialCalculator updateFinancialCalculator=(FinancialCalculator)getObject(FinancialCalculator.class, financialCalculator.getId());
		updateFinancialCalculator.setBrand("calbrand");
		ScientificCalculator updateScientificCalculator=(ScientificCalculator)getObject(ScientificCalculator.class, scientificCalculator.getId());
		updateScientificCalculator.setBrand("calbrand");
		GraphicCalculator updateGraphicalCalculator=(GraphicCalculator)getObject(GraphicCalculator.class, graphicCalculator.getId());
		updateFinancialCalculator.setBrand("calbrand");
		
		update(updateCalculator);
		update(updateFinancialCalculator);
		update(updateScientificCalculator);
		update(updateGraphicalCalculator);
		
		Calculator resultCalculator=(Calculator)getObject(Calculator.class, calculator.getId());
		FinancialCalculator resultFinancialCalculator=(FinancialCalculator)getObject(FinancialCalculator.class, financialCalculator.getId());
		ScientificCalculator resultScientificCalculator=(ScientificCalculator)getObject(ScientificCalculator.class, scientificCalculator.getId());
		GraphicCalculator resultGraphicalCalculator=(GraphicCalculator)getObject(GraphicCalculator.class, graphicCalculator.getId());

		Assert.assertEquals(updateCalculator.getBrand(), resultCalculator.getBrand());
		Assert.assertEquals(updateGraphicalCalculator.getBrand(), resultGraphicalCalculator.getBrand());
		Assert.assertEquals(updateScientificCalculator.getBrand(), resultScientificCalculator.getBrand());
		Assert.assertEquals(updateFinancialCalculator.getBrand(), resultFinancialCalculator.getBrand());
	}
	
	public void testDeleteObjectWithTwoLevelInheritanceSameTable(){
		log.debug("\n-----------testDeleteObjectWithTwoLevelInheritanceSameTable()---------\n");
		Calculator calculator=new Calculator();
		calculator.setBrand("calbrand");
		FinancialCalculator financialCalculator=new FinancialCalculator();
		financialCalculator.setBrand("financialbrand");
		ScientificCalculator scientificCalculator=new ScientificCalculator();
		scientificCalculator.setBrand("scientificBrand");
		GraphicCalculator graphicCalculator=new GraphicCalculator();
		graphicCalculator.setBrand("graphicBrand");
		
		save(calculator);
		save(financialCalculator);
		save(scientificCalculator);
		save(graphicCalculator);
		
		Calculator deleteCalculator=(Calculator)getObject(Calculator.class, calculator.getId());
		FinancialCalculator deleteFinancialCalculator=(FinancialCalculator)getObject(FinancialCalculator.class, financialCalculator.getId());
		ScientificCalculator deleteScientificCalculator=(ScientificCalculator)getObject(ScientificCalculator.class, scientificCalculator.getId());
		GraphicCalculator deleteGraphicalCalculator=(GraphicCalculator)getObject(GraphicCalculator.class, graphicCalculator.getId());
		
		delete(deleteCalculator);
		delete(deleteFinancialCalculator);
		delete(deleteScientificCalculator);
		delete(deleteGraphicalCalculator);
		
		Calculator resultCalculator=(Calculator)getObject(Calculator.class, calculator.getId());
		FinancialCalculator resultFinancialCalculator=(FinancialCalculator)getObject(FinancialCalculator.class, financialCalculator.getId());
		ScientificCalculator resultScientificCalculator=(ScientificCalculator)getObject(ScientificCalculator.class, scientificCalculator.getId());
		GraphicCalculator resultGraphicalCalculator=(GraphicCalculator)getObject(GraphicCalculator.class, graphicCalculator.getId());
		
		Assert.assertNull(resultCalculator);
		Assert.assertNull(resultFinancialCalculator);
		Assert.assertNull(resultScientificCalculator);
		Assert.assertNull(resultGraphicalCalculator);
	}
}
