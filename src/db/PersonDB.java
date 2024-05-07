package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Person;

// Tjek at FIND ALL Q, FIND BY ID Q osv. er sat op på samme måde som tabellerne i databasen
public class PersonDB implements PersonDAO {
	private static final String FIND_ALL_Q = "select email, firstName, lastName, phoneNo, adminNo, employeeNo from Person";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where email = ?";
	private static final String INSERT_Q = "INSERT INTO Employee (email, firstName, lastName, phoneNo, adminNo, employeeNo) VALUES (?, ?, ?, ?, ?, ?)";
	private PreparedStatement findAllPSS;
	private PreparedStatement findByIDPS;
	private PreparedStatement insert;

	// PreparedStatements skal muligvis fixes?
	public PersonDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPSS = con.prepareStatement(FIND_ALL_Q);
			findByIDPS = con.prepareStatement(FIND_BY_ID_Q);
			insert = con.prepareStatement(INSERT_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}
	}

	@Override
	public List<Person> findAll() throws DataAccessException {
		try {
			ResultSet rs = findAllPSS.executeQuery();
			List<Person> res = buildObjects(rs);
			return res;
		} catch (SQLException e) {
			throw new DataAccessException("Could not find all Persons", e);
		}
		
	}

	
	@Override
	public Person findByEmployeeNo(String email) throws DataAccessException {
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
			res = new Person(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
					rs.getString("phoneNo"), rs.getInt("employeeNo"), rs.getInt("adminNo"));
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

	public void saveCustomer(Person p) throws DataAccessException {
		final String email = p.getEmail();
		final String firstName = p.getFirstName();
		final String lastName = p.getLastName();
		final String phoneNo = p.getPhoneNo();
		final int adminNo = p.getAdminNo();
		final int employeeNo = p.getEmployeeNo(); 
		


		try {
			insert.setString(1, email);
			insert.setString(2, firstName);
			insert.setString(3, lastName);
			insert.setString(4, phoneNo);
			insert.setInt(5, adminNo);
			insert.setInt(6, employeeNo);
			

			insert.executeUpdate();

		} catch (SQLException ex) {
			throw new DataAccessException("Could not build object", ex);
		}
	}

}
