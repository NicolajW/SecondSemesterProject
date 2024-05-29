package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleOrder {
	private double totalPrice;
	private int orderNo;
	private Table table;
	private Person person;
	private List<OrderLine> ol;
	private Map<Integer, List<OrderLine>> orderLinesMap;

	public SaleOrder(int orderNo, double totalPrice, Person person, Table table) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.person = person;
		this.table = table;
		this.ol = new ArrayList<>();
		this.orderLinesMap = new HashMap<>();
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
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

	/**
	 * This method is a hashMap, where we use <code>Map</code> of <code>Integer, List< OrderLine ></code>
	 * So it can have a map of OrderLines, that makes it easier for us to search for an orderLine
	 * Here it start with a <code>saleProductId</code> for orderLine with a <code>SaleProduct</code> and a <code>SaleProductID</code>
	 * Then it use an <code>if</code> statement, that take the List of <code>OrderLine</code> if the saleProduct has a <code>SaleProductID</code>
	 * inside that, it then uses a <code>for</code> loop that loops through the <code>List</code> of OrderLines, in the hashMap. 
	 * then inside the <code>for</code> loop we have an <code>if</code> statement, that determines if we have a
	 * product with a <code>SaleProductID</code> that is the same of the saleProductId used in the hashMap
	 * if it is the same, then it sets the quantity and adds it to the orderLine. 
	 * It then puts the <code>saleProductId</code> and <code>existingOrderLines</code> to the hashMap. 
	 * if it isnt the same <code>saleProductId</code> as it is on the <code>List</code> of OrderLine, 
	 * it then adds a new OrderLine, and puts that in the hashMap, for <code>orderLinesMap</code>. 
	 * @param orderLine
	 * @return orderLinesMap
	 * 
	 */
	public Map<Integer, List<OrderLine>> saleOrderLinesHashMap(OrderLine orderLine) {
		int saleProductId = orderLine.getSaleProduct().getSaleProductID();

		if (orderLinesMap.containsKey(saleProductId)) {
			List<OrderLine> existingOrderLines = orderLinesMap.get(saleProductId);
			for (OrderLine ol : existingOrderLines) {
				if (ol.getSaleProduct().getSaleProductID() == saleProductId) {
					ol.setQuantity(ol.getQuantity() + orderLine.getQuantity());
				}
			}
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
}
