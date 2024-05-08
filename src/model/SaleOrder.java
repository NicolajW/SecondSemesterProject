package model;

import java.util.List;

public class SaleOrder {
	private double totalPrice;
	private int orderNo;
	private Person person;
	private List<OrderLine> ol;
	

	public SaleOrder(int orderNo, double totalPrice, Person employee, int tableNo) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.person = employee;
		this.ol = ol;
	}

	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public Person getEmployee() {
		return person;
	}


	public void setEmployee(Person employee) {
		this.person = employee;
	}


	public List<OrderLine> getOl() {
		return ol;
	}


	public void setOl(List<OrderLine> ol) {
		this.ol = ol;
	}

	public List<SaleOrder> findByOrderNo(int quantity) {
		return null;
	}
	
	
	
	
}
