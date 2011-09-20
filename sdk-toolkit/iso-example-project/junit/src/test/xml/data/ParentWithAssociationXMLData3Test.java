package test.xml.data;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Assistant;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssistantProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.TenuredProfessor;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;


public class ParentWithAssociationXMLData3Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Parent With Association XML Data Test Case";
	}

	public void testAssociationNestedSearchHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor");
		assertNotNull(results);
		assertEquals(1, results.size());

		Professor result = (Professor) results.iterator().next();
		toXML(result);
		Professor result2 = (Professor) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());   assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("2", result2.getId().getExtension());
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
		Professor searchObject = new Professor();
		Ii ii = new Ii();
		ii.setExtension("7");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.AssociateProfessor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AssociateProfessor result = (AssociateProfessor)results.iterator().next();
		toXML(result);
		AssociateProfessor result2 = (AssociateProfessor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("7", result2.getId().getExtension());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch4() throws Exception
	{
		AssociateProfessor searchObject = new AssociateProfessor();
		Ii ii = new Ii();
		ii.setExtension("7");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Professor result = (Professor)results.iterator().next();
		toXML(result);
		Professor result2 = (Professor)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("7", result2.getId().getExtension());
	}
	
	public void testAssociationNestedSearchHQL4() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor where id='7'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.Professor");

		assertNotNull(results);
		assertEquals(1, results.size());

		Professor result = (Professor) results.iterator().next();
		toXML(result);
		Professor result2 = (Professor) fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());   
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("7", result2.getId().getExtension());
	}	
}
