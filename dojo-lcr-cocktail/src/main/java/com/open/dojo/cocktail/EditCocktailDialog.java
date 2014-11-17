package com.open.dojo.cocktail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditCocktailDialog extends JDialog {

	private static final long serialVersionUID = -1274791311484131697L;
	
	private boolean isOk = false;

	private Cocktail cocktail;
	private List<QuantifiedIngredient> ingredients;

	private JLabel errorLabel;

	private JTextField nameField;

	private JButton addButton;
	private JButton removeButton;
	private JTable table;
	private IngredientQuantitiesTableModel tableModel;

	private JButton okButton;
	private JButton cancelButton;



	public EditCocktailDialog(Cocktail cocktail) {
		super((JFrame)null, "Cocktail", true);
		
		this.cocktail = cocktail;
		this.ingredients = new ArrayList<QuantifiedIngredient>(cocktail.getIngredients());
		
		errorLabel = new JLabel();
		errorLabel.setAlignmentX(0.0f);
		errorLabel.setForeground(Color.RED);
		getContentPane().add(errorLabel, BorderLayout.NORTH);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		JPanel formPanel = new JPanel(new GridLayout(1, 2));
		formPanel.add(new JLabel("Nom:"), gc(0, 0));
		formPanel.add(nameField = new JTextField(), gc(0, 1));
		mainPanel.add(formPanel);
		
		JPanel tableButtonPanel = new JPanel();
		tableButtonPanel.add(addButton = new JButton("Ajouter"));
		tableButtonPanel.add(removeButton = new JButton("Supprimer"));
		mainPanel.add(tableButtonPanel);
		
		tableModel = new IngredientQuantitiesTableModel(ingredients);
		table = new JTable(tableModel);
		mainPanel.add(new JScrollPane(table));
		
		getContentPane().add(mainPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton = new JButton("OK"));
		buttonPanel.add(cancelButton = new JButton("Annuler"));
		getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
		
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (validateForm()) {
					dialogToObject();
					isOk = true;
					dispose();
				}
			}

		});
		
		cancelButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ingredients.add(new QuantifiedIngredient());
				tableModel.fireTableDataChanged();
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ingredients.remove(table.convertRowIndexToModel(table.getSelectedRow()));
				tableModel.fireTableDataChanged();
			}
		});
		removeButton.setEnabled(false);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				removeButton.setEnabled(table.getSelectedRow() >= 0);
			}
		});
		table.getColumnModel().getColumn(0).setCellRenderer(new IngredientCellRenderer());
		table.getColumnModel().getColumn(0).setCellEditor(new IngredientCellEditor());
		
		objectToDialog();
		
		pack();
	}

	public boolean validateForm() {
		if (nameField.getText().isEmpty()) {
			errorLabel.setText("Le nom est obligatoire");
			pack();
			return false;
		}
		errorLabel.setText("");
		pack();
		return true;
	}
	
	public void objectToDialog() {
		nameField.setText(cocktail.getName());
	}
	
	public void dialogToObject() {
		cocktail.setName(nameField.getText());
		cocktail.setIngredients(ingredients);
	}
	
	public static GridBagConstraints gc(int row, int col) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = col;
		gbc.gridy = row;
		return gbc;
	}
	
	public boolean open() {
		setVisible(true);
		return isOk;
	}

}
