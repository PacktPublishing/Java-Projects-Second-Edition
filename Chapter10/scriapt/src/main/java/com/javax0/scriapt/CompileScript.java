package com.javax0.scriapt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface CompileScript {
	String value();
	String engine() default "";
}