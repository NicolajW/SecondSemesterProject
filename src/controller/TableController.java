package controller;

import java.util.List;

import db.DataAccessException;
import db.TableDAO;
import db.TableDB;
import model.Table;

public class TableController {
	private TableDAO tDao;
	
	public TableController(){
		try {
			tDao = new TableDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTableStatus(Table table) throws DataAccessException {
		tDao.updateTableStatus(table);
	}
	
	public List<Table> findAllTables() throws DataAccessException {
		return tDao.findAllTables();
	}
	
	public void findByTableNo(int tableNo) throws DataAccessException {
		tDao.findByTableNo(tableNo);
	}
	/**
	 * This method checks the table by its <code>tableNo</code>. 
	 * It creates a new <code>TableDB</code> object. 
	 * It then uses an <code>if</code> to determines whether or not the table is available, 
	 * and if it isnt then it sets the <code>tableStatus</code> to true and updates the tableStatus
	 * @param tableNo
	 * @throws DataAccessException
	 */
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
