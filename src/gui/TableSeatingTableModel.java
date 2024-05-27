package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Table;

public class TableSeatingTableModel extends AbstractTableModel{

	private List<Table> data;
	private static final String[] COL_NAMES = {"Table Number", "Table status"};
	
	
	
	public TableSeatingTableModel(List<Table> data) {
		this.data = data;
		if(this.data == null) {
			this.data = new ArrayList<>();
		}
	}
	
	public void setData(List<Table> data) {
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

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Table t = data.get(rowIndex);
		String res = "";
		switch(columnIndex) {
		case 0: res = "" + t.getTableNo();break;
		case 1: res = "" + t.isTableStatus();break;
		default: res = "<UNKNOWN " + columnIndex + ">";
		}
		return res;
	}
	
    @Override
    public String getColumnName(int column) {
        return COL_NAMES[column];
    }
	
	 public Table getTableAt(int rowIndex) {
	        return data.get(rowIndex);
	    }

}
