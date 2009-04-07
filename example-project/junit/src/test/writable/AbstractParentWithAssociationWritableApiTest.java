package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil;

public class AbstractParentWithAssociationWritableApiTest extends SDKWritableApiBaseTest {

	private static Logger log = Logger.getLogger(AbstractParentWithAssociationWritableApiTest.class);
	public static String getTestCaseName() {
		return "Abstract Parent With Association Writable Api Test Case";
	}
	
	public void testSaveObjectPrivateTeacher() {
		log.debug("\n---------testSaveObjectPrivateTeacher()---------\n\n");
		PrivateTeacher privateTeacher = new PrivateTeacher();
		privateTeacher.setName("privateTeacher");
		privateTeacher.setYearsExperience(20);

		save(privateTeacher);

		PrivateTeacher result = (PrivateTeacher) getObject(PrivateTeacher.class, privateTeacher.getId());
		Assert.assertEquals(privateTeacher.getName(), result.getName());
		Assert.assertEquals(privateTeacher.getYearsExperience(), result.getYearsExperience());
	}
	
	public void testSaveObjectPrivateTeacherWithOne2ManyAssociation() {
		log.debug("\n---------testSaveObjectPrivateTeacherWithOne2ManyAssociation()---------\n\n");
		PrivateTeacher privateTeacher = new PrivateTeacher();
		privateTeacher.setName("privateTeacher");
		privateTeacher.setYearsExperience(20);
		Collection<Pupil> pupils=new HashSet<Pupil>();
		Pupil pupil=new Pupil();
		pupil.setName("pupil");
		pupils.add(pupil);
		privateTeacher.setPupilCollection(pupils);
		
		save(pupil);//cascade=none,inverse=false
		save(privateTeacher);

		PrivateTeacher result = (PrivateTeacher) getObjectAndLazyCollection(PrivateTeacher.class, privateTeacher.getId(),"pupilCollection");
		Assert.assertEquals(privateTeacher.getName(), result.getName());
		Assert.assertEquals(privateTeacher.getYearsExperience(), result.getYearsExperience());
		Assert.assertEquals(pupil.getName(), result.getPupilCollection().iterator().next().getName());
	}
	
	public void testUpdateObjectPrivateTeacherWithOne2ManyAssociation() {
		log.debug("\n---------testUpdateObjectPrivateTeacherWithOne2ManyAssociation()---------\n\n");
		PrivateTeacher privateTeacher = new PrivateTeacher();
		privateTeacher.setName("privateTeacher");
		privateTeacher.setYearsExperience(20);
		Collection<Pupil> pupils=new HashSet<Pupil>();
		Pupil pupil=new Pupil();
		pupil.setName("pupil");
		pupils.add(pupil);
		privateTeacher.setPupilCollection(pupils);
		
		save(pupil);//cascade=none,inverse=false
		save(privateTeacher);

		PrivateTeacher updatePrivateTeacher = (PrivateTeacher) getObjectAndLazyCollection(PrivateTeacher.class, privateTeacher.getId(),"pupilCollection");
		updatePrivateTeacher.setName("updatePrivateTeacher");
		Pupil updatePupil=updatePrivateTeacher.getPupilCollection().iterator().next();
		updatePupil.setName("updatePupil");
		
		update(updatePupil);
		update(updatePrivateTeacher);
		
		PrivateTeacher result = (PrivateTeacher) getObjectAndLazyCollection(PrivateTeacher.class, updatePrivateTeacher.getId(),"pupilCollection");
		Assert.assertEquals(updatePrivateTeacher.getName(), result.getName());
		Assert.assertEquals(updatePrivateTeacher.getYearsExperience(), result.getYearsExperience());
		Assert.assertEquals("updatePupil", result.getPupilCollection().iterator().next().getName());
	}
	
	public void testDeleteObjectPrivateTeacher() {
		log.debug("\n---------testDeleteObjectPrivateTeacher()---------\n\n");
		PrivateTeacher privateTeacher = new PrivateTeacher();
		privateTeacher.setName("privateTeacher");
		privateTeacher.setYearsExperience(20);
		
		save(privateTeacher);
				
		PrivateTeacher deletePrivateTeacher = (PrivateTeacher) getObject(PrivateTeacher.class, privateTeacher.getId());
		delete(deletePrivateTeacher);

		PrivateTeacher result = (PrivateTeacher) getObject(PrivateTeacher.class, privateTeacher.getId());
		Assert.assertNull(result);
	}
}
