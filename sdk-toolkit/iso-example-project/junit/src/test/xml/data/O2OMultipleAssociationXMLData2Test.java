package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Parent;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;



public class O2OMultipleAssociationXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association Test Case";
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch3() throws Exception
	{
		Child searchObject = new Child();
		Ii ii = new Ii();
		ii.setExtension("5");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Child result = (Child)i.next();
		toXML(result);
		Child result2 = (Child)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getName());
		
		assertNull(result2.getFather());
		assertNull(result2.getMother());
	}
	
	public void testGetAssociation() throws Exception
	{

		Child searchObject = new Child();
		Ii ii = new Ii();
		ii.setExtension("2");
		searchObject.setId(ii);// A Child with both a Mother and Father
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Parent father;
		Parent mother;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Child result = (Child)i.next();
			toXML(result);
			Child result2 = (Child)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());
			
			validateAssociation(result,"Parent","father", false);
			
			father = result2.getFather();
			assertNotNull(father);
			assertNotNull(father.getId());
			assertNotNull(father.getName());
			
			validateAssociation(result,"Parent","mother", false);
			
			mother = result2.getMother();
			assertNotNull(mother);
			assertNotNull(mother.getId());
			assertNotNull(mother.getName());
		}
	}
	
	public void testGetAssociationHQL() throws Exception {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child where id='2'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.Child");
		assertNotNull(results);
		assertEquals(1, results.size());

		Parent father;
		Parent mother;
		for (Iterator i = results.iterator(); i.hasNext();) {
			Child result = (Child) i.next();
			toXML(result);
			Child result2 = (Child) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getName());

			validateAssociation(result, "Parent", "father", false);

			father = result2.getFather();
			assertNotNull(father);
			assertNotNull(father.getId());
			assertNotNull(father.getName());

			validateAssociation(result, "Parent", "mother", false);

			mother = result2.getMother();
			assertNotNull(mother);
			assertNotNull(mother.getId());
			assertNotNull(mother.getName());
		}
	}
}
