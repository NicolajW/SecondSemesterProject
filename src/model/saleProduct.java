package model;

public abstract class  SaleProduct {
	public String name;
	public Double price;
	public String description;
	public String type;
	
	
	public SaleProduct(String name, Double price, String description, String type) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.type = type;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
}
