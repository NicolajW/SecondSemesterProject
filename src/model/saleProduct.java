package model;

public abstract class saleProduct {
	private String barcode;
	public String name;
	public Double price;
	public String description;
	
	
	public saleProduct(String barcode, String name, Double price, String description) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.description = description;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
