package db;

import java.util.List;

import model.Table;


public interface TableDAO {
	List<Table> findAll() throws DataAccessException;
	Table findByTableNo(int tableNo) throws DataAccessException;
	void saveTableStatus(Table table) throws DataAccessException;
}