/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent;

import java.util.ArrayList;
import java.util.Collection;


public class O2OMultipleAssociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Child.class);
		mappedKlasses.add(Parent.class);
		
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
		Class targetClass = Parent.class;
		Parent criteria = new Parent();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(10,results.length);
		
		for (Object obj : results){
			Parent result = (Parent)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
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
		Class targetClass = Child.class;
		Child criteria = new Child();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Child result = (Child)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
		}		
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Class targetClass = Child.class;
		Child criteria = new Child();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Child result = (Child)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(0,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(1,motherResults.length);			
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Class targetClass = Child.class;
		Child criteria = new Child();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Child result = (Child)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(1,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(0,motherResults.length);			
	}
	

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch3() throws Exception
	{
//		Child searchObject = new Child();
//		searchObject.setId(new Integer(5));
//		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );
//
//		assertNotNull(results);
//		assertEquals(1,results.size());
//		
//		Iterator i = results.iterator();
//		Child result = (Child)i.next();
//		assertNotNull(result);
//		assertNotNull(result.getId());
//		assertNotNull(result.getName());
//		
//		assertNull(result.getFather());
//		assertNull(result.getMother());
		
		Class targetClass = Child.class;
		Child criteria = new Child();
		criteria.setId(new Integer(5));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Child result = (Child)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(0,fatherResults.length);
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(0,motherResults.length);		
	}	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch() throws Exception
	{
		Class targetClass = Parent.class;
		Child criteria = new Child();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);			

		assertNotNull(results);
		assertEquals(1,results.length);
	}
	
	public void testGetAssociation() throws Exception
	{
		Class targetClass = Child.class;
		Child criteria = new Child();
		criteria.setId(new Integer(2));// A Child with both a Mother and Father

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Child result = (Child)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] fatherResults = getAssociationResults(result, "father", 0);
		assertEquals(1,fatherResults.length);
		Parent father = (Parent)fatherResults[0];
		assertNotNull(father);
		assertNotNull(father.getId());
		assertNotNull(father.getName());	
		
		Object[] motherResults = getAssociationResults(result, "mother", 0);
		assertEquals(1,motherResults.length);		
		Parent mother = (Parent)motherResults[0];
		assertNotNull(mother);
		assertNotNull(mother.getId());
		assertNotNull(mother.getName());		
	}	
}
