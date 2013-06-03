/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.cacoresdk.domain.inheritance.onechild;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class OneChildTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "One Child Test Case";
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
		Mammal searchObject = new Mammal();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Mammal result = (Mammal)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getHairColor());
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
		Human searchObject = new Human();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Human result = (Human)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getDiet());
			assertNotNull(result.getHairColor());
		}
	}

	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human");
		assertNotNull(results);
		assertEquals(4, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Human result = (Human) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getDiet());
		}
	}
	
	public void testEntireObjectHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal");
		assertNotNull(results);
		assertEquals(5, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Mammal result = (Mammal) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getHairColor());
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
	public void testAssociationNestedSearch1() throws ApplicationException
	{
		Mammal searchObject = new Mammal();
		St st=new St();
		st.setValue("Hair_Color1");
		searchObject.setHairColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Human result = (Human)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getDiet());
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
		Human searchObject = new Human();
		St st=new St();
		st.setValue("Hair_Color1");
		searchObject.setHairColor(st);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Mammal result = (Mammal)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getHairColor());
		}
	}


	public void testAssociationHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human where hairColor='Hair_Color1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Mammal result = (Mammal) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getHairColor());
		}
	}
	
	public void testAssociationHQL2() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal where hairColor='Hair_Color1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human");
		assertNotNull(results);
		assertEquals(1, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Human result = (Human) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getHairColor());
		}
	}
}
