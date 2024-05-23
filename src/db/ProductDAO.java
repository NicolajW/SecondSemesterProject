package db;

import java.util.List;

import model.Ingredients;
import model.Product;

public interface ProductDAO {
	List<Product> findAll() throws DataAccessException;

	Product findByProductID(int productID) throws DataAccessException;

	int findInventoryIDByBarcode(String barcode) throws DataAccessException;

	int findProductIDOnIngredient(int saleProductID) throws DataAccessException;

	List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException;
}
