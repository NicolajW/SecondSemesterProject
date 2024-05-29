package controller;

import db.DataAccessException;
import db.PersonDAO;
import db.PersonDB;
import model.Person;

public class PersonController {
	private PersonDAO pDao;

	public PersonController(){
		try {
			pDao = new PersonDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	public Person findByPersonEmail(String email) throws DataAccessException{
		return pDao.findByPersonEmail(email);
	}
}
