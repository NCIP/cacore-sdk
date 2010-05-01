package test.gov.nih.nci.cacoresdk.domain.inheritance.abstrakt;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLAssociation;
import gov.nih.nci.system.query.cql.CQLAttribute;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLPredicate;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class AbstractParentWithAssociationTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Abstract Parent With Association Test Case";
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
		PrivateTeacher searchObject = new PrivateTeacher();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PrivateTeacher result = (PrivateTeacher)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
			assertNotNull(result.getName());
			assertNotNull(result.getYearsExperience());
			assertNull(result.getYearsExperience().getValue());
			assertEquals(result.getYearsExperience().getNullFlavor(),NullFlavor.MSK);
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
		Pupil searchObject = new Pupil();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pupil result = (Pupil)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}


	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PrivateTeacher result = (PrivateTeacher)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
			assertNotNull(result.getName());
			assertNotNull(result.getYearsExperience());
			assertEquals(result.getYearsExperience().getNullFlavor(),"MSK");
		}
	}

	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		assertNotNull(results);
		assertEquals(3, results.size());
		for (Iterator i = results.iterator(); i.hasNext();) {
			PrivateTeacher result = (PrivateTeacher) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
			assertNotNull(result.getName());
			assertNotNull(result.getYearsExperience());
			assertEquals(result.getYearsExperience().getNullFlavor(),"MSK");
		}
	}
	
	/**
	 * Uses CQL Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testEntireObjectCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pupil result = (Pupil)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");

		assertNotNull(results);
		assertEquals(4, results.size());
		for (Iterator i = results.iterator(); i.hasNext();) {
			Pupil result = (Pupil) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
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
		PrivateTeacher searchObject = new PrivateTeacher();
		St st=new St();
		st.setValue("Non-existent name");
		searchObject.setName(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(0,results.size());
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
		PrivateTeacher searchObject = new PrivateTeacher();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		PrivateTeacher result = (PrivateTeacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("1", result.getId().getExtension());
	}

	/**
	 * Uses CQL Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testZeroAssociationCQL() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Bad Name"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(0,results.size());
		
	}

	public void testZeroAssociationHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher where name.value='Bad name'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");

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
	public void testAssociationNestedSearch2() throws ApplicationException
	{
		PrivateTeacher searchObject = new PrivateTeacher();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Teacher result = (Teacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("1",result.getId().getExtension());
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
		Teacher searchObject = new PrivateTeacher();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		PrivateTeacher result = (PrivateTeacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("1",result.getId().getExtension());
	}

	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationCQL1() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Teacher result = (Teacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"TeacherRoot LocalConstant");
		assertEquals("1", result.getId().getExtension());
	}

	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");

		assertNotNull(results);
		assertEquals(1, results.size());
		Teacher result = (Teacher) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");		
		assertEquals("1", result.getId().getExtension());
	}

	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationCQL2() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher");
		association.setAttribute(new CQLAttribute("id", CQLPredicate.EQUAL_TO,"2"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		PrivateTeacher result = (PrivateTeacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("2", result.getId().getExtension());
	}

	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		assertNotNull(results);
		assertEquals(1, results.size());

		PrivateTeacher result = (PrivateTeacher) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("2", result.getId().getExtension());
	}
	
	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationCQL3() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");
		association.setTargetRoleName("pupilCollection");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Pupil_Name_1"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Teacher result = (Teacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("1", result.getId().getExtension());
	}
	
	public void testAssociationHQL3() throws ApplicationException {
		
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select teacher from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher teacher " +
				"left join fetch teacher.pupilCollection pupil where pupil.name='Pupil_Name_1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		assertNotNull(results);
		assertEquals(1, results.size());

		Teacher result = (Teacher) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"TeacherRoot LocalConstant");
		assertEquals("1", result.getId().getExtension());
	}
	
	/**
	 * Uses CQL Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws ApplicationException
	 */
	public void testAssociationCQL4() throws ApplicationException
	{
		CQLQuery cqlQuery = new CQLQuery();
		CQLObject target = new CQLObject();

		CQLAssociation association = new CQLAssociation();
		association.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Pupil");
		association.setTargetRoleName("pupilCollection");
		association.setAttribute(new CQLAttribute("name", CQLPredicate.EQUAL_TO,"Pupil_Name_3"));
		
		target.setName("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		target.setAssociation(association);
		cqlQuery.setTarget(target);
		
		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(1,results.size());
		
		PrivateTeacher result = (PrivateTeacher)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
		assertEquals("3", result.getId().getExtension());
	}

	public void testAssociationHQL4() throws ApplicationException {
		
		HQLCriteria hqlCriteria = new HQLCriteria(
				"select teacher from gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher teacher " +
				"left join fetch teacher.pupilCollection pupil where pupil.name='Pupil_Name_3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher");
		assertNotNull(results);
		assertEquals(1, results.size());

		Teacher result = (Teacher) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),"TeacherRoot LocalConstant");
		assertEquals("3", result.getId().getExtension());
	}
	
	public void testGetAssociation() throws ApplicationException
	{
		PrivateTeacher searchObject = new PrivateTeacher();
		Ii ii=new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pupil pupil;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			PrivateTeacher result = (PrivateTeacher)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),"PrivateTeacherRoot LocalConstant");
			assertNotNull(result.getYearsExperience());
			assertEquals(result.getYearsExperience().getNullFlavor(),"MSK");
			
			for(Iterator j = result.getPupilCollection().iterator();j.hasNext();)
			{
				pupil = (Pupil)j.next();
				assertNotNull(pupil);
				assertNotNull(pupil.getId());
				assertEquals(pupil.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
				assertNotNull(pupil.getName());
			}
		}
	}	
	
	public void testIsAbstract() throws ApplicationException
	{
		assertTrue(Modifier.isAbstract(Teacher.class.getModifiers()));
	}
}
