package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Batch;
import model.Food;
import model.Ingredients;
import model.Product;
import model.SaleProduct;
import model.Wine;


public class ProductDB {
	private Batch b;
	private Ingredients i;

	private static final String FIND_ALL_Q = "select barcode from Product";
    private static final String JOIN_ALL_Q = "SELECT * FROM Product"
    		+ " FULL OUTER JOIN Batch ON productID = productID_PKFK"
    		+ " FULL OUTER JOIN Ingredients ON productID = productID_PKFK";
	private static final String FIND_BY_Q = JOIN_ALL_Q + " WHERE productID= ?"; 
	private static final String INSERT_INTO_PRODUCT_Q = "insert into Product (productID, barcode, inventoryID_FK, type) values (?, ?, ?, ?);";
	private static final String INSERT_INTO_BATCH_Q = "insert into Batch (productID_PKFK, wineHouse, type) values (?, ?, ?);";
	private static final String INSERT_INTO_INGREDIENTS_Q = "insert into Ingredients (productID_PKFK, saleProductID_PKFK, type, name, typeOfFood) values (?, ?, ?, ?, ?);";

	private PreparedStatement findAllPSS;
	private PreparedStatement findByProductIDPS;
	private PreparedStatement insertInP, insertInB, insertInI;

	public ProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPSS = con.prepareStatement(FIND_ALL_Q);
			findByProductIDPS = con.prepareStatement(FIND_BY_Q);
			insertInP = con.prepareStatement(INSERT_INTO_PRODUCT_Q);
			insertInB = con.prepareStatement(INSERT_INTO_BATCH_Q); 
			insertInI = con.prepareStatement(INSERT_INTO_INGREDIENTS_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
	}
	
	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = null;
		String type = rs.getString("type");
		if (type.equals("batch")) {
			p = new Batch(
					rs.getString("barcode"),
					new Wine(rs.getInt("saleProductID_PKFK"), null, null, null, null, null, null, null, null),
					rs.getString("wineHouse"),
					rs.getString("type")
					);
		} else if (type.equals("ingredients")) {
			p = new Ingredients(
					rs.getString("name"), 
					rs.getString("typeOfFood"), 
					rs.getString("barcode"), 
					rs.getInt("quantity"),
					rs.getString("type")
					);
		}
		return p;
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
	
	
}
