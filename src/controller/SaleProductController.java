package controller;

import java.util.List;
import java.util.Map;

import db.DataAccessException;
import db.InventoryDAO;
import db.InventoryDB;
import db.SaleProductDAO;
import model.Food;
import model.Ingredients;
import model.Inventory;
import model.SaleProduct;
import model.Wine;
import db.SaleProductDB;



public class SaleProductController {
	private SaleProductDAO pDao;
	private InventoryDAO iDao;
	
	public SaleProductController() throws DataAccessException {
		pDao = new SaleProductDB();
		iDao = new InventoryDB();
	}
	public SaleProduct findByProductById(int id) throws DataAccessException {
		return pDao.findByProductById(id);
	}
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		iDao.updateProductQuantity(inventory);
	}
	
	public int findProductIDOnWine(int saleProductID) throws DataAccessException{
		return pDao.findProductIDOnWine(saleProductID);
	}
	
	public Wine findWineOnSaleProductID(int saleProductID) throws DataAccessException {
		return pDao.findWineOnSaleProductID(saleProductID);
	}
	
	public Food findFoodOnSaleProductID(int saleProductID) throws DataAccessException{
		return pDao.findFoodOnSaleProductID(saleProductID);
	}
	
	public int findProductIDOnIngredient(int saleProductID) throws DataAccessException{
		return pDao.findProductIDOnIngredient(saleProductID);
	}
	
	public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException{
		return pDao.findIngredientsByFoodID(foodID);
	}
}
