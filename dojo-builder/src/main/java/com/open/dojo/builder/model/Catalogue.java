package com.open.dojo.builder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Catalogue {

    private Periode periode;
    private List<Article> articles = new ArrayList<Article>();

    public List<Article> getArticles() {
        return articles;
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

    public Optional<Article> getArticle(String name) {
        return articles.stream().filter(article -> article.getName().equals(name)).findFirst();
    }

}
