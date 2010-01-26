package test.ws;


import gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Fish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory;

import java.util.ArrayList;
import java.util.Collection;


public class ImplicitParentWithAssociationWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "Implicit Parent With Association WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(AngelFish.class);
		mappedKlasses.add(DiscusFish.class);
		mappedKlasses.add(Fish.class);
		mappedKlasses.add(FishTank.class);
		mappedKlasses.add(FreshwaterFishTank.class);
		mappedKlasses.add(SaltwaterFishTank.class);
		mappedKlasses.add(Substrate.class);
		mappedKlasses.add(Tank.class);
		mappedKlasses.add(TankAccessory.class);
		
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
		Class targetClass = AngelFish.class;
		AngelFish criteria = new AngelFish();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			AngelFish result = (AngelFish)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getFinSize());	
			assertNotNull(result.getGenera());	
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
		Class targetClass = DiscusFish.class;
		DiscusFish criteria = new DiscusFish();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			DiscusFish result = (DiscusFish)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
			assertNotNull(result.getPrimaryColor());
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

		Class targetClass = Fish.class;
		Fish criteria = new Fish();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Fish result = (Fish)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getGenera());
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

		Class targetClass = FishTank.class;
		FishTank criteria = new FishTank();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			FishTank result = (FishTank)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getShape());
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

		Class targetClass = FreshwaterFishTank.class;
		FreshwaterFishTank criteria = new FreshwaterFishTank();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			FreshwaterFishTank result = (FreshwaterFishTank)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getShape());
			assertNotNull(result.getFilterModel());
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

		Class targetClass = SaltwaterFishTank.class;
		SaltwaterFishTank criteria = new SaltwaterFishTank();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		for (Object obj : results){
			SaltwaterFishTank result = (SaltwaterFishTank)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getNumGallons());
			assertNotNull(result.getShape());
			assertNotNull(result.getProteinSkimmerModel());
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

		Class targetClass = Substrate.class;
		Substrate criteria = new Substrate();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Substrate result = (Substrate)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
			assertNotNull(result.getName());
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

		Class targetClass = Tank.class;
		Tank criteria = new Tank();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			Tank result = (Tank)obj;
			assertNotNull(result);
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

		Class targetClass = TankAccessory.class;
		TankAccessory criteria = new TankAccessory();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		for (Object obj : results){
			TankAccessory result = (TankAccessory)obj;
			assertNotNull(result);
			assertNotNull(result.getId());
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
		Class targetClass = Tank.class;
		FishTank criteria = new FishTank();
		criteria.setId(10);//No such row exists

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
		Class targetClass = AngelFish.class;
		Fish criteria = new Fish();
		criteria.setId(new Integer(3));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		AngelFish result = (AngelFish)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getFinSize());
		assertNotNull(result.getGenera());
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
	public void testAssociationNestedSearch2() throws Exception
	{
		Class targetClass = Fish.class;
		AngelFish criteria = new AngelFish();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Fish result = (Fish)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(new Integer(4), result.getId());		
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
		Class targetClass = Tank.class;
		FishTank criteria = new FishTank();
		criteria.setId(new Integer(2));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Tank result = (Tank)results[0];
		assertNotNull(result);
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
		Class targetClass = FishTank.class;
		Tank criteria = new Tank();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		FishTank result = (FishTank)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());	
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
		Class targetClass = FishTank.class;
		SaltwaterFishTank criteria = new SaltwaterFishTank();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		FishTank result = (FishTank)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
		assertEquals(new Integer(4), result.getId());		
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
		Class targetClass = SaltwaterFishTank.class;
		FishTank criteria = new FishTank();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		SaltwaterFishTank result = (SaltwaterFishTank)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getNumGallons());
		assertNotNull(result.getShape());
		assertNotNull(result.getProteinSkimmerModel());
		assertEquals(new Integer(4), result.getId());			
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
		Class targetClass = Tank.class;
		Fish criteria = new Fish();
		criteria.setId(new Integer(1));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Tank result = (Tank)results[0];
		assertNotNull(result);	
	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch9() throws Exception
	{
		Class targetClass = Tank.class;
		TankAccessory criteria = new TankAccessory();
		criteria.setId(new Integer(1));

		Object[] results;
		boolean flag = false;
		try {
			results = getQueryObjectResults(targetClass, criteria);
		} catch (Exception e) {//outer join fetches not currently supported by hibernate for implicit polymorphic queries
			flag = true;
		}

		assertTrue(flag);
	
	}	
	
	/**
	 * Uses Nested Search Criteria for inheritance as association in search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * 
	 * @throws Exception
	 */
	public void xtestAssociationNestedSearch11() throws Exception
	{
		Class targetClass = Substrate.class;
		SaltwaterFishTank criteria = new SaltwaterFishTank();
		criteria.setId(new Integer(4));

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Substrate result = (Substrate)results[0];
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
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
	public void xtestGetAssociation1() throws Exception
	{
		Class targetClass = Tank.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		Fish fish;
		for(Object obj : results)
		{
			Tank tank = (Tank)obj;
			assertNotNull(tank);
			
			Object[] associatedResults = getAssociationResults(tank, "fishCollection", 0);
			for (Object obj2 : associatedResults){
				fish = (Fish)obj2;
				assertNotNull(fish);
				assertNotNull(fish.getId());
				assertNotNull(fish.getGenera());
			}
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
	public void testGetAssociation2() throws Exception
	{
		Class targetClass = Fish.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		Tank tank;
		for(Object obj : results)
		{
			Fish fish = (Fish)obj;
			assertNotNull(fish);
			assertNotNull(fish.getId());
			
			Object[] associatedResults = getAssociationResults(fish, "tank", 0);
			for (Object obj2 : associatedResults){
				tank = (Tank)obj2;
				assertNotNull(tank);
			}
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
	public void xtestGetAssociation3() throws Exception
	{
		Class targetClass = Tank.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		TankAccessory tankAccessory;
		for(Object obj : results)
		{
			Tank tank = (Tank)obj;
			assertNotNull(tank);
			
			Object[] associatedResults = getAssociationResults(tank, "tankAccessoryCollection", 0);
			for (Object obj2 : associatedResults){
				tankAccessory = (TankAccessory)obj2;
				assertNotNull(tankAccessory);
				assertNotNull(tankAccessory.getId());
				assertNotNull(tankAccessory.getName());
			}
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
	public void testGetAssociation4() throws Exception
	{
		Class targetClass = TankAccessory.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(4,results.length);
		
		Tank tank;
		boolean flag = false;
		for(Object obj : results)
		{
			TankAccessory tankAccessory = (TankAccessory)obj;
			assertNotNull(tankAccessory);
			assertNotNull(tankAccessory.getId());
			assertNotNull(tankAccessory.getName());
			
			flag = false;
			try {
				Object[] associatedResults = getAssociationResults(tankAccessory, "tankCollection", 0);
			} catch (Exception e) {//outer join fetches not currently supported by hibernate for implicit polymorphic queries
				flag = true;
			}

			assertTrue(flag);
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
	public void testGetAssociation5() throws Exception
	{
		Class targetClass = SaltwaterFishTank.class;
		Object criteria = targetClass.newInstance();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(2,results.length);
		
		Substrate substrate;
		for(Object obj : results)
		{
			SaltwaterFishTank tank = (SaltwaterFishTank)obj;
			assertNotNull(tank);
			assertNotNull(tank.getId());
			assertNotNull(tank.getNumGallons());
			assertNotNull(tank.getProteinSkimmerModel());
			assertNotNull(tank.getShape());
			
			Object[] associatedResults = getAssociationResults(tank, "substrateCollection", 0);
			for (Object obj2 : associatedResults){
				substrate = (Substrate)obj2;
				assertNotNull(substrate);
				assertNotNull(substrate.getId());
				assertNotNull(substrate.getName());
			}
		}
	}	
}
