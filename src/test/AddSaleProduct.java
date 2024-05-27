package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.PersonController;
import controller.ProductController;
import controller.SaleOrderController;
import controller.SaleProductController;
import db.DataAccessException;
import model.Product;
import model.SaleOrder;
import model.SaleProduct;

class AddSaleProduct {
	
	private static PersonController pCtrl;
	private static ProductController proCtrl;
	private static SaleOrderController soCtrl;
	private static SaleProductController spCtrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pCtrl = new PersonController();
		proCtrl = new ProductController();
		
		
	}

	

	@Test
	void testAddSaleProductTypeWineSuccess() throws DataAccessException {
		// Arrange
		spCtrl = new SaleProductController();
		proCtrl = new ProductController();
		soCtrl = new SaleOrderController();
		
		// Act
		String sp = spCtrl.findByProductById(1).toString();
		
		
		
		
		// Assert
		assertNotNull(sp);
		
		System.out.println(sp);
	}

}
