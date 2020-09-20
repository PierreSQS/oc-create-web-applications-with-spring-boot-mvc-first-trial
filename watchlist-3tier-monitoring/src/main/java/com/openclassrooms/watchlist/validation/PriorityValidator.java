package com.openclassrooms.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassrooms.watchlist.annotations.Priority;

public class PriorityValidator implements ConstraintValidator<Priority, String> {

	@Override
	public boolean isValid(String priorityStr, ConstraintValidatorContext context) {
		return priorityStr.trim().length() == 1 && "LMH".contains(priorityStr.trim());
	}

}
