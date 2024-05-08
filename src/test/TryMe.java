package test;

import controller.PersonController;
import controller.ProductController;
import controller.SaleOrderController;
import db.DataAccessException;
import db.PersonDB;
import db.TableDB;
import model.Person;
import model.SaleProduct;
import model.Table;

public class TryMe {
	public static void main(String[] args) throws DataAccessException {
		
		TableDB tbd = new TableDB();
		Table t = new Table(false, 1);
		tbd.saveTableStatus(t);
		tbd.findAll();
		for(int i = 0; i < tbd.findAll().size(); i++) {
			System.out.println(tbd.findAll().get(i).isTableStatus());
		}
		
		
		PersonDB pdb = new PersonDB();
		Person p = pdb.findByEmployeeNo("uggabugga@email.com");
		System.out.println(p.toString());
		
		ProductController pctrl = new ProductController();
		SaleProduct sp = pctrl.findByProductById(1);
		
		System.out.println(sp);
		
		SaleOrderController octrl = new SaleOrderController();
//		octrl.createSaleOrder("WillyLover@email.com", 1);
//		octrl.addProduct(2);
		
		
	}

}
