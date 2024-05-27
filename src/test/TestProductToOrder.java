package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SaleProductController;
import db.DataAccessException;
import db.TableDB;
import model.Food;
import model.Person;
import model.SaleOrder;
import model.Table;
import controller.PersonController;
import controller.SaleOrderController;




public class TestProductToOrder {
	private static SaleProductController spCtrl;
    private static SaleOrderController soCtrl;
    private static PersonController pCtrl;
    private static SaleOrder saleOrder;
    
    @BeforeAll
    public static void setUpAll() throws Exception {
        spCtrl = new SaleProductController();
        soCtrl = new SaleOrderController();
        pCtrl = new PersonController();
        TableDB tbd = new TableDB();
        Table t = new Table(false, 1);
		tbd.updateTableStatus(t);
		System.out.println("hej");
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
    public void ProductToOrderSuccess() throws DataAccessException {
        //Arrange
        Person p = soCtrl.findByPersonEmail("Man@mail.com");
        Table t = new Table(false, 1);
    	soCtrl.updateTableStatus(t);
        
        //Act
        saleOrder = soCtrl.createSaleOrder(p.getEmail(), 1);
        soCtrl.addProduct(2, 1);
        soCtrl.addProduct(1, 2);

        //Assert
        assertNotNull(saleOrder.getOl());
        assertEquals(saleOrder.getOl().get(0).getSaleProduct().getSaleProductID(), 1);
        assertEquals(saleOrder.getOl().get(1).getSaleProduct().getSaleProductID(), 2);

    }
    
    @Test
    public void FoodToOrderSuccess() throws DataAccessException {
    	//Arrange
    	Person p2 = soCtrl.findByPersonEmail("bobby@hotmail.com");
    	Food f = spCtrl.findFoodOnSaleProductID(2);
    	Table t = new Table(false, 2);
    	soCtrl.updateTableStatus(t);
    	
    	//Act
    	saleOrder = soCtrl.createSaleOrder(p2.getEmail(), 2);
    	soCtrl.addProduct(2, 2);
    	
    	//Assert
    	assertNotNull(saleOrder.getOl());
//    	assertEquals(saleOrder.getOl().get(0).getSaleProduct().getFood(), 2);
        assertEquals(2, saleOrder.getOl().get(0).getSaleProduct().getFood().getSaleProductID());
//    	assertEquals(saleOrder.getOl().get(0).getSaleProduct().getFood().getMenuName(), "tapas");
    }

}
