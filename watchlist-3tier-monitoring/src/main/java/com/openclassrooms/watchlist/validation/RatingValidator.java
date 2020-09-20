package com.openclassrooms.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassrooms.watchlist.annotations.Rating;

public class RatingValidator implements ConstraintValidator<Rating, String> {

	@Override
	public boolean isValid(String ratingStr, ConstraintValidatorContext context) {
	    Double rating = 0.0;
		try {
			rating = Double.parseDouble(ratingStr);
		} catch (NumberFormatException e) {
			
		}
	    return rating > 5.0 && rating < 10.0;
	}

}
