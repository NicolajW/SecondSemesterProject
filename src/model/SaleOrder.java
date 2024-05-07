package model;

import java.util.List;

public class SaleOrder {
	private double totalPrice;
	private int orderNo;
	private Employee employee;
	private List<OrderLine> ol;
	

	public SaleOrder(int orderNo, double totalPrice, Employee employee, int tableNo) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.employee = employee;
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


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
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
