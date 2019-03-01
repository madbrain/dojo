package com.github.madbrain.dojo.cqlator.ast;

import com.github.madbrain.dojo.cqlator.ast.SimpleType;
import com.github.madbrain.dojo.cqlator.ast.Type;

public class Types {
    public static final Type TEXT = new SimpleType("text");
    public static final Type DATE = new SimpleType("date");
    public static final Type UUID = new SimpleType("uuid");
}
