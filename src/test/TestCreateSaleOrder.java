package test;

import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import controller.PersonController;
import controller.SaleOrderController;
import controller.TableController;
import db.DataAccessException;
import db.TableDB;
import model.Person;
import model.SaleOrder;
import model.Table;

public class TestCreateSaleOrder {
	
	private static PersonController pctrl;
	private static SaleOrderController soCtrl;
	private static SaleOrder saleOrder;
	private static TableController tCtrl;
		
	@BeforeAll
	public static void setUpAll() throws Exception {
		pctrl = new PersonController();
		soCtrl = new SaleOrderController();
		tCtrl = new TableController();
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
	public void createSaleOrderSuccess() throws DataAccessException {
		// Arrange
		Person p = pctrl.findByPersonEmail("bobby@hotmail.com");
		Table t = new Table(false, 1);
		tCtrl.updateTableStatus(t);
		
		// Act
		saleOrder = soCtrl.createSaleOrder(p.getEmail(), t);
		SaleOrder save = soCtrl.saveOrder();
		
		// Assert
		assertNotNull(saleOrder);
		assertEquals(save.getPerson().getEmail(), saleOrder.getPerson().getEmail());
		
	}
	
	@Test
	public void createSaleOrderFailed() throws DataAccessException {
		
		//Arrange
		Person p = pctrl.findByPersonEmail("bobby@hotmail.com");
        Table t = new Table(true, 3);
        tCtrl.updateTableStatus(t);
		
		//Act
		saleOrder = soCtrl.createSaleOrder(p.getEmail(), t);
		SaleOrder save = soCtrl.saveOrder();
		
		//Assert
		assertNotNull(tCtrl);
		assertEquals(save.getPerson().getEmail(), saleOrder.getPerson().getEmail());
	}
}
