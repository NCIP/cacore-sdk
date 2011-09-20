package test.gov.nih.nci.cacoresdk.domain.other.differentpackage;

import gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie;
import gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class DifferentPackageWithAssociationTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Different Package With Association Test Case";
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
		Dessert searchObject = new Dessert();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Dessert result = (Dessert)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
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
		Pie searchObject = new Pie();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getFilling());
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
		Utensil searchObject = new Utensil();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Utensil result = (Utensil)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getName());
		}
	}

	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");

		assertNotNull(results);
		assertEquals(2, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Pie result = (Pie) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getFilling());
		}
	}
	
	public void testEntireObjectHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Utensil");
		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Utensil result = (Utensil) i.next();
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
		Pie searchObject = new Pie();
		St st=new St();
		st.setValue("Non-existent Filling");
		searchObject.setFilling(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

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
		Dessert searchObject = new Dessert();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());
	}

	public void testZeroAssociationHQL() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie where filling='Non-existent filling'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
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
		Pie searchObject = new Pie();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Dessert result = (Dessert)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("3",result.getId().getExtension());
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
		Dessert searchObject = new Dessert();
		Ii ii=new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Pie result = (Pie)results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertNotNull(result.getFilling());
		assertEquals("4", result.getId().getExtension());
	}

	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie where id='3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");

		assertNotNull(results);
		assertEquals(1, results.size());

		Dessert result = (Dessert) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());
	}

	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert where id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		assertNotNull(results);
		assertEquals(1, results.size());

		Pie result = (Pie) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("4", result.getId().getExtension());
	}

	public void testAssociationHQL3() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert as dessert "
						+ "left join fetch dessert.utensilCollection as utensil where utensil.name='Spoon'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.Dessert");
		assertNotNull(results);
		assertEquals(2, results.size());

		Dessert result = (Dessert) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("1", result.getId().getExtension());
	}

	public void testAssociationHQL4() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie as pie "
						+ "left join fetch pie.utensilCollection as utensil where utensil.name='Spoon'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie");
		assertNotNull(results);
		assertEquals(1, results.size());

		Pie result = (Pie) results.iterator().next();
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
		assertEquals("3", result.getId().getExtension());
	}

	public void testGetAssociation() throws ApplicationException
	{
		Pie searchObject = new Pie();
		Ii ii=new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.other.differentpackage.associations.Pie",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Utensil utensil;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pie result = (Pie)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getFilling());
			
			for(Iterator j = result.getUtensilCollection().iterator();j.hasNext();)
			{
				utensil = (Utensil)j.next();
				assertNotNull(utensil);
				assertNotNull(utensil.getId());
				assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
				assertNotNull(utensil.getName());
			}
		}
	}	
	
	public void testIsDifferentPackage1() throws ApplicationException
	{
		assertFalse(Dessert.class.getPackage().getName().equals(Pie.class.getPackage().getName()));
	}
	
	public void testIsDifferentPackage2() throws ApplicationException
	{
		assertFalse(Dessert.class.getPackage().getName().equals(Utensil.class.getPackage().getName()));
	}	
	
	public void testIsDifferentPackage3() throws ApplicationException
	{
		assertTrue(Pie.class.getPackage().getName().equals(Utensil.class.getPackage().getName()));
	}	
}
