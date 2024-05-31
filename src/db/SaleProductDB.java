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
	private static final String JOIN_ALL_Q = "SELECT * FROM saleProduct"
			+ " FULL OUTER JOIN wine ON saleProductID = saleProductID_PKFK"
			+ " FULL OUTER JOIN food ON saleProductID = food_id";
	private static final String FIND_BY_Q = JOIN_ALL_Q + " WHERE saleProductID= ?";
	private static final String FIND_BY_Q_WINE = JOIN_ALL_Q + " WHERE saleProductID_PKFK = ?";
	private static final String FIND_BY_Q_FOOD = JOIN_ALL_Q + " WHERE food_id = ?";

	private static final String INSERT_INTO_SALEPRODUCT_Q = "insert into saleProduct (name, price, description, type) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_WINE_Q = "insert into Wine (grapeType, yearProduced, wineHouse, region) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_FOOD_Q = "insert into Food (menuName) values (?);";



	private PreparedStatement findAllPSS;
	private PreparedStatement findByProductIDPS;
	private PreparedStatement insertInSP, insertInW, insertInF;
	private PreparedStatement findByProductIDPKFKPS;
	private PreparedStatement findByFoodIDPS;

	//Instantiates SaleProductDB
	public SaleProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByProductIDPKFKPS = con.prepareStatement(FIND_BY_Q_WINE);
			findByFoodIDPS = con.prepareStatement(FIND_BY_Q_FOOD);
			findAllPSS = con.prepareStatement(JOIN_ALL_Q);
			findByProductIDPS = con.prepareStatement(FIND_BY_Q);
			insertInSP = con.prepareStatement(INSERT_INTO_SALEPRODUCT_Q);
			insertInW = con.prepareStatement(INSERT_INTO_WINE_Q);
			insertInF = con.prepareStatement(INSERT_INTO_FOOD_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
	}

	
	/**
	 * Here it finds all sale products, with a <code>List</code>, and uses the <code>buildObjects</code> method
	 * it uses a try catch block, where it executes the query for the <code>List</code> of SaleProducts
	 * @return res
	 * @throws DataAccessException
	 * @throws SQLException
	 */
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

	/**
	 * This method finds product by its id using <code>saleProductID</code>
	 * and uses a try catch block, in case it cant find saleProduct ID. 
	 * @param saleProductID
	 * @return res
	 * @throws DataAccessException
	 */
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

	/**
	 * This method finds wine by <code>saleProductID</code>
	 * @param saleProductID
	 * @param res
	 * @throws DataAccessException
	 */
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

	/**
	 * This method finds food by <code>saleProductID</code>
	 * @param saleProductID
	 * @return res
	 * @throws DataAccessException
	 */
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

	/**
	 * This method finds product by <code>saleProductID</code>
	 * @param saleProductID
	 * @return res
	 * @throws DataAccessException
	 */
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

	/**
	 * This method creates an object of <code>SaleProduct</code> and
	 * we use an <code>if</code> statement that determines whether the saleProduct, is type wine or food
	 * @param rs
	 * @return sp
	 * @throws SQLException
	 */
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
					rs.getString("menuName")
					);
		}
		return sp;
	}

	/**
	 * This method creates a <code>List</code> of type <code>SaleProduct</code> using <code>ArrayList</code>
	 * a <code>while</code> loop to build the object
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	private List<SaleProduct> buildObjects(ResultSet rs) throws SQLException {
		List<SaleProduct> res = new ArrayList<>();
		while (rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

	/**
	 * 
	 * This method saves the sale prodcuts and gets the relevant information for sp, w(for wine), and f(for food)
	 * It then has a try catch block to make sure it saves the the information, before assigning it to the <code>if</code> statement
	 * The <code>if</code> statement determines whether it is a type <code>Wine</code> or type <code>Food</code> and  the saleProduct is saved as Wine or Food
	 * It uses a try catch block, and catches the SQLException in case it cant save the saleProduct. 
	 * @param sp from SaleProduct
	 * @throws DataAccessException
	 */
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
