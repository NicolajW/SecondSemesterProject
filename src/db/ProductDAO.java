package db;

import java.util.List;

import model.Ingredients;
import model.Product;

public interface ProductDAO {
	List<Product> findAll() throws DataAccessException;
	Product findByProductID(int productID) throws DataAccessException;
	Product findInventoryById(int inventoryID) throws DataAccessException;
	Product findProductByBarcode(String barcode) throws DataAccessException;
	int findInventoryIDByBarcode(String barcode) throws DataAccessException;
}
