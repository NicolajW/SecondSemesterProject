package model;

public class Table {
	private boolean tableStatus;
	private int tableNo;
	
	public Table(boolean tableStatus, int tableNo) {
		this.tableStatus = tableStatus;
		this.tableNo = tableNo;
	}

	public boolean isTableStatus() {
		return tableStatus;
	}


	public void setTableStatus(boolean tableStatus) {
		this.tableStatus = tableStatus;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	
	
	

}
