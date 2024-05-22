package test;

import controller.TableController;
import db.DataAccessException;
import model.Table;

public class TryMetwo {
	public static void main(String[] args) throws DataAccessException {
		Table t = new Table(false, 1);
		TableController tc = new TableController();
		tc.updateTableStatus(t);
		tc.findByTableNo(1);
		System.out.println(t);
	}

}
