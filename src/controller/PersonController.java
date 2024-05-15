package controller;

import db.DataAccessException;
import db.PersonDAO;
import db.PersonDB;
import model.Person;

public class PersonController {
	
	private PersonDAO pDao;
	
	public PersonController() throws DataAccessException{
		pDao = new PersonDB();
	}
	
	public Person findByEmployeeNo(String email) throws DataAccessException{
		return pDao.findByEmployeeNo(email);
	}
}