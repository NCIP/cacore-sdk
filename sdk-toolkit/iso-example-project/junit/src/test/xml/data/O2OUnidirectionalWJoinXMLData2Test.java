package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle;
import gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;


public class O2OUnidirectionalWJoinXMLData2Test extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Unidirectional With Join Test Case";
	}
	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verifies that the associated object has required Id
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch1() throws Exception
	{
		Bag searchObject = new Bag();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bag result = (Bag)i.next();
		toXML(result);
		Bag result2 = (Bag)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());   
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getStyle());
		
		Handle handle = result2.getHandle();
		assertNotNull(handle);
		
		assertNotNull(handle);
		assertNotNull(handle.getId());
		assertNotNull(handle.getColor());
		assertEquals("1",handle.getId().getExtension());
	}

	/**
	 * Uses Nested Search Criteria for search to get associated object
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that none of the attribute is null
	 * Verified the Id attribute's value of the returned object 
	 * 
	 * @throws Exception
	 */
	public void testOneAssociatedObjectNestedSearch2() throws Exception
	{
		Bag searchObject = new Bag();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Handle handle = (Handle)i.next();
		toXML(handle);
		Handle result2 = (Handle)fromXML(handle);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getColor());

		assertEquals("1",result2.getId().getExtension());
	}

	public void testOneAssociatedObjectNestedSearchHQL2() throws Exception {
		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle where id='1'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Handle");

		assertNotNull(results);
		assertEquals(1, results.size());

		Iterator i = results.iterator();

		Handle handle = (Handle) i.next();
		toXML(handle);
		Handle result2 = (Handle) fromXML(handle);

		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());  
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getColor());

		assertEquals("1", result2.getId().getExtension());
	}
	
	public void testGetAssociation() throws Exception
	{

		Bag searchObject = new Bag();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.unidirectional.withjoin.Bag",searchObject );

		assertNotNull(results);
		assertEquals(11,results.size());
		
		Handle handle;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bag result = (Bag)i.next();
			toXML(result);
			Bag result2 = (Bag)fromXML(result);
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());   
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getStyle());
			
			if (new Integer(result2.getId().getExtension()) < 11){
				validateAssociation(result,"Handle","handle", false);
				handle = result2.getHandle();
				assertNotNull(handle);
				assertNotNull(handle.getId());
				assertNotNull(handle.getColor());
			}
		}
	}	
}
