package com.open.dojo.builder.model;

import java.util.ArrayList;
import java.util.List;

public class Article {

    private static class NoRemise implements RemiseFunction {

        @Override
        public double compute(double quantity) {
            return 0;
        }

    }

    private String name;
    private List<Modele> modeles = new ArrayList<Modele>();
    private RemiseFunction remise = new NoRemise();

    public List<Modele> getModels() {
        return modeles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RemiseFunction getRemise() {
        return remise;
    }

    public void setRemise(RemiseFunction remise) {
        this.remise = remise;
    }

    public double buy(int quantity) {
        if (modeles.size() > 1) {
            throw new RuntimeException("choose a model to buy");
        }
        return modeles.get(0).getPrice() * quantity * (1 - remise.compute(quantity) / 100);
    }
}
