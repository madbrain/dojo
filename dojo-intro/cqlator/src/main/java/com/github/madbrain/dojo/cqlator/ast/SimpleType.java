package com.github.madbrain.dojo.cqlator.ast;

public class SimpleType implements Type {
    private String name;

    public SimpleType(String name) {
        this.name = name;
    }
}
