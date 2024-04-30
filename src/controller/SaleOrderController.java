package controller;

import db.DataAccessException;
import db.SaleOrderDB;
import model.SaleOrder;

public class SaleOrderController {
	private EmployeeController ectrl;
	private ProductController pctrl;
	private SaleOrder saleOrder;
	private SaleOrderDB sodb;

	public SaleOrderController(){
		try {
			sodb = new SaleOrderDB();
			ectrl = new EmployeeController();
			pctrl = new ProductController();
			saleOrder = this.saleOrder;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SaleOrder createSaleOrder(int employeeNo) throws DataAccessException {
	}
	
	//Ikke bare void?
	public Product addProduct(String barcode) throws DataAccessException {
	}
	
	public void saveOrder() throws DataAccessException {
	}
	
	private void updateInventory(int id) {
		
	}
}
