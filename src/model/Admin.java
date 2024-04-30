package model;

public class Admin extends Person{
	
	private int adminNo;

	public Admin(String firstName, String lastName, String email, String phoneNo, int adminNo) {
		super(firstName, lastName, email, phoneNo);
		this.adminNo = adminNo;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

}
