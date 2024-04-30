package db;

import java.util.List;
import java.sql.SQLException;
import model.saleProduct;
import model.Food;
import model.Wine;


public interface ProductDAO {
	List<saleProduct> findAll() throws DataAccessException;
//	saleProduct findById(int barcode) throws DataAccessException;
	saleProduct findByProductById(int id); 
}
