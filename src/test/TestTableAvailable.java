package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SaleOrderController;
import controller.SaleProductController;
import controller.TableController;
import db.DataAccessException;
import model.Table;

public class TestTableAvailable {

	private static TableController tCtrl;
	private static SaleOrderController soCtrl;
	private static SaleProductController spCtrl;
	
	
	@BeforeAll
	public static void setUpAll() throws Exception {
		tCtrl = new TableController();
		soCtrl = new SaleOrderController();
		spCtrl = new SaleProductController();
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
	public void checkIfTableIsTrue() throws DataAccessException {
		//Arrange
		Table t = new Table(false, 2);
		
		//Act
		tCtrl.updateTableStatus(t);		
		
		//Assert
		assertNotNull(t);
		assertEquals(t.isTableStatus(), true);
		
	}
	@Test
	public void chechIfTableIsFalse() throws DataAccessException {
		//Arrange
		Table t = new Table(false, 2);
		
		//Act
		tCtrl.updateTableStatus(t);		
		
		//Assert
		assertNotNull(t);
		assertEquals(t.isTableStatus(), false);
		
	}
	
	
}

