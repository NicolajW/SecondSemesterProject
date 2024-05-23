package controller;

import db.DataAccessException;
import db.TableDAO;
import db.TableDB;
import model.Table;

public class TableController {
	private TableDAO tDao;
	
	
	
	
	public TableController() throws DataAccessException{
		tDao = new TableDB();
	}
	
	public void updateTableStatus(Table table) throws DataAccessException {
		tDao.updateTableStatus(table);
	}
	
	public void findAllTables() throws DataAccessException {
		tDao.findAllTables();
	}
	
	public void findByTableNo(int tableNo) throws DataAccessException {
		tDao.findByTableNo(tableNo);
	}
	
	public void checkTable(int tableNo) throws DataAccessException {
		TableDB tadb = new TableDB();
		Table table = tadb.findByTableNo(tableNo);
	    if (table.isTableStatus() != true) {
	        table.setTableStatus(true);
	        tadb.updateTableStatus(table);
	        
	    } else {
	        throw new DataAccessException("Table:" + tableNo + " not available: ", null);
	    }
	}
}
