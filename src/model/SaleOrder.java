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
	private Map<Integer, List<OrderLine>> orderLinesMap;

	public SaleOrder(int orderNo, double totalPrice, Person person, int tableNo) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.person = person;
		this.tableNo = tableNo;
		this.ol = new ArrayList<>();
		this.orderLinesMap = new HashMap<>();
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public double getTotalPrice() {
		double total = 0;
	
			for (OrderLine line : ol) {	
				total += line.getSaleProduct().getPrice() * line.getQuantity();
				System.out.println(line.getSaleProduct().getPrice());
			}
		this.totalPrice = total;
		return total;
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

	public Person getPerson() {
		return person;
	}

	private void addOrderLine(OrderLine orderLine) {
		if (ol == null) {
			ol = new ArrayList<>();
		}
		ol.add(orderLine);
	}

	public Map<Integer, List<OrderLine>> saleOrderLinesHashMap(OrderLine orderLine) {
		int saleProductId = orderLine.getSaleProduct().getSaleProductID();

		if (orderLinesMap.containsKey(saleProductId)) {
			List<OrderLine> existingOrderLines = orderLinesMap.get(saleProductId);
			for (OrderLine ol : existingOrderLines) {
				if (ol.getSaleProduct().getSaleProductID() == saleProductId) {
					ol.setQuantity(ol.getQuantity() + orderLine.getQuantity());
				}
			}
			//existingOrderLines.add(orderLine);
			orderLinesMap.put(saleProductId, existingOrderLines);
		} else {
			addOrderLine(orderLine);
			orderLinesMap.put(saleProductId, ol);
		}
		return orderLinesMap;
	}

	public void setPerson(Person person) {
		this.person = person;
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
