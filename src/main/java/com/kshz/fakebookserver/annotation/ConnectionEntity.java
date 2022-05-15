package com.kshz.fakebookserver.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.kshz.fakebookserver.validator.ConnectionEntityValidator;

@Constraint(validatedBy = ConnectionEntityValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConnectionEntity {
	String message() default "Invalid Entity";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
