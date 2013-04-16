/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class O2OUnidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional With Join Test Case";
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
		Bag searchObject = new Bag();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(11,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bag result = (Bag)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "style", "value", result.getStyle().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Bag result2 = (Bag)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getStyle());
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
		Handle searchObject = new Handle();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle",searchObject );

		assertNotNull(results);
		assertEquals(12,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Handle result = (Handle)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "color", "value", result.getColor().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Handle result2 = (Handle)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getColor());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Bag searchObject = new Bag();
		Ii ii = new Ii();
		ii.setExtension("11");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bag result = (Bag)i.next();
		toXML(result);
		Bag result2 = (Bag)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getStyle());
		
		Handle handle = result2.getHandle();
		assertNull(handle);
	}
	
	public void testZeroAssociatedObjectsNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag where id='11'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag");

		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();
		Bag result = (Bag) i.next();
		toXML(result);
		Bag result2 = (Bag) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());  
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getStyle());

		Handle handle = result2.getHandle();
		assertNull(handle);
	}
}
