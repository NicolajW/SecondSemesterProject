package model;

public class Food extends saleProduct {
	public String menuName;

	public Food(String barcode, String name, Double price, String description, String menuName) {
	    super(barcode, name, price, description);
	    this.menuName = menuName;
	}
	    
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
	
	
}


