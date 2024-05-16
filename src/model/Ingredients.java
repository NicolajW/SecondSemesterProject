package model;

public class Ingredients extends Product {
	private String name;
	private String type;
	
	public Ingredients(String name, String type, String barcode) {
		super(barcode);
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
