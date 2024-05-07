package controller;

import db.DataAccessException;
import db.PersonDB;
import db.SaleOrderDB;
import model.Person;
import model.SaleOrder;
import model.Table;
import model.saleProduct;
import db.TableDB;

public class SaleOrderController {
	private PersonController perctrl;
	private ProductController proctrl;
	private SaleOrder saleOrder;
	private SaleOrderDB sodb;
	private TableDB tadb;

	public SaleOrderController(){ 
		try {
			sodb = new SaleOrderDB();
			perctrl = new PersonController();
			proctrl = new ProductController();
			saleOrder = this.saleOrder;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SaleOrder createSaleOrder(int employeeNo, int tableNo) throws DataAccessException {
		PersonDB persdb = new PersonDB();
		Person employee = perctrl.findByEmployeeNo(employeeNo);
		checkTable(tableNo);
		saleOrder = new SaleOrder(0, 0d, employee, tableNo);
		return saleOrder;
	}
	
	public void addProduct(String barcode) throws DataAccessException {
		
		
	}
	
	public void saveOrder() throws DataAccessException {
		
	}
	
	private void updateInventory(int id) {
		
	}
	
	private void checkTable(int tableNo) throws DataAccessException {
		TableDB tadb = new TableDB();
		Table table = tadb.findByTableNo(tableNo);
	    if (table.isTableStatus() != true) {
	        table.setTableStatus(true);
	        tadb.saveTableStatus(table);
	        
	    } else {
	        throw new DataAccessException("Table:" + tableNo + " not available: ", null);
	    }
		
		
	}
	
}
