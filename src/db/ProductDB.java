package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.saleProduct;
import model.Food;
import model.Wine;

public class ProductDB implements ProductDAO {
	private static final String FIND_ALL_Q =
			"select saleProductID, name, price, description, type from saleProduct";
	private static final String FIND_BY_Q =
			FIND_ALL_Q + "where saleProductID = ?";
	private PreparedStatement findAllPSS;
	private PreparedStatement findByProductIDPS;
			
	
	public ProductDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			 findAllPSS = con.prepareStatement(FIND_ALL_Q);
	         findByProductIDPS = con.prepareStatement(FIND_BY_Q);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare queries", e);
		}
	}

	@Override
	public List<saleProduct> findAll() throws DataAccessException {
		try {
			ResultSet rs = findAllPSS.executeQuery();
			List<saleProduct> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Cloud not find all", e);
		}
	}

	@Override
	public saleProduct findByProductById(int saleProductID) throws DataAccessException {
		saleProduct res = null;
		try {
			findByProductIDPS.setInt(1, saleProductID);
			ResultSet rs = findByProductIDPS.executeQuery();
			if(rs.next()){
				res = buildObject(rs);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by id =" + saleProductID, e);
		}
		return res;
	}

	private saleProduct buildObject(ResultSet rs ) throws SQLException {
		saleProduct sp = null;
		String type = rs.getString("type");
		if(type.equals("wine")) {
			sp = new Wine(
				rs.getString("name"),
				rs.getDouble("price"),
				rs.getString("description"),
				rs.getString("type"),
				rs.getString("grapeType"),
				rs.getString("year"),
				rs.getString("wineHouse"),
				rs.getString("region"),
				rs.getInt("amountLeft")
				);
		} else if(type.equals("food")) {
			sp = new Food(
				rs.getString("name"),
				rs.getDouble("price"),
				rs.getString("description"),
				rs.getString("type"),
				rs.getString("menuName")
				);
		}
		return sp;
		}
	
	public List<saleProduct> buildObjects(ResultSet rs) throws SQLException{
		List<saleProduct> res = new ArrayList<>();
		while(rs.next()) {
			res.add(buildObject(rs));
		}
		return res;
	}

}
