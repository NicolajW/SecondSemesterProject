package controller;

import db.DataAccessException;
import db.ProductDAO;
import model.SaleProduct;
import db.ProductDB;



public class ProductController {
	private ProductDAO pDao;
	
	public ProductController() throws DataAccessException {
		pDao = new ProductDB();
	}
	public SaleProduct findByProductById(int id) throws DataAccessException {
		return pDao.findByProductById(id);
	}
	public void updateInventory(int id) throws DataAccessException {
	}
}
