package test;

import java.util.List;

import controller.PersonController;
import controller.ProductController;
import controller.SaleProductController;
import controller.SaleOrderController;
import controller.TableController;
import db.DataAccessException;
import db.PersonDB;
import db.ProductDB;
import db.SaleOrderDB;
import db.TableDB;
import model.Ingredients;
import model.Person;
import model.SaleOrder;
import model.SaleProduct;
import model.Table;

public class TryMe {
	public static void main(String[] args) throws DataAccessException {
//		
//		TableDB tbd = new TableDB();
//		tbd.updateTableStatus(t);
		//tbd.findAll();
//		
//		
		Table t = new Table(false, 1);
		TableController tc = new TableController();
		tc.updateTableStatus(t);
		tc.findByTableNo(1);
		System.out.println(t);
//		
		
//		for(int i = 0; i < tbd.findAll().size(); i++) {
//			System.out.println(tbd.findAll().get(i).isTableStatus());
//		}
		
		
//		PersonDB pdb = new PersonDB();
//		Person p = pdb.findByEmployeeNo("uggabugga@hotmail.com");
//		System.out.println(p.toString());
		
		//ProductController pctrl = new ProductController();
//		SaleOrderDB sodb = new SaleOrderDB();
		

		
		SaleOrderController octrl = new SaleOrderController();
		SaleOrder so = octrl.createSaleOrder("bobby@hotmail.com", 1);
		//octrl.addProduct(1, 1);
		octrl.addProduct(1, 2);
		octrl.addProduct(1, 2);
		octrl.addProduct(1, 2);
		//octrl.updateInventory();
		octrl.saveOrder();
		//octrl.updateInventory();
		//System.out.println(so.getOl().get(0).getSaleProduct());
		//System.out.println(so.getOl().get(0).getQuantity());
		
		
		
//		System.out.println(prdb.findAll());
		
//		octrl.saveOrder();
	
		
		
	}

}
