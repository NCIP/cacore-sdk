package test.gov.nih.nci.cacoresdk.domain.interfaze;

import gov.nih.nci.cacoresdk.domain.interfaze.Carnivora;
import gov.nih.nci.cacoresdk.domain.interfaze.Digitigrade;
import gov.nih.nci.cacoresdk.domain.interfaze.Dog;
import gov.nih.nci.cacoresdk.domain.interfaze.Pet;
import gov.nih.nci.cacoresdk.domain.interfaze.differentpackage.Canidae;
import gov.nih.nci.cacoresdk.domain.interfaze.differentpackage.Mammalia;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLObject;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.sun.xml.bind.CycleRecoverable;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

public class InterfaceTest extends SDKISOTestBase
{
	public static String getTestCaseName()
	{
		return "Interface Test Case";
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
		Dog searchObject = new Dog();
		
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.interfaze.Dog",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Dog result = (Dog)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getBreed());
			assertNotNull(result.getGender());
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
		
		target.setName("gov.nih.nci.cacoresdk.domain.interfaze.Dog");
		cqlQuery.setTarget(target);

		Collection results = getApplicationService().query(cqlQuery);

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Dog result = (Dog)i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getBreed());
			assertNotNull(result.getGender());
		}
	}
	
	public void testEntireObjectHQL1() throws ApplicationException {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.interfaze.Dog");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.interfaze.Dog");
		assertNotNull(results);
		assertEquals(3, results.size());

		for (Iterator i = results.iterator(); i.hasNext();) {
			Dog result = (Dog) i.next();
			assertNotNull(result);
			assertNotNull(result.getId());
			assertEquals(result.getId().getRoot(),II_ROOT_GLOBAL_CONSTANT_VALUE);
			assertNotNull(result.getBreed());
			assertNotNull(result.getGender());
		}
	}
	
	public void testExtendsInterface1() throws ApplicationException
	{
		assertEquals(Carnivora.class.asSubclass(Mammalia.class),Carnivora.class);
	}
	
	public void testExtendsInterface2() throws ApplicationException
	{
		assertEquals(Canidae.class.asSubclass(Carnivora.class),Canidae.class);
	}
	
	public void testExtendsInterface3() throws ApplicationException
	{
		assertEquals(Canidae.class.asSubclass(Digitigrade.class),Canidae.class);
	}
	
	public void testImplementsInterface1() throws ApplicationException
	{
		List<Class> implementedInterfaces = new ArrayList<Class>();
		implementedInterfaces.add(Serializable.class);
		implementedInterfaces.add(Canidae.class);
		implementedInterfaces.add(Pet.class);
		implementedInterfaces.add(CycleRecoverable.class);
		
		for (Class interfaceKlass : Dog.class.getInterfaces()){
			Boolean result = implementedInterfaces.contains(interfaceKlass);
			assertTrue(result);
		}
	}
	
	public void testIsInterface1() throws ApplicationException
	{
		assertTrue(Modifier.isInterface(Canidae.class.getModifiers()));
		assertTrue(Canidae.class.isInterface());
	}
	
	public void testIsInterface2() throws ApplicationException
	{
		assertTrue(Modifier.isInterface(Digitigrade.class.getModifiers()));
		assertTrue(Digitigrade.class.isInterface());
	}
		
	public void testIsInterface3() throws ApplicationException
	{
		assertTrue(Modifier.isInterface(Carnivora.class.getModifiers()));
		assertTrue(Carnivora.class.isInterface());
	}
	
	public void testIsInterface4() throws ApplicationException
	{
		assertTrue(Modifier.isInterface(Mammalia.class.getModifiers()));
		assertTrue(Mammalia.class.isInterface());
	}
	
	public void testIsInterface5() throws ApplicationException
	{
		assertTrue(Modifier.isInterface(Pet.class.getModifiers()));
		assertTrue(Pet.class.isInterface());
	}
	
	public void testInstanceof() throws ApplicationException
	{
		Dog dog = new Dog();
		assertTrue(dog instanceof Pet);
		assertTrue(dog instanceof Canidae);
		assertTrue(dog instanceof Carnivora);
		assertTrue(dog instanceof Digitigrade);
		assertTrue(dog instanceof Mammalia);
	}
}
