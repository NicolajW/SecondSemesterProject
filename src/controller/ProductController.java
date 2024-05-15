package controller;

import java.util.Map;

import db.DataAccessException;
import db.InventoryDAO;
import db.InventoryDB;
import db.ProductDAO;
import model.Inventory;
import model.SaleProduct;
import db.ProductDB;



public class ProductController {
	private ProductDAO pDao;
	private InventoryDAO iDao;
	
	public ProductController() throws DataAccessException {
		pDao = new ProductDB();
		iDao = new InventoryDB();
	}
	public SaleProduct findByProductById(int id) throws DataAccessException {
		return pDao.findByProductById(id);
	}
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		iDao.updateProductQuantity(inventory);
	}
	
//	public Map<Integer, SaleProduct> getAllProductsAsMap() throws DataAccessException{
//		return pDao.getAllProductsAsMap();
//	}
	
//	public SaleProduct findByMapProductID(int saleProductID) throws DataAccessException {
//		return pDao.findByMapProductID(saleProductID);
//	}
}
