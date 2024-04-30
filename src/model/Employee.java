package model;

public class Employee extends Person {

	private int employeeNo;

	public Employee(String firstName, String lastName, String email, String phoneNo, int employeeNo) {
		super(firstName, lastName, email, phoneNo);
		this.employeeNo = employeeNo;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

}
