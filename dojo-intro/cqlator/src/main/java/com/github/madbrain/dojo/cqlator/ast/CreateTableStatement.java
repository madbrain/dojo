package com.github.madbrain.dojo.cqlator.ast;

import java.util.List;

public class CreateTableStatement implements Statement {
    public CreateTableStatement(Name tableName, ColumnDefinition...columnDefinitions) {

    }

    public Name getTableName() {
        return null;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return null;
    }
}
