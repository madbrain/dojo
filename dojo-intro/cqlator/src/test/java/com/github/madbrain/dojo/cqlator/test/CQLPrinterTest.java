package com.github.madbrain.dojo.cqlator.test;

import com.github.madbrain.dojo.cqlator.CQLPrinter;
import com.github.madbrain.dojo.cqlator.ast.Statement;
import com.github.madbrain.dojo.cqlator.ast.*;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class CQLPrinterTest {

    @Test
    public void canPrintCQL() {
        List<Statement> statements = Collections.singletonList(new CreateTableStatement(new Name("tableName"),
                new SimpleColumnDefinition("id", Types.UUID),
                new SimpleColumnDefinition("title", Types.TEXT),
                new PrimaryKeyDefinition(new PartitionKey("id"))));

        CQLPrinter printer = new CQLPrinter();

        String content = printer.print(statements);

        Assertions.assertThat(content).isEqualTo(
                "CREATE TABLE tableName (\n" +
                "  id uuid,\n" +
                "  title text,\n" +
                " PRIMARY KEY (id)\n" +
                ");\n");
    }
}
