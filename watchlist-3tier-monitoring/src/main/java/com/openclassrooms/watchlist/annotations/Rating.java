package com.openclassrooms.watchlist.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.openclassrooms.watchlist.validation.RatingValidator;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingValidator.class)
public @interface Rating {
	String message() default "Please 5.0 < Rating < 10.0";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
