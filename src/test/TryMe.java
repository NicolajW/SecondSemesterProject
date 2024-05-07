package test;

import controller.PersonController;
import db.DataAccessException;
import db.PersonDB;
import db.TableDB;
import model.Person;
import model.Table;

public class TryMe {
	public static void main(String[] args) throws DataAccessException {
		
		TableDB tbd = new TableDB();
		Table t = new Table(true, 1);
		tbd.saveTableStatus(t);
		tbd.findAll();
		for(int i = 0; i < tbd.findAll().size(); i++) {
			System.out.println(tbd.findAll().get(i).isTableStatus());
		}
		
		
		PersonDB pdb = new PersonDB();
		Person p = pdb.findByEmployeeNo("uggabugga@email.com");
		System.out.println(p.toString());
		
		
		
	}

}
