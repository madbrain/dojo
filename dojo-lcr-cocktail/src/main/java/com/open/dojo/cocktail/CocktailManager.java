package com.open.dojo.cocktail;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class CocktailManager extends JFrame {

	private static final long serialVersionUID = -8934756829362707852L;
	
	public CocktailManager() {
		super("Cocktail Manager");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Ingrédients", new IngredientsPanel());
		tabbedPane.add("Cocktails", new CocktailsPanel());
		tabbedPane.setSelectedIndex(1);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		new CocktailManager().setVisible(true);
	}
}
