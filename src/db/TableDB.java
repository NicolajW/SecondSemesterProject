package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Table;

public class TableDB implements TableDAO{
	private static final String FIND_ALL_Q = "select tableNo, tableStatus from TableSeating";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where tableNo = ?";
	private PreparedStatement findAllPS;
	private PreparedStatement findByIDPS;
	
	public TableDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByIDPS = con.prepareStatement(FIND_BY_ID_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}

	}
	
	private Table buildObject(ResultSet rs) throws SQLException {
		Table res = null;
		if (rs.next()) {
			res = new Table(rs.getBoolean("tableStatus"), rs.getInt("tableNo"));
		}
		return res;
	}

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
	public List<Table> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table findByTableNo(int tableNo) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
