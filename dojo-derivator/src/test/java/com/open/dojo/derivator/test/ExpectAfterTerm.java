package com.open.dojo.derivator.test;

import com.open.dojo.derivator.Expression;

public interface ExpectAfterTerm {
	Expression build();
	ExpectAfterTerm end();
	ExpectExpr add();
	ExpectExpr mul();
}