package db;

import java.util.List;

import model.Table;

public interface TableDAO {
	List<Table> findAllTables() throws DataAccessException;

	Table findByTableNo(int tableNo) throws DataAccessException;
	
	void updateTableStatus(Table table) throws DataAccessException;
}
