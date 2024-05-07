package model;

public class Food extends SaleProduct {
	public String menuName;

	public Food(String name, Double price, String description, String type, String menuName) {
	    super(name, price, description, type);
	    this.menuName = menuName;
	}
	    
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
	
	
}


