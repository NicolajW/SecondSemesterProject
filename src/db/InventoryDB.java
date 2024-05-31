package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Inventory;

public class InventoryDB implements InventoryDAO {
	private static final String FIND_ALL_Q = "select id, quantity from Inventory";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where id = ?";
	private static final String UPDATE_Q = "update Inventory set quantity = ? where id = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIDPS;
	private PreparedStatement update;

	/**
	 *Instantiates prepared statements for <code>FIND_ALL_Q</code>, <code>FIND_BY_ID_Q</code> and <code>UPDATE_Q</code>
	 * to instantiate the queries using DBConnection class that connects to the database. 
	 * @throws DataAccessException
	 */
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

	/**
	 * This method instantiates an object of <code> Inventory </code> where it uses an if statement to get quantity and id of products in the Inventory class
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	private Inventory buildObject(ResultSet rs) throws SQLException {
		Inventory res = null;
		if (rs.next()) {
			res = new Inventory(
					rs.getInt("quantity"), 
					rs.getInt("id")
					);
		}
		return res;
	}

	/**
	 * This method instantiates a <code>List</code> creating a new <code>ArrayList</code> of type Inventory
	 * <code>Inventory</code> e sets the method buildObject with rs.
	 * 
	 * <code>while</code> loop adds e while we have a buildObject with data
	 * @param rs
	 * @return res which is a new <code>List</code> of the Inventory
	 * @throws DataAccessException, SQLException
	 */
	private List<Inventory> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Inventory> res = new ArrayList<>();
		Inventory e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;

	}

	/**
	 * This method finds by inventoryNo, with inventoryNo
	 * @param <code>int</code> inventoryNo
	 * We use a try catch block, to handle the exception to tell if it can find tableNo of e
	 * It uses <code>findByIDPS</code> and executes the query with inventoryNo
	 * @throws DataAccessException
	 */
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

	/**
	 * Updates the quantity of products where it then sets the a new quantity, and sets an id of the <code>inventory</code>
	 * and then executes the update
	 * @param inventory
	 * it uses try catch block to handle an SQLException
	 * @exception SQLException 
	 * @throws DataAccessException
	 */
	@Override
	public void updateProductQuantity(Inventory inventory) throws DataAccessException {
		final double quantity = inventory.getQuantity();
		final int id = inventory.getId();
		try {
			update.setDouble(1, quantity);
			update.setInt(2, id);

			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not update inventory", e);
		}
	}

}
