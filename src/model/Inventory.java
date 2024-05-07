package model;

import db.DataAccessException;
import db.ProductDB;

public class Inventory {
	private int quantity;
	private int id;

	public Inventory(int quantity, int id) {
		super();
		this.quantity = quantity;
		this.id = id;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
