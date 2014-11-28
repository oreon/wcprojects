package org.witchcraft.base.entity;


/*
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValidatorClass(UniqueValidator.class)
*/
public @interface Unique { 
	String message() default " already exists";

	String entityName();

	String fieldName();
}