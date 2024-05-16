package model;

public class Batch extends Product {
	private Wine wine;
	private String wineHouse;

	public Batch(String barcode, Wine wine, String wineHouse, String type) {
		super(barcode, type);
		this.wine = wine;
		this.wineHouse = wineHouse;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

	public String getWineHouse() {
		return wineHouse;
	}

	public void setWineHouse(String wineHouse) {
		this.wineHouse = wineHouse;
	}
	

}
