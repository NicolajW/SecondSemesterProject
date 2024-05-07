package model;

import java.util.List;

public class OrderLine {
	private int quantity;
	private SaleProduct saleProduct;
	private SaleOrder saleOrder;
	
	public OrderLine(int quantity) {
		this.quantity = quantity;
	}

	public SaleProduct getSaleProduct() {
		return saleProduct;
	}

	public void setSaleProduct(SaleProduct saleProduct) {
		this.saleProduct = saleProduct;
	}

	public SaleOrder getSaleOrder() {
		return saleOrder;
	}

	public void setSaleOrder(SaleOrder saleOrder) {
		this.saleOrder = saleOrder;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
