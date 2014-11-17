package com.open.dojo.cocktail;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class IngredientsPanel extends JPanel {

	private static final long serialVersionUID = 1478575575346969064L;
	
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;

	private IngredientsTableModel tableModel;
	
	private List<Ingredient> ingredients;

	public IngredientsPanel() {
		setLayout(new BorderLayout());
		
		ingredients = Database.getAllIngredients();
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(addButton = new JButton("Ajouter"));
		toolBar.add(editButton = new JButton("Modifier"));
		toolBar.add(removeButton = new JButton("Supprimer"));
		add(toolBar, BorderLayout.PAGE_START);
		
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Ingredient ingredient = new Ingredient();
				EditIngredientDialog dialog = new EditIngredientDialog(ingredient);
				if (dialog.open()) {
					addIngredient(ingredient);
				}
			}

		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Ingredient ingredient = ingredients.get(table.convertRowIndexToModel(table.getSelectedRow()));
				EditIngredientDialog dialog = new EditIngredientDialog(ingredient);
				if (dialog.open()) {
					modifyIngredient(ingredient);
				}
			}

		});
		editButton.setEnabled(false);
		
		removeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Ingredient ingredient = ingredients.get(table.convertRowIndexToModel(table.getSelectedRow()));
				removeIngredient(ingredient);
			}

		});
		removeButton.setEnabled(false);

		tableModel = new IngredientsTableModel(ingredients);
		table = new JTable(tableModel);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				editButton.setEnabled(table.getSelectedRow() >= 0);
				removeButton.setEnabled(table.getSelectedRow() >= 0);
			}
		});
	}
	
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
		Database.saveOrUpdateIngredient(ingredient);
		tableModel.fireTableDataChanged();
	}
	
	public void modifyIngredient(Ingredient ingredient) {
		Database.saveOrUpdateIngredient(ingredient);
		tableModel.fireTableDataChanged();
	}
	
	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
		Database.removeIngredient(ingredient);
		tableModel.fireTableDataChanged();
	}
}
