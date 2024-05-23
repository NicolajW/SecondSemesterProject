package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.SaleProduct;
import model.Food;
import model.Ingredients;
import model.Wine;

public class SaleProductDB implements SaleProductDAO {

	private Wine w;
	private Food f;

	private static final String FIND_ALL_Q = "select saleProductID, name, price, description, type from saleProduct";
	private static final String FIND_ALL_Q_WINE = "select grapeType, yearProduced, wineHouse, region, productID_PKFK from Wine";
	private static final String FIND_ALL_Q_FOOD = "select food_id, menuName, type from Food";
    private static final String JOIN_ALL_Q = "SELECT * FROM saleProduct"
    		+ " FULL OUTER JOIN wine ON saleProductID = saleProductID_PKFK"
    		+ " FULL OUTER JOIN food ON saleProductID = food_id";
	private static final String FIND_BY_Q = JOIN_ALL_Q + " WHERE saleProductID= ?"; 
	private static final String FIND_BY_Q_WINE = JOIN_ALL_Q + " WHERE saleProductID_PKFK = ?"; 
	private static final String FIND_BY_Q_FOOD = JOIN_ALL_Q + " WHERE food_id = ?"; 
	
	//NYT-------------------------------------------
	private static final String FIND_PRODUCT_ID_ON_INGREDIENT_Q = "SELECT productID_PKFK FROM Ingredients WHERE saleProductID_PKFK = ?";
	
	private static final String INSERT_INTO_SALEPRODUCT_Q = "insert into saleProduct (name, price, description, type) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_WINE_Q = "insert into Wine (grapeType, yearProduced, wineHouse, region) values (?, ?, ?, ?);";
	
	//NYT-------------------------------------------
	private static final String FIND_INGREDIENTS_BY_FOOD_Q = "SELECT name, typeOfFood, type FROM Ingredients WHERE saleProductID_PKFK = ?";
	
	private static final String INSERT_INTO_FOOD_Q = "insert into Food (menuName) values (?);";

	private PreparedStatement findAllPSS;
	private PreparedStatement findByProductIDPS;
	private PreparedStatement insertInSP, insertInW, insertInF;
	private PreparedStatement findByProductIDPKFKPS;
	private PreparedStatement findByFoodIDPS;
	private PreparedStatement findProductIDOnIngredientPS;
	private PreparedStatement findIngredientsByFoodPS;
	

	public SaleProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByProductIDPKFKPS = con.prepareStatement(FIND_BY_Q_WINE);
			findByFoodIDPS = con.prepareStatement(FIND_BY_Q_FOOD);
			findAllPSS = con.prepareStatement(FIND_ALL_Q);
			findByProductIDPS = con.prepareStatement(FIND_BY_Q);
			insertInSP = con.prepareStatement(INSERT_INTO_SALEPRODUCT_Q);
			insertInW = con.prepareStatement(INSERT_INTO_WINE_Q); 
			insertInF = con.prepareStatement(INSERT_INTO_FOOD_Q);
			
			//NYT-------------------------------------------
			findIngredientsByFoodPS = con.prepareStatement(FIND_INGREDIENTS_BY_FOOD_Q);
			findProductIDOnIngredientPS = con.prepareStatement(FIND_PRODUCT_ID_ON_INGREDIENT_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
	}
	
	//NYT-------------------------------------------
	@Override
	public List<Ingredients> findIngredientsByFoodID(int foodID) throws DataAccessException {
	    List<Ingredients> ingredients = new ArrayList<>();
	    try {
	        findIngredientsByFoodPS.setInt(1, foodID);
	        ResultSet rs = findIngredientsByFoodPS.executeQuery();
	        while (rs.next()) {
	            Ingredients ingredient = new Ingredients(
	                rs.getString("name"),
	                rs.getString("typeOfFood"),
	                null,
	                null,
	                rs.getString("type")
	            );
	            ingredients.add(ingredient);
	        }
	    } catch (SQLException e) {
	        throw new DataAccessException("Could not find ingredients for food ID = " + foodID, e);
	    }
	    return ingredients;
	}

