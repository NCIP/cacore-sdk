package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;

import java.util.Collection;
import java.util.Iterator;

import test.xml.data.SDKXMLDataTestBase;

public class TwoLevelInheritanceXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Two Level Inheritance XML Data Test Case";
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
		Display searchObject = new Display();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Display result = (Display)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"height",result.getHeight());
			validateAttribute(result,"width",result.getWidth());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Display result2 = (Display)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
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
		Monitor searchObject = new Monitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"height",result.getHeight());
			validateAttribute(result,"width",result.getWidth());
			validateAttribute(result,"brand",result.getBrand());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Monitor result2 = (Monitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
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
		CRTMonitor searchObject = new CRTMonitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"height",result.getHeight());
			validateAttribute(result,"width",result.getWidth());
			validateAttribute(result,"brand",result.getBrand());
			validateAttribute(result,"refreshRate",result.getRefreshRate());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			CRTMonitor result2 = (CRTMonitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
			assertNotNull(result2.getBrand());
			assertNotNull(result2.getRefreshRate());
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
		LCDMonitor searchObject = new LCDMonitor();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"height",result.getHeight());
			validateAttribute(result,"width",result.getWidth());
			validateAttribute(result,"brand",result.getBrand());
			validateAttribute(result,"dpiSupported",result.getDpiSupported());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			LCDMonitor result2 = (LCDMonitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
			assertNotNull(result2.getBrand());
			assertNotNull(result2.getDpiSupported());
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
		Monitor searchObject = new Monitor();
		searchObject.setBrand("A");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			toXML(result);
			CRTMonitor result2 = (CRTMonitor)fromXML(result);

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
		CRTMonitor searchObject = new CRTMonitor();
		searchObject.setBrand("A");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			toXML(result);
			Monitor result2 = (Monitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
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
		Monitor searchObject = new Monitor();
		searchObject.setBrand("B");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			toXML(result);
			LCDMonitor result2 = (LCDMonitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
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
		LCDMonitor searchObject = new LCDMonitor();
		searchObject.setBrand("C");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			toXML(result);
			Monitor result2 = (Monitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
			assertNotNull(result2.getBrand());
		}
	}
}
