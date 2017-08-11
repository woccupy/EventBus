package com.woccupyl.lib.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Subscribor {
	
	public enum Errors{Catch,Throw};
	
	public Errors error() default Errors.Throw;
	
	public int order() default 10;
	
}
