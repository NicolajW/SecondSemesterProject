package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Batch;
import model.Food;
import model.Ingredients;
import model.Product;
import model.Wine;

public class ProductDB implements ProductDAO {
	private static final String JOIN_ALL_Q = "SELECT * FROM Product"
			+ " FULL OUTER JOIN Batch ON productID = proID_PKFK"
			+ " FULL OUTER JOIN Ingredients ON productID = productID_PKFK";
	private static final String FIND_BY_PRODUCT_ID_Q = JOIN_ALL_Q + " WHERE productID= ?";
	private static final String FIND_BY_PRODUCT_BARCODE_Q= JOIN_ALL_Q + " WHERE barcode= ?";
	private static final String FIND_INGREDIENTS_BY_FOOD_Q = "SELECT name, typeOfFood, type FROM Ingredients WHERE saleProductID_PKFK = ?";
	private static final String FIND_PRODUCT_ID_ON_INGREDIENT_Q = "SELECT productID_PKFK FROM Ingredients WHERE saleProductID_PKFK = ?";
	
	private PreparedStatement findByProductIDPS;
	private PreparedStatement joinAllPS;
	private PreparedStatement findProductByBarcodePS;
	private PreparedStatement findProductIDOnIngredientPS;
	private PreparedStatement findIngredientsByFoodPS;

	public ProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByProductIDPS = con.prepareStatement(FIND_BY_PRODUCT_ID_Q);
			findProductByBarcodePS = con.prepareStatement(FIND_BY_PRODUCT_BARCODE_Q);
			joinAllPS = con.prepareStatement(JOIN_ALL_Q);
			findIngredientsByFoodPS = con.prepareStatement(FIND_INGREDIENTS_BY_FOOD_Q);
			findProductIDOnIngredientPS = con.prepareStatement(FIND_PRODUCT_ID_ON_INGREDIENT_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
	}
	
	// NYT-------------------------------------------
	/**
	 * This method creates a List of type <code>Ingredients</code> using <code>ArrayList</code>
	 * it uses <code>findIngredientsByFoodPS</code> to find the ingredients for the food, and gets the id of the type food
	 * a <code>while</code> loop used to create new <code>Ingredients</code> and then execute the query
	 * @param foodID
	 * @return ingredients
	 * @throws DataAccessException
	 */
		@Override
		public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException {
			List<Ingredients> ingredients = new ArrayList<>();
			try {
				findIngredientsByFoodPS.setInt(1, foodID);
				ResultSet rs = findIngredientsByFoodPS.executeQuery();
				while (rs.next()) {
					Ingredients ingredient = new Ingredients(rs.getString("name"), rs.getString("typeOfFood"), null, null,
							rs.getString("type"));
					ingredients.add(ingredient);
				}
			} catch (SQLException e) {
				throw new DataAccessException("Could not find ingredients for food ID = " + foodID, e);
			}
			return ingredients;
		}
		
		// NYT-------------------------------------------
		/**
		 * This method finds product id on ingredients and uses the <code>saleProductID</code>
		 * It makes a ResultSet with the <code>saleProductID</code> on ingredients and executes the query
		 * @param saleProductID
		 * @return res
		 * @throws DataAccessException
		 */
		@Override
		public int findProductIDOnIngredient(int saleProductID) throws DataAccessException {
			int res = 0;
			try {
				findProductIDOnIngredientPS.setInt(1, saleProductID);
				ResultSet rs = findProductIDOnIngredientPS.executeQuery();
				if (rs.next()) {
					res = rs.getInt("productID_PKFK");
				}
			} catch (SQLException e) {
				throw new DataAccessException("Could not find product ID for ingredient ID = " + saleProductID, e);
			}
			return res;
		}

		/**
		 * This method creates an object of type <code>Product</code>
		 * it starts with creating a Product p, and then and <code>if</code> statement to create a new Batch for the Product p
		 * with the relevant information for the Product needed to create the object so we can see the type of product and the information of the product
		 * @param rs
		 * @return p
		 * @throws SQLException
		 */
	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = null;
		String type = rs.getString("type");
		if (type.equals("batch")) {
			p = new Batch(rs.getString("barcode"),
					new Wine(rs.getInt("saleProductID_PKFK"), null, null, null, null, null, null, null, null),
					rs.getInt("numberOfBatches"), 
					rs.getString("type")
					);
		} else if (type.equals("ingredients")) {
			p = new Ingredients(rs.getString("name"), rs.getString("typeOfFood"),
					new Food(rs.getInt("saleProductID_PKFK"), null, 0.0, null, null, null), 
					rs.getString("barcode"),
					rs.getString("type")
					);
		}
		return p;
	}

	/**
	 * This method create objects with a <code>List</code> of Product using <code>ArrayList</code>
	 * a <code>while</code> loop to build the object from the <code>buildObject</code> method
	 * and then adds <code>Product</code> p
	 * @param rs
	 * @return res
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private List<Product> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Product> res = new ArrayList<>();
		while (rs.next()) {
			Product p = buildObject(rs);
			res.add(p);
		}
		return res;
	}

	@Override
	/**
	 * This method finds all Products from the <code>List</code>
	 * Then it joins all in the ResultSet and executes the query
	 * @return res
	 * @throws SQLException
	 */
	public List<Product> findAll() throws DataAccessException {
		try {
			ResultSet rs = joinAllPS.executeQuery();
			List<Product> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all", e);
		}
	}

	@Override
	public int findInventoryIDByBarcode(String barcode) throws DataAccessException {
		int res = 0;
		try {
			findProductByBarcodePS.setString(1, barcode);
			ResultSet rs = findProductByBarcodePS.executeQuery();
			if (rs.next()) {
				res = rs.getInt("InventoryID_FK");
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by barcode = " + barcode, e);
		}
		return res;
	}

	@Override
	public Product findByProductID(int productID) throws DataAccessException {
		Product res = null;
		try {
			findByProductIDPS.setInt(1, productID);
			ResultSet rs = findByProductIDPS.executeQuery();
			if (rs.next()) {
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id = " + productID, e);
		}
		return res;
	}

}
