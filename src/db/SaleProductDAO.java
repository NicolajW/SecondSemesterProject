package db;

import java.util.List;
import model.SaleProduct;
import model.Food;
import model.Wine;

public interface SaleProductDAO {
	List<SaleProduct> findAll() throws DataAccessException;

	SaleProduct findByProductById(int id) throws DataAccessException;

	int findProductIDOnWine(int saleProductID) throws DataAccessException;

	Wine findWineOnSaleProductID(int saleProductID) throws DataAccessException;

	Food findFoodOnSaleProductID(int saleProductID) throws DataAccessException;
}
