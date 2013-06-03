/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Display;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;


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
	public void xtestEntireObjectNestedSearch1() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "height", "value", result.getHeight().getValue());
			validateIso90210Element(result, "width", "value", result.getWidth().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Display result2 = (Display)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch2() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "height", "value", result.getHeight().getValue());
			validateIso90210Element(result, "width", "value", result.getWidth().getValue());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Monitor result2 = (Monitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch3() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "height", "value", result.getHeight().getValue());
			validateIso90210Element(result, "width", "value", result.getWidth().getValue());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			validateIso90210Element(result, "refreshRate", "value", result.getRefreshRate().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			CRTMonitor result2 = (CRTMonitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch4() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "height", "value", result.getHeight().getValue());
			validateIso90210Element(result, "width", "value", result.getWidth().getValue());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			validateIso90210Element(result, "dpiSupported", "value", result.getDpiSupported().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			LCDMonitor result2 = (LCDMonitor)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
			assertNotNull(result2.getBrand());
			assertNotNull(result2.getDpiSupported());
		}
	}
	
	public void xtestEntireObjectNestedSearchHQL4() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor");

		LCDMonitor searchObject = new LCDMonitor();
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			LCDMonitor result = (LCDMonitor) i.next();
			toXML(result);

			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "height", "value", result.getHeight().getValue());
			validateIso90210Element(result, "width", "value", result.getWidth().getValue());
			validateIso90210Element(result, "brand", "value", result.getBrand().getValue());
			validateIso90210Element(result, "dpiSupported", "value", result.getDpiSupported().getValue());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			LCDMonitor result2 = (LCDMonitor) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestAssociationNestedSearch1() throws Exception
	{
		Monitor searchObject = new Monitor();
		
		St  st = new St();
		st.setValue("A");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			CRTMonitor result = (CRTMonitor)i.next();
			toXML(result);
			CRTMonitor result2 = (CRTMonitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getBrand());
		}
	}

	public void xtestAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor where brand='A'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.CRTMonitor");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			CRTMonitor result = (CRTMonitor) i.next();
			toXML(result);
			CRTMonitor result2 = (CRTMonitor) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestAssociationNestedSearch2() throws Exception
	{
		CRTMonitor searchObject = new CRTMonitor();
		St  st = new St();
		st.setValue("A");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			toXML(result);
			Monitor result2 = (Monitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestAssociationNestedSearch3() throws Exception
	{
		Monitor searchObject = new Monitor();
		St  st = new St();
		st.setValue("B");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.LCDMonitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			LCDMonitor result = (LCDMonitor)i.next();
			toXML(result);
			LCDMonitor result2 = (LCDMonitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
		St  st = new St();
		st.setValue("C");
		searchObject.setBrand(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.Monitor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Monitor result = (Monitor)i.next();
			toXML(result);
			Monitor result2 = (Monitor)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getHeight());
			assertNotNull(result2.getWidth());
			assertNotNull(result2.getBrand());
		}
	}
}
