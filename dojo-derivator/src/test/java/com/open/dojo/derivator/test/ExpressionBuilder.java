package com.open.dojo.derivator.test;

import java.util.Stack;

import com.open.dojo.derivator.Expression;

public class ExpressionBuilder implements ExpectExpr, ExpectAfterTerm {
	
	private static class Group {
		
		Group parent;
		Stack<Expression> elements = new Stack<Expression>();
		Stack<Operator> operators = new Stack<Operator>();
		
		public Group(Group parent) {
			this.parent = parent;
		}

		public Group end() {
			parent.elements.push(build());
			return parent;
		}

		public void push(Operator operator) {
			// should test priority other than class
			if (operators.size() > 0
					&& operators.peek().getClass() == operator.getClass()) {
				makeOne();
				operators.push(operator);
			}
			operators.push(operator);
		}
		
		private void makeOne() {
			Operator previous = operators.pop();
			Expression right = elements.pop();
			Expression left = elements.pop();
			elements.push(previous.make(left, right));
		}

		public Expression build() {
			while (operators.size() > 0) {
				makeOne();
			}
			return elements.get(0);
		}

	}
	
	private interface Operator {

		Expression make(Expression left, Expression right);
	}
	
	private static class AddOperator implements Operator {

		@Override
		public Expression make(Expression left, Expression right) {
			return new Expression(Expression.ADD, left, right);
		}
		
	}
	
	private static class MulOperator implements Operator {

		@Override
		public Expression make(Expression left, Expression right) {
			return new Expression(Expression.MUL, left, right);
		}
		
	}

	private Group currentGroup = new Group(null);
	
	@Override
	public ExpectAfterTerm var(String name) {
		currentGroup.elements.push(new Expression(name));
		return this;
	}
	
	@Override
	public ExpectAfterTerm constant(int value) {
		currentGroup.elements.push(new Expression(value));
		return this;
	}

	@Override
	public ExpectExpr add() {
		currentGroup.push(new AddOperator());
		return this;
	}
	
	@Override
	public ExpectExpr mul() {
		currentGroup.push(new MulOperator());
		return this;
	}
	
	@Override
	public ExpectExpr start() {
		currentGroup = new Group(currentGroup);
		return this;
	}
	
	@Override
	public ExpectAfterTerm end() {
		currentGroup = currentGroup.end();
		return this;
	}
	
	@Override
	public Expression build() {
		return currentGroup.build();
	}
	
}
