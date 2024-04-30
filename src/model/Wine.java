package model;

public class Wine extends saleProduct {
	public String type;
	public String grapeType;
	public String year;
	public String wineHouse;
	public String region;
	public int amountLeft;
	
	public Wine(String barcode, String name, Double price, String description, String type, String grapeType, String year, String wineHouse, String region, int amountLeft) {
		super(barcode, name, price, description);
		this.type = type;
		this.grapeType = grapeType;
		this.year = year;
		this.wineHouse = wineHouse;
		this.region = region;
		this.amountLeft = amountLeft;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrapeType() {
		return grapeType;
	}

	public void setGrapeType(String grapeType) {
		this.grapeType = grapeType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getWineHouse() {
		return wineHouse;
	}

	public void setWineHouse(String wineHouse) {
		this.wineHouse = wineHouse;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft;
	}
}
