package tests.library.daos;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.daos.BookMapDAO;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;

public class TestBookMapDAO {

	private IBookDAO _dao;
	private IBookHelper _helper;
	
	@Before
	public void setUp() throws Exception {
		_helper = mock(IBookHelper.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor() {
		//Setup
		_dao = new BookMapDAO(_helper);
		
		//Verify
		assertTrue( _dao instanceof BookMapDAO );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorBadArgsNoHelper() {
		//Setup
		_dao = new BookMapDAO(null);
	}
	
	@Test
	public void testOverloadedConstructor() {
		fail("Not yet implemented");
	}
/*
	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBookByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testListBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBooksByAuthorTitle() {
		fail("Not yet implemented");
	}
*/
}
