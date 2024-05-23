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
		if (wine != null) {
			saleProduct.setWine(wine);
		} else if (food != null) {
			List<Ingredients> ingredients = spctrl.findIngredientsByFoodID(saleProductID);
			food.setIngredients(ingredients); // Set the ingredients list for the Food object
			saleProduct.setFood(food);
		}
		System.out.println(saleProduct.getWine());
		System.out.println(saleProduct.getFood());

	}

	public void saveOrder() throws DataAccessException {
		soDao.saveOrder(saleOrder);
		updateInventory();
		updateTableStatus();
	}

	public void updateTableStatus() throws DataAccessException {
		Table t = new Table(false, saleOrder.getTableNo());
		tc.updateTableStatus(t);
	}

	public void updateInventory() throws DataAccessException {

		for (int i = 0; i < saleOrder.getOl().size(); i++) {
			if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("wine")) {
				Wine wine = saleOrder.getOl().get(i).getSaleProduct().getWine();

				int productID = spctrl
						.findProductIDOnWine(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());

				Product p = pctrl.findByProductID(productID);
				int inventoryID = pctrl.findInventoryIDByBarcode(p.getBarcode());
				Inventory inventory = ictrl.findByInventoryNo(inventoryID);
				inventory.setQuantity(inventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
				ictrl.updateProductQuantity(inventory);

				// NYT
			} else if (saleOrder.getOl().get(i).getSaleProduct().getType().equalsIgnoreCase("food")) {
				Food food = saleOrder.getOl().get(i).getSaleProduct().getFood();
				List<Ingredients> ingredients = food.getIngredients();
				for (Ingredients ingredient : ingredients) {
					int ingredientProductID = spctrl
							.findProductIDOnIngredient(saleOrder.getOl().get(i).getSaleProduct().getSaleProductID());
					Product ingredientProduct = pctrl.findByProductID(ingredientProductID);
					int ingredientInventoryID = pctrl.findInventoryIDByBarcode(ingredientProduct.getBarcode());
					Inventory ingredientInventory = ictrl.findByInventoryNo(ingredientInventoryID);
					ingredientInventory
							.setQuantity(ingredientInventory.getQuantity() - saleOrder.getOl().get(i).getQuantity());
					ictrl.updateProductQuantity(ingredientInventory);
				}

			}
		}
	}

	public void checkTable(int tableNo) throws DataAccessException {
		tc.checkTable(tableNo);
	}

}
