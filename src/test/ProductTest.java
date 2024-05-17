package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.SaleProductDB;
import model.SaleProduct;

public class ProductTest {
	private SaleProductDB productdb;
	public static void main(String[] args ) throws DataAccessException, SQLException {
		SaleProductDB product = new SaleProductDB();
		product.buildObjects(null);
		System.out.println(product);
//		SaleProductDB productdb = new SaleProductDB();
//		System.out.println(productdb);
	}
}

