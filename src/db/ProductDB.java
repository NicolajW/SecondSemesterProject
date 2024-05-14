package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SaleProduct;
import model.Food;
import model.Wine;

public class ProductDB implements ProductDAO {

	private Wine w;
	private Food f;

	private static final String FIND_ALL_Q = "select saleProductID, name, price, description, type from saleProduct";
    private static final String JOIN_ALL_Q = "SELECT * FROM saleProduct"
    		+ " FULL OUTER JOIN wine ON saleProductID = saleProductID_PKFK"
    		+ " FULL OUTER JOIN food ON saleProductID = food_id";
	private static final String FIND_BY_Q = JOIN_ALL_Q + " WHERE saleProductID= ?"; 
	private static final String INSERT_INTO_SALEPRODUCT_Q = "insert into saleProduct (name, price, description, type) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_WINE_Q = "insert into Wine (grapeType, yearProduced, wineHouse, region) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_FOOD_Q = "insert into Food (menuName) values (?);";

	private PreparedStatement findAllPSS;
	private PreparedStatement findByProductIDPS;
	private PreparedStatement insertInSP, insertInW, insertInF;

	public ProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPSS = con.prepareStatement(FIND_ALL_Q);
			findByProductIDPS = con.prepareStatement(FIND_BY_Q);
			insertInSP = con.prepareStatement(INSERT_INTO_SALEPRODUCT_Q);
			insertInW = con.prepareStatement(INSERT_INTO_WINE_Q);
			insertInF = con.prepareStatement(INSERT_INTO_FOOD_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
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
