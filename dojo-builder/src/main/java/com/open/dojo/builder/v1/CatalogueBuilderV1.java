package com.open.dojo.builder.v1;

import com.open.dojo.builder.model.Catalogue;
import com.open.dojo.builder.model.Periode;

public class CatalogueBuilderV1 {

    public CatalogueBuilderV1 of(Periode period) {
        throw new RuntimeException();
    }

    public CatalogueBuilderV1 article(String name, double price, String reference) {
        throw new RuntimeException();
    }

    public CatalogueBuilderV1 article(String name) {
        throw new RuntimeException();
    }

    public CatalogueBuilderV1 modele(String description, double price, String reference) {
        throw new RuntimeException();
    }

    public Catalogue build() {
        throw new RuntimeException();
    }

}
