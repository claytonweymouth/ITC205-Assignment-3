package tests.library.entities;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import library.entities.Book;
import library.entities.Loan;



public class TestBook {

	private IBook _book;
	private ILoan _loan;
	
	private String _title;
	private String _author;
	private String _callNumber;
	private int _bookId;
	
	@Before
	public void setUp() throws Exception {
		_loan = mock(ILoan.class);
		
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
		//Setup
		_book = new Book(_title, _author, _callNumber, _bookId);
		
		//Execute
		_book.borrow(_loan);
		
		//Verify
		assertTrue(_book.getState() == EBookState.ON_LOAN);
		assertTrue(_book.getLoan() == _loan);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBorrowBadParamLoanNull() {
		//Setup
		_book = new Book(_title, _author, _callNumber, _bookId);
		
		//Execute
		_book.borrow(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBorrowStateNotAvaliable() {
		//Setup
		_book = new Book(_title, _author, _callNumber, _bookId);
		_book.dispose();
		
		//Execute
		_book.borrow(_loan);
	}
	
	@Test
	public void testGetLoan() {
		//Setup
		_book = new Book(_title, _author, _callNumber, _bookId);
		
		//Execute
		_book.borrow(_loan);
		ILoan testLoan = _book.getLoan();
		
		//Verify
		assertTrue(testLoan instanceof ILoan);
		assertTrue(testLoan == _loan);
	}
	
	@Test
	public void testGetLoanWhenNotLoaned() {
		//Setup
		_book = new Book(_title, _author, _callNumber, _bookId);
		
		//Execute
		ILoan testLoan = _book.getLoan();
		
		//Verify
		assertFalse(testLoan instanceof ILoan);
		assertFalse(testLoan == _loan);
		assertTrue(testLoan == null);
	}
	/*
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
