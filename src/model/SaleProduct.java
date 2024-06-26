package model;

public abstract class SaleProduct {

	private int SaleProductID;
	private String name;
	private Double price;
	private String description;
	private String type;
	private Product product;
	private Wine wine;
	private Food food;

	public SaleProduct(int SaleProductID, String name, Double price, String description, String type) {
		super();
		this.SaleProductID = SaleProductID;
		this.name = name;
		this.price = price;
		this.description = description;
		this.type = type;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getSaleProductID() {
		return SaleProductID;
	}

	public void setSaleProductID(int saleProductID) {
		SaleProductID = saleProductID;
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
