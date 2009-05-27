package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author;
import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book;


public class M2MUnidirectionalWritableApiTest extends SDKWritableApiBaseTest {
	private static Logger log = Logger.getLogger(M2MUnidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "Many to Many Unidirectional WritableApi Test Case";
	}
	
	public void testSaveObjectBook() {
		log.debug("\n------saveBook() --------\n\n");
		Book book = new Book();
		book.setName("Book");

		save(book);

		Book result = (Book) getObject(Book.class, book.getId());
		Assert.assertEquals(book.getName(), result.getName());
	}
	
	public void testSaveObjectBookAndM2MCollection() {
		log.debug("\n------saveObjectBookAndAddRelationWithAuthor() --------\n\n");
		Book book = new Book();
		book.setName("Book");
		Author author = new Author();
		author.setName("author");
		Collection<Author> authors = new HashSet<Author>();
		authors.add(author);
		book.setAuthorCollection(authors);

		save(author);
		save(book);
		
		Book result=(Book)getObjectAndLazyCollection(Book.class, book.getId(), "authorCollection");
		Assert.assertEquals(book.getName(), result.getName());
		Assert.assertEquals(1, result.getAuthorCollection().size());
	}
	
	public void testUpdateObjectBookAndM2MCollection() {
		log.debug("\n------saveObjectBookAndAddRelationWithAuthor() --------\n\n");
		Book book = new Book();
		book.setName("Book");
		Author author = new Author();
		author.setName("author");
		Collection<Author> authors = new HashSet<Author>();
		authors.add(author);
		book.setAuthorCollection(authors);

		save(author);
		save(book);
		
		Book updateBook=(Book)getObjectAndLazyCollection(Book.class, book.getId(), "authorCollection");
		updateBook.setName("updatedBook");
		Author updatedAuthor=updateBook.getAuthorCollection().iterator().next();
		updatedAuthor.setName("updatedAuthor");
		
		update(updatedAuthor);
		update(updateBook);
		
		Book resultBook=(Book)getObjectAndLazyCollection(Book.class, updateBook.getId(), "authorCollection");
		Author resultAuthor=resultBook.getAuthorCollection().iterator().next();
		Assert.assertEquals("updatedBook", resultBook.getName());
		Assert.assertEquals("updatedAuthor", resultAuthor.getName());
	}
}
