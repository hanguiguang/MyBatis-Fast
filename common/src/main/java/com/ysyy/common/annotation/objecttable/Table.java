//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.annotation.objecttable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	String name() default "";

	boolean isAuxiliary() default false;

	String catalog() default "";

	String schema() default "";

	UniqueConstraint[] uniqueConstraints() default {};

	Index[] indexes() default {};
}
