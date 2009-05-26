package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle;

public class O2OUnidirectionalWritableApiTest extends SDKWritableApiBaseTest{
	private static Logger log = Logger.getLogger(O2OUnidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One Unidirectional WritableApi Test Case";
	}
	
	public void testSaveObjectBag(){
		log.debug("\n--------------testSaveObjectBag()------------\n");
		Bag bag=new Bag();
		bag.setStyle("style");
		
		save(bag);
		
		Bag result=(Bag)getObject(Bag.class, bag.getId());
		Assert.assertEquals(bag.getStyle(), result.getStyle());
	}
	
	public void testSaveObjectBagWithOne2OneAssociation(){
		log.debug("\n--------------testSaveObjectBagWithOne2OneAssociation()------------\n");
		Bag bag=new Bag();
		bag.setStyle("style");
		Handle handle=new Handle();
		handle.setColor("color");
		bag.setHandle(handle);
		
		save(bag);
		
		Bag result = (Bag) getObjectAndLazyObject(Bag.class, bag.getId(),"handle");
		Assert.assertEquals(bag.getStyle(), result.getStyle());
		Assert.assertEquals(bag.getHandle().getColor(), result.getHandle().getColor());
	}
	
	public void testUpdateObjectBagWithOne2OneAssociation(){
		log.debug("\n--------------testUpdateObjectBagWithOne2OneAssociation()------------\n");
		Bag bag=new Bag();
		bag.setStyle("style");
		Handle handle=new Handle();
		handle.setColor("color");
		bag.setHandle(handle);
		
		save(bag);
		
		Bag updateBag=(Bag)getObjectAndLazyObject(Bag.class, bag.getId(),"handle");
		updateBag.setStyle("updateStyle");
		updateBag.getHandle().setColor("updateColor");
		update(updateBag);
		
		Bag result = (Bag) getObjectAndLazyObject(Bag.class, bag.getId(),"handle");
		Assert.assertEquals(updateBag.getStyle(), result.getStyle());
		Assert.assertEquals(updateBag.getHandle().getColor(), result.getHandle().getColor());
	}
	
	public void testDeleteObjectBagWithOne2OneCascadeDelete(){
		log.debug("\n--------------testDeleteObjectBagWithOne2OneCascadeDelete()------------\n");
		Bag bag=new Bag();
		bag.setStyle("style");
		Handle handle=new Handle();
		handle.setColor("color");
		bag.setHandle(handle);
		
		save(bag);
		
		Bag deleteBag = (Bag) getObjectAndLazyObject(Bag.class, bag.getId(),"handle");
		Handle deleteHandle = deleteBag.getHandle();
		delete(deleteBag);

		Bag resultBag = (Bag) getObject(Bag.class, deleteBag.getId());
		Handle resultHandle = (Handle) getObject(Handle.class, deleteHandle.getId());

		Assert.assertNull(resultBag);
		Assert.assertNull(resultHandle);
	}
}
