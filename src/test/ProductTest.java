package test;

import db.DataAccessException;
import db.SaleProductDB;

public class ProductTest {
	private SaleProductDB productdb;
	public static void main(String[] args ) throws DataAccessException {
		SaleProductDB productdb = new SaleProductDB();
		productdb.findAll();
		System.out.println(productdb);
	}
}

