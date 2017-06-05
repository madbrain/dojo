package com.open.dojo.builder.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.open.dojo.builder.model.Article;
import com.open.dojo.builder.model.Catalogue;
import com.open.dojo.builder.model.Modele;
import com.open.dojo.builder.model.Periode;
import com.open.dojo.builder.v1.CatalogueBuilderV1;
import com.open.dojo.builder.v2.CatalogueBuilderV2;
import com.open.dojo.builder.v3.CatalogueBuilderV3;
import com.open.dojo.builder.v4.CatalogueBuilderV4;

public class TestDocumentParser {

    public Catalogue createReferenceCatalogue() {

        Catalogue catalogue = new Catalogue();
        catalogue.setPeriode(Periode.PrintempsEte);

        Article article1 = new Article();
        article1.setName("Collier pour chien");
        Modele modele11 = new Modele();
        modele11.setPrice(10.0);
        modele11.setReference("CH1");
        article1.getModels().add(modele11);
        catalogue.getArticles().add(article1);

        Article article2 = new Article();
        article2.setName("Antipuce");
        catalogue.getArticles().add(article2);

        Modele model21 = new Modele();
        model21.setDescription("10ml");
        model21.setPrice(20.0);
        model21.setReference("CH2A");
        article2.getModels().add(model21);

        Modele model22 = new Modele();
        model22.setDescription("20ml");
        model22.setPrice(35.0);
        model22.setReference("CH2B");
        article2.getModels().add(model22);

        Article article3 = new Article();
        article3.setName("Nonosse");
        Modele modele31 = new Modele();
        modele31.setPrice(5.0);
        modele31.setReference("CH3");
        article3.getModels().add(modele31);
        catalogue.getArticles().add(article3);

        return catalogue;
    }

    @Test
    public void testCatalogueBuilderV1() {

        Catalogue catalogue = new CatalogueBuilderV1()
                .of(Periode.PrintempsEte)
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .modele("10ml", 20, "CH2A")
                .modele("20ml", 35, "CH2B")
                .article("Nonosse", 5, "CH3")
                .build();

        Assertions.assertThat(catalogue)
                .isEqualToComparingFieldByFieldRecursively(createReferenceCatalogue());

    }

    /**
     * Le builder V1 permet de creer un catalogue sans définir la période et des articles incomplets
     */
    @Test
    public void testCatalogueBuilderV1_Bad() {

        Catalogue catalogue = new CatalogueBuilderV1()
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .article("Nonosse", 5, "CH3")
                .build();

        Assertions.assertThat(catalogue).isNotNull();

    }

    @Test
    public void testCatalogueBuilderV2() {

        Catalogue catalogue = new CatalogueBuilderV2()
                .of(Periode.PrintempsEte)
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .modele("10ml", 20, "CH2A")
                .modele("20ml", 35, "CH2B")
                .article("Nonosse", 5, "CH3")
                .build();

        Assertions.assertThat(catalogue)
                .isEqualToComparingFieldByFieldRecursively(createReferenceCatalogue());

    }

    /**
     * Le builder V2 permet toujours de créer des articles incomplets
     */
    @Test
    public void testCatalogueBuilderV2_Bad() {

        Catalogue catalogue = new CatalogueBuilderV2()
                .of(Periode.PrintempsEte)
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .article("Nonosse", 5, "CH3")
                .build();

        Assertions.assertThat(catalogue).isNotNull();
    }

    @Test
    public void testCatalogueBuilderV3() {

        Catalogue catalogue = new CatalogueBuilderV3()
                .of(Periode.PrintempsEte)
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .modele("10ml", 20, "CH2A")
                .modele("20ml", 35, "CH2B")
                .article("Nonosse", 5, "CH3")
                .build();

        Assertions.assertThat(catalogue)
                .isEqualToComparingFieldByFieldRecursively(createReferenceCatalogue());

    }

    /**
     * Le builder V4 permet d'indiquer le calcul d'une remise
     */
    @Test
    public void testCatalogueBuilderV4() {

        Catalogue catalogue = new CatalogueBuilderV4()
                .of(Periode.PrintempsEte)
                .article("Collier pour chien", 5, "CH1")
                .article("Antipuce")
                .modele("10ml", 20, "CH2A")
                .modele("20ml", 35, "CH2B")
                .article("Nonosse", 5, "CH3")
                .remise(quantity -> quantity > 15 ? 10.0 : 0.0)
                .build();

        Assertions.assertThat(catalogue.getArticle("Nonosse").get().buy(20))
                .isEqualTo(20 * 5 * (1 - 10.0 / 100));

    }
}
