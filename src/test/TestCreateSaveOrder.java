package test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import controller.PersonController;
import controller.SaleOrderController;
import db.DataAccessException;
import model.Person;
import model.SaleOrder;

public class TestCreateSaveOrder {
	
	private static PersonController pctrl;
	private static SaleOrderController soCtrl;
	private static SaleOrder saleOrder;
		
	@BeforeAll
	public static void setUpAll() throws Exception {
		pctrl = new PersonController();
		soCtrl = new SaleOrderController();
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
		Person p = pctrl.findByPersonEmail("bobby@hotmail.com");
		
		// Act
		saleOrder = soCtrl.createSaleOrder(p.getEmail(), 1);
		soCtrl.saveOrder();
		
		
		// Assert
		assertNotNull(saleOrder);
		
	}
	

}
