package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PersonController;
import controller.SaleOrderController;
import db.DataAccessException;
import model.Person;
import model.SaleOrder;

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
	public void emailPersonDontExist() throws DataAccessException {
		// Arrange
		Person p = pCtrl.findByPersonEmail("fakebobby@hotmail.com");
		// Act
		
		
		// Assert
		assertNotNull(p);
		assertEquals(p.getEmail(), "bobby@hotmail.com");
		
	}
	@Test
	public void emailPersonDoExist() throws DataAccessException {
		// Arrange
		Person p = pCtrl.findByPersonEmail("bobby@hotmail.com");
		// Act
		
		
		// Assert
		assertNotNull(p);
		assertEquals(p.getEmail(), "bobby@hotmail.com");
		
}

}
