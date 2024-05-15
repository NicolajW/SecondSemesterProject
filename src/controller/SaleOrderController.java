package controller;

import db.DataAccessException;
import db.OrderLineDB;
import db.PersonDB;
import db.SaleOrderDB;
import model.OrderLine;
import model.Person;
import model.SaleOrder;
import model.Table;
import model.SaleProduct;
import db.TableDB;

public class SaleOrderController {
	private PersonController perctrl;
	private ProductController proctrl;
	private SaleOrderDB sodb;
	private TableDB tadb;
	private SaleOrder saleOrder;
	private OrderLineDB oldb;

	public SaleOrderController(){ 
		try {
			oldb = new OrderLineDB();
			sodb = new SaleOrderDB();
			perctrl = new PersonController();
			proctrl = new ProductController();
			saleOrder = this.saleOrder;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
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
		
//		OrderLine ol = new OrderLine(0.0, null, null);
//		ol.setSaleOrder(saleOrder);
//		ol.setSaleProduct(proctrl.findByProductById(productId));
//		ol.setQuantity(quantity);
//		saleOrder.addOrderLine(ol);
		
	    SaleProduct product = proctrl.findByProductById(productId);
	    OrderLine orderLine = new OrderLine(quantity, product, saleOrder);
	    saleOrder.saleOrderLinesHashMap(orderLine);
	    System.out.println(saleOrder);
		
	}
	
//	public void mapAllProducts() throws DataAccessException {
//		proctrl.getAllProductsAsMap();
//	}
//	
//	public SaleProduct findMapProduct(int saleProductID) throws DataAccessException {
//		return proctrl.findByMapProductID(saleProductID);
//	}
	
	public void saveOrder() throws DataAccessException {
		sodb.saveOrder(saleOrder);
		//updateInventory(saleOrder.getOrderNo());
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
