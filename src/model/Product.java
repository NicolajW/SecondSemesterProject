package model;

public abstract class Product {
	private String barcode;
	private String type;

	public Product(String barcode, String type) {
		super();
		this.barcode = barcode;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
}
