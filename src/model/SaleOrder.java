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

	public SaleOrder(int orderNo, double totalPrice, Person employee, int tableNo) {
		this.totalPrice = totalPrice;
		this.orderNo = orderNo;
		this.person = employee;
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

	public Map<Integer, List<OrderLine>> saleOrderLinesHashMap(OrderLine orderLine) {
        int saleProductId = orderLine.getSaleProduct().getSaleProductID();

        if (orderLinesMap.containsKey(saleProductId)) {
            List<OrderLine> existingOrderLines = orderLinesMap.get(saleProductId);
            for (OrderLine ol : existingOrderLines) {
                if (ol.getSaleProduct().getSaleProductID() == saleProductId) {
                    ol.setQuantity(ol.getQuantity() + orderLine.getQuantity());
                    return orderLinesMap; // Assuming each sale product ID is unique
                }
            }
            // If no matching order line found, add new one
            existingOrderLines.add(orderLine);
            orderLinesMap.put(saleProductId, existingOrderLines);
        } else {
            List<OrderLine> newOrderLines = new ArrayList<>();
            newOrderLines.add(orderLine);
            orderLinesMap.put(saleProductId, newOrderLines);
        }
        return orderLinesMap;
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
