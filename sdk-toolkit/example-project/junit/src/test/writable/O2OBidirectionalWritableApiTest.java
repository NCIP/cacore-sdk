/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.OrderLine;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.Product;

public class O2OBidirectionalWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(O2OBidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One Bidirectional WritableApi Test Case";
	}
	
	public void testSaveObjectOrderLineOne2OneProduct(){
		log.debug("\n-----------testSaveObjectOrderLineOne2OneProduct()--------\n");
		OrderLine orderLine=new OrderLine();
		orderLine.setName("orderline");
		Product product=new Product();
		product.setName("product");
		orderLine.setProduct(product);
		product.setLine(orderLine);
		
		save(orderLine);
		
		OrderLine resultOrderLine=(OrderLine)getObjectAndLazyObject(OrderLine.class, orderLine.getId(), "product");
		Assert.assertEquals(orderLine.getName(), resultOrderLine.getName());
		Assert.assertEquals(orderLine.getProduct().getName(), resultOrderLine.getProduct().getName());
	}

	public void testDeleteObjectProductOne2OneOrder(){
		log.debug("\n-----------testDeleteObjectOrderLineOne2OneProduct()--------\n");
		OrderLine orderLine=new OrderLine();
		orderLine.setName("orderline");
		Product product=new Product();
		product.setName("product");
		orderLine.setProduct(product);
		product.setLine(orderLine);
		
		save(orderLine);
		
		OrderLine updateOrderLine=(OrderLine)getObjectAndLazyObject(OrderLine.class, orderLine.getId(),"product");
		delete(updateOrderLine);
		
		OrderLine resultOrderLine=(OrderLine)getObject(OrderLine.class, orderLine.getId());
		Product resultProduct=(Product)getObject(Product.class, updateOrderLine.getProduct().getId());
		
		Assert.assertNull(resultOrderLine);
		Assert.assertNull(resultProduct);
	}
	
	public void testUpdateObjectProductOne2OneOrder(){
		log.debug("\n-----------testDeleteObjectOrderLineOne2OneProduct()--------\n");
		OrderLine orderLine=new OrderLine();
		orderLine.setName("orderline");
		Product product=new Product();
		product.setName("product");
		orderLine.setProduct(product);
		product.setLine(orderLine);
		
		save(orderLine);
		
		orderLine.setName("updateOrder");
		update(orderLine);
		
		OrderLine resultOrderLine=(OrderLine)getObject(OrderLine.class, orderLine.getId());
		Assert.assertEquals(orderLine.getName(), resultOrderLine.getName());
	}
}
