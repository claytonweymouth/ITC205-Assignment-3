package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import library.entities.Book;
import library.interfaces.daos.IBookHelper;
import library.daos.BookHelper;
import library.interfaces.entities.IBook;

public class TestIntegrationBookHelperEntity {

	private String _author;
	private String _title;
	private String _callNumber;
	private int _bookId;
	
	private IBookHelper _helper;
	private IBook _book;

	@Before
	public void setUp() throws Exception {
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
		_bookId = 42;
		
		_helper = new BookHelper();		
	}

	@Test
	public void testMakeBook() {
		//execute
		_book = _helper.makeBook(_author, _title, _callNumber, _bookId);

		//verify
		assertNotNull(_book);
		assertTrue(_book instanceof Book);
		assertEquals(_book.getAuthor(), _author);
		assertEquals(_book.getTitle(), _title);
		assertEquals(_book.getCallNumber(), _callNumber);
		assertEquals(_book.getID(), _bookId);
	}
}