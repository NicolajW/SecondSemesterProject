package db;

import model.Inventory;

public interface InventoryDAO {
	Inventory findByInventoryNo(int inventoryNo) throws DataAccessException;

	void updateProductQuantity(Inventory inventory) throws DataAccessException;
}
