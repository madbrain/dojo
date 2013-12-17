package com.open.dojo.builder.model;

import java.util.ArrayList;
import java.util.List;


public class Catalogue {

	private Periode periode;
	private List<Article> articles = new ArrayList<Article>();

	public Catalogue(Periode periode, List<Article> articles) {
		this.periode = periode;
		this.articles.addAll(articles);
	}

	public List<Article> getArticles() {
		return articles ;
	}

	public Periode getPeriode() {
		return periode;
	}

}
