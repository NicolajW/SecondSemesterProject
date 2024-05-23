package model;

public class Batch extends Product {
	private Wine wine;
	private int numberOfBatches;

	public Batch(String barcode, Wine wine, int numberOfBatches, String type) {
		super(barcode, type);
		this.wine = wine;
		this.numberOfBatches = numberOfBatches;
	}

	public Wine getWine() {
		return wine;
	}

	public void setWine(Wine wine) {
		this.wine = wine;
	}

	public int getnumberOfBatches() {
		return numberOfBatches;
	}

	public void setnumberOfBatches(int numberOfBatches) {
		this.numberOfBatches = numberOfBatches;
	}

}
