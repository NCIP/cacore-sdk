package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal;

import java.util.Collection;
import java.util.Iterator;

import test.xml.data.SDKXMLDataTestBase;

public class OneChildXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One Child XML Data Test Case";
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
		Mammal searchObject = new Mammal();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal",searchObject );

		assertNotNull(results);
		assertEquals(5,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Mammal result = (Mammal)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"hairColor",result.getHairColor());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Mammal result2 = (Mammal)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHairColor());
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
		Human searchObject = new Human();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Human result = (Human)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"diet",result.getDiet());
			validateAttribute(result,"hairColor",result.getHairColor());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			Human result2 = (Human)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getDiet());
			assertNotNull(result2.getHairColor());
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
		Mammal searchObject = new Mammal();
		searchObject.setHairColor("Hair_Color1");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Human",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Human result = (Human)i.next();
			toXML(result);
			Human result2 = (Human)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getDiet());
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
		Human searchObject = new Human();
		searchObject.setHairColor("Hair_Color1");
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.onechild.Mammal",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Mammal result = (Mammal)i.next();
			toXML(result);
			Mammal result2 = (Mammal)fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getHairColor());
		}
	}
}
