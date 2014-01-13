package com.open.dojo.derivator.test;

import org.junit.Assert;
import org.junit.Test;

import com.open.dojo.derivator.Derivator;
import com.open.dojo.derivator.Expression;

public class TestDerivator {
	
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
	public void testAddMultExpressionString() {
		// x + 3
		Expression expr = new ExpressionBuilder().start().var("x").add().constant(3).end().mul().constant(10).build();
		Assert.assertEquals("(x + 3) * 10", expr.toString());
		
	}

	@Test
	public void testSimpleExpression() {
		// x + 3
		Expression expr = new ExpressionBuilder().var("x").add().constant(3).build();
		
		Expression derivative = new Derivator().derive(expr, "x");
		
		Assert.assertEquals(new ExpressionBuilder().constant(1).build(), derivative);
		
	}
	
	@Test
	public void testMultiplyExpression() {
		// x * 3
		Expression expr = new ExpressionBuilder().var("x").mul().constant(3).build();
		
		Expression derivative = new Derivator().derive(expr, "x");
		
		Assert.assertEquals(new ExpressionBuilder().constant(3).build(), derivative);
		
	}
	
	
	@Test
	public void testMultiplyConstants() {
		// 2 * 3
		Expression expr = new ExpressionBuilder().constant(2).mul().constant(3).build();
		
		Expression derivative = new Derivator().derive(expr, "x");
		
		Assert.assertEquals(new ExpressionBuilder().constant(0).build(), derivative);
		
	}
	
	@Test
	public void testComplexMultiplyExpression() {
		// x * x
		Expression expr = new ExpressionBuilder().var("x").mul().var("x").build();
		
		Expression derivative = new Derivator().derive(expr, "x");
		
		Assert.assertEquals(new ExpressionBuilder().var("x").add().var("x").build(), derivative);
		
	}
}
