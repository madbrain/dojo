package com.open.dojo.builder.v2;

public interface IArticleBuilder extends ICatalogueBuilder {

    /**
     * Ajoute un modèle à l'article
     * 
     * @param description
     * @param price
     * @param reference
     * @return
     */
    IArticleBuilder modele(String description, double price, String reference);

}
