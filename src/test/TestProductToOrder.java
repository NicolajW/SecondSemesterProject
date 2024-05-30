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
import model.Wine;
import model.Person;
import model.SaleOrder;
import model.Table;
import controller.SaleOrderController;




public class TestProductToOrder {
	private static SaleProductController spCtrl;
    private static SaleOrderController soCtrl;
    private static SaleOrder saleOrder;
    
    @BeforeAll
    public static void setUpAll() throws Exception {
        spCtrl = new SaleProductController();
        soCtrl = new SaleOrderController();
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
    public void ProductToOrderSuccess() throws DataAccessException {
        //Arrange
        Person p = soCtrl.findByPersonEmail("bobby@hotmail.com");
        Table t = new Table(false, 1);
    	soCtrl.updateTableStatus(t);
        
        //Act
        saleOrder = soCtrl.createSaleOrder(p.getEmail(), t);
        soCtrl.addProduct(2, 3);
        soCtrl.addProduct(1, 4);

        //Assert
        assertNotNull(saleOrder.getOl());
        assertEquals(saleOrder.getOl().get(0).getSaleProduct().getSaleProductID(), 3);
        assertEquals(saleOrder.getOl().get(1).getSaleProduct().getSaleProductID(), 4);

    }
    
    @Test
    public void FoodToOrderSuccess() throws DataAccessException {
    	//Arrange
    	Person p2 = soCtrl.findByPersonEmail("bobby@hotmail.com");
    	Food f = spCtrl.findFoodOnSaleProductID(3);
    	Table t = new Table(false, 2);
    	soCtrl.updateTableStatus(t);
    	
    	//Act
    	saleOrder = soCtrl.createSaleOrder(p2.getEmail(), t);
    	soCtrl.addProduct(2, 3);
    	
    	//Assert
    	assertNotNull(saleOrder.getOl());
        assertEquals(3, saleOrder.getOl().get(0).getSaleProduct().getFood().getSaleProductID());
    }
    @Test
    public void WineToOrderSuccess() throws DataAccessException {
    	//Arrange
    	Person p2 = soCtrl.findByPersonEmail("bobby@hotmail.com");
    	Wine w = spCtrl.findWineOnSaleProductID(4);
    	Table t = new Table(false, 2);
    	soCtrl.updateTableStatus(t);
    	
    	//Act
    	saleOrder = soCtrl.createSaleOrder(p2.getEmail(), t);
    	soCtrl.addProduct(1, 4);
    	
    	//Assert
    	assertNotNull(saleOrder.getOl());
        assertEquals(4, saleOrder.getOl().get(0).getSaleProduct().getWine().getSaleProductID());
    }

}
