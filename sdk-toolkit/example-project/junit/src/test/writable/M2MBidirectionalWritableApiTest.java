/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee;
import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project;

public class M2MBidirectionalWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(M2MBidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "Many to Many Bidirectional WritableApi Test Case";
	}
	
	public void testSaveObjectEmployee(){
		log.debug("\n------saveObjectEmployee() --------\n\n");
		Employee employee=new Employee();
		employee.setName("Employee");
		save(employee);

		Employee result = (Employee) getObject(Employee.class, employee.getId());
		Assert.assertEquals(employee.getName(), result.getName());
	}
	
	public void testSaveObjectEmployeeWithM2MColl() {
		log.debug("\n------testSaveObjectEmployeeWithM2MColl() --------\n\n");
		Employee employee = new Employee();
		employee.setName("Employee");
		Collection<Project> projects = new HashSet<Project>();
		Project project = new Project();
		project.setName("project");
		projects.add(project);
		employee.setProjectCollection(projects);
		
		save(project);
		save(employee);

		Employee result = (Employee) getObjectAndLazyCollection(Employee.class,employee.getId(),"projectCollection");
		Assert.assertEquals(employee.getName(), result.getName());
		Assert.assertEquals(1, result.getProjectCollection().size());
	}
	
	public void testUpdateObjectEmployeeAndUpdateDeleteM2MColl() {
		log.debug("\n------testUpdateObjectEmployeeAndUpdateDeleteM2MColl() --------\n\n");
		Employee employee = new Employee();
		employee.setName("Employee");
		Collection<Project> projects = new HashSet<Project>();
		Project project = new Project();
		project.setName("project");
		projects.add(project);
		employee.setProjectCollection(projects);
		
		save(project);
		save(employee);

		Employee updateEmployee = (Employee) getObjectAndLazyCollection(Employee.class,employee.getId(),"projectCollection");
		updateEmployee.setName("updatedEmployee");
		Project  updateProject=updateEmployee.getProjectCollection().iterator().next();
		updateProject.setName("updateProject");
		
		updateEmployee.getProjectCollection().remove(updateProject);
		
		update(updateProject);
		update(updateEmployee);

		Employee resultEmployee = (Employee) getObjectAndLazyCollection(Employee.class,updateEmployee.getId(),"projectCollection");
		Project resultProject=(Project) getObject(Project.class,updateProject.getId());

		Assert.assertEquals(updateEmployee.getName(), resultEmployee.getName());
		Assert.assertEquals(1, resultEmployee.getProjectCollection().size());
		Assert.assertEquals(updateProject.getName(), resultProject.getName());
	}

	public void testDeleteObjectEmployeeWithM2MColl() {
		log.debug("\n------testDeleteObjectEmployeeWithM2MColl() --------\n\n");
		Employee employee = new Employee();
		employee.setName("Employee");
		Collection<Project> projects = new HashSet<Project>();
		Project project = new Project();
		project.setName("project");
		projects.add(project);
		employee.setProjectCollection(projects);
		
		save(project);
		save(employee);

		Employee result = (Employee) getObjectAndLazyCollection(Employee.class,employee.getId(),"projectCollection");
		Assert.assertEquals(employee.getName(), result.getName());
		Assert.assertEquals(1, result.getProjectCollection().size());
		
		employee.getProjectCollection().removeAll(projects);
		delete(employee);
		
		Project resultProject=(Project)getObject(Project.class, project.getId());
		Assert.assertNotNull("project should not be deleted as employee is deleted",resultProject);
	}
}
