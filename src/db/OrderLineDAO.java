package db;

import java.util.List;

import model.OrderLine;

public interface OrderLineDAO {
	List<OrderLine> findAll() throws DataAccessException;
	List<OrderLine> findById(int orderNo) throws DataAccessException;
}
