package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Employee;



// Tjek at FIND ALL Q, FIND BY ID Q osv. er sat op på samme måde som tabellerne i databasen
public class EmployeeDB implements EmployeeDAO {
	private static final String FIND_ALL_Q = 
			"select firstName, lastName, email, phoneNo, employeeNo from Customer";
	private static final String FIND_BY_ID_Q = 
		FIND_ALL_Q + " where phoneNo = ?";
	private static final String INSERT_Q = 
			"INSERT INTO Employee (firstName, lastName, email, phoneNo, employeeNo) VALUES (?, ?, ?, ?, ?)";
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
		
		return null;
	}

	@Override
	public Employee create(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
