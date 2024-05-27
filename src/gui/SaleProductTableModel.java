package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SaleProduct;

public class SaleProductTableModel extends AbstractTableModel {
	
	private List<SaleProduct> data;
	private static final String[] COL_NAMES = {"ID", "Price", "Name", "Description", "type"};
	
	
	
	public SaleProductTableModel(List<SaleProduct> data) {
		this.data = data;
		if(this.data == null) {
			this.data = new ArrayList<>();
		}
	}
	
	public void setData(List<SaleProduct> data) {
		this.data = data;
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}
	
	public SaleProduct getSaleProduct(int rowIndex) {
		return data.get(rowIndex);
	}
	
    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		SaleProduct sp = data.get(rowIndex);
		String res = "";
		switch(columnIndex) {
		case 0: res = "" + sp.getSaleProductID();break;
		case 1: res = "" + sp.getPrice();break;
		case 2: res = sp.getName();break;
		case 3: res = sp.getDescription();break;
		case 4: res = sp.getType();break;
		default: res = "<UNKNOWN " + columnIndex + ">";
		}
		return res;
	}

}
