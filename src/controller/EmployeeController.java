package controller;

import db.DataAccessException;
import db.EmployeeDAO;
import db.EmployeeDB;
import model.Employee;

public class EmployeeController {
	
	private EmployeeDAO eDao;
	
	public EmployeeController() throws DataAccessException{
		eDao = new EmployeeDB();
	}
	
	public Employee findByEmployeeNo(int employeeNo) throws DataAccessException{
		return eDao.findByEmployeeNo(employeeNo);
	}
}
