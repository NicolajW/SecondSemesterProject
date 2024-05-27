package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SaleOrderController;
import controller.TableController;
import db.PersonDB; 
import db.TableDB;
import model.SaleOrder;
import model.Wine;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

public class UnitTests {
	
	SaleOrderController soCtrl = new SaleOrderController();
	private PersonDB pDB;
	private TableDB tDB;
	private TableController tableCtrl;
		
	@BeforeAll
	public static void setUpAll() throws Exception {
	}
	
	@AfterAll
	public static void tearDownAll() throws Exception {
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		soCtrl = new SaleOrderController();
		tableCtrl = new TableController();
		pDB = new PersonDB();
		tDB = new TableDB();
		pDB.findByPersonEmail("Jeff@mail.com");
		tDB.findByTableNo(1);
		tableCtrl.findAllTables();
	}
	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void saleCreated() throws Exception {
		
		SaleOrder res = soCtrl.createSaleOrder("booby@hotmail.com", 1);
		assertNotNull(res);
		assertEquals("Jeff@mail.com", res.getPerson().getEmail());
		assertEquals(1, res.getTableNo());
	}
	
	@Test
	public void wineIsAddedToOrder() throws Exception {
		Wine wine = new Wine(1, "Red Wine", 119.95, "This is Wine", "Wine", "Raspberries", "1969", "Winegarden", "Italy");
		Wine res = wine.getWine();
		assertNotNull(res);
		assertEquals("Red Wine", res.getName());
	}
	@Test
	public void testForAllTables() throws Exception {
		tableCtrl = new TableController();
		tableCtrl.findAllTables();
		equals(null);
	}
}
	
