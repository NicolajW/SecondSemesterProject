package controller;

import db.DataAccessException;

import db.PersonDB;
import db.SaleOrderDB;
import model.OrderLine;
import model.Person;
import model.SaleOrder;
import model.Table;
import model.SaleProduct;

public class SaleOrderController {
	private PersonController perctrl;
	private SaleProductController spctrl;
	private ProductController pctrl;
	private SaleOrderDB sodb;
	private SaleOrder saleOrder;
	private TableController tc;
	private InventoryController ictrl;

	public SaleOrderController() {
		try {

			sodb = new SaleOrderDB();
			perctrl = new PersonController();
			spctrl = new SaleProductController();
			saleOrder = this.saleOrder;
			tc = new TableController();
			ictrl = new InventoryController();

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public SaleOrder createSaleOrder(String email, int tableNo) throws DataAccessException {
		PersonDB persdb = new PersonDB();
		Person employee = perctrl.findByEmployeeNo(email);
		checkTable(tableNo);
		saleOrder = new SaleOrder(0, 0d, employee, tableNo);
		return saleOrder;

	}

	public void addProduct(double quantity, int productId) throws DataAccessException {
		SaleProduct product = spctrl.findByProductById(productId);
		OrderLine orderLine = new OrderLine(quantity, product, saleOrder);
		saleOrder.saleOrderLinesHashMap(orderLine);
		System.out.println(saleOrder);

	}

	public void saveOrder() throws DataAccessException {
		sodb.saveOrder(saleOrder);
		// updateInventory(saleOrder.getOrderNo());
		updateTableStatus();
	}

	public void updateTableStatus() throws DataAccessException {
		Table t = new Table(false, saleOrder.getTableNo());
		tc.updateTableStatus(t);
	}

	public void updateInventory(int id) throws DataAccessException {
		pctrl.findByProductID(id);
		
	}

	public void checkTable(int tableNo) throws DataAccessException {
		tc.checkTable(tableNo);
	}

}
