package test;

import controller.ProductController;
import controller.SaleOrderController;
import db.DataAccessException;
import db.PersonDB;
import db.TableDB;
import model.Person;
import model.Table;

public class TryMeNew {
	public static void main(String[] args) throws DataAccessException {
		TableDB tdb = new TableDB();
		Table t = new Table(false, 1);
		tdb.saveTableStatus(t);
		PersonDB pdb = new PersonDB();
		Person p = pdb.findByEmployeeNo("uggabugga@email.com");
		ProductController pc = new ProductController();
		SaleOrderController soc = new SaleOrderController();
		soc.createSaleOrder("uggabugga@email.com", 1);
		
	}
	
}
