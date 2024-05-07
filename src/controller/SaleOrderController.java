package controller;

import db.DataAccessException;
import db.SaleOrderDB;
import model.Employee;
import model.SaleOrder;
import model.Table;
import model.saleProduct;
import db.TableDB;

public class SaleOrderController {
	private PersonController ectrl;
	private ProductController pctrl;
	private SaleOrder saleOrder;
	private SaleOrderDB sodb;
	private TableDB tadb;

	public SaleOrderController(){ 
		try {
			sodb = new SaleOrderDB();
			ectrl = new PersonController();
			pctrl = new ProductController();
			saleOrder = this.saleOrder;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SaleOrder createSaleOrder(int employeeNo, int tableNo) throws DataAccessException {
		checkTable(tableNo);
		Employee employee = ectrl.findByEmployeeNo(employeeNo);
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
		Table table = tadb.findByTableNo(tableNo);
	    if (table.isTableStatus() != true) {
	        table.setTableStatus(true);
	        tadb.saveTableStatus(table);
	        
	    } else {
	        throw new DataAccessException("Table:" + tableNo + " not available: ", null);
	    }
		
		
	}
	
}
