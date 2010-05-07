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
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

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
	public void testEntireObjectNestedSearch1() throws Exception
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
	public void testEntireObjectNestedSearch2() throws Exception
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
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch3() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "filterModel", "value", result.getFilterModel().getValue());
			validateIso90210Element(result, "numGallons", "value", result.getNumGallons().getValue());
			validateIso90210Element(result, "shape", "value", result.getShape().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			FreshwaterFishTank result2 = (FreshwaterFishTank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch4() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "proteinSkimmerModel", "value", result.getProteinSkimmerModel().getValue());
			validateIso90210Element(result, "numGallons", "value", result.getNumGallons().getValue());
			validateIso90210Element(result, "shape", "value", result.getShape().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			SaltwaterFishTank result2 = (SaltwaterFishTank)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch5() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "genera", "value", result.getGenera().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Fish result2 = (Fish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch6() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "genera", "value", result.getGenera().getValue());	
			validateIso90210Element(result, "finSize", "value", result.getFinSize().getValue());				
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			AngelFish result2 = (AngelFish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch7() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "genera", "value", result.getGenera().getValue());	
			validateIso90210Element(result, "primaryColor", "value", result.getPrimaryColor().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			DiscusFish result2 = (DiscusFish)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch8() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());	
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			TankAccessory result2 = (TankAccessory)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());  
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void xtestEntireObjectNestedSearch9() throws Exception
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
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());				
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Substrate result2 = (Substrate)fromXML(result);
			
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
	public void xtestAssociationNestedSearch1() throws Exception
	{
		AngelFish searchObject = new AngelFish();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
		toXML(result);
		Fish result2 = (Fish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getGenera());
		assertEquals("3", result2.getId().getExtension());
	}
	
	public void xtestAssociationNestedSearchHQL1() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish where id='3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Fish result = (Fish)results.iterator().next();
		toXML(result);
		Fish result2 = (Fish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getGenera());
		assertEquals("3", result2.getId().getExtension());
	}	

	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch2() throws Exception
	{
		Fish searchObject = new Fish();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AngelFish result = (AngelFish)results.iterator().next();
		toXML(result);
		AngelFish result2 = (AngelFish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertEquals("3", result2.getId().getExtension());
	}
	
	public void xtestAssociationNestedSearchHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish where id='3'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish");

		assertNotNull(results);
		assertEquals(1,results.size());
		
		AngelFish result = (AngelFish)results.iterator().next();
		toXML(result);
		AngelFish result2 = (AngelFish)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertEquals("3", result2.getId().getExtension());
	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch10() throws Exception
	{
		SaltwaterFishTank searchObject = new SaltwaterFishTank();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate",searchObject );

		assertNotNull(results);
		assertEquals(2,results.size());
		
		Substrate result = (Substrate)results.iterator().next();
		toXML(result);
		Substrate result2 = (Substrate)fromXML(result);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		assertEquals("1", result2.getId().getExtension());
	}	
}
