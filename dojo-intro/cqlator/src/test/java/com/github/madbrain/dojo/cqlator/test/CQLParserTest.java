package com.github.madbrain.dojo.cqlator.test;

import com.github.madbrain.dojo.cqlator.*;
import com.github.madbrain.dojo.cqlator.ast.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class CQLParserTest {

    @Test
    public void canParseAddAlterStatement() {
        String content = "ALTER TABLE table_name ADD column_name text;";
        CQLParser parser = new CQLParser(new CQLLexer(content));

        Statement statement = parser.parseStatements().get(0);

        Assertions.assertThat(statement).isInstanceOf(AlterTableStatement.class);
        AlterTableStatement alter = (AlterTableStatement) statement;
        Assertions.assertThat(alter.getTableName()).isEqualToComparingFieldByField(new Name("table_name"));
        Assertions.assertThat(alter.getOperation()).isInstanceOf(AddColumnOperation.class);
        AddColumnOperation operation = (AddColumnOperation) alter.getOperation();
        Assertions.assertThat(operation.getColumnName()).isEqualTo("column_name");
        Assertions.assertThat(operation.getType()).isEqualTo(Types.TEXT);
    }

    @Test
    public void canParseDropAlterStatement() {
        String content = "ALTER TABLE keyspace_name.table_name DROP column_name;";
        CQLParser parser = new CQLParser(new CQLLexer(content));

        Statement statement = parser.parseStatements().get(0);

        Assertions.assertThat(statement).isInstanceOf(AlterTableStatement.class);
        AlterTableStatement alter = (AlterTableStatement) statement;
        Assertions.assertThat(alter.getTableName()).isEqualToComparingFieldByField(new Name("keyspace_name", "table_name"));
        Assertions.assertThat(alter.getOperation()).isInstanceOf(DropColumnOperation.class);
        DropColumnOperation operation = (DropColumnOperation) alter.getOperation();
        Assertions.assertThat(operation.getColumnName()).isEqualTo("column_name");
    }

    @Test
    public void canParseCreateStatement() {
        String content = "CREATE TABLE keyspace_name.table_name ( column_name text primary key, column2 date);";
        CQLParser parser = new CQLParser(new CQLLexer(content));

        Statement statement = parser.parseStatements().get(0);

        Assertions.assertThat(statement).isInstanceOf(CreateTableStatement.class);
        CreateTableStatement create = (CreateTableStatement) statement;
        Assertions.assertThat(create.getTableName()).isEqualToComparingFieldByField(new Name("keyspace_name", "table_name"));
        Assertions.assertThat(create.getColumnDefinitions()).hasSize(2);
        Assertions.assertThat(create.getColumnDefinitions().get(0)).isInstanceOf(SimpleColumnDefinition.class);
        SimpleColumnDefinition simple1 = (SimpleColumnDefinition) create.getColumnDefinitions().get(0);
        Assertions.assertThat(simple1.getColumnName()).isEqualTo("column_name");
        Assertions.assertThat(simple1.getType()).isEqualTo(Types.TEXT);
        Assertions.assertThat(simple1.isPrimary()).isEqualTo(true);
        Assertions.assertThat(create.getColumnDefinitions().get(1)).isInstanceOf(SimpleColumnDefinition.class);
        SimpleColumnDefinition simple2 = (SimpleColumnDefinition) create.getColumnDefinitions().get(1);
        Assertions.assertThat(simple2.getColumnName()).isEqualTo("column2");
        Assertions.assertThat(simple2.getType()).isEqualTo(Types.DATE);
        Assertions.assertThat(simple2.isPrimary()).isEqualTo(false);
    }

    @Test
    public void canParseCreateStatementWithPartitionKey() {
        String content = "CREATE TABLE keyspace_name.table_name ( column0 text, column1 uuid, column2 date, PRIMARY KEY ((column0, column1), column2));";
        CQLParser parser = new CQLParser(new CQLLexer(content));

        Statement statement = parser.parseStatements().get(0);

        Assertions.assertThat(statement).isInstanceOf(CreateTableStatement.class);
        CreateTableStatement create = (CreateTableStatement) statement;
        Assertions.assertThat(create.getTableName()).isEqualToComparingFieldByField(new Name("keyspace_name", "table_name"));
        Assertions.assertThat(create.getColumnDefinitions()).hasSize(3);
        Assertions.assertThat(create.getColumnDefinitions().get(2)).isInstanceOf(PrimaryKeyDefinition.class);
        PrimaryKeyDefinition primary = (PrimaryKeyDefinition) create.getColumnDefinitions().get(1);
        Assertions.assertThat(primary.getElements()).hasSize(2);
        Assertions.assertThat(primary.getElements().get(0)).isInstanceOf(PartitionKey.class);
        PartitionKey partitionKey = (PartitionKey) primary.getElements().get(0);
        Assertions.assertThat(partitionKey.getColumns()).containsExactly("column0", "column1");
        Assertions.assertThat(primary.getElements().get(1)).isInstanceOf(ClusteringKey.class);
        ClusteringKey clusteringKey = (ClusteringKey) primary.getElements().get(1);
        Assertions.assertThat(clusteringKey.getName()).isEqualTo("column2");
    }

    @Test
    public void canParseMultipleStatements() {
        String content = "CREATE TABLE tableName (id uuid PRIMARY KEY); ALTER TABLE tableName ADD title text;";
        CQLParser parser = new CQLParser(new CQLLexer(content));

        List<Statement> statements = parser.parseStatements();

        Assertions.assertThat(statements).hasSize(2);
    }
}
