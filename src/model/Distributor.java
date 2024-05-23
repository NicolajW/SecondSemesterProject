package model;

public class Distributor {
	private String name;
	private String streetName;
	private int cvrNo;

	public Distributor(String name, String streetName, int cvrNo) {
		super();
		this.name = name;
		this.streetName = streetName;
		this.cvrNo = cvrNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getCvrNo() {
		return cvrNo;
	}

	public void setCvrNo(int cvrNo) {
		this.cvrNo = cvrNo;
	}

}
