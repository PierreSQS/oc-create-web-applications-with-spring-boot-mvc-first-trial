package com.openclassrooms.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassrooms.watchlist.annotations.BadMovie;
import com.openclassrooms.watchlist.domain.WatchlistItem;

public class BadMovieValidator implements ConstraintValidator<BadMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem item, ConstraintValidatorContext context) {
		Double rating = 0.0;
		
		try {
			rating = Double.valueOf(item.getRating());
		} catch (NumberFormatException e) {
			rating = 7.0;
		}
		
		return !(rating <= 6.0 && item.getComment().length() < 15);
	}

}
