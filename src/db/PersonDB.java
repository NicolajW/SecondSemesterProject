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
	
	/**
	 * This method find a person by its email with
	 * @param <code> String </code> email
	 * it finds the persons id and then its email, <code> ResultSet </code> rs executes the query for findByIDPS
	 * @return res which an object of the <code> Person </code> class
	 * @throws DataAccessException
	 */
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

	/**
	 * This method instantiates and object of <code>Person</code>
	 * here it creates a new Person where it gets the information matching with the <code>Person</code> class
	 * @param rs
	 * @return res, which is an instance of <code> Person </code>
	 * @throws SQLException
	 */
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

	/**
	 * This method creates objects of List <code>Person</code> of type <code>ArrayList</code>
	 * a while loop to add a Person p into the <code>ArrayList</code>
	 * @param rs
	 * @return res, which is an <code>ArrayList</code> of type Person
	 */
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
