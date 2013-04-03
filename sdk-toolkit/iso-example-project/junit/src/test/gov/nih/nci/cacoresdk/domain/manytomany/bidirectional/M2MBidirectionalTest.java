package test.gov.nih.nci.cacoresdk.domain.manytomany.bidirectional;

import java.util.Collection;
import java.util.Iterator;

import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee;
import gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class M2MBidirectionalTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Many to Many Bidirectional Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch1() throws ApplicationException
	{
		Employee searchObject = new Employee();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Employee result = (Employee)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectNestedSearch2() throws ApplicationException
	{
		Project searchObject = new Project();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Project result = (Project)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * erifies that the associated object is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws ApplicationException
	{
		Employee searchObject = new Employee();
		Ii ii=new Ii();
		ii.setExtension("7");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Employee result = (Employee)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		
		Collection projectCollection = result.getProjectCollection();
		assertEquals(0,projectCollection.size());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set is 0
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws ApplicationException
	{
		Employee searchObject = new Employee();
		Ii ii=new Ii();
		ii.setExtension("7");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}		

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch1() throws ApplicationException
	{
		Employee searchObject = new Employee();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Employee result = (Employee)i.next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getName());
		
		Collection projectCollection = result.getProjectCollection();
		Iterator j = projectCollection.iterator();
		
		Project project = (Project)j.next();
		assertNotNull(project);
		assertNotNull(project.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(project.getName());
		assertEquals("1",project.getId().getExtension());
		
		Collection employeeCollection = project.getEmployeeCollection();
		assertEquals(1,employeeCollection.size());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch2() throws ApplicationException
	{
		Employee searchObject = new Employee();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Project project = (Project)i.next();
		assertNotNull(project);
		assertNotNull(project.getId());
		assertEquals(project.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(project.getName());
		assertEquals("1",project.getId().getExtension());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws ApplicationException
	 */
	public void testOneAssociatedObjectNestedSearch3() throws ApplicationException
	{
		Project searchObject = new Project();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Employee employee = (Employee)i.next();
		assertNotNull(employee);
		assertNotNull(employee.getId());
		assertEquals(employee.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(employee.getName());
		assertEquals("1",employee.getId().getExtension());
	}	
	
	public void testOneAssociatedObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select project from gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project project "
						+ "left join fetch project.employeeCollection employee where employee.id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project");
		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Project project = (Project) i.next();
			assertNotNull(project);
			assertNotNull(project.getId());
			assertNotNull(project.getName());
			assertEquals(true, new Integer(project.getId().getExtension()) > 1);
		}
	}

	
	public void testOneAssociatedObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select employee from gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee employee "
						+ "left join fetch employee.projectCollection project where project.id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project");
		assertNotNull(results);
		assertEquals(1, results.size());
		Iterator i = results.iterator();
		Employee employee = (Employee) i.next();
		assertNotNull(employee);
		assertNotNull(employee);
		assertNotNull(employee.getId());
		assertNotNull(employee.getName());
		assertEquals("4", employee.getId().getExtension());
	}

	public void testZeroAssociatedObjectHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select employee from gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee employee "
						+ "left join fetch employee.projectCollection project where project.id='7'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Project");

		assertNotNull(results);
		assertEquals(0, results.size());
	}
}
