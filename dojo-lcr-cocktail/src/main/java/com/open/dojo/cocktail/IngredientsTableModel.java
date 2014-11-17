package com.open.dojo.cocktail;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IngredientsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4113522600691967227L;
	
	private List<Ingredient> ingredients;

	public IngredientsTableModel(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public int getRowCount() {
		return ingredients.size();
	}

	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Nom";
		}
		return "Prix";
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Ingredient ingredient = ingredients.get(rowIndex);
		if (columnIndex == 0) {
			return ingredient.getName();
		}
		return ingredient.getUnitPrice();
	}

}
