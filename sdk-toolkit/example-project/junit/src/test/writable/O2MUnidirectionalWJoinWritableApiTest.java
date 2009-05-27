package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Button;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.withjoin.Shirt;

public class O2MUnidirectionalWJoinWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(O2MUnidirectionalWJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to Many Unidirectional With Join WritableApi Test Case";
	}
	
	public void testSaveObjectShirt(){
		log.debug("-----testSaveObjectShirt()  method ----\n\n");
		Shirt shirt=new Shirt();
		shirt.setStyle("style");
		
		save(shirt);
		
		Shirt result=(Shirt)getObject(Shirt.class, shirt.getId());
		Assert.assertEquals(shirt.getStyle(), result.getStyle());
	}
	
	public void testSaveObjectShirtWithM2MButtonColl(){
		log.debug("-----saveKeyChain()  method ----\n\n");
		Shirt shirt=new Shirt();
		shirt.setStyle("style");
		Button button =new Button();
		button.setHoles(12);
		Collection<Button> buttons=new HashSet<Button>();
		buttons.add(button);
		shirt.setButtonCollection(buttons);
		
		save(shirt);
		
		Shirt result=(Shirt)getObjectAndLazyCollection(Shirt.class, shirt.getId(),"buttonCollection");
		Assert.assertEquals(shirt.getStyle(), result.getStyle());
		Assert.assertEquals(shirt.getButtonCollection().size(), result.getButtonCollection().size());
	}
	
	public void testUpdateObjectShirtWithCascadeAllM2MButtonColl(){
		log.debug("-----testUpdateObjectShirtWithCascadeAllM2MButtonColl()  method ----\n\n");
		Shirt shirt=new Shirt();
		shirt.setStyle("style");
		Button button =new Button();
		button.setHoles(12);
		Collection<Button> buttons=new HashSet<Button>();
		buttons.add(button);
		shirt.setButtonCollection(buttons);
		
		save(shirt);
		
		Shirt updateShirt=(Shirt)getObjectAndLazyCollection(Shirt.class, shirt.getId(),"buttonCollection");
		updateShirt.setStyle("updateShirt");
		updateShirt.getButtonCollection().iterator().next().setHoles(20);
		
		update(updateShirt);

		Shirt result=(Shirt)getObjectAndLazyCollection(Shirt.class, updateShirt.getId(),"buttonCollection");
		Assert.assertEquals(updateShirt.getStyle(), result.getStyle());
		Assert.assertEquals(updateShirt.getButtonCollection().iterator().next().getHoles(), result.getButtonCollection().iterator().next().getHoles());
	}
	
	public void testDeleteObjectShirtWithM2MButtonColl(){
		log.debug("-----testDeleteObjectShirtWithM2MButtonColl()  method ----\n\n");
		Shirt shirt=new Shirt();
		shirt.setStyle("style");
		Button button =new Button();
		button.setHoles(12);
		Collection<Button> buttons=new HashSet<Button>();
		buttons.add(button);
		shirt.setButtonCollection(buttons);
		
		save(shirt);
		button=shirt.getButtonCollection().iterator().next();
		delete(shirt);
		
		Shirt shirtResult=(Shirt)getObjectAndLazyCollection(Shirt.class, shirt.getId(),"buttonCollection");
		Button buttonResult=(Button)getObject(Button.class, button.getId());
		Assert.assertNull(shirtResult);
		Assert.assertNull(buttonResult);
	}
}
