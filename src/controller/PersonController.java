package controller;

import db.DataAccessException;
import db.PersonDAO;
import db.PersonDB;
import model.Person;

public class PersonController {
	private PersonDAO pDao;
	
	//Instantiates a new PersonDB, object. 
	public PersonController(){
		try {
			pDao = new PersonDB();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method finds the email of the <code>Person</code> object. 
	 * @param email
	 * @return pDao, which is the email that is found from the dataBase. 
	 * @throws DataAccessException
	 */
	public Person findByPersonEmail(String email) throws DataAccessException{
		return pDao.findByPersonEmail(email);
	}
}
