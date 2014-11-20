package com.open.dojo.cocktail;

import javax.swing.table.DefaultTableCellRenderer;

public class IngredientCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -4442863593715958755L;

	public void setValue(Object value) {
        setText((value == null) ? "" : ((Ingredient)value).getName());
    }

}
