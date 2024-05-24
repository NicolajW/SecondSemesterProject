package controller;

import java.util.Iterator;
import java.util.List;

import db.DataAccessException;

import db.PersonDB;
import db.SaleOrderDAO;
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
	private SaleOrderDAO soDao;
	private SaleOrder saleOrder;
	private TableController tc;
	private InventoryController ictrl;

	public SaleOrderController() {
		try {
			soDao = new SaleOrderDB();
			perctrl = new PersonController();
			spctrl = new SaleProductController();
			this.saleOrder = saleOrder;
			tc = new TableController();
			ictrl = new InventoryController();
			pctrl = new ProductController();

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public SaleOrder createSaleOrder(String email, int tableNo) throws DataAccessException {
		PersonDB persdb = new PersonDB();
		Person employee = findByPersonEmail(email);
		checkTable(tableNo);
		saleOrder = new SaleOrder(0, 0d, employee, tableNo);
		return saleOrder;

	}

	public void addProduct(double quantity, int saleProductID) throws DataAccessException {
		SaleProduct saleProduct = findByProductById(saleProductID);
		OrderLine orderLine = new OrderLine(quantity, saleProduct, saleOrder);
		saleOrder.saleOrderLinesHashMap(orderLine);

		Wine wine = findWineOnSaleProductID(saleProductID);
		Food food = findFoodOnSaleProductID(saleProductID);
		if (wine != null) {
			saleProduct.setWine(wine);
		} else if (food != null) {
			List<Ingredients> ingredients = findIngredientsByFoodID(saleProductID);
			food.setIngredients(ingredients); 
			saleProduct.setFood(food);
		}
	}

	public void saveOrder() throws DataAccessException {
		soDao.saveOrder(saleOrder);
		updateInventory();
		updateTableForSaveOrder();
	}

	public void updateTableForSaveOrder() throws DataAccessException {
		Table t = new Table(false, saleOrder.getTableNo());
		updateTableStatus(t);
	}

	private void updateWineInventory() throws DataAccessException {
		for (int i = 0; i < saleOrder.getOl().size(); i++) {
			int productID = findProductIDOnWine(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
			Product p = findByProductID(productID);

			int inventoryID = findInventoryIDByBarcode(p.getBarcode());
			Inventory inventory = findByInventoryNo(inventoryID);

			inventory.setQuantity(inventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
			updateProductQuantity(inventory);
		}
	}

	private void updateFoodInventory() throws DataAccessException {
		for (int i = 0; i < saleOrder.getOl().size(); i++) {
			
			int ingredientProductID = findProductIDOnIngredient(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
			Product ingredientProduct = findByProductID(ingredientProductID);
				
			int ingredientInventoryID = findInventoryIDByBarcode(ingredientProduct.getBarcode());
			Inventory ingredientInventory = findByInventoryNo(ingredientInventoryID);
				
			ingredientInventory.setQuantity(ingredientInventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
			updateProductQuantity(ingredientInventory);
		}
	}

	public void updateInventory() throws DataAccessException {
		for (int i = 0; i < saleOrder.getOl().size(); i++) {
			if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("wine")) {
				updateWineInventory();
			} else if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("food")) {
				updateFoodInventory();				
				}
			}
		}
	
	
	public void updateTableStatus(Table table) throws DataAccessException {
		tc.updateTableStatus(table);
	}
	
	public Person findByPersonEmail(String email) throws DataAccessException{
		return perctrl.findByPersonEmail(email);
	}
	
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		ictrl.updateProductQuantity(inventory);
	}
	
	public Inventory findByInventoryNo(int InventoryNo) throws DataAccessException {
		return ictrl.findByInventoryNo(InventoryNo);
	}
	
	public int findInventoryIDByBarcode(String barcode) throws DataAccessException {
		return pctrl.findInventoryIDByBarcode(barcode);
	}
	
	public Product findByProductID(int productID) throws DataAccessException {
		return pctrl.findByProductID(productID);
	}
	
	public int findProductIDOnIngredient(int saleProductID) throws DataAccessException {
		return pctrl.findProductIDOnIngredient(saleProductID);
	}
	public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException{
		return pctrl.findIngredientsByFoodID(foodID);
	}
	
	public Wine findWineOnSaleProductID(int saleProductID) throws DataAccessException {
		return spctrl.findWineOnSaleProductID(saleProductID);
	}
	
	public int findProductIDOnWine(int saleProductID) throws DataAccessException{
		return spctrl.findProductIDOnWine(saleProductID);
	}
	
	public Food findFoodOnSaleProductID(int saleProductID) throws DataAccessException{
		return spctrl.findFoodOnSaleProductID(saleProductID);
	}
	
	public SaleProduct findByProductById(int id) throws DataAccessException {
		return spctrl.findByProductById(id);
	}
	

	public void checkTable(int tableNo) throws DataAccessException {
		tc.checkTable(tableNo);
	}
	
	public void findAllTables() throws DataAccessException {
		tc.findAllTables();
	}

}
