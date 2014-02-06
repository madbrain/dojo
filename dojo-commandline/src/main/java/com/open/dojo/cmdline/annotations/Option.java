package com.open.dojo.cmdline.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Option {

	String name();

	String argName() default "";

	int index() default -1;

	String description() default "";

}
