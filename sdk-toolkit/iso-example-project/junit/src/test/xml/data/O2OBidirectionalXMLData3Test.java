package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class O2OBidirectionalXMLData3Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional XML Data Test Case";
	}

	public void testGetMethods2() throws Exception
	{
		OrderLine searchObject = new OrderLine();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		OrderLine result = (OrderLine)results.iterator().next();
		toXML(result);
		OrderLine result2 = (OrderLine)fromXML(result);
		assertEquals("1",result2.getProduct().getId().getExtension());

		Ii ii2 = new Ii();
		ii2.setExtension("2");
		searchObject.setId(ii2);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (OrderLine)results.iterator().next();
		toXML(result);
		result2 = (OrderLine)fromXML(result);
		assertEquals("2",result2.getProduct().getId().getExtension());

		Ii ii3 = new Ii();
		ii3.setExtension("3");
		searchObject.setId(ii3);
		results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		result = (OrderLine)results.iterator().next();
		toXML(result);
		result2 = (OrderLine)fromXML(result);
		assertNull(result2.getProduct());
		
	}
	
	public void testGetMethodsHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine");

		assertNotNull(results);
		assertEquals(1, results.size());

		OrderLine result = (OrderLine) results.iterator().next();
		toXML(result);
		OrderLine result2 = (OrderLine) fromXML(result);
		assertEquals("1",result2.getProduct().getId().getExtension());

		hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine where id='2'");
		results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine");

		assertNotNull(results);
		assertEquals(1, results.size());

		result = (OrderLine) results.iterator().next();
		toXML(result);
		result2 = (OrderLine) fromXML(result);
		assertEquals("2", result2.getProduct().getId().getExtension());

		hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine where id='3'");
		results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine");

		assertNotNull(results);
		assertEquals(1, results.size());

		result = (OrderLine) results.iterator().next();
		toXML(result);
		result2 = (OrderLine) fromXML(result);
		assertNull(result2.getProduct());
	}
	
	public void testGetAssociation1() throws Exception
	{

		Product searchObject = new Product();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		OrderLine line;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Product result = (Product)i.next();
			toXML(result);
			Product result2 = (Product)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());
			
			if (new Integer(result2.getId().getExtension()) < 3){ // Product id = 3 does not have an associated Orderline	
				validateAssociation(result,"OrderLine","line", false);
				line = result2.getLine();
				assertNotNull(line);
				assertNotNull(line.getId());
				assertNotNull(line.getName());
			}
		}
	}
	
	public void testGetAssociation2() throws Exception
	{

		OrderLine searchObject = new OrderLine();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		Product product;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			OrderLine result = (OrderLine)i.next();
			toXML(result);
			OrderLine result2 = (OrderLine)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());
			
			if (new Integer(result2.getId().getExtension()) < 3){ // OrderLine id = 3,4, and 5 don't have an associated Product
				validateAssociation(result,"Product","product", false);
				product = result2.getProduct();
				assertNotNull(product);
				assertNotNull(product.getId());
				assertNotNull(product.getName());
			}
		}
	}		
}
