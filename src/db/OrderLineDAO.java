package db;

import java.util.List;

import model.OrderLine;

public interface OrderLineDAO {
	List<OrderLine> findAll() throws DataAccessException;
	OrderLine findBySaleOrder(int orderNo) throws DataAccessException;
}
