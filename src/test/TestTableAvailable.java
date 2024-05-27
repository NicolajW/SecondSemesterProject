package test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SaleOrderController;
import controller.SaleProductController;
import controller.TableController;
import db.DataAccessException;

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
	public static void setUp() throws Exception {
	}
	
	@AfterEach
	public static void tearDown() throws Exception {
		
	}
	
	@Test
	public void checkIfTableIsAvailable() throws Exception {
		//Arrange
		
		
		//Act
		
		
		//Assert
		
	}
	
	
}

