package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import library.daos.BookHelper;
import library.daos.BookMapDAO;

import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class TestIntegrationBookHelperDao {

	private IBookHelper _helper;
	private IBook _book;    
	private IBookDAO _dao;
	
	private String _author;
	private String _title;
	private String _callNumber;
	private int _bookId;

	@Before
	public void setUp()
	{
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
		_bookId = 42;
		
		_helper = new BookHelper();
		_dao = new BookMapDAO(_helper);
		_book = new Book(_author, _title, _callNumber, _bookId);		
	}

	@After
	public void clearSetUp()
	{
		_helper = null;
		_dao = null;
		_book = null;
	}

	@Test
	public void testBookMapDAO() {
		//execute
		_dao = new BookMapDAO(_helper);

		//verify
		assertTrue(_dao instanceof BookMapDAO);
	}

	@Test
	public void testAddBook() {
		//execute
		_book = _dao.addBook(_author, _title, _callNumber);

		//verify
		assertTrue(_book instanceof Book);
		assertEquals(_author, _book.getAuthor());
		assertEquals(_title, _book.getTitle());
		assertEquals(_callNumber, _book.getCallNumber());
	}

	@Test
	public void testGetBookByID() {
		//execute
		_book = _dao.addBook(_author, _title, _callNumber);

		IBook testBook = _dao.getBookByID(_book.getID());

		//verify
		assertEquals(_book, testBook);
	}

	@Test
	public void testListBooks() {
		//setup
		IBook testBook = _dao.addBook(_author, _title, _callNumber);
		IBook anotherTestBook = _dao.addBook(_author, _title, _callNumber);

		//execute
		List<IBook> testList = _dao.listBooks();

		//verify
		assertTrue(testList instanceof List<?>);
		assertTrue(testList.contains(testBook));
		assertTrue(testList.contains(anotherTestBook));
	}

	@Test
	public void testFindBooksByAuthor() {
		//setup
		IBook testBook = _dao.addBook(_author, _title, _callNumber);
		IBook anotherTestBook = _dao.addBook("Other Author", _title, _callNumber);
		
		//execute
		List<IBook> testList = _dao.findBooksByAuthor("Other Author");

		//verify
		assertTrue(testList instanceof List<?>);
		assertFalse(testList.contains(testBook));
		assertTrue(testList.contains(anotherTestBook));
	}

	@Test
	public void testFindBooksByTitle() {
		//setup
		IBook testBook = _dao.addBook(_author, _title, _callNumber);
		IBook anotherTestBook = _dao.addBook(_author, "Other Title", _callNumber);
		
		//execute
		List<IBook> testList = _dao.findBooksByTitle("Other Title");

		//verify
		assertTrue(testList instanceof List<?>);
		assertFalse(testList.contains(testBook));
		assertTrue(testList.contains(anotherTestBook));
	}

	@Test
	public void testFindBooksByAuthorTitle() {
		//Setup
		IBook testBookOne = _dao.addBook(_author, _title, _callNumber);
		IBook testBookTwo = _dao.addBook("Needle", _title, _callNumber);
		IBook testBookThree = _dao.addBook(_author, "Needle", _callNumber);
		IBook testBookFour = _dao.addBook("Haystack", _title, _callNumber);
		IBook testBookFive = _dao.addBook(_author, "Haystack", _callNumber);
		IBook testBookSix = _dao.addBook("Needle", "Haystack", _callNumber);
		
		//Execute
		List<IBook> testBookList = _dao.findBooksByAuthorTitle("Needle", "Haystack");
		
		//Verify
		assertTrue(testBookList instanceof List<?>);
		assertTrue(testBookList.contains(testBookSix));
		assertFalse(testBookList.contains(testBookOne));
		assertFalse(testBookList.contains(testBookTwo));
		assertFalse(testBookList.contains(testBookThree));
		assertFalse(testBookList.contains(testBookFour));
		assertFalse(testBookList.contains(testBookFive));
	}
}