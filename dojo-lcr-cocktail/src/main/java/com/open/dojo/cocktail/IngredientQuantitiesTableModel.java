package com.open.dojo.cocktail;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IngredientQuantitiesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6104593125807679572L;
	
	private List<QuantifiedIngredient> ingredients;

	public IngredientQuantitiesTableModel(List<QuantifiedIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public int getRowCount() {
		return ingredients.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Ingrédient";
		}
		return "Quantité";
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		QuantifiedIngredient ingredient = ingredients.get(rowIndex);
		if (columnIndex == 0) {
			if (ingredient.getIngredient() != null) {
				return ingredient.getIngredient();
			}
			return null;
		}
		return ingredient.getQuantity();
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Ingredient.class;
		}
		return Double.class;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		QuantifiedIngredient ingredient = ingredients.get(rowIndex);
		if (columnIndex == 0) {
			ingredient.setIngredient((Ingredient) aValue);
		} else {
			ingredient.setQuantity(((Double)aValue).doubleValue());
		}
	}

}
