package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Table;

public class TableDB implements TableDAO {
	private static final String FIND_ALL_Q = "select tableNo, tableStatus from TableSeating";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where tableNo = ?";
	private static final String UPDATE_Q = "update TableSeating set tableStatus = ? where tableNo = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIDPS;
	private PreparedStatement update;

	public TableDB() throws DataAccessException {
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
	 * This method creates an object of type <code>Table</code>. 
	 * Then we use the <code>if</code> statement for the next ResultSet that creates the new table, with
	 * status and number of the <code>Table</code> object. 
	 * @param rs
	 * @return res
	 * @throws SQLException
	 */
	private Table buildObject(ResultSet rs) throws SQLException {
		Table res = null;
		if (rs.next()) {
			res = new Table(
					rs.getBoolean("tableStatus"), 
					rs.getInt("tableNo")
					);
		}
		return res;
	}

	/**
	 * This method creates a <code>List</code> of type <code>Table</code> using <code>ArrayList</code>.
	 * A <code>while</code> loop that adds the buildObejct, for Table e, while e isnt <code>null</code>.
	 * @param rs
	 * @return res
	 * @throws DataAccessException
	 * @throws SQLException
	 */
	private List<Table> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Table> res = new ArrayList<>();
		Table e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;

	}

	@Override
	/**
	 * This method find all the table from the <code>List</code> of all <code>Table</code> objects
	 * @return res
	 */
	public List<Table> findAllTables() throws DataAccessException {
		try {
			ResultSet rs = findAllPS.executeQuery();
			List<Table> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all tables", e);
		}
	}

	@Override
	public Table findByTableNo(int tableNo) throws DataAccessException {
		Table res = null;
		try {
			findByIDPS.setInt(1, tableNo);
			ResultSet rs = findByIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by tableNo", e);
		}
		return res;
	}

	@Override
	public void updateTableStatus(Table table) throws DataAccessException {
		final boolean tableStatus = table.isTableStatus();
		final int tableNo = table.getTableNo();
		try {
			update.setBoolean(1, tableStatus);
			update.setInt(2, tableNo);

			update.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("Could not save table status", e);
		}

	}

}
