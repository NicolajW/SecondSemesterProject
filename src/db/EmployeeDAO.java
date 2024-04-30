package db;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {
	List<Employee> findAll() throws DataAccessException;
	Employee findByEmployeeNo(int employeeNo) throws DataAccessException; 
	Employee create(Employee e);
}
