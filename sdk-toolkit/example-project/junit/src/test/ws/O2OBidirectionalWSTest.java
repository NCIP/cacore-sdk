/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;

import java.util.ArrayList;
import java.util.Collection;


public class O2OBidirectionalWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(OrderLine.class);
		mappedKlasses.add(Product.class);
		
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
		Class targetClass = Product.class;
		Product criteria = new Product();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Product result = (Product)obj;
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
		Class targetClass = OrderLine.class;
		OrderLine criteria = new OrderLine();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			OrderLine result = (OrderLine)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
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
		Class targetClass = Product.class;
		Product criteria = new Product();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Product result = (Product)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] orderLineResults = getAssociationResults(result, "line", 0);
		assertEquals(0,orderLineResults.length);
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Class targetClass = OrderLine.class;
		Product criteria = new Product();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);			
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
	public void testOneAssociatedObjectNestedSearch1() throws Exception
	{
		Class targetClass = Product.class;
		Product criteria = new Product();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Product result = (Product)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());	
		
		Object[] orderLineResults = getAssociationResults(result, "line", 0);
		assertEquals(1,orderLineResults.length);
		
		OrderLine orderLine = (OrderLine)orderLineResults[0];
		assertNotNull(orderLine);
		assertNotNull(orderLine.getId());
		assertNotNull(orderLine.getName());
		assertEquals(new Integer(1),orderLine.getId());		
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Class targetClass = OrderLine.class;
		Product criteria = new Product();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		OrderLine orderLine = (OrderLine)results[0];
		assertNotNull(orderLine);
		assertNotNull(orderLine.getId());
		assertNotNull(orderLine.getName());
		assertEquals(new Integer(1),orderLine.getId());		
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch3() throws Exception
	{
		Class targetClass = Product.class;
		OrderLine criteria = new OrderLine();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Product product = (Product)results[0];
		assertNotNull(product);
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertEquals(new Integer(1),product.getId());			
	}	
	
	
	public void testGetMethods1() throws Exception
	{
		Class targetClass = Product.class;
		Product criteria = new Product();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Product result = (Product)results[0];
		Object[] orderLineResults = getAssociationResults(result, "line", 0);
		assertEquals(new Integer(1),((OrderLine)orderLineResults[0]).getId());
		
		criteria.setId(new Integer(2));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (Product)results[0];
		orderLineResults = getAssociationResults(result, "line", 0);
		assertEquals(new Integer(2),((OrderLine)orderLineResults[0]).getId());
		
		criteria.setId(new Integer(3));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (Product)results[0];
		orderLineResults = getAssociationResults(result, "line", 0);
		assertEquals(0,orderLineResults.length);		
		
	}

	public void testGetMethods2() throws Exception
	{
		Class targetClass = OrderLine.class;
		OrderLine criteria = new OrderLine();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		OrderLine result = (OrderLine)results[0];
		Object[] productResults = getAssociationResults(result, "product", 0);
		assertEquals(new Integer(1),((Product)productResults[0]).getId());
		
		criteria.setId(new Integer(2));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (OrderLine)results[0];
		productResults = getAssociationResults(result, "product", 0);
		assertEquals(new Integer(2),((Product)productResults[0]).getId());
		
		criteria.setId(new Integer(3));
		results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		result = (OrderLine)results[0];
		productResults = getAssociationResults(result, "product", 0);
		assertEquals(0,productResults.length);		
		
	}
	
	public void testGetAssociation1() throws Exception
	{
		Class targetClass = Product.class;
		Product criteria = new Product();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Product result = (Product)results[0];
		Object[] orderLineResults = getAssociationResults(result, "line", 0);
		OrderLine orderLine = (OrderLine)orderLineResults[0];
		assertNotNull(orderLine);
		assertNotNull(orderLine.getId());
		assertNotNull(orderLine.getName());
		assertEquals(new Integer(1),orderLine.getId());		
	}
	
	public void testGetAssociation2() throws Exception
	{
		Class targetClass = OrderLine.class;
		OrderLine criteria = new OrderLine();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		OrderLine result = (OrderLine)results[0];
		Object[] productResults = getAssociationResults(result, "product", 0);
		Product product = (Product)productResults[0];
		assertNotNull(product);
		assertNotNull(product.getId());
		assertNotNull(product.getName());		
		assertEquals(new Integer(1),product.getId());		
	}		
}
