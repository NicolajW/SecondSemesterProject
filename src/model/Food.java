package model;

import java.util.ArrayList;
import java.util.List;

public class Food extends SaleProduct {
	public String menuName;
	private List<Ingredients> ingredients;

	public Food(int saleProductID, String name, Double price, String description, String type, String menuName) {
	    super(saleProductID, name, price, description, type);
	    this.menuName = menuName;
	    ingredients = new ArrayList<>();
	}
	    
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<Ingredients> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredients> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	
	
	
}


