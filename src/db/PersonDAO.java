package db;

import java.util.List;

import model.Person;

public interface PersonDAO {
	Person findByPersonEmail(String email) throws DataAccessException;
}
