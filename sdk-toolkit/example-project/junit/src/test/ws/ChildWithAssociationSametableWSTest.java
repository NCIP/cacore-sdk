/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Designer;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.DesignerShoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.Shoes;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.sametable.SportsShoes;

import java.util.ArrayList;
import java.util.Collection;


public class ChildWithAssociationSametableWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Child With Association Same Table WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Designer.class);
		mappedKlasses.add(DesignerShoes.class);
		mappedKlasses.add(Shoes.class);
		mappedKlasses.add(SportsShoes.class);
		
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
		Class targetClass = Shoes.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);

		for(Object obj : results)
		{
			Shoes result = (Shoes)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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
		Class targetClass = SportsShoes.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);

		for(Object obj : results)
		{
			SportsShoes result = (SportsShoes)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
			assertNotNull(result.getSportsType());
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
		Class targetClass = DesignerShoes.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);

		for(Object obj : results)
		{
			DesignerShoes result = (DesignerShoes)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getColor());
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
		Class targetClass = Designer.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);

		for(Object obj : results)
		{
			Designer result = (Designer)obj;
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
		Class targetClass = Shoes.class;
		SportsShoes criteria = new SportsShoes();
		criteria.setColor("Purple");

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
		Class targetClass = SportsShoes.class;
		Shoes criteria = new Shoes();
		criteria.setColor("Red");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		SportsShoes result = (SportsShoes)results[0];
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
	public void testAssociationNestedSearch2() throws Exception
	{
		Class targetClass = Shoes.class;
		SportsShoes criteria = new SportsShoes();
		criteria.setColor("Red");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Shoes result = (Shoes)results[0];
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
		Class targetClass = DesignerShoes.class;
		Shoes criteria = new Shoes();
		criteria.setColor("Black");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		DesignerShoes result = (DesignerShoes)results[0];
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
		Class targetClass = Shoes.class;
		DesignerShoes criteria = new DesignerShoes();
		criteria.setColor("Black");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		DesignerShoes result = (DesignerShoes)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(3), result.getId());
	}
	
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testGetAssociation() throws Exception
	{
		Class targetClass = DesignerShoes.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Designer designer;
		for(Object obj : results)
		{
			DesignerShoes designerShoes = (DesignerShoes)obj;
			assertNotNull(designerShoes);
			assertNotNull(designerShoes.getId());
			assertNotNull(designerShoes.getColor());
			
			Object[] associatedResults = getAssociationResults(designerShoes, "designer", 0);
			for (Object obj2 : associatedResults){
				designer = (Designer)obj2;
				assertNotNull(designer);
				assertNotNull(designer.getId());
				assertNotNull(designer.getName());
			}
		}
	}	
}
