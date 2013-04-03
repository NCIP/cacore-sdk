package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;


public class AbstractParentWithAssociationXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Abstract Parent With Association XML Data Test Case 2";
	}
	
	@Before public void cleanUp(){
		try {
			super.tearDown();
		} catch (Exception e){
			System.out.println("Exception encountered cleaning up between test methods: " + e.getMessage());
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
		if("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher".equals(result2.getClass().getName())){
			assertEquals("TeacherRoot LocalConstant",result2.getId().getRoot());
		}
		if("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher".equals(result2.getClass().getName())){
			assertEquals("PrivateTeacherRoot LocalConstant",result2.getId().getRoot());
		}
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
		assertEquals("PrivateTeacherRoot LocalConstant",result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("2", result2.getId().getExtension());
	}
}
