package model;

import java.util.List;

public class SaleOrder {
	private double totalPrice;
	private int orderNo;
	private Employee employee;
	private List<Orderline> ol;
	

	public SaleOrder(double totalPrice, int orderNo, Employee employee, List<Orderline> ol) {
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


	public List<Orderline> getOl() {
		return ol;
	}


	public void setOl(List<Orderline> ol) {
		this.ol = ol;
	}
	
	
	
	
}
