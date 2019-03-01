package com.github.madbrain.dojo.cqlator;

import com.github.madbrain.dojo.cqlator.ast.Statement;

import java.util.List;

public class CQLParser {
    private CQLLexer lexer;

    public CQLParser(CQLLexer lexer) {
        this.lexer = lexer;
    }

    public List<Statement> parseStatements() {
        throw new RuntimeException("To be complted");
    }
}
