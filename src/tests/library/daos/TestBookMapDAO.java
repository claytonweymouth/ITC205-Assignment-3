package tests.library.daos;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class TestBookMapDAO {

	private IBookDAO _dao;
	private IBookHelper _helper;
	
	private String _title;
	private String _author;
	private String _callNumber;
	
	@Before
	public void setUp() throws Exception {
		_helper = new BookHelper(); // Should have mocked this, but tests were failing?
		//_helper = mock(IBookHelper.class);
		_dao = new BookMapDAO(_helper);
		
		_title = "Test Book Title";
		_author = "Test Book Author";
		_callNumber = "Test Call Number";
	}

	@After
	public void tearDown() throws Exception {
		_dao = null;
	}

	@Test
	public void testConstructor() {
		//Verify
		assertTrue( _dao instanceof BookMapDAO );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorBadArgsNoHelper() {
		_dao = new BookMapDAO(null);
	}
		
	@Test
	public void testAddBook() {
		//Execute
		IBook testBook = _dao.addBook(_author, _title, _callNumber);
		
		//Verify
		assertTrue(testBook instanceof Book);
	}

	@Test
	public void testGetBookByID() {
		//Setup
		IBook testBookOne = _dao.addBook(_author, _title, _callNumber);
		
		//Execute
		IBook testBookTwo = _dao.getBookByID(testBookOne.getID());
		
		//Verify
		assertTrue(testBookTwo instanceof Book);
		assertEquals(testBookOne.getID(), testBookTwo.getID());
	}

	@Test
	public void testListBooks() {
		//Setup
		IBook testBookOne = _dao.addBook(_author, _title, _callNumber);
		IBook testBookTwo = _dao.addBook(_author, _title, _callNumber);
		
		//Execute
		List<IBook> testBookList = _dao.listBooks();
		
		//Verify
		assertTrue(testBookList instanceof List<?>);
		assertTrue(testBookList.contains(testBookOne));
		assertTrue(testBookList.contains(testBookTwo));
	}

	@Test
	public void testFindBooksByAuthor() {
		//Setup
		IBook testBookOne = _dao.addBook(_author, _title, _callNumber);
		IBook testBookTwo = _dao.addBook("Needle", _title, _callNumber);
		
		//Execute
		List<IBook> testBookList = _dao.findBooksByAuthor("Needle");
		
		//Verify
		assertTrue(testBookList instanceof List<?>);
		assertTrue(testBookList.contains(testBookTwo));
	}
	
	@Test
	public void testFindBooksByTitle() {
		//Setup
		IBook testBookOne = _dao.addBook(_author, _title, _callNumber);
		IBook testBookTwo = _dao.addBook(_author, "Needle", _callNumber);
		
		//Execute
		List<IBook> testBookList = _dao.findBooksByTitle("Needle");
		
		//Verify
		assertTrue(testBookList instanceof List<?>);
		assertTrue(testBookList.contains(testBookTwo));
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
