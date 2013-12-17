package com.open.dojo.builder.model;

import java.util.ArrayList;
import java.util.List;

public class Article {

	private String name;
	private List<Modele> modeles = new ArrayList<Modele>();

	public Article(String name, List<Modele> modeles) {
		this.name = name;
		this.modeles.addAll(modeles);
	}

	public String getName() {
		return name;
	}

	public List<Modele> getModels() {
		return modeles;
	}

}
