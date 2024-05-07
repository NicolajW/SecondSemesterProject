package controller;

import db.DataAccessException;
import db.SaleOrderDB;
import model.SaleOrder;
import model.SaleProduct;

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
		return saleOrder;
	}
	
	//Ikke bare void?
	public SaleProduct addProduct(String barcode) throws DataAccessException {
		return null;
	}
	
	public void saveOrder() throws DataAccessException {
	}
	
	private void updateInventory(int id) {
		
	}
}
