package test.xml.data;

import gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;

public class ImplicitParentWithAssociationXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "Implicit Parent With Association XML Data Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestEntireObjectNestedSearch1() throws Exception
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Tank result = (Tank)i.next();
			toXML(result);
			
			validateClassElements(result);
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Tank result2 = (Tank)fromXML(result);
			
			assertNotNull(result2);
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
	public void xtestEntireObjectNestedSearch2() throws Exception
	{
		FishTank searchObject = new FishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FishTank result = (FishTank)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"numGallons",result.getNumGallons());
			validateAttribute(result,"shape",result.getShape());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			FishTank result2 = (FishTank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getNumGallons());
			assertNotNull(result2.getShape());

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
		FreshwaterFishTank searchObject = new FreshwaterFishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			FreshwaterFishTank result = (FreshwaterFishTank)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"filterModel",result.getFilterModel());
			validateAttribute(result,"numGallons",result.getNumGallons());
			validateAttribute(result,"shape",result.getShape());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			FreshwaterFishTank result2 = (FreshwaterFishTank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getFilterModel());
			assertNotNull(result2.getNumGallons());
			assertNotNull(result2.getShape());
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
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			SaltwaterFishTank result = (SaltwaterFishTank)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"proteinSkimmerModel",result.getProteinSkimmerModel());
			validateAttribute(result,"numGallons",result.getNumGallons());
			validateAttribute(result,"shape",result.getShape());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			SaltwaterFishTank result2 = (SaltwaterFishTank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getProteinSkimmerModel());
			assertNotNull(result2.getNumGallons());
			assertNotNull(result2.getShape());
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
	public void testEntireObjectNestedSearch5() throws Exception
	{
		Fish searchObject = new Fish();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Fish result = (Fish)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"genera",result.getGenera());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Fish result2 = (Fish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getGenera());
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
	public void testEntireObjectNestedSearch6() throws Exception
	{
		AngelFish searchObject = new AngelFish();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			AngelFish result = (AngelFish)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"genera",result.getGenera());
			validateAttribute(result,"finSize",result.getFinSize());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			AngelFish result2 = (AngelFish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getGenera());
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
	public void testEntireObjectNestedSearch7() throws Exception
	{
		DiscusFish searchObject = new DiscusFish();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			DiscusFish result = (DiscusFish)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"genera",result.getGenera());
			validateAttribute(result,"primaryColor",result.getPrimaryColor());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			DiscusFish result2 = (DiscusFish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getGenera());
			assertNotNull(result2.getPrimaryColor());
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
	public void testEntireObjectNestedSearch8() throws Exception
	{
		TankAccessory searchObject = new TankAccessory();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			TankAccessory result = (TankAccessory)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			TankAccessory result2 = (TankAccessory)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
	public void testEntireObjectNestedSearch9() throws Exception
	{
		Substrate searchObject = new Substrate();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Substrate result = (Substrate)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateAttribute(result,"id",result.getId());
			validateAttribute(result,"name",result.getName());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Substrate result2 = (Substrate)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
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
		AngelFish searchObject = new AngelFish();
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
		toXML(result);
		Fish result2 = (Fish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getGenera());
		assertNotNull(result2.getId());
		assertEquals(new Integer(3), result2.getId());
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
		Fish searchObject = new Fish();
	
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AngelFish result = (AngelFish)results.iterator().next();
		toXML(result);
		AngelFish result2 = (AngelFish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertEquals(new Integer(3), result2.getId());
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch3() throws Exception
	{
		Tank searchObject = new Tank();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
		toXML(result);
		FishTank result2 = (FishTank)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getNumGallons());
		assertNotNull(result2.getShape());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch4() throws Exception
	{
		FishTank searchObject = new FishTank();
	
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Tank result = (Tank)results.iterator().next();
		toXML(result);
		Tank result2 = (Tank)fromXML(result);

		assertNotNull(result2);
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch5() throws Exception
	{
		FishTank searchObject = new FishTank();
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FreshwaterFishTank result = (FreshwaterFishTank)results.iterator().next();
		toXML(result);
		FreshwaterFishTank result2 = (FreshwaterFishTank)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getNumGallons());
		assertNotNull(result2.getShape());
		assertNotNull(result2.getFilterModel());
		assertEquals(new Integer(1), result2.getId());
	}

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch6() throws Exception
	{
		FreshwaterFishTank searchObject = new FreshwaterFishTank();
	
		searchObject.setId(new Integer(2));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		FishTank result = (FishTank)results.iterator().next();
		toXML(result);
		FishTank result2 = (FishTank)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getNumGallons());
		assertNotNull(result2.getShape());
		assertEquals(new Integer(2), result2.getId());
	}
	

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch7() throws Exception
	{
		Fish searchObject = new Fish();
	
		searchObject.setId(new Integer(1));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Tank result = (Tank)results.iterator().next();
		toXML(result);
		Tank result2 = (Tank)fromXML(result);

		assertNotNull(result2);
	}
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void testAssociationNestedSearch10() throws Exception
	{
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
	
		searchObject.setId(new Integer(3));
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Substrate result = (Substrate)results.iterator().next();
		toXML(result);
		Substrate result2 = (Substrate)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		assertEquals(new Integer(1), result2.getId());
	}		
}
