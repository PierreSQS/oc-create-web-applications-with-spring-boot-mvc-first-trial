package com.openclassrooms.watchlist.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.openclassrooms.watchlist.validation.PriorityValidator;

@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriorityValidator.class)
public @interface Priority {
	String message() default "Please enter M, L or H for priority";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	

}
