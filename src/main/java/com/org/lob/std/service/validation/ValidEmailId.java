package com.org.lob.std.service.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = ValidEmailIdValidator.class)
public @interface ValidEmailId {

	String message() default "name and subject should be Match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String field();

	String fieldMatch();

	@Documented
	@Retention(RUNTIME)
	@Target(TYPE)
	@interface List {
		ValidEmailId[] value();
	}
}
