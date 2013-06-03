/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef;
import gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant;

import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

public class M2OUnidirectionalXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Many to One Unidirectional XML Data Test Case";
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
		Restaurant searchObject = new Restaurant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Restaurant",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Restaurant result = (Restaurant)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Restaurant result2 = (Restaurant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());
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
		Chef searchObject = new Chef();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chef result = (Chef)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Chef result2 = (Chef)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());
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
		Chef searchObject = new Chef();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		toXML(result);
		Chef result2 = (Chef)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
	
		assertNull(result2.getRestaurant());
	}
	
	public void testAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef where id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytoone.unidirectional.Chef");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Chef result = (Chef)i.next();
		toXML(result);
		Chef result2 = (Chef)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
	
		assertNull(result2.getRestaurant());
	}
}
