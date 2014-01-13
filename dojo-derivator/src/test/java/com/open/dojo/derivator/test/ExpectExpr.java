package com.open.dojo.derivator.test;

public interface ExpectExpr {
	ExpectAfterTerm var(String name);
	ExpectAfterTerm constant(int value);
	ExpectExpr start();
}