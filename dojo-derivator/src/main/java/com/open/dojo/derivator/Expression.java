package com.open.dojo.derivator;

import java.util.ArrayList;
import java.util.List;

public class Expression {

	public static final int VAR = 0;
	public static final int CONST = 1;
	public static final int ADD = 2;
	public static final int MUL = 3;

	private int type;
	private String name;
	private int value;
	private List<Expression> children = new ArrayList<Expression>();

	public Expression(String name) {
		this.type = VAR;
		this.name = name;
	}

	public Expression(int value) {
		this.type = CONST;
		this.value = value;
	}

	public Expression(int type, Expression left, Expression right) {
		this.type = type;
		this.children.add(left);
		this.children.add(right);
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public Expression getChild(int index) {
		return children.get(index);
	}

	@Override
	public boolean equals(Object o) {
		Expression e = (Expression) o;
		if (e.type != type) {
			return false;
		}
		switch (type) {
		case Expression.VAR:
			return name.equals(e.name);
		case Expression.CONST:
			return e.value == value;
		case Expression.ADD:
		case Expression.MUL:
			return children.get(0).equals(e.children.get(0));
		default:
			throw new IllegalStateException("unknown expression " + type);
		}
	}

	@Override
	public String toString() {
		switch (type) {
		case VAR:
			return name;
		case CONST:
			return String.valueOf(value);
		case ADD:
			return children.get(0) + " + " + children.get(1);
		case MUL: {
			StringBuilder builder = new StringBuilder();
			putParents(builder, children.get(0));
			builder.append(" * ");
			putParents(builder, children.get(1));
			return builder.toString();
		}
		}
		throw new IllegalStateException("unknown type " + type);
	}

	private static void putParents(StringBuilder builder, Expression expr) {
		if (expr.getType() == Expression.ADD) {
			builder.append("(");
		}
		builder.append(expr);
		if (expr.getType() == Expression.ADD) {
			builder.append(")");
		}
	}

}
