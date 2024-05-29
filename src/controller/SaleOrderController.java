package controller;

import java.util.List;

import db.DataAccessException;

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
	
	//Instantiates a new saleOrderController creating new controllers for saleOrderDB, PersonController, SaleProductController, 
	//TableController, InventoryController, and ProductController, to be able to create a sale
	public SaleOrderController() {
		try {
			soDao = new SaleOrderDB();
			perctrl = new PersonController();
			spctrl = new SaleProductController();
			tc = new TableController();
			ictrl = new InventoryController();
			pctrl = new ProductController();

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates the saleOrder of type <code>SaleOrder</code>. 
	 * It creates a saleOrder by getting a tableNo and <code>employee</code> email. 
	 * It then checks the <code>tableNo</code> so it knows which table it is and then creates, 
	 * a new <code>SaleOrder</code> object, with its parameters, including the tableNo and email, from the employee. 
	 * @param email
	 * @param table
	 * @return saleOrder that was created
	 * @throws DataAccessException
	 */
	public SaleOrder createSaleOrder(String email, Table table) throws DataAccessException {
		int tableNo = table.getTableNo();
		Person employee = findByPersonEmail(email);
		checkTable(tableNo);
		saleOrder = new SaleOrder(0, 0d, employee, table);
		return saleOrder;

	}

	/**
	 * This method adds a products with its quantity, and saleProductID. 
	 * It creates a <code>SaleProduct</code> object, with the productId found.
	 * It then creates an <code>OrderLine</code> object, using a new OrderLine with <code>quantity, saleProduct, saleOrder</code> as its parameters
	 * Here it then have 2 types of products, which is a <code>Wine</code> object, and a <code>Food</code> object. 
	 * Then it uses an <code>if</code> statement for firstly wine, and else food, where it set the wine or food to the <code>SaleProduct</code>. 
	 * @param quantity
	 * @param saleProductID
	 * @throws DataAccessException
	 */
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

	/**
	 * This method saves the order, by making the price, saving it with <code>soDao</code> 
	 * and updates the inventory and table with the given order saved in the dataBase
	 * @return saleOrder saved in the inventory and table. 
	 * @throws DataAccessException
	 */
	public SaleOrder saveOrder() throws DataAccessException {
		saleOrder.setTotalPrice(getTotalPrice());
		soDao.saveOrder(saleOrder);
		updateInventory();
		updateTableForSaveOrder();
		return saleOrder;
	}

	/**
	 * This method updates the table for the saved order. 
	 * @throws DataAccessException
	 */
	public void updateTableForSaveOrder() throws DataAccessException {
		Table t = new Table(false, saleOrder.getTable().getTableNo());
		updateTableStatus(t);
	}

	/**
	 * This method updates the wine in the inventory
	 * It uses a <code>for</code> loop, to get the saleOrder from the OrderLines. 
	 * the <code>productID</code>  is used by finding the productID on the wine, and gets the saleProduct from the <code>saleOrder</code> saved in the OrderLine
	 * Then it has a <code>Product</code> p object, that has a productID. 
	 * it updates the inventoryID and sets the quantity of the inventory, 
	 * and sets the <code>saleOrder</code> and gets the index and quantity, saved in the OrderLine. 
	 * @throws DataAccessException
	 */
	private void updateWineInventory() throws DataAccessException {
		for (int i = 0; i > saleOrder.getOl().size(); i++) {
			int productID = findProductIDOnWine(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
			Product p = findByProductID(productID);

			int inventoryID = findInventoryIDByBarcode(p.getBarcode());
			Inventory inventory = findByInventoryNo(inventoryID);

			inventory.setQuantity(inventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
			updateProductQuantity(inventory);
		}
	}

	/**
	 * This method updates the foodInventory. 
	 * @throws DataAccessException
	 */
	private void updateFoodInventory() throws DataAccessException {
		for (int i = 0; i > saleOrder.getOl().size(); i++) {
			
			int ingredientProductID = findProductIDOnIngredient(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
			Product ingredientProduct = findByProductID(ingredientProductID);
				
			int ingredientInventoryID = findInventoryIDByBarcode(ingredientProduct.getBarcode());
			Inventory ingredientInventory = findByInventoryNo(ingredientInventoryID);
				
			ingredientInventory.setQuantity(ingredientInventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
			updateProductQuantity(ingredientInventory);
		}
	}

	/**
	 * This method updates the Inventory. 
	 * @throws DataAccessException
	 */
	public void updateInventory() throws DataAccessException {
		for (int i = 0; i < saleOrder.getOl().size(); i++) {
			if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("wine")) {
				updateWineInventory();
			} else if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("food")) {
				updateFoodInventory();				
				}
			}
		}
	
	public double getTotalPrice() {
		return saleOrder.getTotalPrice();
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
