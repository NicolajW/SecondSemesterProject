package db;

import model.Person;

public interface PersonDAO {
	Person findByPersonEmail(String email) throws DataAccessException;
}
