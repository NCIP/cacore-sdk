package test.writable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.onetomany.selfassociation.Element;

public class O2MSelfAssociationWritableApiTest extends SDKWritableApiBaseTest{
	
	public static String getTestCaseName() {
		return "One to Many self association WritableApi Test Case";
	}
	
	public void testSaveObjectElement(){
		System.out.println("\n-------testSaveObjectElement()--------\n");
		Element element=new Element();
		element.setName("Element");
		
		save(element);
		
		Element result=(Element)getObject(Element.class, element.getId());
		Assert.assertEquals(element.getName(), result.getName());
	}
	
	public void testSaveObjectElementAndSelfAssociatedElements(){
		System.out.println("\n-------testSaveObjectElementAndSelfAssociatedElements()--------\n");
		Element element=new Element();
		element.setName("Element");
		Element selfElement1 =new Element();
		selfElement1.setName("selfElement");
		Element selfElement2 =new Element();
		selfElement2.setName("selfElement");
		Collection<Element> elements=new HashSet<Element>();
		elements.add(selfElement1);
		elements.add(selfElement2);
		element.setChildCollection(elements);
		
		save(element);

		Element result=(Element)getObjectAndLazyCollection(Element.class, element.getId(),"childCollection");
		Assert.assertEquals(element.getName(), result.getName());
		Iterator<Element> iterator=result.getChildCollection().iterator();
		Assert.assertEquals(selfElement1.getName(),iterator.next().getName());
		Assert.assertEquals(selfElement2.getName(), iterator.next().getName());
	}
	
	public void testDeleteObjectElementAndSelfAssociation(){
		System.out.println("\n-------testDeleteObjectElementAndSelfAssociation()--------\n");
		Element element=new Element();
		element.setName("Element");
		Element selfElement1 =new Element();
		selfElement1.setName("selfElement");
		Element selfElement2 =new Element();
		selfElement2.setName("selfElement");
		Collection<Element> elements=new HashSet<Element>();
		elements.add(selfElement1);
		elements.add(selfElement2);
		element.setChildCollection(elements);
		
		save(element);
		
		Element deleteElement=(Element)getObject(Element.class, element.getId());
		delete(deleteElement);
		
		Element resultElement=(Element)getObject(Element.class, element.getId());
		Element resultElement1=(Element)getObject(Element.class, selfElement1.getId());
		Element resultElement2=(Element)getObject(Element.class, selfElement2.getId());
		Assert.assertNull(resultElement);
		Assert.assertNull(resultElement1);
		Assert.assertNull(resultElement2);
	}
}
