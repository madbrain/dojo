package com.github.madbrain.dojo.cqlator.test;

import com.github.madbrain.dojo.cqlator.CQLLexer;
import com.github.madbrain.dojo.cqlator.CQLMerger;
import com.github.madbrain.dojo.cqlator.CQLParser;
import com.github.madbrain.dojo.cqlator.ast.Statement;
import com.github.madbrain.dojo.cqlator.ast.CreateTableStatement;
import com.github.madbrain.dojo.cqlator.ast.Name;
import com.github.madbrain.dojo.cqlator.ast.SimpleColumnDefinition;
import com.github.madbrain.dojo.cqlator.ast.Types;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class CQLMergerTest {
    @Test
    public void canMergeStatements() {
        String content = "CREATE TABLE tableName (id uuid PRIMARY KEY); ALTER TABLE tableName ADD title text;";

        CQLParser parser = new CQLParser(new CQLLexer(content));

        List<Statement> statements = parser.parseStatements();

        CQLMerger merger = new CQLMerger();

        List<Statement> result = merger.merge(statements);

        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0)).isInstanceOf(CreateTableStatement.class);
        CreateTableStatement create = (CreateTableStatement) result.get(0);
        Assertions.assertThat(create.getTableName()).isEqualToComparingFieldByField(new Name("tableName"));
        Assertions.assertThat(create.getColumnDefinitions()).hasSize(2);
        Assertions.assertThat(create.getColumnDefinitions().get(0)).isInstanceOf(SimpleColumnDefinition.class);
        SimpleColumnDefinition column1 = (SimpleColumnDefinition) create.getColumnDefinitions().get(0);
        Assertions.assertThat(column1.getColumnName()).isEqualTo("id");
        Assertions.assertThat(column1.getType()).isEqualTo(Types.UUID);
        Assertions.assertThat(column1.isPrimary()).isTrue();
        Assertions.assertThat(create.getColumnDefinitions().get(1)).isInstanceOf(SimpleColumnDefinition.class);
        SimpleColumnDefinition column2 = (SimpleColumnDefinition) create.getColumnDefinitions().get(1);
        Assertions.assertThat(column2.getColumnName()).isEqualTo("title");
        Assertions.assertThat(column2.getType()).isEqualTo(Types.DATE);
        Assertions.assertThat(column2.isPrimary()).isFalse();
    }
}
