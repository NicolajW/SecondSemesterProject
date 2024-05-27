package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import controller.SaleProductController;
import db.DataAccessException;
import db.TableDB;
import model.Person;
import model.SaleOrder;
import model.Table;
import controller.PersonController;
import controller.SaleOrderController;




public class TestProductToOrder {
    private static SaleProductController spCtrl;
    private static SaleOrderController soCtrl = new SaleOrderController();
    private static PersonController pCtrl;
    private static SaleOrder saleOrder;
@BeforeAll
    public static void setUpAll() throws Exception {
        System.out.println("start");
        spCtrl = new SaleProductController();
        soCtrl = new SaleOrderController();
        pCtrl = new PersonController();
        System.out.println(pCtrl);
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
        Person p = soCtrl.findByPersonEmail("Man@mail.com");

        //Act
        saleOrder = soCtrl.createSaleOrder(p.getEmail(), 1);
        soCtrl.addProduct(2, 1);
        soCtrl.addProduct(1, 2);

        //Assert
        assertNotNull(saleOrder.getOl());
        assertEquals(saleOrder.getOl().get(0).getSaleProduct().getSaleProductID(), 1);
        assertEquals(saleOrder.getOl().get(1).getSaleProduct().getSaleProductID(), 2);

    }
//    @Test
//    public void CreateSaveOrderSuccess() throws DataAccessException {
//        // Arrange
//        Person p = pCtrl.findByPersonEmail("bobby@hotmail.com");
//
//        // Act
//        saleOrder = soCtrl.createSaleOrder(p.getEmail(), 1);
//        soCtrl.saveOrder();
//
//
//        // Assert
//        assertNotNull(saleOrder);
//        assertEquals(save.getPerson().getEmail(), saleOrder.getPerson().getEmail());
//
//    }


}
