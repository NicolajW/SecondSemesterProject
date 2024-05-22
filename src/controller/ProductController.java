package controller;

import db.DataAccessException;
import db.ProductDAO;
import db.ProductDB;
import model.Product;

public class ProductController {

	private ProductDAO pDao;


	public ProductController() throws DataAccessException {
		pDao = new ProductDB();
	}

	public void findAll() throws DataAccessException {
		pDao.findAll();
	}

	public Product findByProductID(int productID) throws DataAccessException {
		return pDao.findByProductID(productID);
	}
	public Product findInventoryByID(int inventoryID) throws DataAccessException {
		return pDao.findInventoryById(inventoryID);
		
	}
	public Product findProductByBarcode(String barcode) throws DataAccessException {
		return pDao.findProductByBarcode(barcode);
	}
	
	public int findInventoryIDByBarcode(String barcode) throws DataAccessException {
		return pDao.findInventoryIDByBarcode(barcode);
	}

}
