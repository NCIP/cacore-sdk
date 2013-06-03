/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.ws;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note;

import java.util.ArrayList;
import java.util.Collection;


public class OneChildSametableWSTest extends SDKWSTestBase
{
	public static String getTestCaseName()
	{
		return "One Child Same Table WS Test Case";
	}
	
	protected Collection<Class> getClasses() throws Exception
	{	
		Collection<Class> mappedKlasses = new ArrayList<Class>();
		
		mappedKlasses.add(Note.class);
		mappedKlasses.add(Currency.class);
		
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
		Class targetClass = Currency.class;
		Currency criteria = new Currency();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Currency currency = (Currency)obj;
			assertNotNull(currency);
			assertNotNull(currency.getId());
			assertNotNull(currency.getCountry());	
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
		Class targetClass = Note.class;
		Currency criteria = new Note();

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(3,results.length);
		
		for (Object obj : results){
			Note note = (Note)obj;
			assertNotNull(note);
			assertNotNull(note.getId());
			assertNotNull(note.getValue());			
			assertNotNull(note.getCountry());	
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
		//Class targetClass = Note.class;
		Class targetClass = Currency.class;
		Currency criteria = new Currency();
		criteria.setCountry("USA");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Note note = (Note)results[0];
		assertNotNull(note);
		assertNotNull(note.getId());
		assertNotNull(note.getCountry());			
		assertNotNull(note.getValue());		
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
		Class targetClass = Currency.class;
		Note criteria = new Note();
		criteria.setCountry("Germany");

		Object[] results = getQueryObjectResults(targetClass, criteria);

		assertNotNull(results);
		assertEquals(1,results.length);
		
		Currency currency = (Currency)results[0];
		assertNotNull(currency);
		assertNotNull(currency.getId());
		assertNotNull(currency.getCountry());					
	}
}
