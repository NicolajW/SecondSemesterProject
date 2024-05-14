package model;

import java.util.List;

public class OrderLine {
	private double quantity;
	private SaleProduct saleProduct;
	private SaleOrder saleOrder;
	
	public OrderLine(double quantity, SaleProduct saleProduct, SaleOrder saleOrder) {
		this.quantity = quantity;
		this.saleProduct = saleProduct;
		this.saleOrder = saleOrder;
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


	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}
