package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		this.ol = new ArrayList<>();
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

		boolean found = false;
		for (OrderLine existingOrderLine : ol) {
			if (existingOrderLine.getSaleProduct()
					.getSaleProductID() == (orderLine.getSaleProduct().getSaleProductID())) {
				existingOrderLine.setQuantity(existingOrderLine.getQuantity() + orderLine.getQuantity());
				found = true;
				break;
			} else if (!found) {
				ol.add(orderLine);
			}
		}
	}

	public Map saleOrderLinesHashMap(OrderLine orderLine) {
		int i = 0;
		HashMap<Integer, List<OrderLine>> map = new HashMap<Integer, List<OrderLine>>();
//		if(map.get(i).get(i).getSaleProduct().getSaleProductID() == orderLine.getSaleProduct().getSaleProductID()) {
//			map.get(i).get(i).setQuantity(map.get(i).get(i).getQuantity() + orderLine.getQuantity());
//		}else {
//			map.put(i, ol);
//		}

		if (map.containsKey(orderLine.getSaleProduct().getSaleProductID())) {
			
			orderLine.setQuantity(orderLine.getQuantity() + map.get());
			map.put(orderLine.getSaleProduct().getSaleProductID(), ol);
		} else {
			ol.add(orderLine);
			map.put(orderLine.getSaleProduct().getSaleProductID(), ol);
			
		}
		

		return map;
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

	@Override
	public String toString() {
		return "SaleOrder [ol=" + ol + "]";
	}

}
