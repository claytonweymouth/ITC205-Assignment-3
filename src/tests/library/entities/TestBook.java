package tests.library.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import library.entities.Book;
import library.entities.Loan;



public class TestBook {

	IBook _book;
	ILoan _loan;
	
	String _title;
	String _author;
	String _callNumber;
	int _bookId;
	
	@Before
	public void setUp() throws Exception {
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
		_bookId = 42;
	}

	@After
	public void tearDown() throws Exception {
		_book = null;
		_loan = null;
	}

	@Test
	public void testCreateBook() {
		_book = new Book(_title, _author, _callNumber, _bookId);		
		assertTrue( _book instanceof Book );
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamTitleNull() {
		_book = new Book(null, _author, _callNumber, _bookId);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamTitleBlank() {
		_book = new Book("", _author, _callNumber, _bookId);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamAuthorNull() {
		_book = new Book(_title, null, _callNumber, _bookId);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamAuthorBlank() {
		_book = new Book(_title, "", _callNumber, _bookId);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamCallNumberNull() {
		_book = new Book(_title, _author, null, _bookId);		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamCallNumberBlank() {
		_book = new Book(_title, _author, "", _bookId);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamBookIdNotZero() {
		_book = new Book(_title, _author, _callNumber, 0);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateBookBadParamBookIdNotLessThanZero() {
		_book = new Book(_title, _author, _callNumber, -1);		
	}

	@Test
	public void testBorrow() {
		fail("Not yet implemented");
	}
	
	/*
	@Test
	public void testGetLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testLose() {
		fail("Not yet implemented");
	}

	@Test
	public void testRepair() {
		fail("Not yet implemented");
	}

	@Test
	public void testDispose() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCallNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
	*/
}