	@Override
	public List<SaleProduct> findAll() throws DataAccessException {
		try {
			ResultSet rs = findAllPSS.executeQuery();
			List<SaleProduct> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all saleProducts", e);
		}
	}
	
	//NYT-------------------------------------------
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
	
	@Override
	public int findProductIDOnWine(int saleProductID) throws DataAccessException {
		int res = 0;
		try {
			findByProductIDPKFKPS.setInt(1, saleProductID);
			ResultSet rs = findByProductIDPKFKPS.executeQuery();
			if (rs.next()) {
				res = rs.getInt("productID_PKFK");
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id = " + saleProductID, e);
		}
		return res;
	}
	
	@Override
	public Wine findWineOnSaleProductID(int saleProductID) throws DataAccessException {
		Wine res = null;
		try {
			findByProductIDPKFKPS.setInt(1, saleProductID);
			ResultSet rs = findByProductIDPKFKPS.executeQuery();
			if (rs.next()) {
					res = (Wine) buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id = " + saleProductID, e);
		}
		return res;
	}
	
	@Override
	public Food findFoodOnSaleProductID(int saleProductID) throws DataAccessException {
		Food res = null;
		try {
			findByFoodIDPS.setInt(1, saleProductID);
			ResultSet rs = findByFoodIDPS.executeQuery();
			if (rs.next()) {
					res = (Food) buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id = " + saleProductID, e);
		}
		return res;
	}

	@Override
	public SaleProduct findByProductById(int saleProductID) throws DataAccessException {
		SaleProduct res = null;
		try {
			findByProductIDPS.setInt(1, saleProductID);
			ResultSet rs = findByProductIDPS.executeQuery();
			if (rs.next()) {
					res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id = " + saleProductID, e);
		}
		return res;
	}

	private SaleProduct buildObject(ResultSet rs) throws SQLException {
		SaleProduct sp = null;
		String type = rs.getString("type");
		if (type.equals("wine")) {
			sp = new Wine(
					rs.getInt("saleProductID"), 
					rs.getString("name"), 
					rs.getDouble("price"), 
					rs.getString("description"),
					rs.getString("type"), 
					rs.getString("grapeType"), 
					rs.getString("yearProduced"),
					rs.getString("wineHouse"), 
					rs.getString("region")
					);
		} else if (type.equals("food")) {
			sp = new Food(
					rs.getInt("saleProductID"), 
					rs.getString("name"), 
					rs.getDouble("price"), 
					rs.getString("description"),
					rs.getString("type"), 
					rs.getString("menuName"));
		}
		return sp;
	}

	public List<SaleProduct> buildObjects(ResultSet rs) throws SQLException {
		List<SaleProduct> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	public void saveSaleProduct(SaleProduct sp) throws DataAccessException {
		ResultSet rs = null;
		final String name = sp.getName();
		final Double price = sp.getPrice();
		final String description = sp.getDescription();
		final String type = sp.getType();
		final String grapeType = w.getGrapeType();
		final String yearProduced = w.getYearProduced();
		final String wineHouse = w.getWineHouse();
		final String region = w.getRegion();
		final String menuName = f.getMenuName();
		try {
			insertInSP.setString(1, name);
			insertInSP.setDouble(2, price);
			insertInSP.setString(3, description);
			insertInSP.setString(4, type);
			insertInSP.executeUpdate();

		} catch (SQLException e) {
			throw new DataAccessException("Could not save product", e);
		}
		if (type.equals("Wine")) {
			try {
				insertInW.setString(1, grapeType);
				insertInW.setString(2, yearProduced);
				insertInW.setString(3, wineHouse);
				insertInW.setString(4, region);
			} catch (SQLException e) {
				throw new DataAccessException("Could not build Wine", e);
			}

		} else if (type.equals("Food")) {
			try {
				insertInF.setString(1, menuName);
			} catch (SQLException e) {
				throw new DataAccessException("Could not build Food", e);
			}
		}
	}
}
