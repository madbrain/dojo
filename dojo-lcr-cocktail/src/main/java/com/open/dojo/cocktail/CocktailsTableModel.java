package com.open.dojo.cocktail;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CocktailsTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 8827295225422992167L;
	
	private List<Cocktail> cocktails;

	public CocktailsTableModel(List<Cocktail> cocktails) {
		this.cocktails = cocktails;
	}

	public int getRowCount() {
		return cocktails.size();
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Nom";
		} else if (columnIndex == 1) {
			return "Exotique";
		} else {
			return "Prix";
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Cocktail cocktail = cocktails.get(rowIndex);
		if (columnIndex == 0) {
			return cocktail.getName();
		}
		if (columnIndex == 1) {
			return cocktail.isExotic() ? "Exotique" : "";
		}
		return cocktail.getPrice();
	}

}
