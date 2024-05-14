package model;

import java.util.ArrayList;
import java.util.List;

public class SaleOrder {
	private double totalPrice;
	private int orderNo;
	private int tableNo;
	private Person person;
	private List<OrderLine> ol;
	

	public SaleOrder(int orderNo, double totalPrice, Person employee, int tableNo) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.person = employee;
		this.tableNo = tableNo;
		this.ol = ol;
	}

	
	public int getTableNo() {
		return tableNo;
	}


	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
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
	
	public void addOrderLine(OrderLine orderLine) {
        if (ol == null) {
            ol = new ArrayList<>();
        }
        
        ol.add(orderLine);
        
//        for(int i = 0; i < ol.size(); i++) {
//        	if(ol.get(i).getSaleProduct() == orderLine.getSaleProduct()) {
//        		double quantity = ol.get(i).getQuantity();
//        		ol.get(i).setQuantity(quantity + 1);
//        	}else if(ol.get(i).getSaleProduct() != orderLine.getSaleProduct()) {	
//        		ol.add(orderLine);
//        	}
//        }
        
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
