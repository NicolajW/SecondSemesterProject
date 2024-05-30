package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SaleOrderController;
import controller.TableController;
import db.DBConnection;
import db.DataAccessException;
import db.SaleOrderDB;
import db.TableDB;
import model.Person;
import model.SaleOrder;
import model.Table;

public class TestOrderSavedInDatabase {

	
	private static DBConnection db;
	private static SaleOrderController soCtrl;
	private static TableController tCtrl;
	private static SaleOrder saleOrder;
	private static SaleOrderDB soDB;
	
	@BeforeAll
	public static void setUpAll() throws Exception {
		soCtrl = new SaleOrderController();
		tCtrl = new TableController();
		db = DBConnection.getInstance();
		   TableDB tbd = new TableDB();
	        Table t = new Table(false, 1);
			tbd.updateTableStatus(t);
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
	public void testOrderSavedInDatabase() throws DataAccessException {
		//Arrange
		Person p = soCtrl.findByPersonEmail("bobby@hotmail.com");
		Table t = new Table(false, 1);
		soCtrl.updateTableStatus(t);
		
		//Act
		saleOrder = soCtrl.createSaleOrder(p.getEmail(), t);
	    SaleOrder saved = soCtrl.saveOrder();
		
	    //Assert
		assertNotNull(saved.getOrderNo());
		assertEquals(saved.getPerson().getEmail(), saleOrder.getPerson().getEmail());
	}
}
