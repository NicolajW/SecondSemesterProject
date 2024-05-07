package db;

import java.util.List;
import java.sql.SQLException;
import model.SaleProduct;
import model.Food;
import model.Wine;


public interface ProductDAO {
	List<SaleProduct> findAll() throws DataAccessException;
//	saleProduct findById(int barcode) throws DataAccessException;
	SaleProduct findByProductById(int id) throws DataAccessException; 
}
