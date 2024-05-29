package model;


public class Inventory {
	private double quantity;
	private int id;

	public Inventory(double quantity, int id) {
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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double d) {
		this.quantity = d;
	}
}
