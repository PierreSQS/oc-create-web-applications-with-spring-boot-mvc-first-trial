package com.openclassrooms.watchlist.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.annotations.GoodMovie;

public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {

	@Override
	public boolean isValid(WatchlistItem item, ConstraintValidatorContext context) {
		Double rating = 0.0;
		String priority = item.getPriority().trim();
		try {
			rating = Double.parseDouble(item.getRating());
		} catch (NumberFormatException e) {
			rating = 0.0;
		}

		return !((rating >= 8.0) && "L".equals(priority));

	}

}
