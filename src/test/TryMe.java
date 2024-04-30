package test;

import db.DataAccessException;
import db.TableDB;
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
	}

}
