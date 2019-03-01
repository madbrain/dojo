package com.github.madbrain.dojo.cqlator.ast;

public class Name {
    private String keyspace;
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public Name(String keyspace, String name) {
        this.keyspace = keyspace;
        this.name = name;
    }

    public String getKeyspace() {
        return keyspace;
    }

    public String getName() {
        return name;
    }
}
