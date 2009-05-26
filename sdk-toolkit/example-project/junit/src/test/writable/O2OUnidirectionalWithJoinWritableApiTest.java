package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Address;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.Person;

public class O2OUnidirectionalWithJoinWritableApiTest extends SDKWritableApiBaseTest{
	private static Logger log = Logger.getLogger(O2OUnidirectionalWithJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One Unidirectional WithJoin WritableApi Test Case";
	}
	
	public void testSaveObjectPerson(){
		log.debug("\n--------------testSaveObjectPerson()------------\n");
		Person person=new Person();
		person.setName("person");
		
		save(person);
		
		Person result=(Person)getObject(Person.class, person.getId());
		Assert.assertEquals(person.getName(), result.getName());
	}
	
	public void testSaveObjectPersonWithOne2OneAssociation(){
		log.debug("\n--------------testSaveObjectPersonWithOne2OneAssociation()------------\n");
		Person person=new Person();
		person.setName("person");
		Address address=new Address();
		address.setZip("22030");
		person.setLivesAt(address);
		
		save(person);
		
		Person result=(Person)getObjectAndLazyObject(Person.class, person.getId(),"livesAt");
		Assert.assertEquals(person.getName(), result.getName());
		Assert.assertEquals(person.getLivesAt().getZip(), result.getLivesAt().getZip());
	}
	
	public void testUpdateObjectPersonWithOne2OneAssociation(){
		log.debug("\n--------------testSaveObjectPersonWithOne2OneAssociation()------------\n");
		Person person=new Person();
		person.setName("person");
		Address address=new Address();
		address.setZip("22030");
		person.setLivesAt(address);
		
		save(person);
		
		Person updatePerson=(Person)getObjectAndLazyObject(Person.class, person.getId(),"livesAt");
		updatePerson.setName("updatePerson");
		updatePerson.getLivesAt().setZip("22142");
		update(updatePerson);
		
		Person result=(Person)getObjectAndLazyObject(Person.class, person.getId(),"livesAt");
		Assert.assertEquals(updatePerson.getName(), result.getName());
		Assert.assertEquals(updatePerson.getLivesAt().getZip(), result.getLivesAt().getZip());
	}
	
	public void testDeleteObjectPersonWithOne2OneCascadeAll(){
		log.debug("\n--------------testSaveObjectPersonWithOne2OneAssociation()------------\n");
		Person person=new Person();
		person.setName("person");
		Address address=new Address();
		address.setZip("22030");
		person.setLivesAt(address);
		
		save(person);
		
		Person deletePerson=(Person)getObjectAndLazyObject(Person.class, person.getId(),"livesAt");
		delete(deletePerson);
		
		Person resultPerson=(Person)getObjectAndLazyObject(Person.class, person.getId(),"livesAt");
		Address resultAddress=(Address)getObject(Address.class, person.getLivesAt().getId());

		Assert.assertNull(resultPerson);
		Assert.assertNull(resultAddress);
	}
}
