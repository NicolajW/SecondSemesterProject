package controller;


import db.DataAccessException;
import db.SaleProductDAO;
import model.Food;
import model.SaleProduct;
import model.Wine;
import db.SaleProductDB;



public class SaleProductController {
	private SaleProductDAO pDao;
	
	public SaleProductController() {
		try {
			pDao = new SaleProductDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	public SaleProduct findByProductById(int id) throws DataAccessException {
		return pDao.findByProductById(id);
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
}
