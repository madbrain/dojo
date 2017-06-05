package com.open.dojo.builder.v3;

public interface IArticleBuilder {

    /**
     * Ajoute un modèle à l'article
     * 
     * @param description
     * @param price
     * @param reference
     * @return
     */
    ICompleteArticleBuilder modele(String description, double price, String reference);

}
