package com.open.dojo.cocktail;

public class QuantifiedIngredient {

	private Ingredient ingredient;
	private double quantity;
	
	public QuantifiedIngredient(Ingredient ingredient, double quantity) {
		this.ingredient = ingredient;
		this.quantity = quantity;
	}

	public QuantifiedIngredient() {
	}

	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return ingredient.getUnitPrice() * quantity;
	}
	
}
