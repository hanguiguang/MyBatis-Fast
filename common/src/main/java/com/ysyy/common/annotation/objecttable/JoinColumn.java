//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.annotation.objecttable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JoinColumn {
	String name() default "";

	String family() default "data";

	String qualifier() default "";

	String referencedColumnName() default "";

	boolean unique() default false;

	boolean nullable() default true;

	boolean insertable() default true;

	boolean updatable() default true;

	String columnDefinition() default "";

	String table() default "";
}
