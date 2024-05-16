package model;

public abstract class Product {
	private String barcode;

	public Product(String barcode) {
		super();
		this.barcode = barcode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "Product [barcode=" + barcode + "]";
	}
	

}
