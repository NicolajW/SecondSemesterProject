package controller;

import java.util.List;

import db.DataAccessException;
import db.ProductDAO;
import db.ProductDB;
import model.Ingredients;
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

	public int findInventoryIDByBarcode(String barcode) throws DataAccessException {
		return pDao.findInventoryIDByBarcode(barcode);
	}
	
	public int findProductIDOnIngredient(int saleProductID) throws DataAccessException{
		return pDao.findProductIDOnIngredient(saleProductID);
	}
	
	public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException{
		return pDao.findIngredientsByFoodID(foodID);
	}

}
