package test.xml.data;

import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride;
import gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.util.Collection;
import java.util.Iterator;

import test.xml.mapping.SDKXMLDataTestBase;


public class O2OMultipleAssociationWJoinXMLDataTest extends SDKXMLDataTestBase
{
	public static String getTestCaseName()
	{
		return "One to One Multiple Association Test Case";
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
		InLaw searchObject = new InLaw();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.InLaw",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			InLaw result = (InLaw)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			InLaw result2 = (InLaw)fromXML(result);
			
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
	public void testEntireObjectNestedSearch2() throws Exception
	{
		Bride searchObject = new Bride();
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(4,results.size());
		
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bride result = (Bride)i.next();
			toXML(result);
			
			validateClassElements(result);
			validateIso90210Element(result, "id", "extension", result.getId().getExtension());
			validateIso90210Element(result, "name", "value", result.getName().getValue());
			
			assertTrue(validateXMLData(result, searchObject.getClass()));

			Bride result2 = (Bride)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
		}
	}

	
	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch1() throws Exception
	{
		Bride searchObject = new Bride();
		Ii ii = new Ii();
		ii.setExtension("2");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		toXML(result);
		Bride result2 = (Bride)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNotNull(result2.getFather());
		assertNull(result2.getMother());
	}

	/**
	 * Uses Nested Search Criteria for search
	 * Verifies that the results are returned 
	 * Verifies size of the result set
	 * Verifies that the associated object is null
	 * 
	 * @throws Exception
	 */
	public void testZeroAssociatedObjectsNestedSearch2() throws Exception
	{
		Bride searchObject = new Bride();
		Ii ii = new Ii();
		ii.setExtension("3");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		toXML(result);
		Bride result2 = (Bride)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNull(result2.getFather());
		assertNotNull(result2.getMother());
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
		Bride searchObject = new Bride();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		Iterator i = results.iterator();
		Bride result = (Bride)i.next();
		toXML(result);
		Bride result2 = (Bride)fromXML(result);
		
		assertNotNull(result2);
		assertNotNull(result2.getId());
		assertNotNull(result2.getName());
		
		assertNull(result2.getFather());
		assertNull(result2.getMother());
	}
	

	public void testGetAssociation() throws Exception
	{

		Bride searchObject = new Bride();
		Ii ii = new Ii();
		ii.setExtension("4");
		searchObject.setId(ii);// A Bride with both a Mother- and Father-in-Law
		Collection results = getApplicationService().search("gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride",searchObject );

		assertNotNull(results);
		assertEquals(1,results.size());
		
		InLaw fatherInLaw;
		InLaw motherInLaw;
		for(Iterator i = results.iterator();i.hasNext();)
		{
			Bride result = (Bride)i.next();
			toXML(result);
			Bride result2 = (Bride)fromXML(result);
			
			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());
			
			validateAssociation(result,"InLaw","father");			
			
			fatherInLaw = result2.getFather();
			assertNotNull(fatherInLaw);
			assertNotNull(fatherInLaw.getId());
			assertNotNull(fatherInLaw.getName());
			
			validateAssociation(result,"InLaw","mother");
			
			motherInLaw = result2.getMother();
			assertNotNull(motherInLaw);
			assertNotNull(motherInLaw.getId());
			assertNotNull(motherInLaw.getName());
		}
	}
	

	public void testGetAssociationHQL() throws Exception {

		HQLCriteria hqlCriteria = new HQLCriteria(
				"from gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride where id='4'");
		Collection results = search(hqlCriteria,
				"gov.nih.nci.cacoresdk.domain.onetoone.multipleassociation.withjoin.Bride");

		assertNotNull(results);
		assertEquals(1, results.size());

		InLaw fatherInLaw;
		InLaw motherInLaw;
		for (Iterator i = results.iterator(); i.hasNext();) {
			Bride result = (Bride) i.next();
			toXML(result);
			Bride result2 = (Bride) fromXML(result);

			assertNotNull(result2);
			assertNotNull(result2.getId());
			assertNotNull(result2.getName());

			validateAssociation(result, "InLaw", "father");

			fatherInLaw = result2.getFather();
			assertNotNull(fatherInLaw);
			assertNotNull(fatherInLaw.getId());
			assertNotNull(fatherInLaw.getName());

			validateAssociation(result, "InLaw", "mother");

			motherInLaw = result2.getMother();
			assertNotNull(motherInLaw);
			assertNotNull(motherInLaw.getId());
			assertNotNull(motherInLaw.getName());
		}
	}
}
