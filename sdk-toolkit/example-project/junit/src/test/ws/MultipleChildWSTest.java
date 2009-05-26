package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.GraduateStudent;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.Student;
import gov.nih.nci.cacoresdk.domain.inheritance.multiplechild.UndergraduateStudent;

import java.util.ArrayList;
import java.util.Collection;


public class MultipleChildWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Multiple Child WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(GraduateStudent.class);
		mappedKlasses.add(Student.class);
		mappedKlasses.add(UndergraduateStudent.class);
		
		return mappedKlasses;
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch1() throws Exception
	{
		Class targetClass = Student.class;
		Student criteria = new Student();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(10,results.length);
		
		for (Object obj : results){
			Student result = (Student)obj;
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
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Class targetClass = UndergraduateStudent.class;
		Student criteria = new Student();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			UndergraduateStudent result = (UndergraduateStudent)obj;
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
	 * @throws Exception
	 */
	public void testEntireObjectNestedSearch3() throws Exception
	{
		Class targetClass = GraduateStudent.class;
		GraduateStudent criteria = new GraduateStudent();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			GraduateStudent result = (GraduateStudent)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());	
		}		
	}
	

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the result set is empty
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociationNestedSearch() throws Exception
	{
		Class targetClass = Student.class;
		Student criteria = new Student();
		criteria.setName("Invalid Name");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(0,results.length);
	}


	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch1() throws Exception
	{
		Class targetClass = Student.class;
		UndergraduateStudent criteria = new UndergraduateStudent();
		criteria.setName("Student_Name2");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Student student = (Student)results[0];
		assertNotNull(student);
		assertNotNull(student.getId());
		assertNotNull(student.getName());		
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch2() throws Exception
	{
//		Class targetClass = GraduateStudent.class;
		Class targetClass = Student.class;
		Student criteria = new Student();
		criteria.setName("Student_Name6");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		GraduateStudent graduateStudent = (GraduateStudent)results[0];
		assertNotNull(graduateStudent);
		assertNotNull(graduateStudent.getId());
		assertNotNull(graduateStudent.getName());			
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch3() throws Exception
	{
		Class targetClass = Student.class;
		GraduateStudent criteria = new GraduateStudent();
		criteria.setName("Student_Name7");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Student student = (Student)results[0];
		assertNotNull(student);
		assertNotNull(student.getId());
		assertNotNull(student.getName());			
	}

}
