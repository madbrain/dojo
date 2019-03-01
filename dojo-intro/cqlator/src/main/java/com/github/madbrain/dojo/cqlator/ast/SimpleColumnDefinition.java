package com.github.madbrain.dojo.cqlator.ast;

public class SimpleColumnDefinition implements ColumnDefinition {
    public SimpleColumnDefinition(String name, Type type) {

    }

    public String getColumnName() {
        return null;
    }

    public Type getType() {
        return null;
    }

    public boolean isPrimary() {
        return false;
    }
}
