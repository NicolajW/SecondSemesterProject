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
import db.SaleProductDB;
import db.TableDB;
import model.Ingredients;
import model.Person;
import model.SaleOrder;
import model.SaleProduct;
import model.Table;

public class TryMe {
	public static void main(String[] args) throws DataAccessException {
//		
		TableDB tbd = new TableDB();
		Table t = new Table(false, 3);
		tbd.updateTableStatus(t);
		
//		SaleProductDB dosb = new SaleProductDB();
//		dosb.findAll();
		
		
		
		
		
		
		
		
//		tbd.findAll();
//		
//		TableDB tDB = new TableDB();
		
//		List<Table> list = tDB.findAllTables();
//		for(Table table : list) {
//			System.out.println(table);
		}

	
		
		
	}

//}
