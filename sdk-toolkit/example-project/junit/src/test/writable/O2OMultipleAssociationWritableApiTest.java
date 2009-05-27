package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent;

public class O2OMultipleAssociationWritableApiTest extends SDKWritableApiTestBase{
	private static Logger log = Logger.getLogger(O2OMultipleAssociationWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One MultipleAssociation WritableApi Test Case";
	}
	
	public void testSaveObjectChild(){
		log.debug("\n----------testSaveObjectChild()-------------\n");
		Child child=new Child();
		child.setName("child");
		
		save(child);
		
		Child result=(Child)getObject(Child.class, child.getId());
		Assert.assertEquals(child.getName(), result.getName());
	}
	
	public void testSaveObjectChildWithMultipleAssociation(){
		log.debug("\n----------testSaveObjectChildWithMultipleAssociation()-------------\n");
		Child child=new Child();
		child.setName("child");	
		Parent father=new Parent();
		father.setName("father");
		Parent mother=new Parent();
		mother.setName("mother");
		child.setFather(father);
		child.setMother(mother);
		
		save(child);
		
		Child result=(Child)getObjectAndMultipleLazyObjects(Child.class, child.getId(), "mother", "father");
		Assert.assertEquals(child.getName(), result.getName());
		Assert.assertEquals(child.getFather().getName(), result.getFather().getName());
		Assert.assertEquals(child.getMother().getName(), result.getMother().getName());
	}
	
	public void testDeleteObjectChildWithMultipleAssociationCascadeDelete(){
		log.debug("\n----------testDeleteObjectChildWithMultipleAssociationCascadeDelete()-------------\n");
		Child child=new Child();
		child.setName("child");		
		Parent father=new Parent();
		father.setName("father");
		Parent mother=new Parent();
		mother.setName("mother");		
		child.setFather(father);
		child.setMother(mother);
		
		save(child);
		
		Child deleteChild=(Child)getObject(Child.class, child.getId());
		delete(deleteChild);
		
		Child resultChild=(Child)getObject(Child.class, child.getId());
		Assert.assertNull(resultChild);
	}
	
	public void testUpdateObjectChildWithMultipleAssociationCascadeSaveUpdate(){
		log.debug("\n----------testUpdateObjectChildWithMultipleAssociationCascadeSaveUpdate()-------------\n");
		Child child=new Child();
		child.setName("child");
		Parent father=new Parent();
		father.setName("father");
		Parent mother=new Parent();
		mother.setName("mother");
		child.setFather(father);
		child.setMother(mother);
		
		save(child);
		
		Child updateChild=(Child)getObjectAndMultipleLazyObjects(Child.class, child.getId(), "mother", "father");
		updateChild.setName("updateName");
		updateChild.getFather().setName("updateFather");
		updateChild.getMother().setName("updateMother");
		update(updateChild);
		
		Child result=(Child)getObjectAndMultipleLazyObjects(Child.class, child.getId(), "mother", "father");
		Assert.assertEquals(updateChild.getName(), result.getName());
		Assert.assertEquals(updateChild.getFather().getName(), result.getFather().getName());
		Assert.assertEquals(updateChild.getMother().getName(), result.getMother().getName());
	}
}
