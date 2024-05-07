package model;

import java.util.List;

public class OrderLine {
	private int quantity;
	private List<SaleOrder> so;

	public OrderLine(int quantity) {
		this.quantity = quantity;
		this.so = so;
	}

	public OrderLine(int id, int quantity, SaleOrder orderNo, int saleProductID) {
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<OrderLine> findById(int orderNo) {
		return null;
	}
	
}
