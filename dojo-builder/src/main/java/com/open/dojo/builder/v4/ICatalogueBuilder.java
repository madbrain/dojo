package com.open.dojo.builder.v4;

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
    IOptionalRemiseBuilder article(String name, double price, String reference);

    /**
     * Ajoute un article pouvant comporter plusieurs modèles
     * 
     * @param name
     * @return
     */
    IArticleWithoutRemiseBuilder article(String name);

    /**
     * Construit le catalogue
     * 
     * @return
     */
    Catalogue build();

}
