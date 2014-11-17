package com.open.dojo.cocktail;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {

	private int id;
	private String name;
	private List<QuantifiedIngredient> ingredients = new ArrayList<QuantifiedIngredient>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<QuantifiedIngredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<QuantifiedIngredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public boolean isExotic() {
		for (QuantifiedIngredient ingredient : ingredients) {
			if (ingredient.getIngredient().isExotic()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean containsAlcool() {
		for (QuantifiedIngredient ingredient : ingredients) {
			if (ingredient.getIngredient().getAlcoolRate() >= 0.0) {
				return true;
			}
		}
		return false;
	}

	public double getPrice() {
		double price = 0.0;
		for (QuantifiedIngredient ingredient : ingredients) {
			price += ingredient.getPrice();
		}
		if (! containsAlcool()) {
			price *= 1.1;
		} else if (isExotic()) {
			price *= 1.3;
		} else {
			price *= 1.2;
		}
		price = ((int)((price * 2) + 1) / 2.0) - 0.01;
		return price;
	}

}
