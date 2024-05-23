package db;

import model.SaleOrder;

public interface SaleOrderDAO {
	void saveOrder(SaleOrder order) throws DataAccessException;

	SaleOrder findByOrderNo(int orderNo, boolean fullAssociation) throws DataAccessException;
}
