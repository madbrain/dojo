package com.open.dojo.cocktail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditIngredientDialog extends JDialog {
	private static final long serialVersionUID = 1794138708719100401L;
	
	private static final String[] UNIT_NAMES = new String[] {
		"litre (l)",
		"millilitre (ml)",
		"cuillerée",
		"pincée",
		"pièce"
	};
	
	private static final String[] CONTINENT_NAMES = new String[] {
		"",
		"Europe",
		"Amérique du Nord",
		"Amérique du Sud",
		"Afrique",
		"Asie",
		"Océanie"
	};

	private JLabel errorLabel;
	private JTextField nameField;
	private JComboBox unit;
	private JTextField alcoolRate;
	private JComboBox origin;
	private JTextField unitPrice;

	private JButton okButton;
	private JButton cancelButton;
	private boolean isOk = false;

	private Ingredient ingredient;

	public EditIngredientDialog(Ingredient ingredient) {
		super((JFrame) null, "Ingrédient", true);
		
		this.ingredient = ingredient;
		
		errorLabel = new JLabel();
		errorLabel.setAlignmentX(0.0f);
		errorLabel.setForeground(Color.RED);
		getContentPane().add(errorLabel, BorderLayout.NORTH);
		
		JPanel formPanel = new JPanel(new GridLayout(5, 2));
		formPanel.add(new JLabel("Nom:"), gc(0, 0));
		formPanel.add(nameField = new JTextField(), gc(0, 1));
		formPanel.add(new JLabel("Unité:"), gc(1, 0));
		formPanel.add(unit = new JComboBox(UNIT_NAMES), gc(1, 1));
		formPanel.add(new JLabel("Taux d'alcool:"), gc(2, 0));
		formPanel.add(alcoolRate = new JTextField(), gc(2, 1));
		formPanel.add(new JLabel("Origine:"), gc(3, 0));
		formPanel.add(origin = new JComboBox(CONTINENT_NAMES), gc(3, 1));
		formPanel.add(new JLabel("Prix unitaire:"), gc(4, 0));
		formPanel.add(unitPrice = new JTextField(), gc(4, 1));
		getContentPane().add(formPanel,BorderLayout.CENTER);
	
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
		
		objectToDialog();
		
		pack();
	}
	
	public void objectToDialog() {
		nameField.setText(ingredient.getName());
		unit.setSelectedIndex(ingredient.getUnit().ordinal());
		alcoolRate.setText(String.valueOf(ingredient.getAlcoolRate()));
		origin.setSelectedIndex(ingredient.getOrigin().ordinal());
		unitPrice.setText(String.valueOf(ingredient.getUnitPrice()));
	}
	
	public void dialogToObject() {
		ingredient.setName(nameField.getText());
		ingredient.setUnit(Unit.values()[unit.getSelectedIndex()]);
		ingredient.setAlcoolRate(Double.parseDouble(alcoolRate.getText()));
		ingredient.setOrigin(Origin.values()[origin.getSelectedIndex()]);
		ingredient.setUnitPrice(Double.parseDouble(unitPrice.getText()));
	}
	
	public boolean validateForm() {
		if (nameField.getText().isEmpty()) {
			errorLabel.setText("Le nom est obligatoire");
			pack();
			return false;
		}
		if (unit.getSelectedIndex() <= 1) {
			try {
				Double.parseDouble(alcoolRate.getText());
			} catch (NumberFormatException e) {
				errorLabel.setText("Le taux d'alcool est invalide");
				pack();
				return false;
			}
		}
		try {
			Double.parseDouble(unitPrice.getText());
		} catch (NumberFormatException e) {
			errorLabel.setText("Le prix unitaire est invalide");
			pack();
			return false;
		}
		errorLabel.setText("");
		pack();
		return true;
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
