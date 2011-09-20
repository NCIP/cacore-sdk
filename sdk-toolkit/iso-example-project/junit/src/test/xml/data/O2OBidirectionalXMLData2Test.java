package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;


public class O2OBidirectionalXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional XML Data Test Case";
	}
	public void testOneAssociatedObjectNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product");
		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();
		Product result = (Product) i.next();
		toXML(result);
		Product result2 = (Product) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());

		OrderLine orderLine = result2.getLine();
		assertNotNull(orderLine);
		assertNotNull(orderLine.getId());
		assertNotNull(orderLine.getName());
		assertEquals("1", orderLine.getId().getExtension());
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
		Product searchObject = new Product();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		OrderLine orderLine = (OrderLine)i.next();
		toXML(orderLine);
		OrderLine result2 = (OrderLine)fromXML(orderLine);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("1",result2.getId().getExtension());
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
		OrderLine searchObject = new OrderLine();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Product product = (Product)i.next();
		toXML(product);
		Product result2 = (Product)fromXML(product);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("1",result2.getId().getExtension());
	}	

	public void testGetMethods1() throws Exception
	{
		Product searchObject = new Product();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Product result = (Product)results.iterator().next();
		toXML(result);
		Product result2 = (Product)fromXML(result);
		assertEquals("1",result2.getLine().getId().getExtension());

		Ii ii2 = new Ii();
		ii2.setExtension("2");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Product)results.iterator().next();
		toXML(result);
		result2 = (Product)fromXML(result);
		assertEquals("2",result2.getLine().getId().getExtension());

		Ii ii3 = new Ii();
		ii3.setExtension("3");
		searchObject.setId(ii3);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (Product)results.iterator().next();
		toXML(result);
		result2 = (Product)fromXML(result);
		assertNull(result2.getLine());
		
	}
}
