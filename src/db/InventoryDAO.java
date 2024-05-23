package db;

import model.Inventory;
import model.SaleProduct;

public interface InventoryDAO {
	Inventory findByInventoryNo(int inventoryNo) throws DataAccessException;

	void updateProductQuantity(Inventory inventory) throws DataAccessException;

}
