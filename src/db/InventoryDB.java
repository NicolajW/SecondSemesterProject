package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Inventory;
import model.SaleProduct;
import model.Table;

public class InventoryDB implements InventoryDAO {
	private static final String FIND_ALL_Q = "select id, quantity from Inventory";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where id = ?";
	private static final String UPDATE_Q = "update Inventory set quantity = ? where id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIDPS;
	private PreparedStatement update;
	
	public InventoryDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByIDPS = con.prepareStatement(FIND_BY_ID_Q);
			update = con.prepareStatement(UPDATE_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}
	}
	
	private Inventory buildObject(ResultSet rs) throws SQLException {
		Inventory res = null;
		if (rs.next()) {
			res = new Inventory(rs.getInt("quantity"), rs.getInt("id"));
		}
		return res;
	}

	private List<Inventory> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Inventory> res = new ArrayList<>();
		Inventory e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;

	}

	@Override
	public Inventory findByInventoryNo(int inventoryNo) throws DataAccessException {
		Inventory res = null;
		try {
			findByIDPS.setInt(1, inventoryNo);
			ResultSet rs = findByIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by tableNo", e);
		}
		return res;
	}

	@Override
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		final int quantity = inventory.getQuantity();
		final int id = inventory.getId();
		try {
			update.setInt(1, quantity);
			update.setInt(2, id);
			
			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not update inventory", e);
		}
	}

}
