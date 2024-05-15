package test;

import controller.PersonController;
import controller.ProductController;
import controller.SaleOrderController;
import db.DataAccessException;
import db.PersonDB;
import db.TableDB;
import model.Person;
import model.SaleOrder;
import model.SaleProduct;
import model.Table;

public class TryMe {
	public static void main(String[] args) throws DataAccessException {
		
		TableDB tbd = new TableDB();
		Table t = new Table(false, 1);
		tbd.saveTableStatus(t);
		tbd.findAll();
//		for(int i = 0; i < tbd.findAll().size(); i++) {
//			System.out.println(tbd.findAll().get(i).isTableStatus());
//		}
		
		
		PersonDB pdb = new PersonDB();
		Person p = pdb.findByEmployeeNo("uggabugga@hotmail.com");
//		System.out.println(p.toString());
		
		ProductController pctrl = new ProductController();
		
		SaleOrderController octrl = new SaleOrderController();
		SaleOrder so = octrl.createSaleOrder("uggabugga@hotmail.com", 1);
		octrl.addProduct(1, 2);
		octrl.addProduct(1, 2);
		octrl.saveOrder();
		//System.out.println(so.getOl().get(0).getSaleProduct());
		//System.out.println(so.getOl().get(0).getQuantity());

		
//		octrl.saveOrder();
	
		
		
	}

}
