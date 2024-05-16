package model;

public class Ingredients extends Product {
	private String name;
	private String typeOfFood;
	private int quantity;
	
	public Ingredients(String name, String typeOfFood, String barcode, int quantity, String type) {
		super(barcode, type);
		this.name = name;
		this.typeOfFood = typeOfFood;
		this.quantity = quantity;
	}
	
	

	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeOfFood() {
		return typeOfFood;
	}

	public void setTypeOfFood(String typeOfFood) {
		this.typeOfFood = typeOfFood;
	}
	
	

}
