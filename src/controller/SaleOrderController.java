package controller;

import java.util.Iterator;

import db.DataAccessException;

import db.PersonDB;
import db.SaleOrderDB;
import model.Food;
import model.Ingredients;
import model.Inventory;
import model.OrderLine;
import model.Person;
import model.Product;
import model.SaleOrder;
import model.Table;
import model.Wine;
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
			pctrl = new ProductController();

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

	public void addProduct(double quantity, int saleProductID) throws DataAccessException {
		SaleProduct saleProduct = spctrl.findByProductById(saleProductID);
		OrderLine orderLine = new OrderLine(quantity, saleProduct, saleOrder);
		saleOrder.saleOrderLinesHashMap(orderLine);
		
		
		Wine wine = spctrl.findWineOnSaleProductID(saleProductID);
		Food food = spctrl.findFoodOnSaleProductID(saleProductID);
		if(wine != null) {
			saleProduct.setWine(wine);			
		}else {
			saleProduct.setFood(food);
			
		}
		System.out.println(saleProduct.getWine());
		System.out.println(saleProduct.getFood());

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

	public void updateInventory() throws DataAccessException {
		
		//GET PRODUCT FROM ORDERLINE
		//GET INVENTORYID FROM PRODUCT OBJECT
		//SEARCH AND GET PRODUCT FROM BARCODE WITH PRODUCT OBJECTS BARCODE
		//GET INVENTORY ID FROM PRODUCT DB
		//FIND INVENTORY BY ID
		//CALL UPDATE INVENTORY BY INVENTORY OBJEC
		
		//WINE AND FOOD IF STATEMENT
		//OBJECT WINE AND FOOD ON SALEPRODUCT
		//ADD WINE AND FOOD ON SALE PRODUCT
		//FIND FUCKING PRODUCT ID ON WINE/FOOD

		for(int i = 0; i < saleOrder.getOl().size(); i++) {
			if(saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("wine")) {
				Wine wine = saleOrder.getOl().get(i).getSaleProduct().getWine();
				
				int productID = spctrl.findProductIDOnWine(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
				
				Product p = pctrl.findByProductID(productID);
				int inventoryID = pctrl.findInventoryIDByBarcode(p.getBarcode());
				Inventory inventory = ictrl.findByInventoryNo(inventoryID);
				inventory.setQuantity(inventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
				ictrl.updateProductQuantity(inventory);
			}else {
				
			}
				
				
				
//			Product p = saleOrder.getOl().get(i).getSaleProduct().getProduct();
//			//p = pctrl.findProductByBarcode(p.getBarcode());
//			int inventoryID = pctrl.findInventoryIDByBarcode(p.getBarcode());
//			Inventory inventory = ictrl.findByInventoryNo(inventoryID);
//			inventory.setQuantity(inventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
//			ictrl.updateProductQuantity(inventory);
		}
		
		
		//Product p = pctrl.findInventoryByID();
		
		//ictrl.updateProductQuantity(null);
		
		
		
		
	}

	public void checkTable(int tableNo) throws DataAccessException {
		tc.checkTable(tableNo);
	}

}
