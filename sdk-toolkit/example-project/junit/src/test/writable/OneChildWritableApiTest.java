package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal;

public class OneChildWritableApiTest extends SDKWritableApiTestBase{

	private static Logger log = Logger.getLogger(OneChildWritableApiTest.class);
	public static String getTestCaseName() {
		return "One Child Writable Api Test Case";
	}
	
	public void testSaveObjectWithOneChildInheritance(){
		log.debug("\n-------testSaveObjectWithOneChildInheritance()-------");
		Mammal mammal=new Mammal();
		mammal.setHairColor("blk");
		
		Human human=new Human();
		human.setDiet("veg");
		human.setHairColor("blk");
		
		save(mammal);
		save(human);
		
		Mammal resultMammal=(Mammal)getObject(Mammal.class, mammal.getId());
		Human resultHuman=(Human)getObject(Human.class, human.getId());
		
		Assert.assertEquals(mammal.getHairColor(), resultMammal.getHairColor());
		Assert.assertEquals(human.getHairColor(), resultHuman.getHairColor());
	}
	
	public void testUpdateObjectWithOneChildInheritance(){
		log.debug("\n-------testUpdateObjectWithOneChildInheritance()-------");
		Mammal mammal=new Mammal();
		mammal.setHairColor("blk");
		
		Human human=new Human();
		human.setDiet("veg");
		human.setHairColor("blk");
		
		save(mammal);
		save(human);
		
		Mammal updateMammal=(Mammal)getObject(Mammal.class, mammal.getId());
		Human updateHuman=(Human)getObject(Human.class, human.getId());
		updateMammal.setHairColor("red");
		updateHuman.setHairColor("red");
		
		update(updateMammal);
		update(updateHuman);
		
		Mammal resultMammal=(Mammal)getObject(Mammal.class, mammal.getId());
		Human resultHuman=(Human)getObject(Human.class, human.getId());
		
		Assert.assertEquals(updateMammal.getHairColor(), resultMammal.getHairColor());
		Assert.assertEquals(updateHuman.getHairColor(), resultHuman.getHairColor());
	}
	
	public void testDeleteObjectWithOneChildInheritance(){
		log.debug("\n-------testDeleteObjectWithOneChildInheritance()-------");
		Mammal mammal=new Mammal();
		mammal.setHairColor("blk");
		
		Human human=new Human();
		human.setDiet("veg");
		human.setHairColor("blk");
		
		save(mammal);
		save(human);
		
		Mammal deleteMammal=(Mammal)getObject(Mammal.class, mammal.getId());
		Human deleteHuman=(Human)getObject(Human.class, human.getId());

		delete(deleteMammal);
		delete(deleteHuman);
		
		Mammal resultMammal=(Mammal)getObject(Mammal.class, mammal.getId());
		Human resultHuman=(Human)getObject(Human.class, human.getId());
		
		Assert.assertNull(resultMammal);
		Assert.assertNull(resultHuman);
	}
}
