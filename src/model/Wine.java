package model;

public class Wine extends SaleProduct {
	public String grapeType;
	public String yearProduced;
	public String wineHouse;
	public String region;
	public int amountLeft;
	
	public Wine(int saleProductID, String name, Double price, String description, String type, String grapeType, String yearProduced, String wineHouse, String region, int amountLeft) {
		super(saleProductID, name, price, description, type);
		this.grapeType = grapeType;
		this.yearProduced = yearProduced;
		this.wineHouse = wineHouse;
		this.region = region;
		this.amountLeft = amountLeft;
	}


	public String getGrapeType() {
		return grapeType;
	}

	public void setGrapeType(String grapeType) {
		this.grapeType = grapeType;
	}

	public String getYearProduced() {
		return yearProduced;
	}

	public void setYear(String yearProduced) {
		this.yearProduced = yearProduced;
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


	@Override
	public String toString() {
		return "Wine [grapeType=" + grapeType + ", yearProduced=" + yearProduced + ", wineHouse=" + wineHouse
				+ ", region=" + region + ", amountLeft=" + amountLeft + "]";
	}
	
	
}
