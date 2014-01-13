package com.open.dojo.derivator;

public class Derivator {

	public Expression derive(Expression expr, String variableName) {
		return simplify(compute(expr, variableName));
	}

	private Expression compute(Expression expr, String variableName) {
		switch (expr.getType()) {
		case Expression.VAR:
			if (expr.getName().equals(variableName)) {
				return new Expression(1);
			}
			return new Expression(0);

		case Expression.CONST:
			return new Expression(0);

		case Expression.ADD: {
			// d(u+v) = d(u) + d(v)
			Expression left = compute(expr.getChild(0), variableName);
			Expression right = compute(expr.getChild(1), variableName);
			return new Expression(Expression.ADD, left, right);
		}
		case Expression.MUL:
			// d(u*v) = u*d(v) + v*d(u)
			Expression left = compute(expr.getChild(0), variableName);
			Expression right = compute(expr.getChild(1), variableName);
			return new Expression(Expression.ADD,
					new Expression(Expression.MUL, expr.getChild(0), right),
					new Expression(Expression.MUL, expr.getChild(1), left));
		default:
			throw new IllegalArgumentException("unknown expression " + expr.getType());
		}
	}

	private Expression simplify(Expression expr) {
		switch (expr.getType()) {
		case Expression.VAR:
		case Expression.CONST:
			return expr;

		case Expression.ADD: {
			Expression left = simplify(expr.getChild(0));
			Expression right = simplify(expr.getChild(1));

			// x + 0 => x
			// 0 + x => x
			if (isConstant(left, 0)) {
				return right;
			}
			if (isConstant(right, 0)) {
				return left;
			}
			return new Expression(Expression.ADD, left, right);
		}

		case Expression.MUL:
			Expression left = simplify(expr.getChild(0));
			Expression right = simplify(expr.getChild(1));

			// x * 0 => 0
			// 0 * x => 0
			// x * 1 => x
			// 1 * x => x
			if (isConstant(left, 0)) {
				return new Expression(0);
			}
			if (isConstant(right, 0)) {
				return new Expression(0);
			}
			if (isConstant(left, 1)) {
				return right;
			}
			if (isConstant(right, 1)) {
				return left;
			}
			return new Expression(Expression.ADD, left, right);

		default:
			throw new IllegalArgumentException("unknown expression " + expr.getType());
		}
	}

	private boolean isConstant(Expression expr, int value) {
		if (expr.getType() == Expression.CONST) {
			return expr.getValue() == value;
		}
		return false;
	}

}
