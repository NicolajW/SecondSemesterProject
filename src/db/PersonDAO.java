package db;

import java.util.List;

import model.Person;

public interface PersonDAO {
	List<Person> findAll() throws DataAccessException;

	Person findByEmployeeNo(String email) throws DataAccessException;
}
