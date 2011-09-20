package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;


public class O2OBidirectionalWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Bidirectional With Join XML Data Test Case";
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
		Pendant searchObject = new Pendant();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Pendant result = (Pendant)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "shape", "value", result.getShape().getValue());

			assertTrue(validateXMLData(result, searchObject.getClass()));

			Pendant result2 = (Pendant)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension()); 
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
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
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Chain searchObject = new Chain();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(3,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Chain result = (Chain)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "metal", "value", result.getMetal().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Chain result2 = (Chain)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId().getExtension());
			assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
			assertNotNull(result2.getMetal());
		}
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
		Pendant searchObject = new Pendant();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Pendant result = (Pendant)i.next();
		toXML(result);
		Pendant result2 = (Pendant)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension());
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getShape());
		
		Chain chain = result2.getChain();
		assertNotNull(chain);
		
		assertNotNull(chain.getId());
		assertNotNull(chain.getMetal());
		assertEquals("1",chain.getId().getExtension());
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
		Pendant searchObject = new Pendant();
		Ii ii = new Ii();
		ii.setExtension("1");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		
		Chain result = (Chain)i.next();
		toXML(result);
		Chain result2 = (Chain)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId().getExtension()); 
		assertEquals(II_ROOT_GLOBAL_CONSTANT_VALUE,result2.getId().getRoot());
		assertNotNull(result2.getMetal());
		assertEquals("1",result2.getId().getExtension());
	}
}
