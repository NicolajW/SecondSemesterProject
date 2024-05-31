package controller;

import db.DataAccessException;
import db.InventoryDAO;
import db.InventoryDB;
import model.Inventory;

public class InventoryController {

	private InventoryDAO iDao;

	//Instantiates an object of InventoryDB
	public InventoryController() throws DataAccessException {
		iDao = new InventoryDB();
	}
	/**
	 * @param <code> int </code> inventoryNo
	 * @return the <code> findByInventoryNo </code> with @param inventoryNo
	 * @throws DataAccessException
	 */
	public Inventory findByInventoryNo(int inventoryNo) throws DataAccessException {
		return iDao.findByInventoryNo(inventoryNo);
	}
	
	/**	
	 * instantiates <code> updateProductQuantity </code> for inventory
	 * @param <code> Inventory </code> inventory
	 * @throws DataAccessException
	 */
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		iDao.updateProductQuantity(inventory);
	}
}
