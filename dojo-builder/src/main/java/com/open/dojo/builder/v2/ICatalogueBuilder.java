package com.open.dojo.builder.v2;

import com.open.dojo.builder.model.Catalogue;

public interface ICatalogueBuilder {

    /**
     * Ajout un article avec un seul modèle par défaut
     * 
     * @param name
     * @param price
     * @param reference
     * @return
     */
    ICatalogueBuilder article(String name, double price, String reference);

    /**
     * Ajoute un article pouvant comporter plusieurs modèles
     * 
     * @param name
     * @return
     */
    IArticleBuilder article(String name);

    /**
     * Construit le catalogue
     * 
     * @return
     */
    Catalogue build();

}
