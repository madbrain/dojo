package com.open.dojo.derivator.test;

import org.junit.Assert;
import org.junit.Test;

import com.open.dojo.derivator.Expression;

public class TestExpressionBuilder {

	@Test
	public void testAddExpressionString() {
		// x + 3
		Expression expr = new ExpressionBuilder().var("x").add().constant(3).build();
		Assert.assertEquals("x + 3", expr.toString());
		
	}
	
	@Test
	public void testMultiplyExpressionString() {
		// x * 3
		Expression expr = new ExpressionBuilder().var("x").mul().constant(3).build();
		Assert.assertEquals("x * 3", expr.toString());
		
	}
	
	@Test
	public void testExpr1() {
		// x + 3 * 4
		Expression expr = new ExpressionBuilder()
			.var("x").add().constant(3).mul().constant(4).build();
		Assert.assertEquals("x + 3 * 4", expr.toString());
		
	}
	
	@Test
	public void testExpr2() {
		// (x + 3) * 4
		Expression expr = new ExpressionBuilder()
			.start().var("x").add().constant(3).end().mul().constant(4).build();
		Assert.assertEquals("(x + 3) * 4", expr.toString());
		
	}
	
	@Test
	public void testExpr3() {
		// (x + 3) * (4 + x * 2)
		Expression expr = new ExpressionBuilder()
			.start().var("x").add().constant(3).end()
			.mul().start().constant(4).add().var("x").mul().constant(2).end().build();
		Assert.assertEquals("(x + 3) * (4 + x * 2)", expr.toString());
		
	}
	
	@Test
	public void testExpr4() {
		// (x + 3) * (4 + 2 * (x + 2))
		Expression expr = new ExpressionBuilder()
			.start().var("x").add().constant(3).end()
				.mul()
			.start().constant(4).add().constant(2).mul()
				.start().var("x").add().constant(2).end()
			.end().build();
		Assert.assertEquals("(x + 3) * (4 + 2 * (x + 2))", expr.toString());
		
	}
}
