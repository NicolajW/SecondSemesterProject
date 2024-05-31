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

	/**
	 * This method finds all products from the <code>ProductDAO</code> class
	 * @throws DataAccessException
	 */
	public void findAll() throws DataAccessException {
		pDao.findAll();
	}

	/**
	 * @param productID
	 * @return the id of the product found in the dataBase. 
	 * @throws DataAccessException
	 */
	public Product findByProductID(int productID) throws DataAccessException {
		return pDao.findByProductID(productID);
	}
	

	/**
	 * This method finds the inventory id by <code>barcode</code> of the sale products. 
	 * @param barcode
	 * @return the id of the inventory by its <code>barcode</code>. 
	 * @throws DataAccessException
	 */
	public int findInventoryIDByBarcode(String barcode) throws DataAccessException {
		return pDao.findInventoryIDByBarcode(barcode);
	}
	
	/**
	 * This method finds the <code>saleProductID</code> on its ingredients. 
	 * @param saleProductID
	 * @return the ingredient of the productID, that it gets from <code>saleProductID</code>. 
	 * @throws DataAccessException
	 */
	public int findProductIDOnIngredient(int saleProductID) throws DataAccessException{
		return pDao.findProductIDOnIngredient(saleProductID);
	}
	
	/**
	 * This method is a <code>List</code> of ingredients found by the foodID. 
	 * @param foodID
	 * @return the ingredients found by the <code>foodID</code>. 
	 * @throws DataAccessException
	 */
	public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException{
		return pDao.findIngredientsByFoodID(foodID);
	}
}
