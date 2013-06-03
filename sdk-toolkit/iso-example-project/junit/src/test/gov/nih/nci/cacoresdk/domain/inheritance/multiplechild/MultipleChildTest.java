/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.multiplechild;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class MultipleChildTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Multiple Child Test Case";
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
		Student searchObject = new Student();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student",searchObject );

		assertNotNull(results);
		assertEquals(10,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Student result = (Student)i.next();
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
		UndergraduateStudent searchObject = new UndergraduateStudent();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			UndergraduateStudent result = (UndergraduateStudent)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
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
	public void testEntireObjectNestedSearch3() throws ApplicationException
	{
		GraduateStudent searchObject = new GraduateStudent();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraduateStudent result = (GraduateStudent)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertNotNull(result.getProjectName());
		}
	}
	
	public void testEntireObjectHQL1() throws ApplicationException {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent");

		assertNotNull(results);
		assertEquals(5, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			UndergraduateStudent result = (UndergraduateStudent) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
	
	/**
	 * Uses HQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student");

		assertNotNull(results);
		assertEquals(10, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Student result = (Student) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testEntireObjectHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent");

		assertNotNull(results);
		assertEquals(5, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			GraduateStudent result = (GraduateStudent) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
			assertNotNull(result.getProjectName());
		}
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociationNestedSearch() throws ApplicationException
	{
		UndergraduateStudent searchObject = new UndergraduateStudent();
		St st=new St();
		st.setValue("Student_Name8");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
	}

	public void testZeroAssociationHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent where name='Student_Name9'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent");

		assertNotNull(results);
		assertEquals(0, results.size());

	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		UndergraduateStudent searchObject = new UndergraduateStudent();
		St st=new St();
		st.setValue("Student_Name2");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Student result = (Student)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		Student searchObject = new Student();
		St st=new St();
		st.setValue("Student_Name6");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			GraduateStudent result = (GraduateStudent)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationNestedSearch3() throws ApplicationException
	{
		GraduateStudent searchObject = new GraduateStudent();
		St st=new St();
		st.setValue("Student_Name7");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Student result = (Student)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
	
	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student where name='Student_Name3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Student result = (Student) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}	

	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent where name='Student_Name3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			UndergraduateStudent result = (UndergraduateStudent) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}	
	
	public void testAssociationHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student where name='Student_Name8'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student");

		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Student result = (Student) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
	
	public void testAssociationHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent where name='Student_Name8'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			GraduateStudent result = (GraduateStudent) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}
}
