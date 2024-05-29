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
import controller.SaleProductController;
import db.DataAccessException;
import db.TableDB;
import model.Person;
import model.SaleOrder;
import model.Table;

public class TestProductDontExist {
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
    public void productDontExist() throws DataAccessException {
    	   //Arrange
        Person p = soCtrl.findByPersonEmail("bobby@hotmail.com");
        Table t = new Table(false, 1);
    	soCtrl.updateTableStatus(t);
        
        //Act
        saleOrder = soCtrl.createSaleOrder(p.getEmail(), t);
        soCtrl.addProduct(1, 10);
        soCtrl.addProduct(1, 2);

        //Assert
        assertNotNull(saleOrder.getOl());
        assertEquals(saleOrder.getOl().get(0).getSaleProduct().getSaleProductID(), 1);
        assertEquals(saleOrder.getOl().get(1).getSaleProduct().getSaleProductID(), 2);

    }
}

