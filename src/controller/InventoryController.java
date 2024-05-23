package controller;

import db.DataAccessException;
import db.InventoryDAO;
import db.InventoryDB;
import model.Inventory;

public class InventoryController {

	private InventoryDAO iDao;

	public InventoryController() throws DataAccessException {
		iDao = new InventoryDB();
	}

	public Inventory findByInventoryNo(int inventoryNo) throws DataAccessException {
		return iDao.findByInventoryNo(inventoryNo);
	}

	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		iDao.updateProductQuantity(inventory);
	}

}
