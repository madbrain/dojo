package com.github.madbrain.dojo.cqlator;

public enum TokenType {
    SELECT,
    FROM,
    WHERE,
    IDENT,
    EOF, STRING_LITERAL, CREATE, TABLE, KEYSPACE, PRIMARY, DOT, COMA, SEMI, L_PAR, R_PAR;
}
