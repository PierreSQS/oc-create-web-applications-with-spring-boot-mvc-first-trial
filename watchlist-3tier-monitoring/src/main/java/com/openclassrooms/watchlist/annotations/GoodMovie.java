package com.openclassrooms.watchlist.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.openclassrooms.watchlist.validation.GoodMovieValidator;

@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GoodMovieValidator.class)
public @interface GoodMovie {
	String message() default "If the movie is as good as rating 8 then priority should be at least M";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
