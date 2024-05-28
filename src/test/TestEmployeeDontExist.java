package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PersonController;
import db.DataAccessException;
import model.Person;

public class TestEmployeeDontExist {

	private static PersonController pCtrl;
		
	@BeforeAll
	public static void setUpAll() throws Exception {
		pCtrl = new PersonController();
	}
	
	@AfterAll
	public static void tearDownAll() throws Exception {
	}
	
	@BeforeEach
	public void setUp() throws Exception {
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void CreateSaveOrderSuccess() throws DataAccessException {
		// Arrange
		Person p = pCtrl.findByPersonEmail("fakeBobby@hotmail.com");
		String per = p.getEmail();
		// Act
		
		
		// Assert
		assertNotNull(p);
		assertEquals(p.getEmail(), "bobby@hotmail.com");
		
	}
}


