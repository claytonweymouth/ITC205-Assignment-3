package tests.library.daos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.daos.BookHelper;
import library.entities.Book;
import library.interfaces.entities.IBook;

public class BookHelperTest {

	private BookHelper _helper;
	
	private String _title;
	private String _author;
	private String _callNumber;
	private int _bookId;
	
	@Before
	public void setUp() throws Exception {
		_helper = new BookHelper();
		
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
		_bookId = 42;
	}

	@After
	public void tearDown() throws Exception {
		_helper = null;
	}

	@Test
	public void testMakeBook() {
		//execute
		IBook testBook = _helper.makeBook(_author, _title, _callNumber, _bookId);
		
		//verify
		assertTrue(testBook instanceof Book);
		assertEquals(testBook.getAuthor(), _author);
		assertEquals(testBook.getTitle(), _title);
		assertEquals(testBook.getCallNumber(), _callNumber);
		assertEquals(testBook.getID(), _bookId);
	}

}
