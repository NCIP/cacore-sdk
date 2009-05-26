package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.junit.Assert;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.Computer;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.HardDrive;

public class O2MBidirectionalWritableApiTest extends SDKWritableApiBaseTest {
	private static Logger log = Logger.getLogger(O2MBidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to Many Bidirectional WritableApi Test Case";
	}

	public void testSaveComputer(){
		log.debug("\n-----testSaveComputer()  method ----\n\n");
		Computer computer=new Computer();
		computer.setType("laptop");
		computer.setId(100);
		save(computer);
		
		Computer result=(Computer)getObject(Computer.class, computer.getId());
		Assert.assertEquals(computer.getType(), result.getType());
	}
	
	public void testSaveAndUpdateComputerWithOne2ManyHardDrives(){
		log.debug("-----testSaveAndUpdateComputerWithOne2ManyHardDrives()  method ----\n\n");
		Computer computer=new Computer();
		computer.setType("laptop");
		HardDrive hardDrive=new HardDrive();
		hardDrive.setSize(200);
		Collection<HardDrive> hardDrives = new HashSet<HardDrive>();
		hardDrives.add(hardDrive);
		computer.setHardDriveCollection(hardDrives);
		hardDrive.setComputer(computer);
		
		save(computer);
				
		Computer updateComputer=(Computer)getObjectAndLazyCollection(Computer.class, computer.getId(),"hardDriveCollection");
		updateComputer.setType("updatelaptop");
		Collection<HardDrive> hardDriveCollection=updateComputer.getHardDriveCollection();
		HardDrive updateHardDrive=hardDriveCollection.iterator().next();
		updateHardDrive.setSize(100);
		
		update(updateComputer);

		Computer result=(Computer)getObjectAndLazyCollection(Computer.class, computer.getId(),"hardDriveCollection");
		Assert.assertEquals(updateComputer.getType(), result.getType());
		Assert.assertEquals(updateComputer.getHardDriveCollection().iterator()
				.next().getSize(), result.getHardDriveCollection().iterator()
				.next().getSize());
	}
	
	public void testSaveHardDriveAndManytoOneComputerCascadeNone(){
		log.debug("\n-----testSaveHardDriveAndManytoOneComputerCascadeNone()  method ----\n\n");
		Computer computer=new Computer();
		computer.setType("laptop");
		HardDrive hardDrive=new HardDrive();
		hardDrive.setSize(200);
		hardDrive.setComputer(computer);
	
		save(computer);
		save(hardDrive);
		
		HardDrive result=(HardDrive)getObjectAndLazyObject(HardDrive.class, hardDrive.getId(),"computer");
		Assert.assertEquals(hardDrive.getSize(), result.getSize());
		Assert.assertEquals(hardDrive.getComputer().getType(), result.getComputer().getType());
	}
	
	public void testSaveHardDriveAndFKComputerNotNullCondition(){
		log.debug("\n-----testSaveHardDriveAndFKComputerNotNullCondition()  method ----\n\n");
		HardDrive hardDrive=new HardDrive();
		hardDrive.setComputer(null);
		hardDrive.setSize(200);
		try {
			save(hardDrive);
			Assert.fail("must through an exception for not null check condition");
		} catch (Exception e) {
			Assert.assertEquals("gov.nih.nci.system.applicationservice.ApplicationException: Error while querying DAO: ",e.getMessage());
		}
	}
	
	public void testDeleteMany2OneObjectHardDriveCascadeNone(){
		log.debug("\n-----testDeleteMany2OneObjectHardDriveCascadeNone() method ----\n\n");
		Computer computer=new Computer();
		computer.setType("laptop");
		HardDrive hardDrive=new HardDrive();
		hardDrive.setSize(200);
		hardDrive.setComputer(computer);
	
		save(computer);
		save(hardDrive);
		
		HardDrive deleteHardDrive=(HardDrive)getObject(HardDrive.class, hardDrive.getId());
		delete(deleteHardDrive);
		
		HardDrive resultHardDrive=(HardDrive)getObject(HardDrive.class, deleteHardDrive.getId());
		Computer many2OneComputer=(Computer)getObject(Computer.class, computer.getId());
		Assert.assertNull("harddrive should be deleted ",resultHardDrive);
		Assert.assertNotNull("cascade=none on many-one computer should not be deleted ",many2OneComputer);
	}
}
