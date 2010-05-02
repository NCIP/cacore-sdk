package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class AbstractParentWithAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Abstract Parent With Association XML Data Test Case";
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
		Teacher searchObject = new PrivateTeacher();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Teacher result = (Teacher)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Teacher result2 = (Teacher)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals("TeacherRoot LocalConstant",result2.getId().getRoot());
			assertNotNull(result2.getName());
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
		PrivateTeacher searchObject = new PrivateTeacher();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PrivateTeacher result = (PrivateTeacher)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			validateIso90210Element(result, "yearsExperience", "value", result.getYearsExperience().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			PrivateTeacher result2 = (PrivateTeacher)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals("PrivateTeacherRoot LocalConstant",result2.getId().getRoot());
			assertEquals(NullFlavor.MSK,result2.getYearsExperience().getNullFlavor());
			assertNotNull(result2.getName());
			assertNotNull(result2.getYearsExperience());

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
		Pupil searchObject = new Pupil();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pupil result = (Pupil)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());			
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Pupil result2 = (Pupil)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());			
		}
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
		Teacher searchObject = new PrivateTeacher();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		PrivateTeacher result = (PrivateTeacher)results.iterator().next();
		toXML(result);
		PrivateTeacher result2 = (PrivateTeacher)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals("PrivateTeacherRoot LocalConstant",result2.getId().getRoot());
		assertEquals(NullFlavor.MSK,result2.getYearsExperience().getNullFlavor());
		assertNotNull(result2.getName());
		assertNotNull(result2.getYearsExperience());
		assertEquals("1", result2.getId().getExtension());
	}

	public void testAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");

		assertNotNull(results);
		assertEquals(1, results.size());

		PrivateTeacher result = (PrivateTeacher) results.iterator().next();
		toXML(result);
		PrivateTeacher result2 = (PrivateTeacher) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals("PrivateTeacherRoot LocalConstant",result2.getId().getRoot());
		assertEquals(NullFlavor.MSK,result2.getYearsExperience().getNullFlavor());
		assertNotNull(result2.getName());
		assertNotNull(result2.getYearsExperience());
		assertEquals("1", result2.getId().getExtension());
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
		PrivateTeacher searchObject = new PrivateTeacher();
		Ii ii = new Ii();
		ii.setExtension("2");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Teacher result = (Teacher)results.iterator().next();
		toXML(result);
		Teacher result2 = (Teacher)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals("TeacherRoot LocalConstant",result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("2", result2.getId().getExtension());
	}
	
	public void testAssociationNestedSearchHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");

		assertNotNull(results);
		assertEquals(1, results.size());

		Teacher result = (Teacher) results.iterator().next();
		toXML(result);
		Teacher result2 = (Teacher) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals("TeacherRoot LocalConstant",result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("2", result2.getId().getExtension());
	}
}
