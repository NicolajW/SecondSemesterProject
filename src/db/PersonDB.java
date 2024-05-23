package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Person;

public class PersonDB implements PersonDAO {
	private static final String FIND_ALL_Q = "select email, firstName, lastName, phoneNo, adminNo, employeeNo from Person";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where email = ?";
	private PreparedStatement findByIDPS;

	public PersonDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findByIDPS = con.prepareStatement(FIND_BY_ID_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}
	}
	
	@Override
	public Person findByPersonEmail(String email) throws DataAccessException {
		Person res = null;
		try {
			findByIDPS.setString(1, email);
			ResultSet rs = findByIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by employeeNo", e);
		}
		return res;
	}

	private Person buildObject(ResultSet rs) throws SQLException {
		Person res = null;
		if (rs.next()) {
			res = new Person(
					rs.getString("firstName"), 
					rs.getString("lastName"), 
					rs.getString("email"),
					rs.getString("phoneNo"),
					rs.getInt("employeeNo"), 
					rs.getInt("adminNo"));
		}
		return res;
	}

	private List<Person> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Person> res = new ArrayList<>();
		Person p = buildObject(rs);
		while (p != null) {
			res.add(p);
			p = buildObject(rs);
		}
		return res;

	}

}
