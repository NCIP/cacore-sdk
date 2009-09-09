package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel;

public class ParentWithAssociationSametableWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(ParentWithAssociationSametableWritableApiTest.class);
	public static String getTestCaseName() {
		return "Parent With Association Same Table Test Case";
	}
	
	public void testSaveObjectParentWithAssociationSameTableInheritance(){
		log.debug("\n-------testSaveObjectParentWithAssociationSameTableInheritance()-------");
		Wheel wheel1=new Wheel();
		wheel1.setRadius(20);
		Wheel wheel2=new Wheel();
		wheel2.setRadius(20);
		Wheel wheel3=new Wheel();
		wheel3.setRadius(20);
		
		Luggage luggage = new Luggage();
		luggage.setCapacity(23);
		luggage.setWheel(wheel1);
		HardTop hardTop=new HardTop();
		hardTop.setCapacity(200);
		hardTop.setKeyCode(100);
		hardTop.setWheel(wheel2);
		SoftTop softTop=new SoftTop();
		softTop.setCapacity(500);
		softTop.setExpandable(true);
		softTop.setWheel(wheel3);
		
		save(wheel1);
		save(wheel2);
		save(wheel3);
		save(luggage);
		save(hardTop);
		save(softTop);

		Luggage resultLuggage = (Luggage) getObjectAndLazyObject(Luggage.class, luggage.getId(),"wheel");
		HardTop resultHardTop = (HardTop) getObjectAndLazyObject(HardTop.class, hardTop.getId(),"wheel");
		SoftTop resultSoftTop = (SoftTop) getObjectAndLazyObject(SoftTop.class, softTop.getId(),"wheel");
		
		Assert.assertEquals(luggage.getCapacity(), resultLuggage.getCapacity());
		Assert.assertEquals(hardTop.getCapacity(), resultHardTop.getCapacity());
		Assert.assertEquals(hardTop.getWheel().getRadius(), resultHardTop.getWheel().getRadius());
		Assert.assertEquals(softTop.getCapacity(), resultSoftTop.getCapacity());
	}
	
	public void testUpdateObjectParentWithAssociationSameTableInheritance(){
		log.debug("\n-------testSaveObjectParentWithAssociationSameTableInheritance()-------");
		Wheel wheel1=new Wheel();
		wheel1.setRadius(20);
		Wheel wheel2=new Wheel();
		wheel2.setRadius(20);
		Wheel wheel3=new Wheel();
		wheel3.setRadius(20);
		
		Luggage luggage = new Luggage();
		luggage.setCapacity(23);
		luggage.setWheel(wheel1);
		HardTop hardTop=new HardTop();
		hardTop.setCapacity(200);
		hardTop.setKeyCode(100);
		hardTop.setWheel(wheel2);
		SoftTop softTop=new SoftTop();
		softTop.setCapacity(500);
		softTop.setExpandable(true);
		softTop.setWheel(wheel3);
		
		save(wheel1);
		save(wheel2);
		save(wheel3);
		save(luggage);
		save(hardTop);
		save(softTop);

		Luggage updateLuggage = (Luggage) getObjectAndLazyObject(Luggage.class, luggage.getId(),"wheel");
		Wheel updateWheel1=updateLuggage.getWheel();
		updateWheel1.setRadius(200);
		HardTop updateHardTop = (HardTop) getObjectAndLazyObject(HardTop.class, hardTop.getId(),"wheel");
		Wheel updateWheel2=updateHardTop.getWheel();
		updateWheel2.setRadius(200);
		SoftTop updateSoftTop = (SoftTop) getObjectAndLazyObject(SoftTop.class, softTop.getId(),"wheel");
		Wheel updateWheel3=updateSoftTop.getWheel();
		updateWheel3.setRadius(200);
		
		
		update(updateWheel1);
		update(updateWheel2);
		update(updateWheel3);
		update(updateLuggage);
		update(updateHardTop);
		update(updateSoftTop);
		
		Luggage resultLuggage = (Luggage) getObjectAndLazyObject(Luggage.class, luggage.getId(),"wheel");
		HardTop resultHardTop = (HardTop) getObjectAndLazyObject(HardTop.class, hardTop.getId(),"wheel");
		SoftTop resultSoftTop = (SoftTop) getObjectAndLazyObject(SoftTop.class, softTop.getId(),"wheel");
		
		Assert.assertEquals(updateLuggage.getCapacity(), resultLuggage.getCapacity());
		Assert.assertEquals(updateLuggage.getWheel().getRadius(), resultLuggage.getWheel().getRadius());
		Assert.assertEquals(updateHardTop.getCapacity(), resultHardTop.getCapacity());
		Assert.assertEquals(updateHardTop.getWheel().getRadius(), resultHardTop.getWheel().getRadius());
		Assert.assertEquals(updateSoftTop.getCapacity(), resultSoftTop.getCapacity());
		Assert.assertEquals(updateSoftTop.getWheel().getRadius(), resultSoftTop.getWheel().getRadius());
	}
	
	public void testDeleteObjectParentWithAssociationSameTableInheritance(){
		log.debug("\n-------testSaveObjectParentWithAssociationSameTableInheritance()-------");
		Wheel wheel1=new Wheel();
		wheel1.setRadius(20);
		Wheel wheel2=new Wheel();
		wheel2.setRadius(20);
		Wheel wheel3=new Wheel();
		wheel3.setRadius(20);
		
		Luggage luggage = new Luggage();
		luggage.setCapacity(23);
		luggage.setWheel(wheel1);
		HardTop hardTop=new HardTop();
		hardTop.setCapacity(200);
		hardTop.setKeyCode(100);
		hardTop.setWheel(wheel2);
		SoftTop softTop=new SoftTop();
		softTop.setCapacity(500);
		softTop.setExpandable(true);
		softTop.setWheel(wheel3);
		
		save(wheel1);
		save(wheel2);
		save(wheel3);
		save(luggage);
		save(hardTop);
		save(softTop);

		Luggage deleteLuggage = (Luggage) getObjectAndLazyObject(Luggage.class, luggage.getId(),"wheel");
		HardTop deleteHardTop = (HardTop) getObjectAndLazyObject(HardTop.class, hardTop.getId(),"wheel");
		SoftTop deleteSoftTop = (SoftTop) getObjectAndLazyObject(SoftTop.class, softTop.getId(),"wheel");
		
		delete(deleteLuggage);
		delete(deleteHardTop);
		delete(deleteSoftTop);
		
		Luggage resultLuggage = (Luggage) getObjectAndLazyObject(Luggage.class, luggage.getId(),"wheel");
		HardTop resultHardTop = (HardTop) getObjectAndLazyObject(HardTop.class, hardTop.getId(),"wheel");
		SoftTop resultSoftTop = (SoftTop) getObjectAndLazyObject(SoftTop.class, softTop.getId(),"wheel");
	
		Assert.assertNull(resultLuggage);
		Assert.assertNull(resultHardTop);
		Assert.assertNull(resultSoftTop);
	}
}
