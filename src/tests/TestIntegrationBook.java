package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import library.entities.Book;
import library.entities.Loan;
import library.entities.Member;

import library.interfaces.entities.EBookState;

import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class TestIntegrationBook {

	private IBook _book;
	private ILoan _loan;
	private IMember _member;

	private String _author;
	private String _title;
	private String _callNumber;
	private int _bookId;

	private Date _borrowDate;
	private Date _dueDate;
	private Calendar _calendar;

	@Before
	public void setUp() throws Exception {
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
		_bookId = 42;

		_calendar = Calendar.getInstance();
		_borrowDate = new Date();
		_calendar.setTime(_borrowDate);
		_calendar.add(Calendar.DATE, ILoan.LOAN_PERIOD);
		_dueDate = _calendar.getTime();

		_book = new Book(_author, _title, _callNumber, _bookId);
		_member = new Member("firstName", "lastName", "contactPhone", "email", 1);

		_loan = new Loan(_book, _member, _borrowDate, _dueDate);
	}

	@After
	public void tearDown() throws Exception {
		_book = null;
		_member = null;
		_loan = null;		
	}

	@Test
	public void testBorrow() {
		//execute
		_book.borrow(_loan);  

		//verify
		assertEquals(_book.getState(), EBookState.ON_LOAN );
	}

	@Test(expected=RuntimeException.class)
	public void testBorrowUnavailable() {
		//setup
		_book.borrow(_loan); 

		//execute
		_book.borrow(_loan); //not a bug, testing borrowing a borrowed book      
	}

	@Test
	public void testGetLoan() {
		//setup
		_book.borrow(_loan);

		//execute
		ILoan testLoan = _book.getLoan();

		//verify
		assertEquals(testLoan, _loan);
	}

	@Test
	public void testGetLoanAvaliable() {
		//execute
		ILoan testLoan = _book.getLoan();

		//verify
		assertNull(testLoan);
	}

	@Test
	public void testGetLoanWhenLost() {
		//setup
		_book.borrow(_loan);
		_book.lose();

		//execute
		ILoan testLoan = _book.getLoan();

		//verify
		assertEquals(EBookState.LOST, _book.getState());
		assertTrue(testLoan instanceof Loan);
	}	

}
