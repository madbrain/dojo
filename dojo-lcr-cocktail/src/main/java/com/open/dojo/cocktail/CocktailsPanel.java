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

public class CocktailsPanel extends JPanel {

	private static final long serialVersionUID = 4100374461874917272L;
	
	private JTable table;
	private JButton addButton;
	private JButton editButton;
	private JButton removeButton;

	private CocktailsTableModel tableModel;

	private List<Cocktail> cocktails;

	public CocktailsPanel() {
		setLayout(new BorderLayout());
		
		cocktails = Database.getAllCocktails();
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(addButton = new JButton("Ajouter"));
		toolBar.add(editButton = new JButton("Modifier"));
		toolBar.add(removeButton = new JButton("Supprimer"));
		add(toolBar, BorderLayout.PAGE_START);
		
		addButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Cocktail cocktail = new Cocktail();
				EditCocktailDialog dialog = new EditCocktailDialog(cocktail);
				if (dialog.open()) {
					addCocktail(cocktail);
				}
			}

		});
		
		editButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Cocktail cocktail = cocktails.get(table.convertRowIndexToModel(table.getSelectedRow()));
				EditCocktailDialog dialog = new EditCocktailDialog(cocktail);
				if (dialog.open()) {
					modifyCocktail(cocktail);
				}
			}

		});
		editButton.setEnabled(false);
		
		removeButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Cocktail cocktail = cocktails.get(table.convertRowIndexToModel(table.getSelectedRow()));
				removeCocktail(cocktail);
			}

		});
		removeButton.setEnabled(false);

		tableModel = new CocktailsTableModel(cocktails);
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
	
	public void addCocktail(Cocktail cocktail) {
		cocktails.add(cocktail);
		Database.saveOrUpdateCocktail(cocktail);
		tableModel.fireTableDataChanged();
	}
	
	public void modifyCocktail(Cocktail cocktail) {
		Database.saveOrUpdateCocktail(cocktail);
		tableModel.fireTableDataChanged();
	}
	
	public void removeCocktail(Cocktail cocktail) {
		cocktails.remove(cocktail);
		Database.removeCocktail(cocktail);
		tableModel.fireTableDataChanged();
	}
}
