/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTop;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.HardTopType;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Luggage;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.SoftTop;
import gov.nih.nci.cacoresdk.domain.inheritance.parentwithassociation.sametable.Wheel;

import java.util.ArrayList;
import java.util.Collection;


public class ParentWithAssociationSametableWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Parent With Association Same Table WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(HardTop.class);
		mappedKlasses.add(HardTopType.class);
		mappedKlasses.add(Luggage.class);
		mappedKlasses.add(SoftTop.class);
		mappedKlasses.add(Wheel.class);
		
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
	public void testGetTotalNumberRecords() throws Exception
	{
		String targetClassName = Luggage.class.getName();
		Luggage criteria = new Luggage();

		Object result = getTotalNumberOfRecords(targetClassName, criteria);

		assertEquals(5,result);

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
		Class targetClass = Luggage.class;
		Luggage criteria = new Luggage();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		for (Object obj : results){
			Luggage result = (Luggage)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());	
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
		Class targetClass = HardTop.class;
		HardTop criteria = new HardTop();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			HardTop result = (HardTop)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getKeyCode());
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
		
		Class targetClass = SoftTop.class;
		SoftTop criteria = new SoftTop();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			SoftTop result = (SoftTop)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			assertNotNull(result.getExpandable());
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
	public void testEntireObjectNestedSearch4() throws Exception
	{
		Class targetClass = Wheel.class;
		Wheel criteria = new Wheel();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Wheel result = (Wheel)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getRadius());
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
		Class targetClass = Luggage.class;
		HardTop criteria = new HardTop();
		criteria.setCapacity(99);//No such row exists

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
		//Class targetClass = HardTop.class;
		Class targetClass = Luggage.class;
		Luggage criteria = new Luggage();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		HardTop result = (HardTop)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(1), result.getId());		
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
		Class targetClass = Luggage.class;
		HardTop criteria = new HardTop();
		criteria.setId(new Integer(2));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Luggage result = (Luggage)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(2), result.getId());			
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
//		Class targetClass = SoftTop.class;
		Class targetClass = Luggage.class;		
		Luggage criteria = new Luggage();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		SoftTop result = (SoftTop)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());		
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
		Class targetClass = Luggage.class;
		SoftTop criteria = new SoftTop();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Luggage result = (Luggage)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());		
	}
	
	public void testGetAssociation() throws Exception
	{
		Class targetClass = Luggage.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(5,results.length);
		
		Wheel wheel;
		for(Object obj : results)
		{
			Luggage result = (Luggage)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getCapacity());
			
			Object[] associatedResults = getAssociationResults(result, "wheel", 0);
			for (Object obj2 : associatedResults){
				wheel = (Wheel)obj2;
				assertNotNull(wheel);
				assertNotNull(wheel.getId());
				assertNotNull(wheel.getRadius());
			}
		}
	}
}
