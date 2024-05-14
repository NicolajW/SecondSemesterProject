package db;

import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import model.SaleProduct;
import model.Food;
import model.Wine;


public interface ProductDAO {
	List<SaleProduct> findAll() throws DataAccessException;
//	saleProduct findById(int barcode) throws DataAccessException;
	SaleProduct findByProductById(int id) throws DataAccessException;
	Map<Integer, SaleProduct> getAllProductsAsMap() throws DataAccessException;
	SaleProduct findByMapProductID(int saleProductID) throws DataAccessException; 
}
