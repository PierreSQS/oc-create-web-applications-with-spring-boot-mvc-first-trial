package com.openclassrooms.watchlist.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.openclassrooms.watchlist.validation.BadMovieValidator;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = BadMovieValidator.class)
public @interface BadMovie {
	String message() default "Please leave a comment of at least 15 characters"+"\n"+"if you mean the film is so bad!";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
