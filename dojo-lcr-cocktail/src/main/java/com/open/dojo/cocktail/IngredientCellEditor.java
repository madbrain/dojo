package com.open.dojo.cocktail;

import java.awt.Component;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class IngredientCellEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -318609928194079463L;

	public IngredientCellEditor() {
		super(new JComboBox());
		((JComboBox)editorComponent).setRenderer(new BasicComboBoxRenderer() {
			
			private static final long serialVersionUID = -4995508238588251328L;

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				setText(value == null ? "" : ((Ingredient)value).getName());
				return this;
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			 boolean isSelected,
			 int row, int column) {
		List<Ingredient> ingredients = Database.getAllIngredients();
		for (int i = 0; i < table.getRowCount(); ++i) {
			if (i != row) {
				ingredients.remove(table.getValueAt(i, 0));
			}
		}
		((JComboBox)editorComponent).setModel(new DefaultComboBoxModel(ingredients.toArray(new Ingredient[ingredients.size()])));
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}
}
