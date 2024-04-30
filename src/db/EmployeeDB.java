package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

// Tjek at FIND ALL Q, FIND BY ID Q osv. er sat op på samme måde som tabellerne i databasen
public class EmployeeDB implements EmployeeDAO {
	private static final String FIND_ALL_Q = "select firstName, lastName, email, phoneNo, employeeNo from Person";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where phoneNo = ?";
	private static final String INSERT_Q = "INSERT INTO Employee (firstName, lastName, email, phoneNo, employeeNo) VALUES (?, ?, ?, ?, ?)";
	private PreparedStatement findAllPSS;
	private PreparedStatement findByIDPS;
	private PreparedStatement insert;

	// PreparedStatements skal muligvis fixes?
	public EmployeeDB() throws DataAccessException {
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
	public List<Employee> findAll() throws DataAccessException {
		return null;
	}

	@Override
	public Employee findByEmployeeNo(int employeeNo) throws DataAccessException {
		Employee res = null;
		try {
			findByIDPS.setInt(1, employeeNo);
			ResultSet rs = findByIDPS.executeQuery();
			res = buildObject(rs);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by employeeNo", e);
		}
		return res;
	}

	private Employee buildObject(ResultSet rs) throws SQLException {
		Employee res = null;
		if (rs.next()) {
			res = new Employee(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
					rs.getString("phoneNo"), rs.getInt("employeeNo"));
		}
		return res;
	}

	private List<Employee> buildObjects(ResultSet rs) throws DataAccessException, SQLException {
		List<Employee> res = new ArrayList<>();
		Employee e = buildObject(rs);
		while (e != null) {
			res.add(e);
			e = buildObject(rs);
		}
		return res;

	}

	public void saveCustomer(Employee e) throws DataAccessException {
		final String firstName = e.getFirstName();
		final String lastName = e.getLastName();
		final String email = e.getEmail();
		final String phoneNo = e.getPhoneNo();
		final int employeeNo = e.getEmployeeNo();

		try {
			insert.setString(1, firstName);
			insert.setString(2, lastName);
			insert.setString(3, email);
			insert.setString(4, phoneNo);
			insert.setInt(5, employeeNo);

			insert.executeUpdate();

		} catch (SQLException ex) {
			throw new DataAccessException("Could not build object", ex);
		}
	}

}
