package controller;

import db.DataAccessException;
import db.ProductDAO;
import db.ProductDB;

public class ProductController {

	private ProductDAO pDao;


	public ProductController() throws DataAccessException {
		pDao = new ProductDB();
	}

	public void findAll() throws DataAccessException {
		pDao.findAll();
	}

	public void findByProductID(int productID) throws DataAccessException {
		pDao.findByProductID(productID);
	}

}
