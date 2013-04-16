/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent;

public class MultipleChildWritableApiTest extends SDKWritableBaseTest{
	private static Logger log = Logger.getLogger(MultipleChildWritableApiTest.class);
	public static String getTestCaseName() {
		return "Multiple Child Writable Api Test Case";
	}
	
	public void testSaveMultipleChildInheritance(){
		log.debug("\n--------testSaveMultipleChildInheritance()----------\n");
		Student student = new Student();
		student.setName("student");

		UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
		undergraduateStudent.setName("undergraduate");

		GraduateStudent graduateStudent = new GraduateStudent();
		graduateStudent.setName("graduateStudent");
		graduateStudent.setProjectName("project");

		save(student);
		save(undergraduateStudent);
		save(graduateStudent);
		
		Student resultStudent=(Student)getObject(Student.class, student.getId());
		UndergraduateStudent resultUnderGraduateStudent=(UndergraduateStudent)getObject(UndergraduateStudent.class, undergraduateStudent.getId());
		GraduateStudent resultGraduateStudent=(GraduateStudent)getObject(GraduateStudent.class, graduateStudent.getId());
		
		Assert.assertEquals(student.getName(), resultStudent.getName());
		Assert.assertEquals(undergraduateStudent.getName(), resultUnderGraduateStudent.getName());
		Assert.assertEquals(graduateStudent.getName(), resultGraduateStudent.getName());
	}
	
	public void testUpdateMultipleChildInheritance(){
		log.debug("\n--------testSaveMultipleChildInheritance()----------\n");
		Student student = new Student();
		student.setName("student");

		UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
		undergraduateStudent.setName("undergraduate");

		GraduateStudent graduateStudent = new GraduateStudent();
		graduateStudent.setName("graduateStudent");
		graduateStudent.setProjectName("project");

		save(student);
		save(undergraduateStudent);
		save(graduateStudent);

		Student updateStudent=(Student)getObject(Student.class, student.getId());
		UndergraduateStudent updateUnderGraduateStudent=(UndergraduateStudent)getObject(UndergraduateStudent.class, undergraduateStudent.getId());
		GraduateStudent updateGraduateStudent=(GraduateStudent)getObject(GraduateStudent.class, graduateStudent.getId());

		updateStudent.setName("updatestudent");
		updateUnderGraduateStudent.setName("updateUnderGraduateStudent");
		updateGraduateStudent.setName("updateUnderGraduateStudent");
		
		update(updateStudent);
		update(updateUnderGraduateStudent);
		update(updateGraduateStudent);
		
		Student resultStudent=(Student)getObject(Student.class, student.getId());
		UndergraduateStudent resultUnderGraduateStudent=(UndergraduateStudent)getObject(UndergraduateStudent.class, undergraduateStudent.getId());
		GraduateStudent resultGraduateStudent=(GraduateStudent)getObject(GraduateStudent.class, graduateStudent.getId());
		
		Assert.assertEquals(updateStudent.getName(), resultStudent.getName());
		Assert.assertEquals(updateUnderGraduateStudent.getName(), resultUnderGraduateStudent.getName());
		Assert.assertEquals(updateGraduateStudent.getName(), resultGraduateStudent.getName());
	}
	
	public void testDeleteMultipleChildInheritance(){
		log.debug("\n--------testSaveMultipleChildInheritance()----------\n");
		Student student = new Student();
		student.setName("student");

		UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
		undergraduateStudent.setName("undergraduate");

		GraduateStudent graduateStudent = new GraduateStudent();
		graduateStudent.setName("graduateStudent");
		graduateStudent.setProjectName("project");

		save(student);
		save(undergraduateStudent);
		save(graduateStudent);
		
		Student deleteStudent=(Student)getObject(Student.class, student.getId());
		UndergraduateStudent deleteUnderGraduateStudent=(UndergraduateStudent)getObject(UndergraduateStudent.class, undergraduateStudent.getId());
		GraduateStudent deleteGraduateStudent=(GraduateStudent)getObject(GraduateStudent.class, graduateStudent.getId());

		delete(deleteStudent);
		delete(deleteUnderGraduateStudent);
		delete(deleteGraduateStudent);
		
		Student resultStudent=(Student)getObject(Student.class, student.getId());
		UndergraduateStudent resultUnderGraduateStudent=(UndergraduateStudent)getObject(UndergraduateStudent.class, undergraduateStudent.getId());
		GraduateStudent resultGraduateStudent=(GraduateStudent)getObject(GraduateStudent.class, graduateStudent.getId());
		
		Assert.assertNull(resultStudent);
		Assert.assertNull(resultUnderGraduateStudent);
		Assert.assertNull(resultGraduateStudent);
	}
}
