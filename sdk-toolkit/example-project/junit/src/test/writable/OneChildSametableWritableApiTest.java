/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import org.apache.log4j.Logger;

import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Currency;
import gov.nih.nci.cacoresdk.domain.inheritance.onechild.sametable.Note;
import junit.framework.Assert;

public class OneChildSametableWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(OneChildSametableWritableApiTest.class);
	public static String getTestCaseName() {
		return "One Child Same Table Writable Api Test Case";
	}

	public void testSaveObjectWithSameTableInheritance(){
		log.debug("\n-------testSaveObjectWithSameTableInheritance()-------");
		Currency currency = new Currency();
		currency.setCountry("usa");
		Note note = new Note();
		note.setCountry("usa");
		note.setValue(100);

		save(currency);
		save(note);

		Currency resultCurrency = (Currency) getObject(Currency.class, currency.getId());
		Note resultNote = (Note) getObject(Note.class, note.getId());

		Assert.assertEquals(currency.getCountry(), resultCurrency.getCountry());
		Assert.assertEquals(note.getValue(), resultNote.getValue());
	}
	
	public void testUpdateObjectWithSameTableInheritance(){
		log.debug("\n-------testUpdateObjectWithSameTableInheritance()-------");
		Currency currency = new Currency();
		currency.setCountry("usa");
		Note note = new Note();
		note.setCountry("usa");
		note.setValue(100);

		save(currency);
		save(note);

		Currency updateCurrency = (Currency) getObject(Currency.class, currency.getId());
		Note updateNote = (Note) getObject(Note.class, note.getId());
		updateCurrency.setCountry("india");
		updateNote.setValue(500);
		
		update(updateCurrency);
		update(updateNote);
		
		Currency resultCurrency = (Currency) getObject(Currency.class, updateCurrency.getId());
		Note resultNote = (Note) getObject(Note.class, updateNote.getId());

		Assert.assertEquals(updateCurrency.getCountry(), resultCurrency.getCountry());
		Assert.assertEquals(updateNote.getValue(), resultNote.getValue());
	}
	
	public void testDeleteObjectWithSameTableInheritance(){
		log.debug("\n-------testDeleteObjectWithSameTableInheritance()-------");
		Currency currency = new Currency();
		currency.setCountry("usa");
		Note note = new Note();
		note.setCountry("usa");
		note.setValue(100);

		save(currency);
		save(note);

		Currency deleteCurrency = (Currency) getObject(Currency.class, currency.getId());
		Note deleteNote = (Note) getObject(Note.class, note.getId());
		
		delete(deleteCurrency);
		delete(deleteNote);
		
		Currency resultCurrency = (Currency) getObject(Currency.class, deleteCurrency.getId());
		Note resultNote = (Note) getObject(Note.class, deleteNote.getId());

		Assert.assertNull(resultCurrency);
		Assert.assertNull(resultNote);
	}
}
