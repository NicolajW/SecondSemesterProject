package controller;

import java.util.Map;

import db.DataAccessException;
import db.InventoryDAO;
import db.InventoryDB;
import db.SaleProductDAO;
import model.Inventory;
import model.SaleProduct;
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
}
