package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;

import java.util.ArrayList;
import java.util.Collection;


public class TwoLevelInheritanceWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(CRTMonitor.class);
		mappedKlasses.add(Display.class);
		mappedKlasses.add(LCDMonitor.class);
		mappedKlasses.add(Monitor.class);
		
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
		Class targetClass = Display.class;
		Display criteria = new Display();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Display result = (Display)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getHeight());
			assertNotNull(result.getWidth());	
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
		Class targetClass = Monitor.class;
		Monitor criteria = new Monitor();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Monitor result = (Monitor)obj;
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
		Class targetClass = CRTMonitor.class;
		CRTMonitor criteria = new CRTMonitor();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		for (Object obj : results){
			CRTMonitor result = (CRTMonitor)obj;
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
		Class targetClass = LCDMonitor.class;
		LCDMonitor criteria = new LCDMonitor();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			LCDMonitor result = (LCDMonitor)obj;
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
		Class targetClass = Monitor.class;
		CRTMonitor criteria = new CRTMonitor();
		criteria.setBrand("B");//No such CRTMonitor row

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
	public void testAssociationNestedSearch1() throws Exception
	{
//		Class targetClass = CRTMonitor.class;		
		Class targetClass = Monitor.class;	
		Monitor criteria = new Monitor();
		criteria.setBrand("A");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		CRTMonitor result = (CRTMonitor)results[0];
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
	public void testAssociationNestedSearch2() throws Exception
	{
		Class targetClass = Monitor.class;	
		CRTMonitor criteria = new CRTMonitor();
		criteria.setBrand("A");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Monitor result = (Monitor)results[0];
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
	public void testAssociationNestedSearch3() throws Exception
	{
//		Class targetClass = LCDMonitor.class;
		Class targetClass = Monitor.class;			
		Monitor criteria = new Monitor();
		criteria.setBrand("B");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		LCDMonitor result = (LCDMonitor)results[0];
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
		Class targetClass = Monitor.class;	
		LCDMonitor criteria = new LCDMonitor();
		criteria.setBrand("C");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Monitor result = (Monitor)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getBrand());			
	}
}
